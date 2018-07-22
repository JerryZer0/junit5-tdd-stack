package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.controllers.ParkingController;
import com.thoughtworks.parkingSystem5.controllers.ParkingManageController;
import com.thoughtworks.parkingSystem5.domain.Request;

public class Router {

    private ParkingController parkingController;
    private ParkingManageController manageController;
    private String currentPage;

    public static final String MAIN_PAGE = "mainPage";
    public static final String CHOSE_OPERATION_PAGE = "choseOperationPage";
    public static final String OPERATION_PAGE = "operationPage";
    public static final String PARK_PAGE = "parkPage";
    public static final String UNPARK_PAGE = "unparkPage";

    public static final String MANAGE_LOT_PAGE = "manageLotPage";
    public static final String CHECK_LOT_PAGE = "checkLotPage";
    public static final String ADD_LOT_PAGE = "addLotPage";
    public static final String REMOVE_LOT_PAGE = "removeLotPage";


    Router(ParkingController parkingController, ParkingManageController manageController, String currentPage) {
        this.parkingController = parkingController;
        this.manageController = manageController;
        this.currentPage = currentPage;

    }

    Router(ParkingController controller, String currentPage) {
        this.parkingController = controller;
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

            case CHOSE_OPERATION_PAGE:
                choseOperation(request);
                break;
            case OPERATION_PAGE:
                parkPage(request);
                break;
            case PARK_PAGE:
                parkingController.parkCar(request);
                this.setCurrentPage(CHOSE_OPERATION_PAGE);
                break;
            case UNPARK_PAGE:
                parkingController.unParkCar();
                this.setCurrentPage(CHOSE_OPERATION_PAGE);
                break;


            case MANAGE_LOT_PAGE:
                managePage(request);
                break;
            case CHECK_LOT_PAGE:
                managePage(request);
                break;
            case ADD_LOT_PAGE:
                manageController.addParkingLotOperation(request);
                this.setCurrentPage(CHOSE_OPERATION_PAGE);
                break;
            case REMOVE_LOT_PAGE:
                manageController.removeParkingLotOperation(request);
                this.setCurrentPage(CHOSE_OPERATION_PAGE);
                break;

        }
    }

    public void choseOperation(Request request) {
        switch (request.getCommand()) {
            case "1":
                parkingController.getMainPage();
                this.setCurrentPage(OPERATION_PAGE);
                break;
            case "2":
                manageController.getMainPage();
                this.setCurrentPage(MANAGE_LOT_PAGE);
                break;
            default:
                parkingController.wrongOperation();
                this.setCurrentPage(CHOSE_OPERATION_PAGE);
                break;
        }
    }

    public void parkPage(Request request) {
        switch (request.getCommand()) {
            case "1":
                this.setCurrentPage(parkingController.parkOperation());
                break;
            case "2":
                this.setCurrentPage(parkingController.unparkOperation());
                break;
            default:
                parkingController.wrongOperation();
                this.setCurrentPage(OPERATION_PAGE);
                break;
        }
    }

    public void managePage(Request request) {
        switch (request.getCommand()) {
            case "1":
                manageController.shouLotInfo();
                this.setCurrentPage(CHOSE_OPERATION_PAGE);
                break;
            case "2":
                manageController.addParkingLot();
                this.setCurrentPage(ADD_LOT_PAGE);
                break;
            case "3":
                manageController.removeParkingLot();
                this.setCurrentPage(REMOVE_LOT_PAGE);
                break;
            default:
                manageController.wrongOperation();
                this.setCurrentPage(CHOSE_OPERATION_PAGE);
                break;
        }
    }

}
