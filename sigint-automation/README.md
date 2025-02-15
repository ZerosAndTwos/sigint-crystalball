**Summary**:
- General Description
- How to create a new test
- Create api test
- Create UI test
- Reporting test results
- How to start tests execution
- Re-run failed test cases
- Issue and TMS links 

**General Description**.

G4 framework source code is built using module approach. There are 3 modules: api, ui and common.
Common module holds code that is shared across all other modules. Main components:
- Model library that includes all G4 entities: user, target, reports, records etc. Every entity that is created during test execution must be deleted with help of collections classes in a test method annotated with @AfterStories.
- Zephyr and JIRA modules. Those modules are used for tests results reporting to Zephyr.
- Allure reports. Incapsulates logic of generating html reports for every test run.
- G4HttpClient. Is used to send http requests. As an response G4Response object is used
- Steps class. Incapsulated operations that need to be done before/after or/and test case/scenario/story and are common for both modules: api and ui.
- TeelaEmbedabble. Runner class for JBehave framework. Here is defined configuration that is used during text execution: defined path where stories are located, associated steps etc.
- AppContext. Share entities that can be used across multiply tests. This are: logged user, countries, languages and dictionary. Dictionary is used in CB project to store static content: record types and sources;
- Properties: holds all available properties within CB testing framework. Includes properties for execution controlling, JIRA and Zephyr connection etc.
- RunContext. Dictionary based class to share user-defined variables that should be shared between tests

**How to create a new test.**\
Before proceeding further a component test is belong to need to be defined: either api or ui. Create new test in a corresponding module of the framework. Sequence is the following:
1) Create a story file. All story files ( story file can be considered as a test suite)  located in resources package of the module, like /src/main/resources/stories/*.story. Create new story file if it doesn't exist with extension .story.
2) Create a scenario. Scenario is mapped to test case instance and should match Gherkin syntax.

    Sample scenario:
    
        Scenario: On Login request with correct credentials token should be sent
        Given as admin user
        When I sent sign in request with correct credentials
        Then I got response code 200
        And I got token in response

3) Create a steps .java POJO class. Map steps to java methods. Steps .java class should be created under /src/main/java/steps. All step related business logic should be implemented here. 
    
    Sample mapping:

        @Then("I got response code $real")
        public void checkResponseCode(String real) {
            Integer actual  = context.getFromRunContext("code", Integer.class);
            Integer expected = Integer.valueOf(real);
            Assert.assertEquals("Actual code:"+actual + Expected code:"+real, actual, expected);
        }
        
4) Map steps class to java bean using sprint configuration xml file located under /src/main/resources/steps.xml. 

    It looks like this:

        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://www.springframework.org/schema/beans
                   http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">
            <bean id="loginSteps" class="steps.UILoginSteps">
            </bean>
        </beans>
        
5) Create a java library that incapsulates logic of a step.
 
    Rules are the following: 
    
    - In case there is no such Entity you would like to manipulate, create new Entity class in 'common' module that extends 'Entity' abstract class. Add new EntityList class that extends abstract class 
    EntityList<Entity>. Register class to Entities class. This is needed in order all test data created during test will be deleted further.
    - To manipulate with Entity create new service class that incapsulates CRUD operations. This class must implement EntityService<Entity> interface. 
    - All business related logic should be implemented in steps class, not service


**Logging**

To log events use log4j instance Logger log = Logger.getRootLogger()

**Create api test**

All api tests are located in api tests module. High-level architecture of module is built with MVC pattern where models are: HttpRequest, CB entities. Controllers are services and views are steps classes.

Basic flow to create a test on API:

- Extend HttpRequest class to create a new HttpRequest according to test.
- Implement EntityService interface within CRUD operations
- Evaluate result of operations via object 'OperationResult' in step class

**Create UI test**

All classes and modules that are specific to ui tests are located in module 'ui_tests'. High level architecture of module is built on PageObject model with MVC on top. 
PageObject classes incapsulate actions on page, e.g. input text, click on button, select value in drop-down etc. Controller classes provide interface to implement business actions on 
a page or across multiply pages, e.g. search for an entity requires steps: fill in filter, click on search button, evaluate results. Step classes are client classes that use controllers' methods as steps implementations.

To implement a test that uses a context page (context page is a page that is built from ToolBar (actions bar), SearchToolBar (search filters and appropriate actions) and EntityTable (list of entities):

- Implement ContextPageFactory interface : create methods to return exact copies of EntityTable, EntityToolBar and SearchToolBar.
- Implement Context interface by passing implementation of ContextPageFactory
- Extend ContextPage abstract class and define exact context that should be returned on context() method call
- Implement PageControllerFactory: create methods to return exact copies of SearchController, TableController and ToolBarController.
- Pass instance of PageControllerFactory to this page controller to implement business actions.
= Create a step class to use controllers.

**Reporting test results**

G4 Automation uses Allure framework as reporting tool. 

On every test fail system attaches logs of services in case of docker configuration. Note that logs will be collected only if docker is deployed on the same host where tests are running. For UI tests there is a screenshot. To view screenshot or/and logs go to tab "Defects' -> under issue you can find attached artifacts.
In case you need to browse allure report locally use allure commandline tool to generate html report from .xml output in directory target/allure-results

**How to start tests execution**

To start tests execution maven task 'run-stories-as-embeddables' is used. It searches for Runner class by pattern *Runner.java which incapsulates JBehave embeddable runner logic: define steps, stories, configuration.

    mvn clean install -Pui (to start ui tests) -Papi (to start api tests) {run options}

Run options:
- browser = String value. Applicable for ui module only. Defines browser to run tests within. Default value: chrome
- remoteRun - boolean. Applicable for ui tests only. Defines if ui tests should be run on either remote selenium hub or locally. 
- seleniumHub - String value. Applicable for ui tests only. In case option remoteRun is set to true, dockerized environment( selenium hub + chrome node + firefox node)  can be deployed from project root directory to localhost. Call 'docker-compose up -d' for deployment ( in this case pass seleniumHub=localhost).
- metaFilters - String value. Used to control scope of tests execution. default meta matcher :== ([+|-] [name] [value pattern])+

**Re-run failed test cases**

Jbehave doesn't have easy way to re-run failed test case by name or id. The best way to re-run a single test is to use a meta filter:

1) Checkout source code from gitlab: git@gitlab.pegasus.ae:ind/cb/qe/sigint-automation.git
2) For ui test go to module 'ui-tests', for api test go to module 'api-tests'
3) Navigate to story location /src/main/java/resources/stories
4) Find your test case by title and mark it with MetaFilter @wip 

        Scenario: On Login request with correct credentials token should be sent
        Meta:@wip
        Given as admin user
        When I sent sign in request with correct credentials
        Then I got response code 200
        And I got token in response

5) Start test execution:

        mvn clean install -Pui (to start ui tests) -Papi (to start api tests) -DappURL={g4_url} -DremoteRun={true/false} 
        -DseleniumHub={selenium_hub} -DmetaFilter=+wip
        
**Issue and TMS links**

Allure report support Issue and TMS links.
You can link your tests to some resources such as TMS (test management system) or bug tracker.

    Scenario: scenario description
    Meta:
    @issue CB-0001
    @test 12345
    Given a system state
    When I do something
    Then system is in a different state