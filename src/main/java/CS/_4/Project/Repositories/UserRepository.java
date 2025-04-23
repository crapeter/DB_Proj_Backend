package CS._4.Project.Repositories;

import CS._4.Project.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u WHERE u.email = :e")
  User findByEmail(@Param("e") String email);
}