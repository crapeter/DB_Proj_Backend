package CS._4.Project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "D_Id", nullable = false)
  private Long id;

  @Column(name = "Name", length = 100)
  private String name;

  @Column(name = "Dep_Type", length = 50)
  private String depType;

  @Lob
  @Column(name = "Contact_Info")
  private String contactInfo;

  @Lob
  @Column(name = "Location")
  private String location;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "Mgr_Id")
  private FirstResponder mgr;

}