package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class CustomersController {
    @FXML
    private TableView<Customers> customersView ;

    public TableView<Customers> getCustomersView() {
        return customersView;
    }

    @FXML
    private TableColumn<Customers,String> idColumn;
    @FXML
    private  TableColumn<Customers,String> nameColumn;
    @FXML
    private  TableColumn<Customers,String> phoneColumn;

    @FXML
    public void initialize() throws Exception {
        setCustomersViewCellData();
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
    private TextField idColumnInput;

    public TextField getIdColumnInput() {
        return idColumnInput;
    }

    @FXML
    private TextField fullNameColumnInput;

    public TextField getFullNameColumnInput() {
        return fullNameColumnInput;
    }

    @FXML
    private TextField contactColumnInput;

    public TextField getContactColumnInput() {
        return contactColumnInput;
    }

    @FXML
    private Button insertButton;

    public Button getInsertButton() {
        return insertButton;
    }

    @FXML
    private Text insertError;

    public Text getInsertError() {
        return insertError;
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

    public void setCustomersViewCellData(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
    }



    public boolean insert(String [] pedia) throws SQLException, ClassNotFoundException {
        boolean returnState = CustomersDAO.insertCustomer(pedia);
        setCustomersViewCellData();
        ObservableList customersList = CustomersDAO.getAllRecords();
        populateTable(customersList);
        return returnState;
    }

    public boolean delete(Customers selectedItem) throws SQLException, ClassNotFoundException {
        boolean returnState = CustomersDAO.deleteCustomer(selectedItem);
        setCustomersViewCellData();
        ObservableList customersList = CustomersDAO.getAllRecords();
        populateTable(customersList);
        return  returnState;
    }


}


