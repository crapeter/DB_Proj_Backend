package CS._4.Project.Repositories;

import CS._4.Project.Models.ResponseTeamIncident;
import CS._4.Project.Models.ResponseTeamIncidentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseTeamIncidentRepository extends JpaRepository<ResponseTeamIncident, ResponseTeamIncidentId> {
}