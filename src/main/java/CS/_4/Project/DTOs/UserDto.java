package CS._4.Project.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
  @JsonProperty("fName")
  private String fName;

  @JsonProperty("mInit")
  private String mInit;

  @JsonProperty("lName")
  private String lName;

  private String email;

  private String homeAddr;

  private String phoneNum;

  private Character sex;

  private LocalDate dob;
}
