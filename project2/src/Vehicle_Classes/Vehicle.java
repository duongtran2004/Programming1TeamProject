package Vehicle_Classes;
import Port.Port;
import Trip.Trip;
import Container_Class.*;
import FileIO.*;
import Users.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
//@JsonSubTypes ({@JsonSubTypes.Type(value = Vehicle_Classes.ship.class, name = "Vehicle_Classes.ship"), @JsonSubTypes.Type(value = Vehicle_Classes.basic_truck.class, name = "basic truck"), @JsonSubTypes.Type(value = Vehicle_Classes.reefer_truck.class, name = "reefer truck"), @JsonSubTypes.Type(value = Vehicle_Classes.tanker_truck.class, name = "basic truck") })
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String vid;
    private String name;
    private String current_port;
    private double fuel_capacity;
    private double current_fuel;
    private double carrying_capacity;
    public Vehicle(){

    }

    public Vehicle(String Vid, String name, double fuel_capacity, double carrying_capacity, String port){
        this.vid = Vid;
        this.name = name;
        this.fuel_capacity = fuel_capacity;
        this.carrying_capacity = carrying_capacity;
        this.current_port = port;
    }


    public String toString(){
        return "Vehicle Id: " + this.vid + "\n" + "Vehicle name: " + this.name + "\n" + "Current Port: \n" + this.current_port + "\n" + "Fuel capacity: " + this.fuel_capacity + "\n" + "Current fuel: " + this.current_fuel + "\n" + "Carrying capacity: " + this.carrying_capacity + "\n" +"\n";}


    //------------------------------------Getters-------------------------------------------//
    public String getVid(){
        return this.vid;
    }
    public String getCurrent_port(){
        return this.current_port;
    }

    public String getName(){return this.name;}

    /*public Container_Class.Open_Top.Trip.Trip getCurrent_trip() {
        return current_trip;
    }*/

    public double getFuel_capacity() {
        return fuel_capacity;
    }

    public double getCurrent_fuel() {
        return current_fuel;
    }

    public double getCarrying_capacity() {
        return carrying_capacity;
    }

    //-------------------------------------------------Setters------------------------------------//


    public void setVid(String vid) {
        this.vid = vid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrent_port(String port) throws IOException {
        this.current_port = port;

    }

    public void setFuel_capacity(double fuel_capacity) throws IOException {
        this.fuel_capacity = fuel_capacity;

    }

    public void setCurrent_fuel(double current_fuel) throws IOException {
        this.current_fuel = current_fuel;

    }

    public void setCarrying_capacity(double carrying_capacity) throws IOException {
        this.carrying_capacity = carrying_capacity;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            System.out.println("true");
            return true;
        }

        return this.vid.equals(((Vehicle) obj).vid) && this.name.equals(((Vehicle) obj).name);
    }




    public static boolean createNewVehicle(String name, String type, double fuel_capacity, double carrying_capacity, String port) throws IOException {

        String Vid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Vid = Vid + random_id.nextInt(10);
        }
        if (type.equals("1")){
           ship new_ship = new ship("SH"+Vid, name, fuel_capacity, carrying_capacity, port);
            FileIOUtil.InputObjectIntoFile(new_ship, "Ship.json");

        }
        else if (type.equals("2")){
            basic_truck new_basic_truck = new basic_truck("BT"+Vid, name, fuel_capacity, carrying_capacity, port);
            FileIOUtil.InputObjectIntoFile(new_basic_truck, "BasicTruck.json");
        }

        else if (type.equals("3")){
            reefer_truck new_reefer_truck = new reefer_truck("RT"+Vid, name, fuel_capacity, carrying_capacity, port);
            FileIOUtil.InputObjectIntoFile(new_reefer_truck, "ReeferTruck.json");
        }

        else {
            tanker_truck new_tanker = new tanker_truck("TT"+Vid, name, fuel_capacity, carrying_capacity, port);
            FileIOUtil.InputObjectIntoFile(new_tanker, "TankerTruck.json");
        }
        Port target = Port.queryPortbyID(port);
        target.setNumberofVehiclesOnsite(target.getNumberofVehiclesOnsite() + 1);
        FileIOUtil.updatePortFromFile(target);


        return true;
    }

    public static ArrayList<Vehicle> getVehicles() throws IOException {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        File SH_file = new File("Ship.json");
        if (SH_file.exists()){
            for (ship ship: FileIOUtil.ReadShipFromFile()){
                vehicles.add((Vehicle) ship);
            }
        }
        File BT_file = new File("BasicTruck.json");
        if (BT_file.exists()){
            for (basic_truck truck: FileIOUtil.ReadBasicTruckFromFile()){
                vehicles.add((Vehicle) truck);
            }
        }
        File RT_file = new File("ReeferTruck.json");
        if (RT_file.exists()){
            for (reefer_truck truck: FileIOUtil.ReadReeferFromFile()){
                vehicles.add((Vehicle) truck);
            }
        }
        File TT_file = new File("TankerTruck.json");
        if (TT_file.exists()){
            for (tanker_truck tanker: FileIOUtil.ReadTankerFromFile()){
                vehicles.add((Vehicle) tanker);
            }
        }
        return vehicles;
    }

    public static Vehicle queryVehiclebyID(String Vid) throws IOException {
        for (Vehicle vehicle: Vehicle.getVehicles()){
            if (vehicle.vid.equals(Vid)){
                return vehicle;
            }
        }
        System.out.println("Vehicle not found");
        return null;
    }

    public static void sortVehicleby_FuelCapacity(ArrayList<Vehicle> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_fuelcapac = vehicle_list.get(i).getFuel_capacity();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getFuel_capacity() > max_fuelcapac){
                        index = k;
                        max_fuelcapac = vehicle_list.get(index).getFuel_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < vehicle_list.size(); i++){
                double min_fuelcapac = vehicle_list.get(i).getFuel_capacity();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getFuel_capacity() < min_fuelcapac){
                        index = k;
                        min_fuelcapac = vehicle_list.get(index).getFuel_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }

    }

    public static void sortVehicleby_MaxCapacity(ArrayList<Vehicle> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_capac = vehicle_list.get(i).getCarrying_capacity();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getCarrying_capacity() > max_capac){
                        index = k;
                        max_capac = vehicle_list.get(k).getCarrying_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < vehicle_list.size(); i++){
                double min_capac = vehicle_list.get(i).getCarrying_capacity();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getCarrying_capacity() < min_capac){
                        index = k;
                        min_capac = vehicle_list.get(k).getCarrying_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }

    }

    public static void sortVehicleby_CurrentFuel(ArrayList<Vehicle> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_currentfuel = vehicle_list.get(i).getCurrent_fuel();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getCurrent_fuel() > max_currentfuel){
                        index = k;
                        max_currentfuel = vehicle_list.get(k).getCurrent_fuel();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < vehicle_list.size(); i++){
                double min_currentfuel = vehicle_list.get(i).getCurrent_fuel();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getCurrent_fuel() < min_currentfuel){
                        index = k;
                        min_currentfuel = vehicle_list.get(k).getCurrent_fuel();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }

    }

    public void depart() throws IOException {
        this.current_port = null;
        FileIOUtil.updateVehicleFromFile(this);


    }

    public void arrive(Port port) throws IOException {
        this.current_port = port.getPid();
        FileIOUtil.updateVehicleFromFile(this);

    }

    public boolean refuel() throws IOException {
        this.current_fuel = this.fuel_capacity;
        FileIOUtil.updateVehicleFromFile(this);
        return true;
    }
    public boolean fuelRemaining(Trip trip) throws IOException {
        double totalFuelConsumption_perkm = 0;
        for (String cid: trip.getTo_be_delivered_containers()){
            if (cid.startsWith("SH")){
                if (this.getVid().startsWith("SH")){
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_ship();
                }
                else {
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_truck();
                }

            }
            else if (cid.startsWith("OT")){
                if (this.getVid().startsWith("SH")){
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_ship();
                }
                else {
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_truck();
                }
            }
            else if (cid.startsWith("OS")){
                if (this.getVid().startsWith("SH")){
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_ship();
                }
                else {
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_truck();
                }
            }
            else if (cid.startsWith("RE")){
                if (this.getVid().startsWith("SH")){
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_ship();
                }
                else {
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_truck();
                }
            }
            else{
                if (this.getVid().startsWith("SH")){
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_ship();
                }
                else {
                    totalFuelConsumption_perkm = totalFuelConsumption_perkm + Dry_Storage.getFuel_consumption_per_km_on_truck();
                }
            }

        }
        double remainFuel = this.current_fuel - (totalFuelConsumption_perkm * Port.queryPortbyID(trip.getD_port()).distanceCalc(Port.queryPortbyID(trip.getA_port())));
        this.current_fuel = (double) Math.round(remainFuel * 100)/100;
        FileIOUtil.updateVehicleFromFile(this);
        return true;
    }

    public boolean successAssessment(ArrayList<Container> container_list, double trip_length){
        double fuel_consumption_per_km = 0;
        double container_weight = 0;

        for (Container container: container_list){
            container_weight = container_weight + container.getWeight();
            if (this.vid.startsWith("SH")){
                if (container.getCid().startsWith("DS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + Dry_Storage.getFuel_consumption_per_km_on_ship();
                }
                else if (container.getCid().startsWith("OT")){
                    fuel_consumption_per_km = fuel_consumption_per_km + Open_Top.getFuel_consumption_per_km_on_ship();
                }

                else if (container.getCid().startsWith("OS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + Open_Side.getFuel_consumption_per_km_on_ship();
                }
                else if (container.getCid().startsWith("RE")){
                    fuel_consumption_per_km = fuel_consumption_per_km + Refriderated.getFuel_consumption_per_km_on_ship();
                }
                else {
                    fuel_consumption_per_km = fuel_consumption_per_km + Liquid.getFuel_consumption_per_km_on_ship();
                }

            }
            else {
                if (container.getCid().startsWith("DS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + Dry_Storage.getFuel_consumption_per_km_on_truck();
                }
                else if (container.getCid().startsWith("OT")){
                    fuel_consumption_per_km = fuel_consumption_per_km + Open_Top.getFuel_consumption_per_km_on_truck();
                }

                else if (container.getCid().startsWith("OS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + Open_Side.getFuel_consumption_per_km_on_truck();
                }
                else if (container.getCid().startsWith("RE")){
                    fuel_consumption_per_km = fuel_consumption_per_km + Refriderated.getFuel_consumption_per_km_on_truck();
                }
                else {
                    fuel_consumption_per_km = fuel_consumption_per_km + Liquid.getFuel_consumption_per_km_on_truck();
                }
            }
        }

        double total_fuel_consumption = trip_length * fuel_consumption_per_km;
        if (this.fuel_capacity < total_fuel_consumption || this.carrying_capacity < container_weight){
            return false;
        }
        else {
            return true;
        }
    }

    public static void filteringVehiclebyType() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please select the vehicle type you want to filter:\nsh: ship\nbt: basic trucks\nrt: reefer trucks\ntt: tanker trucks\nq: quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("sh")){
                ArrayList<ship> ships = ship.getShip();
                Utlity.sortingByShip(ships);

            }
            else if (selection.equalsIgnoreCase("bt")){
                ArrayList<basic_truck> basic_trucks = basic_truck.getBasicTruck();
                Utlity.sortingBasicTruck(basic_trucks);
            }
            else if (selection.equalsIgnoreCase("rt")){
                ArrayList<reefer_truck> reefer_trucks = reefer_truck.getReeferTruck();
                Utlity.sortingReefer(reefer_trucks);


            }
            else if (selection.equalsIgnoreCase("tt")) {
                ArrayList<tanker_truck> tanker_trucks = tanker_truck.getTankerTruck();
                Utlity.sortingTanker(tanker_trucks);
            }
            else {
                System.out.println("option is not available. Please choose another option");
            }
        }
    }

    public static void filteringVehiclebyPortID(ArrayList<Vehicle> vehicles) throws IOException {
        System.out.println("Please enter port id");
        Port filter_port = Utlity.user_ChoosePort();
        if (filter_port != null){
            for (int i =0; i < vehicles.size(); i++){
                if (!filter_port.equals(vehicles.get(i).getCurrent_port())){
                    vehicles.remove(vehicles.get(i));
                }
            }
            Utlity.sortingVehicle(vehicles);
        }


    }

}








