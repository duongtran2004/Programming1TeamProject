package Port;

import java.io.*;

import Container_Class.Container;
import Trip.Trip;
import Users.*;
import FileIO.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;


public class Port implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String pid;
    private double latitude;
    private double longitude;
    private String name;
    private double storingCapacity;
    private boolean landingAbility;
    private int numberofContainersOnsite=0;
    private int numberofVehiclesOnsite=0;
    private double currentCapacity=0;

    public boolean equals(Port port) {
        if (port == this) {
            System.out.println("true");
            return true;
        }

        return this.pid.equals(port.pid) && this.name.equals(port.name) && this.latitude == port.latitude  && this.longitude == port.longitude;


    }

    public  Port(){

    }

    public Port(String Pid, double latitude, double longitude, String name, double storing_capacity, boolean landing_ability){
        this.pid = Pid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.storingCapacity = storing_capacity;
        this.landingAbility = landing_ability;
    }
    public String toString(){
        return "Port id: " + this.pid + "\n" + "Port name: "+ this.name + "\n" + "Port latitude: " + this.latitude + "\n" + "Port longitude: " + this.longitude + "\n" + "Storing capacity: "+ this.storingCapacity + "\n" + "Landing ability: " + this.landingAbility + "\n" +"Number of vehicles onsite: " + this.numberofVehiclesOnsite + "\n" + "Number of containers onsite: " + this.numberofContainersOnsite + "\n" + "Current Capacity " + this.currentCapacity + "\n";}

    public static Port createNewPort(String name, double latitude, double longitude, double storing_capacity, boolean landing_ability) throws IOException {
        String Pid = "";
        Random random = new Random();
        for (int i = 1; i<=10; i++){
            Pid = Pid + random.nextInt(10);
        }
        Port new_port = new Port(Pid, latitude, longitude, name, storing_capacity, landing_ability);
        FileIOUtil.InputObjectIntoFile(new_port, "Port.json");
        return new_port;

    }

    public static ArrayList<Port> getPorts() throws IOException {
        return FileIOUtil.ReadPortFromFile();
    }

    public static Port queryPortbyID(String Pid) throws IOException {
        for (Port port: FileIOUtil.ReadPortFromFile()){
            if (port.pid.equals(Pid)){
                return port;
            }
        }
        System.out.println("This port does not exist");
        return null;
    }
    //--------------------------------------------Getters-----------------------------------------//


    public String getPid() {
        return pid;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }


    public int getNumberofContainersOnsite() {
        return this.numberofContainersOnsite;
    }

    public int getNumberofVehiclesOnsite() {
        return numberofVehiclesOnsite;
    }
    public void setCurrentCapacity(double capacity) {
        this.currentCapacity = capacity;
    }

    public double getCurrentCapacity() {
        return this.currentCapacity;
    }



    //-----------------------------------------------------Setters---------------------------------------------//


    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public void setNumberofContainersOnsite(int number) {
        this.numberofContainersOnsite = number;
    }
    public void setNumberofVehiclesOnsite(int numberofVehiclesOnsite) {
        this.numberofVehiclesOnsite = numberofVehiclesOnsite;
    }

    public double distanceCalc(Port targetPort){
        double distance = (3440.1 * Math.acos((Math.sin(this.latitude * (Math.PI/180)) * Math.sin(targetPort.latitude * (Math.PI/180))) + Math.cos(this.latitude * (Math.PI/180)) * Math.cos(targetPort.latitude * (Math.PI/180)) * Math.cos(this.longitude * (Math.PI/180) - targetPort.longitude * (Math.PI/180)))) * 1.852 ;
        distance = (double) Math.round(distance * 100)/100;
        return distance;
    }

    public static void sortPortbyStoringCapacity(ArrayList<Port> port_list, boolean order){
        if (!order){
            for (int i =0; i < port_list.size(); i++){
                double max_capac = port_list.get(i).getStoringCapacity();
                Port temp = port_list.get(i);
                int index = i;
                for (int k = i+1; k < port_list.size(); k++){
                    if (port_list.get(k).getStoringCapacity() > max_capac){
                        index = k;
                        max_capac = port_list.get(k).getStoringCapacity();
                    }
                }
                port_list.set(i, port_list.get(index));
                port_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < port_list.size(); i++){
                double min_capac = port_list.get(i).getStoringCapacity();
                Port temp = port_list.get(i);
                int index = i;
                for (int k = i+1; k < port_list.size(); k++){
                    if (port_list.get(k).getStoringCapacity() < min_capac){
                        index = k;
                        min_capac = port_list.get(k).getStoringCapacity();
                    }
                }
                port_list.set(i, port_list.get(index));
                port_list.set(index, temp);
            }
        }

    }
    public static void sortPortbyCurentCapacity(ArrayList<Port> port_list, boolean order){
        if (!order){
            for (int i =0; i < port_list.size(); i++){
                double max_capac = port_list.get(i).getCurrentCapacity();
                Port temp = port_list.get(i);
                int index = i;
                for (int k = i+1; k < port_list.size(); k++){
                    if (port_list.get(k).getCurrentCapacity() > max_capac){
                        index = k;
                        max_capac = port_list.get(k).getCurrentCapacity();
                    }
                }
                port_list.set(i, port_list.get(index));
                port_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < port_list.size(); i++){
                double min_capac = port_list.get(i).getCurrentCapacity();
                Port temp = port_list.get(i);
                int index = i;
                for (int k = i+1; k < port_list.size(); k++){
                    if (port_list.get(k).getCurrentCapacity() < min_capac){
                        index = k;
                        min_capac = port_list.get(k).getCurrentCapacity();
                    }
                }
                port_list.set(i, port_list.get(index));
                port_list.set(index, temp);
            }
        }

    }

    public static void sortPortbyVehicleOnsites(ArrayList<Port> port_list, boolean order){
        if (!order){
            for (int i =0; i < port_list.size(); i++){
                int max_capac = port_list.get(i).getNumberofVehiclesOnsite();
                Port temp = port_list.get(i);
                int index = i;
                for (int k = i+1; k < port_list.size(); k++){
                    if (port_list.get(k).getNumberofVehiclesOnsite() > max_capac){
                        index = k;
                        max_capac = port_list.get(k).getNumberofVehiclesOnsite();
                    }
                }
                port_list.set(i, port_list.get(index));
                port_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < port_list.size(); i++){
                double min_capac = port_list.get(i).getNumberofVehiclesOnsite();
                Port temp = port_list.get(i);
                int index = i;
                for (int k = i+1; k < port_list.size(); k++){
                    if (port_list.get(k).getNumberofVehiclesOnsite() < min_capac){
                        index = k;
                        min_capac = port_list.get(k).getNumberofVehiclesOnsite();
                    }
                }
                port_list.set(i, port_list.get(index));
                port_list.set(index, temp);
            }
        }

    }

    public static void sortPortbyContainerOnsites(ArrayList<Port> port_list, boolean order){
        if (!order){
            for (int i =0; i < port_list.size(); i++){
                int max_capac = port_list.get(i).getNumberofContainersOnsite();
                Port temp = port_list.get(i);
                int index = i;
                for (int k = i+1; k < port_list.size(); k++){
                    if (port_list.get(k).getNumberofContainersOnsite() > max_capac){
                        index = k;
                        max_capac = port_list.get(k).getNumberofContainersOnsite();
                    }
                }
                port_list.set(i, port_list.get(index));
                port_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < port_list.size(); i++){
                double min_capac = port_list.get(i).getNumberofContainersOnsite();
                Port temp = port_list.get(i);
                int index = i;
                for (int k = i+1; k < port_list.size(); k++){
                    if (port_list.get(k).getNumberofContainersOnsite() < min_capac){
                        index = k;
                        min_capac = port_list.get(k).getNumberofContainersOnsite();
                    }
                }
                port_list.set(i, port_list.get(index));
                port_list.set(index, temp);
            }
        }

    }

    public static void filteringPortsbyLandingAbility(ArrayList<Port> port_list){
        while (true){
            System.out.println("Enter landing ability (t: true _ f: false)");
            String response = Utlity.scanner.nextLine();
            if (response.equalsIgnoreCase("t")){
                port_list.removeIf(n -> !n.landingAbility);
                Utlity.sortingPort(port_list);
                break;
            }
            else if (response.equalsIgnoreCase("f")){
                port_list.removeIf(n -> n.landingAbility);
                Utlity.sortingPort(port_list);
                break;
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }

        }


    }

    public double forecastCapacity(Date date) throws IOException {
        double forcasted_capacity = 0;
        for (Trip trip: Trip.getTrip()){
            if (!date.after(trip.getDate_of_arrival()) && date.before(trip.getDate_of_arrival()) && this.getPid().equals(trip.getA_port())){
                for (String cid: trip.getTo_be_delivered_containers()){
                    forcasted_capacity = forcasted_capacity + Container.queryContainerbyID(cid).getWeight();
                }
            }
            if (!date.after(trip.getDate_of_departure()) && date.before(trip.getDate_of_departure()) && this.getPid().equals(trip.getD_port())){
                for (String cid: trip.getTo_be_delivered_containers()){
                    forcasted_capacity = forcasted_capacity - Container.queryContainerbyID(cid).getWeight();
                }
            }
        }
        return forcasted_capacity;
    }

    public static ArrayList<Port> checkPortAvailability(double weight, Date date) throws IOException {
        ArrayList<Port> available_Port = new ArrayList<Port>();
        for (Port port: Port.getPorts()){
            if (port.forecastCapacity(date) + weight <= port.getStoringCapacity()){
                available_Port.add(port);
            }
        }
        return available_Port;
    }







}
