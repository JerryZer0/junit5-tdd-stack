package com.thoughtworks.parkingSystem4;

public class Router {

    private ParkingController controller;
    private String currentPage;

    public static final String MAIN_PAGE = "mainPage";
    public static final String OPERATION_PAGE = "operationPage";
    public static final String PARK_PAGE = "parkPage";
    public static final String UNPARK_PAGE = "unparkPage";

    Router(ParkingController controller, String currentPage) {
        this.controller = controller;
        this.currentPage = currentPage;

    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void checkCurrentPage(Request request) {

        switch (currentPage) {
//            case "mainPage":
//                getRootMainPage();
//                break;
            case OPERATION_PAGE:
                parkPage(request);
                break;
            case PARK_PAGE:
                controller.parkCar(request);
                this.setCurrentPage(OPERATION_PAGE);
                break;
            case UNPARK_PAGE:
                controller.unParkCar();
                this.setCurrentPage(OPERATION_PAGE);
                break;
        }
    }

    public void parkPage(Request request) {
        switch (request.getCommand()) {
            case "1":
                this.setCurrentPage(controller.parkOperation());
                break;
            case "2":
                this.setCurrentPage(controller.unparkOperation());
                break;
            default:
                controller.wrongOperation();
                this.setCurrentPage(OPERATION_PAGE);
                break;
        }
    }
}
