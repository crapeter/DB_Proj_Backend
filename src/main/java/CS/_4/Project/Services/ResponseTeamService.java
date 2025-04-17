package CS._4.Project.Services;

import CS._4.Project.DTOs.ResponseTeamDto;
import CS._4.Project.Repositories.ResponseTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseTeamService {
  private final ResponseTeamRepository responseTeamRepo;

  public ResponseTeamService(ResponseTeamRepository responseTeamRepo) {
    this.responseTeamRepo = responseTeamRepo;
  }

  public List<ResponseTeamDto> getResponseTeamByDepId(Long depId) {
    return responseTeamRepo.findByDepId(depId).stream().map(Mapper::toResponseTeamDto).toList();
  }
}
