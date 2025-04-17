package CS._4.Project.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class AlertDto {
  private String alertType;
  private String severity;
  private String location;
  private String callerEmail;
  private List<String> resourceType;
}
