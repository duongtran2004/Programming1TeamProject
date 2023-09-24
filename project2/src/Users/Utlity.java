package Users;

import Container_Class.*;
import Port.Port;
import Trip.Trip;
import Vehicle_Classes.*;
import FileIO.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Utlity {

    public static Scanner scanner = new Scanner(System.in);
    //-------------------------------------------------------------Table Generation----------------------------------------------------//

    public static String manager_Table(ArrayList<Port_Manager> manager_list){
        String table = "-".repeat(60) + "\n" + "|" + " ".repeat(6) + "ID" + " ".repeat(6) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3) + "|" + " ".repeat(3) + "USERNAME" + " ".repeat(3) + "|" + " ".repeat(3) + "PASSWORD" + " ".repeat(2)+ "|\n" + "-".repeat(60) + "\n";
        for (Port_Manager manager: manager_list){
            table = table + "|" + " ".repeat(2) + manager.getEid() + " ".repeat(2);
            table = table + "|" + " ".repeat(2) + manager.getPort() + " ".repeat(2);
            if (manager.getUsername().length() % 2 == 0){
                table = table + "|" + " ".repeat((14 - manager.getUsername().length())/2) + manager.getUsername() + " ".repeat((14 - manager.getUsername().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - manager.getUsername().length())/2 + 1) + manager.getUsername() + " ".repeat((14 - manager.getUsername().length())/2);
            }
            if (manager.getUsername().length() % 2 == 0){
                table = table + "|" + " ".repeat((13 - manager.getPassword().length())/2 + 1) + manager.getPassword() + " ".repeat((13 - manager.getPassword().length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((13 - manager.getPassword().length())/2) + manager.getPassword() + " ".repeat((13 - manager.getPassword().length())/2) + "|\n";
            }
            table = table +"-".repeat(60) + "\n";

        }
        return table;
    }

    public static String admin_Table(ArrayList<System_Admin> admin_list){
        String table = "-".repeat(45) + "\n" + "|" + " ".repeat(6) + "ID" + " ".repeat(6) + "|" + " ".repeat(3) + "USERNAME" + " ".repeat(3) + "|" + " ".repeat(3) + "PASSWORD" + " ".repeat(2)+ "|\n" + "-".repeat(45);
        for (System_Admin admin: admin_list){
            table = table + "|" + " ".repeat(2) + admin.getEid() + " ".repeat(2);
            if (admin.getUsername().length() % 2 == 0){
                table = table + "|" + " ".repeat((14 - admin.getUsername().length())/2) + admin.getUsername() + " ".repeat((14 - admin.getUsername().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - admin.getUsername().length())/2 + 1) + admin.getUsername() + " ".repeat((14 - admin.getUsername().length())/2);
            }
            if (admin.getUsername().length() % 2 == 0){
                table = table + "|" + " ".repeat((13 - admin.getPassword().length())/2 + 1) + admin.getPassword() + " ".repeat((13 - admin.getPassword().length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((13 - admin.getPassword().length())/2) + admin.getPassword() + " ".repeat((13 - admin.getPassword().length())/2) + "|\n";
            }
            table = table +"-".repeat(45) + "\n";

        }
        return table;
    }
    public static String port_Table(ArrayList<Port> ports){
        String table = "-".repeat(126) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " "+ "LANDING"+ " "+ "|" +" LATITUDE " + "|" + "LONGITUDE" + "| No Vehicles " + "|No Container" + "| CURRENT CAPACITY " + "| CAPACITY |" + "\n" + "-".repeat(126) + "\n";
        for (Port port: ports){
            table = table + "|" + " ".repeat(2) + port.getPid() + " ".repeat(2);
            if (port.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - port.getName().length())/2) + port.getName() + " ".repeat((20 - port.getName().length())/2);
            }
            else {

                table = table + "|" + " ".repeat((20 - port.getName().length())/2 + 1) + port.getName() + " ".repeat((20 - port.getName().length())/2);
            }
            if (port.isLandingAbility()){
                table = table + "|" + " ".repeat(3) + "true" + " ".repeat((2));
            }
            else {
                table = table + "|" + " ".repeat(2) + "false" + " ".repeat((2));
            }
            if (Double.toString(port.getLatitude()).length() % 2 ==0){
                table = table + "|" + " ".repeat((10 - Double.toString(port.getLatitude()).length())/2) + Double.toString(port.getLatitude()) + " ".repeat((10 - Double.toString(port.getLatitude()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((10 - Double.toString(port.getLatitude()).length())/2 + 1) + Double.toString(port.getLatitude()) + " ".repeat((10 - Double.toString(port.getLatitude()).length())/2);
            }
            if (Double.toString(port.getLongitude()).length() % 2 ==0){
                table = table + "|" + " ".repeat((9 - Double.toString(port.getLongitude()).length())/2 + 1) + Double.toString(port.getLongitude()) + " ".repeat((9 - Double.toString(port.getLongitude()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((9 - Double.toString(port.getLongitude()).length())/2) + Double.toString(port.getLongitude()) + " ".repeat((9 - Double.toString(port.getLongitude()).length())/2);
            }
            if (Integer.toString(port.getNumberofVehiclesOnsite()).length() % 2 == 0){
                table = table + "|" + " ".repeat((13 - Integer.toString(port.getNumberofVehiclesOnsite()).length())/2 + 1) + port.getNumberofVehiclesOnsite() + " ".repeat((13 - Integer.toString(port.getNumberofVehiclesOnsite()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((13 - Integer.toString(port.getNumberofVehiclesOnsite()).length())/2) + port.getNumberofVehiclesOnsite() + " ".repeat((13 - Integer.toString(port.getNumberofVehiclesOnsite()).length())/2);
            }
            if (Integer.toString(port.getNumberofContainersOnsite()).length() % 2 == 0){
                table = table + "|" + " ".repeat((13 - Integer.toString(port.getNumberofContainersOnsite()).length())/2 + 1) + port.getNumberofContainersOnsite() + " ".repeat((13 - Integer.toString(port.getNumberofContainersOnsite()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((13 - Integer.toString(port.getNumberofContainersOnsite()).length())/2) + port.getNumberofContainersOnsite() + " ".repeat( (13- Integer.toString(port.getNumberofContainersOnsite()).length())/2);
            }

            if (Double.toString(port.getCurrentCapacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((18 - Double.toString(port.getCurrentCapacity()).length())/2) + port.getCurrentCapacity() + " ".repeat((18 - Double.toString(port.getCurrentCapacity()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((18 - Double.toString(port.getCurrentCapacity()).length())/2+1) + port.getCurrentCapacity() + " ".repeat((18 - Double.toString(port.getCurrentCapacity()).length())/2);
            }

            if (Double.toString(port.getStoringCapacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((10 - Double.toString(port.getStoringCapacity()).length())/2) + port.getStoringCapacity() + " ".repeat((10 - Double.toString(port.getStoringCapacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((10 - Double.toString(port.getStoringCapacity()).length())/2 + 1) + port.getStoringCapacity() + " ".repeat((10 - Double.toString(port.getStoringCapacity()).length())/2) + "|\n";
            }

            table = table + "-".repeat(126) + "\n";
        }
        return  table;

    }
    public static String vehicle_Table(ArrayList<Vehicle> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (Vehicle vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            if (vehicle.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);
            }

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((12 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }
    public static String ship_Table(ArrayList<ship> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (ship vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            } if (vehicle.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);
            }

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((12 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }
    public static String basic_Table(ArrayList<basic_truck> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (basic_truck vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            if (vehicle.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);
            }

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((12 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }
    public static String reefer_Table(ArrayList<reefer_truck> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (reefer_truck vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            if (vehicle.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);
            }

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((12 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }
    public static String tanker_Table(ArrayList<tanker_truck> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (tanker_truck vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((12 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }



    public static String container_Table(ArrayList<Container> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Container container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            if (container.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);
            }
            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(Container.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                if (container.getCid().startsWith("DS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length())/2) + Dry_Storage.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("OT")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_ship()).length())/2) + Open_Top.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("OS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length())/2) + Open_Side.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("RE")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length())/2) + Refriderated.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else {
                    table = table + "|" + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_ship()).length())/2) + Liquid.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_ship()).length())/2);
                }


            }
            else {
                if (container.getCid().startsWith("DS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Dry_Storage.getFuel_consumption_per_km_on_truck() + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2);
                }
                else if (container.getCid().startsWith("OT")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Open_Top.getFuel_consumption_per_km_on_truck() + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2);
                }
                else if (container.getCid().startsWith("OS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Open_Side.getFuel_consumption_per_km_on_truck() + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2);
                }
                else if (container.getCid().startsWith("RE")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Refriderated.getFuel_consumption_per_km_on_truck() + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2);
                }
                else {
                    table = table + "|" + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2 +1) + Liquid.getFuel_consumption_per_km_on_truck() + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2);
                }
            }

            if (Double.toString(Container.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                if (container.getCid().startsWith("DS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2 ) + Dry_Storage.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";
                }
                else if (container.getCid().startsWith("OT")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2 ) + Open_Top.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else if (container.getCid().startsWith("OS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2 ) + Open_Side.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else if (container.getCid().startsWith("RE")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2 ) + Refriderated.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else {
                    table = table + "|" + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2 ) + Liquid.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
            }
            else {
                if (container.getCid().startsWith("DS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Dry_Storage.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";
                }
                else if (container.getCid().startsWith("OT")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2 + 1 ) + Open_Top.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else if (container.getCid().startsWith("OS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2 + 1 ) + Open_Side.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else if (container.getCid().startsWith("RE")){
                    table = table + "|" + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2 + 1 ) + Refriderated.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else {
                    table = table + "|" + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2 + 1 ) + Liquid.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String DS_Table(ArrayList<Dry_Storage> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Dry_Storage container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            if (container.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);
            }

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(Dry_Storage.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_ship()).length())/2) + Dry_Storage.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_ship()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_ship()).length())/2 + 1) + Dry_Storage.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_ship()).length())/2);

            }
            if (Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2) + Dry_Storage.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Dry_Storage.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Dry_Storage.getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";

            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String OT_Table(ArrayList<Open_Top> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Open_Top container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            if (container.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);
            }

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(Open_Top.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_ship()).length())/2) + Dry_Storage.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_ship()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_ship()).length())/2 + 1) + Open_Top.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_ship()).length())/2);

            }
            if (Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2) + Open_Top.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Open_Top.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";

            }

            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String OS_Table(ArrayList<Open_Side> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Open_Side container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            if (container.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);
            }

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length())/2) + Open_Side.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length())/2 + 1) + Open_Side.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_ship()).length())/2);

            }
            if (Double.toString(Open_Top.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2) + Open_Side.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Open_Side.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Open_Side.getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";

            }

            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String RE_Table(ArrayList<Refriderated> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Refriderated container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            if (container.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);
            }

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(Refriderated.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_ship()).length())/2) + Refriderated.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_ship()).length())/2);
            }

            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_ship()).length())/2+ 1) + Refriderated.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_ship()).length())/2);

            }
            if (Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2) + Refriderated.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Refriderated.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Refriderated.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";

            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String LI_Table(ArrayList<Liquid> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Liquid container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            if (container.getCurrent_port() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else {
                table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);
            }

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(Liquid.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_ship()).length())/2) + Liquid.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_ship()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_ship()).length())/2 + 1) + Liquid.getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_ship()).length())/2);
            }
            if (Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2) + Liquid.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2 + 1) + Liquid.getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(Liquid.getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }

    public static String trip_Table(ArrayList<Trip> trip_list){
        String table = "-".repeat(134) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(3) + "D_PORT ID" + " ".repeat(2) + "|" + " ".repeat(3) + "A_PORT ID" + " ".repeat(2) + "|" + " ".repeat(6) + "DATE OF DEPARTURE" + " ".repeat(6) + "|" + " ".repeat(7) + "DATE OF ARRIVAL" + " ".repeat(6) +"|" + " ".repeat(3) + "STATUS" + " ".repeat(3) + "|\n"+  "-".repeat(134) + "\n";
        for (Trip trip: trip_list){
            table = table + "|" + " " + trip.getTid() + " ";
            table = table + "|" + trip.getVehicle() + " ";
            table = table + "|" + " ".repeat(2) + trip.getD_port() + " ".repeat(2);
            table = table + "|" + " ".repeat(2) + trip.getA_port() + " ".repeat(2);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
            String Dod = dateFormatter.format(trip.getDate_of_departure());
            String Doa = dateFormatter.format(trip.getDate_of_arrival());
            if (Dod.length() % 2 == 0){
                table = table + "|" + " ".repeat(((29 - Dod.length())/2 + 1)) + Dod + " ".repeat((29 - Dod.length())/2);
            }
            else{
                table = table + "|" + " ".repeat(( 29 - Dod.length()/2)) + Dod + " ".repeat((29 - Dod.length())/2);
            }
            if (Doa.length() % 2 == 0){
                table = table + "|" + " ".repeat(((29 - Doa.length())/2 + 1)) + Doa + " ".repeat((29- Doa.length())/2);
            }
            else {
                table = table + "|" + " ".repeat(((29 - Doa.length())/2 + 1)) + Doa + " ".repeat((29 - Doa.length())/2);
            }
            table = table + "|" + " ".repeat((12 - trip.getStatus().length())/2 ) + trip.getStatus() + " ".repeat((12 - trip.getStatus().length())/2) + "|\n" + "-".repeat(134) + "\n";




        }
        return table;
    }




    //Y-N verification
    public static boolean yes_Or_no(String string){
        Scanner scanner = new Scanner(System.in);
        String rep = "";
        while (true){
            System.out.println(string);
            rep = scanner.nextLine();
            if (rep.equalsIgnoreCase("y") || rep.equalsIgnoreCase("n")){
                if (rep.equalsIgnoreCase("y")){
                    return true;
                }
                else {
                    return false;
                }

            }
            else {
                System.out.println("Option does not exist. Please enter again");
            }
        }

    }
    //--------------------------------------------Sorting Ports-----------------------------------------------------//
    public static boolean sortingPort(ArrayList<Port> ports) {

        while (true) {
            System.out.println("Would you like to sort the list Y or N or Q to quit");
            String response = Utlity.scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please choose\na: To sort by Storing Capacity\nb: To sort by onsite vehicles\nc: To sort by onsite containers\nd: To sort by current capacity\nq: to quit ");
                    response = Utlity.scanner.nextLine();
                    if (response.equalsIgnoreCase("a")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Port.sortPortbyStoringCapacity(ports, order);
                        break;
                    }
                    else if (response.equalsIgnoreCase("b")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Port.sortPortbyVehicleOnsites(ports, order);
                        System.out.println(Utlity.port_Table(ports));
                    }
                    else if (response.equalsIgnoreCase("c")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Port.sortPortbyContainerOnsites(ports, order);
                        System.out.println(Utlity.port_Table(ports));
                    }
                    else if (response.equalsIgnoreCase("d")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Port.sortPortbyCurentCapacity(ports, order);
                        System.out.println(Utlity.port_Table(ports));
                    }
                    else if (response.equalsIgnoreCase("q")){
                        return false;
                    }
                    else {
                        System.out.println("Option does not exist. Please choose again.");
                    }
                }
            } else if (response.equalsIgnoreCase("n")) {
                System.out.println(Utlity.port_Table(ports));
                break;

            } else if (response.equalsIgnoreCase("q")) {
                return false;
            } else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
        return true;

    }
    //--------------------------------------------Sorting Vehicle_Classes.Vehicle---------------------------------------------------//
    public static boolean sortingVehicle(ArrayList<Vehicle> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N or Q to quit) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.vehicle_Table(vehicles));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: quit ");
                    selection = scanner.nextLine();
                    if (selection.equals("a")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Vehicle.sortVehicleby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.vehicle_Table(vehicles));
                    }
                    else if (selection.equals("b")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Vehicle.sortVehicleby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.vehicle_Table(vehicles));

                    }
                    else if (selection.equals("c")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Vehicle.sortVehicleby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.vehicle_Table(vehicles));

                    }
                    else if (selection.equalsIgnoreCase("q")){
                        return false;
                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
        return true;

    }

    public static boolean sortingByShip(ArrayList<ship> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N or Q to quit) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.ship_Table(vehicles));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: Quit ");
                    selection = scanner.nextLine();
                    if (selection.equalsIgnoreCase("q")){
                        return false;
                    }
                    if (selection.equalsIgnoreCase("a")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        ship.sortby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.ship_Table(vehicles));


                    }
                    else if (selection.equalsIgnoreCase("b")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        ship.sortby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.ship_Table(vehicles));


                    }
                    else if (selection.equalsIgnoreCase("c")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        ship.sortby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.ship_Table(vehicles));

                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
        return true;
    }

    public static boolean sortingBasicTruck(ArrayList<basic_truck> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N or Q to escape) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.basic_Table(vehicles));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: Quit ");
                    selection = scanner.nextLine();
                    System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                    if (selection.equalsIgnoreCase("q")){
                        return false;
                    }
                    if (selection.equalsIgnoreCase("a")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        basic_truck.sortby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.basic_Table(vehicles));


                    }
                    else if (selection.equalsIgnoreCase("b")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        basic_truck.sortby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.basic_Table(vehicles));


                    }
                    else if (selection.equalsIgnoreCase("c")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        basic_truck.sortby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.basic_Table(vehicles));



                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }

            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
        return true;
    }


    public static boolean sortingReefer(ArrayList<reefer_truck> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N or Q to quit) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.reefer_Table(vehicles));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: Quit ");
                    selection = scanner.nextLine();
                    if (selection.equals("q")){
                        return false;
                    }
                    if (selection.equalsIgnoreCase("a")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        reefer_truck.sortby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.reefer_Table(vehicles));


                    }
                    else if (selection.equalsIgnoreCase("b")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        reefer_truck.sortby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.reefer_Table(vehicles));


                    }
                    else if (selection.equalsIgnoreCase("c")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        reefer_truck.sortby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.reefer_Table(vehicles));

                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
        return true;
    }

    public static boolean sortingTanker(ArrayList<tanker_truck> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N or Q to quit) ");
            String response = Utlity.scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.tanker_Table(vehicles));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: Quit ");
                    selection = scanner.nextLine();
                    if (selection.equalsIgnoreCase("q")){
                        return false;
                    }
                    if (selection.equalsIgnoreCase("a")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        tanker_truck.sortby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.tanker_Table(vehicles));


                    }
                    else if (selection.equalsIgnoreCase("b")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        tanker_truck.sortby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.tanker_Table(vehicles));


                    }
                    else if (selection.equalsIgnoreCase("c")){
                        System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        tanker_truck.sortby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.tanker_Table(vehicles));

                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
        return true;
    }

    //---------------------------------------Sorting Containers------------------------------------------//

    public static boolean sortingContainer(ArrayList<Container> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N or Q to  quit) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.container_Table(containers));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (t: ascending _ f: descending)");
                boolean order = Utlity.userInput_boolean();
                Container.sortContainerByWeight(containers, order);
                System.out.println(Utlity.container_Table(containers));
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
        return true;
    }

    public static boolean sortingDSContainer(ArrayList<Dry_Storage> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N or Q to quit) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.DS_Table(containers));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = Utlity.userInput_boolean();
                Dry_Storage.sortContainerby_Weight(containers, order);
                System.out.println(Utlity.DS_Table(containers));
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
        return true;

    }
    public static boolean sortingOTContainer(ArrayList<Open_Top> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N or Q to quit) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.OT_Table(containers));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = Utlity.userInput_boolean();
                Open_Top.sortContainerby_Weight(containers, order);
                System.out.println(Utlity.OT_Table(containers));
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
        return true;
    }
    public static boolean sortingOSContainer(ArrayList<Open_Side> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N or Q to quit ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.OS_Table(containers));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = Utlity.userInput_boolean();
                Open_Side.sortContainerby_Weight(containers, order);
                System.out.println(Utlity.OS_Table(containers));
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
        return true;
    }
    public static boolean sortingREContainer(ArrayList<Refriderated> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N or Q to quit) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.RE_Table(containers));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = Utlity.userInput_boolean();
                Refriderated.sortContainerby_Weight(containers, order);
                System.out.println(Utlity.RE_Table(containers));
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
        return true;
    }
    public static boolean sortingLIContainer(ArrayList<Liquid> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N or Q to quit) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.LI_Table(containers));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = Utlity.userInput_boolean();
                Liquid.sortContainerby_Weight(containers, order);
                System.out.println(Utlity.LI_Table(containers));
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
        return true;
    }

    //---------------------------------------------------Sorting Trip-------------------------------------------------------//
    public static boolean sortingTrip(ArrayList<Trip> trip_list){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N or Q to quit) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")){
                System.out.println(Utlity.trip_Table(trip_list));
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please choose the property you would like to sort this list by:\na: Date of Departure\nb: Date of Arrival\nq: Quit");
                    String selection = scanner.nextLine();

                    if (selection.equalsIgnoreCase("q")){
                        break;
                    }
                    else if (selection.equalsIgnoreCase("a") ){
                        System.out.println("In which order would you like to sort this list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Trip.sortTripbyDod(trip_list, order);
                        System.out.println(Utlity.trip_Table(trip_list));

                    }
                    else if (selection.equalsIgnoreCase("b")){
                        System.out.println("In which order would you like to sort this list? (true: ascending _ false: descending");
                        boolean order = Utlity.userInput_boolean();
                        Trip.sortTripbyDoa(trip_list, order);
                        System.out.println(Utlity.trip_Table(trip_list));

                    }
                    else {
                        System.out.println("Option does not exist. Please choose again");
                    }
                }
            }
            else if (response.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }

        }
        return true;
    }

    //---------------------------------------------------------------Filter Methods------------------------------------------------------//

    public static boolean viewManager(ArrayList<Port_Manager> managers) throws IOException {
        System.out.println(Utlity.manager_Table(managers));
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Press p if you do want to filter by port, s if you want to search manager by ID, and q to quit");
            String selection = scanner.nextLine();
            scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("p")){
                Port_Manager.filteringManagerbyPort(managers);
                System.out.println(Utlity.manager_Table(managers));
            }
            else if (selection.equalsIgnoreCase("s")){
                System.out.println("Please enter manager ID");
                String Eid = scanner.nextLine();
                System.out.println(Port_Manager.queryManagerbyID(Eid));
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
        return true;


    }

    public static boolean viewAdmin(ArrayList<System_Admin> admins){
        System.out.println(admin_Table(admins));
        return true;
    }

    public static boolean viewVehicle(ArrayList<Vehicle> vehicles) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (!Utlity.sortingVehicle(vehicles)){
            return false;
        }
        while (true){
            vehicles = Vehicle.getVehicles();
            System.out.println("Press t if you want to filter by type, p if you do want to filter by port, s to search for vehicle by ID, q to quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("t")){
                Vehicle.filteringVehiclebyType();
            }
            else if (selection.equalsIgnoreCase("p")){
                Vehicle.filteringVehiclebyPortID(vehicles);

            }
            else if (selection.equalsIgnoreCase("s")){
                System.out.println("Please enter Vehicle ID");
                String Vid = scanner.nextLine();
                System.out.println(Vehicle.queryVehiclebyID(Vid));
            }
            else {
                System.out.println("option is not available. Please choose another option");
            }

        }
        return true;
    }

    public static boolean viewContainers(ArrayList<Container> container_list) throws IOException {
        //Container_Class.Container can be filtered by: type(Check the Cid, each type has a distinct id pattern)
        //Container_Class.Container can be sorted by: weight
        Scanner scanner = new Scanner(System.in);
        if (!Utlity.sortingContainer(container_list)){
            return false;
        }
        while (true){
            container_list = Container.getContainer();
            System.out.println("Press t if you want to filter by type, p if you do want to filter by current port, s if you want to search container by ID, q to quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("t")){
                Container.filteringContainerbyType();
            }
            else if (selection.equalsIgnoreCase("p")){
                Container.filteringContainerbyPortID(container_list);
            }
            else if (selection.equalsIgnoreCase("s")){
                System.out.println("Please enter container ID");
                String Cid = scanner.nextLine();
                System.out.println(Container.queryContainerbyID(Cid));
            }
            else {
                System.out.println("option is not available. Please choose another option");
            }

        }
        return true;
    }

    public static boolean viewPort(ArrayList<Port> port_list) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (!Utlity.sortingPort(port_list)){
            return false;
        }
        while (true){
            System.out.println("Choose ft if you want to filter ports by their landing ability, s if you want to search Port by ID, q if you want to quit");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("q")){
                break;
            }
            else if (response.equalsIgnoreCase("ft")){
                Port.filteringPortsbyLandingAbility(port_list);
            }
            else if (response.equalsIgnoreCase("s")){
                System.out.println("Please enter Port ID");
                String Pid = scanner.nextLine();
                System.out.println(Port.queryPortbyID(Pid));
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }

        }

        return true;
    }

    public static boolean viewTrips(ArrayList<Trip> filtered_list) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (!Utlity.sortingTrip(filtered_list)){
            return false;
        }
        while (true){
            filtered_list = FileIOUtil.ReadTripFromFile();
            System.out.println("Press f if you want to filter, n if you do not want to filter, and q to quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("n")){
                break;
            }
            else if (selection.equalsIgnoreCase("f")){
                while (true){
                    System.out.println("What would you like to filter by:\nv: Vehicle\npa: Port of Arrival\npd: Port of Departure\ns: ID\nst: status\nq: Quit");
                    selection = scanner.nextLine();
                    if (selection.equalsIgnoreCase("q")){
                        break;
                    }
                    else if (selection.equalsIgnoreCase("v")){
                        boolean conti = Trip.filteringTripsbyVehicle(filtered_list);
                        if (!conti){
                            return false;
                        }

                    }
                    else if (selection.equalsIgnoreCase("pa")) {
                        boolean conti = Trip.filteringTripbyArrivalPort(filtered_list);
                        if (!conti){
                            return false;
                        }

                    }
                    else if (selection.equalsIgnoreCase("pd")){
                        boolean conti = Trip.filteringTripbyDeparturePort(filtered_list);
                        if (!conti){
                            return false;
                        }
                    }
                    else if (selection.equalsIgnoreCase("s")){
                        System.out.println("Please enter trip ID");
                        String Tid = scanner.nextLine();
                        System.out.println(Trip.queryByName(Tid));
                    }
                    else if (selection.equalsIgnoreCase("st")){
                        Trip.filteringTripbyStatus(filtered_list);
                    }
                    else {
                        System.out.println("Option does not exist. Please enter again");
                    }
                }
            }
            else if (selection.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. ");
            }
            
        }

        return true;

    }

    public static String userInput_ObjectName(){
        String name = "";
        while (true){
            System.out.println("Please input string: (Press q to quit)");
            name = Utlity.scanner.nextLine();
            if (name.equalsIgnoreCase("q")){
                return name;
            }
            if (!Utlity.yes_Or_no("String: " + name + ". " + " Would you like to enter again? (Y or N)" )){
                break;
            }
        }
        return name;
    }

    public static double userInput_DoublePara(){
        double value = 0;
        while (true){
            while (true){
                try{
                    System.out.println("Please enter numeric value (Press -1 to quit)");
                    value = Utlity.scanner.nextDouble();
                    break;
                }
                catch (Exception e){
                    System.out.println("Numerical values only.");
                }
                finally {
                    Utlity.scanner.nextLine();
                }
            }

            if (value == -1){
                return value;
            }
            else {
                value = (double) Math.round(value * 100)/100;
                if (!Utlity.yes_Or_no( "value =  "  + value+ " Would you like to enter again? (Y or N)")){
                    break;
                }
            }

        }
        return value;


    }

    public static int userInput_IntPara(){
        int value = 0;
        while (true){
            while (true){
                try{
                    System.out.println("Please enter numeric value (Press -1 to quit)");
                    value = Utlity.scanner.nextInt();
                    break;
                }
                catch (Exception e){
                    System.out.println("Integers only");
                }
                finally {
                    Utlity.scanner.nextLine();
                }
            }
            if (value == -1){
                break;
            }
            else {

                if (!Utlity.yes_Or_no( "value =  " + value + " Would you like to enter again? (Y or N)")){
                    break;
                }
            }

        }
        return value;
    }

    public static boolean userInput_boolean(){
        boolean value = true;
        while (true){
            try{
                System.out.println("true or false?");
                value = Utlity.scanner.nextBoolean();

                break;
            }
            catch (Exception e){
                System.out.println("Boolean values only");
            }
            finally {
                Utlity.scanner.nextLine();
            }

        }
        return value;


    }

    public static double userInput_Coordinate(String type){
        double coordinate = 0;
        while (true){
            String hemisphere = "";
            double degree = 0;
            double minute = 0;
            double second = 0;
            while (true){
                if (type.equalsIgnoreCase("latitude")){
                    while (true){
                        System.out.println("Please input the port latitude");
                        System.out.println("Please choose hemisphere (N-S)" );
                        hemisphere = scanner.nextLine();

                        if (hemisphere.equalsIgnoreCase("n") || hemisphere.equalsIgnoreCase("s")){
                            break;
                        }
                        else{
                            System.out.println("Incorrect format. Please choose again");
                        }
                    }
                }
                else {
                    while (true){
                        System.out.println("Please input the port latitude");
                        System.out.println("Please choose hemisphere (W-E)" );
                        hemisphere = scanner.nextLine();

                        if (hemisphere.equalsIgnoreCase("w") || hemisphere.equalsIgnoreCase("e")){
                            break;
                        }
                        else{
                            System.out.println("Incorrect format. Please choose again");
                        }
                    }

                }

                while (true){
                    try{
                        System.out.println("Please input degree (maximum 90 for Latitude 180 for longitude)");
                        degree = scanner.nextDouble();
                        System.out.println("Please input minute");
                        minute = scanner.nextDouble();
                        System.out.println("Please input second");
                        second = scanner.nextDouble();

                        break;
                    }
                    catch (Exception e){
                        System.out.println("Numerical values only");

                    }
                    finally {
                        Utlity.scanner.nextLine();
                    }
                }

                double total_degree = (double) Math.round((degree + minute/60 + second/3600)*100)/100;
                if (total_degree <= 180 && type.equals("longitude")){
                    if (hemisphere.equalsIgnoreCase("e")){
                        coordinate = total_degree;
                    }
                    else {
                        coordinate = -total_degree;
                    }
                    break;
                }
                else if (type.equals("latitude") && total_degree <= 90){
                    if (hemisphere.equalsIgnoreCase("n")){
                        coordinate = total_degree;
                    }
                    else {
                        coordinate = -total_degree;
                    }
                    break;
                }
                else {
                    System.out.println("Incorrect latitude. Please enter again");
                }
            }

            if (!Utlity.yes_Or_no("Port Coordinate: " + degree + " degree " + minute + " minutes " + second + " seconds " + hemisphere + ". " +"Would you like to enter again? (Y-N)" )){
                break;
            }
        }
        return coordinate;
    }

    public static Port user_ChoosePort() throws IOException {
        Port port = new Port();
        while (true){
            while (true){
                System.out.println("Please input the ID of the port (Press Q to quit)");
                String Pid = Utlity.scanner.nextLine();
                if (Pid.equalsIgnoreCase("q")){
                    return null;
                }
                port = Port.queryPortbyID(Pid);
                if (port != null){
                    break;
                }
            }
            if (!Utlity.yes_Or_no("Please check detail \n" + port + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }
        return port;
    }

    public static Container user_ChooseContainer() throws IOException {
        Container container = new Container();
        while (true){
            while (true){
                System.out.println("Please input the ID of the container (Press Q to quit)");
                String cid = Utlity.scanner.nextLine();
                if (cid.equalsIgnoreCase("q")){
                    return null;
                }
                container = Container.queryContainerbyID(cid);
                if (container != null){
                    break;
                }
            }
            if (!Utlity.yes_Or_no("Please check detail \n" + container + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }
        return container;

    }

    public static Vehicle user_chooseEligibleVehicle(Port port, Port port_of_arrival, Date date_of_arrival, Date date_of_departure, ArrayList<Container> containers) throws IOException {
        ArrayList<Vehicle> deployable_vehicles = Trip.checking_vehicle_eligibility(port, port_of_arrival, date_of_arrival, date_of_departure, containers);
        if (deployable_vehicles.isEmpty()){
            System.out.println("No deployable vehicle");
            return null;
        }

        String prompt = "Please select from the following vehicles (Press 0 to quit)\n ";
        for (int i =0; i <deployable_vehicles.size(); i++){
            prompt = prompt + (i+1) + ": " + deployable_vehicles.get(i).getName() + "\n";
        }
        System.out.println(Utlity.vehicle_Table(deployable_vehicles));
        System.out.println(prompt);
        String response = scanner.nextLine();
        if (response.equals("0")){
            return null;
        }
        int selection = Integer.parseInt(response);
        return deployable_vehicles.get(selection-1);
    }

    public static Trip user_chooseTrip() throws IOException {
        Trip trip = new Trip();
        while (true){
            while (true){
                System.out.println("Please input the id of the trip (Press Q to quit)");
                String Tid = Utlity.scanner.nextLine();
                if (Tid.equalsIgnoreCase("q")){
                    return null;
                }
                trip = Trip.queryByName(Tid);
                if (trip != null){
                    break;
                }

            }
            if (!Utlity.yes_Or_no("Please check detail \n" + trip + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }
        return trip;
    }

    public static Date user_chooseDate(){
        Date date =new Date();
        while (true){
            System.out.println("Please input the year (Input 0 to quit)");
            while (true){
                try{
                    int year = Utlity.scanner.nextInt();
                    if (year == 0){
                        return null;
                    }
                    System.out.println("Please input month (Input 0 to quit) ");
                    int month = Utlity.scanner.nextInt();
                    if (month == 0){
                        return null;
                    }
                    System.out.println("Please input day (Input 0 to quit)");
                    int day = Utlity.scanner.nextInt();
                    if (day == 0){
                        return null;
                    }
                    date = new Date(year - 1900, month-1, day);
                   break;
                }
                catch (Exception e){
                    System.out.println("Integer only");
                }
                finally {
                    Utlity.scanner.nextLine();
                }
            }
            if (!Utlity.yes_Or_no("Date: " + date + ". " + "Would you like to enter again? (Y-N)" )){
                break;
            }
        }
        return date;

    }

    public static Vehicle user_ChooseVehicle() throws IOException {
        Vehicle vehicle = null;
        while (true){
            while (true){
                System.out.println("Please enter the id of the vehicle you would like to remove (Press Q to quit)");
                String Vid = Utlity.scanner.nextLine();
                if (Vid.equalsIgnoreCase("q")){
                    return null;
                }
                vehicle = Vehicle.queryVehiclebyID(Vid);
                if (vehicle != null){
                    break;
                }
            }
            if (!Utlity.yes_Or_no("Please check detail \n" + vehicle + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }

        return vehicle;
    }

    public static System_Admin user_chooseSysAdmin() throws IOException {
        System_Admin admin = new System_Admin();
        while (true){
            while (true){
                System.out.println("Please enter the id of the admin you would like to remove (Press Q to quit)");
                String Eid = Utlity.scanner.nextLine();
                if (Eid.equalsIgnoreCase("q")){
                    return null;
                }
                admin = System_Admin.queryAdminbyID(Eid);
                if (admin != null){
                    break;
                }
                else {
                    System.out.println("Please enter another id.");
                }
            }
            if (!Utlity.yes_Or_no("Please check detail \n" + admin + " " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }
        return admin;
    }

    public static Port_Manager user_choosePortManager() throws IOException {
        Port_Manager manager= new Port_Manager();
        while (true){
            while (true){
                System.out.println("Please enter the id of the manager you would like to remover (Press Q to quit)");
                String Eid = Utlity.scanner.nextLine();
                if (Eid.equalsIgnoreCase("q")){
                    return null;
                }
                manager = Port_Manager.queryManagerbyID(Eid);
                if (manager != null){
                    break;
                }
                else{
                    System.out.println("Please enter another id");
                }
            }
            if (!Utlity.yes_Or_no("Please check detail \n" + manager + " " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }

        return manager;
    }

    public static Port user_choosePortforContainer(double weight, Date date) throws IOException {
        File file = new File("Trip.json");
        ArrayList<Port> available_port = new ArrayList<Port>();
        if (file.length() == 0){
            for (Port port: Port.getPorts()){
                if (port.getCurrentCapacity() + weight <= port.getStoringCapacity()){
                    available_port.add(port);
                }
            }
        }
        else {
            available_port = Port.checkPortAvailability(weight, date);
        }
        if (available_port.isEmpty()){
            System.out.println("Max capacity reached");
            return null;
        }
        System.out.println(Utlity.port_Table(available_port));
        while (true){
            String prompt = "Please choose from the following port or Press 0 to quit\n";
            for (int i = 0; i< available_port.size(); i++){
                prompt = prompt + (i+1) + ": "+ available_port.get(i).getPid() + "\n";
            }
            System.out.println(prompt);
            String selection = Utlity.scanner.nextLine();
            if (selection.equals("0")){
                return null;
            }
            else if (Integer.parseInt(selection) > available_port.size()){
                System.out.println("Option does not exist. Please choose again");
            }
            else {
                return  available_port.get(Integer.parseInt(selection ) - 1);
            }
        }
    }






}

