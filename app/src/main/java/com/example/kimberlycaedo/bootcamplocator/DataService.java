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
        bootCamps.add(new Devslopes( 10.3217381f , 123.8998818f, "University of SanJose", "Cebu City", "img"));
        bootCamps.add(new Devslopes( 10.3196824f,123.8998121f, "Pasil Suba", "Cebu City", "img"));
        bootCamps.add(new Devslopes( 10.3186325f,123.9004658f, "Gaisano South", "Cebu City", "img"));
        return bootCamps;
    }


}


