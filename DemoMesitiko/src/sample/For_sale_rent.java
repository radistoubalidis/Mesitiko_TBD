package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class For_sale_rent {

    private StringProperty ownerName,realtieId,contractType;

    public For_sale_rent(){
        this.ownerName = new SimpleStringProperty();
        this.realtieId = new SimpleStringProperty();
        this.contractType = new SimpleStringProperty();
    }

    public void setOwnerName(String ownerName) {
        this.ownerName.set(ownerName);
    }

    public StringProperty ownerNameProperty() {
        return ownerName;
    }

    public void setRealtieId(String realtieId) {
        this.realtieId.set(realtieId);
    }

    public StringProperty realtieIdProperty() {
        return realtieId;
    }

    public void setContractType(String contractType) {
        this.contractType.set(contractType);
    }

    public StringProperty contractTypeProperty() {
        return contractType;
    }

}
