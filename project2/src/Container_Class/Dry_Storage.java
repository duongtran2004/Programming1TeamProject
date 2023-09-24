package Container_Class;

import Port.Port;
import FileIO.*;

import java.io.IOException;
import java.util.ArrayList;

public class Dry_Storage extends Container {
    private static double fuel_consumption_per_km_on_ship = 3.5;
    private static double fuel_consumption_per_km_on_truck = 4.6;

    public Dry_Storage(){

    }
    public Dry_Storage(String Cid, String name, double weight, String port){
        super(Cid, name, weight, port);
    }
    public static ArrayList<Dry_Storage> getDryStorage() throws IOException {
        return FileIOUtil.ReadDryStorageFromFile();
    }


    public static double getFuel_consumption_per_km_on_ship() {
        return fuel_consumption_per_km_on_ship;
    }


    public static double getFuel_consumption_per_km_on_truck() {
        return fuel_consumption_per_km_on_truck;
    }

    public static void setFuel_consumption_per_km_on_ship(double fuel_consumption_per_km_on_ship) {
        Dry_Storage.fuel_consumption_per_km_on_ship = fuel_consumption_per_km_on_ship;
    }

    public static void setFuel_consumption_per_km_on_truck(double fuel_consumption_per_km_on_truck) {
        Dry_Storage.fuel_consumption_per_km_on_truck = fuel_consumption_per_km_on_truck;
    }

    public static Dry_Storage queryDryStoragebyID(String Cid) throws IOException {
        for (Dry_Storage container: Dry_Storage.getDryStorage()  ){
            if (container.getCid().equals(Cid)){
                return container;
            }
        }
        System.out.println("Container_Class.Container does not exist");
        return null;
    }

    public static void sortContainerby_Weight(ArrayList<Dry_Storage> container_list, boolean order){
        if (!order){
            for (int i =0; i < container_list.size(); i++){
                double max_weight = container_list.get(i).getWeight();
                int index = i;
                Dry_Storage temp = container_list.get(i);
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
                Dry_Storage temp = container_list.get(i);
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
}


