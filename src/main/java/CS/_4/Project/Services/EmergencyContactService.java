package CS._4.Project.Services;

import CS._4.Project.DTOs.EmergencyContactDto;
import CS._4.Project.Models.EmergencyContact;
import CS._4.Project.Models.User;
import CS._4.Project.Repositories.EmergencyContactRepository;
import CS._4.Project.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyContactService {
  private final EmergencyContactRepository emergencyContactRepo;
  private final UserRepository userRepo;
  private final Mapper mapper;

  public EmergencyContactService(
      EmergencyContactRepository emergencyContactRepo,
      UserRepository userRepo,
      Mapper mapper
  ) {
    this.emergencyContactRepo = emergencyContactRepo;
    this.userRepo = userRepo;
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

  public void deleteEmergencyContact(String email, String fName) {
    User user = userRepo.findByEmail(email);
    if (user == null) {
      throw new RuntimeException("User with email " + email + " not found");
    }

    List<EmergencyContact> emergencyContacts = emergencyContactRepo.findByDependentId(user.getId());
    for (EmergencyContact ec : emergencyContacts) {
      if (ec.getFName().equals(fName)) {
        emergencyContactRepo.delete(ec);
        return;
      }
    }
  }
}
