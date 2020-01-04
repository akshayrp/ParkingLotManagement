package parkingLot;

public class AssignLot {
    ParkingStrategy strategy = new ParkingStrategy();

    public ParkingLot getLot(Enum parkingBasedOn) throws ParkingLotException {
        if (parkingBasedOn.equals(DriverType.HANDICAP_DRIVER))
            return strategy.getLotForHandicapDriver();
        else if (parkingBasedOn.equals(Vehicle.LARGE))
            return strategy.getLotForLargeVehicle();
        else
            return strategy.getLotForNormalDriver();
    }
}
