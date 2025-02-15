package ae.pegasus.framework.model.entities;

import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.information_managment.ReportCategory;
import ae.pegasus.framework.model.information_managment.report.Report;
import ae.pegasus.framework.model.information_managment.rfi.RequestForInformation;

import java.io.InputStream;

public class Entities {

    private static UsersList users;
    private static TargetGroupsList targetGroups;
    private static TargetsList targets;
    private static RoleList roles;
    private static GroupList groups;
    private static RecordList records;
    private static ReportList reports;
    private static MasterReportList masterReports;
    private static RequestForInformationList requestForInformations;
    private static RequestForApproveList requestForApproves;
    private static SourceList sources;
    private static ReportCategoriesList reportCategories;
    private static ProfileList profiles;
    private static WhitelistsList whitelists;
    private static TeamList teams;
    private static TagList tags;
    private static ResponsibilityList responsibilities;
    private static TitleList titles;
    private static OrganizationList organizations;
    private static DesignationList designations;
    private static DesignationMappingList designationMappings;
    private static CBEntityList cbEntities;
    private static FinderFileList cbFinderFiles;
    private static FinderCaseList cbFinderCases;

    public static EntityList<DesignationMapping> getDesignationMappings() {
        if (designationMappings == null) {
            designationMappings = new DesignationMappingList();
        }
        return designationMappings;
    }

    public static EntityList<Designation> getDesignations() {
        if (designations == null) {
            designations = new DesignationList();
        }
        return designations;
    }

    public static EntityList<Team> getTeams() {
        if (teams == null) {
            teams = new TeamList();
        }
        return teams;
    }

    public static EntityList<Target> getTargets() {
        if (targets == null) {
            targets = new TargetsList();
        }
        return targets;
    }

    public static EntityList<TargetGroup> getTargetGroups() {
        if (targetGroups == null) {
            targetGroups = new TargetGroupsList();
        }
        return targetGroups;
    }

    private static UsersList getDefaultUsers() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream users_input = classloader.getResourceAsStream("default_users.json");
        return JsonConverter.jsonToObjectsList(users_input, UsersList.class);
    }

    public static EntityList<User> getUsers() {
        if (users == null) {
            users = getDefaultUsers();
        }
        return users;
    }

    public static EntityList<Record> getRecords() {
        if (records == null) {
            records = new RecordList();
        }
        return records;
    }

    public static EntityList<Role> getRoles() {
        if (roles == null) {
            roles = new RoleList();
        }
        return roles;
    }

    public static EntityList<Group> getGroups() {
        if (groups == null) {
            groups = new GroupList();
        }
        return groups;
    }

    public static EntityList<Report> getReports() {
        if (reports == null) {
            reports = new ReportList();
        }
        return reports;
    }

    public static EntityList<Source> getSources() {
        if (sources == null) {
            sources = new SourceList();
        }
        return sources;
    }

    public static EntityList<ReportCategory> getReportCategories() {
        if (reportCategories == null) {
            reportCategories = new ReportCategoriesList();
        }
        return reportCategories;
    }

    public static EntityList<Profile> getProfiles() {
        if (profiles == null) {
            profiles = new ProfileList();
        }
        return profiles;
    }

    public static EntityList<Whitelist> getWhitelists() {
        if (whitelists == null) {
            whitelists = new WhitelistsList();
        }
        return whitelists;
    }

    public static EntityList<Tag> getTags() {
        if (tags == null) {
            tags = new TagList();
        }
        return tags;
    }

    public static EntityList<Responsibility> getResponsibilities() {
        if (responsibilities == null) {
            responsibilities = new ResponsibilityList();
        }
        return responsibilities;
    }

    public static EntityList<Title> getTitles() {
        if (titles == null) {
            titles = new TitleList();
        }
        return titles;
    }

    public static EntityList<Organization> getOrganizations() {
        if (organizations == null) {
            organizations = new OrganizationList();
        }
        return organizations;
    }

    public static EntityList<SearchRecord> getCBEntities() {
        if (cbEntities == null) {
            cbEntities = new CBEntityList();
        }
        return cbEntities;
    }

    public static FinderFileList getFinderFiles() {
        if (cbFinderFiles == null) {
            cbFinderFiles = new FinderFileList();
        }
        return cbFinderFiles;
    }

    public static FinderCaseList getFinderCases() {
        if (cbFinderCases == null) {
            cbFinderCases = new FinderCaseList();
        }
        return cbFinderCases;
    }

    public static EntityList<RequestForInformation> getRequestForInformations() {
        if (requestForInformations == null) {
            requestForInformations = new RequestForInformationList();
        }
        return requestForInformations;
    }

    public static RequestForApproveList getRequestForApproves() {
        if (requestForApproves == null) {
            requestForApproves = new RequestForApproveList();
        }
        return requestForApproves;
    }

    public static MasterReportList getMasterReports() {
        if (masterReports == null) {
            masterReports = new MasterReportList();
        }
        return masterReports;
    }

}
