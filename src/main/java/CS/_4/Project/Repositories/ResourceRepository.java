package CS._4.Project.Repositories;

import CS._4.Project.Models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
  @Query("select r from Resource r where r.rType = ?1")
  Resource getResourceByRType(String rType);

  @Query("SELECT r.rType FROM Resource r WHERE r.id IN :ids")
  List<String> getResourceNamesById(@Param("ids") List<Long> ids);
}