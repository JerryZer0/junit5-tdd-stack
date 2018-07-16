package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.controllers.ParkingController;
import com.thoughtworks.parkingSystem5.controllers.ParkingManageController;
import com.thoughtworks.parkingSystem5.domain.ParkingBoy;
import com.thoughtworks.parkingSystem5.domain.ParkingLot;
import com.thoughtworks.parkingSystem5.domain.Request;
import com.thoughtworks.parkingSystem5.domain.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(1));

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        Request request = new Request();
        Response response = new Response();
        ParkingController controller = new ParkingController(parkingBoyList, request, response);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);
        Scanner in = new Scanner(System.in);

        response.send("1.停车服务\n2.停车场管理\n请输入您要进入的页面：");
        String currentPage = "choseOperationPage";
        Router router = new Router(controller, manageController, currentPage);

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
}
