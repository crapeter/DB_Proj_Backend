package CS._4.Project.Services;

import CS._4.Project.Models.IncidentReport;
import CS._4.Project.Repositories.IncidentReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IncidentReportService {
  private final IncidentReportRepository incidentReportRepo;

  public IncidentReportService(IncidentReportRepository incidentReportRepo) {
    this.incidentReportRepo = incidentReportRepo;
  }

  public ResponseEntity<String> create(IncidentReport incidentReport) {
    if (incidentReport == null) {
      return ResponseEntity.badRequest().body("Incident report cannot be null");
    } else if (incidentReport.getIrDescription() == null || incidentReport.getIrDescription().isEmpty()) {
      return ResponseEntity.badRequest().body("Incident report description cannot be empty");
    } else if (incidentReport.getIrDate() == null) {
      return ResponseEntity.badRequest().body("Incident report date cannot be null");
    }

    incidentReportRepo.save(incidentReport);
    return ResponseEntity.ok("Incident report created successfully");
  }
}
