package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;


public class OwnersController {

    @FXML
    private TableView<Owners> ownersView ;
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
        idColumn.setCellValueFactory(cellData-> cellData.getValue().idProperty());
        fullNameColumn.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        numOfRealtiesColumn.setCellValueFactory(cellData -> cellData.getValue().numOfRealtiesProperty().asObject());
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
