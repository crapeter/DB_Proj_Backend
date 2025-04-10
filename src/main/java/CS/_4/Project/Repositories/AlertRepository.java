package CS._4.Project.Repositories;

import CS._4.Project.Models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}