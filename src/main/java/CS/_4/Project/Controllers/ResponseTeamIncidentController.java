package CS._4.Project.Controllers;

import CS._4.Project.DTOs.ResponseTeamIncidentDto;
import CS._4.Project.Services.ResponseTeamIncidentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rti")
public class ResponseTeamIncidentController {
  private final ResponseTeamIncidentService responseTeamIncidentService;

  public ResponseTeamIncidentController(ResponseTeamIncidentService responseTeamIncidentService) {
    this.responseTeamIncidentService = responseTeamIncidentService;
  }

  @GetMapping
  public Iterable<ResponseTeamIncidentDto> getResponseTeamIncidents() {
    return responseTeamIncidentService.getRecentIncidentReports();
  }
}
