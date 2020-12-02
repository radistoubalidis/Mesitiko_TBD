package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

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



}
