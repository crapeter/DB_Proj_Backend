package CS._4.Project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Setter
@Entity
@Table(name = "alerts")
public class Alert {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "A_Id", nullable = false)
  private Long id;

  @Column(name = "Alert_Type", length = 100)
  private String alertType;

  @Column(name = "Severity", length = 100)
  private String severity;

  @Lob
  @Column(name = "Location")
  private String location;

  // U_Id shows a warning, but it is not a problem
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumns({
    @JoinColumn(name = "C_Id", referencedColumnName = "C_Id", nullable = false),
    @JoinColumn(name = "U_Id", referencedColumnName = "U_Id", nullable = false)
  })
  private Caller c;

}