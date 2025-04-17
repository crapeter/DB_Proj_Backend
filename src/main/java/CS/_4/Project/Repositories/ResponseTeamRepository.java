package CS._4.Project.Repositories;

import CS._4.Project.Models.ResponseTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseTeamRepository extends JpaRepository<ResponseTeam, Long> {
  List<ResponseTeam> findByDepId(Long depId);
}