package CS._4.Project.Repositories;

import CS._4.Project.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}