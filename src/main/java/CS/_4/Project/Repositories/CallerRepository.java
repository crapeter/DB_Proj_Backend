package CS._4.Project.Repositories;

import CS._4.Project.Models.Caller;
import CS._4.Project.Models.CallerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallerRepository extends JpaRepository<Caller, CallerId> {
}