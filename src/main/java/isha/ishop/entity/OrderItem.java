package isha.ishop.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_item", schema = "ishop", catalog = "")
public class OrderItem {
    private long id;
    private long idProduct;
    private int count;
    private long idOrder;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_product")
    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    @Basic
    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "id_order")
    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id &&
                idProduct == orderItem.idProduct &&
                count == orderItem.count &&
                idOrder == orderItem.idOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idProduct, count, idOrder);
    }
}
