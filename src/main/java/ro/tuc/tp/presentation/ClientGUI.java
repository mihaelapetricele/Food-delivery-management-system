package ro.tuc.tp.presentation;


import ro.tuc.tp.business.BaseProduct;
import ro.tuc.tp.business.DeliveryService;
import ro.tuc.tp.business.MenuItem;
import ro.tuc.tp.business.UserData;
import ro.tuc.tp.data.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Class that implements the client interface
 * @author Petricele Mihaela
 */
public class ClientGUI extends JFrame implements ActionListener {
    JButton search = new JButton("SEARCH");
    JButton list = new JButton("LIST");
    JButton order = new JButton("ORDER");
    JLabel titluFrame = new JLabel("CLIENT PAGE");
    JLabel title = new JLabel("Title: ");
    JLabel rating = new JLabel("Rating: ");
    JLabel calories = new JLabel("Calories: ");
    JLabel protein = new JLabel("Protein: ");
    JLabel fat = new JLabel("Fat: ");
    JLabel sodium = new JLabel("Sodium: ");
    JLabel price = new JLabel("Price: ");
    JLabel orderLabel = new JLabel("Create an order ");
    JTextField titleField = new JTextField(10);
    JTextField ratingField = new JTextField(8);
    JTextField caloriesField = new JTextField(8);
    JTextField proteinField = new JTextField(8);
    JTextField fatField = new JTextField(8);
    JTextField sodiumField = new JTextField(8);
    JTextField priceField = new JTextField(8);
    JTable tableClient = new JTable(10, 8);
    JPanel panel;
    List<MenuItem> listaProduse = null;
    JPanel client = new JPanel();
    JFrame frame = new JFrame("Client Page");
    JScrollPane scrollPane;

    DeliveryService deliveryService;
    UserData userData;
    User userClient;

    public ClientGUI(DeliveryService deliveryService, UserData userData, User userClient) {
        this.deliveryService = deliveryService;
        this.userData = userData;
        this.userClient = userClient;

        frame.setSize(900, 500);
        listaProduse = deliveryService.getItemCollection().stream().collect(Collectors.toList());

        panel = new JPanel() {
            public Dimension getPreferredSize() {
                return new Dimension(900, 500);
            }

            ;
        };
        panel.setLayout(null);
        titluFrame.setBounds(350, 10, 300, 20);
        titluFrame.setFont(new Font("Serif", Font.BOLD, 24));

        title.setBounds(180, 80, 55, 20);
        title.setFont(new Font("Serif", Font.BOLD, 16));
        titleField.setBounds(225, 80, 100, 20);

        rating.setBounds(340, 80, 100, 20);
        rating.setFont(new Font("Serif", Font.BOLD, 16));
        ratingField.setBounds(400, 80, 50, 20);

        calories.setBounds(465, 80, 100, 20);
        calories.setFont(new Font("Serif", Font.BOLD, 16));
        caloriesField.setBounds(540, 80, 50, 20);

        protein.setBounds(600, 80, 100, 20);
        protein.setFont(new Font("Serif", Font.BOLD, 16));
        proteinField.setBounds(660, 80, 50, 20);

        fat.setBounds(260, 120, 100, 20);
        fat.setFont(new Font("Serif", Font.BOLD, 16));
        fatField.setBounds(300, 120, 50, 20);

        sodium.setBounds(365, 120, 100, 20);
        sodium.setFont(new Font("Serif", Font.BOLD, 16));
        sodiumField.setBounds(430, 120, 50, 20);

        price.setBounds(500, 120, 100, 20);
        price.setFont(new Font("Serif", Font.BOLD, 16));
        priceField.setBounds(550, 120, 50, 20);

        search.setBounds(320, 170, 90, 25);
        search.setBackground(new Color(201, 236, 255));
        search.addActionListener(this);
        list.setBounds(420, 170, 90, 25);
        list.setBackground(new Color(201, 236, 255));
        list.addActionListener(this);

        orderLabel.setBounds(280, 210, 160, 20);
        orderLabel.setFont(new Font("Serif", Font.ITALIC, 17));
        order.setBounds(400, 210, 90, 25);
        order.setBackground(new Color(201, 236, 255));
        order.addActionListener(this);

        scrollPane = new JScrollPane(tableData());
        scrollPane.setBounds(155, 260, 600, 170);


        panel.add(titluFrame);
        panel.add(scrollPane);
        panel.add(title);
        panel.add(titleField);
        panel.add(rating);
        panel.add(ratingField);
        panel.add(calories);
        panel.add(caloriesField);
        panel.add(protein);
        panel.add(proteinField);
        panel.add(fat);
        panel.add(fatField);
        panel.add(sodium);
        panel.add(sodiumField);
        panel.add(price);
        panel.add(priceField);
        panel.add(search);
        panel.add(list);
        panel.add(orderLabel);
        panel.add(order);

        panel.setBackground(new Color(254, 255, 220));
        client.setMaximumSize(new Dimension(900, 610));
        client.add(panel);
        frame.setContentPane(client);
        frame.setVisible(true);
    }

    /**
     * Method that displays the products in a table
     * @return the JTable that will pe displayed
     */
    public JTable tableData() {
        String[] col = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        listaProduse = listaProduse.stream().sorted(Comparator.comparing(MenuItem::getTitle)).distinct().collect(Collectors.toList());
        for (MenuItem menuItem : listaProduse) {
            String title = String.valueOf(menuItem.getTitle());
            double rating = menuItem.getRating();
            int calories = menuItem.getCalories();
            int fat = menuItem.getFat();
            int sodium = menuItem.getSodium();
            int protein = menuItem.getProtein();
            double price = menuItem.getPrice();
            Object[] data = {title, rating, calories, protein, fat, sodium, price};
            tableModel.addRow(data);
        }
        tableClient = new JTable(tableModel);

        return tableClient;
    }

    /**
     * Method that updates the data from the JTable
     */
    public void updateTable()
    {
        panel.remove(scrollPane);
        scrollPane = new JScrollPane(tableData());
        scrollPane.setBounds(155, 260, 600, 170);
        scrollPane.setVisible(true);
        panel.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            deliveryService.importItem();
            listaProduse = deliveryService.getItemCollection().stream().collect(Collectors.toList());
            String title = "";
            double rating = 0, price = 0;
            int calories = 0, protein = 0, fat = 0, sodium = 0;
            if (!titleField.getText().equals("")) {
                title = titleField.getText();
                listaProduse.addAll(deliveryService.searchByTitle(titleField.getText()));
            }
            if (!ratingField.getText().equals("")) {
                rating = Double.parseDouble(ratingField.getText());
                listaProduse.addAll(deliveryService.searchByRating(Double.parseDouble(ratingField.getText())));
            }
            if (!proteinField.getText().equals("")) {
                protein = Integer.parseInt(proteinField.getText());
                listaProduse.addAll(deliveryService.searchByProtein(Integer.parseInt(proteinField.getText())));
            }
            if (!caloriesField.getText().equals("")) {
                calories = Integer.parseInt(caloriesField.getText());
                listaProduse.addAll(deliveryService.searchByCalories(Integer.parseInt(caloriesField.getText())));
            }
            if (!sodiumField.getText().equals("")) {
                sodium = Integer.parseInt(sodiumField.getText());
                listaProduse.addAll(deliveryService.searchBySodium(Integer.parseInt(sodiumField.getText())));
            }
            if (!fatField.getText().equals("")) {
                fat = Integer.parseInt(fatField.getText());
                listaProduse.addAll(deliveryService.searchByFat(Integer.parseInt(fatField.getText())));
            }
            if (!priceField.getText().equals("")) {
                price = Double.parseDouble(priceField.getText());
                listaProduse.addAll(deliveryService.searchByPrice(Double.parseDouble(priceField.getText())));
            }

            String searchTitle = title;
            listaProduse.removeIf(c -> !c
                    .getTitle()
                    .equals(searchTitle) && !searchTitle.equals(""));
            double searchRating = rating;
            listaProduse.removeIf(c -> c
                    .getRating() != searchRating && searchRating != 0);
            int searchCalories = calories;
            listaProduse.removeIf(c -> c
                    .getCalories() != searchCalories && searchCalories != 0);
            int searchProtein = protein;
            listaProduse.removeIf(c -> c
                    .getProtein() != searchProtein && searchProtein != 0);
            int searchFat = fat;
            listaProduse.removeIf(c -> c
                    .getFat() != searchFat && searchFat != 0);
            int searchSodium = sodium;
            listaProduse.removeIf(c -> c
                    .getSodium() != searchSodium && searchSodium != 0);
            double searchPrice = price;
            listaProduse.removeIf(c -> c
                    .getPrice() != searchPrice && searchPrice != 0);
            List<MenuItem> searchList = listaProduse
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());
            listaProduse = searchList;
            for (MenuItem item : searchList) {
                System.out.println(item);
            }
            updateTable();
        }
        if (e.getSource() == order) {
            DefaultTableModel model = (DefaultTableModel) tableClient.getModel();
            double totalPrice = 0;
//            String clientID = null; //LoginGUI.getUsername().getText();
//            for(String key : UserData.getClient().keySet())
//            {
//                clientID = key;
//            }
            List<MenuItem> itemToOrder = new ArrayList<>();
            int[] indexs = tableClient.getSelectedRows();
            Object[] row = new Object[7];
            for (int index : indexs) {
                row[0] = model.getValueAt(index, 0);
                row[1] = model.getValueAt(index, 1);
                row[2] = model.getValueAt(index, 2);
                row[3] = model.getValueAt(index, 3);
                row[4] = model.getValueAt(index, 4);
                row[5] = model.getValueAt(index, 5);
                row[6] = model.getValueAt(index, 6);
                itemToOrder.add(new BaseProduct(row[0].toString(), Double.parseDouble(row[1].toString()), Integer.parseInt(row[2].toString()), Integer.parseInt(row[3].toString()), Integer.parseInt(row[4].toString()), Integer.parseInt(row[5].toString()), Double.parseDouble(row[6].toString())));
            }
            System.out.println(itemToOrder);
            for (MenuItem item : itemToOrder) {
                totalPrice = totalPrice + item.computePrice();
            }
            assert false;
            if (!itemToOrder.isEmpty()) {
                deliveryService.createOrder(userClient.getUsername(), totalPrice, itemToOrder);
            } else {
                JOptionPane.showMessageDialog(frame, "No product was ordered!");
            }
        }
        if(e.getSource() == list)
        {
            listaProduse = deliveryService.getItemCollection().stream().collect(Collectors.toList());
            updateTable();
        }
    }
}
