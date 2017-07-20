/**
 * Created by lucas on 14.07.2017.
 */
public class parkingSlot {
    private double longitude;
    private double latitude;
    private byte status;

    //const
    public static final byte SLOT_TAKEN = 1;
    public static final byte SLOT_FREE = 2;
    public static final byte SLOT_RESERVED = 3;

    public parkingSlot(double longitude, double latitutde, byte status) {
        this.longitude = longitude;
        this.latitude = latitutde;
        this.status = status;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void saveSlotToDatabase() {

    }
}
