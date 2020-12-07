/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.lang.*;
import javafx.util.Duration;
import javafx.animation.*;


import java.sql.SQLException;
import java.text.ParseException;


public class Main extends Application {

    Stage window;
    Scene loginScene,myPageScene,realtiesScene,customersScene,ownersScene,contractsScene,diathesimaScene;

    static boolean result;


    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;


        // LOAD FXML FILES - start

        // load login page file
        FXMLLoader login = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent loginRoot = login.load();
        loginScene = new Scene(loginRoot);


        //load menu page file
        //load menu page file
        FXMLLoader myPage = new FXMLLoader(getClass().getResource("myPage.fxml"));
        Parent myPageRoot = myPage.load();
        myPageScene = new Scene(myPageRoot);
        String css = getClass().getResource("style.css").toExternalForm();
        myPageScene.getStylesheets().add(css);


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


        // LOAD FXML FILES - end



        // event handler gia otan pataei to koympi sign in - start
        LoginPageController loginCntrl = login.getController();
        loginCntrl.getAuthButton().setOnAction(e -> {
                if(loginCntrl.getUserTextField().getText().equals("radis") && loginCntrl.getPassWordTextField().getText().equals("radis")){
                    window.setScene(myPageScene);
                }else{
                    loginCntrl.getOutputText().setText("Authentication Error.");
                }
        });
        // event handler gia otan pataei to koympi sign in - end


        //ftiaxnw ena antikeimeno MyPageController gia diaxeirish
        MyPageController myPageCntrl = myPage.getController();


        //OPEN PAGES - start

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

        // GO BACK BUTTONS HANDLE

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
        // go back buttons end

        //OPEN PAGES - end





        //  REALTIES FXML EVENT HANDLING - start

        //event handling gia otan paei na kanei diagrafh/eisagwgh stous realties
        realtiesCntrl.getDeleteButton().setOnAction(e ->{
            deleteRealtie(realtiesCntrl);
        });

        realtiesCntrl.getInsertRealtie().setOnAction(e -> {
            insertRealtie(realtiesCntrl);
        });

            //event handling gia otan pataei kapoio apo ta radio Buttons
            final ToggleGroup myFilters_realties = realtiesCntrl.getFilters();
            realtiesCntrl.getFilterByDiamerismata().setToggleGroup(myFilters_realties);
            realtiesCntrl.getFilterByMonokatoikia().setToggleGroup(myFilters_realties);
            realtiesCntrl.getFilterByVilla().setToggleGroup(myFilters_realties);
            realtiesCntrl.getFilterByEpaggelmatikosXwros().setToggleGroup(myFilters_realties);
            realtiesCntrl.getFilterByGh().setToggleGroup(myFilters_realties);
            realtiesCntrl.getDeleteFilters().setToggleGroup(myFilters_realties);

            myFilters_realties.selectedToggleProperty().addListener(e ->{
                    String epilogh = ((RadioButton)myFilters_realties.getSelectedToggle()).getText();
                   realtiesCntrl.getFilterSelected().setText("show only "+ epilogh);
                    try {
                        realtiesCntrl.FilterBy(epilogh);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                });

            //handling otan pathsei delete filters
            realtiesCntrl.getDeleteFilters().setOnAction(e ->{
                try {
                    realtiesCntrl.FilterBy("");
                    realtiesCntrl.getFilterSelected().setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            });

        //REALTIES FXML EVENT HANDLING - end


        // CONTRACTS FXML EVENT HANDLING - start

        //event handling gia eisagwgh diagrafh stous contracts
        contractsCntrl.getInsertContract().setOnAction(e ->{
            try {
                insertContract(contractsCntrl);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        });

        contractsCntrl.getDeleteButton().setOnAction(e ->{
            deleteContract(contractsCntrl);
        });

        // CONTRACTS FXML EVENT HANDLING - end



        // CUSTOMERS FXML EVENT HANDLING - start

        //event handling gia eisagwgh diagrafh stous customers
        customersCntrl.getInsertButton().setOnAction(e ->{
            insertCustomer(customersCntrl);
        });
        customersCntrl.getDeleteButton().setOnAction(e ->{
            try {
                deleteCustomer(customersCntrl);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });




        // FOR_SALE_RENT EVENT HANDLING - start

        //event handling gia filtra sto fsr
        final ToggleGroup myFilters_fsr = diathesimaCntrl.getFilters();
        diathesimaCntrl.getFilterByRent().setToggleGroup(myFilters_fsr);
        diathesimaCntrl.getFilterBySale().setToggleGroup(myFilters_fsr);
        diathesimaCntrl.getDeleteFilters().setToggleGroup(myFilters_fsr);
        diathesimaCntrl.getDiathesima().setToggleGroup(myFilters_fsr);
        myFilters_fsr.selectedToggleProperty().addListener(e ->{
            String epilogh = ((RadioButton)myFilters_fsr.getSelectedToggle()).getText();
            diathesimaCntrl.getSelectedFilter().setText("show only "+epilogh);
            try {
                diathesimaCntrl.filterBy(epilogh);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        //insert delete fsr
        diathesimaCntrl.getInsertFSR().setOnAction(e ->{
            insertFSR(diathesimaCntrl);
        });



        // FOR_SALE_RENT EVENT HANDLING - end



        //handling otan pathsei delete filters
        diathesimaCntrl.getDeleteFilters().setOnAction(e ->{
            try {
                diathesimaCntrl.filterBy("");
                diathesimaCntrl.getSelectedFilter().setText("");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });

        // OWNERS FXML EVENT HANDLING - start

        //event handling gia eisagwgh/diagrafh stous owenrs
        ownersCntrl.getInsertButton().setOnAction(e ->{
            insertOwner(ownersCntrl);
        });

        ownersCntrl.getDeleteButton().setOnAction(e ->{
            deleteOwner(ownersCntrl);
        });
        // OWNERS EVENT HANDLING - end





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

    public static void insertRealtie(RealtiesController realtiesCntrl){

        String [] pedia =   {realtiesCntrl.getNewRealtieId().getText()
                ,realtiesCntrl.getNewRealtieAddress().getText()
                ,realtiesCntrl.getNewRealtieRtype().getText()
                };
        String m2str = realtiesCntrl.getNewRealtieM2().getText();
        float m2_real = 0;
        try {
             m2_real = Float.parseFloat(m2str);
        }catch (NumberFormatException ex){
            realtiesCntrl.getInsertMessage().setText("ΠΑΡΑΚΑΛΩ ΒΑΛΤΕ ΑΡΙΘΜΟ ΓΙΑ Τ.Μ.");
            realtiesCntrl.getInsertMessage().setStyle("-fx-fill : red");
            ex.printStackTrace();
        }


        try {
            boolean result = realtiesCntrl.insert(pedia,m2_real);
            if (result) {
                realtiesCntrl.getNewRealtieId().setText("");
                realtiesCntrl.getNewRealtieAddress().setText("");
                realtiesCntrl.getNewRealtieM2().setText("");
                realtiesCntrl.getNewRealtieRtype().setText("");

                Text message = realtiesCntrl.getInsertMessage();
                makeTextFade(message);
            }
            else {
                realtiesCntrl.getInsertMessage().setStyle("-fx-fill : red");
                realtiesCntrl.getInsertMessage().setText("Η ΕΓΓΡΑΦΗ ΔΕΝ ΠΡΑΓΜΑΤΟΠΟΙΗΘΗΚΕ ΠΑΡΑΚΑΛΩ ΠΡΟΣΠΑΘΕΙΣΤΕ ΞΑΝΑ!");
                Text message = realtiesCntrl.getInsertMessage();
                makeTextFade(message);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }


    }

    public static void deleteRealtie(RealtiesController realtiesCntrl)  {
        Realties selectedItem = realtiesCntrl.getRealtiesView().getSelectionModel().getSelectedItem();

        try {
            boolean result = realtiesCntrl.delete(selectedItem);
            if (result) {
                Text message = realtiesCntrl.getDeleteError();
                message.setText("Η ΕΓΓΡΑΦΗ ΔΙΑΓΡΑΦΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
                makeTextFade(message);

            }else {
                realtiesCntrl.getDeleteError().setStyle("-fx-fill : red");
                realtiesCntrl.getDeleteError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = realtiesCntrl.getDeleteError();
                makeTextFade(message);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    public static void insertContract(ContractsController contractsCntrl) throws ParseException {
        String customerName = contractsCntrl.getCustomerNameInput().getText();
        String ownerName = contractsCntrl.getOwnerNameInput().getText();
        String realtie_id = contractsCntrl.getRealtie_idNameInput().getText();
        String str_date = contractsCntrl.getDateInput().getText();


        try{
            boolean result = contractsCntrl.insert(customerName,ownerName,realtie_id,str_date);
            if(result){
                contractsCntrl.getCustomerNameInput().setText("");
                contractsCntrl.getOwnerNameInput().setText("");
                contractsCntrl.getRealtie_idNameInput().setText("");
                contractsCntrl.getDateInput().setText("");
                contractsCntrl.getSqlError().setText("ΤΟ ΣΥΜΒΟΛΑΙΟ ΚΑΤΑΧΩΡΗΘΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
                Text message = contractsCntrl.getSqlError();
                makeTextFade(message);
            }else {
                contractsCntrl.getSqlError().setStyle("-fx-fill : red");
                contractsCntrl.getSqlError().setText("ΤΟ ΣΥΜΒΟΛΑΙΟ ΔΕΝ ΚΑΤΑΧΩΡΗΘΗΚΕ!\n ΚΑΠΟΙΟ ΣΤΟΙΧΕΙΟ ΕΙΝΑΙ ΛΑΘΟΣ");
                Text message =  contractsCntrl.getSqlError();
                makeTextFade(message);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    public static void deleteContract (ContractsController contractsCntrl){
        Contracts selectedItem = contractsCntrl.getContractsView().getSelectionModel().getSelectedItem();

        try{
            boolean result = contractsCntrl.delete(selectedItem);
            if(result){
                Text message = contractsCntrl.getSqlError();
                message.setText("ΤΟ ΣΥΜΒΟΛΑΙΟ ΔΙΑΓΡΑΦΤΗΚΕ ΕΠΟΤΥΧΩ΅!");
                makeTextFade(message);
            }else{
                contractsCntrl.getSqlError().setStyle(" -fx-fill : red ");
                contractsCntrl.getSqlError().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = contractsCntrl.getSqlError();
                makeTextFade(message);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException | ParseException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    public static void insertCustomer (CustomersController customersCntrl){
        String [] pedia = {
          customersCntrl.getIdColumnInput().getText(),
          customersCntrl.getFullNameColumnInput().getText(),
          customersCntrl.getContactColumnInput().getText()
        };
        try{
            boolean result = customersCntrl.insert(pedia);
            if (result) {
                customersCntrl.getContactColumnInput().setText("");
                customersCntrl.getFullNameColumnInput().setText("");
                customersCntrl.getIdColumnInput().setText("");

                customersCntrl.getInsertError().setText("Η ΝΕΑ ΕΓΓΡΑΦΗ ΑΠΟΘΗΚΕΥΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ");
                Text message = customersCntrl.getInsertError();
                makeTextFade(message);
            }else {
                customersCntrl.getInsertError().setStyle("-fx-fill : red");
                customersCntrl.getInsertError().setText("ΠΑΡΑΚΑΛΩ ΕΙΣΑΓΕΤΑΙ ΣΩΣΤΑ ΤΑ ΣΤΟΙΧΕΙΑ");
                Text message = customersCntrl.getInsertError();
                makeTextFade(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void deleteCustomer (CustomersController customersCntrl) throws SQLException, ClassNotFoundException {
        Customers selectedItem = customersCntrl.getCustomersView().getSelectionModel().getSelectedItem();

        try {
            boolean result = customersCntrl.delete(selectedItem);

            if (result) {
                Text message = customersCntrl.getDeleteError();
                message.setText("H ΔΙΑΓΡΑΦΗ ΠΕΛΑΤΗ ΠΡΑΓΜΑΤΟΠΟΙΗΘΗΚΕ!");
                makeTextFade(message);

            } else {
                customersCntrl.getDeleteError().setStyle("-fx-fill : red ");
                customersCntrl.getDeleteError().setText("ΠΑΡΑΚΑΛΩ ΔΙΑΛΕΞΤΕ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = customersCntrl.getDeleteError();
                makeTextFade(message);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public static void insertOwner (OwnersController ownersCntrl){
        String pedia [] = {
          ownersCntrl.getIdColumnInput().getText(),
          ownersCntrl.getNameColumnInput().getText(),
          ownersCntrl.getContactNumberColumnInput().getText()
        };
        int numOfRealties = Integer.parseInt(ownersCntrl.getNumOfRealtiesColumnInput().getText());

        try{
             boolean result = ownersCntrl.insert(pedia,numOfRealties);
             if(result){
                 ownersCntrl.getContactNumberColumnInput().setText("");
                 ownersCntrl.getNameColumnInput().setText("");
                 ownersCntrl.getIdColumnInput().setText("");
                 ownersCntrl.getNumOfRealtiesColumnInput().setText("");

                 ownersCntrl.getInsertError().setText("H ΝΕΑ ΕΓΓΡΑΦΗ ΑΠΟΘΗΚΕΥΤΗΚΕ ΜΕ ΕΠΙΤΥΧΙΑ!");
                 Text message = ownersCntrl.getInsertError();
                 makeTextFade(message);
             }else {
                 ownersCntrl.getInsertError().setStyle("-fx-fill : red");
                 ownersCntrl.getInsertError().setText("ΠΑΡΑΚΑΛΩ ΣΥΜΠΛΗΡΩΣΤΕ ΤΑ ΣΤΟΙΧΕΙΑ ΣΩΣΤΑ");
                 Text message = ownersCntrl.getInsertError();
                 makeTextFade(message);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void deleteOwner (OwnersController ownersCntrl){
        Owners selectedItem = ownersCntrl.getOwnersView().getSelectionModel().getSelectedItem();

        try{
            boolean result = ownersCntrl.delete(selectedItem);
            if(result){
                ownersCntrl.getDeleteError().setText("H ΔΙΑΓΡΑΦΗ ΠΡΑΓΜΑΤΟΠΟΙΗΘΗΚΕ");
                Text message = ownersCntrl.getDeleteError();
                makeTextFade(message);
            }
            else{
                ownersCntrl.getDeleteError().setStyle("-fx-fill : red ");
                ownersCntrl.getDeleteError().setText("ΠΑΡΑΚΑΛΩ ΔΙΑΛΕΞΤΕ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = ownersCntrl.getDeleteError();
                makeTextFade(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void insertFSR (For_sale_rentController diathesimaCntrl){
        String ownersName = diathesimaCntrl.getOwnerNameInput().getText();
        String realtie_id = diathesimaCntrl.getRealtie_idInput().getText();
        String contractType = diathesimaCntrl.getContractTypeInput().getText();

        try{
            boolean result = diathesimaCntrl.insert(ownersName,realtie_id,contractType);
            if(result){
                diathesimaCntrl.getSqlMessage().setText("Η ΕΓΓΡΑΦΗ ΠΡΑΓΜΑΤΟΠΟΙΗΘΗΚΕ!");
                Text message = diathesimaCntrl.getSqlMessage();
                makeTextFade(message);
                diathesimaCntrl.getContractTypeInput().setText("");
                diathesimaCntrl.getOwnerNameInput().setText("");
                diathesimaCntrl.getRealtie_idInput().setText("");
            }else {
                diathesimaCntrl.getSqlMessage().setStyle("-fx-fill : red");
                diathesimaCntrl.getSqlMessage().setText("Η ΕΓΓΡΑΦΗ ΔΕΝ ΠΡΑΓΜΑΤΟΠΟΙΗΘΗΚΕ ΒΑΛΤΕ ΣΩΣΤΑ ΤΑ ΣΤΟΙΧΕΙΑ");
                Text message = diathesimaCntrl.getSqlMessage();
                makeTextFade(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void deleteFSR(For_sale_rentController diathesimaCntrl){
        For_sale_rent selctedItem = diathesimaCntrl.getFor_sale_rentView().getSelectionModel().getSelectedItem();

        try {
            boolean result = diathesimaCntrl.delete(selctedItem);
            if(result){
                diathesimaCntrl.getSqlMessage().setText("Η ΔΙΑΓΡΑΦΗ ΠΡΑΓΜΑΤΟΠΟΙΗΘΗΚΕ");
                Text message = diathesimaCntrl.getSqlMessage();
                makeTextFade(message);
            }else {
                diathesimaCntrl.getSqlMessage().setStyle("-fx-fill : red");
                diathesimaCntrl.getSqlMessage().setText("ΕΠΙΛΕΞΤΕ ΜΙΑ ΓΡΑΜΜΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                Text message = diathesimaCntrl.getSqlMessage();
                makeTextFade(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private static FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(4), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }

    private static void  makeTextFade(Text message){
        FadeTransition fader = createFader(message);
        SequentialTransition seqT = new SequentialTransition(
                message,
                fader
        ) ;
        seqT.play();
    }

}
