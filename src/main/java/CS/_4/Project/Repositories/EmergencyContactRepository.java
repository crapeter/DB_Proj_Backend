package CS._4.Project.Repositories;

import CS._4.Project.Models.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
  @Query("select ec from EmergencyContact ec join ec.u u where u.email = ?1")
  List<EmergencyContact> findByDependentEmail(@Param("dependentEmail") String dependentEmail);
}