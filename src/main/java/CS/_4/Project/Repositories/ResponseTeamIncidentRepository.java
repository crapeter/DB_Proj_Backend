package CS._4.Project.Repositories;

import CS._4.Project.Models.ResponseTeamIncident;
import CS._4.Project.Models.ResponseTeamIncidentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseTeamIncidentRepository extends JpaRepository<ResponseTeamIncident, ResponseTeamIncidentId> {
  List<ResponseTeamIncident> findTop7ByOrderByDispatchStatusDesc();
}