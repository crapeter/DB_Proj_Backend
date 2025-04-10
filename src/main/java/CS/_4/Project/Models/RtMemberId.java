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
public class RtMemberId implements Serializable {
  @Serial
  private static final long serialVersionUID = 8278242865465474299L;

  @Column(name = "RT_Id", nullable = false)
  private Long rtId;

  @Column(name = "FR_Id", nullable = false)
  private Long frId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    RtMemberId entity = (RtMemberId) o;
    return Objects.equals(this.frId, entity.frId) &&
        Objects.equals(this.rtId, entity.rtId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frId, rtId);
  }

}