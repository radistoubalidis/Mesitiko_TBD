package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;


public class ContractsController {

    @FXML
    TableView<Contracts> contractsView;
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
        customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        ownerNameColumn.setCellValueFactory(cellData -> cellData.getValue().ownerNameProperty());
        realtieIdColumn.setCellValueFactory(cellData -> cellData.getValue().realtieIDProperty());
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        ObservableList contractsList = ContractsDAO.getAllRecords();
        populateTable(contractsList);
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
    private Button deleteButton ;

    public Button getDeleteButton() {
        return deleteButton;
    }

    @FXML
    private Text deleteError;

    public Text getDeleteError() {
        return deleteError;
    }
}
