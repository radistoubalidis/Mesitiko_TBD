package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class OwnersDAO {

    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185289" ;
    static Connection dbConnection = null;
    static String     username = "it185289";
    static String     passwd = "NevaT1red!";
    static Statement  statement = null;
    static ResultSet rs = null;

    public static ObservableList<Owners> getAllRecords() throws ClassNotFoundException,SQLException {
        String sql = "select * from owners";

        try
        {
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Owners> ownersList = getOwnerObjects(OwnersDAO.rs);
            return ownersList;
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }


    private static ObservableList<Owners> getOwnerObjects(ResultSet rs) throws  ClassNotFoundException,SQLException{

        try
        {
            ObservableList<Owners> ownersList = FXCollections.observableArrayList();
            while (rs.next())
            {
                Owners ow = new Owners();
                ow.setId(rs.getString("owner_id"));
                ow.setFullName(rs.getString("fullname"));
                ow.setPhone(rs.getString("contact_number"));
                ow.setNumOfRealties(rs.getInt("numofrealties"));
                ownersList.add(ow);
            }
            return ownersList;
        }catch (SQLException e)
        {
            System.out.println("Error occured while fetching the record from the DataBase"+e);
            e.printStackTrace();
            throw e;
        }
    }


    public static boolean insertOwner(String [] pedia,int numOfRealties) throws ClassNotFoundException, SQLException {
        boolean returnState = false;
        String query = "select * from insert_owners('"+pedia[0]+"','"+pedia[1]+"','"+pedia[2]+"','"+numOfRealties+"')" ;
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url,username,passwd);
        try {
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(query);
            returnState = true;
        }catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnState;
    }

    public static boolean deleteOwner(Owners selectedItem){
        String owner_id = selectedItem.idProperty().get();
        String name = selectedItem.fullNameProperty().get();
        String number = selectedItem.phoneProperty().get();
        int numOfrealties = selectedItem.numOfRealtiesProperty().get();
        boolean returnState = false;
        String query = "select * from delete_owners('"+owner_id+"','"+name+"','"+number+"','"+numOfrealties+"')";
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
