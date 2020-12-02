package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


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

    public static boolean  insertContract(String customerName, String ownerName, String realtie_id, String date) throws SQLException, ClassNotFoundException {
            Customers selectedCustomer = findCustomer(customerName);
            Owners selectedOwner = findOwner(ownerName);
            Realties selectedRealtie = findRealtie(realtie_id);
            Contracts newContract = new Contracts();
            newContract.setCustomerName(selectedCustomer.getNameProperty().get());
            newContract.setOwnerName(selectedOwner.fullNameProperty().get());
            newContract.setRealtieID(selectedRealtie.getIdProperty().get());
            newContract.setStartDate(date);
            boolean returnState = false;
            System.out.println(selectedCustomer);
            System.out.println(selectedOwner);
            System.out.println(selectedRealtie);

            if(selectedCustomer != null && selectedOwner != null && selectedRealtie != null){
                String query = "select * from insert_contracts('"+
                        selectedCustomer.getIdProperty().get()+"','"+
                        selectedOwner.idProperty().get()+"','"+
                        selectedRealtie.getIdProperty().get()+"','"+
                        newContract.startDateProperty().get()+"')";
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
            }else {
                return false;
            }
    }

    public static boolean deleteContract(Contracts selectedItem) throws SQLException, ClassNotFoundException, ParseException {
        Customers selectedCustomer  = findCustomer(selectedItem.customerNameProperty().get()) ;
        Owners selectedOwner = findOwner(selectedItem.ownerNameProperty().get());
        Realties selectedRealtie = findRealtie(selectedItem.realtieIDProperty().get());
        String str_date = selectedItem.startDateProperty().get();

        boolean returnState = false;


        if(selectedCustomer != null && selectedOwner != null && selectedRealtie != null){

            String query = "select * from delete_contracts('"+
                    selectedCustomer.getIdProperty().get()+"','"+
                    selectedOwner.idProperty().get()+"','"+
                    selectedRealtie.getIdProperty().get()+"','"+str_date+"')";
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

        }else return false;
    }


    public static Customers findCustomer(String name) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        dbConnection = DriverManager.getConnection(url,username,passwd);
        try{
            Customers cust = new Customers();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery("select * from findcustomer('"+name+"')");
            ResultSetMetaData rsMetadata = rs.getMetaData();
            System.out.println(rsMetadata.getColumnCount());
            while (rs.next()) {
                cust.setIdProperty(rs.getString(1));
                cust.setNameProperty(rs.getString(2));
                cust.setPhoneProperty(rs.getString(3));
            }
            return cust;
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
