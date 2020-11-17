package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class For_sale_rentController {

    @FXML
    private TableView<For_sale_rent> for_sale_rentView;
    @FXML
    private TableColumn<For_sale_rent,String> ownerNameColumn;
    @FXML
    private TableColumn<For_sale_rent,String> realtieIdColumn ;
    @FXML
    private TableColumn<For_sale_rent,String> contractTypeColumn;

    @FXML
    private Button goBackButton;

    public Button getGoBackButton() {
        return goBackButton;
    }

    @FXML
    public void initialize() throws  Exception {
        ownerNameColumn.setCellValueFactory(cellData -> cellData.getValue().ownerNameProperty());
        realtieIdColumn.setCellValueFactory(cellData -> cellData.getValue().realtieIdProperty());
        contractTypeColumn.setCellValueFactory(cellData -> cellData.getValue().contractTypeProperty());
        ObservableList<For_sale_rent> diathesimaList = For_sale_rentDAO.getAllRecords();
        populateTable(diathesimaList);
    }

    private void populateTable(ObservableList diathesimaList) {
        for_sale_rentView.setItems(diathesimaList);
    }

}
