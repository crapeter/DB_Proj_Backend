package CS._4.Project.Controllers;

import CS._4.Project.DTOs.AlertDto;
import CS._4.Project.Services.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
  private final AlertService alertService;

  public AlertController(AlertService alertService) {
    this.alertService = alertService;
  }

  // Get all alerts
  @GetMapping
  public ResponseEntity<List<AlertDto>> getAllAlerts() {
    List<AlertDto> alerts = alertService.getAllAlerts();
    return ResponseEntity.ok(alerts);
  }

  @PostMapping("/create")
  public ResponseEntity<String> createAlert(@RequestBody AlertDto alertDto) {
    return alertService.createAlert(alertDto);
  }

  @GetMapping("/previous")
  public Iterable<AlertDto> pastTwoDaysAlerts() {
    // Hardcoded to 1 day for data display on the frontend
    return alertService.previousDaysAlerts(1);
  }

  @GetMapping("/specific/day")
  public Iterable<AlertDto> specificDayAlerts(@RequestParam LocalDateTime day) {
    return alertService.specificDayAlerts(day);
  }
}
