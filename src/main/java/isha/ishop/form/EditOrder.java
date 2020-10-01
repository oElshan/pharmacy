package isha.ishop.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EditOrder {

    @NotNull
    private Long id;
    @NotEmpty(message = "client name cannot be empty!")
    private String clientName;
    @NotEmpty(message = "client phone cannot be empty!")
    private String clientPhone;
    @NotEmpty(message = "client address cannot be empty!")
    private String clientAddress;
    private String clientEmail;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail){
        this.clientEmail = clientEmail;
    }

    @Override
    public String toString() {
        return "EditOrder{" +
                "clientName='" + clientName + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                '}';
    }
}
