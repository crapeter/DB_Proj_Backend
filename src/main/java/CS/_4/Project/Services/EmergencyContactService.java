package CS._4.Project.Services;

import CS._4.Project.DTOs.EmergencyContactDto;
import CS._4.Project.Models.EmergencyContact;
import CS._4.Project.Repositories.EmergencyContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmergencyContactService {
  private final EmergencyContactRepository emergencyContactRepo;

  public EmergencyContactService(EmergencyContactRepository emergencyContactRepo) {
    this.emergencyContactRepo = emergencyContactRepo;
  }

  public ResponseEntity<String> createEC(EmergencyContact EC) {
    try {
      emergencyContactRepo.save(EC);
      return ResponseEntity.ok("Emergency contact created successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error creating emergency contact: " + e.getMessage());
    }
  }

  public ResponseEntity<EmergencyContactDto> getEmergencyContact(String email) {
    EmergencyContact optEC = emergencyContactRepo.findByDependentEmail(email);
    if (optEC == null) {
      return ResponseEntity.notFound().build();
    }
    EmergencyContactDto ecDto = new EmergencyContactDto();
    ecDto = Mapper.toEmergencyContactDto(optEC);
    return ResponseEntity.ok(ecDto);
  }
}
