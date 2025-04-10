package CS._4.Project.Repositories;

import CS._4.Project.Models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}