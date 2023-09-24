package Trip;

import Port.Port;
import Users.Utlity;
import Vehicle_Classes.*;
import FileIO.*;
import Container_Class.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Trip implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String vehicle;
    private String tid;
    private String status = "Incomplete";
    private String d_port;
    private String a_port;
    private Date date_of_departure;
    private Date date_of_arrival;
    private ArrayList<String> to_be_delivered_containers = new ArrayList<String>();


    public Trip(){

    }
    public Trip(Vehicle vehicle, String Tid, Port D_port, Port A_port, Date date_of_departure, Date date_of_arrival, ArrayList<String> containers){
        this.vehicle = vehicle.getVid();
        this.tid = Tid;
        this.d_port = D_port.getPid();
        this.a_port = A_port.getPid();
        this.date_of_arrival = date_of_arrival;
        this.date_of_departure = date_of_departure;
        this.to_be_delivered_containers = containers;
    }
    public String toString(){
        return "Container_Class.Open_Top.Trip.Trip id: " + this.tid + "\n" + "Vehicle_Classes.Vehicle: " + this.vehicle + "\n" + "Status: " + this.status + "\n" + "Departure Port.Port: " + this.d_port + "\n" + "Arrival Port.Port: " + this.a_port + "\n" + "Date of Departure: " + this.date_of_departure + "\n"+ "Date of Arrival: " + this.date_of_arrival + "\n" + "\n";}

    public boolean equals(Trip trip) {
        if (trip == this) {
            System.out.println("true");
            return true;
        }
        return this.tid.equals(trip.tid) && this.vehicle.equals(trip.vehicle);
    }

    //------------------------------------------Getter--------------------------------------//

    public String getTid(){
        return this.tid;
    }


    public String getVehicle(){
        return  this.vehicle;
    }
    public ArrayList<String> getTo_be_delivered_containers(){
        return this.to_be_delivered_containers;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getD_port(){
        return this.d_port;
    }
    public String getA_port(){
        return this.a_port;
    }
    public Date getDate_of_departure(){
        return  date_of_departure;
    }
    public Date getDate_of_arrival(){
        return date_of_arrival;
    }

    public String getStatus() {
        return status;
    }

    //---------------------------------------------------Setter----------------------------------------//


    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setD_port(String d_port) {
        this.d_port = d_port;
    }

    public void setA_port(String a_port) {
        this.a_port = a_port;
    }

    public void setDate_of_departure(Date date_of_departure) {
        this.date_of_departure = date_of_departure;
    }

    public void setDate_of_arrival(Date date_of_arrival) {
        this.date_of_arrival = date_of_arrival;
    }

    public void setTo_be_delivered_containers(ArrayList<String> to_be_delivered_containers) {
        this.to_be_delivered_containers = to_be_delivered_containers;
    }

    public static ArrayList<Trip> getTrip() throws IOException {
        return FileIOUtil.ReadTripFromFile();
    }


    public static ArrayList<Vehicle> checking_vehicle_eligibility(Port port, Port port_of_arrival, Date date_of_arrival, Date date_of_departure, ArrayList<Container>container_list) throws IOException {
        //this method returns a list of vehicle available to be deployed on the trip. Vehicle_Classes.Vehicle eligibility is evaluated based on the arrival base's landing ability (False means only accessible to ships) whether a vehicle is capable of finishing a trip given a list of containers and trip length (by calling the successAssement method from the Vehicle_Classes.Vehicle Class), whether it is on another trip on the given trip duration.
        ArrayList<String> unavailable_vehicles = new ArrayList<String>();
        ArrayList<Vehicle> vehicles = Vehicle.getVehicles();
        ArrayList<Vehicle> deployable_vehicle = new ArrayList<Vehicle>();
        File file = new File("Trip.json");
        if (file.length() == 0){
            for (Vehicle vehicle: vehicles){
                if (port_of_arrival.isLandingAbility()){
                    if (vehicle.getCurrent_port().equals(port.getPid()) && vehicle.successAssessment(container_list, port.distanceCalc(port_of_arrival))){
                        deployable_vehicle.add(vehicle);
                    }
                }
                else {
                    if (vehicle.getCurrent_port().equals(port.getPid())  && vehicle.getVid().startsWith("SH") && vehicle.successAssessment(container_list, port.distanceCalc(port_of_arrival))){
                        deployable_vehicle.add(vehicle);
                    }
                }
            }
        }
        else {
            for (Trip trip: FileIOUtil.ReadTripFromFile()){
                if (trip.getD_port().equals(port.getPid())){
                    if ((date_of_departure.after(trip.date_of_departure) && date_of_departure.before(trip.date_of_arrival)) || ((date_of_arrival.after(trip.date_of_departure) && date_of_arrival.before(trip.date_of_arrival))) || (date_of_departure.equals(trip.date_of_arrival) || date_of_departure.equals(trip.date_of_departure)) || (date_of_arrival.equals(trip.date_of_arrival) || date_of_arrival.equals(trip.date_of_departure))){
                        unavailable_vehicles.add(trip.getVehicle());
                    }
                }
            }

            for (Vehicle vehicle: vehicles){
                if (port_of_arrival.isLandingAbility()){
                    if (vehicle.getCurrent_port() != null && vehicle.getCurrent_port().equals(port.getPid()) && vehicle.successAssessment(container_list, port.distanceCalc(port_of_arrival)) && !unavailable_vehicles.contains(vehicle.getVid())){
                        deployable_vehicle.add(vehicle);
                    }
                }
                else {
                    if (vehicle.getCurrent_port() != null && vehicle.getCurrent_port().equals(port.getPid()) && vehicle.successAssessment(container_list, port.distanceCalc(port_of_arrival)) && !unavailable_vehicles.contains(vehicle.getVid()) && vehicle.getVid().startsWith("SH")){
                        deployable_vehicle.add(vehicle);
                    }
                }
            }

        }
        return deployable_vehicle;
    }



    //this method will register a trip into the system. It prompts the user to input date and port of departure and arrival, it will then have them choose from a list of available vehicles returned by the checking_vehicle_eligibility function. The id of the trip will be randomly generated
    public static Trip registeringTrip(Port port, Date date_of_departure, Date date_of_arrival, Port port_of_arrival, ArrayList<String> containers, Vehicle selected_vehicle) throws IOException {
        //Randomly generating trip id
        Random generating_id = new Random();
        String Tid = "t";
        for (int i =0; i<=10; i++){
            Tid = Tid + generating_id.nextInt(10);
        }
        Trip new_trip = new Trip(selected_vehicle, Tid, port, port_of_arrival, date_of_departure, date_of_arrival, containers);
        FileIOUtil.InputObjectIntoFile(new_trip, "Trip.json");
        return new_trip;
    }

    public static Trip queryByName(String Tid) throws IOException {
        for (Trip trip: FileIOUtil.ReadTripFromFile()){
            if (trip.getTid().equals(Tid)){
                return trip;
            }
        }
        return null;
    }
    public static void sortTripbyDod(ArrayList<Trip> trip_list, boolean order){
        if (!order){
            for (int i =0; i < trip_list.size(); i++){
                Date latest_date_of_departure = trip_list.get(i).getDate_of_departure();
                int index = i;
                Trip temp = trip_list.get(i);
                for (int k = i+1; k < trip_list.size(); k++){
                    if (trip_list.get(k).getDate_of_departure().after(latest_date_of_departure)){
                        index = k;
                        latest_date_of_departure = trip_list.get(k).getDate_of_departure();
                    }
                }
                trip_list.set(i, trip_list.get(index));
                trip_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < trip_list.size(); i++){
                Date earliest_date_of_departure = trip_list.get(i).getDate_of_departure();
                int index = i;
                Trip temp = trip_list.get(i);
                for (int k = i+1; k < trip_list.size(); k++){
                    if (trip_list.get(k).getDate_of_departure().before(earliest_date_of_departure)){
                        index = k;
                        earliest_date_of_departure = trip_list.get(k).getDate_of_departure();
                    }
                }
                trip_list.set(i, trip_list.get(index));
                trip_list.set(index, temp);
            }
        }
    }

    public static void sortTripbyDoa(ArrayList<Trip> trip_list, boolean order){
        if (!order){
            for (int i =0; i < trip_list.size(); i++){
                Date latest_date_of_arrival = trip_list.get(i).getDate_of_arrival();
                int index = i;
                Trip temp = trip_list.get(i);
                for (int k = i+1; k < trip_list.size(); k++){
                    if (trip_list.get(k).getDate_of_arrival().after(latest_date_of_arrival)){
                        index = k;
                        latest_date_of_arrival = trip_list.get(k).getDate_of_arrival();
                    }
                }
                trip_list.set(i, trip_list.get(index));
                trip_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < trip_list.size(); i++){
                Date earliest_date_of_arrival = trip_list.get(i).getDate_of_arrival();
                int index = i;
                Trip temp = trip_list.get(i);
                for (int k = i+1; k < trip_list.size(); k++){
                    if (trip_list.get(k).getDate_of_arrival().before(earliest_date_of_arrival)){
                        index = k;
                        earliest_date_of_arrival = trip_list.get(k).getDate_of_arrival();
                    }
                }
                trip_list.set(i, trip_list.get(index));
                trip_list.set(i, trip_list.get(index));
            }
        }
    }




    //This method allows Port.Port Manager to view the outgoing trips after/before inputted dates
    public static ArrayList<Trip> outgoingTrip_query(Port port, Date after, Date before) throws IOException {
        //Have the users input the interval they would like to query
        ArrayList<Trip> trip_from_port = new ArrayList<Trip>();
        ArrayList<Trip> trip_list = new ArrayList<Trip>();
        for (Trip o: FileIOUtil.ReadTripFromFile()){
            trip_list.add(o);
        }
        //querying
        //if the user does not enter both before and after filtering dates, the function will return all outgoing trips
        if (after == null && before == null){
            for (Trip trip: trip_list){
                if (trip.getD_port().equals(port.getPid())){
                    trip_from_port.add(trip);
                }
            }
        }

        //if the user only enter the after filtering date, the function will return all the outgoing trip after that date
        else if (before == null && !(after==null)){
            for (Trip trip: trip_list){
                if (trip.date_of_departure.after(after) && trip.getD_port().equals(port.getPid())){
                    trip_from_port.add(trip);
                }
            }
        }

        //if the user only enter the after filtering date, the function will return all the outgoing trip before that date
        else if (after == null && !(before == null)){
            for (Trip trip: trip_list){
                if (trip.date_of_departure.before(before) && trip.getD_port().equals(port.getPid())){
                    trip_from_port.add(trip);
                }
            }
        }
        else {
            for (Trip trip: trip_list){
                if (trip.date_of_departure.before(before) && trip.date_of_departure.after(after) && trip.getD_port().equals(port.getPid())){
                    trip_from_port.add(trip);
                }
            }
        }

        return trip_from_port;
    }


    //This method allows Port.Port Manager to see the incoming trip after/before inputted dates.
    public static ArrayList<Trip> incomingTrip_query(Port port, Date after, Date before) throws IOException {
        //Have the users input the interval they would like to query
        ArrayList<Trip> trip_from_port = new ArrayList<Trip>();
        ArrayList<Trip> trip_list = Trip.getTrip();


        //querying
        //if the user does not enter both before and after filtering dates, the function will return all incoming trips
        if (after == null && before == null){
            for (Trip trip: trip_list){
                if (trip.getA_port().equals(port.getPid())){
                    trip_from_port.add(trip);
                }
            }
        }
        //if the user only enter the after filtering date, the function will return all the outgoing trip after that date
        else if (before == null && !(after==null)){
            for (Trip trip: trip_list){
                if (trip.getDate_of_arrival().after(after) && trip.getA_port().equals(port.getPid())){
                    trip_from_port.add(trip);
                }
            }
        }

        //if the user only enter the after filtering date, the function will return all the outgoing trip before that date
        else if (after == null && !(before == null)){
            for (Trip trip: trip_list){
                if (trip.getDate_of_arrival().before(before) && trip.getA_port().equals(port.getPid())){
                    trip_from_port.add(trip);
                }
            }
        }
        else{
            for (Trip trip: trip_list){
                if (trip.date_of_arrival.before(before) && trip.date_of_arrival.after(after) && trip.getD_port().equals(port.getPid())){
                    trip_from_port.add(trip);
                }
            }
        }
        return trip_from_port;
    }

    public void completeTrip(String progress) throws IOException {
        this.setStatus(progress);
        FileIOUtil.updateTripFromFile(this);
    }


    public static boolean filteringTripsbyVehicle(ArrayList<Trip> filtered_list) throws IOException {
        Vehicle filter_vehicle = Utlity.user_ChooseVehicle();
        if (filter_vehicle == null){
            return false;
        }
        filtered_list.removeIf(n -> n.getVehicle().equals(filter_vehicle.getVid()));
        Utlity.sortingTrip(filtered_list);
        System.out.println(Utlity.trip_Table(filtered_list));
        return true;

    }

    public static boolean filteringTripbyArrivalPort(ArrayList<Trip> filtered_list) throws IOException {
        Port filter_port = Utlity.user_ChoosePort();
        if (filter_port == null){
            return false;
        }
        filtered_list.removeIf(n->!n.getA_port().equals(filter_port.getPid()));
        Utlity.sortingTrip(filtered_list);
        System.out.println(Utlity.trip_Table(filtered_list));
        return true;
    }

    public static boolean filteringTripbyDeparturePort(ArrayList<Trip> filtered_list) throws IOException {
        Port filter_port = Utlity.user_ChoosePort();
        if (filter_port == null){
            return false;
        }
        filtered_list.removeIf(n->!n.getD_port().equals(filter_port.getPid()));
        Utlity.sortingTrip(filtered_list);
        System.out.println(Utlity.trip_Table(filtered_list));
        return true;

    }

    public static boolean filteringTripbyStatus(ArrayList<Trip> filtered_list){
        while (true){
            System.out.println("a: See Completed Trips\nb: See Incompleted Trips\nc: Commenced\nq: Quit");
            String selection = Utlity.scanner.nextLine();
            if (selection.equalsIgnoreCase("a")){
                filtered_list.removeIf(trip -> !trip.getStatus().equalsIgnoreCase("Commenced"));
                if (!Utlity.sortingTrip(filtered_list)){
                    return false;
                }
            }
            else if (selection.equalsIgnoreCase("b")){
                filtered_list.removeIf(trip -> !trip.getStatus().equalsIgnoreCase("Complete"));
                if (!Utlity.sortingTrip(filtered_list)){
                    return false;
                }
            }
            else if (selection.equalsIgnoreCase("c")){
                filtered_list.removeIf(trip -> !trip.getStatus().equalsIgnoreCase("Commenced"));
                if (!Utlity.sortingTrip(filtered_list)){
                    return false;
                }
            }
            else if (selection.equalsIgnoreCase("q")){
                return false;
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
    }
}