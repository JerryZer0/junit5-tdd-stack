package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.controllers.ParkingController;
import com.thoughtworks.parkingSystem5.controllers.ParkingManageController;
import com.thoughtworks.parkingSystem5.domain.ParkingBoy;
import com.thoughtworks.parkingSystem5.domain.ParkingLot;
import com.thoughtworks.parkingSystem5.domain.Request;
import com.thoughtworks.parkingSystem5.domain.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SystemTest {
    @Test
    public void should_chose_wrongOperation_and_currentPage_is_choseOperationPage_when_input_is_incorrect(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "choseOperationPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("21354");
        router.checkCurrentPage(request);
        verify(controller).wrongOperation();
        assertThat(router.getCurrentPage(),is("choseOperationPage"));
    }

    @Test
    public void should_chose_parkingController_and_currentPage_is_operationPage_when_input_is_1(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "choseOperationPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("1");
        router.checkCurrentPage(request);
        verify(controller).getMainPage();
        assertThat(router.getCurrentPage(),is("operationPage"));
    }

    @Test
    public void should_chose_manageController_and_currentPage_is_manageLotPage_when_input_is_2(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "choseOperationPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("2");
        router.checkCurrentPage(request);
        verify(manageController).getMainPage();
        assertThat(router.getCurrentPage(),is("manageLotPage"));
    }

    @Test
    public void should_chose_wrongOperation_and_currentPage_is_choseOperationPage_when_input_is_incorrect_in_manageController(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "manageLotPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("21354");
        router.checkCurrentPage(request);
        verify(manageController).wrongOperation();
        assertThat(router.getCurrentPage(),is("choseOperationPage"));
    }

    @Test
    public void should_chose_show_info_and_currentPage_is_choseOperationPage_when_in_2_and_input_is_1(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "checkLotPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("1");
        router.checkCurrentPage(request);
        verify(manageController).shouLotInfo();
        assertThat(router.getCurrentPage(),is("choseOperationPage"));
    }

    @Test
    public void should_in_manageController_addParkingLot_and_currentPage_is_addLotPage_when_in_2_and_input_is_2(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "checkLotPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("2");
        router.checkCurrentPage(request);
        verify(manageController).addParkingLot();
        assertThat(router.getCurrentPage(),is("addLotPage"));
    }
    @Test
    public void should_in_manageController_addParkingLotOperation_and_currentPage_is_choseOperationPage_when_information_is_correct(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "addLotPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("addLotPage");
        router.checkCurrentPage(request);
        verify(manageController).addParkingLotOperation(request);

        assertThat(router.getCurrentPage(),is("choseOperationPage"));
    }

    @Test
    public void should_chose_manageController_removeParkingLot_and_currentPage_is_removeLotPage_when_input_is_3_in_2(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "checkLotPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("3");
        router.checkCurrentPage(request);
        verify(manageController).removeParkingLot();
        assertThat(router.getCurrentPage(),is("removeLotPage"));
    }

    @Test
    public void should_chose_manageController_removeParkingLotOperation_and_currentPage_is_choseOperationPage_when_input_is_3_in_2(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingController controller = mock(ParkingController.class);
        ParkingManageController manageController = mock(ParkingManageController.class);
        String currentPage = "removeLotPage";
        Router router = new Router(controller,manageController, currentPage);

        when(request.getCommand()).thenReturn("removeLotPage");
        router.checkCurrentPage(request);
        verify(manageController).removeParkingLotOperation(request);
        assertThat(router.getCurrentPage(),is("choseOperationPage"));
    }

//    @Test
//    public void should_(){
//        String str = "（你傻，8）";
//        boolean key = true;
//        if(!str.substring(0,1).equals("（"))
//            key = false;
//        if(!str.substring(str.length() - 1,str.length()).equals("）"))
//            key = false;
//        String info = str.substring(1, str.length() - 1);
//        String []baseInfo = info.split("，");
//        System.out.println(baseInfo[0]);
//    }

    @Test
    public void should_getNmae(){
        Request request = new Request();
        request.setCommand("（你好，8）");

        Response response = new Response();
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(parkingBoy);
        ParkingController controller = new ParkingController(parkingBoyList, request, response);
        ParkingManageController manageController = new ParkingManageController(parkingBoyList, request, response);

        //Router router = new Router(controller, manageController, "addLotPage");
        manageController.addParkingLotOperation(request);
        //parkingBoy.addParkingLot();
    }
}
