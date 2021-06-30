package ro.tuc.tp.business;

import ro.tuc.tp.data.FileWriter;
import ro.tuc.tp.data.Serializator;
import ro.tuc.tp.presentation.LoginGUI;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Class that implements the operations for the admin and client
 * @author Mihaela Petricele
 * @invariant isWellFormed()
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private final Map<Order, List<MenuItem>> collectionMap;
    private List<MenuItem> itemCollection;

    public DeliveryService() {
        collectionMap = new HashMap<>();
        itemCollection = new ArrayList<>();
    }

    @Override
    public void importItem() {
        try {
            assert isWellFormed();
            Path path = Paths.get("products.csv");
            List<MenuItem> productList;
            if (Files.exists(path)) {
                Stream<String> readAllLines = Files.lines(path);

                productList = readAllLines
                        .skip(1)
                        .map(line -> line.split(","))
                        .map(line -> new BaseProduct(line[0], Double.parseDouble(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6])))
                        .distinct()
                        .sorted(Comparator.comparing(BaseProduct::getTitle))
                        .collect(toList());
                itemCollection.addAll(productList);
            }
        } catch (IOException e) {
            System.out.println("Eroare la import!");
        }
        assert !itemCollection.isEmpty();
        assert isWellFormed();
    }

    @Override
    public void addProduct(MenuItem item) {
        assert item != null;
        assert isWellFormed();
        itemCollection.add(item);
        List<MenuItem> newItemCollection = itemCollection
                .stream()
                .distinct()
                .sorted(Comparator.comparing(MenuItem::getTitle))
                .collect(toList());
        itemCollection = newItemCollection;
        assert itemCollection.size() + 1 == getSize();
        assert isWellFormed();
    }

    @Override
    public void deleteProduct(MenuItem item) {
        assert item != null;
        assert isWellFormed();
        itemCollection.removeIf(x -> item.getTitle().equals(x.getTitle()));
        assert itemCollection.size() == getSize() - 1;
        assert isWellFormed();
    }

    @Override
    public void editProduct(MenuItem oldItem, MenuItem newItem) {
        assert oldItem != null;
        assert newItem != null;
        assert isWellFormed();
        itemCollection.remove(oldItem);
        itemCollection.add(newItem);
        assert getSize() == itemCollection.size();
        assert isWellFormed();
    }

    @Override
    public void createOrder(String idClient, double totalPrice, List<MenuItem> menuItemList) {
        assert isWellFormed();
        LocalDateTime timestamp = LocalDateTime.now();
        Order order = new Order(collectionMap.size() + 1, idClient, timestamp, totalPrice);
        collectionMap.put(order, menuItemList);
        setChanged();
        notifyObservers(order);
        FileWriter.writeOrder(order);
        assert !collectionMap.isEmpty();
        assert isWellFormed();
    }

    /**
     * Class invariant
     *
     * @return true if the class is Well Formed or false otherwise
     */
    public boolean isWellFormed() {
        List<MenuItem> list = new ArrayList<>();
        for (MenuItem item : itemCollection) {
            if (!list.contains(item)) {
                list.add(item);
            }
        }
        if (getSize() == list.size())
            return true;
        else
            return false;
    }

    @Override
    public List<MenuItem> searchByTitle(String criteria) {
        assert criteria != null;
        assert isWellFormed();
        List<MenuItem> itemList;
        itemList = itemCollection
                .stream()
                .filter(c -> c.getTitle().contains(criteria))
                .collect(toList());
        assert isWellFormed();
        return itemList;
    }

    @Override
    public List<MenuItem> searchByRating(double criteria) {
        assert criteria != 0;
        assert isWellFormed();
        List<MenuItem> itemList;
        itemList = itemCollection
                .stream()
                .filter(c -> c.getRating() == criteria)
                .collect(toList());
        assert isWellFormed();
        return itemList;
    }

    @Override
    public List<MenuItem> searchByCalories(int criteria) {
        assert criteria != 0;
        assert isWellFormed();
        List<MenuItem> itemList;
        itemList = itemCollection
                .stream()
                .filter(c -> c.getCalories() == criteria)
                .collect(toList());
        assert isWellFormed();
        return itemList;
    }

    @Override
    public List<MenuItem> searchByProtein(int criteria) {
        assert criteria != 0;
        assert isWellFormed();
        List<MenuItem> itemList;
        itemList = itemCollection
                .stream()
                .filter(c -> c.getProtein() == criteria)
                .collect(toList());
        assert isWellFormed();
        return itemList;
    }

    @Override
    public List<MenuItem> searchByFat(int criteria) {
        assert criteria != 0;
        assert isWellFormed();
        List<MenuItem> itemList;
        itemList = itemCollection
                .stream()
                .filter(c -> c.getFat() == criteria)
                .collect(toList());
        assert isWellFormed();
        return itemList;
    }

    @Override
    public List<MenuItem> searchBySodium(int criteria) {
        assert criteria != 0;
        assert isWellFormed();
        List<MenuItem> itemList;
        itemList = itemCollection
                .stream()
                .filter(c -> c.getSodium() == criteria)
                .collect(toList());
        assert isWellFormed();
        return itemList;
    }

    @Override
    public List<MenuItem> searchByPrice(double criteria) {
        assert criteria != 0;
        assert isWellFormed();
        List<MenuItem> itemList;
        itemList = itemCollection
                .stream()
                .filter(c -> c.getPrice() == criteria)
                .collect(toList());
        assert isWellFormed();
        return itemList;
    }

    public List<Order> getRaportA(int startHour, int endHour) {
        assert startHour != 0;
        assert startHour != endHour;
        assert endHour != 24;
        assert isWellFormed();
        List<Order> afterCollection;
        afterCollection = collectionMap.keySet()
                .stream()
                .filter(map -> map.getTimestampOrder().getHour() >= startHour && map.getTimestampOrder().getHour() <= endHour)
                .collect(toList());
        assert afterCollection.size() == collectionMap.size();
        assert isWellFormed();
        return afterCollection;
    }

    public String getRaportB(int number) {
        assert number != 0;
        assert isWellFormed();
        StringBuilder rezultat = new StringBuilder();
        for (MenuItem menuItem : itemCollection) {
            long count = collectionMap.entrySet()
                    .stream()
                    .filter(c -> c.getValue().contains(menuItem))
                    .count();
            if (count > number) {
                rezultat.append(menuItem.toString()).append("\n");
            }
        }
        assert isWellFormed();
        return rezultat.toString();
    }

    public String getRaportC(int number, double value) {
        assert number != 0;
        assert value != 0;
        assert isWellFormed();
        List<String> list = new ArrayList<>();
        List<String> listClient = new ArrayList<>();
        StringBuilder rezultat = new StringBuilder();
        for (String key : UserData.getClient().keySet()) {
            long count = collectionMap.entrySet()
                    .stream()
                    .filter(c -> c.getKey().getPrice() > value)
                    .filter(c -> c.getKey().getClientID().equals(key))
                    .count();
            if (count > number) {
                list.add(key);
            }
        }
        listClient = list.stream().distinct().collect(Collectors.toList());
        for (String client : listClient) {
            rezultat.append(client);
        }
        assert isWellFormed();
        return rezultat.toString();
    }

    public String getRaportD(DayOfWeek day) {
        assert day != null;
        assert isWellFormed();
        long count;
        List<MenuItem> prod;
        StringBuilder rezultat = new StringBuilder();
        for (MenuItem menuItem : itemCollection) {
            count = collectionMap.entrySet()
                    .stream()
                    .filter(c -> c.getKey().getTimestampOrder().getDayOfWeek().equals(day))
                    .filter(c -> c.getValue().contains(menuItem))
                    .count();
            if (count > 0)
                rezultat.append(menuItem.toString()).append(" --- ").append(count).append("times\n");
        }
        assert isWellFormed();
        return rezultat.toString();
    }


    public int getSize() {
        return itemCollection.size();
    }

    public List<MenuItem> getItemCollection() {
        return itemCollection;
    }

    public Map<Order, List<MenuItem>> getCollectionMap() {
        return collectionMap;
    }

    public void setItemCollection(List<MenuItem> itemCollection) {
        this.itemCollection = itemCollection;
    }
}
