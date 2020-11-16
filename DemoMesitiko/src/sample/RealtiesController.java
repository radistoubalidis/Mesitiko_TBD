package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.SQLException;


public class RealtiesController {

        @FXML
        TableView<Realties> realtiesView ;
        public TableView<Realties> getRealtiesView() { return  realtiesView; }
        @FXML
        private  TableColumn<Realties,String> idColumn;
        @FXML
        private  TableColumn<Realties,String> addressColumn;
        @FXML
        private  TableColumn<Realties,Float> m2Column;
        @FXML
        private  TableColumn<Realties,String> r_typeColumn;

        @FXML
        private TextField newRealtieId, newRealtieAddress,newRealtieM2,newRealtieRtype;

        public TextField getNewRealtieId() {
                return newRealtieId;
        }

        public TextField getNewRealtieAddress(){return newRealtieAddress;}

        public TextField getNewRealtieM2() {return newRealtieM2;}

        public TextField getNewRealtieRtype() {
                return newRealtieRtype;
        }

        @FXML
        private Label insertMessage;
        public Label getInsertMessage() {return insertMessage;}

        @FXML
        private void initialize()throws Exception{
                idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
                addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
                m2Column.setCellValueFactory(cellData -> cellData.getValue().getM2Property().asObject());
                r_typeColumn.setCellValueFactory(cellData -> cellData.getValue().getR_typeProperty());
                ObservableList realtiesList = RealtiesDAO.getAllRecords();
                populateTable(realtiesList);
        }

        private void populateTable(ObservableList<Realties> realtiesList){
                realtiesView.setItems(realtiesList);
        }

        public void getInitilizeMehod() throws Exception {
                initialize();
        }


        public boolean insert(String [] pedia,float m2) throws SQLException, ClassNotFoundException {
                boolean returnState =  RealtiesDAO.insertRealtie(pedia,m2);
                realtiesView.refresh();
                return returnState;
        }


        @FXML
        private Button insertRealtie;

        public Button getInsertRealtie() {
                return insertRealtie;
        }

        @FXML
        private Button goBackButton;

        public Button getGoBackButton(){
                return goBackButton;
        }

        @FXML
        private Button deleteButton;

        public Button getDeleteButton() {
                return deleteButton;
        }

        @FXML
        private Text deleteError;

        public Text getDeleteError() {
                return deleteError;
        }


}






