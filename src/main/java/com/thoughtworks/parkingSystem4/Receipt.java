package com.thoughtworks.parkingSystem4;

import java.util.UUID;

public class Receipt {
    private int parkingLot;
    private String uuid = UUID.randomUUID().toString();

    Receipt() {};

    Receipt(int numb) {
        parkingLot = numb;
    }

    public String getReceiptId() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
