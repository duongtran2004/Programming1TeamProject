package Vehicle_Classes;
import Port.Port;
import FileIO.*;

import java.io.IOException;
import java.util.ArrayList;

public class reefer_truck extends Vehicle{
    public reefer_truck(){

    }
    public reefer_truck(String Vid, String name, double fuel_capacity, double carrying_capacity, String port){
        super(Vid, name, fuel_capacity,carrying_capacity, port);
    }
    public static ArrayList<reefer_truck> getReeferTruck() throws IOException {
        return FileIOUtil.ReadReeferFromFile();
    }
    public static reefer_truck queryReeferTruckbyId(String Vid) throws IOException {
        reefer_truck query_truck = null;
        for (reefer_truck truck: reefer_truck.getReeferTruck()){
            if (truck.getVid().equals(Vid)){
                query_truck = truck;
            }
        }
        return query_truck;
    }

    public static void sortby_FuelCapacity(ArrayList<reefer_truck> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_fuelcapac = vehicle_list.get(i).getFuel_capacity();
                int index = i;
                reefer_truck temp = vehicle_list.get(i);
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
                reefer_truck temp = vehicle_list.get(i);
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

    public static void sortby_MaxCapacity(ArrayList<reefer_truck> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_capac = vehicle_list.get(i).getCarrying_capacity();
                int index = i;
                reefer_truck temp = vehicle_list.get(i);
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
                reefer_truck temp = vehicle_list.get(i);
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
    public static void sortby_CurrentFuel(ArrayList<reefer_truck> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_currentfuel = vehicle_list.get(i).getCurrent_fuel();
                int index = i;
                reefer_truck temp = vehicle_list.get(i);
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
                reefer_truck temp = vehicle_list.get(i);
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
