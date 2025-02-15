package ae.pegasus.framework.steps;

import ae.pegasus.framework.errors.NullReturnException;
import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.CBEntityList;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.model.entities.EntityList;
import ae.pegasus.framework.services.FinderFileService;
import ae.pegasus.framework.services.ProfileService;
import ae.pegasus.framework.utils.DateHelper;
import ae.pegasus.framework.verification.profiler.ProfileMergeVerification;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ae.pegasus.framework.ingestion.IngestionService.INJECTIONS_FILE;
import static ae.pegasus.framework.json.JsonConverter.jsonToObject;
import static ae.pegasus.framework.json.JsonConverter.toJsonString;
import static ae.pegasus.framework.steps.APIFinderFileSteps.getRandomFinderFile;
import static ae.pegasus.framework.utils.DateHelper.isTimeout;
import static ae.pegasus.framework.utils.FileHelper.readTxtFile;
import static ae.pegasus.framework.utils.RandomGenerator.getRandomItemFromList;
import static ae.pegasus.framework.utils.StepHelper.compareByCriteria;
import static ae.pegasus.framework.utils.StringUtils.readStringFromFile;
import static ae.pegasus.framework.utils.StringUtils.saveStringToFile;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class APIProfileSteps extends APISteps {

    private static final ProfileService service = new ProfileService();

    @When("I send create profile request")
    public void createProfile() {
        Profile profile = getRandomProfile();
        FinderFile finderFile = Entities.getFinderFiles().getLatest();

        service.addToFile(profile, finderFile);
        service.add(profile);
        context.put("profile", profile);
    }

    private static Profile getRandomProfile() {
        return objectInitializer.randomEntity(Profile.class);
    }

    private void profilesShouldBeEquals(Profile expected, Profile actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getActive(), actual.getActive());
        assertEquals(expected.getProperties().getDescription(), actual.getProperties().getDescription());

        assertTrue(APITargetGroupSteps.equalsTargetGroups(actual.getGroups(), expected.getGroups()));

        assertEquals(expected.getEntities(), actual.getEntities());
        //FIXME Assert.assertEquals(expected.getIdentifierCount(), actual.getIdentifierCount());

        assertEquals(expected.getJsonType(), actual.getJsonType());
        assertEquals(expected.getCategory(), actual.getCategory());
        assertEquals(expected.getMergingProfilesIDs(), actual.getMergingProfilesIDs());
        assertEquals(expected.getCriminalRecord(), actual.getCriminalRecord());
        assertEquals(expected.getClassification(), actual.getClassification());

        if (expected.getTarget() == null) {
            assertTrue(actual.getTarget() == null || !actual.getTarget());
        } else {
            assertEquals(expected.getTarget(), actual.getTarget());
        }
    }

    @When("I send get profile details request")
    public void getProfileDetails() {
        Profile profile = Entities.getProfiles().getLatest();
        if (profile == null) profile = context.get("profile", Profile.class);

        OperationResult<Profile> result = service.view(profile.getId());

        if (result.isSuccess()) {
            context.put("profile", result.getEntity());
        }
    }

    @Then("Profile is correct")
    public void profileIsCorrect() throws NullReturnException {
        Profile requested = context.get("profile", Profile.class);
        Profile created = service.getByName(requested.getName());

        profilesShouldBeEquals(requested, created);
    }

    @When("I send delete profile request")
    public void deleteProfile() {
        Profile profile = Entities.getProfiles().getLatest();
        context.put("profile", profile);
        service.remove(profile);
    }

    @Then("Profile list size $criteria $value")
    public void profileListMoreThan(String criteria, String value) {
        List<Profile> profiles = context.get("profileList", List.class);

        assertTrue(compareByCriteria(criteria, profiles.size(), Integer.valueOf(value)));
    }

    @When("I send update profile request")
    public void updateProfile() {
        Profile profile = context.get("profile", Profile.class);

        Profile updatedProfile = getRandomProfile();
        updatedProfile.setId(profile.getId());
        updatedProfile.setFiles(profile.getFiles());
        context.put("profile", updatedProfile);

        service.update(updatedProfile);
    }

    @When("I send merge two profile into one request")
    public void mergeTwoProfileIntoOne() {
        EntityList<Profile> profileEntityList = Entities.getProfiles();
        assertTrue("Profile EntityList size shouldHave at least 2 profiles", profileEntityList.size() > 1);
        Profile firstProfile = profileEntityList.getLatest();
        Profile secondProfile = profileEntityList.getEntities().get(profileEntityList.size() - 2);
        assertNotNull(firstProfile.getId());
        assertNotNull(secondProfile.getId());

        context.put("firstProfileToMerge", firstProfile);
        context.put("secondProfileToMerge", secondProfile);

        OperationResult<Profile> operationResult = service.merge(firstProfile, secondProfile);
        context.put("profile", operationResult.getEntity());
    }

    @Then("Merged profile is correct")
    public void mergedProfileShouldBeCorrect() {
        Profile firstProfileToMerge = context.get("firstProfileToMerge", Profile.class);
        Profile secondProfileToMerge = context.get("secondProfileToMerge", Profile.class);
        Profile mergedProfile = context.get("profile", Profile.class);

        ProfileMergeVerification verification = new ProfileMergeVerification();
        verification.verify(firstProfileToMerge, secondProfileToMerge, mergedProfile);
    }

    @When("I send get $number merged profile details request")
    public void getOneOfMergedProfile(String number) {
        Profile firstProfileToMerge = context.get("firstProfileToMerge", Profile.class);
        Profile secondProfileToMerge = context.get("secondProfileToMerge", Profile.class);

        if (number.equals("first")) {
            service.view(firstProfileToMerge.getId());
        } else {
            service.view(secondProfileToMerge.getId());
        }
    }

    @When("get random profile from profile list")
    public void getRandomProfileFromList() {
        List<Profile> profiles = context.get("profileList", List.class);

        context.put("profile", getRandomItemFromList(profiles));
    }


    @When("add $count $type from profile:$profileName to injection file")
    public void addPhoneToInjectionFile(String count, String type, String profileName) {
        Profile profile = service.getByName(profileName);

        IdentifierType identifierType = IdentifierType.valueOf(type);
        List<String> identifiersList = getTargetIdentifiersList(profile, identifierType, Integer.valueOf(count));

        DataInjection injections = new File(INJECTIONS_FILE.toString()).exists() ?
                jsonToObject(readStringFromFile(INJECTIONS_FILE.toString()), DataInjection.class) : new DataInjection();

        switch (identifierType) {
            case PHONE_NUMBER:
                assertTrue(injections.getPhones().add(getRandomItemFromList(identifiersList)));
                break;
            case IMSI:
                List<Long> imsis = identifiersList.stream().map(Long::valueOf).collect(Collectors.toList());
                assertTrue(injections.getImsis().add(getRandomItemFromList(imsis)));
                break;
            case IMEI:
                List<Long> imeis = identifiersList.stream().map(Long::valueOf).collect(Collectors.toList());
                assertTrue(injections.getImeis().add(getRandomItemFromList(imeis)));
                break;
            default:
                throw new AssertionError("Unsupported identifierType:" + identifierType);
        }

        log.info("INJECTIONS_FILE:" + toJsonString(injections));
        saveStringToFile(toJsonString(injections), INJECTIONS_FILE.toString());
    }

    private List<String> getTargetIdentifiersList(Profile profile, IdentifierType identifierType, Integer count) {
        List<String> targetIdentifiers = new ArrayList<>();
        profile.getIdentifiers().stream()
                .filter(identifier -> identifier.getType() == identifierType)
                .forEach(identifier -> targetIdentifiers.add(identifier.getValue()));

        List<String> identifiers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            identifiers.add(getRandomItemFromList(targetIdentifiers));
        }
        return identifiers;
    }

    @When("send get profile identifierAggregations request")
    public void getProfileIdentifierAggregations() {
        Profile profile = context.get("profile", Profile.class);

        OperationResult<List<IdentifierSummary>> result = service.getIdentifierAggregations(profile.getId());
        context.put("identifierSummaryList", result.getEntity());
    }


    @Then("identifierAggregations hit counts for:$identifierType of profile:$name should incremented")
    public void identifierAggregationsCountsShouldIncremented(String identifierType, String name) {
        List<IdentifierSummary> identifiers = context.get("identifierSummaryList", List.class);
        Profile profile = context.get("profile", Profile.class);
        List<IdentifierSummary> updatedIdentifiers = service.getIdentifierAggregations(profile.getId()).getEntity();

        List<IdentifierSummary> prevIdentifierSummary = identifiers.stream()
                .filter(identifierSummary -> identifierSummary.getType() == IdentifierType.valueOf(identifierType))
                .collect(Collectors.toList());
        List<IdentifierSummary> newIdentifierSummary = updatedIdentifiers.stream()
                .filter(identifierSummary -> identifierSummary.getType() == IdentifierType.valueOf(identifierType))
                .collect(Collectors.toList());

        log.info("Previous:" + toJsonString(prevIdentifierSummary));
        log.info("New:" + toJsonString(newIdentifierSummary));

        final int[] hitCount = {0};
        prevIdentifierSummary.forEach(identifier -> hitCount[0] -= identifier.getTargetHitsCount());
        newIdentifierSummary.forEach(identifier -> hitCount[0] += identifier.getTargetHitsCount());
        assertEquals(1, hitCount[0]);
    }

    @Given("Find or create test target from json:$target_file")
    public void uploadTestTarget(String targetFile) {
        Profile target = jsonToObject(readTxtFile(targetFile), Profile.class);

        // set requirement permissions
        ReqPermission reqPermission = objectInitializer.randomEntity(ReqPermission.class);
        target.setReqPermissions(new ArrayList<>(Stream.of(reqPermission).collect(Collectors.toList())));

        // find target in system
        log.info("Try find target:" + target.getName() + " in system...");
        FinderFileService fileService = new FinderFileService();
        FinderFileSearchFilter filter = new FinderFileSearchFilter();
        filter
                .setQuery(target.getName())
                .setSortField("name")
                .setPageSize(100);

        // get only profiles
        OperationResult<List<ProfileAndTargetGroup>> operationResult = fileService.searchFileMembers(filter);
        List<Profile> profiles = fileService.extractEntitiesByTypeFromResponse(operationResult, ProfilerJsonType.Profile);

        // filter them by exact name
        Profile finalTarget = target;
        List<Profile> filteredProfiles = profiles.stream()
                .filter(profile -> profile.getName().contains(finalTarget.getName()))
                .collect(Collectors.toList());

        if (filteredProfiles.isEmpty()) {
            log.info("Target not found, create and publish target from:" + targetFile);
            //create file
            OperationResult<FinderFile> fileOperationResult = fileService.add(getRandomFinderFile());
            if (fileOperationResult.isSuccess()) {
                // add to file
                target = service.addToFile(target, fileOperationResult.getEntity());
                // add assigned team
                Assert.assertTrue("Unable add assigned team to target",
                        target.getAssignedTeams()
                                .add(getRandomItemFromList(appContext.getLoggedUser().getUser().getParentTeamIds())));
                OperationResult<Profile> targetResult = service.add(target);
                if (targetResult.isSuccess()) {
                    context.put("profile", targetResult.getEntity());
                } else {
                    throw new OperationResultError(targetResult);
                }
            } else {
                throw new OperationResultError(fileOperationResult);
            }
        } else {
            log.info("Target " + target.getName() + " already exist in system");
            OperationResult<Profile> result = service.view(getRandomItemFromList(filteredProfiles).getId());
            if (!result.isSuccess()) {
                throw new AssertionError(result.getCode() + " " + result.getMessage());
            } else {
                context.put("profile", result.getEntity());
            }
        }
    }

    @When("I send get profile summary request")
    public void getProfileSummary() {
        Profile profile = Entities.getProfiles().getLatest();
        OperationResult<Profile> result = service.summary(profile.getId());

        if (result.isSuccess()) {
            context.put("profile", result.getEntity());
        }
    }

    @AfterStory
    public void cleanUp() {
        new ArrayList<>(Entities.getProfiles().getEntities())
                .stream()
                .filter(profile -> profile.getName().contains("QE auto"))
                .forEach(service::remove);
    }

    @When("upload new target image:$image to target")
    public void uploadTargetImage(String image) {
        Profile profile = context.get("profile", Profile.class);

        if (profile.getUploadedImage() != null && !profile.getUploadedImage().isEmpty()) {
            if (!service.deleteImage(profile.getId()).isSuccess()) {
                log.error("Unable delete current target image");
            }
        }

        URL url = getClass().getClassLoader().getResource(image);
        if (url != null) {
            File file = new File(url.getFile());
            OperationResult<FileMetaData> result = service.uploadImage(profile, file);
            profile.setUploadedImage(result.getEntity().getFileId());

            context.put("profile", profile);
            context.put("fileMetaData", result.getEntity());
        } else {
            throw new AssertionError("File: " + image + " not found");
        }
    }

    @Then("Profile contain uploaded image")
    public void profileShouldContainUploadedImage() {
        Profile profile = context.get("profile", Profile.class);
        FileMetaData fileMetaData = context.get("fileMetaData", FileMetaData.class);

        assertNotNull("Profile uploadedImage is null!", profile.getUploadedImage());

        Pattern pattern = Pattern.compile("(/api/upload-platform/files/).*(/content)");
        assertTrue(pattern.matcher(profile.getUploadedImage()).find());
        assertTrue(pattern.matcher(fileMetaData.getFileId()).find());
    }

    @When("upload audio file to profile")
    public void uploadAudioFile() {
        File audioFile = context.get("audioFile", File.class);
        Profile profile = Entities.getProfiles().getLatest();

        OperationResult<VoiceFile> result = service.uploadAudioFile(profile, audioFile);

        context.put("voiceFile", result.getEntity());
        context.put("profile", profile);
    }

    @Then("uploaded audio file is processed")
    public void uploadedAudioFileIsProcesse() {
        VoiceFile voiceFile = context.get("voiceFile", VoiceFile.class);
        Profile profile = context.get("profile", Profile.class);

        Integer WAITING_TIME = 5 * 60;
        Integer POLLING_INTERVAL = 10;
        Date deadline = DateHelper.getDateWithShift(WAITING_TIME);

        CBEntityList entities = new CBEntityList();
        entities.setEntities(service.getVoiceEvents(profile).getEntity());

        while (entities.getEntity(voiceFile.getVoiceEventId()) == null && !isTimeout(deadline)) {
            DateHelper.waitTime(POLLING_INTERVAL);
            entities.setEntities(service.getVoiceEvents(profile).getEntity());
        }
        assertNotNull("Unable process uploaded audio voice", entities.getEntity(voiceFile.getVoiceEventId()));

        context.put("voiceEvent", entities.getEntity(voiceFile.getVoiceEventId()));
    }

    @When("create voiceID for profile")
    public void createVoiceIDForProfile() {
        SearchRecord voiceEvent = context.get("voiceEvent", SearchRecord.class);
        Profile profile = context.get("profile", Profile.class);

        // create VoiceID from random channel of random voice event in this target
        List<String> channelId;
        try {
            channelId = (ArrayList<String>) voiceEvent.getAttributes().get("CHANNEL_ID");
        } catch (NullPointerException e) {
            throw new AssertionError("attribute CHANNEL_ID not found:\n" + toJsonString(voiceEvent));
        }
        assertFalse("CHANNEL_ID field is empty!\n" + toJsonString(voiceEvent),
                channelId == null || channelId.isEmpty());

        VoiceRecord record = new VoiceRecord();
        record.setId(voiceEvent.getId());
        record.setChannel(Integer.valueOf(getRandomItemFromList(channelId)));

        Voice voice = new Voice();
        voice.getRecords().add(record);

        OperationResult<Voice> result = service.createVoiceID(profile, voice);
        context.put("voice", result.getEntity());
        context.put("profile", profile);
    }

    @Then("profile contain created voiceID")
    public void profileContainVoiceID() {
        Voice voice = context.get("voice", Voice.class);
        Profile profile = context.get("profile", Profile.class);

        assertEquals(voice.getId(), profile.getVoiceModelId());
    }

    @When("Add $identifierType identifier to profile request")
    public void addIdentifierToProfile(String identifierType) {
        Profile profile = Entities.getProfiles().getLatest();
        List<SearchRecord> participants = context.get("participants", List.class);

        SearchRecord participant = getRandomItemFromList(participants);
        if (participant.getIdentifiers().containsKey(identifierType)) {
            Object o = participant.getIdentifiers().get(identifierType);
            String json = toJsonString(o);
            Identifier[] identifiers = jsonToObject(json, Identifier[].class);
            Identifier identifier = identifiers[0];
            identifier.setSources(participant.getSources());

            profile.getIdentifiers().add(identifier);
        } else {
            throw new AssertionError("Event participant has no " + identifierType + ":\n" + toJsonString(participant));
        }

        service.update(profile);
        context.put("profile", profile);
    }

    @When("Get random voice event from profile")
    public void getRandomVoiceEventFromProfile() {
        Profile profile = Entities.getProfiles().getLatest();

        OperationResult<List<SearchRecord>> result = service.getVoiceEvents(profile);
        List<SearchRecord> voiceEvents = result.getEntity();
        SearchRecord voiceEvent = Objects.requireNonNull(getRandomItemFromList(voiceEvents));

        context.put("voiceEvent", voiceEvent);
    }

    @When("Remove Voice ID request")
    public void removeVoiceID() {
        Profile profile = Entities.getProfiles().getLatest();
        service.removeVoiceID(profile);
    }
}