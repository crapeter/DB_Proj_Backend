package CS._4.Project.Repositories;

import CS._4.Project.Models.FirstResponder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FirstResponderRepository extends JpaRepository<FirstResponder, Long> {
  @Query("SELECT fr FROM FirstResponder fr WHERE fr.u.id = ?1")
  FirstResponder findByUId(Long id);

  @Query("SELECT fr FROM FirstResponder fr WHERE fr.u.email = ?1")
  FirstResponder findByEmail(String email);

  @Query("SELECT fr FROM FirstResponder fr WHERE fr.dep.id = ?1")
  FirstResponder findByDepId(Long id);

  @Query("SELECT fr FROM FirstResponder fr WHERE fr.u.id = ?1 AND fr.dep.id = ?2")
  FirstResponder findByUIdAndDepId(Long uId, Long depId);
}