package CS._4.Project.Services;

import CS._4.Project.DTOs.ResponseTeamIncidentDto;
import CS._4.Project.Repositories.ResponseTeamIncidentRepository;
import org.springframework.stereotype.Service;

@Service
public class ResponseTeamIncidentService {
  private final ResponseTeamIncidentRepository responseTeamIncidentRepo;

  public ResponseTeamIncidentService(ResponseTeamIncidentRepository responseTeamIncidentRepo) {
    this.responseTeamIncidentRepo = responseTeamIncidentRepo;
  }

  public ResponseTeamIncidentDto getIncidentReport() {
    return null;
  }
}
