package Container_Class;

import Port.Port;
import Vehicle_Classes.*;
import FileIO.*;
import Users.*;

import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Container implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String cid;
    private String name;
    private double weight;
    private  String current_port;
    private String current_vehicle = null;
    private static double fuel_consumption_per_km_on_ship;
    private static double fuel_consumption_per_km_on_truck;

    public Container(){

    }

    public Container(String Cid, String name, double weight, String port){
        this.cid = Cid;
        this.name = name;
        this.weight = weight;
        this.current_port = port;
    }


    public boolean equals(Container container) {
        if (container == this) {
            System.out.println("true");
            return true;
        }

        return this.cid.equals(container.cid) && this.name.equals(container.name);


    }

    public String toString(){
        return "Container_Class.Container id: " + this.cid + "\n" + "Container name: " + this.name + "\n" + "Current Port: " + this.current_port + "\n" + "Weight: " + this.weight +  "\n";}


    public static boolean createNewContainer(String type, String name, Port port, double weight) throws IOException {
        String Cid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Cid = Cid + random_id.nextInt(10);
        }

        if (type.equals("1")){
            Dry_Storage container = new Dry_Storage("DS" + Cid, name, weight, port.getPid());
            FileIOUtil.InputObjectIntoFile(container, "DryStorage.json");

        }
        if (type.equals("2")){
            Open_Top container = new Open_Top("OT" + Cid, name, weight, port.getPid());
            FileIOUtil.InputObjectIntoFile(container, "OpenTop.json");

        }
        if (type.equals("3")){
            Open_Side container = new Open_Side("OS" + Cid, name, weight, port.getPid() );
            FileIOUtil.InputObjectIntoFile(container, "OpenSide.json");

        }
        if (type.equals("4")){
            Refriderated container = new Refriderated("RE" + Cid, name, weight, port.getPid());
            FileIOUtil.InputObjectIntoFile(container, "Refrigerated.json");

        }
        if (type.equals("5")){
            Liquid container = new Liquid("LI" + Cid, name, weight, port.getPid());
            FileIOUtil.InputObjectIntoFile(container, "Liquid.json");

        }
        port.setNumberofContainersOnsite(port.getNumberofContainersOnsite() + 1);
        port.setCurrentCapacity(port.getCurrentCapacity() + weight);
        FileIOUtil.updatePortFromFile(port);
        return true;

    }

    public String getName(){
        return this.name;
    }
    public String getCid(){
        return this.cid;
    }

    public String getCurrent_port(){
        return this.current_port;
    }

    public String getCurrent_vehicle() {
        return current_vehicle;
    }

    public double getWeight(){
        return this.weight;
    }

    public static double getFuel_consumption_per_km_on_ship(){
        return Container.fuel_consumption_per_km_on_ship;
    }

    public static double getFuel_consumption_per_km_on_truck(){
        return Container.fuel_consumption_per_km_on_truck;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCurrent_port(String port) {
        this.current_port = port;
    }

    public void setCurrent_vehicle(String vehicle){
        this.current_vehicle = vehicle;
    }

    public static void setFuel_consumption_per_km_on_ship(double fuel_consumption_per_km_on_ship) {
        Container.fuel_consumption_per_km_on_ship = fuel_consumption_per_km_on_ship;
    }

    public static void setFuel_consumption_per_km_on_truck(double fuel_consumption_per_km_on_truck) {
        Container.fuel_consumption_per_km_on_truck = fuel_consumption_per_km_on_truck;
    }

    public static ArrayList<Container> getContainer() throws IOException {
        ArrayList<Container> containers = new ArrayList<Container>();
        File DS_file = new File("DryStorage.json");
        if (DS_file.exists()){
            for (Dry_Storage container: FileIOUtil.ReadDryStorageFromFile()){
                containers.add((Container) container);
            }
        }
        File OT_file = new File("OpenTop.json");
        if (OT_file.exists()){
            for (Open_Top container: FileIOUtil.ReadOpen_TopFromFile()){
                containers.add((Container) container);
            }
        }

        File OS_file = new File("OpenSide.json");
        if (OS_file.exists()){
            for (Open_Side container: FileIOUtil.ReadOpen_SideFromFile()){
                containers.add((Container) container);
            }
        }

        File RE_file = new File("Refrigerated.json");
        if (RE_file.exists()){
            for (Refriderated container: FileIOUtil.ReadRefridgeratedFromFile()){
                containers.add((Container) container);
            }
        }

        File LI_file = new File("Liquid.json");
        if (LI_file.exists()){
            for (Liquid container: FileIOUtil.ReadLiquidFromFile()){
                containers.add((Container) container);
            }
        }





        return containers;
    }

    public static void sortContainerByWeight(ArrayList<Container> container_list, boolean order){
        if (!order){
            for (int i =0; i < container_list.size(); i++){
                double max_weight = container_list.get(i).getWeight();
                int index = i;
                Container temp = container_list.get(i);
                for (int k = i+1; k < container_list.size(); k++){
                    if (container_list.get(k).getWeight() > max_weight){
                        index = k;
                        max_weight = container_list.get(k).getWeight();
                    }
                }
                container_list.set(i, container_list.get(index));
                container_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < container_list.size(); i++){
                double min_weight = container_list.get(i).getWeight();
                int index = i;
                Container temp = container_list.get(i);
                for (int k = i+1; k < container_list.size(); k++){
                    if (container_list.get(k).getWeight() < min_weight){
                        index = k;
                        min_weight = container_list.get(k).getWeight();
                    }
                }
                container_list.set(i, container_list.get(index));
                container_list.set(index, temp);
            }
        }
    }



    public static Container queryContainerbyID(String Cid) throws IOException {
        for (Container container: Container.getContainer() ){
            if (container.cid.equals(Cid)){
                return container;
            }
        }
        System.out.println("Container does not exist");
        return null;
    }

    public void enterPort(Port arrival_port) throws IOException {
        this.current_port = arrival_port.getPid();
        FileIOUtil.updateContainerFromFile(this);
        arrival_port.setCurrentCapacity(arrival_port.getCurrentCapacity() + this.weight);
        arrival_port.setNumberofContainersOnsite(arrival_port.getNumberofContainersOnsite() + 1);
        FileIOUtil.updatePortFromFile(arrival_port);

    }

    public void leavePort(Port departure_port) throws IOException {
        this.current_port = null;
        departure_port.setCurrentCapacity(departure_port.getCurrentCapacity() - this.weight);
        departure_port.setNumberofContainersOnsite(departure_port.getNumberofContainersOnsite() - 1);
        FileIOUtil.updateContainerFromFile(this);
        FileIOUtil.updatePortFromFile(departure_port);
    }


    public void loadedonVehicle(String vehicle) throws IOException {
        this.current_vehicle = vehicle;
        FileIOUtil.updateContainerFromFile(this);
    }

    public void loadedoffVehicle() throws IOException {
        this.current_vehicle = null;
        FileIOUtil.updateContainerFromFile(this);
    }

    public static void filteringContainerbyType() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please select the vehicle type you want to filter:\nds: Dry Storage\not: Open Top\nos: Open Side\nre: Refridgerated\nli: Container_Class.Liquid\nq: Quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            if (selection.equalsIgnoreCase("ds")){
                ArrayList<Dry_Storage> containers = Dry_Storage.getDryStorage();
                double total_weight = 0.0;
                for (Container container: containers){
                    total_weight = total_weight + container.weight;
                }
                System.out.println(String.format("Total weight for Dry Storage containers is %.2f", total_weight));
                Utlity.sortingDSContainer(containers);
                break;

            }
            else if (selection.equalsIgnoreCase("ot")){
                ArrayList<Open_Top> containers = Open_Top.getOpenTop();
                double total_weight = 0.0;
                for (Container container: containers){
                    total_weight = total_weight + container.weight;
                }
                System.out.println(String.format("Total weight for Open Top containers is %.2f", total_weight));
                Utlity.sortingOTContainer(containers);
                break;

            }
            else if (selection.equalsIgnoreCase("os")){
                ArrayList<Open_Side> containers = Open_Side.getOpenSide();
                double total_weight = 0.0;
                for (Container container: containers){
                    total_weight = total_weight + container.weight;
                }
                System.out.println(String.format("Total weight for Dry Storage containers is %.2f", total_weight));
                Utlity.sortingOSContainer(containers);
                break;

            }
            else if (selection.equalsIgnoreCase("re")) {
                ArrayList<Refriderated> containers = Refriderated.getRefridgerated();
                double total_weight = 0.0;
                for (Container container: containers){
                    total_weight = total_weight + container.weight;
                }
                System.out.println(String.format("Total weight for Dry Storage containers is %.2f", total_weight));
                Utlity.sortingREContainer(containers);
                break;


            }
            else if (selection.equalsIgnoreCase("li")) {
                ArrayList<Liquid> containers = Liquid.getLiquid();
                double total_weight = 0.0;
                for (Container container: containers){
                    total_weight = total_weight + container.weight;
                }
                System.out.println(String.format("Total weight for Dry Storage containers is %.2f", total_weight));
                Utlity.sortingLIContainer(containers);
                break;

            }
            else {
                System.out.println("option is not available. Please choose another option");
            }
        }
    }

    public static void filteringContainerbyPortID(ArrayList<Container> containers) throws IOException {
        Port filter_port = Utlity.user_ChoosePort();
        if (filter_port != null){
            for (int i =0; i < containers.size(); i++){
                if (!filter_port.equals(containers.get(i).getCurrent_port())){
                    containers.remove(containers.get(i));
                }
            }
            Utlity.sortingContainer(containers);
        }


    }

}



