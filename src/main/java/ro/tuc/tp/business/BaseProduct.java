package ro.tuc.tp.business;


import java.io.Serializable;

/**
 * Class that contains the data about the base product
 * @author Petricele Mihaela
 */
public  class BaseProduct extends MenuItem implements Serializable {
    /**
     * A new basic product is created by calling the super constructor
     * @param title product title
     * @param rating product rating
     * @param calories product calories
     * @param protein product protein
     * @param fat product fat
     * @param sodium product sodium
     * @param price product price
     */

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, double price)
    {
        super(title,rating,calories,protein,fat,sodium,price);
    }

    /**
     * The abstract method of the MenuItem class
     * @return the title of the base product
     */
    @Override
    public String computeTitle() { return getTitle(); }

    /**
     * The abstract method of the MenuItem class
     * @return the price of the base product
     */
    @Override
    public double computePrice() { return getPrice(); }

    /**
     * The abstract method of the MenuItem class
     * @return the rating of the base product
     */
    @Override
    public double computeRating() { return getRating(); }

    /**
     * The abstract method of the MenuItem class
     * @return the calories of the base product
     */
    @Override
    public int computeCalories() { return getCalories(); }

    /**
     * The abstract method of the MenuItem class
     * @return the protein of the base product
     */
    @Override
    public int computeProtein() { return getProtein(); }

    /**
     * The abstract method of the MenuItem class
     * @return the fat of the base product
     */
    @Override
    public int computeFat() { return getFat(); }

    /**
     * The abstract method of the MenuItem class
     * @return the sodium of the base product
     */
    @Override
    public int computeSodium() { return getSodium(); }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
