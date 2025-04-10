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
@Table(name = "emergency_contact")
public class EmergencyContact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EC_Id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "U_Id")
  private User u;

  @Column(name = "F_Name", length = 50)
  private String fName;

  @Column(name = "M_Init", length = 1)
  private String mInit;

  @Column(name = "L_Name", length = 50)
  private String lName;

  @Column(name = "Sex")
  private Character sex;

  @Column(name = "DOB")
  private LocalDate dob;

  @Column(name = "Relationship", length = 50)
  private String relationship;

}