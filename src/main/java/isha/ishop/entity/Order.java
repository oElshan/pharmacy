package isha.ishop.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Order {
    private long id;
    private long idAcaunt;
    private Timestamp created;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_acaunt")
    public long getIdAcaunt() {
        return idAcaunt;
    }

    public void setIdAcaunt(long idAcaunt) {
        this.idAcaunt = idAcaunt;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                idAcaunt == order.idAcaunt &&
                Objects.equals(created, order.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idAcaunt, created);
    }
}
