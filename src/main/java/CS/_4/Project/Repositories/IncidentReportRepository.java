package CS._4.Project.Repositories;

import CS._4.Project.Models.IncidentReport;
import CS._4.Project.Models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidentReportRepository extends JpaRepository<IncidentReport, Long> {
  @Query("select ir.r from IncidentReport ir where ir.d.id = ?1 and ir.a.id = ?2")
  List<Long> findByDIdandAId(Long dId, Long aId);
}