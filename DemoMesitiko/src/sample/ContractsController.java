package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.text.ParseException;


public class ContractsController {

    @FXML
    private TableView<Contracts> contractsView;

    public TableView<Contracts> getContractsView() {
        return contractsView;
    }

    @FXML
    TableColumn<Contracts,String> customerNameColumn;
    @FXML
    TableColumn<Contracts,String> ownerNameColumn;
    @FXML
    TableColumn<Contracts,String> realtieIdColumn;
    @FXML
    TableColumn<Contracts,String> startDateColumn;

    @FXML
    public void initialize() throws Exception {
        setContractsViewCellData();
        ObservableList contractsList = ContractsDAO.getAllRecords();
        populateTable(contractsList);
    }

    public void setContractsViewCellData(){
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        ownerNameColumn.setCellValueFactory(cellData -> cellData.getValue().ownerNameProperty());
        realtieIdColumn.setCellValueFactory(cellData -> cellData.getValue().realtieIDProperty());
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
    }

    private void populateTable(ObservableList contractsList) {
        contractsView.setItems(contractsList);
    }

    @FXML
    private Button goBackButton ;

    public Button getGoBackButton() {
        return goBackButton;
    }

    @FXML
    private Button insertContract;

    public Button getInsertContract() {
        return insertContract;
    }

    @FXML
    private Button deleteButton ;

    public Button getDeleteButton() {
        return deleteButton;
    }

    @FXML
    private Text deleteError;

    public Text getDeleteError() {
        return deleteError;
    }

    @FXML
    private TextField customerNameInput;

    public TextField getCustomerNameInput() {
        return customerNameInput;
    }

    @FXML
    private  TextField ownerNameInput;

    public TextField getOwnerNameInput() {
        return ownerNameInput;
    }

    @FXML
    private TextField realtie_idNameInput;

    public TextField getRealtie_idNameInput() {
        return realtie_idNameInput;
    }

    @FXML
    private TextField dateInput;

    public TextField getDateInput() {
        return dateInput;
    }

    @FXML
    private Text sqlError;

    public Text getSqlError() {
        return sqlError;
    }

    public boolean insert(String customerName, String ownerName, String realtie_id, String date) throws SQLException, ClassNotFoundException {
            boolean returnState = ContractsDAO.insertContract(customerName,  ownerName,  realtie_id,  date);
            setContractsViewCellData();
            ObservableList contractsList = ContractsDAO.getAllRecords();
            populateTable(contractsList);
            return returnState;
    }

    public boolean delete(Contracts selectedItem) throws ParseException, SQLException, ClassNotFoundException {
        boolean returnState = ContractsDAO.deleteContract(selectedItem);
        setContractsViewCellData();
        ObservableList contractsList = ContractsDAO.getAllRecords();
        populateTable(contractsList);
        return  returnState;
    }




}
