package CS._4.Project.DTOs;

import lombok.Data;

@Data
public class CallerDto {
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  public CallerDto(String firstName, String lastName, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
}
