package CS._4.Project.Controllers;

import CS._4.Project.DTOs.EmergencyContactDto;
import CS._4.Project.Models.EmergencyContact;
import CS._4.Project.Services.EmergencyContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emergency-contacts")
public class EmergencyContactController {
  private final EmergencyContactService emergencyContactService;

  public EmergencyContactController(EmergencyContactService emergencyContactService) {
    this.emergencyContactService = emergencyContactService;
  }

  @PostMapping("/create")
  public ResponseEntity<String> createEmergencyContact(@RequestBody EmergencyContact emergencyContact) {
    return emergencyContactService.createEC(emergencyContact);
  }

  @GetMapping("/get")
  public ResponseEntity<EmergencyContactDto> getEmergencyContact(@RequestParam String email) {
    return emergencyContactService.getEmergencyContact(email);
  }
}
