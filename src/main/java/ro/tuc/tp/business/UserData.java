package ro.tuc.tp.business;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class that contains the login data for admin, client and employee
 * @author Petricele Mihaela
 */
public class UserData implements Serializable {
    /**
     * HashMap containing the username and password of the admin
     */
    public static HashMap<String, String> admin = new HashMap<>();
    /**
     * HashMap containing the username and password of the client
     */
    public static HashMap<String, String> client = new HashMap<>();
    /**
     * HashMap containing the username and password of the employee
     */
    public static HashMap<String, String> employee = new HashMap<>();

    /**
     * Constructor that initially initializes hashmaps with test data
     */
    public UserData()
    {
        admin.put("admin", "admin");
        employee.put("employee", "employee");
        employee.put("employee2", "employee2");
        employee.put("employee3", "employee3");
    }

    /**
     * The method that adds a new customer
     * @param name customer username
     * @param password customer password
     */
    public static void addClient(String name, String password)
    {
        client.put(name, password);
        System.out.println(client);
    }

    /**
     * The method that adds a new employee
     * @param name employee username
     * @param password employee password
     */
    public static void addEmployee(String name, String password)
    {
        employee.put(name,password);
        System.out.println(employee);
    }

    public static HashMap<String, String> getAdmin() {
        return admin;
    }

    public static void setAdmin(HashMap<String, String> admin) {
        UserData.admin = admin;
    }

    public static HashMap<String, String> getClient() {
        return client;
    }

    public static void setClient(HashMap<String, String> client) {
        UserData.client = client;
    }

    public static HashMap<String, String> getEmployee() {
        return employee;
    }

    public static void setEmployee(HashMap<String, String> employee) {
        UserData.employee = employee;
    }
}
