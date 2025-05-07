package CS._4.Project.Controllers;

import CS._4.Project.DTOs.EmergencyContactDto;
import CS._4.Project.Services.EmergencyContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ec")
public class EmergencyContactController {
  private final EmergencyContactService emergencyContactService;

  public EmergencyContactController(EmergencyContactService emergencyContactService) {
    this.emergencyContactService = emergencyContactService;
  }

  @PostMapping("/create")
  public ResponseEntity<String> createEmergencyContact(@RequestBody EmergencyContactDto emergencyContact) {
    return emergencyContactService.createEC(emergencyContact);
  }

  @GetMapping("/get")
  public Iterable<EmergencyContactDto> getEmergencyContact(@RequestParam String email) {
    return emergencyContactService.getEmergencyContact(email);
  }

  @DeleteMapping()
  public ResponseEntity<String> deleteEmergencyContact(@RequestParam String email, @RequestParam String fName) {
    try {
      emergencyContactService.deleteEmergencyContact(email, fName);
      return ResponseEntity.ok("Emergency contact deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error deleting emergency contact: " + e.getMessage());
    }
  }
}
