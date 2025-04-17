package CS._4.Project.Repositories;

import CS._4.Project.Models.Caller;
import CS._4.Project.Models.CallerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CallerRepository extends JpaRepository<Caller, CallerId> {
  @Query("select coalesce(max(c.id.cId), 0) from Caller c")
  Long findMaxCId();

  @Query("select c.id.uId from Caller c")
  List<Long> findByUId(Long id);

  @Query("select c.id.cId from Caller c where c.id.uId = ?1")
  Caller findById(Long id);
}