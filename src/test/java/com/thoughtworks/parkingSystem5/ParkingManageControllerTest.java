package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.controllers.ParkingManageController;
import com.thoughtworks.parkingSystem5.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingManageControllerTest {

    @Test
    public void should_get_the_base_information_about_the_parkingLot_when_order_is_correct(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);
        String info = "|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" +
                "|001|西南停车场|28(个)|8(辆)|20(个)|\n" +
                "|002|西南停车场|12(个)|8(辆)|4(个)|\n" +
                "\n" +
                "总车位：40(个)\n" +
                "停车总量：16（辆）\n" +
                "总剩余车位：24（个）";
        when(parkingBoy.lotInfo()).thenReturn(info);
        manageController.showLotInfo();
        verify(response).send(info);
    }

    @Test
    public void should_add_the_parkingLot_successful_when_information_is_correct(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);
        String parkingLotInfo = "（北停车场，3）";
        when(request.getCommand()).thenReturn(parkingLotInfo);
        manageController.addParkingLotOperation(request);
        verify(parkingBoy).addParkingLot(any());
        verify(response).send("停车场添加成功！");
    }

    @Test
    //@DisplayName("throws exception")
    public void should_add_the_parkingLot_failed_when_information_is_incorrect(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);
        String parkingLotInfo = "（北停车场，ert）";
        when(request.getCommand()).thenReturn(parkingLotInfo);
        manageController.addParkingLotOperation(request);
        //assertThrows(Exception.class, () -> manageController.addParkingLotOperation(request));
        verify(response).send("输入有误");

    }

    @Test
    public void should_delete_the_parkingLot_successful_when_parkingLot_is_empty_and_lotOd_is_correct(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);
        String lotId = "4234";
        when(request.getCommand()).thenReturn(lotId);
        when(parkingBoy.removeLot(lotId)).thenReturn(2);

        manageController.removeParkingLotOperation(request);
        verify(response).send("停车场删除成功！");
    }

    @Test
    public void should_delete_the_parkingLot_failed_when_parkingLot_is_not_empty_and_lotOd_is_correct(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);
        String lotId = "4234";
        when(request.getCommand()).thenReturn(lotId);
        when(parkingBoy.removeLot(lotId)).thenReturn(1);

        manageController.removeParkingLotOperation(request);
        verify(response).send("停车场删除失败，原因：此停车场中，依然停有汽车，无法删除！");
    }

    @Test
    public void should_delete_the_parkingLot_failed_when_lotOd_is_incorrect(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);
        String lotId = "4234";
        when(request.getCommand()).thenReturn("34444");
        when(parkingBoy.removeLot(lotId)).thenReturn(0);

        manageController.removeParkingLotOperation(request);
        verify(response).send("停车场删除失败，原因：此停车场不存在！");
    }
}
