package com.momenalhendawy.myway;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;

public class Sendtouserlocation {

    private GeoPoint geo_point;
    private Timestamp Srart_Time ;

    public GeoPoint getGeo_point() {
        return geo_point;
    }

    public void setGeo_point(GeoPoint geo_point) {
        this.geo_point = geo_point;
    }

    public Timestamp getSrart_Time() {
        return Srart_Time;
    }

    public void setSrart_Time(Timestamp srart_Time) {
        Srart_Time = srart_Time;
    }
}
