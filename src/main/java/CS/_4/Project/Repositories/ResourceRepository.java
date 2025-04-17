package CS._4.Project.Repositories;

import CS._4.Project.Models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
  @Query("select r from Resource r where r.rType = ?1")
  Resource getResourceByRType(String rType);
}