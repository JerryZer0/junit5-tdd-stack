package com.thoughtworks.parkingSystem4;

public class ParkingController {
    Response response;
    Request request;
    ParkingSystem system;

    public static final String MAIN_PAGE = "mainPage";
    public static final String OPERATION_PAGE = "operationPage";
    public static final String PARK_PAGE = "parkPage";
    public static final String UNPARK_PAGE = "unparkPage";

    ParkingController(ParkingSystem system, Request request, Response response) {
        this.system = system;
        this.request = request;
        this.response = response;
    }

    public Request checkCurrentPage(Request request) {
        this.request = request;
        switch (request.getCurrentPage()) {
//            case "mainPage":
//                getMainPage();
//                break;
            case "operationPage":
                parkPage();
                break;
            case "parkPage":
                parkCar();
                break;
            case "unparkPage":
                unParkCar();
                break;
        }
        return this.request;
    }

    public void parkCar() {
        Car car = new Car(request.getCommand());
        Receipt receipt = system.park(car);
        response.send("停车成功，您的小票是：" + receipt.getReceiptId());
        getMainPage();
    }

    public void unParkCar() {
        Receipt receipt = new Receipt();
        receipt.setUuid(request.getCommand());
        Car car = system.getOutCar(receipt);
        String message;
        if (car == null) {
            message = "车已取出，您的车牌号是：" + car.getCarid();
        } else {

        }
        response.send("车已取出，您的车牌号是：" + car.getCarid());
        getMainPage();
    }

    public void parkPage() {
        switch (request.getCommand()) {
            case "1":
                parkOperation();
                break;
            case "2":
                unparkOperation();
                break;
            default:
                wrongOperation();
                break;
        }
    }

    public void unparkOperation() {
        response.send("请输入小票编号：");
        request.setCurrentPage(UNPARK_PAGE);
    }

    public void wrongOperation() {
        response.send("非法指令，请查证后再输");
        getMainPage();
    }

    public void parkOperation() {
        if (system.isFull()) {
            response.send("车已停满，请晚点再来");
            getMainPage();
        } else {
            response.send("请输入车牌号：");
            request.setCurrentPage(PARK_PAGE);
        }

    }

    public void getMainPage() {
        response.send("1.停车\n2.取车\n请输入您要进行的操作：");
        request.setCurrentPage(OPERATION_PAGE);
    }
}
