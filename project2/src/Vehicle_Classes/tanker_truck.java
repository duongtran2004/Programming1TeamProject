package Vehicle_Classes;
import Port.Port;
import FileIO.*;
import java.io.IOException;
import java.util.ArrayList;

public class tanker_truck extends Vehicle{
    public tanker_truck(){

    }
    public tanker_truck(String Vid, String name, double fuel_capacity, double carrying_capacity, String port){
        super(Vid, name, fuel_capacity,carrying_capacity, port);
    }
    public static ArrayList<tanker_truck> getTankerTruck() throws IOException {
        return FileIOUtil.ReadTankerFromFile();
    }
    public static tanker_truck queryTankerTruckbyId(String Vid) throws IOException {
        tanker_truck query_truck = null;
        for (tanker_truck truck: tanker_truck.getTankerTruck()){
            if (truck.getVid().equals(Vid)){
                query_truck = truck;
            }
        }
        return query_truck;
    }

    public static void sortby_FuelCapacity(ArrayList<tanker_truck> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_fuelcapac = vehicle_list.get(i).getFuel_capacity();
                int index = i;
                tanker_truck temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getFuel_capacity() > max_fuelcapac){
                        index = k;
                        max_fuelcapac = vehicle_list.get(k).getFuel_capacity();
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
                tanker_truck temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getFuel_capacity() < min_fuelcapac){
                        index = k;
                        min_fuelcapac = vehicle_list.get(k).getFuel_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }

    }

    public static void sortby_MaxCapacity(ArrayList<tanker_truck> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_capac = vehicle_list.get(i).getCarrying_capacity();
                int index = i;
                tanker_truck temp = vehicle_list.get(i);
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
                tanker_truck temp = vehicle_list.get(i);
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

    public static void sortby_CurrentFuel(ArrayList<tanker_truck> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_currentfuel = vehicle_list.get(i).getCurrent_fuel();
                int index = i;
                tanker_truck temp = vehicle_list.get(i);
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
                tanker_truck temp = vehicle_list.get(i);
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
}
