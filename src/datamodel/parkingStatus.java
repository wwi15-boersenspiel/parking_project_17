package datamodel;

/**
 * Created by Surfen on 21.07.2017.
 */
public class parkingStatus {
    private double sensor_id;
    private int time;
    private int status;
    public final static String TABLENAME = "parking_status";

    public parkingStatus(double sensor_id, int time, int status) {
        this.sensor_id = sensor_id;
        this.time = time;
        this.status = status;
    }

    public double getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(double sensor_id) {
        this.sensor_id = sensor_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
