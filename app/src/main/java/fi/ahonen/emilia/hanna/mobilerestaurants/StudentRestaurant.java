package fi.ahonen.emilia.hanna.mobilerestaurants;

/**
 * Created by Hanna on 25.11.2017.
 */

public class StudentRestaurant {

    private String name;
    private String address;
    private double lat;
    private double lon;
    private String rssUrl;

    public StudentRestaurant(String name, String address, double lat, double lon, String rssUrl){
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.rssUrl = rssUrl;
    }

    public String getName(){return name;}

    public String getAddress(){
            return address;
        }

    public double getLat(){
            return lat;
        }

    public double getLon(){
            return lon;
        }

    public String getUrl() {return rssUrl;}
}
