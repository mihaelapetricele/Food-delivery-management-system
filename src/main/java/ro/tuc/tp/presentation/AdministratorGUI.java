package ro.tuc.tp.presentation;

import ro.tuc.tp.business.BaseProduct;
import ro.tuc.tp.business.CompositeProduct;
import ro.tuc.tp.business.DeliveryService;
import ro.tuc.tp.business.MenuItem;
import ro.tuc.tp.data.FileWriter;
import ro.tuc.tp.data.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Class that implements the administrator interface
 * @author Petricele Mihaela
 */
public class AdministratorGUI extends JFrame implements ActionListener {
    JButton insert = new JButton("ADD");
    JButton update = new JButton("MODIFY");
    JButton delete = new JButton("DELETE");
    JButton compute = new JButton("COMPUTE");
    JButton list = new JButton("LIST");
    JButton importItem = new JButton("IMPORT");
    JButton getRaportA = new JButton("RAPORT A");
    JButton getRaportB = new JButton("RAPORT B");
    JButton getRaportC = new JButton("RAPORT C");
    JButton getRaportD = new JButton("RAPORT D");
    JLabel titluFrame = new JLabel("ADMINISTRATOR PAGE");
    JLabel title = new JLabel("Title: ");
    JLabel rating = new JLabel("Rating: ");
    JLabel calories = new JLabel("Calories: ");
    JLabel protein = new JLabel("Protein: ");
    JLabel fat = new JLabel("Fat: ");
    JLabel sodium = new JLabel("Sodium: ");
    JLabel price = new JLabel("Price: ");
    JLabel startHour = new JLabel("Start hour: ");
    JLabel endHour = new JLabel("End hour: ");
    JLabel productOrderTimes = new JLabel("Enter number of times: ");
    JLabel clientOrderTimes = new JLabel("Enter number of times: ");
    JLabel valueOrder = new JLabel("Enter the value: ");
    JLabel specificDay = new JLabel("Enter a day: ");
    //JLabel orderTimes = new JLabel("Enter number of times: ");
    JTextField titleField = new JTextField(10);
    JTextField ratingField = new JTextField(8);
    JTextField caloriesField = new JTextField(8);
    JTextField proteinField = new JTextField(8);
    JTextField fatField = new JTextField(8);
    JTextField sodiumField = new JTextField(8);
    JTextField priceField = new JTextField(8);
    JTextField getStartHour = new JTextField(8);
    JTextField getEndHour = new JTextField(8);
    JTextField getProductOrderTimes = new JTextField(8);
    JTextField getClientOrderTimes = new JTextField(8);
    //JTextField getOrderTimes = new JTextField(8);
    JTextField getValueOrder = new JTextField(8);
    JTextField getSpecificDay = new JTextField(8);
    JTable tableAdmin = new JTable(1450, 7);
    JScrollPane scrollPane;

    DeliveryService deliveryService;
    Serializator serializator = new Serializator();
    FileWriter fileWriter = new FileWriter();

    JPanel admin = new JPanel();
    JFrame frame = new JFrame("Administrator Page");
    JPanel panel = new JPanel() {
        public Dimension getPreferredSize() {
            return new Dimension(900, 700);
        }
    };

    public AdministratorGUI(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;

        frame.setSize(900, 700);

        panel.setLayout(null);
        titluFrame.setBounds(300, 10, 300, 20);
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

        importItem.setBounds(150, 170, 90, 25);
        importItem.setBackground(new Color(201, 236, 255));
        importItem.addActionListener(this);
        insert.setBounds(250, 170, 90, 25);
        insert.setBackground(new Color(201, 236, 255));
        insert.addActionListener(this);
        update.setBounds(350, 170, 90, 25);
        update.setBackground(new Color(201, 236, 255));
        update.addActionListener(this);
        delete.setBounds(450, 170, 90, 25);
        delete.setBackground(new Color(201, 236, 255));
        delete.addActionListener(this);
        compute.setBounds(550, 170, 110, 25);
        compute.setBackground(new Color(201, 236, 255));
        compute.addActionListener(this);
        list.setBounds(670, 170, 90, 25);
        list.setBackground(new Color(201, 236, 255));
        list.addActionListener(this);

        startHour.setBounds(170, 460, 120, 25);
        startHour.setFont(new Font("Serif", Font.BOLD, 16));
        getStartHour.setBounds(260, 460, 90, 25);

        endHour.setBounds(400, 460, 120, 25);
        endHour.setFont(new Font("Serif", Font.BOLD, 16));
        getEndHour.setBounds(480, 460, 90, 25);

        getRaportA.setBounds(710, 460, 100, 25);
        getRaportA.setBackground(new Color(201, 236, 255));
        getRaportA.addActionListener(this);

        productOrderTimes.setBounds(170, 495, 180, 25);
        productOrderTimes.setFont(new Font("Serif", Font.BOLD, 16));
        getProductOrderTimes.setBounds(340, 495, 90, 25);


        getRaportB.setBounds(710, 495, 100, 25);
        getRaportB.setBackground(new Color(201, 236, 255));
        getRaportB.addActionListener(this);

        clientOrderTimes.setBounds(170, 530, 180, 25);
        clientOrderTimes.setFont(new Font("Serif", Font.BOLD, 16));
        getClientOrderTimes.setBounds(335, 530, 90, 25);

        valueOrder.setBounds(445, 530, 150, 25);
        valueOrder.setFont(new Font("Serif", Font.BOLD, 16));
        getValueOrder.setBounds(560, 530, 90, 25);

        getRaportC.setBounds(710, 530, 100, 25);
        getRaportC.setBackground(new Color(201, 236, 255));
        getRaportC.addActionListener(this);

        specificDay.setBounds(170, 565, 150, 25);
        specificDay.setFont(new Font("Serif", Font.BOLD, 16));
        getSpecificDay.setBounds(270, 565, 90, 25);

//        orderTimes.setBounds(390, 565, 170, 25);
//        orderTimes.setFont(new Font("Serif", Font.BOLD, 16));
//        getOrderTimes.setBounds(560, 565, 90, 25);

        getRaportD.setBounds(710, 565, 100, 25);
        getRaportD.setBackground(new Color(201, 236, 255));
        getRaportD.addActionListener(this);

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
        panel.add(importItem);
        panel.add(insert);
        panel.add(update);
        panel.add(delete);
        panel.add(compute);

        panel.add(startHour);
        panel.add(getStartHour);
        panel.add(endHour);
        panel.add(getEndHour);
        panel.add(getRaportA);
        panel.add(productOrderTimes);
        panel.add(getProductOrderTimes);
        panel.add(getRaportB);
        panel.add(clientOrderTimes);
        panel.add(getClientOrderTimes);
        panel.add(valueOrder);
        panel.add(getValueOrder);
        panel.add(getRaportC);
        panel.add(specificDay);
        panel.add(getSpecificDay);
//        panel.add(orderTimes);
//        panel.add(getOrderTimes);
        panel.add(getRaportD);
        panel.add(list);

        panel.setBackground(new Color(254, 255, 220));
        admin.setMaximumSize(new Dimension(900, 610));
        admin.add(panel);
        frame.setContentPane(admin);
        frame.setVisible(true);
    }

    /**
     * Method that displays the products in a table
     * @return the JTable that will pe displayed
     */
    public JTable tableData() {
        String[] col = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        List<MenuItem> collection;
        collection = deliveryService.getItemCollection();
        collection = collection.stream().sorted(Comparator.comparing(MenuItem::getTitle)).distinct().collect(Collectors.toList());
        for (MenuItem menuItem : collection) {
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
        tableAdmin.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tableAdmin.setRowSelectionAllowed(true);
        tableAdmin.setColumnSelectionAllowed(false);
        tableAdmin = new JTable(tableModel);

        return tableAdmin;
    }

    /**
     * Method that updates the data from the JTable
     */
    public void updateTable()
    {
        panel.remove(scrollPane);
        tableAdmin = tableData();
        scrollPane = new JScrollPane(tableData());
        scrollPane.setBounds(155, 260, 600, 170);
        scrollPane.setVisible(true);
        panel.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == importItem) {
            deliveryService.importItem();
        }

        if (e.getSource() == insert) {
            String title = titleField.getText();
            double rating = Double.parseDouble(ratingField.getText());
            int calories = Integer.parseInt(caloriesField.getText());
            int fat = Integer.parseInt(fatField.getText());
            int sodium = Integer.parseInt(sodiumField.getText());
            int protein = Integer.parseInt(proteinField.getText());
            double price = Double.parseDouble(priceField.getText());
            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            //System.out.println(baseProduct);
            deliveryService.addProduct(baseProduct);
            updateTable();
        }
        if (e.getSource() == delete) {
            DefaultTableModel model = (DefaultTableModel) tableAdmin.getModel();
            int row = tableAdmin.getSelectedRow();
            String title = model.getValueAt(row, 0).toString();
            double rating = Double.parseDouble(model.getValueAt(row, 1).toString());
            int calories = Integer.parseInt(model.getValueAt(row, 2).toString());
            int fat = Integer.parseInt(model.getValueAt(row, 3).toString());
            int sodium = Integer.parseInt(model.getValueAt(row, 4).toString());
            int protein = Integer.parseInt(model.getValueAt(row, 5).toString());
            double price = Double.parseDouble(model.getValueAt(row, 6).toString());
            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            //System.out.println(baseProduct);
            deliveryService.deleteProduct(baseProduct);
            updateTable();
        }

        if (e.getSource() == update) {
            DefaultTableModel model = (DefaultTableModel) tableAdmin.getModel();
            int row = tableAdmin.getSelectedRow();
            String title = model.getValueAt(row, 0).toString();
            double rating = Double.parseDouble(model.getValueAt(row, 1).toString());
            int calories = Integer.parseInt(model.getValueAt(row, 2).toString());
            int fat = Integer.parseInt(model.getValueAt(row, 3).toString());
            int sodium = Integer.parseInt(model.getValueAt(row, 4).toString());
            int protein = Integer.parseInt(model.getValueAt(row, 5).toString());
            double price = Double.parseDouble(model.getValueAt(row, 6).toString());

            String titleNew = titleField.getText();
            double ratingNew = Double.parseDouble(ratingField.getText());
            int caloriesNew = Integer.parseInt(caloriesField.getText());
            int fatNew = Integer.parseInt(fatField.getText());
            int sodiumNew = Integer.parseInt(sodiumField.getText());
            int proteinNew = Integer.parseInt(proteinField.getText());
            double priceNew = Double.parseDouble(priceField.getText());
            BaseProduct newProduct = new BaseProduct(titleNew, ratingNew, caloriesNew, proteinNew, fatNew, sodiumNew, priceNew);
            BaseProduct oldProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            List<MenuItem> products = deliveryService.getItemCollection().stream().collect(Collectors.toList());
            for (MenuItem menuItem: products) {
                if (menuItem.getTitle().equals(oldProduct.getTitle())) {
                    deliveryService.deleteProduct(menuItem);
                }
            }
            deliveryService.editProduct(oldProduct, newProduct);
            updateTable();
//            System.out.println(oldProduct);
//            System.out.println(newProduct);
            //System.out.println(deliveryService.getItemCollection());
        }

        if (e.getSource() == compute) {
            DefaultTableModel model = (DefaultTableModel) tableAdmin.getModel();
            int[] indexs = tableAdmin.getSelectedRows();
            Object[] row = new Object[7];
            List<MenuItem> productList = new ArrayList<>();
            for (int index : indexs) {
                row[0] = model.getValueAt(index, 0);
                row[1] = model.getValueAt(index, 1);
                row[2] = model.getValueAt(index, 2);
                row[3] = model.getValueAt(index, 3);
                row[4] = model.getValueAt(index, 4);
                row[5] = model.getValueAt(index, 5);
                row[6] = model.getValueAt(index, 6);
                productList.add(new BaseProduct(row[0].toString(), Double.parseDouble(row[1].toString()), Integer.parseInt(row[2].toString()), Integer.parseInt(row[3].toString()), Integer.parseInt(row[4].toString()), Integer.parseInt(row[5].toString()), Double.parseDouble(row[6].toString())));
            }
            String title = JOptionPane.showInputDialog(frame, "Enter the title of the composite product", null);

            CompositeProduct produsCompus = new CompositeProduct(title, productList);
            produsCompus.setPrice(produsCompus.computePrice());
            produsCompus.setCalories(produsCompus.computeCalories());
            produsCompus.setFat(produsCompus.computeFat());
            produsCompus.setProtein(produsCompus.computeProtein());
            produsCompus.setSodium(produsCompus.computeSodium());
            produsCompus.setRating(produsCompus.computeRating());

            deliveryService.addProduct(produsCompus);
            updateTable();
            //System.out.println(produsCompus);

        }

        if (e.getSource() == getRaportA) {
            int sHour = Integer.parseInt(getStartHour.getText());
            int eHour = Integer.parseInt(getEndHour.getText());
            fileWriter.reportAWrite(deliveryService.getRaportA(sHour, eHour));
        }
        if (e.getSource() == getRaportB) {
            int times = Integer.parseInt(getProductOrderTimes.getText());
            fileWriter.reportBWrite(deliveryService.getRaportB(times));
        }
        if (e.getSource() == getRaportC) {
            int times = Integer.parseInt(getClientOrderTimes.getText());
            int value = Integer.parseInt(getValueOrder.getText());
            fileWriter.reportCWrite(deliveryService.getRaportC(times, value));
        }
        if (e.getSource() == getRaportD) {
            DayOfWeek day = DayOfWeek.of(Integer.parseInt(getSpecificDay.getText()));
            fileWriter.reportDWrite(deliveryService.getRaportD(day));
        }

        if (e.getSource() == list) {
          updateTable();
        }
    }
}



