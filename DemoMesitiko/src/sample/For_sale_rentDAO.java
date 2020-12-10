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
        String sql  = "select * from displayFSR()";
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
        String query = "Select * from filterContract('"+epilogh+"')";
        if (epilogh.equals("RENT")) {
            try {
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
                rs = statement.executeQuery("select * FROM filterAvailable()");
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


    public static boolean insertFSR(String ownerName,String realtie_id,String contractType) throws SQLException, ClassNotFoundException {
        Owners ow = findOwner(ownerName);
        Realties real = findRealtie(realtie_id);
        boolean returnState = false;

        if(ow != null && real != null){
            String sql = "select * from insert_for_sale_rent('"+real.getIdProperty().get()+"','"+ow.idProperty().get()+"','"+contractType+"')";

            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url,username,passwd);
            try {
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(sql);
                returnState = true;
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnState;

        }else return false;
    }


    public static boolean deleteFSR(For_sale_rent selectedItem) throws SQLException, ClassNotFoundException {
        Owners ow = findOwner(selectedItem.ownerNameProperty().get());
        Realties real = findRealtie(selectedItem.realtieIdProperty().get());
        boolean returnState = false;

        if(ow != null && real != null){
            String sql = "select * from delete_for_sale_rent('"+real.getIdProperty().get()+"','"+ow.idProperty().get()+"','"+selectedItem.contractTypeProperty().get()+"')";
            Class.forName(driverClassName);
            dbConnection = DriverManager.getConnection(url,username,passwd);
            try {
                statement = dbConnection.createStatement();
                rs = statement.executeQuery(sql);
                returnState = true;
            }catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnState;

        }else return false;
    }






    public static Owners findOwner(String name) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url,username,passwd);
        try{
            Owners ow = new Owners();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery("select * from findowner('"+name+"')");
            ResultSetMetaData rsMetadata = rs.getMetaData();
            System.out.println(rsMetadata.getColumnCount());
            while (rs.next()) {
                ow.setId(rs.getString(1));
                ow.setFullName(rs.getString(2));
                ow.setPhone(rs.getString(3));
                ow.setNumOfRealties(rs.getInt(4));
            }
            return ow;
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Realties findRealtie(String id) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url,username,passwd);
        try{
            Realties real = new Realties();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery("select * from findRealtie('"+id+"')");
            ResultSetMetaData rsMetadata = rs.getMetaData();
            System.out.println(rsMetadata.getColumnCount());
            while (rs.next()) {
                real.setIdProperty(rs.getString(1));
                real.setAddressProperty(rs.getString(2));
                real.setM2Property(rs.getFloat(3));
                real.setR_typeProperty(rs.getString(4));
            }
            return real;
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}




