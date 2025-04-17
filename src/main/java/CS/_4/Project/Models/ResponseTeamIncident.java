package CS._4.Project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "response_team_incidents")
public class ResponseTeamIncident {
  @EmbeddedId
  private ResponseTeamIncidentId id;

  @MapsId("rtId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "RT_Id", nullable = false)
  private ResponseTeam rt;

  @MapsId("irId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "IR_Id", nullable = false)
  private IncidentReport ir;

  @Column(name = "Time_Dispatched")
  private LocalDateTime timeDispatched;

  @Column(name = "Dispatch_Status", length = 50)
  private String dispatchStatus;

}