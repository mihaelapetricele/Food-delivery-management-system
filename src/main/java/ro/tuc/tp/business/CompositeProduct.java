package ro.tuc.tp.business;

import java.io.Serializable;

import java.util.List;

/**
 * Class that contains the data about the composite product
 * @author Petricele Mihaela
 */
public class CompositeProduct extends MenuItem implements Serializable {
    /**
     * The list of products that compose the composite product
     */
    private final List<MenuItem> productList;


    /**
     * The constructor of the composite product
     * @param title the title of the composite product
     * @param productList the list with base products
     */
    public CompositeProduct(String title,List<MenuItem> productList) {
        super.setTitle(title);
        this.productList = productList;
    }

    @Override
    public String computeTitle() {
        return super.getTitle();
    }

    /**
     * The method that composes the price of the composite product from the price of basic products
     * @return the price of the composite product
     */
    @Override
    public double computePrice() {
        double totalPrice = 0;
        for (MenuItem menuItem : productList) {
            totalPrice += menuItem.getPrice();
        }
        return totalPrice;
    }



    /**
     * The method that composes the rating of the composite product the rating of basic products
     * @return the rating of the composite product
     */
    @Override
    public double computeRating() {
        int dim = productList.size();
        double sumRating = 0;
        for (MenuItem menuItem : productList) {
            sumRating += menuItem.getRating();
        }
        return sumRating / dim;
    }

    /**
     * The method that composes the calories of the composite product from the calories of basic products
     * @return the calories of the composite product
     */
    @Override
    public int computeCalories() {
        int sumCalories = 0;
        for (MenuItem menuItem : productList) {
            sumCalories += menuItem.getCalories();
        }
        return sumCalories;
    }

    /**
     * The method that composes the protein of the composite product from the protein of basic products
     * @return the protein of the composite product
     */
    @Override
    public int computeProtein() {
        int sumProtein = 0;
        for (MenuItem menuItem : productList) {
            sumProtein += menuItem.getProtein();
        }
        return sumProtein;
    }

    /**
     * The method that composes the fat of the composite product from the fat of basic products
     * @return the fat of the composite product
     */
    @Override
    public int computeFat() {
        int sumFat = 0;
        for (MenuItem menuItem : productList) {
            sumFat += menuItem.getFat();
        }
        return sumFat;
    }

    /**
     * The method that composes the sodium of the composite product from the sodium of basic products
     * @return the sodium of the composite product
     */
    @Override
    public int computeSodium() {
        int sumSodium = 0;
        for (MenuItem menuItem : productList) {
            sumSodium += menuItem.getSodium();
        }
        return sumSodium;
    }

    /**
     * The method that add a base product to the list
     * @param item a base product
     */
    public void addProducts(MenuItem item) {
        productList.add(item);
    }

    public void removeProducts(MenuItem item) {
        productList.remove(item);
    }

    public List<MenuItem> getProductList() {
        return productList;
    }

    public int getSizeComposite() {
        return productList.size();
    }

    @Override
    public String toString() {
        return "CompositeProduct: " + computeTitle() + " " + computeRating() + " " + computeCalories() + " " + computeProtein() + " " + computeFat() + " " + computeSodium() + " " + computePrice();
    }
}
