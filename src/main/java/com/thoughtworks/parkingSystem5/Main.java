package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.shell.Router;
import com.thoughtworks.parkingSystem5.shell.controller.ParkingController;
import com.thoughtworks.parkingSystem5.shell.controller.ParkingManageController;
import com.thoughtworks.parkingSystem5.domain.ParkingBoy;
import com.thoughtworks.parkingSystem5.domain.ParkingLot;
import com.thoughtworks.parkingSystem5.shell.io.Request;
import com.thoughtworks.parkingSystem5.shell.io.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        String initRootPath = "main";
        Scanner in = new Scanner(System.in);

        String currentPage = "choseOperationPage";
        Request request = new Request();
        Router router = initRouter(currentPage,request);

        try {
            while (true) {
                String order = in.nextLine();
                request.setCommand(order);
                router.checkCurrentPage(request);
            }
        } catch (Exception e) {
            System.err.println("App end!");
        }
    }

    private static Router initRouter(String currentPage, Request request) {
        ParkingLot parkingLot1 = new ParkingLot("hah", 2);
        ParkingLot parkingLot2 = new ParkingLot("heh", 2);

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot1);
        parkingBoy.addParkingLot(parkingLot2);

        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);

        Response response = new Response();
        ParkingController controller = new ParkingController(parkingBoyList, request, response);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);

        response.send("1.停车服务\n2.停车场管理\n请输入您要进入的页面：");
        Router router = new Router(controller,manageController,currentPage);
        return router;
    }
}
