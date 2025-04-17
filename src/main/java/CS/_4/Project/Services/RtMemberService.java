package CS._4.Project.Services;

import CS._4.Project.Repositories.RtMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class RtMemberService {
  private final RtMemberRepository rtMemberRepo;

  public RtMemberService(RtMemberRepository rtMemberRepo) {
    this.rtMemberRepo = rtMemberRepo;
  }
}
