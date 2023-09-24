package FileIO;

import Container_Class.*;
import Port.Port;
import Trip.Trip;
import Users.Port_Manager;
import Users.System_Admin;
import Vehicle_Classes.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class FileIOUtil {

    public static ArrayList<Object> ReadObjectFromFile(String file_name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(file_name);
        ArrayList<Object> list = new ArrayList<Object>();
        list = mapper.readValue(file, new TypeReference<ArrayList<Object>>() {
        });
        return list;
    }
    public static ArrayList<Port> ReadPortFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("Port.json");
        ArrayList<Port> list = new ArrayList<Port>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<Port>>() {});
        }
        return list;
    }
    //---------------------------------------------Read From Files-------------------------------------------------//
    public static ArrayList<ship> ReadShipFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("Ship.json");
        ArrayList<ship> list = new ArrayList<ship>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<ship>>() {});
        }

        return list;
    }
    public static ArrayList<basic_truck> ReadBasicTruckFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("BasicTruck.json");
        ArrayList<basic_truck> list = new ArrayList<basic_truck>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<basic_truck>>() {});
        }

        return list;
    }
    public static ArrayList<reefer_truck> ReadReeferFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("ReeferTruck.json");
        ArrayList<reefer_truck> list = new ArrayList<reefer_truck>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<reefer_truck>>() {});
        }

        return list;
    }
    public static ArrayList<tanker_truck> ReadTankerFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("TankerTruck.json");
        ArrayList<tanker_truck> list = new ArrayList<tanker_truck>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<tanker_truck>>() {});
        }

        return list;
    }

    public static ArrayList<Dry_Storage> ReadDryStorageFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("DryStorage.json");
        ArrayList<Dry_Storage> list = new ArrayList<Dry_Storage>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<Dry_Storage>>() {});
        }

        return list;
    }
    public static ArrayList<Open_Top> ReadOpen_TopFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("OpenTop.json");
        ArrayList<Open_Top> list = new ArrayList<Open_Top>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<Open_Top>>() {});
        }
        return list;
    }
    public static ArrayList<Open_Side> ReadOpen_SideFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("OpenSide.json");
        ArrayList<Open_Side> list = new ArrayList<Open_Side>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<Open_Side>>() {});
        }

        return list;
    }
    public static ArrayList<Refriderated> ReadRefridgeratedFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("Refrigerated.json");
        ArrayList<Refriderated> list = new ArrayList<Refriderated>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<Refriderated>>() {});
        }
        return list;
    }
    public static ArrayList<Liquid> ReadLiquidFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("Liquid.json");
        ArrayList<Liquid> list = new ArrayList<Liquid>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<Liquid>>() {});
        }

        return list;
    }

    public static ArrayList<Trip> ReadTripFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("Trip.json");
        ArrayList<Trip> list = new ArrayList<Trip>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<Trip>>() {});
        }

        return list;
    }
    public static ArrayList<Port_Manager> ReadManagerFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("PortManager.json");
        ArrayList<Port_Manager> list = new ArrayList<Port_Manager>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<Port_Manager>>() {});
        }

        return list;
    }

    public static ArrayList<System_Admin> ReadAdminFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("SystemAdmin.json");
        ArrayList<System_Admin> list = new ArrayList<System_Admin>();
        if (file.length() != 0 ){
            list = mapper.readValue(file, new TypeReference<ArrayList<System_Admin>>() {});
        }

        return list;
    }

    public static void InputObjectIntoFile(Object object, String file_name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(file_name);
        if (file.exists() ){
            ArrayList<Object> list_2 = FileIOUtil.ReadObjectFromFile(file_name);
            list_2.add(object);
            mapper.writeValue(file, list_2);
        }
        else {
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(object);
            mapper.writeValue(file, list);
        }
    }
    //------------------------------------------Update Method-------------------------------------------//

    public static void updatePortFromFile(Port port) throws IOException {
        File file = new File("Port.json");
        ArrayList<Port> list = FileIOUtil.ReadPortFromFile();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(port)){
                list.set(i, port);
                break;
            }
        }

        file.delete();
        file.createNewFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);

    }
    public static void updateTripFromFile(Trip trip) throws IOException {
        File file = new File("Trip.json");
        ArrayList<Trip> list = FileIOUtil.ReadTripFromFile();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(trip)){
                list.set(i, trip);
                break;
            }
        }

        file.delete();
        file.createNewFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);

    }

    public static void updateManagerFromFile(Port_Manager manager) throws IOException {
        File file = new File("PortManager.json");
        ArrayList<Port_Manager> list = FileIOUtil.ReadManagerFromFile();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(manager)){
                list.set(i, manager);
                break;
            }
        }

        file.delete();
        file.createNewFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);

    }

    public static void updateAdminFromFile(System_Admin admin) throws IOException {
        File file = new File("SystemAdmin.json");
        ArrayList<System_Admin> list = FileIOUtil.ReadAdminFromFile();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(admin)){
                list.set(i, admin);
                break;
            }
        }

        file.delete();
        file.createNewFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);

    }


    public static void updateVehicleFromFile(Vehicle vehicle) throws IOException {
        if (vehicle.getVid().startsWith("SH")){
            ship ship = (ship) vehicle;
            File file = new File("Ship.json");
            ArrayList<ship> list = FileIOUtil.ReadShipFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals(ship)){
                    list.set(i, ship);
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);

        }
        else if (vehicle.getVid().startsWith("BT")){
            basic_truck basicTruck = (basic_truck) vehicle;
            File file = new File("BasicTruck.json");
            ArrayList<basic_truck> list = FileIOUtil.ReadBasicTruckFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals(basicTruck)){
                    list.set(i, basicTruck);
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (vehicle.getVid().startsWith("RT")){
            reefer_truck reeferTruck = (reefer_truck) vehicle;
            File file = new File("ReeferTruck.json");
            ArrayList<reefer_truck> list = FileIOUtil.ReadReeferFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals(reeferTruck)){
                    list.set(i, reeferTruck);
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (vehicle.getVid().startsWith("TT")){
            tanker_truck tankerTruck = (tanker_truck) vehicle;
            File file = new File("TankerTruck.json");
            ArrayList<tanker_truck> list = FileIOUtil.ReadTankerFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals(tankerTruck)){
                    list.set(i, tankerTruck);
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }

    }
    public static void updateContainerFromFile(Object object) throws IOException {
        if (object instanceof Dry_Storage){
            File file = new File("DryStorage.json");
            ArrayList<Dry_Storage> list = FileIOUtil.ReadDryStorageFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Dry_Storage) object)){
                    list.set(i, (Dry_Storage) object);
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);

        }
        else if (object instanceof Open_Top){
            File file = new File("OpenTop.json");
            ArrayList<Open_Top> list = FileIOUtil.ReadOpen_TopFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Open_Top) object)){
                    list.set(i, (Open_Top) object);
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Open_Side){
            File file = new File("OpenSide.json");
            ArrayList<Open_Side> list = FileIOUtil.ReadOpen_SideFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Open_Side) object)){
                    list.set(i, (Open_Side) object);
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Refriderated){
            File file = new File("Refrigerated.json");
            ArrayList<Refriderated> list = FileIOUtil.ReadRefridgeratedFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Refriderated) object)){
                    list.set(i, (Refriderated) object);
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else {
            File file = new File("Liquid.json");
            ArrayList<Liquid> list = FileIOUtil.ReadLiquidFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Liquid) object)){
                    list.set(i, (Liquid) object);
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }

    }

//----------------------------------------------Remove Methods-----------------------------------------------//

    public static void removePortFromFile(Port port) throws IOException {
        File file = new File("Port.json");
        ArrayList<Port> list = FileIOUtil.ReadPortFromFile();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(port)){
                list.remove(list.get(i));
                break;
            }
        }
        file.delete();
        file.createNewFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);

    }

    public static void removeManagerFromFile(Port_Manager manager) throws IOException {
        File file = new File("PortManager.json");
        ArrayList<Port_Manager> list = FileIOUtil.ReadManagerFromFile();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(manager)){
                list.remove(manager);
                break;
            }
        }

        file.delete();
        file.createNewFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);

    }

    public static void removeAdminFromFile(System_Admin admin) throws IOException {
        File file = new File("SystemAdmin.json");
        ArrayList<System_Admin> list = FileIOUtil.ReadAdminFromFile();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(admin)){
                list.remove(admin);
                break;
            }
        }

        file.delete();
        file.createNewFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);

    }

    public static void removeTripFromFile(Trip trip) throws IOException {
        File file = new File("Port.json");
        ArrayList<Trip> list = FileIOUtil.ReadTripFromFile();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(trip)){
                list.remove(list.get(i));
                break;
            }
        }
        file.delete();
        file.createNewFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, list);

    }

    public static void removeVehicleFromFile(Object object) throws IOException {
        if (object instanceof ship){
            File file = new File("Ship.json");
            ArrayList<ship> list = FileIOUtil.ReadShipFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((ship) object)){
                    list.remove(list.get(i));
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);

        }
        else if (object instanceof basic_truck){
            File file = new File("BasicTruck.json");
            ArrayList<basic_truck> list = FileIOUtil.ReadBasicTruckFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((basic_truck) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof reefer_truck){
            File file = new File("ReeferTruck.json");
            ArrayList<reefer_truck> list = FileIOUtil.ReadReeferFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((reefer_truck) object)){
                    list.remove(list.get(i));
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof tanker_truck){
            File file = new File("TankerTruck.json");
            ArrayList<tanker_truck> list = FileIOUtil.ReadTankerFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((tanker_truck) object)){
                    list.remove(list.get(i));
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }

    }

    public static void removeContainerFromFile(Object object) throws IOException {
        if (object instanceof Dry_Storage){
            File file = new File("DryStorage.json");
            ArrayList<Dry_Storage> list = FileIOUtil.ReadDryStorageFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Dry_Storage) object)){
                    list.remove(list.get(i));
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);

        }
        else if (object instanceof Open_Top){
            File file = new File("OpenTop.json");
            ArrayList<Open_Top> list = FileIOUtil.ReadOpen_TopFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Open_Top) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Open_Side){
            File file = new File("OpenSide.json");
            ArrayList<Open_Side> list = FileIOUtil.ReadOpen_SideFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Open_Side) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Refriderated){
            File file = new File("Refrigerated.json");
            ArrayList<Refriderated> list = FileIOUtil.ReadRefridgeratedFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Refriderated) object)){
                    list.remove(list.get(i));
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else {
            File file = new File("Liquid.json");
            ArrayList<Liquid> list = FileIOUtil.ReadLiquidFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Liquid) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }

    }

}






