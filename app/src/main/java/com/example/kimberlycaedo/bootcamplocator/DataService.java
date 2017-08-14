package com.example.kimberlycaedo.bootcamplocator;

import java.util.ArrayList;

/**
 * Created by Kimberly Cañedo on 14/08/2017.
 */

public class DataService {
    private static final DataService INSTANCE = new DataService();

    public static DataService getInstance() {
        return INSTANCE;
    }

    public DataService() {
    }

    public ArrayList<Devslopes> getNearBootCampLocations(int zipcode){
        ArrayList<Devslopes> bootCamps =new ArrayList<>();
        bootCamps.add(new Devslopes( 10.3217381f , 123.8998818f, "Valentino Private Pool", "Lapu-Lapu City", "img"));
        bootCamps.add(new Devslopes( 10.3196824f,123.8998121f, "Sala Piano Museum", "Lapu-Lapu City", "img"));
        bootCamps.add(new Devslopes( 10.3186325f,123.9004658f, "Golden Peak Hotel", "Lapu-Lapu City", "img"));
        return bootCamps;
    }


}


