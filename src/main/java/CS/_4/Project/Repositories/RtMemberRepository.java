package CS._4.Project.Repositories;

import CS._4.Project.Models.RtMember;
import CS._4.Project.Models.RtMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RtMemberRepository extends JpaRepository<RtMember, RtMemberId> {
}