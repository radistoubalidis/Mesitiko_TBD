package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contracts {

    private StringProperty customerName,ownerName,realtieID,startDate;

    public Contracts(){
        this.customerName = new SimpleStringProperty();
        this.ownerName = new SimpleStringProperty();
        this.realtieID = new SimpleStringProperty();
        this.startDate = new SimpleStringProperty();
    }




    public StringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public StringProperty ownerNameProperty() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName.set(ownerName);
    }

    public StringProperty realtieIDProperty() {
        return realtieID;
    }

    public void setRealtieID(String realtieID) {
        this.realtieID.set(realtieID);
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }
}
