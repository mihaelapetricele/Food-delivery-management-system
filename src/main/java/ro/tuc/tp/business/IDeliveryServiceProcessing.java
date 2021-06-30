package ro.tuc.tp.business;

import java.security.Timestamp;
import java.time.DayOfWeek;
import java.util.List;

/**
 * Interface that describes the methods that will be implemented for the admin and client
 * @author Petricele Mihaela
 */
public interface IDeliveryServiceProcessing {

    ////////////////////////////////////////////////////////////////////ADMIN OPERATIONS

    /**
     *  The method that imports the products from the csv file
     * @post !itemCollection.isEmpty()
     */
    void importItem();

    /**
     * Adds a new item as a base product in the products list
     * @param item the base product that will be added to the product list
     * @pre item!=null
     * @post getSize() == getSize()@pre + 1
     */
    void addProduct(MenuItem item);

    /**
     * Method that delete a base product from the list with products
     * @param item the product to be deleted
     * @pre !item.isEmpty()
     * @post getSize() == getSize()@pre - 1
     */
    void deleteProduct(MenuItem item);
//    /**
//     * Method that delete a composite product from the list with products
//     * @param item the product to be deleted
//     * @pre !item
//     * @post compositeProduct.getProductList().size()@pre - 1;
//     */
//    void deleteCompositeProduct(MenuItem item, CompositeProduct compositeProduct);

    /**
     * Method that updates a product
     * @param oldItem the product which that will be edited
     * @param newItem the new product edited
     * @pre oldItem != null;
     * @pre newItem != null;
     */
    void editProduct(MenuItem oldItem, MenuItem newItem);

    /**
     * Method that generate the data for the first report
     * @param startHour the start hour for order
     * @param endHour the end hour for order
     * @return list with the order that was made between startHour and endHour
     * @pre startHour != 0
     * @pre startHour != endHour
     * @pre endHour != 24
     * @post afterCollection.size() == collectionMap.size();
     */
    List<Order> getRaportA(int startHour, int endHour);

    /**
     * Method that generate the data for the second report
     * @param number represent the number of times an product was ordered
     * @return the products ordered more than the specified number as parameter
     * @pre number != 0
     */
    String getRaportB(int number);

    /**
     * Method that generate the data for the third report
     * @param number the number of times
     * @param value the amount of the order
     * @return the clients that have ordered more than the specified number as parameter and the value of the order was higher than the specified value
     * @pre number != 0
     * @pre value != 0
     */
    String getRaportC(int number, double value);

    /**
     * Method that generate the data for the fourth report
     * @param day the day when the order was made
     * @return the products ordered within a specified day with the number of times they have been ordered
     * @pre day != null
     */
    String getRaportD(DayOfWeek day);

    ////////////////////////////////////////////////////////////////////CLIENT OPERATIONS

    /**
     * The method that creates an order
     * @param idClient the customer who placed the order
     * @param totalPrice the total price of the order
     * @param menuItemList the list of ordered products
     * @pre idClient != null
     * @pre totalPrice != 0
     * @pre !menuItemList.isEmpty()
     * @post !collectionMap.isEmpty()
     */
    void createOrder(String idClient, double totalPrice, List<MenuItem> menuItemList);

    /**
     * Method that find all de products that have the keyword the same as the parameter of the method
     * @param criteria the title
     * @return the products with the same name as criteria
     * @pre criteria != null
     */
    List<MenuItem> searchByTitle(String criteria);

    /**
     * Method that find all de products that have the rating the same as the parameter of the method
     * @param criteria the rating
     * @return the products with the same rating as criteria
     * @pre criteria != 0
     */
    List<MenuItem> searchByRating(double criteria);

    /**
     * Method that find all de products that have the calories the same as the parameter of the method
     * @param criteria the calories
     * @return the products with the same calories as criteria
     * @pre criteria != 0
     */
    List<MenuItem> searchByCalories(int criteria);

    /**
     * Method that find all de products that have the protein the same as the parameter of the method
     * @param criteria the protein
     * @return the products with the same protein as criteria
     * @pre criteria != 0
     */
    List<MenuItem> searchByProtein(int criteria);

    /**
     * Method that find all de products that have the fat the same as the parameter of the method
     * @param criteria the fat
     * @return the products with the same fat as criteria
     * @pre criteria != 0
     */
    List<MenuItem> searchByFat(int criteria);

    /**
     * Method that find all de products that have the sodium the same as the parameter of the method
     * @param criteria the sodium
     * @return the products with the same sodium as criteria
     * @pre criteria != 0
     */
    List<MenuItem> searchBySodium(int criteria);

    /**
     * Method that find all de products that have the price the same as the parameter of the method
     * @param criteria the price
     * @return the products with the same price as criteria
     * @pre criteria != 0
     */
    List<MenuItem> searchByPrice(double criteria);


}
