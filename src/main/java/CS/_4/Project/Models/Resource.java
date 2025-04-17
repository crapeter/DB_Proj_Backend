package CS._4.Project.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resources")
public class Resource {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "R_Id", nullable = false)
  private Long id;

  @Column(name = "R_Type", length = 100, unique = true)
  private String rType;

  @Column(name = "Quantity")
  private Integer quantity;

  @Lob
  @Column(name = "Location")
  private String location;

  @Column(name = "R_Status", length = 50)
  private String rStatus;

}