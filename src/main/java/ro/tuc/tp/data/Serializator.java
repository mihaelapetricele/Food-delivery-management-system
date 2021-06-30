package ro.tuc.tp.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.tuc.tp.business.BaseProduct;
import ro.tuc.tp.business.MenuItem;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that performs serialization and deserialization
 * @author Petricele Mihaela
 */
public class Serializator {

    /**
     * Serialized file that retains data about clients
     */
    private static final String clientFile = "client.txt";
    /**
     * Serialized file that retains data about products
     */
    private static final String productFile = "product.txt";

    /**
     * The method that serializes the product list
     * @param menuItemList list of products to be serialized
     */
    public static void serializeProduct(List<MenuItem> menuItemList)
    {
        try {
            JSONArray jsonArr = new JSONArray();
            File serFile = new File(productFile);
            FileWriter serWrite = new FileWriter(serFile);
            for(MenuItem item : menuItemList)
            {
                JSONObject object = new JSONObject();
                object.put("title",item.getTitle());
                object.put("rating",item.getRating());
                object.put("calories",item.getCalories());
                object.put("protein",item.getProtein());
                object.put("fat",item.getProtein());
                object.put("sodium",item.getSodium());
                object.put("price",item.getPrice());
                jsonArr.add(object);
            }
            serWrite.write(jsonArr.toJSONString());
            serWrite.flush();
            serWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method that serializes the data for clients
     * @param hashMap contains data about customers to be serialized
     */
    public static void serializeClient(HashMap<String,String> hashMap)
    {
        try {
            JSONArray jsonArr = new JSONArray();
            File serFile = new File(clientFile);
            FileWriter serWrite = new FileWriter(serFile);
            for(String key : hashMap.keySet())
            {
                for(String value : hashMap.values())
                {
                    JSONObject object = new JSONObject();
                    object.put("username",key);
                    object.put("password",value);
                    jsonArr.add(object);
                }
            }
            serWrite.write(jsonArr.toJSONString());
            serWrite.flush();
            serWrite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method that performs deserialization of products
     * @return list of deserialized objects of type menuItem
     */
    public static List<MenuItem> deserializeProduct()
    {
        try {
            List<MenuItem> items = new ArrayList<>();
            Object obj = new JSONParser().parse(new FileReader(productFile));
            JSONArray jsonArr = (JSONArray) obj;
            for (Object o : jsonArr) {
                JSONObject jObj = (JSONObject) o;
                BaseProduct item = new BaseProduct((String) jObj.get("title"),
                        (Double) jObj.get("rating"),
                        Integer.parseInt(String.valueOf(jObj.get("calories"))),
                        Integer.parseInt(String.valueOf(jObj.get("protein"))),
                        Integer.parseInt(String.valueOf( jObj.get("fat"))),
                        Integer.parseInt(String.valueOf( jObj.get("sodium"))),
                        (Double) jObj.get("price"));
                items.add(item);
            }
            return items;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * The method that performs deserialization of clients data
     * @return hashMap with deserialized clients data
     */
    public static HashMap<String,String> deserializeClient()
    {
        try {
            HashMap<String,String> clientMap = new HashMap<>();
            Object obj = new JSONParser().parse(new FileReader(clientFile));
            JSONArray jsonArr = (JSONArray) obj;
            for (Object o : jsonArr) {
                JSONObject jObj = (JSONObject) o;
                clientMap.put((String) jObj.get("username"),(String) jObj.get("password"));
            }
            return clientMap;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
