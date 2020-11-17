package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class ContractsDAO {

    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185289" ;
    static Connection dbConnection = null;
    static String     username = "it185289";
    static String     passwd = "NevaT1red!";
    static Statement  statement = null;
    static ResultSet rs = null;

    public static ObservableList<Contracts> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from contract_names";
        try{
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Contracts> contractsList = getContractObjects(ContractsDAO.rs);
            return contractsList;
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Contracts> getContractObjects(ResultSet rs) throws ClassNotFoundException,SQLException{
        try{
            ObservableList<Contracts> contractsList = FXCollections.observableArrayList();
            while (rs.next()){
                Contracts con = new Contracts();
                con.setCustomerName(rs.getString(1));
                con.setOwnerName(rs.getString(2));
                con.setRealtieID(rs.getString(3));
                con.setStartDate(rs.getString(4));
                contractsList.add(con);
            }
            return contractsList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the record from the DataBase"+e);
            e.printStackTrace();
            throw e;
        }
    }

}
