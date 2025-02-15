package ae.pegasus.framework.services;

import ae.pegasus.framework.errors.OperationResultError;
import ae.pegasus.framework.http.G4Response;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.requests.ProfileRequest;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;

public class ProfileService implements EntityService<Profile> {

    private static final ProfileRequest request = new ProfileRequest();
    private static final Logger log = Logger.getLogger(ProfileService.class);

    @Override
    public OperationResult<Profile> add(Profile entity) {
        log.info("Creating new Profile: " + toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.create(entity));

        OperationResult<Profile> operationResult = new OperationResult<>(response, Profile.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getProfiles().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    @Override
    public OperationResult remove(Profile entity) {
        log.info("Deleting profile id: " + entity.getId());

        G4Response response = g4HttpClient.sendRequest(request.delete(entity.getId()));

        OperationResult operationResult = new OperationResult<>(response, RequestResult.class);
        if (operationResult.isSuccess()) {
            Entities.getProfiles().removeEntity(entity);
        }
        return operationResult;
    }

    @Override
    public OperationResult<List<Profile>> search(SearchFilter filter) {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<List<Profile>> list() {
        throw new NotImplementedException("");
    }

    @Override
    public OperationResult<Profile> update(Profile entity) {
        log.info("Update Profile: " + toJsonString(entity));
        G4Response response = g4HttpClient.sendRequest(request.update(entity));

        OperationResult<Profile> operationResult = new OperationResult<>(response, Profile.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getProfiles().addOrUpdateEntity(operationResult.getEntity());
        } else {
            throw new OperationResultError(operationResult);
        }
        return operationResult;
    }

    @Override
    public OperationResult<Profile> view(String id) {
        log.info("Getting profile details id: " + id);
        G4Response response = g4HttpClient.sendRequest(request.view(id));

        OperationResult<Profile> operationResult = new OperationResult<>(response, Profile.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getProfiles().addOrUpdateEntity(operationResult.getEntity());
        }
        return operationResult;
    }

    public OperationResult<Profile> merge(Profile firstProfile, Profile secondProfile) {
        log.info("Merge two profile into one");

        G4Response response = g4HttpClient.sendRequest(request.merge(firstProfile.getId(), secondProfile.getId()));

        OperationResult<Profile> operationResult = new OperationResult<>(response, Profile.class, "data");
        if (operationResult.isSuccess()) {
            Entities.getProfiles().addOrUpdateEntity(operationResult.getEntity());
        }

        return operationResult;
    }

    public OperationResult<List<IdentifierSummary>> getIdentifierAggregations(String profileId) {
        log.info("GET /api/profiler/profiles/" + profileId + "/identifierAggregations");
        G4Response response = g4HttpClient.sendRequest(request.identifiersSummary(profileId));

        OperationResult<IdentifierSummary[]> operationResult = new OperationResult<>(response, IdentifierSummary[].class, "data");
        if (operationResult.isSuccess()) {
            Entities.getProfiles().getEntity(profileId).setIdentifiersSummary(new ArrayList<>(Arrays.asList(operationResult.getEntity())));
            return new OperationResult<>(response, Arrays.asList(operationResult.getEntity()));
        } else {
            throw new AssertionError("GET /api/profiler/profiles/" + profileId + "/identifierAggregations return:\n" + toJsonString(response));
        }
    }

    public Profile getByName(String name) {
        for (Profile entity : Entities.getProfiles()) {
            if (entity.getName().equals(name)) {
                return entity;
            }
        }

        log.error("Can't find profile by name:" + name);
        throw new AssertionError("Can't find profile by name:" + name);
    }

    public OperationResult<Profile> summary(String id) {
        log.info("Getting profile summary, id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.summary(id));

        OperationResult<Profile> result = new OperationResult<>(response, Profile.class, "data");
        if (result.isSuccess()) {
            Entities.getProfiles().addOrUpdateEntity(result.getEntity());
        }
        return result;
    }

    public OperationResult<Profile> getOrCreateDraft(String id) {
        log.info("GetOrCreateDraft profile, id:" + id);
        G4Response response = g4HttpClient.sendRequest(request.getOrCreateDraft(id));

        OperationResult<Profile> result = new OperationResult<>(response, Profile.class, "data");
        if (result.isSuccess()) {
            Entities.getProfiles().addOrUpdateEntity(result.getEntity());
        }
        return result;
    }

    public Profile addToFile(Profile profile, FinderFile file) {
        profile.getFiles().add(new Profile.FinderFile(file.getId(), file.getName()));
        return profile;
    }

    public OperationResult<FileMetaData> uploadImage(Profile profile, File file) {
        log.info("Uploading image to profile id: " + profile.getId() + ", name: " + profile.getName());
        G4Response response = g4HttpClient.sendRequest(request.imageUpload(profile.getId(), file));

        OperationResult<FileMetaData> result = new OperationResult<>(response, FileMetaData.class, "data");
        if (result.isSuccess()) {
            return result;
        } else {
            throw new OperationResultError(result);
        }
    }

    public OperationResult deleteImage(String profileID) {
        G4Response response = g4HttpClient.sendRequest(request.deleteImage(profileID));
        return new OperationResult(response);
    }

    public OperationResult<List<SearchRecord>> getVoiceEvents(Profile profile) {
        log.info("Get voice events for profileId: " + profile.getId() + " name:" + profile.getName());
        G4Response response = g4HttpClient.sendRequest(request.voiceEvents(profile.getId()));

        OperationResult<SearchRecord[]> result = new OperationResult<>(response, SearchRecord[].class, "data");
        if (result.isSuccess()) {
            return new OperationResult<>(response, Arrays.asList(result.getEntity()));
        } else {
            throw new OperationResultError(result);
        }
    }

    public OperationResult<Voice> getVoiceModel(String profileId) {
        log.info("Get voice print for profileId: " + profileId);
        G4Response response = g4HttpClient.sendRequest(request.voice(profileId));

        OperationResult<Voice> result = new OperationResult<>(response, Voice.class, "data");
        if (result.isSuccess()) {
            return result;
        } else {
            throw new OperationResultError(result);
        }
    }

    public OperationResult<Voice> createVoiceID(Profile profile, Voice voice) {
        log.info("Create VoiceID for profile id:" + profile.getId() + " name: " + profile.getName() + ", payload:" + toJsonString(voice));
        G4Response response = g4HttpClient.sendRequest(request.createVoiceID(profile.getId(), voice));

        OperationResult<Voice> result = new OperationResult<>(response, Voice.class, "data");
        if (result.isSuccess()) {
            profile.setVoiceModelId(result.getEntity().getId());
            return result;
        } else {
            throw new OperationResultError(result);
        }
    }

    public OperationResult removeVoiceID(Profile profile) {
        log.info("Remove VoiceID for profile id:" + profile.getId() + " name: " + profile.getName());
        G4Response response = g4HttpClient.sendRequest(request.removeVoiceID(profile));
        return new OperationResult<>(response);
    }

    public OperationResult<VoiceFile> uploadAudioFile(Profile profile, File file) {
        log.info("Create voice print for profile id:" + profile.getId() + ", name:" + profile.getName());
        log.info("Uploading file:" + file.getAbsolutePath());
        G4Response response = g4HttpClient.sendRequest(request.uploadAudioFile(profile.getId(), file));
        log.info(response.getCode() + " " + response.getMessage());

        OperationResult<VoiceFile> result = new OperationResult<>(response, VoiceFile.class, "data");
        if (result.isSuccess()) {
            profile.getVoiceFiles().add(result.getEntity());
            return result;
        } else {
            throw new OperationResultError(result);
        }
    }
}
