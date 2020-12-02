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


    public static boolean insertCustomer(String [] pedia) throws ClassNotFoundException, SQLException {
        String query = "select * from insert_customers('"+pedia[0]+"','"+pedia[1]+"','"+pedia[2]+"')";
        boolean returnState=false;
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url,username,passwd);
        try {
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(query);
            if(rs.rowUpdated())    returnState = true;
        }catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnState;

    }

    public static boolean deleteCustomer(Customers selectedItem){
        String customer_id = selectedItem.getIdProperty().get();
        String name = selectedItem.getNameProperty().get();
        String contact = selectedItem.getPhoneProperty().get();
        String query = "select * from delete_customers('"+customer_id+"','"+name+"','"+contact+"')";
        boolean returnState = false ;

        try {
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(query);
            returnState = true ;
        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return returnState;
    }


}
