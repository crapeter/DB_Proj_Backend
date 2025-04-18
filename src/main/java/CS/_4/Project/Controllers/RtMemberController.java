package CS._4.Project.Controllers;

import CS._4.Project.Services.RtMemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rtm")
public class RtMemberController {
  private final RtMemberService rtMemberService;

  public RtMemberController(RtMemberService rtMemberService) {
    this.rtMemberService = rtMemberService;
  }
}
