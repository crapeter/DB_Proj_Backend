package CS._4.Project.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfoDto {
  @JsonProperty("fName")
  private String fName;

  @JsonProperty("mInit")
  private String mInit;

  @JsonProperty("lName")
  private String lName;

  private String email;
}
