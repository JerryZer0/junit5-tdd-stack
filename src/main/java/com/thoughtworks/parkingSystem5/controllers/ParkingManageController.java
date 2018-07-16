package com.thoughtworks.parkingSystem5.controllers;

import com.thoughtworks.parkingSystem5.domain.ParkingLot;
import com.thoughtworks.parkingSystem5.domain.Request;
import com.thoughtworks.parkingSystem5.domain.Response;
import com.thoughtworks.parkingSystem5.domain.ParkingBoy;

import java.util.ArrayList;
import java.util.List;

public class ParkingManageController implements BaseController {

    Response response;
    Request request;
    List<ParkingBoy> parkingBoyList = new ArrayList<>();

    public static final String MAIN_PAGE = "mainPage";
    public static final String OPERATION_PAGE = "operationPage";
    public static final String PARK_PAGE = "parkPage";
    public static final String UNPARK_PAGE = "unparkPage";

    public String reason[] = {"此停车场不存在！", "此停车场中，依然停有汽车，无法删除！"};

    public ParkingManageController(List<ParkingBoy> parkingBoyList, Request request, Response response) {
        this.parkingBoyList = parkingBoyList;
        this.request = request;
        this.response = response;
    }

    public void getBaseInfo() {
        String baseInfo = parkingBoyList.get(0).lotInfo();
        response.send(baseInfo);
    }

    public void addParkingLot() {
        response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");
    }

    public void addParkingLotSuccessfully() {
        response.send("停车场添加成功！");
    }

    public void removeParkingLot() {
        response.send("请输入需要删除的被管理停车场ID:");
    }

    public void removeParkingLotSuccessfully() {
        response.send("停车场删除成功！");
    }

    public void removeParkingLotfailed() {
        response.send(reason[0]);
    }

    public void getMainPage() {
        response.send("1.查看停车场详情\n2.添加停车场\n3.删除停车场");
    }

    public void getRootMainPage() {
        response.send("1.停车服务\n2.停车场管理\n请输入您要进入的页面：");
    }

    public void wrongOperation() {
        response.send("非法指令，请查证后再输");
        getRootMainPage();
    }

    public void shouLotInfo() {
        ParkingBoy parkingBoy = parkingBoyList.get(0);
        response.send(parkingBoy.lotInfo());
        getRootMainPage();
    }

    public void addParkingLotOperation(Request request) {
        boolean key = true;
        String str = request.getCommand();
        if(!str.substring(0,1).equals("（"))
            key = false;
        if(!str.substring(str.length() - 1,str.length()).equals("）"))
            key = false;
        String info = str.substring(1, str.length() - 1);
        String []baseInfo = info.split("，");
        int count = -1;
        try {
            count = Integer.parseInt(baseInfo[1]);
        }catch (Exception e){
            key = false;
        }
        if(key && count > 0){
            ParkingLot parkingLot = new ParkingLot(baseInfo[0],count);
            parkingBoyList.get(0).addParkingLot(parkingLot);
            response.send("停车场添加成功！");
        }else{
            response.send("输入有误");
        }
        getRootMainPage();
    }

    public void removeParkingLotOperation(Request request) {
        String lotId = request.getCommand();
        int flag = parkingBoyList.get(0).removeLot(lotId);
        if(flag == 2){
            response.send("停车场删除成功！");
        }else{
            response.send("停车场删除失败，原因："+reason[flag]);
        }
        getRootMainPage();
    }
}
