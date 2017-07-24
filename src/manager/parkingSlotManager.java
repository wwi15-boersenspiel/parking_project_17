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

    //TODO: Add toJSON() Funktion??
    public static Collection<parkingSlot> getNearestSlots(float longitude, float latitude, int amount, int status, int distance) {

        System.out.println("get nearest parking slot");

        Collection<parkingSlot> parkingSlots = new ArrayList<>();



        // JOIN Nötig da Status in anderer Tabelle ist

        Connection con = null;

        //Todo: Try with resources
        try {
            con = getConnection();

            //TODO: Statement anpassen
            PreparedStatement ps = con.prepareStatement(" Select  psl.id, psl.latitude, psl.longitude, pst.status, pst.time, SQRT( "+
                           " POW(69.1 * (psl.latitude - ?), 2) + " +
                                 "   POW(69.1 * (? - psl.longitude) * COS(psl.latitude / 57.3), 2)) AS distance "+
                  "  From parking_project.parking_spot psl "+
                  "  INNER JOIN parking_status pst on psl.id = pst.sensor_id "+
                   " WHERE pst.status <= ? "+
                    " ORDER BY psl.id desc "+
                    " LIMIT 3"
            );


            ps.setDouble(1, latitude);
            ps.setDouble(2, longitude);
            ps.setInt(3, status);



            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                parkingSlot tmpParkingSlot = new parkingSlot(rs.getDouble("longitude"), rs.getDouble("latitude"), rs.getInt("status"));
                parkingSlots.add(tmpParkingSlot);
            }

            return parkingSlots;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
