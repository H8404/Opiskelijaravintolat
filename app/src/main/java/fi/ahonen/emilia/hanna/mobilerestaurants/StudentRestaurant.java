package fi.ahonen.emilia.hanna.mobilerestaurants;

/**
 * Created by Hanna on 25.11.2017.
 */

public class StudentRestaurant {

    private String address;
    private double lat;
    private double lon;

    public StudentRestaurant(String address, double lat, double lon){
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    public String getAddress(){
            return address;
        }

    public double getLat(){
            return lat;
        }

    public double getLon(){
            return lon;
        }
}
