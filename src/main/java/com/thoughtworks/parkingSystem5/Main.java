package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.controllers.ParkingController;
import com.thoughtworks.parkingSystem5.domain.ParkingBoy;
import com.thoughtworks.parkingSystem5.domain.ParkingLot;

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
        Scanner in = new Scanner(System.in);

        response.send("1.停车\n2.取车\n请输入您要进行的操作：");
        String currentPage = "operationPage";
        Router router = new Router(controller, currentPage);

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
