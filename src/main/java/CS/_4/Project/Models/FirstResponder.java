package CS._4.Project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "first_responders")
public class FirstResponder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FR_Id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "U_Id")
  private CS._4.Project.Models.User u;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "Dep_Id")
  private Department dep;

}