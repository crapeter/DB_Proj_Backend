package CS._4.Project.Services;

import CS._4.Project.DTOs.EmergencyContactDto;
import CS._4.Project.Models.EmergencyContact;
import CS._4.Project.Repositories.EmergencyContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactService {
  private final EmergencyContactRepository emergencyContactRepo;
  private final Mapper mapper;

  public EmergencyContactService(EmergencyContactRepository emergencyContactRepo, Mapper mapper) {
    this.emergencyContactRepo = emergencyContactRepo;
    this.mapper = mapper;
  }

  public ResponseEntity<String> createEC(EmergencyContactDto EC) {
    try {
      emergencyContactRepo.save(mapper.toEmergencyContact(EC));
      return ResponseEntity.ok("Emergency contact created successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error creating emergency contact: " + e.getMessage());
    }
  }

  public Iterable<EmergencyContactDto> getEmergencyContact(String email) {
    List<EmergencyContact> optEC = emergencyContactRepo.findByDependentEmail(email);
    if (optEC == null) {
      return null;
    }
    return optEC.stream()
        .map(mapper::toEmergencyContactDto)
        .toList();
  }
}
