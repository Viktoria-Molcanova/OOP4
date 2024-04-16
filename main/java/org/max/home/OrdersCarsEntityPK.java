import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrdersProductsEntityPK implements Serializable {
    private short orderId;
    private short carId;

    @Column(name = "order_id")
    @Id
    public short getOrderId() {
        return orderId;
    }

    public void setOrderId(short orderId) {
        this.orderId = orderId;
    }

    @Column(name = "car_id")
    @Id
    public short getCarId() {
        return carId;
    }

    public void setCarId(short carId) {
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersProductsEntityPK that = (OrdersProductsEntityPK) o;
        return orderId == that.orderId && carId == that.carId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, carId);
    }
}