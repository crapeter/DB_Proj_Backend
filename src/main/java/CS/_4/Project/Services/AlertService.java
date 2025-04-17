package CS._4.Project.Services;

import CS._4.Project.DTOs.AlertDto;
import CS._4.Project.Models.*;
import CS._4.Project.Repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class AlertService {
  private final Random random = new Random();

  private final AlertRepository alertRepo;
  private final UserRepository userRepo;
  private final CallerRepository callerRepo;
  private final ResourceRepository resourceRepo;
  private final DepartmentRepository departmentRepo;
  private final ResponseTeamRepository responseTeamRepo;
  private final ResponseTeamIncidentRepository responseTeamIncidentRepo;
  private final IncidentReportService incidentReportService;

  public AlertService(
      AlertRepository alertRepo, UserRepository userRepo,
      CallerRepository callerRepo, IncidentReportService incidentReportService,
      ResourceRepository resourceRepo, DepartmentRepository departmentRepo,
      ResponseTeamRepository responseTeamRepo, ResponseTeamIncidentRepository responseTeamIncidentRepo
  ) {
    this.alertRepo = alertRepo;
    this.userRepo = userRepo;
    this.callerRepo = callerRepo;
    this.incidentReportService = incidentReportService;
    this.resourceRepo = resourceRepo;
    this.departmentRepo = departmentRepo;
    this.responseTeamRepo = responseTeamRepo;
    this.responseTeamIncidentRepo = responseTeamIncidentRepo;
  }

  /*
   * TODO:
   *  - Implement the logic to create an alert
   *  - Validate the AlertDto object
   *  - Save the alert to the database
   *  - Notify the user about the alert creation
   *  - Dispatch a response team to handle the alert (this is ResponseTeamIncident, which joins the incident report
   *   and response team)
   *  - Create an incident report for the alert
   * */
  public ResponseEntity<String> createAlert(AlertDto alertDto) {
    // creation of the alert
    User user = userRepo.findByEmail(alertDto.getCallerEmail());
    Caller caller = callerRepo.findById(user.getId());
    Alert alert = createAlert(alertDto, caller);
    alertRepo.save(alert);

    // Getting the resource type
    String resourceType = alertDto.getAlertType();
    Resource resource = resourceRepo.getResourceByRType(resourceType);

    // Get the department and response team
    AssignedDepartments assignedDepartments = assign();
    if (assignedDepartments == null) {
      return ResponseEntity.badRequest().body("No available response team");
    }
    Department department = assignedDepartments.department();
    ResponseTeam responseTeam = assignedDepartments.responseTeam();

    // Dispatch the response team
    responseTeam.setDispatched(true);

    // creation of the incident report
    IncidentReport incidentReport = new IncidentReport();
    incidentReport.setIrDate(LocalDate.now());
    incidentReport.setIrDescription("Incident report for alert: " + alert.getId());
    incidentReport.setD(department);
    incidentReport.setA(alert);
    incidentReport.setR(resource);
    ResponseEntity<String> successfulCreation = incidentReportService.create(incidentReport);

    if (successfulCreation.getStatusCode().isError()) {
      return ResponseEntity.badRequest().body("Failed to create incident report");
    }

    // Create the response team incident id
    ResponseTeamIncidentId responseTeamIncidentId = new ResponseTeamIncidentId();
    responseTeamIncidentId.setIrId(incidentReport.getId());
    responseTeamIncidentId.setRtId(responseTeam.getId());

    // Create the response team incident
    ResponseTeamIncident responseTeamIncident = new ResponseTeamIncident();
    responseTeamIncident.setId(responseTeamIncidentId);
    responseTeamIncident.setIr(incidentReport);
    responseTeamIncident.setRt(responseTeam);
    responseTeamIncident.setTimeDispatched(Instant.from(LocalDateTime.now()));
    responseTeamIncident.setDispatchStatus("Dispatched");
    responseTeamIncidentRepo.save(responseTeamIncident);

    return ResponseEntity.ok("Alert created successfully");
  }

  public List<AlertDto> getAllAlerts() {
    return alertRepo.findAll().stream()
        .map(Mapper::toAlertDto)
        .toList();
  }

  public List<AlertDto> previousDaysAlerts(int days) {
    LocalDateTime cutOff = LocalDateTime.now().minusDays(days);
    return alertRepo.findPreviousDaysAlerts(cutOff).stream()
        .map(Mapper::toAlertDto)
        .toList();
  }

  public List<AlertDto> specificDayAlerts(LocalDateTime day) {
    return alertRepo.findSpecificDayAlerts(day).stream()
        .map(Mapper::toAlertDto)
        .toList();
  }

  private Alert createAlert(AlertDto alertDto, Caller caller) {
    Alert alert = new Alert();
    alert.setAlertType(alertDto.getAlertType());
    alert.setSeverity(alertDto.getSeverity());
    alert.setLocation(alertDto.getLocation());
    alert.setCreatedAt(LocalDateTime.now());
    alert.setC(caller);
    return alert;
  }

  private AssignedDepartments assign() {
    List<ResponseTeam> allResponseTeams = responseTeamRepo.findAll();
    int numberOfResponseTeams = allResponseTeams.size();
    int count = 0;

    ResponseTeam responseTeam = null;
    Department department = null;
    while (count < numberOfResponseTeams) {
      // Choose a department
      List<Department> departments = departmentRepo.findAll();
      department = departments.get(random.nextInt(departments.size()));

      // Choose a response team
      List<ResponseTeam> responseTeams = responseTeamRepo.findByDepId(department.getId());
      for (ResponseTeam rt : responseTeams) {
        if (rt.isDispatched()) continue;
        responseTeam = rt;
      }
      count++;
    }

    if (responseTeam == null) {
      // If no response team is available, return null or throw an exception
      return null;
    }
    return new AssignedDepartments(department, responseTeam);
  }
}
