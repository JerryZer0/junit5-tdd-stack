package com.thoughtworks.parkingSystem3;

import java.util.Scanner;

public class ParkingSystemIO {

    public void systemStarShow() {
        System.out.println("1.停车");
        System.out.println("2.取车");
        System.out.println("请输入您要进行的操作：");
    }

    public String getOrder() {
        Scanner in = new Scanner(System.in);
        String orderId = in.nextLine();
        return orderId;
    }

    public void remindErrorOrder() {
        System.out.println("非法指令，请查证后再输");
    }

    public void parkingLotIsNotFull() {
        System.out.println("请输入车牌号：");
        Scanner in = new Scanner(System.in);
        String carId = in.nextLine();
    }

    public void parkSuccessfully(String receiptId) {
        System.out.println("停车成功，您的小票是：");
        System.out.println(receiptId);
    }

    public void parkingLotIsFull() {
        System.out.println("车已停满，请晚点再来");
    }

    public void getOutCarFailed() {
        System.out.println("非法小票，无法取出车，请查证后再输");
    }

    public void getOutCarSuccessfully(String carId) {
        System.out.println("车已取出，您的车牌号是：" + carId);
    }
}
