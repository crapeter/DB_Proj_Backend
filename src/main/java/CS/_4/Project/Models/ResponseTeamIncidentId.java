package CS._4.Project.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ResponseTeamIncidentId implements Serializable {
  @Serial
  private static final long serialVersionUID = 4819049180653353498L;

  @Column(name = "RT_Id", nullable = false)
  private Long rtId;

  @Column(name = "IR_Id", nullable = false)
  private Long irId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ResponseTeamIncidentId entity = (ResponseTeamIncidentId) o;
    return Objects.equals(this.irId, entity.irId) &&
        Objects.equals(this.rtId, entity.rtId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(irId, rtId);
  }

}