import datamodel.parkingSlot;
import manager.parkingSlotManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

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
                Collection<parkingSlot> parkingSlots = parkingSlotManager.getNearestSlots(0, 0, spotData.getInt("amount"), spotData.getInt("status"), spotData.getInt("distance"));
                returnObject.put("data", "slots");
                JSONArray spotsArray = new JSONArray();

                for(parkingSlot tmpslot : parkingSlots){
                    JSONObject spotObject = new JSONObject();
                    spotObject.put("longitude", tmpslot.getLongitude());
                    spotObject.put("latitude", tmpslot.getLatitude());
                    spotObject.put("status", tmpslot.getStatus());
                    spotsArray.put(spotObject);
                }
                returnObject.put("slots", spotsArray);
                client.write(returnObject.toString());
                break;


        }

    }
}

