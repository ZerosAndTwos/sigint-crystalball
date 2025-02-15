package services;

import http.OperationResult;
import model.Report;
import model.ReportOwner;
import model.ReportStatus;
import model.User;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;

public class ReportCreateService {
    
    
    
    public static Report generateReport() {
        Report report = new Report();
        report.setSubject("subject:" + RandomStringUtils.randomAlphabetic(4));
        report.setStatus(ReportStatus.DRAFT);
        initOwner(report);
        report.setCreatedAt(new Date());
        return report;
    }
    
    private static void initOwner(Report report) {
        UserService userService = new UserService();
        OperationResult<User> operationResult = userService.me();
        User user = operationResult.getEntity();
        User reportUser = new User();
        reportUser.setId(user.getId());

        ReportOwner owner = new ReportOwner();
        owner
                .setRole(userService.getReportRole(user))
                .setUser(reportUser);
        report.setAuthorId(user.getId());
        report.setOwner(owner);
        
    }
}
