package ro.tuc.tp.business;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Class that defines a command and contains the attributes of a command
 * @author Petricele Mihaela
 */
public class Order {
        private int OrderID;
        private final String ClientID;
        private LocalDateTime timestampOrder;
        private final double price;

    /**
     * The constructor of the order
     * @param orderID order number
     * @param clientID the customer who placed the order
     * @param timestampOrder the data and time the order was placed
     * @param price order price
     */
    public Order(int orderID, String clientID, LocalDateTime timestampOrder, double price) {
        OrderID = orderID;
        ClientID = clientID;
        this.timestampOrder = timestampOrder;
        this.price = price;
    }

    /**
     * The method for hashFunction
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderID() == order.getOrderID() && price == order.price && getClientID().equals(order.getClientID()) && getTimestampOrder().equals(order.getTimestampOrder());
    }

    /**
     * The method for hashFunction
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getOrderID());
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public LocalDateTime getTimestampOrder() {
        return timestampOrder;
    }

    public void setTimestampOrder(LocalDateTime timestampOrder) {
        this.timestampOrder = timestampOrder;
    }

    public String getClientID() {
        return ClientID;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order: " +
                "OrderID= " + OrderID + "\n" +
                "ClientID= '" + ClientID + "'" + "\n" +
                "timestampOrder= " + timestampOrder + "\n" +
                "price= " + price;
    }
}
