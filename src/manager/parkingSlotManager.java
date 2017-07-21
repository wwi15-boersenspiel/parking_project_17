package manager;

import datamodel.parkingSlot;
import datamodel.parkingStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.toRadians;
import static java.lang.StrictMath.asin;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.toDegrees;

/**
 * Created by Surfen on 20.07.2017.
 */
public class parkingSlotManager extends Manager {

    private Connection conn;

    public static Collection<parkingSlot> getNearestSlots(float longitude, float latitude, int amount, int status, int distance) {

        Collection<parkingSlot> parkingSlots = new ArrayList<>();
        int RADIUS_EARTH = 6371;
        double maxLat = latitude + toDegrees(distance / RADIUS_EARTH);
        double minLat = latitude - toDegrees(distance / RADIUS_EARTH);
        double maxLon = longitude + toDegrees(asin(distance / RADIUS_EARTH) / cos(toRadians(latitude)));
        double minLon = longitude - toDegrees(asin(distance / RADIUS_EARTH) / cos(toRadians(latitude)));

        // JOIN Nötig da Status in anderer Tabelle ist
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("Select Id, latitude, longitude, status " +
                     "From " + parkingSlot.TABLENAME + ", " + parkingStatus.TABLENAME + " " +
                     "INNER JOIN parking_status on parking_slot.id = parking_status.sensor_id " +
                     "Where latitude Between ? " +
                     "And ? " +
                     "And Lon Between ? And ?" +
                     "And status <= ?" +
                     "Group By parking_status.sensor_id" +
                     "Order By parking_status.time" +
                     "LIMIT ?");
             ResultSet rs = ps.executeQuery()) {

            ps.setDouble(1, minLat);
            ps.setDouble(2, maxLat);
            ps.setDouble(3, minLon);
            ps.setDouble(4, maxLon);
            ps.setInt(5, status);
            ps.setInt(6, amount);

            while (rs.next()) {
                parkingSlot tmpParlingSlot = new parkingSlot(rs.getDouble("longitude"), rs.getDouble("latitude"), rs.getInt("status"));
                parkingSlots.add(tmpParlingSlot);
            }

            return parkingSlots;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
