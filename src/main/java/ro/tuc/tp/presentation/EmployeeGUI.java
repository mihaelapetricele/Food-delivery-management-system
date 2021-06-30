package ro.tuc.tp.presentation;

import ro.tuc.tp.business.DeliveryService;
import ro.tuc.tp.business.MenuItem;
import ro.tuc.tp.business.Order;
import ro.tuc.tp.business.UserData;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * Class that implements the employee interface
 * @author Petricele Mihaela
 */
public class EmployeeGUI extends JFrame implements Observer {
    JLabel titluFrame = new JLabel("EMPLOYEE PAGE");
    JLabel titluOrder = new JLabel("Order was placed");
    JTextArea orderArea = new JTextArea(100,120);

    JPanel client = new JPanel();
    JFrame frame = new JFrame("Employee Page");
    JPanel panel = new JPanel(){
        public Dimension getPreferredSize(){
            return new Dimension(700,500);
        };
    };

    DeliveryService deliveryService;
    UserData userData;

    public EmployeeGUI(DeliveryService deliveryService, UserData userData)
    {
        this.deliveryService = deliveryService;
        this.userData = userData;
        deliveryService.addObserver(this);

        frame.setSize(700,500);

        panel.setLayout(null);
        titluFrame.setBounds(240,10,300,20);
        titluFrame.setFont(new Font("Serif",Font.BOLD,24));

        titluOrder.setBounds(270,70,300,30);
        titluOrder.setFont(new Font("Serif",Font.BOLD,20));

        orderArea.setBounds(100,130,500,300);
        orderArea.setEditable(false);
        orderArea.setBackground(new Color(255,255,240));

        panel.add(titluFrame);
        panel.add(titluOrder);
        panel.add(orderArea);

        panel.setBackground(new Color( 254,255,220 ));
        client.setMaximumSize(new Dimension(900,610));
        client.add(panel);
        frame.setContentPane(client);
        frame.setVisible(true);
    }

    /**
     * Method is called when an observer is notified of a change
     * @param o the observable object
     * @param arg contains information about what changes in observable object
     */
    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        DeliveryService delServ = (DeliveryService) o;
        List<MenuItem> empList = delServ.getCollectionMap().get(order);
        StringBuilder orderInfo = new StringBuilder();
        orderInfo.append("OrderID: ").append(order.getOrderID()).append(" with ClientID:").append(order.getClientID()).append("\n");
        orderInfo.append("Date: ").append(order.getTimestampOrder()).append(", Price: ").append(order.getPrice()).append("\n");
        orderInfo.append("The Products are: \n");
        for (MenuItem orderItem: empList)
        {
            orderInfo.append(orderItem.getTitle()).append(" ").append(orderItem.computePrice()).append("\n");
        }
        orderInfo.append("\n");
        orderArea.append(orderInfo.toString());
    }
}
