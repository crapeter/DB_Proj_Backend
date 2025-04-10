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
public class CallerId implements Serializable {
  @Serial
  private static final long serialVersionUID = 5408519362962170491L;

  @Column(name = "C_Id", nullable = false)
  private Long cId;

  @Column(name = "U_Id", nullable = false)
  private Long uId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    CallerId entity = (CallerId) o;
    return Objects.equals(this.uId, entity.uId) &&
        Objects.equals(this.cId, entity.cId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uId, cId);
  }

}