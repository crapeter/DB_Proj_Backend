package CS._4.Project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "incident_reports")
public class IncidentReport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "IR_Id", nullable = false)
  private Long id;

  @Column(name = "IR_Date")
  private LocalDate irDate;

  @Lob
  @Column(name = "IR_Description")
  private String irDescription;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "D_Id")
  private Department d;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "A_Id")
  private Alert a;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "R_Id")
  private Resource r;

}