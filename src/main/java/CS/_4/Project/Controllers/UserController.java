package CS._4.Project.Controllers;

import CS._4.Project.DTOs.*;
import CS._4.Project.Models.*;
import CS._4.Project.Services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<String> createUser(@RequestBody User user) {
    return userService.registerUser(user);
  }

  @GetMapping("/login")
  public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
    return userService.correctPassword(password, email);
  }

  @GetMapping
  public Iterable<UserDto> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/info")
  public Iterable<UserInfoDto> getUserInfo() {
    return userService.getUsersInfo();
  }
}
