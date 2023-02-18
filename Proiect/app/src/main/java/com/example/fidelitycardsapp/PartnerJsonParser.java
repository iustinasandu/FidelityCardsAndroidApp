package com.example.fidelitycardsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PartnerJsonParser {

    public static final String PARTNER_NAME = "partnerName";
    public static final String ADDRESS = "address";
    public static final String CUI = "CUI";
    //public static final String ID = "ID";

    public static List<Partner> fromJson(String json){
        try {
            JSONArray array = new JSONArray(json);
            return readPartnersFromJson(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Partner> readPartnersFromJson(JSONArray array) throws JSONException {
        List<Partner> results = new ArrayList<>();
        for(int i=0; i<array.length(); i++){
            JSONObject object = array.getJSONObject(i);
            Partner partner = readPartnerFromJson(object);
            results.add(partner);
        }
        return results;
    }

    private static Partner readPartnerFromJson(JSONObject object) throws JSONException {
        //long id = object.getLong(ID);
        String partnerName = object.getString(PARTNER_NAME);
        String address = object.getString(ADDRESS);
        String CUI = object.getString(PartnerJsonParser.CUI);

        return new Partner(partnerName, address, CUI);
    }
}
