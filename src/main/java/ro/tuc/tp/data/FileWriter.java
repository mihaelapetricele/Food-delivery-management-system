package ro.tuc.tp.data;

import ro.tuc.tp.business.Order;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class that implements the methods for displaying the bill and reports in the file
 * @author Petricele Mihaela
 */
public class FileWriter {

    /**
     * The method that generates the bill
     * @param order the order that was placed
     */
    public static void writeOrder(Order order)
    {
        try {
            File  billFile = new File("bill" + order.getOrderID() + "_" +order.getClientID() + ".txt" );
            java.io.FileWriter fileWriter = new java.io.FileWriter(billFile);
            fileWriter.write(order.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method performs writing to the file data on the first report
     * @param orderList the list of orders placed
     */
    public void reportAWrite(List<Order> orderList) {
        try {
            StringBuilder result = new StringBuilder();
            File firstFile = new File("firstReport.txt");
            java.io.FileWriter firstWriter = new java.io.FileWriter("firstReport.txt");
            if (firstFile.createNewFile()) {
                System.out.println("Fiserul s-a creat: " + firstFile.getName());
            } else {
                System.out.println("Fisierul exista deja");
            }
            result.append("The orders performed between a given Start Hour and a given End Hour\n");
            result.append("\n");
            for(Order order : orderList)
            {
                result.append("Order nr: ").append(order.getOrderID()).append("\n");
                result.append("Client ID:").append(order.getClientID()).append("\n");
                result.append("Date: ").append(order.getTimestampOrder()).append("\n");
            }
            result.append("\n");
            firstWriter.write(String.valueOf(result));
            firstWriter.flush();
            firstWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method performs writing to the file data on the second report
     * @param resultBReport the data to be displayed
     */
    public void reportBWrite(String resultBReport) {
        try {
            StringBuilder result = new StringBuilder();
            File firstFile = new File("secondReport.txt");
            java.io.FileWriter firstWriter = new java.io.FileWriter("secondReport.txt");
            if (firstFile.createNewFile()) {
                System.out.println("Fiserul s-a creat: " + firstFile.getName());
            } else {
                System.out.println("Fisierul exista deja");
            }
            result.append("The products ordered more than a specified number of times so far\n");
            result.append("\n");
            result.append(resultBReport);
            firstWriter.write(String.valueOf(result));
            firstWriter.flush();
            firstWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method performs writing to the file data on the third report
     * @param resultCReport the data to be displayed
     */
    public void reportCWrite(String resultCReport) {
        try {
            StringBuilder result = new StringBuilder();
            File firstFile = new File("thirdReport.txt");
            java.io.FileWriter firstWriter = new java.io.FileWriter("thirdReport.txt");
            if (firstFile.createNewFile()) {
                System.out.println("Fiserul s-a creat: " + firstFile.getName());
            } else {
                System.out.println("Fisierul exista deja");
            }
            result.append("The clients that have ordered more than a specified number of times and\n").append("The value of the order was higher than a specified amount\n");
            result.append("\n");
            result.append(resultCReport);
            firstWriter.write(String.valueOf(result));
            firstWriter.flush();
            firstWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method performs writing to the file data on the fourth report
     * @param resultDReport the data to be displayed
     */
    public void reportDWrite(String resultDReport) {
        try {
            StringBuilder result = new StringBuilder();
            File firstFile = new File("fourthReport.txt");
            java.io.FileWriter firstWriter = new java.io.FileWriter("fourthReport.txt");
            if (firstFile.createNewFile()) {
                System.out.println("Fiserul s-a creat: " + firstFile.getName());
            } else {
                System.out.println("Fisierul exista deja");
            }
            result.append("The products ordered within a specified day with\n").append(" The number of times they have been ordered\n");
            result.append("\n");
            result.append(resultDReport);
            firstWriter.write(String.valueOf(result));
            firstWriter.flush();
            firstWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
