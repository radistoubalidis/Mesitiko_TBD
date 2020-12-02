package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class RealtiesDAO {

    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185289" ;
    static Connection dbConnection = null;
    static String     username = "it185289";
    static String     passwd = "NevaT1red!";
    static Statement  statement = null;
    static ResultSet rs = null;


    private ObservableList<Realties> realtiesList;
    public void setRealtiesList(ObservableList<Realties> list) { this.realtiesList = list; }



    public static ObservableList<Realties> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from Realties";
        try{
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(sql);
            ObservableList<Realties> realtiesList = getRealtieObjects (RealtiesDAO.rs);
            return realtiesList;
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Error occured while fetching the record from the DataBase");
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Realties> getRealtieObjects(ResultSet rs) throws ClassNotFoundException ,SQLException {
        try{
            ObservableList realtiesList = FXCollections.observableArrayList();
            while(rs.next())
            {
                Realties real = new Realties();
                real.setIdProperty(rs.getString("Realtie_id"));
                real.setAddressProperty(rs.getString("address"));
                real.setM2Property(rs.getFloat("m2"));
                real.setR_typeProperty(rs.getString("r_type"));
                realtiesList.add(real);
            }
            return realtiesList;
        }catch (SQLException e){
            System.out.println("Error occured while fetching the record from the DataBase"+e);
            e.printStackTrace();
            throw e;
        }

    }


    public static boolean insertRealtie(String [] pedia , float m2) throws ClassNotFoundException,SQLException {
        String query = "select insert_realties('"+pedia[0]+"','"+pedia[1]+"','"+m2+"','"+pedia[2]+"')" ;
        boolean returnState=false;
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

    public static boolean deleteRealtie(Realties selectedItem) throws ClassNotFoundException,SQLException {


        String realtie_id = selectedItem.getIdProperty().get();
        String address = selectedItem.getAddressProperty().get();
        float m2 = selectedItem.getM2Property().get();
        String r_type = selectedItem.getR_typeProperty().get();
        String query = "select * from delete_realties('"+realtie_id+"','"+address+"','"+m2+"','"+r_type+"')";
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


    public static ObservableList<Realties> filterBy(@NotNull String pedio) throws ClassNotFoundException,SQLException{

        if(pedio.equals("ΔΙΑΜΕΡΙΣΜΑΤΑ")){
            String query = "Select * from realties where r_type='ΔΙΑΜΕΡΙΣΜΑ'";
            try{
                        Class.forName(driverClassName);
                        dbConnection = DriverManager.getConnection (url, username, passwd);
                        statement = dbConnection.createStatement();
                        rs = statement.executeQuery(query);
                        ObservableList realtiesList = FXCollections.observableArrayList();
                        while(rs.next()){
                          Realties real = new Realties();
                          real.setIdProperty(rs.getString(1));
                          real.setM2Property(rs.getFloat(3));
                          real.setAddressProperty(rs.getString(2));
                          real.setR_typeProperty(rs.getString(4));
                          realtiesList.add(real);
                       }
                       return realtiesList;
                   }catch (SQLException e){
                       e.printStackTrace();
                       throw e;
                   }

        }else if (pedio.equals("ΜΟΝΟΚΑΤΟΙΚΙΕΣ")){
                    String query = "Select * from realties where r_type='ΜΟΝΟΚΑΤΟΙΚΙΑ'";
                    try{
                        Class.forName(driverClassName);
                        dbConnection = DriverManager.getConnection (url, username, passwd);
                        statement = dbConnection.createStatement();
                        rs = statement.executeQuery(query);
                        ObservableList realtiesList = FXCollections.observableArrayList();
                        while(rs.next()){
                            Realties real = new Realties();
                            real.setIdProperty(rs.getString(1));
                            real.setM2Property(rs.getFloat(3));
                            real.setAddressProperty(rs.getString(2));
                            real.setR_typeProperty(rs.getString(4));
                            realtiesList.add(real);
                        }
                        return realtiesList;
                    }catch (SQLException | ClassNotFoundException e){
                        e.printStackTrace();
                        throw e;
                    }
        }else if(pedio.equals("ΒΙΛΛΕΣ")){
                    String query = "Select * from realties where r_type='ΒΙΛΛΑ'";
                    try{
                        Class.forName(driverClassName);
                        dbConnection = DriverManager.getConnection (url, username, passwd);
                        statement = dbConnection.createStatement();
                        rs = statement.executeQuery(query);
                        ObservableList realtiesList = FXCollections.observableArrayList();
                        while(rs.next()){
                            Realties real = new Realties();
                            real.setIdProperty(rs.getString(1));
                            real.setM2Property(rs.getFloat(3));
                            real.setAddressProperty(rs.getString(2));
                            real.setR_typeProperty(rs.getString(4));
                            realtiesList.add(real);
                        }
                        return realtiesList;
                    }catch (SQLException e){
                        e.printStackTrace();
                        throw e;
                    }
        }else if (pedio.equals("ΕΠΑΓΓΕΛΜΑΤΙΚΟΙ ΧΩΡΟΙ ")){
                    String query = "Select * from realties where r_type='ΕΠΑΓΓΕΛΜΑΤΙΚΟΣ ΧΩΡΟΣ'";
                    try{
                        Class.forName(driverClassName);
                        dbConnection = DriverManager.getConnection (url, username, passwd);
                        statement = dbConnection.createStatement();
                        rs = statement.executeQuery(query);
                        ObservableList realtiesList = FXCollections.observableArrayList();
                        while(rs.next()){
                            Realties real = new Realties();
                            real.setIdProperty(rs.getString(1));
                            real.setM2Property(rs.getFloat(3));
                            real.setAddressProperty(rs.getString(2));
                            real.setR_typeProperty(rs.getString(4));
                            realtiesList.add(real);
                        }
                        return realtiesList;
                    }catch (SQLException e){
                        e.printStackTrace();
                        throw e;
                    }
        }else if(pedio.equals("ΓΗ")){
                    String query = "Select * from realties where r_type='ΓΗ'";
                    try{
                        Class.forName(driverClassName);
                        dbConnection = DriverManager.getConnection (url, username, passwd);
                        statement = dbConnection.createStatement();
                        rs = statement.executeQuery(query);
                        ObservableList realtiesList = FXCollections.observableArrayList();
                        while(rs.next()){
                            Realties real = new Realties();
                            real.setIdProperty(rs.getString(1));
                            real.setM2Property(rs.getFloat(3));
                            real.setAddressProperty(rs.getString(2));
                            real.setR_typeProperty(rs.getString(4));
                            realtiesList.add(real);
                        }
                        return realtiesList;
                    }catch (SQLException e){
                        e.printStackTrace();
                        throw e;
                    }
        }else {
            try{
                Class.forName(driverClassName);
                dbConnection = DriverManager.getConnection(url,username,passwd);
                statement = dbConnection.createStatement();
                rs = statement.executeQuery("Select * from realties");
                ObservableList realtiesList = FXCollections.observableArrayList();
                while(rs.next()){
                    Realties real = new Realties();
                    real.setIdProperty(rs.getString(1));
                    real.setM2Property(rs.getFloat(3));
                    real.setAddressProperty(rs.getString(2));
                    real.setR_typeProperty(rs.getString(4));
                    realtiesList.add(real);
                }
                return realtiesList;
            }catch (SQLException e){
                e.printStackTrace();
                throw e;
            }
        }
    }




}