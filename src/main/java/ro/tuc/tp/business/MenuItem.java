package ro.tuc.tp.business;


import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract class that contains the corresponding fields for each product and the abstract methods for the composite products
 * @author Petricele Mihaela
 */
public abstract class MenuItem implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private double price;

    /**
     * Constructor with no parameters
     */
    public MenuItem()
    {
    };

    /**
     * Constructor with parameters
     * @param title product name
     * @param rating product rating
     * @param calories product calories
     * @param protein product protein
     * @param fat product fat
     * @param sodium product sodium
     * @param price product price
     */
    public MenuItem(String title, double rating, int calories, int protein, int fat, int sodium, double price)
    {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public double getPrice() {
        return price;
    }

    public abstract String computeTitle();
    public abstract double computePrice();
    public abstract double computeRating();
    public abstract int computeCalories();
    public abstract int computeProtein();
    public abstract int computeFat();
    public abstract int computeSodium();

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + " " +rating + " " +calories + " " +protein + " " +fat + " " +sodium + " " +price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem item = (MenuItem) o;
        return Objects.equals(getTitle(), item.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
