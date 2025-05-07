package CS._4.Project.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmergencyContactDto {
  private String dependentEmail;

  @JsonProperty("fName")
  private String fName;

  @JsonProperty("mInit")
  private String mInit;

  @JsonProperty("lName")
  private String lName;

  private Character sex;
  private LocalDate dob;
  private String relationship;
}
