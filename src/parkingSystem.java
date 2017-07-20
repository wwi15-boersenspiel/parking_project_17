import static java.lang.Math.*;

/**
 * Created by lucas on 14.07.2017.
 */
public class parkingSystem {

    //const
    public static final byte ONLY_FREE_SLOTS = 1;
    public static final byte FREE_AND_RESERVED_SLOTS = 2;
    public static final byte ALL_SLOTS = 3;

    private static final int RADIUS_EARTH = 6371;

    //Returns nearest parking slots in a certain distance depending on the given coordinates
    public static parkingSlot[] getNearestSlots(float longitude, float latitude, int amount, int status, int distance) {

        
       /* double maxLat = latitude + toDegrees(distance / RADIUS_EARTH);
        double minLat = latitude - toDegrees(distance / RADIUS_EARTH);
        double maxLon = longitude + toDegrees(asin(distance / RADIUS_EARTH) / cos(toRadians(latitude)));
        double minLon = longitude - toDegrees(asin(distance / RADIUS_EARTH) / cos(toRadians(latitude)));

        String query;


        query = "Select Id, Postcode, Lat, Lon " +
                "From MyTable " +
                "Where Lat Between :minLat " +
                "And :maxLat " +
                "And Lon Between :minLon And :maxLon" +
                "And Status <= :status" +
                "LIMIT :amount";
                */

        parkingSlot parkingSlots[] = new parkingSlot[3];
        parkingSlots[0] = new parkingSlot(48.689396, 10.161095, parkingSlot.SLOT_FREE);
        parkingSlots[1] = new parkingSlot(48.683281, 10.156654, parkingSlot.SLOT_FREE);
        parkingSlots[2] = new parkingSlot(48.677827, 10.154015, parkingSlot.SLOT_FREE);


        return parkingSlots;
    }


    //Sets the Status of a Parking Slot to reserved
    public static boolean reserveParkingSlot(float longitude, float latitude) {
        return true;
    }
}
