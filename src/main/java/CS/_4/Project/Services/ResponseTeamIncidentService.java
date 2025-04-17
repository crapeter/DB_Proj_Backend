package CS._4.Project.Services;

import CS._4.Project.DTOs.ResponseTeamIncidentDto;
import CS._4.Project.Models.ResponseTeamIncident;
import CS._4.Project.Repositories.ResponseTeamIncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseTeamIncidentService {
  private final ResponseTeamIncidentRepository responseTeamIncidentRepo;
  private final Mapper mapper;

  public ResponseTeamIncidentService(ResponseTeamIncidentRepository responseTeamIncidentRepo, Mapper mapper) {
    this.responseTeamIncidentRepo = responseTeamIncidentRepo;
    this.mapper = mapper;
  }

  public List<ResponseTeamIncidentDto> getRecentIncidentReports() {
    List<ResponseTeamIncident> responseTeamIncidents = responseTeamIncidentRepo.findTop7ByOrderByDispatchStatusDesc();
    return responseTeamIncidents.stream()
        .map(mapper::toResponseTeamIncidentDto)
        .toList();
  }
}
