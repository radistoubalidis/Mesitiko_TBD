package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;


public class OwnersController {

    @FXML
    private TableView<Owners> ownersView ;

    public TableView<Owners> getOwnersView() {
        return ownersView;
    }

    @FXML
    private TableColumn<Owners,String> idColumn ;
    @FXML
    private TableColumn<Owners,String> fullNameColumn ;
    @FXML
    private TableColumn<Owners,String> phoneColumn ;
    @FXML
    private TableColumn<Owners,Integer> numOfRealtiesColumn ;


    @FXML
    private void initialize() throws Exception{
        setOwnersViewCellData();
        ObservableList ownersList = OwnersDAO.getAllRecords();
        populateTable(ownersList);
    }

    private void populateTable(ObservableList<Owners> ownersList){
        ownersView.setItems(ownersList);
    }

    @FXML
    private Button goBackButton;

    public Button getGoBackButton(){return goBackButton;}

    @FXML
    private Button insertButton;

    public Button getInsertButton() {
        return insertButton;
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

    @FXML Text insertError ;

    public Text getInsertError() {
        return insertError;
    }

    @FXML
    private TextField idColumnInput;

    public TextField getIdColumnInput() {
        return idColumnInput;
    }

    @FXML
    private TextField nameColumnInput;

    public TextField getNameColumnInput() {
        return nameColumnInput;
    }

    @FXML
    private TextField contactNumberColumnInput;

    public TextField getContactNumberColumnInput() {
        return contactNumberColumnInput;
    }

    @FXML
    private TextField numOfRealtiesColumnInput;

    public TextField getNumOfRealtiesColumnInput() {
        return numOfRealtiesColumnInput;
    }



    public boolean insert(String [] pedia , int numOfRealties) throws SQLException, ClassNotFoundException {
        boolean returnState =  OwnersDAO.insertOwner(pedia,numOfRealties);
        setOwnersViewCellData();
        ObservableList ownersList = OwnersDAO.getAllRecords();
        populateTable(ownersList);
        return returnState;
    }

    public boolean delete(Owners selectedItem) throws SQLException, ClassNotFoundException {
        boolean returnState = OwnersDAO.deleteOwner(selectedItem);
        setOwnersViewCellData();
        ObservableList ownersList = OwnersDAO.getAllRecords();
        populateTable(ownersList);
        return returnState;
    }

    public void setOwnersViewCellData(){
        idColumn.setCellValueFactory(cellData-> cellData.getValue().idProperty());
        fullNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        numOfRealtiesColumn.setCellValueFactory(cellData -> cellData.getValue().numOfRealtiesProperty().asObject());
    }


}
