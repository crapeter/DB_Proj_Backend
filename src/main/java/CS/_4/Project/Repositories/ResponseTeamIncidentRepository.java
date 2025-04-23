package CS._4.Project.Repositories;

import CS._4.Project.Models.ResponseTeamIncident;
import CS._4.Project.Models.ResponseTeamIncidentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ResponseTeamIncidentRepository extends JpaRepository<ResponseTeamIncident, ResponseTeamIncidentId> {
  List<ResponseTeamIncident> findTop7ByOrderByDispatchStatusDesc();

  @Query("select a from ResponseTeamIncident a where a.timeDispatched >= :day and a.timeDispatched < :dayEnd")
  List<ResponseTeamIncident> findByIncidentDate(@Param("day") LocalDateTime day, @Param("dayEnd") LocalDateTime dayEnd);
}