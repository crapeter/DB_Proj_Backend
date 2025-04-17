package CS._4.Project.Controllers;

import CS._4.Project.Models.IncidentReport;
import CS._4.Project.Services.IncidentReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incident-reports")
public class IncidentReportController {
  private final IncidentReportService incidentReportService;

  public IncidentReportController(IncidentReportService incidentReportService) {
    this.incidentReportService = incidentReportService;
  }

  @PostMapping("/create")
  public ResponseEntity<String> createIncidentReport(@RequestBody IncidentReport incidentReport) {
    return incidentReportService.create(incidentReport);
  }
}
