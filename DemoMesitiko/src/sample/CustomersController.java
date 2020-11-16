package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class CustomersController {
    @FXML
    TableView<Customers> customersView ;
    @FXML
    private TableColumn<Customers,String> idColumn;
    @FXML
    private  TableColumn<Customers,String> nameColumn;
    @FXML
    private  TableColumn<Customers,String> phoneColumn;

    @FXML
    public void initialize() throws Exception {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
        ObservableList customersList = CustomersDAO.getAllRecords();
        populateTable(customersList);
    }

    private void populateTable(ObservableList customersList) {
        customersView.setItems(customersList);
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


