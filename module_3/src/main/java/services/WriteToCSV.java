package services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class WriteToCSV {

    private static final Logger logInfo = LoggerFactory.getLogger("info");
    private static final Logger logWarn = LoggerFactory.getLogger("warn");
    private static final Logger logError = LoggerFactory.getLogger("error");

    public void exportInCSV(String name, String email, String password){
        Scanner scanner = new Scanner(System.in);
        Properties props = loadProperties();

        String url = props.getProperty("url");

        try(Connection connection = DriverManager.getConnection(url, props)){
            ResultSet resultSet;
            Integer user_id = null;
            try(PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ? and email = ? and password = ?")){
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                resultSet = preparedStatement.executeQuery();
                if(!resultSet.next()){
                    logError.error("Couldn't sign in. Wrong input. email: "  + email);
                    throw new RuntimeException("Such user wasn't found. Input was wrong");
                }
                else{
                    System.out.println("System signed in");
                    logInfo.info("System signed in email: "  + email);
                }
                user_id = resultSet.getInt("id");
            }
            try(PreparedStatement preparedStatement = connection.prepareStatement("select * from invoices where user_id = ?")) {
                preparedStatement.setInt(1, user_id);
                resultSet = preparedStatement.executeQuery();
                System.out.println("List of invoices:");
                while (resultSet.next()){
                    System.out.println("id:" + resultSet.getInt("id") + ", amount: " + resultSet.getLong("amount"));
                }
                System.out.println("Choose the invoice (input index)");
                int index = scanner.nextInt();
                System.out.println("Choose the type of sorting by time\n" +
                        "1 >> ascending\n" +
                        "2 >> descending");
                String choice = scanner.next();
                switch (choice){
                    case "1" -> {
                        writer(connection, index, 1);
                    }
                    case "2" -> {
                        writer(connection, index, 2);
                    }
                    default -> {
                        System.out.println("Wrong input");
                        logWarn.warn("Incorrect number when chose action");
                    }
                }
            }
        } catch (SQLException e) {
            logError.error("Error while try find need user and his invoices");
            throw new RuntimeException(e);
        }
    }

    private void writer(Connection connection, int index, int wayOfSorting){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String query = "select operations.id, operations.time, operations.difference, operations.invoice_id, categories.categories, categories.description from operations " +
                "join categories on categories.id=operations.category_id " +
                "where operations.invoice_id = ? and operations.time between ? and ?";
        System.out.println("Input time from which you want to see operations (e.g. 29/07/2021 12:45)");
        String from, to;
        Date dateFrom, dateTo;
        Instant iFrom = null, iTo = null;
        try {
            from = reader.readLine();
            logInfo.info("From date: " + from);
            dateFrom = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(from);
            iFrom = dateFrom.toInstant();
            System.out.println("Input time to which you want to see operations (e.g. 29/07/2021 20:00)");
            to = reader.readLine();
            logInfo.info("To date: " + to);
            dateTo = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(to);
            iTo = dateTo.toInstant();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        if(wayOfSorting == 1){
            query += " order by operations.time asc";
        }
        else
            query += " order by operations.time desc";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, index);
            preparedStatement.setTimestamp(2, Timestamp.from(iFrom));
            preparedStatement.setTimestamp(3, Timestamp.from(iTo));
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("operations.csv", false))){
                ResultSet resultSet = preparedStatement.executeQuery();
                writer.write("operation id, time, amount, category, description\n");
                while(resultSet.next()){
                    writer.write(
                    resultSet.getInt("id") + "," +
                            resultSet.getTimestamp("time") + "," +
                            resultSet.getLong("difference") + "," +
                            resultSet.getString("categories") + "," +
                            resultSet.getString("description") + "\n");
                }
            }
        }catch (SQLException | IOException e) {
            logError.error("Error when try to write data in csv file");
            throw new RuntimeException(e);
        }
    }

    private Properties loadProperties() {

        Properties props = new Properties();

        try(InputStream input = WriteToCSV.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }
}
