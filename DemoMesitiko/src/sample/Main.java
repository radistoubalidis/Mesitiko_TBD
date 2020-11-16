/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.lang.*;


import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    Stage window;
    Scene loginScene,myPageScene,realtiesScene,customersScene,ownersScene,contractsScene,diathesimaScene;

    static boolean result;


    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;


        // load login page file
        FXMLLoader login = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent loginRoot = login.load();
        loginScene = new Scene(loginRoot);


        //load menu page file
        FXMLLoader myPage = new FXMLLoader(getClass().getResource("myPage.fxml"));
        Parent myPageRoot = myPage.load();
        myPageScene = new Scene(myPageRoot);


        //load realties page file
        FXMLLoader realtiesPage = new FXMLLoader(getClass().getResource("Realties.fxml"));
        Parent realtiesRoot = realtiesPage.load();
        realtiesScene = new Scene(realtiesRoot);


        //load customers page file
        FXMLLoader customersPage = new FXMLLoader(getClass().getResource("Customers.fxml"));
        Parent customersRoot = customersPage.load();
        customersScene = new Scene(customersRoot);


        //load owners page file
        FXMLLoader ownersPage = new FXMLLoader(getClass().getResource("Owners.fxml"));
        Parent ownersRoot = ownersPage.load();
        ownersScene = new Scene(ownersRoot);

        //load contracts page file
        FXMLLoader contractsPage = new FXMLLoader(getClass().getResource("Contracts.fxml"));
        Parent contractsRoot = contractsPage.load();
        contractsScene = new Scene(contractsRoot);

        //load diathesima akinhta page file
        FXMLLoader diathesimaPage = new FXMLLoader(getClass().getResource("For_sale_rent.fxml"));
        Parent diathesimaRoot = diathesimaPage.load();
        diathesimaScene = new Scene(diathesimaRoot);







        // event handler gia otan pataei to koympi sign in
        LoginPageController loginCntrl = login.getController();
        loginCntrl.getAuthButton().setOnAction(e -> {
                if(loginCntrl.getUserTextField().getText().equals("radis") && loginCntrl.getPassWordTextField().getText().equals("radis")){
                    window.setScene(myPageScene);
                }else{
                    loginCntrl.getOutputText().setText("Authentication Error.");
                }
        });


        //ftiaxnw ena antikeimeno MyPageController gia diaxeirish
        MyPageController myPageCntrl = myPage.getController();

        //event handling gia otan pataei to koympi probolh akinhtwn
        myPageCntrl.getRealties().setOnAction(e -> {
                window.setScene(realtiesScene);

        });

        //event handling gia otan pataei to koympi probolh pelatwn
        myPageCntrl.getCustomers().setOnAction(e -> {
            window.setScene(customersScene);
        });

        //event handling gia otan pataei to koumpi probolh idiokthtwn
        myPageCntrl.getOwners().setOnAction(e -> {
            window.setScene(ownersScene);
        });

        //event handling gia otan pataei to koumpi probolh sumbolaiwn
        myPageCntrl.getContracts().setOnAction(e -> {
            window.setScene(contractsScene);
        });

        //event handling gia otan pataei to koumpi diathesima akinhta
        myPageCntrl.getFor_sale_rent().setOnAction(e -> {
            window.setScene(diathesimaScene);
        });



        //event handling gia otan pataei to koumpi go back sthn realtiesScene
        RealtiesController realtiesCntrl = realtiesPage.getController();
        realtiesCntrl.getGoBackButton().setOnAction(e -> {
            window.setScene(myPageScene);
        });


        //event handling gia otan pataei to koumpi go back sthn customersScene
        CustomersController customersCntrl = customersPage.getController();
        customersCntrl.getGoBackButton().setOnAction(e -> {
            window.setScene(myPageScene);
        });

        //event hanlding gia otan pataei go Back sto ownersScene
        OwnersController ownersCntrl = ownersPage.getController();
        ownersCntrl.getGoBackButton().setOnAction(e -> {
            window.setScene(myPageScene);
        });

        //event handling gia otan pataei to koumpi goBack sthn contractScene
        ContractsController contractsCntrl = contractsPage.getController();
        contractsCntrl.getGoBackButton().setOnAction(e -> {
            window.setScene(myPageScene);
        });

        //event handling gia otan pataei to koumpi diathesima akinhta
        For_sale_rentController diathesimaCntrl = diathesimaPage.getController();
        diathesimaCntrl.getGoBackButton().setOnAction(e -> {
            window.setScene(myPageScene);
        });

        //event handling gia otan paei na kanei diagrafh stous customers
        customersCntrl.getDeleteButton().setOnAction(e ->{
            customersCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ!");
        });

        //event handling gia otan paei na kanei diagrafh stous owners
        ownersCntrl.getDeleteButton().setOnAction(e ->{
            ownersCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ!");
        });

        //event handling gia otan paei na kanei diagrafh stous realties
        realtiesCntrl.getDeleteButton().setOnAction(e ->{
            realtiesCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
        });

        //event handling otan pataei to koumpi diagrafh
        contractsCntrl.getDeleteButton().setOnAction(e ->{
            contractsCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
        });





        //event handling gia otan pathsei eisagwgh akinhtoy

        realtiesCntrl.getInsertRealtie().setOnAction(e -> {

            String [] pedia = {realtiesCntrl.getNewRealtieId().getText()
                    ,realtiesCntrl.getNewRealtieAddress().getText()
                    ,realtiesCntrl.getNewRealtieRtype().getText()};
            String m2str = realtiesCntrl.getNewRealtieM2().getText();
            Float m2=null;
            String m2_str = realtiesCntrl.getNewRealtieM2().getText();
            float m2_real = m2.parseFloat(m2str);
            try {
                 result = realtiesCntrl.insert(pedia,m2_real);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            if (result)
                    realtiesCntrl.getInsertMessage().setText("Η ΕΓΓΡΑΦΗ ΟΛΟΚΛΗΡΩΘΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
            else {
                realtiesCntrl.getInsertMessage().setTextFill(Color.web("#FF0000"));
                realtiesCntrl.getInsertMessage().setText("Η ΕΓΓΡΑΦΗ ΔΕΝ ΠΡΑΓΜΑΤΟΠΟΙΗΘΗΚΕ ΠΑΡΑΚΑΛΩ ΠΡΟΣΠΑΘΕΙΣΤΕ ΞΑΝΑ!");
                /*TimeUnit sleep = TimeUnit.SECONDS;
                try {
                    sleep.sleep((long)4);
                    realtiesCntrl.getInsertMessage().setText("");
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }*/
            }
        });









        //event handling gia kleisimo efarmoghs
        myPageCntrl.getCloseButton().setOnAction(e -> {
            window.close();
        });

        window.setTitle("E-MESITIKO");
        window.setScene(loginScene);
        window.show();
    }




    public static void main(String[] args) {
        launch(args);
    }


}
