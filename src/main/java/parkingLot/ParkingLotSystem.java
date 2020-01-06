package parkingLot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLotSystem {
    public static List<ParkingLot> parkingLotsList;
    public ParkingLot parkingLot;
    public AssignLot assignLot;

    public ParkingLotSystem() {
        parkingLotsList = new ArrayList<>();
        this.assignLot = new AssignLot();
    }

    public void setMockObject(AssignLot assignLot,ParkingLot parkingLot) {
        this.assignLot = assignLot;
        this.parkingLot = parkingLot;
    }

    public void createParkingLot(int slotCapacity, int numberOfLots) {
        IntStream.range(0, numberOfLots).forEach(lot
                -> parkingLotsList.add(new ParkingLot(lot,slotCapacity)));
    }

    public void parkVehicle(Vehicle vehicle,int slotNumber) throws ParkingLotException {
        parkingLot.parkVehicle(vehicle,slotNumber);
    }

    public void parkVehicle(Enum parkingStrategy,Vehicle vehicle) throws ParkingLotException {
        this.parkingLot = assignLot.getLot(parkingStrategy);
        this.parkingLot.parkVehicle(vehicle);
    }

    public void unParkVehicle(Vehicle vehicle) throws ParkingLotException {
        getParkedVehicleLot(vehicle).unParkVehicle(vehicle);
    }


    public ParkingLot getParkedVehicleLot(Vehicle vehicle) throws ParkingLotException {
        return parkingLotsList.stream()
                .filter(parkingLot -> parkingLot.isVehiclePresent(vehicle)).findFirst().orElseThrow(() -> new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "No vehicle present"));
    }

    public ArrayList<ArrayList<Integer>> getLocationByColor(String findBy) {
        return this.parkingLotsList.stream().map(parkingLot ->
                parkingLot.getLocationByColor(findBy)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<ArrayList<Vehicle>> getLocationByColorAndModel(String findByColor, String findByVehicleModel) {
        return this.parkingLotsList.stream().map(parkingLot ->
                parkingLot.getLocationByColorAndModel(findByColor, findByVehicleModel)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<ArrayList<Vehicle>> getLocationByModel(String findByVehicleModel) {
        return this.parkingLotsList.stream().map(parkingLot ->
                parkingLot.getVehicleByModel(findByVehicleModel)).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<ArrayList<Vehicle>> getLocationOfVehicleByTime(int findByTime) {
        return this.parkingLotsList.stream().map(parkingLot ->
                parkingLot.getVehicleByTime(findByTime)).collect(Collectors.toCollection(ArrayList::new));
    }
}

