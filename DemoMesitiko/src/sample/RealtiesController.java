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
                setRealiesViewCellData();
                ObservableList realtiesList = RealtiesDAO.getAllRecords();
                populateTable(realtiesList);
        }

        private void populateTable(ObservableList<Realties> realtiesList){
                realtiesView.setItems(realtiesList);
        }



        public boolean insert(String [] pedia,float m2) throws SQLException, ClassNotFoundException {
                boolean returnState =  RealtiesDAO.insertRealtie(pedia,m2);
                setRealiesViewCellData();
                ObservableList realtiesList = RealtiesDAO.getAllRecords();
                populateTable(realtiesList);
                return returnState;
        }



        public boolean delete (Realties selectedItem) throws SQLException, ClassNotFoundException {
                boolean returnState = RealtiesDAO.deleteRealtie(selectedItem);
                //System.out.println(selectedItem);
                setRealiesViewCellData();
                ObservableList realtiesList = RealtiesDAO.getAllRecords();
                populateTable(realtiesList);
                return returnState;
        }



        public void setRealiesViewCellData() {
                idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
                addressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
                m2Column.setCellValueFactory(cellData -> cellData.getValue().getM2Property().asObject());
                r_typeColumn.setCellValueFactory(cellData -> cellData.getValue().getR_typeProperty());
        }




        public void FilterBy(String pedio) throws SQLException, ClassNotFoundException {
              ObservableList realtiesList = RealtiesDAO.filterBy(pedio);
                setRealiesViewCellData();
                populateTable(realtiesList);

        }



        @FXML
        private RadioButton filterByDiamerisma ;

        public RadioButton getFilterByDiamerismata() {
                return filterByDiamerisma;
        }

        @FXML
        private RadioButton filterByMonokatoikia;

        public RadioButton getFilterByMonokatoikia() {
                return filterByMonokatoikia;
        }

        @FXML
        private RadioButton filterByVilla ;

        public RadioButton getFilterByVilla() {
                return filterByVilla;
        }

        @FXML
        private RadioButton filterByEpaggelmatikosXwros;

        public RadioButton getFilterByEpaggelmatikosXwros() {
                return filterByEpaggelmatikosXwros;
        }

        @FXML
        private RadioButton filterByGh;

        public RadioButton getFilterByGh() {
                return filterByGh;
        }

        private ToggleGroup filters = new ToggleGroup();

        public ToggleGroup getFilters() {
                return filters;
        }

        @FXML
        private Label selectedFilter;

        public Label getFilterSelected() {
                return selectedFilter;
        }

        @FXML
        private RadioButton deleteFilters;

        public RadioButton getDeleteFilters() {
                return deleteFilters;
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






