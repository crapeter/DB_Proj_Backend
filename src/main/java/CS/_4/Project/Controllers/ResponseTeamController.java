package CS._4.Project.Controllers;

import CS._4.Project.DTOs.ResponseTeamDto;
import CS._4.Project.Services.ResponseTeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/response-teams")
public class ResponseTeamController {
  private final ResponseTeamService responseTeamService;

  public ResponseTeamController(ResponseTeamService responseTeamService) {
    this.responseTeamService = responseTeamService;
  }

  @GetMapping("/{depId}")
  public Iterable<ResponseTeamDto> getResponseTeam(@PathVariable long depId) {
    return responseTeamService.getResponseTeamByDepId(depId);
  }
}
