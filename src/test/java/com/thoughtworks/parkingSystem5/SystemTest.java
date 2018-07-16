package com.thoughtworks.parkingSystem5;

import com.thoughtworks.parkingSystem5.controllers.ParkingController;
import com.thoughtworks.parkingSystem5.controllers.ParkingManageController;
import com.thoughtworks.parkingSystem5.domain.Request;
import com.thoughtworks.parkingSystem5.domain.Response;
import org.junit.jupiter.api.Test;

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
}
