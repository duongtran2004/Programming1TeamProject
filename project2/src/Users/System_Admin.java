package Users;

import Container_Class.*;
import Port.Port;
import Trip.Trip;
import Vehicle_Classes.*;
import FileIO.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class System_Admin extends User{
    public System_Admin(){

    }
    public System_Admin(String Eid, String username, String password, String role){
        super(Eid,username, password, role);
    }
    public static System_Admin queryAdminbyID(String Eid) throws IOException {
        for (System_Admin admin: FileIOUtil.ReadAdminFromFile()){
            if (admin.getEid().equals(Eid)){
                return admin;
            }
        }
        System.out.println("EID not found");
        return null;
    }

    public static System_Admin login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please enter your Eid (Enter Q to quit)");
            String Eid = scanner.nextLine();
            if (Eid.equalsIgnoreCase("q")){
                return null;
            }
            System_Admin verification = queryAdminbyID(Eid);
            if (verification!= null) {
                System.out.println("Please enter your username (Enter Q to quit)");
                String username = scanner.nextLine();
                if (username.equalsIgnoreCase("q")){
                    return null;
                }
                System.out.println("Please enter your password (Enter Q to quit)");
                String password = scanner.nextLine();
                if (password.equalsIgnoreCase("q")){
                    return null;
                }
                if (!verification.getPassword().equals(password) || !verification.getUsername().equals(username)){
                    System.out.println("Incorrect Password or Username");
                }
                else{
                    return verification;
                }
            }
        }
    }

    //-----------------------------------------------CREATE METHODS----------------------------------------------------//
    public static boolean addSystemAdmin() throws IOException {
        String username;
        String password;
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Please input your username here: (Press Q to quit)");
            username = scanner.nextLine();
            if (username.equalsIgnoreCase("q")){
                return false;
            }
            if (!Utlity.yes_Or_no("Username: " + username + "." + "Would you like to input again? (Y or N)" )){
                break;
            }
        }

        while (true){
            System.out.println("Please input your password here: (Press Q to quit)");
            password = scanner.nextLine();
            if (password.equalsIgnoreCase("q")){
                return false;
            }
            if (!Utlity.yes_Or_no("Password: " + password + "." + "Would you like to input again? (Y or N)" )){
                break;
            }
        }

        String Eid = "";
        Random random = new Random();
        for (int i = 1; i<=10; i++){
            Eid = Eid + random.nextInt(10);
        }

        System_Admin new_user = new System_Admin(Eid, username, password, "Admin");
        FileIOUtil.InputObjectIntoFile(new_user, "SystemAdmin.json");

        return true;
    }

    public static boolean addManager() throws IOException {
        String username;
        String password;
        Port port = new Port();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter yor username");
        username = Utlity.userInput_ObjectName();
        if (username.equalsIgnoreCase("q")){
            return false;
        }

        System.out.println("Please enter your password");
        password = Utlity.userInput_ObjectName();
        if (password.equalsIgnoreCase("q")){
            return false;
        }

        while (true){
            String prompt = "Please assign the new manager to a port: (Press 0 to quit)";
            for (int i = 0; i < FileIOUtil.ReadPortFromFile().size(); i++){
                prompt = prompt + (i+1) +": "+ (FileIOUtil.ReadPortFromFile().get(i)).getName() + " ";
            }
            System.out.println(prompt);
            int selection = scanner.nextInt();
            scanner.nextLine();
            if (selection == 0){
                return false;
            }
            port = FileIOUtil.ReadPortFromFile().get(selection-1);
            if (!Utlity.yes_Or_no("Please check port detail:\n" + port + "\n" + "Would you like to choose another port? (Y or N)" )){
                break;
            }

        }
        Port_Manager.addPortManager(username, password, port);
        return true;
    }
    public static boolean addPort() throws IOException {
        String name = "";
        double latitude = 0;
        double longitude = 0;
        double storing_capacity = 0;
        boolean landing_ability = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input name");
        name = Utlity.userInput_ObjectName();
        if (name.equalsIgnoreCase("q")){
            return false;
        }
        System.out.println("Please enter Port's latitude");
        latitude = Utlity.userInput_Coordinate("latitude");
        System.out.println("Please enter Port's longitude");
        longitude = Utlity.userInput_Coordinate("longitude");
        System.out.println("Please input storing capacity");
        storing_capacity = Utlity.userInput_DoublePara();
        if (storing_capacity < 0){
            return false;
        }
        while (true){
            System.out.println("Please input its landing ability");
            landing_ability = Utlity.userInput_boolean();
            if (!Utlity.yes_Or_no("Port landing ability: " + landing_ability + ". " + "Would you like to enter again? (Y or N)")){
                break;
            }
        }
        Port.createNewPort(name, latitude, longitude, storing_capacity, landing_ability);

        return true;
    }
    public static boolean addVehicle() throws IOException {
        String name ="";
        String type = "";
        double fuel_capacity = 0;
        double carrying_capacity = 0;
        Port port = new Port();
        Scanner scanner = new Scanner(System.in);
        String vehicle_type = "";
        System.out.println("Please input name for your vehicle");
        name = Utlity.userInput_ObjectName();
        if (name.equalsIgnoreCase("q")){
            return false;
        }

        while (true){
            while (true){
                System.out.println("Choose 1 for ship, 2 for basic truck, 3 for reefer truck, 4 for tanker truck, 0 to quit");
                type = scanner.nextLine();
                if (type.equals("0")){
                    return false;
                }
                else if (type.equals("1")){
                    vehicle_type = "ship";
                    break;
                }
                else if (type.equals("2")){
                    vehicle_type = "basic truck";
                    break;
                }
                else if (type.equals("3")){
                    vehicle_type= "reefer truck";
                    break;
                }
                else if (type.equals("4")) {
                    vehicle_type = "tanker truck";
                    break;
                }
                else {
                    System.out.println("Option does not exist. Please try again.");
                }
            }

            if (!Utlity.yes_Or_no("Vehicle type: " + vehicle_type + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }

        System.out.println("Please input the fuel capacity of the vehicle");
        fuel_capacity = Utlity.userInput_DoublePara();
        if (fuel_capacity < 0){
            return false;
        }

        System.out.println("Please input the carrying capacity of the vehicle");
        carrying_capacity = Utlity.userInput_DoublePara();
        if (carrying_capacity < 0){
            return false;
        }

        while (true){
            port = Utlity.user_ChoosePort();
            if (port == null){
                return false;
            }
            if (!port.isLandingAbility() && !type.equals("1")){
                System.out.println("Trucks cannot be assigned to this port. Please select another port");
            }
            else {
                break;
            }
        }
        Vehicle.createNewVehicle(name, type, fuel_capacity, carrying_capacity, port.getPid());
        return true;

    }
    public static boolean addContainer() throws IOException {
        String type = "";
        String name = "";
        Port port = new Port();
        double weight = 0;
        Scanner scanner = new Scanner(System.in);
        String Pid = "";

        while (true){
            String container_type = "";
            while (true){
                System.out.println("Choose 1 for Dry Storage, 2 for Open Top, 3 for Open Side, 4 for Refrigerated, 5 for Liquid, 0 to quit");
                type = scanner.nextLine();
                if (type.equalsIgnoreCase("0")){
                    return false;
                }
                if (type.equalsIgnoreCase("1")){
                    container_type = "Dry Storage";
                    break;
                }
                else if (type.equalsIgnoreCase("2")){
                    container_type = "Open Top";
                    break;
                }
                else if (type.equalsIgnoreCase("3")){
                    container_type = "Open Side";
                    break;
                }
                else if (type.equalsIgnoreCase("4")) {
                    container_type = "Refrigerated";
                    break;
                }
                else if (type.equalsIgnoreCase("5")) {
                    container_type = "Container_Class.Liquid";
                    break;
                }
                else {
                    System.out.println("Option does not exist. Please try again");
                }

            }
            if (!Utlity.yes_Or_no("Container type: " + container_type + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }
        System.out.println("Please input name");
        name = Utlity.userInput_ObjectName();
        if (name.equalsIgnoreCase("q")){
            return false;
        }


        System.out.println("Please enter container's weight");
        weight = Utlity.userInput_DoublePara();
        if (weight == 0){
            return false;
        }

        while (true){
            port = Utlity.user_choosePortforContainer(weight, new Date());
            if (port == null){
                return false;
            }
            else {
                System.out.println("Please check detail " + port + " Would you like to try again? (Y_N)" );
                String response = Utlity.scanner.nextLine();
                if (response.equalsIgnoreCase("n")){
                    break;
                }
            }
        }
        Container.createNewContainer(type, name, port, weight);
        return true;

    }

    public static double oilConsumtion() throws IOException {
        System.out.println("Please enter the date you want to check oil consumption");
        double oil_consumption = 0;
        Date checkDate = Utlity.user_chooseDate();
        if (checkDate == null){
            return -1;
        }
        for (Trip trip: Trip.getTrip()){
            if (!checkDate.before(trip.getDate_of_departure()) && !checkDate.after(trip.getDate_of_arrival())){
                double dateDiff = (double) (trip.getDate_of_arrival().getTime() - trip.getDate_of_departure().getTime())/(1000*60*60*24);
                double totalOilConsumptionPerKm = 0;
                for (String cid: trip.getTo_be_delivered_containers()){
                    if (trip.getVehicle().startsWith("SH")){
                        if (cid.startsWith("DS")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Dry_Storage.getFuel_consumption_per_km_on_ship();;
                        }
                        else if (cid.startsWith("OT")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Open_Top.getFuel_consumption_per_km_on_ship();
                        }
                        else if (cid.startsWith("OS")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Open_Side.getFuel_consumption_per_km_on_ship();
                        }
                        else if (cid.startsWith("RE")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Refriderated.getFuel_consumption_per_km_on_ship();
                        }
                        else if (cid.startsWith("LI")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Liquid.getFuel_consumption_per_km_on_ship();
                        }
                    }
                    else {
                        if (cid.startsWith("DS")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Dry_Storage.getFuel_consumption_per_km_on_truck();;
                        }
                        else if (cid.startsWith("OT")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Open_Top.getFuel_consumption_per_km_on_truck();
                        }
                        else if (cid.startsWith("OS")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Open_Side.getFuel_consumption_per_km_on_truck();
                        }
                        else if (cid.startsWith("RE")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Refriderated.getFuel_consumption_per_km_on_truck();
                        }
                        else if (cid.startsWith("LI")){
                            totalOilConsumptionPerKm = totalOilConsumptionPerKm + Liquid.getFuel_consumption_per_km_on_truck();
                        }
                    }
                }
                double totalDistance = 0;
                try{
                    totalDistance = Port.queryPortbyID(trip.getD_port()).distanceCalc(Port.queryPortbyID(trip.getA_port()));
                }
                catch (Exception e){
                    return -1;
                }
                oil_consumption = oil_consumption + (totalDistance * totalOilConsumptionPerKm)/dateDiff;
            }
        }
        System.out.println("Oil Consumption on " + checkDate + " is " + oil_consumption );
        return (double) Math.round(oil_consumption *100)/100;
    }
    //--------------------------------------------------READ METHODS---------------------------------------------------//
    //Ask Users.User if they want to filter. If yes, ask by what key they would like to filter.
    //Ask Users.User if they want to sort. If yes, ask what key they would like to sort by and what direction
    //print result
    //the method to read from list for each class has been provided
    public static boolean viewManager() throws IOException {
        //There is no need to filter or sort managers
        ArrayList<Port_Manager> to_be_printed_list = FileIOUtil.ReadManagerFromFile();
        if (!Utlity.viewManager(to_be_printed_list)){
            return false;
        }
        return true;
    }

    public static boolean viewAdmin() throws IOException {
        ArrayList<System_Admin> admins = FileIOUtil.ReadAdminFromFile();
        if (!Utlity.viewAdmin(admins)){
            return false;
        }
        return true;
    }

    public static boolean viewPort() throws IOException {
        ArrayList<Port> port_list = FileIOUtil.ReadPortFromFile();
        if (!Utlity.viewPort(port_list)){
            return false;
        }
        return true;
    }

    public static boolean viewVehicle() throws IOException {
        //Vehicle_Classes.Vehicle can be filtered by: type(Check the Vid, each type has a distinct id pattern)
        //Vehicle_Classes.Vehicle can be sorted by: fuel_capacity, current fuel, carrying capacity
        ArrayList<Vehicle> vehicles = Vehicle.getVehicles();
        if (!Utlity.viewVehicle(vehicles)){
            return false;
        }
        return true;
    }

    public static boolean viewContainers() throws IOException {
        //Container_Class.Container can be filtered by: type(Check the Cid, each type has a distinct id pattern)
        //Container_Class.Container can be sorted by: weight
        ArrayList<Container> container_list = Container.getContainer();
        if (!Utlity.viewContainers(container_list)){
            return false;
        };
        return true;
    }

    public static boolean viewAllTrips() throws IOException {
        ArrayList<Trip> trips = Trip.getTrip();
        if (!Utlity.viewTrips(trips)){
            return false;
        };
        return true;
    }



    //--------------------------------------------------UPDATE METHODS--------------------------------------------------//

    //Ask user the id of the object they would like to update
    // Then ask them which key they would like to update

    public static boolean updateManager() throws IOException {
        Port_Manager manager= new Port_Manager();
        while (true){
            System.out.println("Please enter the id of the manager you would like to remover (Press Q to quit)");
            String Eid = Utlity.scanner.nextLine();
            if (Eid.equalsIgnoreCase("q")){
                return false;
            }
            manager = Port_Manager.queryManagerbyID(Eid);
            if (manager != null){
                break;
            }
            else{
                System.out.println("Please enter another id");
            }
        }
        while (true){
            System.out.println("Please choose:\na: To change name\nb: To change Password\nc: To change port\nq: To quit");
            String selection = Utlity.scanner.nextLine();
            if (selection.equalsIgnoreCase("a")){
                System.out.println("Please input new name");
                String name = Utlity.userInput_ObjectName();
                if (name.equalsIgnoreCase("q")){
                    return false;
                }
                manager.setUsername(name);
            }
            else if (selection.equalsIgnoreCase("b")){
                System.out.println("Please input new password");
                String password = Utlity.userInput_ObjectName();
                if (password.equalsIgnoreCase("q")){
                    return false;
                }
                manager.setPassword(password);
            }
            else if (selection.equalsIgnoreCase("c")){
                Port port = new Port();
                while (true){
                    while (true){
                        System.out.println("Please input the ID of the port you would like to assign this container to (Press Q to quit)");
                        String Pid = Utlity.scanner.nextLine();
                        if (Pid.equalsIgnoreCase("q")){
                            return false;
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
                manager.setPort(port.getPid());
            }
            else if (selection.equalsIgnoreCase("q")){
                break;
            }
            else {
                System.out.println("Option does not exist");
            }
        }
        FileIOUtil.updateManagerFromFile(manager);
        return true;
    }

    public static boolean updateAdmin() throws IOException {
        System_Admin admin = new System_Admin();
        while (true) {
            System.out.println("Please enter the id of the system you would like to remover (Press Q to quit)");
            String Eid = Utlity.scanner.nextLine();
            if (Eid.equalsIgnoreCase("q")) {
                return false;
            }
            admin = System_Admin.queryAdminbyID(Eid);
            if (admin != null) {
                break;
            } else {
                System.out.println("Please enter another id");
            }
        }
        while (true) {
            System.out.println("Please choose:\na: To change name\nb: To change Password\nq: To quit");
            String selection = Utlity.scanner.nextLine();
            if (selection.equalsIgnoreCase("a")) {
                System.out.println("Please input new name");
                String name = Utlity.userInput_ObjectName();
                if (name.equalsIgnoreCase("q")) {
                    return false;
                }
                admin.setUsername(name);
            } else if (selection.equalsIgnoreCase("b")) {
                System.out.println("Please input new password");
                String password = Utlity.userInput_ObjectName();
                if (password.equalsIgnoreCase("q")) {
                    return false;
                }
                admin.setPassword(password);
            } else if (selection.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("Option does not exist. Please choose again");
            }

        }
        FileIOUtil.updateAdminFromFile(admin);
        return true;
    }

    public static boolean updatePort() throws IOException {
        Port port = Utlity.user_ChoosePort();
        if (port == null){
            return false;
        }
        while (true){
            System.out.println("Please choose:\na: To update name\nb: To update latitude\nc: To update longitude\nd: To update storing capability\ne: To update number of vehicle onsite\nf: To update number of containers on site\ng: To update current capacity\nh: To update landing ability\nq: To quit");
            String selection = Utlity.scanner.nextLine();
            if (selection.equalsIgnoreCase("a")){
                System.out.println("Please input name");
                String name = Utlity.userInput_ObjectName();
                if (name.equalsIgnoreCase("q")){
                    return false;
                }
                port.setName(name);
            }

            else if (selection.equalsIgnoreCase("b")){
                System.out.println("Please enter Port's new latitude");
                double latitude = Utlity.userInput_Coordinate("latitude");
                port.setLatitude(latitude);


            }
            else if (selection.equalsIgnoreCase("c")){
                System.out.println("Please enter Port's new longitude");
                double longitude = Utlity.userInput_Coordinate("longitude");
                port.setLongitude(longitude);
            }

            else if (selection.equalsIgnoreCase("d")){
                System.out.println("Please enter Port storing capacity");
                double storing_capacity = Utlity.userInput_DoublePara();
                if (storing_capacity < 0){
                    return false;
                }
                port.setStoringCapacity(storing_capacity);
            }
            else if (selection.equalsIgnoreCase("e")){
                System.out.println("Please enter number of vehicles onsite");
                int numberOnsiteVehicle = Utlity.userInput_IntPara();
                if (numberOnsiteVehicle < 0){
                    return false;
                }
                port.setNumberofVehiclesOnsite(numberOnsiteVehicle);
            }

            else if (selection.equalsIgnoreCase("f")){
                System.out.println("Please enter number of containers on site");
                int numberOnsiteContainer = Utlity.userInput_IntPara();
                if (numberOnsiteContainer < 0 ){
                    return false;
                }

                port.setNumberofVehiclesOnsite(numberOnsiteContainer);
            }
            else if (selection.equalsIgnoreCase("g")){
                System.out.println("Please input current capacity");
                double current_capacity = Utlity.userInput_DoublePara();
                if (current_capacity < 0){
                    return false;
                }
                port.setCurrentCapacity(current_capacity);

            }
            else if (selection.equalsIgnoreCase("h")){
                System.out.println("Please input Port landing ability");
                boolean landing_ability = Utlity.userInput_boolean();
                port.setLandingAbility(landing_ability);
            }
            else if (selection.equalsIgnoreCase("q")){
                break;
            }
        }
        FileIOUtil.updatePortFromFile(port);
        return true;
    }

    public static  boolean updateVehicle() throws IOException {
        Vehicle vehicle = Utlity.user_ChooseVehicle();
        if (vehicle == null){
            return false;
        }
        while (true){
            System.out.println("Please choose:\na: To update name\nb: To update carrying weight\nc: To update fuel capacity\nd:To update current fuel\ne: To update current port\nq: To quit");
            String selection = Utlity.scanner.nextLine();
            if (selection.equalsIgnoreCase("a")){
                System.out.println("Please input name");
                String name = Utlity.userInput_ObjectName();
                if (name.equalsIgnoreCase("q")){
                    return false;
                }
                vehicle.setName(name);
            }
            else if (selection.equalsIgnoreCase("b")){
                System.out.println("Please input maximum capacity");
                double maximum_capacity = Utlity.userInput_DoublePara();
                if (maximum_capacity < 0){
                    return false;
                }
                vehicle.setCarrying_capacity(maximum_capacity);
            }
            else if (selection.equalsIgnoreCase("c")){
                System.out.println("Please input fuel capacity");
                double fuel_capacity = Utlity.userInput_DoublePara();
                if (fuel_capacity < 0){
                    return false;
                }
                vehicle.setFuel_capacity(fuel_capacity);
            }
            else if (selection.equalsIgnoreCase("d")){
                System.out.println("Please input maximum capacity");
                double current_fuel = Utlity.userInput_DoublePara();
                if (current_fuel < 0){
                    return false;
                }
                vehicle.setCurrent_fuel(current_fuel);
            }
            else if (selection.equalsIgnoreCase("e")){
                Port port = new Port();
                while (true){
                    port = Utlity.user_ChoosePort();
                    if (port == null){
                        return false;
                    }
                    if (!port.isLandingAbility() && !vehicle.getVid().startsWith("SH")){
                        System.out.println("Trucks cannot be assigned to this port. Please select another port");
                    }
                    else {
                        break;
                    }
                }

                vehicle.setCurrent_port(port.getPid());
            }
            else if (selection.equalsIgnoreCase("q")){
                break;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
        FileIOUtil.updateVehicleFromFile(vehicle);
        return true;
    }

    public static boolean updateContainer() throws IOException {
        Container update_container = Utlity.user_ChooseContainer();
        if (update_container == null){
            return false;
        }
        while (true){
            System.out.println("Please choose:\na: To update name\nb: To update weight\nc: To update port\nd: To update current vehicle\nq: To quit");
            String selection = Utlity.scanner.nextLine();
            System.out.println("Please input name");
            if (selection.equalsIgnoreCase("a")){
                String name = Utlity.userInput_ObjectName();
                if (name.equalsIgnoreCase("q")){
                    return false;
                }
                update_container.setName(name);

            }
            else if (selection.equalsIgnoreCase("b")){
                System.out.println("Please enter weight here");
                double weight = Utlity.userInput_DoublePara();
                if (weight < 0){
                    return false;
                }
                update_container.setWeight(weight);


            }
            else if (selection.equalsIgnoreCase("c")){
                System.out.println("Please choose the port you would like to assign this container to");
                Port port = Utlity.user_ChoosePort();
                if (port == null){
                    return false;
                }
                update_container.setCurrent_port(port.getPid());
            }
            else if (selection.equalsIgnoreCase("d")){
                Vehicle vehicle = new Vehicle();
                while (true){
                    vehicle = Utlity.user_ChooseVehicle();
                    if (vehicle == null){
                        return false;
                    }
                    if (update_container.getCid().startsWith("RE") && (!vehicle.getVid().startsWith("SH") && !vehicle.getVid().startsWith("RT"))){
                        System.out.println("Vehicle - Containable incompatible. Please choose again");
                    }
                    if (update_container.getCid().startsWith("LI") && (!vehicle.getVid().startsWith("SH")&& !vehicle.getVid().startsWith("TT"))){
                        System.out.println("Vehicle - Containable incompatible. Please choose again");
                    }
                    else {
                        break;
                    }
                }
                update_container.setCurrent_vehicle(vehicle.getVid());
            }
            else if (selection.equalsIgnoreCase("q")){
                break;
            }
            else{
                System.out.println("Option does not exist. Please choose again");
            }
        }
        FileIOUtil.updateContainerFromFile(update_container);
        return true;
    }




    //--------------------------------------------------DELETE METHODS--------------------------------------------------//

    public static boolean removeManager() throws IOException {
        Port_Manager remove_manager= Utlity.user_choosePortManager();
        if (remove_manager == null){
            return false;
        }
        FileIOUtil.removeManagerFromFile(remove_manager);
        return true;
    }
    public static boolean removePort() throws IOException {
        Port remove_port = Utlity.user_ChoosePort();
        if (remove_port == null){
            return false;
        }
        FileIOUtil.removePortFromFile(remove_port);
        return true;
    }

    public static boolean removeVehicle() throws IOException {
       Vehicle remove_vehicle = Utlity.user_ChooseVehicle();
       if (remove_vehicle == null){
           return false;
       }
        FileIOUtil.removeVehicleFromFile(remove_vehicle);
        return true;
    }

    public static boolean removeContainer() throws IOException {
        Container remove_container = Utlity.user_ChooseContainer();
        if (remove_container == null){
            return false;
        }
        FileIOUtil.removeContainerFromFile(remove_container);

        return true;

    }

    public static boolean removeSystem_Admin() throws IOException {
        System_Admin remove_admin = Utlity.user_chooseSysAdmin();
        if (remove_admin == null){
            return false;
        }
        FileIOUtil.removeAdminFromFile(remove_admin);
        return true;

    }


}
