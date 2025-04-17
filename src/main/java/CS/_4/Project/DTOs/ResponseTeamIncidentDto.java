package CS._4.Project.DTOs;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseTeamIncidentDto {
  // Comes from ResponseTeam
  private String rtTeamLeader;
  private String depName;
  private String dispatchTime;

  // Comes from IncidentReport (irDescription)
  private String incidentReport;

  // Comes from IncidentReport -> Alert
  private String alertType;
  private String severity;
  private LocalDateTime reportTime;

  // Comes from a collecting all the incident reports with the same department id and alert id
  // Comes from IncidentReport -> Resource
  private List<String> resources;
}
