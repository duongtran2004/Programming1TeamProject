import FileIO.FileIOUtil;
import Trip.Trip;
import Users.Port_Manager;
import Users.System_Admin;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;



public class Menu {

    public static void automaticTripDeletion() throws IOException {
        Instant current_Date = Instant.now();
        Instant before = current_Date.minus(Duration.ofDays(6));
        Date dateToCheckAgainst = Date.from(before);

        for (Trip trip: Trip.getTrip()){
            if (trip.getDate_of_arrival().before(dateToCheckAgainst)){
                FileIOUtil.removeTripFromFile(trip);
            }

        }
    }


    public static boolean program_Run() throws IOException {
        automaticTripDeletion();
        WelcomeScreen.displayWelcomeScreen();
        File file = new File("Trip.json");
        if (file.length() != 0){
            automaticTripDeletion();
        }
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Who do you want to login as?\na: Port Manager\nb: System Admin\nq:quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("a")){
                Port_Manager port_manager = Port_Manager.login();
                if (port_manager == null){
                    return false;
                }
                Portmanager_run(port_manager, false);

            }
            else if (selection.equalsIgnoreCase("b")){
                if(System_Admin.login() == null){
                    return false;
                }
                Admin_run(false);
            }
            else if (selection.equalsIgnoreCase("q")){
                return false;
            }
            else{
                System.out.println("Option does not exist. Please try again");
            }
        }
    }
    public static boolean Admin_run(boolean _exit) throws IOException {
        if (_exit) {
            System.out.println("Quiting application");
            return true;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do?\na: add Manager\nb: add Port\nc: add Vehicle\nd: add Container\ne: view manager\nf: view ports\ng: view vehicles\nh: view containers\ni: update manager info\nj: update port info\nk: update vehicle info\nl: update container info\nm: remove manager\nn: remove port\no: remove vehicle\np: remove container\ns: view trip\nt: add System Admin\nu: view System Admin\nv: update System Admin\nw: remove System Admin\nz: To check oil consumption\nq: quit");
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("a")) {
            File port_file = new File("Port.json");
            if (port_file.length() == 0){
                System.out.println("Please create a port first");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.addManager();
                if (!quit){
                    return Admin_run(false);
                }
                while (true) {
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")) {
                        return Admin_run(false);
                    } else if (_quit.equalsIgnoreCase("q")) {
                        return Admin_run(true);
                    } else {
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }


        } else if (selection.equalsIgnoreCase("b")) {
            boolean quit = System_Admin.addPort();
            if (!quit){
                return Admin_run(false);
            }
            else {
                while (true) {
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")) {
                        return Admin_run(false);
                    } else if (_quit.equalsIgnoreCase("q")) {
                        return Admin_run(true);
                    } else {
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }


        }

        else if (selection.equalsIgnoreCase("c")) {
            File port_file = new File("Port.json");
            if (port_file.length() == 0){
                System.out.println("Please create a port first");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.addVehicle();
                if (!quit){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }



        }

        else if (selection.equalsIgnoreCase("d")) {
            File port_file = new File("Port.json");
            if (port_file.length() == 0){
                System.out.println("Please create a port first");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.addContainer();
                if (!quit){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }


        }

        else if (selection.equalsIgnoreCase("e")) {
            File file = new File("PortManager.json");
            if (file.length()==0){
                System.out.println("Please create a manager first");
                return Admin_run(false);
            }
            else {
                System_Admin.viewManager();
                while (true) {
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")) {
                        return Admin_run(false);
                    } else if (_quit.equalsIgnoreCase("q")) {
                        return Admin_run(true);
                    } else {
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }

        }

        else if (selection.equalsIgnoreCase("f")) {
            File port_file = new File("Port.json");
            if (port_file.length() == 0){
                System.out.println("Please create a port first");
                return Admin_run(false);
            }
            else {
                if (!System_Admin.viewPort()){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }

            }

        }
        else if (selection.equalsIgnoreCase("g")) {
            File file_ship = new File("Ship.json");
            File file_basic_Truck = new File("BasicTruck.json");
            File file_reefer = new File("ReeferTruck.json");
            File file_tanker = new File("TankerTruck.json");

            if (file_ship.length() == 0 && file_reefer.length() ==0 && file_tanker.length() == 0 && file_basic_Truck.length() ==0){
                System.out.println("No vehicle currently exists. Please create some");
                return Admin_run(false);

            }
            else {
                if (!System_Admin.viewVehicle()){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }

            }



        }

        else if (selection.equalsIgnoreCase("h")) {
            File file_DS = new File("DryStorage.json");
            File file_OT = new File("OpenTop.json");
            File file_OS = new File("OpenSide.json");
            File file_RE = new File("Refrigerated.json");
            File file_LI = new File("Liquid.json");

            if (file_DS.length() == 0 && file_OT.length() ==0 && file_OS.length() == 0 && file_RE.length() ==0 && file_LI.length() == 0){
                System.out.println("No container currently exists. Please create some");
                return Admin_run(false);

            }
            else {
                if (!System_Admin.viewContainers()){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }


            }

        }

        else if (selection.equalsIgnoreCase("i")) {
            File file = new File("PortManager.json");
            if (file.length()==0){
                System.out.println("Please create a manager first");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.updateManager();
                if (!quit){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }

        }

        else if (selection.equalsIgnoreCase("j")) {
            File port_file = new File("Port.json");
            if (port_file.length() == 0){
                System.out.println("Please create a port first");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.updatePort();
                if (!quit){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }



        }

        else if (selection.equalsIgnoreCase("k")) {
            File file_ship = new File("Ship.json");
            File file_basic_Truck = new File("BasicTruck.json");
            File file_reefer = new File("ReeferTruck.json");
            File file_tanker = new File("TankerTruck.json");

            if (file_ship.length() == 0 && file_reefer.length() ==0 && file_tanker.length() == 0 && file_basic_Truck.length() ==0){
                System.out.println("No vehicle currently exists. Please create some");
                return Admin_run(false);

            }
            else {
                boolean quit = System_Admin.updateVehicle();
                if (!quit){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }
        }

        else if (selection.equalsIgnoreCase("l")) {
            File file_DS = new File("DryStorage.json");
            File file_OT = new File("OpenTop.json");
            File file_OS = new File("OpenSide.json");
            File file_RE = new File("Refrigerated.json");
            File file_LI = new File("Liquid.json");

            if (file_DS.length() == 0 && file_OT.length() ==0 && file_OS.length() == 0 && file_RE.length() ==0 && file_LI.length() == 0){
                System.out.println("No container currently exists. Please create some");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.updateContainer();
                if (!quit){
                    return Admin_run(false);
                }
                else{
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }
        }

        else if (selection.equalsIgnoreCase("m")) {
            File file = new File("PortManager.json");
            if (file.length()==0){
                System.out.println("Please create a manager first");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.removeManager();
                if (!quit){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }
        }


        else if (selection.equalsIgnoreCase("n")) {
            File port_file = new File("Port.json");
            if (port_file.length() == 0){
                System.out.println("Please create a port first");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.removePort();
                if (!quit){
                    return Admin_run(false);
                }
                else{
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }

            }


        }

        else if (selection.equalsIgnoreCase("o")) {
            File file_ship = new File("Ship.json");
            File file_basic_Truck = new File("BasicTruck.json");
            File file_reefer = new File("ReeferTruck.json");
            File file_tanker = new File("TankerTruck.json");

            if (file_ship.length() == 0 && file_reefer.length() ==0 && file_tanker.length() == 0 && file_basic_Truck.length() ==0){
                System.out.println("No vehicle currently exists. Please create some");
                return Admin_run(false);

            }
            else {
                boolean quit = System_Admin.removeVehicle();
                if (!quit){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }
        }

        else if (selection.equalsIgnoreCase("p")) {
            File file_DS = new File("DryStorage.json");
            File file_OT = new File("OpenTop.json");
            File file_OS = new File("OpenSide.json");
            File file_RE = new File("Refrigerated.json");
            File file_LI = new File("Liquid.json");

            if (file_DS.length() == 0 && file_OT.length() ==0 && file_OS.length() == 0 && file_RE.length() ==0 && file_LI.length() == 0){
                System.out.println("No container currently exists. Please create some");
                return Admin_run(false);
            }
            else {
                boolean quit = System_Admin.removeContainer();
                if (!quit){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }



        }
        else if(selection.equalsIgnoreCase("s")){
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("No trip exists");
                return Admin_run(false);

            }
            else {
                System_Admin.viewAllTrips();
                while (true) {
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")) {
                        return Admin_run(false);
                    } else if (_quit.equalsIgnoreCase("q")) {
                        return Admin_run(true);
                    } else {
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }




        }
        else if(selection.equalsIgnoreCase("w")){
            boolean quit = System_Admin.removeSystem_Admin();
            if (!quit){
                return Admin_run(false);
            }
            else {
                while (true) {
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")) {
                        return Admin_run(false);
                    } else if (_quit.equalsIgnoreCase("q")) {
                        return Admin_run(true);
                    } else {
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }


        }
        else if(selection.equalsIgnoreCase("t")){
            boolean quit = System_Admin.addSystemAdmin();
            if (!quit){
                return Admin_run(false);
            }
            else {
                while (true) {
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")) {
                        return Admin_run(false);
                    } else if (_quit.equalsIgnoreCase("q")) {
                        return Admin_run(true);
                    } else {
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }


        }
        else if(selection.equalsIgnoreCase("u")){
            System_Admin.viewAdmin();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if(selection.equalsIgnoreCase("v")){
            boolean quit = System_Admin.updateAdmin();
            if (!quit){
                return Admin_run(false);

            }
            else {
                while (true) {
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")) {
                        return Admin_run(false);
                    } else if (_quit.equalsIgnoreCase("q")) {
                        return Admin_run(true);
                    } else {
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }


        }
        else if (selection.equalsIgnoreCase("z")){
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("A trip must be created first");
                return Admin_run(false);
            }
            else {
                double oil_consumption = System_Admin.oilConsumtion();
                if (oil_consumption < 0){
                    return Admin_run(false);
                }
                else {
                    while (true) {
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")) {
                            return Admin_run(false);
                        } else if (_quit.equalsIgnoreCase("q")) {
                            return Admin_run(true);
                        } else {
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }
        }
        else if (selection.equalsIgnoreCase("q")){
            return Admin_run(true);
        }
        else {
            System.out.println("Option does not exist.");
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
    }






    public static boolean Portmanager_run(Port_Manager manager, boolean _exit) throws IOException {
        if (_exit){
            System.out.println("Quiting application");
            return true;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do?\na: Commence Trip\nb: Complete Trip\nc: Loading Containers on Vehicle\nd: Loading Containers off Vehicle\ne: Incoming Trips Query\nf: Outgoing Trips Query\ng: Registering Trips\nt: update trip\nu: remove trip\nz: view onsite veihcles\nv: view onsite containers\nw: get port info\nq: quit" );
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("a")){
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("Please create a trip first");
                Portmanager_run(manager, false);
            }
            else {
                boolean quit = manager.commenceTrip();
                if (!quit){
                    return Portmanager_run(manager, false);
                }
                else {
                    while (true){
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")){
                            return Portmanager_run(manager, false);
                        }
                        else if (_quit.equalsIgnoreCase("q")) {
                            return Portmanager_run(manager, true);
                        }
                        else{
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }



        }
        else if (selection.equalsIgnoreCase("b")){
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("Please create a trip first");
                Portmanager_run(manager, false);
            }
            else {
                boolean quit = manager.completeTrip();;
                if (!quit){
                    return Portmanager_run(manager, false);
                }
                else {
                    while (true){
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")){
                            return Portmanager_run(manager, false);
                        }
                        else if (_quit.equalsIgnoreCase("q")) {
                            return Portmanager_run(manager, true);
                        }
                        else{
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }



        }
        else if (selection.equalsIgnoreCase("c")){
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("Please create a trip first");
                Portmanager_run(manager, false);
            }
            else {
                boolean quit = manager.loadingContainersonVehicle();
                if (!quit){
                    return Portmanager_run(manager, false);
                }
                else {
                    while (true){
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")){
                            return Portmanager_run(manager, false);
                        }
                        else if (_quit.equalsIgnoreCase("q")) {
                            return Portmanager_run(manager, true);
                        }
                        else{
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }



        }
        else if (selection.equalsIgnoreCase("d")){
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("Please create a trip first");
                Portmanager_run(manager, false);
            }
            else {
                boolean quit = manager.loadingContaineroffVehicle();
                if (!quit){
                    return Portmanager_run(manager, false);
                }
                else {
                    while (true){
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")){
                            return Portmanager_run(manager, false);
                        }
                        else if (_quit.equalsIgnoreCase("q")) {
                            return Portmanager_run(manager, true);
                        }
                        else{
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }



        }
        else if (selection.equalsIgnoreCase("e")){
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("Please create a trip first");
                Portmanager_run(manager, false);
            }
            else {
                boolean quit = manager.incomingTrip_query();
                if (!quit){
                    return Portmanager_run(manager, false);
                }
                else {
                    while (true){
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")){
                            return Portmanager_run(manager, false);
                        }
                        else if (_quit.equalsIgnoreCase("q")) {
                            return Portmanager_run(manager, true);
                        }
                        else{
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }


        }
        else if (selection.equalsIgnoreCase("f")) {
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("Please create a trip first");
                Portmanager_run(manager, false);
            }
            else {
                boolean quit = manager.outgoingTrip_query();
                if (!quit){
                    return Portmanager_run(manager, false);
                }
                else {
                    while (true){
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")){
                            return Portmanager_run(manager, false);
                        }
                        else if (_quit.equalsIgnoreCase("q")) {
                            return Portmanager_run(manager, true);
                        }
                        else{
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }
        }
        else if (selection.equalsIgnoreCase("g")) {
            boolean quit = manager.RegisteringTrip();
            if (!quit){
                return Portmanager_run(manager, false);
            }
            else {
                while (true){
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")){
                        return Portmanager_run(manager, false);
                    }
                    else if (_quit.equalsIgnoreCase("q")) {
                        return Portmanager_run(manager, true);
                    }
                    else{
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }
        }

        else if (selection.equalsIgnoreCase("t")) {
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("Please create a trip first");
                Portmanager_run(manager, false);
            }
            else {
                boolean quit = manager.updateTrip();
                if (!quit){
                    return Portmanager_run(manager, false);
                }
                else {
                    while (true){
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")){
                            return Portmanager_run(manager, false);
                        }
                        else if (_quit.equalsIgnoreCase("q")) {
                            return Portmanager_run(manager, true);
                        }
                        else{
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }
            }



        }

        else if (selection.equalsIgnoreCase("u")) {
            File file = new File("Trip.json");
            if (file.length() == 0){
                System.out.println("Please create a trip first");
                Portmanager_run(manager, false);
            }
            else {
                boolean quit = manager.removeTrip();;
                if (!quit){
                    return Portmanager_run(manager, false);
                }
                else {
                    while (true){
                        System.out.println("Press r to return to the menu and q to quit the application");
                        String _quit = scanner.nextLine();
                        if (_quit.equalsIgnoreCase("r")){
                            return Portmanager_run(manager, false);
                        }
                        else if (_quit.equalsIgnoreCase("q")) {
                            return Portmanager_run(manager, true);
                        }
                        else{
                            System.out.println("Option does not exit. Please choose again");
                        }
                    }
                }

            }
        }
        else if (selection.equalsIgnoreCase("z")) {
            boolean quit = manager.viewVehicleonSite();
            if (!quit){
                return Portmanager_run(manager, false);
            }
            else {
                while (true){
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")){
                        return Portmanager_run(manager, false);
                    }
                    else if (_quit.equalsIgnoreCase("q")) {
                        return Portmanager_run(manager, true);
                    }
                    else{
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }

        }
        else if (selection.equalsIgnoreCase("v")) {
            boolean quit = manager.viewContainersonSite();
            if (!quit){
                return Portmanager_run(manager, false);
            }
            else {
                while (true){
                    System.out.println("Press r to return to the menu and q to quit the application");
                    String _quit = scanner.nextLine();
                    if (_quit.equalsIgnoreCase("r")){
                        return Portmanager_run(manager, false);
                    }
                    else if (_quit.equalsIgnoreCase("q")) {
                        return Portmanager_run(manager, true);
                    }
                    else{
                        System.out.println("Option does not exit. Please choose again");
                    }
                }
            }

        }
        else if (selection.equalsIgnoreCase("w")){
            System.out.println(manager.viewPortInfo());
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        }



        else if (selection.equalsIgnoreCase("q")) {
            return Portmanager_run(manager, true);

        }
        else{
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        }

        return Portmanager_run(manager, true);

    }
}
