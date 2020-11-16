package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.postgresql.util.PSQLException;

import java.sql.*;


import static java.lang.Double.parseDouble;

public class RealtiesDAO {

    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it185289" ;
    static Connection dbConnection = null;
    static String     username = "it185289";
    static String     passwd = "NevaT1red!";
    static Statement  statement = null;
    static ResultSet rs = null;

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


        String query = "INSERT INTO realties(realtie_id,address,m2,r_type) VALUES (?,?,?,?);";
        boolean returnState=false;
        try (Connection db_conn = DriverManager.getConnection(url, username, passwd);
                PreparedStatement ps = dbConnection.prepareStatement(query))
        {
                ps.setString(1, pedia[0]);
                ps.setString(2, pedia[1]);
                ps.setFloat(3, m2);
                ps.setString(4, pedia[2]);
                ps.executeUpdate();
                returnState = true;


        }catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnState;
    }






}