package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class For_sale_rentDAO {

    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185289" ;
    static Connection dbConnection = null;
    static String     username = "it185289";
    static String     passwd = "NevaT1red!";
    static Statement statement = null;
    static ResultSet rs = null;


    public static ObservableList<For_sale_rent> getAllRecords() throws ClassNotFoundException , SQLException {
        String sql  = "select * from FSR_EXTENDED";
        try{
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<For_sale_rent> diathesimaList = getDiathesimaObjects(For_sale_rentDAO.rs);
            return diathesimaList;
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<For_sale_rent> getDiathesimaObjects(ResultSet rs) throws ClassNotFoundException,SQLException{
        try {
            ObservableList diathesimaList = FXCollections.observableArrayList();
            while (rs.next()){
                For_sale_rent fsr = new For_sale_rent();
                fsr.setOwnerName(rs.getString(1));
                fsr.setRealtieId(rs.getString(2));
                fsr.setContractType(rs.getString(3));
                diathesimaList.add(fsr);
            }
            return diathesimaList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the record from the DataBase"+e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<For_sale_rent> filterBy(String epilogh) throws SQLException,ClassNotFoundException {
        if (epilogh.equals("RENT")) {
            try {
                String query = "Select * from FSR_EXTENDED where contract_type='RENT'";
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                ObservableList fsrList = FXCollections.observableArrayList();
                while (rs.next()) {
                    For_sale_rent fsr = new For_sale_rent();
                    fsr.setOwnerName(rs.getString(1));
                    fsr.setRealtieId(rs.getString(2));
                    fsr.setContractType(rs.getString(3));
                    fsrList.add(fsr);
                }
                return fsrList;
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw ex;
            }
        } else if (epilogh.equals("SALE")) {
            try {
                String query = "Select * from FSR_EXTENDED where contract_type='SALE'";
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(query);
                ObservableList fsrList = FXCollections.observableArrayList();
                while (rs.next()) {
                    For_sale_rent fsr = new For_sale_rent();
                    fsr.setOwnerName(rs.getString(1));
                    fsr.setRealtieId(rs.getString(2));
                    fsr.setContractType(rs.getString(3));
                    fsrList.add(fsr);
                }
                return fsrList;
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        } else if (epilogh.equals("")) {
            try {
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery("Select * from FSR_EXTENDED");
                ObservableList fsrList = FXCollections.observableArrayList();
                while (rs.next()) {
                    For_sale_rent fsr = new For_sale_rent();
                    fsr.setOwnerName(rs.getString(1));
                    fsr.setRealtieId(rs.getString(2));
                    fsr.setContractType(rs.getString(3));
                    fsrList.add(fsr);
                }
                return fsrList;
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            try {
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url, username, passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery("select * " +
                        "from FSR_EXTENDED " +
                        "where realtie_id in (select realtie_id from contracts)");
                ObservableList fsrList = FXCollections.observableArrayList();
                while (rs.next()) {
                    For_sale_rent fsr = new For_sale_rent();
                    fsr.setOwnerName(rs.getString(1));
                    fsr.setRealtieId(rs.getString(2));
                    fsr.setContractType(rs.getString(3));
                    fsrList.add(fsr);
                }
                return fsrList;
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

}




