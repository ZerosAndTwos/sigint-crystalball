package steps;

import http.OperationResult;
import model.G4File;
import model.Profile;
import model.TargetGroup;
import org.jbehave.core.annotations.When;

import java.util.*;

import static services.TargetsMigrationService.*;
import static utils.RandomGenerator.*;

public class APITargetsMigrationSteps extends APISteps {

    private static List<Profile> targets = new ArrayList<>();
    private static List<TargetGroup> groups = new ArrayList<>();
    private static List<String> teamNames = new ArrayList<>(Arrays.asList("migration second team","migration team"));
    private static List<String> groupNames = new ArrayList<>(Arrays.asList("QE Group", "Test's Group", "DEV Group",
            "Minsk Group", "Support Group", "Shared Group", "Robbers", "Terrorists", "Others people", "┘à╪¼┘à┘ê╪╣╪⌐ ┘â┘è",
            "┘à╪¼┘à┘ê╪╣╪⌐ ╪¬┘è╪│╪¬", "┘à╪¼┘à┘ê╪╣╪⌐ ╪»┘è┘ü", "┘à╪¼┘à┘ê╪╣╪⌐ ┘à┘è┘å╪│┘â", "┘à╪¼┘à┘ê╪╣╪⌐ ╪º┘ä╪»╪╣┘à", "╪º┘ä┘à╪¼┘à┘ê╪╣╪⌐ ╪º┘ä┘à╪┤╪¬╪▒┘â╪⌐", "╪º┘ä┘ä╪╡┘ê╪╡", "╪º┘ä╪Ñ╪▒┘ç╪º╪¿┘è┘ê┘å", "╪ó╪«╪▒┘ê┘å"));

    @When("Produce targets $targetCount, groups $groupsCount")
    public void produceTargets(String targetCount, String groupsCount) {

        int targetsListSize = Integer.valueOf(targetCount);
        int groupsListSize = Integer.valueOf(groupsCount);

        for (int i = 0; i < groupsListSize; i++) {
            TargetGroup group = objectInitializer.randomEntity(TargetGroup.class);

            group.setId(generateID()); // update ID
            group.setName(getRandomItemFromList(groupNames) + " " + (i + 1)); // update name

            // update team
            Set<String> teamsSet = new HashSet<>();
            Integer teamsCount = generateRandomInteger(1, 2);
            while (teamsSet.size() < teamsCount) {
                teamsSet.add(getRandomItemFromList(teamNames));
            }
            List<String> teams = new ArrayList<>();
            teams.addAll(teamsSet);
            group.setAssignedTeams(teams);

            groups.add(group);
        }

        for (int i = 0; i < targetsListSize; i++) {
            Profile profile = objectInitializer.randomEntity(Profile.class);
            profile.setId(generateID()); // set ID

            Integer targetGroupsCount = generateRandomInteger(1, 2);
            Set<TargetGroup> groupsSet = new HashSet<>();
            while (groupsSet.size() < targetGroupsCount) {
                groupsSet.add(getRandomItemFromList(groups));
            }
            ArrayList<TargetGroup> targetGroups = new ArrayList<>();
            targetGroups.addAll(groupsSet);
            profile.setGroups(targetGroups); //set targetGroupsID

            targets.add(profile);
        }
    }

    @When("Write targets to file")
    public void writeTargetsToFile() {
        writeGroups(groups);
        writeTargets(targets);
        writeIdentifiers(targets);
        writeManualAttributes(targets);
        G4File file = writeFile();

        context.put("importTargetsFile", file);
    }

    @When("Upload targets excel file")
    public void uploadTargets() {
        G4File file = context.get("importTargetsFile", G4File.class);
        OperationResult operationResult = uploadTargetsFile(file);

        context.put("operationResult", operationResult);
    }
}
