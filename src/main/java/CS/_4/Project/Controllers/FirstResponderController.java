package CS._4.Project.Controllers;

import CS._4.Project.Models.FirstResponder;
import CS._4.Project.Services.FirstResponderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/first-responders")
public class FirstResponderController {
  private final FirstResponderService firstResponderService;

  public FirstResponderController(FirstResponderService firstResponderService) {
    this.firstResponderService = firstResponderService;
  }

  @PostMapping("/create")
  public ResponseEntity<String> create(@RequestBody FirstResponder firstResponder) {
    return firstResponderService.create(firstResponder);
  }
}
