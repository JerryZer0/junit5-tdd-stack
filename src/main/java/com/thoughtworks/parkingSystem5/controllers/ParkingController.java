package com.thoughtworks.parkingSystem5.controllers;

import com.thoughtworks.parkingSystem5.*;
import com.thoughtworks.parkingSystem5.domain.Car;
import com.thoughtworks.parkingSystem5.domain.ParkingBoy;
import com.thoughtworks.parkingSystem5.domain.Receipt;

import java.util.ArrayList;
import java.util.List;

public class ParkingController implements BaseController{

    Response response;
    Request request;
    List<ParkingBoy> parkingBoyList = new ArrayList<>();

    public static final String MAIN_PAGE = "mainPage";
    public static final String OPERATION_PAGE = "operationPage";
    public static final String PARK_PAGE = "parkPage";
    public static final String UNPARK_PAGE = "unparkPage";

    public String reason[] = {"此停车场不存在！", "此停车场中，依然停有汽车，无法删除！"};


    ParkingController(List<ParkingBoy> parkingBoyList, Request request, Response response) {
        this.parkingBoyList = parkingBoyList;
        this.request = request;
        this.response = response;
    }

    public Receipt park(Car car) {
        Receipt receipt = null;
        for (int i = 0; i < parkingBoyList.size(); i++) {
            receipt = parkingBoyList.get(i).park(car);
            if (receipt != null) {
                break;
            }
        }
        return receipt;
    }

    public boolean isFull() {
        boolean full = true;
        for (int i = 0; i < parkingBoyList.size(); i++) {
            if (!parkingBoyList.get(i).isFull()) {
                full = false;
            }
        }
        return full;
    }

    public Car getOutCar(Receipt receipt) {
        Car car = null;
        for (int i = 0; i < parkingBoyList.size(); i++) {
            car = parkingBoyList.get(i).getOutCar(receipt);
            if (car != null) {
                break;
            }
        }
        return car;
    }

    public String unparkOperation() {
        response.send("请输入小票编号：");
        return UNPARK_PAGE;
    }

    public void wrongOperation() {
        response.send("非法指令，请查证后再输");
        getMainPage();
    }

    public String parkOperation() {
        String currentPage;
        if (isFull()) {
            response.send("车已停满，请晚点再来");
            getMainPage();
            currentPage = OPERATION_PAGE;
        } else {
            response.send("请输入车牌号：");
            currentPage = PARK_PAGE;
        }
        return currentPage;
    }

    public void parkCar(Request request) {
        Car car = new Car(request.getCommand());
        Receipt receipt = park(car);
        String msg = "停车成功，您的小票是：" + receipt.getReceiptId();
        response.send(msg);
        getMainPage();
    }

    public void unParkCar() {
        Receipt receipt = new Receipt();
        receipt.setUuid(request.getCommand());
        Car car = getOutCar(receipt);
        String message;
        if (car == null) {
            message = "非法小票，无法取出车，请查证后再输";
        } else {
            message = "车已取出，您的车牌号是：" + car.getCarid();
        }
        response.send(message);
        getMainPage();
    }

    public void getMainPage() {
        response.send("1.停车\n2.取车\n请输入您要进行的操作：");
    }

    public void getRootMainPage() {
        response.send("1.停车服务\n2.停车场管理\n请输入您要进入的页面：");
    }

    public void getLotManagement() {
        response.send("1.查看停车场详情\n2.添加停车场\n3.删除停车场");
    }

    public void getBaseInfo() {
        String baseInfo = parkingBoyList.get(0).lotInfo();
        response.send(baseInfo);
    }

    public void addParkingLot() {
        response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");
    }

    public void addParkingLotSuccessfully() {
        response.send("停车场添加成功！");
    }

    public void removeParkingLot() {
        response.send("请输入需要删除的被管理停车场ID:");
    }

    public void removeParkingLotSuccessfully() {
        response.send("停车场删除成功！");
    }

    public void removeParkingLotfailed() {
        response.send(reason[0]);
    }
}
