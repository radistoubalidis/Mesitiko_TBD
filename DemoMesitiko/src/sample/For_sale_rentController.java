package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class For_sale_rentController {

    @FXML
    private TableView<For_sale_rent> for_sale_rentView;

    public TableView<For_sale_rent> getFor_sale_rentView() {
        return for_sale_rentView;
    }

    @FXML
    private TableColumn<For_sale_rent,String> ownerNameColumn;
    @FXML
    private TableColumn<For_sale_rent,String> realtieIdColumn ;
    @FXML
    private TableColumn<For_sale_rent,String> contractTypeColumn;

    @FXML
    private TextField realtie_idInput,contractTypeInput,ownerNameInput;

    public TextField getOwnerNameInput() {
        return ownerNameInput;
    }

    public TextField getRealtie_idInput() {
        return realtie_idInput;
    }

    public TextField getContractTypeInput() {
        return contractTypeInput;
    }

    @FXML
    private Button goBackButton,insertFSR,deleteButton;

    public Button getInsertFSR() {
        return insertFSR;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }


    @FXML
    private RadioButton filterByRent;

    public RadioButton getFilterByRent() {
        return filterByRent;
    }

    @FXML
    private RadioButton filterBySale;

    public RadioButton getFilterBySale() {
        return filterBySale;
    }

    @FXML
    private Label selectedFilter;

    public Label getSelectedFilter() {
        return selectedFilter;
    }

    @FXML
    private Text sqlMessage;

    public Text getSqlMessage() {
        return sqlMessage;
    }

    private  ToggleGroup filters = new ToggleGroup() ;

    public ToggleGroup getFilters() {
        return filters;
    }

    @FXML
    private RadioButton deleteFilters;

    public RadioButton getDeleteFilters() {
        return deleteFilters;
    }

    @FXML
    private RadioButton diathesima;

    public RadioButton getDiathesima() {
        return diathesima;
    }

    @FXML
    public void initialize() throws  Exception {
        setFSRViewCellData();
        ObservableList<For_sale_rent> diathesimaList = For_sale_rentDAO.getAllRecords();
        populateTable(diathesimaList);
    }

    private void populateTable(ObservableList diathesimaList) {
        for_sale_rentView.setItems(diathesimaList);
    }


    public  void filterBy(String epilogh)throws SQLException, ClassNotFoundException{
        ObservableList fsrList = For_sale_rentDAO.filterBy(epilogh);
        setFSRViewCellData();
        populateTable(fsrList);
    }


    public void setFSRViewCellData() {
        ownerNameColumn.setCellValueFactory(cellData -> cellData.getValue().ownerNameProperty());
        realtieIdColumn.setCellValueFactory(cellData -> cellData.getValue().realtieIdProperty());
        contractTypeColumn.setCellValueFactory(cellData -> cellData.getValue().contractTypeProperty());
    }


    public boolean insert(String ownerName,String realtie_id,String contractType) throws SQLException, ClassNotFoundException {
        boolean returnState = For_sale_rentDAO.insertFSR(ownerName,realtie_id,contractType);
        setFSRViewCellData();
        ObservableList fsrList = For_sale_rentDAO.getAllRecords();
        populateTable(fsrList);
        return returnState;
    }

    public boolean delete (For_sale_rent selectedItem) throws SQLException, ClassNotFoundException {
        boolean returState = For_sale_rentDAO.deleteFSR(selectedItem);
        setFSRViewCellData();
        ObservableList fsrList = For_sale_rentDAO.getAllRecords();
        populateTable(fsrList);
        return returState;
    }


}
