package Users;

import Container_Class.*;
import Port.Port;
import Trip.Trip;
import Vehicle_Classes.*;
import Container_Class.*;
import FileIO.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Port_Manager extends User{
    private String port;
    public Port_Manager(){

    }



    public Port_Manager (String Eid, String username, String password, String role, String port){
        super(Eid, username, password, role);
        this.port = port;
    }

    public static void addPortManager(String username, String password, Port port) throws IOException {
        String Eid = "";
        Random random = new Random();
        for (int i = 1; i<=10; i++){
            Eid = Eid + random.nextInt(10);
        }
        Port_Manager new_user = new Port_Manager(Eid, username, password, "Port Manager", port.getPid());
        FileIOUtil.InputObjectIntoFile(new_user, "PortManager.json");
    }

    public String toString(){
        try {
            return "username: " + this.getPassword() + "\n" + "port: " + "\n" + "Employee ID: " + this.getEid() + "Port:\n" + Port.queryPortbyID(port) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------------Getter-------------------------------------//
    public String getPort(){
        return this.port;
    }
    //----------------------------------------Setter-------------------------------------//
    public void setPort(String port) {
        this.port = port;
    }

    public static Port_Manager queryManagerbyID(String Eid) throws IOException {
        for (Port_Manager manager: FileIOUtil.ReadManagerFromFile()){
            if (manager.getEid().equals(Eid)){
                return manager;
            }
        }
        System.out.println("EID not found");
        return null;
    }
    public static void filteringManagerbyPort(ArrayList<Port_Manager> filtered_list) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Port id");
        String Pid = scanner.nextLine();
        Port filter_port = Port.queryPortbyID(Pid);
        for (int i =0; i < filtered_list.size(); i++){
            if (!filter_port.equals(filtered_list.get(i).getPort())){
                filtered_list.remove(filtered_list.get(i));
            }
        }
    }

    public Port viewPortInfo() throws IOException {
        return Port.queryPortbyID(port);
    }

    public static Port_Manager login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please enter your Eid (Enter Q to quit)");
            String Eid = scanner.nextLine();
            if (Eid.equalsIgnoreCase("q")){
                return null;
            }
            Port_Manager verification = queryManagerbyID(Eid);
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

    //This method allows Port.Port Manager to view the outgoing trips after/before inputted dates
    public boolean outgoingTrip_query() throws IOException {
        Date after = null;
        Date before = null;
        while (true){
            System.out.println("Choose -1 if you want to filter trip by before, 0 if you do not want to filter, 1 if you want to filter by after, 2 if you want to filter by both, 3 if you want to see trip on a particular date, 4 if you want to quit");
            String selection = Utlity.scanner.nextLine();
            if (selection.equals("-1")){
                System.out.println("Please enter the date before which you would like to filter trips");
                before = Utlity.user_chooseDate();
                if (before == null){
                    return false;
                }
                System.out.println(Utlity.viewTrips(Trip.outgoingTrip_query(Port.queryPortbyID(this.port), after, before)));
                break;
            }
            else if (selection.equals("0")){
                Utlity.viewTrips(Trip.outgoingTrip_query(Port.queryPortbyID(this.port), after, before));
                break;
            }
            else if (selection.equals("1")){
                System.out.println("Please enter the date after which you would like to filter trips?");
                after = Utlity.user_chooseDate();
                if (after == null){
                    return false;
                }
                Utlity.viewTrips(Trip.outgoingTrip_query(Port.queryPortbyID(this.port), after, before));
                break;
            }
            else if (selection.equals("2")){
                System.out.println("Please enter the date after which you would like to filter trips?");
                after = Utlity.user_chooseDate();
                if (after == null){
                    return false;
                }
                System.out.println("Please enter the date before which you would like to filter trips");
                before = Utlity.user_chooseDate();
                if (before == null){
                    return false;
                }
               Utlity.viewTrips(Trip.outgoingTrip_query(Port.queryPortbyID(this.port), after, before));
                break;
            }
            else if (selection.equalsIgnoreCase("3")){
                System.out.println("Please enter the date on which you would like to see outgoing trips");
                Date date = Utlity.user_chooseDate();
                ArrayList<Trip> trips = Trip.getTrip();
                trips.removeIf(trip -> !trip.getDate_of_departure().equals(date));
                System.out.println(Utlity.viewTrips(trips));
                break;
            }
            else if (selection.equals("4")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }

        return true;


        }


    //This method allows Port.Port Manager to see the incoming trip after/before inputted dates.
    public boolean incomingTrip_query() throws IOException {
        Date after = null;
        Date before = null;
        while (true){
            System.out.println("Choose -1 if you want to filter trip by before, 0 if you do not want to filter, 1 if you want to filter by after, 2 if you want to filter by both, 3 if you want to see incoming trips on a chosen date, 4 if you want to quit");
            String selection = Utlity.scanner.nextLine();
            if (selection.equals("-1")){
                System.out.println("Please enter the date before which you would like to filter trips");
                before = Utlity.user_chooseDate();
                if (before == null){
                    return false;
                }
                Utlity.viewTrips(Trip.incomingTrip_query(Port.queryPortbyID(this.port), after, before));
                break;
            }
            else if (selection.equals("0")){
                Utlity.viewTrips(Trip.incomingTrip_query(Port.queryPortbyID(this.port), after, before));
                break;
            }
            else if (selection.equals("1")){
                System.out.println("Please enter the date after which you would like to filter trips?");
                after = Utlity.user_chooseDate();
                if (after == null){
                    return false;
                }
                Utlity.viewTrips(Trip.incomingTrip_query(Port.queryPortbyID(this.port), after, before));
                break;
            }
            else if (selection.equals("2")){
                System.out.println("Please enter the date after which you would like to filter trips?");
                after = Utlity.user_chooseDate();
                if (after == null){
                    return false;
                }
                System.out.println("Please enter the date before which you would like to filter trips");
                before = Utlity.user_chooseDate();
                if (before == null){
                    return false;
                }
                Utlity.viewTrips(Trip.incomingTrip_query(Port.queryPortbyID(this.port), after, before));
                break;

            }
            else if (selection.equals("3")){
                System.out.println("Please enter the date on which you would like to see incoming trips");
                Date date = Utlity.user_chooseDate();
                ArrayList<Trip> trips = Trip.getTrip();
                trips.removeIf(trip -> !trip.getDate_of_arrival().equals(date));
                Utlity.viewTrips(trips);
                break;
            }
            else if (selection.equals("4")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
        return true;
    }

    //this method is called when the port manager wants to register a new trip. It will call the registeringTrip method from the Container_Class.Open_Top.Trip.Trip class in its body
    public boolean RegisteringTrip() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Date date_of_departure = new Date();
        Date date_of_arrival = new Date();
        Port port_of_arrival = new Port();
        ArrayList<Container> containers = new ArrayList<Container>();
        ArrayList<String> container_id = new ArrayList<String>();
        Vehicle selected_vehicle = null;
        boolean tanker_constraint = false;
        boolean reefer_constrain = false;
        while (true){
            System.out.println("Please input date of departure");
            date_of_departure = Utlity.user_chooseDate();
            if (date_of_departure == null){
                return false;
            }
            date_of_arrival = Utlity.user_chooseDate();
            if (date_of_arrival == null){
                return false;
            }
            if (date_of_departure.before(date_of_arrival)){
                break;
            }
            else {
                System.out.println("Date of departure could not be after date of arrival. Please choose again");
            }

        }
        double total_ContainerWeight = 0;
        while (true){
            total_ContainerWeight = 0;
            containers = new ArrayList<Container>();
            while (true){
                System.out.println("Please select containers to be delivered");
                Container selected_container = Utlity.user_ChooseContainer();
                if (selected_container == null){
                    return false;
                }
                else {
                    total_ContainerWeight = total_ContainerWeight + selected_container.getWeight();
                    containers.add(selected_container);
                    container_id.add(selected_container.getCid());
                    System.out.println("Would you like to enter more containers? (Y-N)");
                    String response = Utlity.scanner.nextLine();
                    if (response.equalsIgnoreCase("n")){
                        break;
                    }
                }
            }
            if (!Utlity.yes_Or_no("Please check if the following containers are correct.\n" + Utlity.container_Table(containers) +"\n" + "Would you like to enter again? (Y-N)" )){
                break;
            }
        }

        for (String cid: container_id){
            if (cid.startsWith("LI")){
                tanker_constraint = true;
            }
            if (cid.startsWith("RE")){
                reefer_constrain = true;
            }
        }

        File file = new File("Trip.json");
        if (file.length() == 0){
            System.out.println("Please enter the Id of the destination port");
            port_of_arrival = Utlity.user_ChoosePort();
            if (port_of_arrival == null){
                return false;
            }
        }
        else {
            while (true){
                port_of_arrival = Utlity.user_choosePortforContainer(total_ContainerWeight, date_of_arrival);
                if (port_of_arrival == null){
                    return false;
                }
                System.out.println("Please check detail: " + port_of_arrival + " Would you like to choose another port (Y-N)");
                String response = Utlity.scanner.nextLine();
                if (response.equalsIgnoreCase("n")){
                    break;
                }
            }

        }
        while (true){
            while (true){
                selected_vehicle = Utlity.user_chooseEligibleVehicle(Port.queryPortbyID(this.port), port_of_arrival, date_of_arrival, date_of_departure, containers);
                if (selected_vehicle == null){
                    return false;
                }
                if (tanker_constraint && (!selected_vehicle.getVid().startsWith("SH") && !selected_vehicle.getVid().startsWith("TT"))){
                    System.out.println("Vehicle-Container constraint violated. Please choose again");
                }
                if (reefer_constrain && (!selected_vehicle.getVid().startsWith("SH") && !selected_vehicle.getVid().startsWith("RT"))){
                    System.out.println("Vehicle-Container constraint violated. Please choose again");
                }

                else {
                   break;
                }

            }

            if (!Utlity.yes_Or_no("Please check if the following vehicle is correct.\n" + selected_vehicle +"\n" + "Would you like to enter again? (Y-N)" )){
                break;
            }
        }
        Trip.registeringTrip(Port.queryPortbyID(this.port), date_of_departure, date_of_arrival, port_of_arrival, container_id, selected_vehicle);
        return true;
    }
    //This method is called at the commencement of each outgoing trip. It will check the vehicle out of the port.
    public boolean commenceTrip() throws IOException {
        Trip trip = Utlity.user_chooseTrip();
        if (trip == null){
            return false;
        }
        trip.completeTrip("Commenced");
        String Vid = trip.getVehicle();
        Vehicle.queryVehiclebyID(Vid).refuel();
        Port port = Port.queryPortbyID(this.port);
        try{
            port.setNumberofVehiclesOnsite(port.getNumberofVehiclesOnsite() - 1);
        }
        catch (Exception e){
            return false;
        }

        FileIOUtil.updatePortFromFile(port);
        if (Vid.startsWith("SH")){
            ship.queryShipbyId(Vid).depart();
        }
        else if (Vid.startsWith("BT")){
            basic_truck.queryBasicTruckbyId(Vid).depart();
        }
        if (Vid.startsWith("RT")){
            reefer_truck.queryReeferTruckbyId(Vid).depart();
        }
        if (Vid.startsWith("TT")){
            tanker_truck.queryTankerTruckbyId(Vid).depart();
        }



        for (String Cid: trip.getTo_be_delivered_containers()){
            if (Cid.startsWith("DS")){
                try {
                    Dry_Storage.queryDryStoragebyID(Cid).leavePort(port);
                }
                catch (Exception e){
                    return false;
                }

            }
            else if (Cid.startsWith("OT")){
                try {
                    Open_Top.queryOpenTopbyID(Cid).leavePort(port);
                }
                catch (Exception e){
                    return false;
                }

            }
            else if (Cid.startsWith("OS")){
                try {
                    Open_Side.queryOpenSidebyID(Cid).leavePort(port);
                }
                catch (Exception e){
                    return false;
                }

            }
            else if (Cid.startsWith("RE")){
                try{
                    Refriderated.queryRefridgeratedbyID(Cid).leavePort(port);
                }
                catch (Exception e){
                    return false;
                }

            }
            else{
                try{
                    Liquid.queryLiquidbyID(Cid).leavePort(port);
                }
                catch (Exception e){
                    return false;
                }

            }

        }
        return true;

    }
    //this method is called after a vehicle reaches its destination port and finishes its trip. It will check the vehicle into the port
    public boolean completeTrip() throws IOException {
        Trip trip = Utlity.user_chooseTrip();
        if (trip == null){
            return false;
        }
        trip.completeTrip("Complete");
        Port main_port = Port.queryPortbyID(this.port);
        try{
            main_port.setNumberofVehiclesOnsite(main_port.getNumberofVehiclesOnsite() + 1);
        }
        catch (Exception e){
            return false;
        }
        FileIOUtil.updatePortFromFile(main_port);
        String Vid = trip.getVehicle();
        Vehicle.queryVehiclebyID(Vid).fuelRemaining(trip);
        if (Vid.startsWith("SH")){
            ship.queryShipbyId(Vid).arrive(main_port);
        }
        else if (Vid.startsWith("BT")){
            basic_truck.queryBasicTruckbyId(Vid).arrive(main_port);
        }
        if (Vid.startsWith("RT")){
            reefer_truck.queryReeferTruckbyId(Vid).arrive(main_port);
        }
        if (Vid.startsWith("TT")){
            tanker_truck.queryTankerTruckbyId(Vid).arrive(main_port);
        }
        for (String Cid: trip.getTo_be_delivered_containers()){
            if (Cid.startsWith("DS")){
                try{
                    Dry_Storage.queryDryStoragebyID(Cid).enterPort(main_port);
                }
                catch (Exception e){
                    return false;
                }

            }
            else if (Cid.startsWith("OT")){
                try{
                    Open_Top.queryOpenTopbyID(Cid).enterPort(main_port);
                }
                catch (Exception e){
                    return false;
                }

            }
            else if (Cid.startsWith("OS")){
                try{
                    Open_Side.queryOpenSidebyID(Cid).enterPort(main_port);
                }
                catch (Exception e){
                    return false;
                }

            }
            else if (Cid.startsWith("RE")){
                try{
                    Open_Side.queryOpenSidebyID(Cid).enterPort(main_port);
                }
                catch (Exception e){
                    return false;
                }
            }
            else{
                try{
                    Liquid.queryLiquidbyID(Cid).enterPort(main_port);
                }
                catch (Exception e){
                    return false;
                }

            }

        }
        return true;

    }
    //this method is called when the port manager wants to load containers off a vehicle
    public boolean loadingContainersonVehicle() throws IOException {
        Trip trip = Utlity.user_chooseTrip();
        if (trip == null){
            return false;
        }
        ArrayList<String> containers_to_be_loaded_on = trip.getTo_be_delivered_containers();
        for (String container_id: containers_to_be_loaded_on){
            if (container_id.startsWith("DS")){
                Dry_Storage container = Dry_Storage.queryDryStoragebyID(container_id);
                try{
                    container.loadedonVehicle(trip.getVehicle());
                }
                catch (Exception e){
                    return false;
                }

            }
            else if (container_id.startsWith("OT")){
                Open_Top container = Open_Top.queryOpenTopbyID(container_id);
                try{
                    container.loadedonVehicle(trip.getVehicle());
                }
                catch (Exception e){
                    return false;
                }


            }
            else if (container_id.startsWith("OS")){
                Open_Side container = Open_Side.queryOpenSidebyID(container_id);
                try{
                    container.loadedonVehicle(trip.getVehicle());
                }
                catch (Exception e){
                    return false;
                }


            }
            else if (container_id.startsWith("RE")){
                Refriderated container = Refriderated.queryRefridgeratedbyID(container_id);
                try{
                    container.loadedonVehicle(trip.getVehicle());
                }
                catch (Exception e){
                    return false;
                }
            }
            else {
                Liquid container = Liquid.queryLiquidbyID(container_id);
                try{
                    container.loadedonVehicle(trip.getVehicle());
                }
                catch (Exception e){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean loadingContaineroffVehicle() throws IOException {
        Trip trip = Utlity.user_chooseTrip();
        if (trip == null){
            return false;
        }
        ArrayList<String> containers_to_be_loaded_on = trip.getTo_be_delivered_containers();
        for (String container_id: containers_to_be_loaded_on){
            if (container_id.startsWith("DS")){
                Dry_Storage container = Dry_Storage.queryDryStoragebyID(container_id);
                try{
                    container.loadedoffVehicle();
                }
                catch (Exception e){
                    return false;
                }

            }
            else if (container_id.startsWith("OT")){
                Open_Top container = Open_Top.queryOpenTopbyID(container_id);
                try{
                    container.loadedoffVehicle();
                }
                catch (Exception e){
                    return false;
                }
            }
            else if (container_id.startsWith("OS")){
                Open_Side container = Open_Side.queryOpenSidebyID(container_id);
                try{
                    container.loadedoffVehicle();
                }
                catch (Exception e){
                    return false;
                }
            }
            else if (container_id.startsWith("RE")){
                Refriderated container = Refriderated.queryRefridgeratedbyID(container_id);
                try{
                    container.loadedoffVehicle();
                }
                catch (Exception e){
                    return false;
                }
            }
            else {
                Liquid container = Liquid.queryLiquidbyID(container_id);
                try{
                    container.loadedoffVehicle();
                }
                catch (Exception e){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean viewVehicleonSite() throws IOException {
        ArrayList<Vehicle> vehicles = Vehicle.getVehicles();
        ArrayList<Vehicle> filtered_vehicle = new ArrayList<Vehicle>();
        for (Vehicle vehicle: vehicles){
            if (vehicle.getCurrent_port() != null && vehicle.getCurrent_port().equals(this.port)){
                filtered_vehicle.add(vehicle);
            }
        }
        if (!Utlity.viewVehicle(filtered_vehicle)){
         return false;
        }
        return true;

    }

    public boolean viewContainersonSite() throws IOException {
        ArrayList<Container> containers = Container.getContainer();
        ArrayList<Container> filtered_container = new ArrayList<Container>();
        for (Container container: containers){
            if (container.getCurrent_port() != null && container.getCurrent_port().equals(this.port)){
                filtered_container.add(container);
            }
        }
        if (!Utlity.viewContainers(filtered_container)){
            return false;
        }
        return true;
    }
    public boolean removeTrip() throws IOException {
        Trip remove_trip = Utlity.user_chooseTrip();
        if (remove_trip == null){
            return false;
        }
        FileIOUtil.removeTripFromFile(remove_trip);
        return true;
    }

    public boolean updateTrip() throws IOException {
        Trip update_trip = Utlity.user_chooseTrip();
        if (update_trip == null){
            return false;
        }

        while (true){
            System.out.println("Please choose:\na: To Change Vehicle\nb: To change destination port\nc: To change date of departure\nd: To change date of arrival\ne: To change list of containers\nq: To quit");
            String selection = Utlity.scanner.nextLine();
            if (selection.equalsIgnoreCase("a")){
                ArrayList<String> container_id = update_trip.getTo_be_delivered_containers();
                ArrayList<Container> containers = new ArrayList<Container>();
                Vehicle selected_vehicle = new Vehicle();
                boolean tanker_constraint = false;
                boolean reefer_constrain = false;
                for (String cid: container_id){
                    containers.add(Container.queryContainerbyID(cid));
                    if (cid.startsWith("LI")){
                        tanker_constraint = true;
                    }
                    if (cid.startsWith("RE")){
                        reefer_constrain = true;
                    }
                }

                while (true){
                    while (true){
                        selected_vehicle = Utlity.user_chooseEligibleVehicle(Port.queryPortbyID(update_trip.getD_port()), Port.queryPortbyID(update_trip.getA_port()), update_trip.getDate_of_arrival(), update_trip.getDate_of_departure(), containers );
                        if (tanker_constraint && (selected_vehicle.getVid().startsWith("SH") || selected_vehicle.getVid().startsWith("TT"))){
                            break;
                        }
                        if (reefer_constrain && (selected_vehicle.getVid().startsWith("SH") || selected_vehicle.getVid().startsWith("RT"))){
                            break;
                        }
                        else {
                            System.out.println("Vehicle-Container constraint violated. Please choose again");
                        }

                    }

                    if (!Utlity.yes_Or_no("Please check if the following vehicle is correct.\n" + selected_vehicle +"\n" + "Would you like to enter again? (Y-N)" )){
                        break;
                    }
                }
            }

            else if (selection.equalsIgnoreCase("b")){
                Port destination_port = Utlity.user_ChoosePort();
                if (destination_port == null){
                    return false;
                }
                update_trip.setA_port(destination_port.getPid());

            }

            else if (selection.equalsIgnoreCase("c")){
                Date date_of_departure = Utlity.user_chooseDate();
                if (date_of_departure == null){
                    return false;
                }
                update_trip.setDate_of_departure(date_of_departure);
            }

            else if (selection.equalsIgnoreCase("d")){
                Date date_of_arrival = new Date();
                while (true){
                    date_of_arrival = Utlity.user_chooseDate();
                    if (date_of_arrival == null){
                        return false;
                    }
                    if (date_of_arrival.before(update_trip.getDate_of_departure())){
                        System.out.println("Date of arrival could not be before date of departure");
                    }
                    else {
                        break;
                    }
                }
                update_trip.setDate_of_arrival(date_of_arrival);

            }

            else if (selection.equalsIgnoreCase("e")){
                ArrayList<String>container_id = new ArrayList<>();
                while (true){
                    ArrayList<Container> containers = new ArrayList<Container>();
                    while (true){
                        System.out.println("Please select containers to be delivered");
                        Container selected_container = Utlity.user_ChooseContainer();
                        if (selected_container == null){
                            return false;
                        }
                        else {
                            containers.add(selected_container);
                            container_id.add(selected_container.getCid());
                            break;
                        }
                    }
                    if (!Utlity.yes_Or_no("Please check if the following containers are correct.\n" + Utlity.container_Table(containers) +"\n" + "Would you like to enter again? (Y-N)" )){
                        break;
                    }
                }
            }
            else if (selection.equalsIgnoreCase("q")){
                break;
            }
            else{
                System.out.println("Option does not exist. Please choose again");
            }
        }
        FileIOUtil.updateTripFromFile(update_trip);
        return true;
    }
}
