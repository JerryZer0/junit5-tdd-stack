package com.thoughtworks.parkingSystem5.controllers;

import com.thoughtworks.parkingSystem5.domain.Request;
import com.thoughtworks.parkingSystem5.domain.Response;
import com.thoughtworks.parkingSystem5.domain.ParkingBoy;

import java.util.ArrayList;
import java.util.List;

public class ParkingManageController implements BaseController{

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

    public void getLotManagement() {
        response.send("1.查看停车场详情\n2.添加停车场\n3.删除停车场");
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

    public String getMainPage() {
        return null;
    }
}
