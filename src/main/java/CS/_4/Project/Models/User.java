package CS._4.Project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "U_Id", nullable = false)
  private Long id;

  @Column(name = "F_Name", length = 50)
  private String fName;

  @Column(name = "M_Init", length = 1)
  private String mInit;

  @Column(name = "L_Name", length = 50)
  private String lName;

  @Column(name = "Email", length = 100)
  private String email;

  @Column(name = "Password")
  private String password;

  @Column(name = "Salt")
  private String salt;

  @Column(name = "Phone_Num", length = 15)
  private String phoneNum;

  @Lob
  @Column(name = "Home_Addr")
  private String homeAddr;

  @Column(name = "Sex")
  private Character sex;

  @Column(name = "DOB")
  private LocalDate dob;

}