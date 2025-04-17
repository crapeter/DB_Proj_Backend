package CS._4.Project.Controllers;

import CS._4.Project.DTOs.CallerDto;
import CS._4.Project.Services.CallerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/callers")
public class CallerController {
  private final CallerService callerService;

  public CallerController(CallerService callerService) {
    this.callerService = callerService;
  }

  // No need for a create method here as when you log in, the caller is created automatically
  @GetMapping
  public Iterable<CallerDto> getAllCallers() {
    return callerService.getAllCallers();
  }

  @GetMapping("/email")
  public ResponseEntity<CallerDto> getCallerByEmail(String email) {
    CallerDto caller = callerService.getCallerByEmail(email);
    return caller != null
        ? ResponseEntity.ok(caller)
        : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{email}")
  public ResponseEntity<String> deleteCaller(@PathVariable String email) {
    if (callerService.getCallerByEmail(email) != null) {
      callerService.deleteCaller(email);
      return ResponseEntity.ok("Caller deleted successfully");
    }
    return ResponseEntity.notFound().build();
  }
}
