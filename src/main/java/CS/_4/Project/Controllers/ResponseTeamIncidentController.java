package CS._4.Project.Controllers;

import CS._4.Project.Services.ResponseTeamIncidentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/response-team-incidents")
public class ResponseTeamIncidentController {
  private final ResponseTeamIncidentService responseTeamIncidentService;

  public ResponseTeamIncidentController(ResponseTeamIncidentService responseTeamIncidentService) {
    this.responseTeamIncidentService = responseTeamIncidentService;
  }
}
