package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomersDAO {

    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185289" ;
    static Connection dbConnection = null;
    static String     username = "it185289";
    static String     passwd = "NevaT1red!";
    static Statement  statement = null;
    static ResultSet rs = null;

    public static ObservableList<Customers> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from Customers";
        try{
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Customers> customersList = getCustomerObjects (CustomersDAO.rs);
            return customersList;
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }


    private static ObservableList<Customers> getCustomerObjects(ResultSet rs) throws ClassNotFoundException ,SQLException {
        try{
            ObservableList customersList = FXCollections.observableArrayList();
            while(rs.next())
            {
                Customers cust = new Customers();
                cust.setIdProperty(rs.getString("Customer_id"));
                cust.setNameProperty(rs.getString("fullName"));
                cust.setPhoneProperty(rs.getString("contact_number"));
                customersList.add(cust);
            }
            return customersList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the record from the DataBase"+e);
            e.printStackTrace();
            throw e;
        }

    }

}
