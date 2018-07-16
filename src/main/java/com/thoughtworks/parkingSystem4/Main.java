package com.thoughtworks.parkingSystem4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ParkingSystemIO io = new ParkingSystemIO();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(1));

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingSystem system = new ParkingSystem(parkingBoyList);
        Scanner in = new Scanner(System.in);

        Request request = new Request();
        Response response = new Response();
        ParkingController controller = new ParkingController(system, request, response);
        response.send("1.停车\n2.取车\n请输入您要进行的操作：");
        request.setCurrentPage("operationPage");
        try {
            while (true) {
                String order = in.nextLine();
                request.setCommand(order);
                request = controller.checkCurrentPage(request);
            }
        } catch (Exception e) {

        }
    }
}
