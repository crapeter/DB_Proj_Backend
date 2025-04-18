package CS._4.Project.Repositories;

import CS._4.Project.Models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
  @Query("select a from Alert a where a.createdAt >= :currentDateTime")
  List<Alert> findPreviousDaysAlerts(LocalDateTime currentDateTime);

  @Query("select a from Alert a where a.createdAt >= :day and a.createdAt < :dayEnd")
  List<Alert> findSpecificDayAlerts(@Param("day") LocalDateTime day, @Param("dayEnd") LocalDateTime dayEnd);
}