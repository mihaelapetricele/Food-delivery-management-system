package ro.tuc.tp;

import ro.tuc.tp.business.DeliveryService;
import ro.tuc.tp.business.UserData;
import ro.tuc.tp.data.Serializator;
import ro.tuc.tp.presentation.LoginGUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setItemCollection(Serializator.deserializeProduct());
        UserData userData = new UserData();
        UserData.setClient(Serializator.deserializeClient());
        LoginGUI loginGUI = new LoginGUI(deliveryService,userData);
    }
}
