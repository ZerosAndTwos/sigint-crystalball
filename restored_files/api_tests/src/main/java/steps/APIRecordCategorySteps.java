package steps;

import conditions.Conditions;
import conditions.Verify;
import data_generator.DataGenerator;
import http.OperationResult;
import model.RecordCategory;
import model.entities.Entities;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import services.RecordCategoryService;
import utils.RandomGenerator;

import java.util.List;

@SuppressWarnings("unused")
public class APIRecordCategorySteps extends APISteps {

    private Logger logger = Logger.getLogger(APIReportSteps.class);
    private RecordCategoryService service = new RecordCategoryService();

    @When("I send create new random record category request")
    public void createRandomRecordCategory() {
        DataGenerator dataGenerator = new DataGenerator(RecordCategory.class);
        RecordCategory recordCategory = (RecordCategory) dataGenerator.produce();
        context.put("recordCategory", recordCategory);
        service.add(recordCategory);

    }

    @When("I send get list of record categories request")
    public void getListOfRecordCategory() {
        OperationResult<List<RecordCategory>> operationResult = service.list();
        context.put("recordCategories", operationResult.getEntity());
    }

    @Then("Record categories list size more than $size")
    @SuppressWarnings("unchecked")
    public void recordCategoriesListSizeShouldbeMoroThan(String size) {
        int lessSize = Integer.valueOf(size);
        List<RecordCategory> categories = context.get("recordCategories", List.class);
        Verify.shouldBe(Conditions.isTrue.element(categories.size() > lessSize));
    }

    @Then("Record category is $criteria list")
    @SuppressWarnings("unchecked")
    public void recordcateroryShouldBeInList(String criteria) {
        RecordCategory category = context.get("recordCategory", RecordCategory.class);
        List<RecordCategory> categories = context.get("recordCategories", List.class);

        boolean contained = false;
        for (RecordCategory recordCategory : categories) {
            if (recordCategory.getName().equals(category.getName())) {
                contained = true;
                break;
            }
        }

        if (criteria.equals("in")) {
            Verify.shouldBe(Conditions.isTrue.element(contained));
        } else {
            Verify.shouldNotBe(Conditions.isTrue.element(contained));
        }
    }

    @When("I get random record category from list")
    @SuppressWarnings("unchecked")
    public void getRandomRecordCategoryfromList() {
        List<RecordCategory> categories = context.get("recordCategories", List.class);
        RecordCategory recordCategory = RandomGenerator.getRandomItemFromList((categories));
        Verify.shouldBe(Conditions.isTrue.element(recordCategory != null));
        context.put("recordCategory", recordCategory);
    }

    @When("I send view record category request")
    public void viewRecordCategory() {
        RecordCategory category = context.get("recordCategory", RecordCategory.class);

        OperationResult<RecordCategory> operationResult = service.view(category.getId());

        Verify.shouldBe(Conditions.isTrue.element(operationResult.getEntity() != null));
        context.put("recordCategory", operationResult.getEntity());
    }

    @Then("Record category is correct")
    public void recordCategoryShouldBeCorrect() {
        RecordCategory category = Entities.getRecordCategories().getLatest();
        RecordCategory recordCategory = context.get("recordCategory", RecordCategory.class);

        Verify.shouldBe(Conditions.equals(category, recordCategory));
    }

    @When("I send update record category request")
    public void updateRecordCategory() {
        RecordCategory category = context.get("recordCategory", RecordCategory.class);

        DataGenerator dataGenerator = new DataGenerator(RecordCategory.class);
        RecordCategory recordCategory = (RecordCategory) dataGenerator.produce();
        recordCategory.setId(category.getId());

        OperationResult operationResult = service.update(recordCategory);

        context.put("recordCategory", recordCategory);
    }
}
