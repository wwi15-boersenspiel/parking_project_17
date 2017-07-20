import datamodel.parkingSlot;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by lucas on 14.07.2017.
 */


public class API {

    public static void processCall(String data, socketClient client) {
        //Switch Case depending on what was called

        JSONObject dataObject = new JSONObject(data);

        JSONObject returnObject = new JSONObject();

        switch (dataObject.getString("data")) {
            case "spot":
                JSONObject spotData = dataObject.getJSONObject("spot_data");
                parkingSlot parkingSlots[] = parkingSystem.getNearestSlots(0, 0, spotData.getInt("amount"), spotData.getInt("status"), spotData.getInt("distance"));
                returnObject.put("data", "slots");
                JSONArray spotsArray = new JSONArray();

              for (int i = 0; i < parkingSlots.length; i++) {
                    JSONObject spotObject = new JSONObject();
                    spotObject.put("longitude", parkingSlots[i].getLongitude());
                    spotObject.put("latitude", parkingSlots[i].getLatitude());
                    spotObject.put("status", parkingSlots[i].getStatus());
                    spotsArray.put(spotObject);
                }
                returnObject.put("slots", spotsArray);
                client.write(returnObject.toString());
                break;


        }

    }
}

