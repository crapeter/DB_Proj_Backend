package CS._4.Project.Services;

import CS._4.Project.DTOs.CallerDto;
import CS._4.Project.Models.User;
import CS._4.Project.Repositories.CallerRepository;
import CS._4.Project.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CallerService {
  private final CallerRepository callerRepo;
  private final UserRepository userRepo;

  public CallerService(CallerRepository callerRepo, UserRepository userRepo) {
    this.callerRepo = callerRepo;
    this.userRepo = userRepo;
  }

  public Iterable<CallerDto> getAllCallers() {
    return userRepo.findAll().stream()
        .map(user -> new CallerDto(user.getFName(), user.getLName(), user.getEmail(), user.getPhoneNum()))
        .toList();
  }

  public CallerDto getCallerByEmail(String email) {
    User user = userRepo.findByEmail(email);
    if (user != null) {
      return new CallerDto(user.getFName(), user.getLName(), user.getEmail(), user.getPhoneNum());
    }
    return null;
  }

  public void deleteCaller(String email) {
    User user = userRepo.findByEmail(email);
    if (user != null) {
      userRepo.delete(user);
    }
  }
}
