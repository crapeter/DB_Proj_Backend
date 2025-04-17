package CS._4.Project.Services;

import CS._4.Project.Models.FirstResponder;
import CS._4.Project.Repositories.FirstResponderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FirstResponderService {
  private final FirstResponderRepository firstResponderRepo;

  public FirstResponderService(FirstResponderRepository firstResponderRepo) {
    this.firstResponderRepo = firstResponderRepo;
  }

  public ResponseEntity<String> create(FirstResponder firstResponder) {
    if (firstResponderRepo.findByUIdAndDepId(firstResponder.getU().getId(), firstResponder.getDep().getId()) != null) {
      return ResponseEntity.badRequest().body("First responder already exists");
    }
    firstResponderRepo.save(firstResponder);
    return ResponseEntity.ok("First responder created successfully");
  }

  public ResponseEntity<String> removeFR(String email) {
    FirstResponder fr = firstResponderRepo.findByEmail(email);
    if (fr == null) {
      return ResponseEntity.badRequest().body("First responder not found");
    }
    firstResponderRepo.delete(fr);
    return ResponseEntity.ok("First responder removed successfully");
  }
}
