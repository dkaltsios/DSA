public class Airport {
    private String name;
    private String code;
    private String location;
    private float GMT;
    private float latitude;
    private float longitude;

    Airport(String name, String code, String location, float GMT, float latitude, float longitude) {
        this.name = name;
        this.code = code;
        this.location = location;
        this.GMT = GMT;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public String getLocation() {
        return location;
    }
    public float getGMT() {
        return GMT;
    }
    public float getLatitude() {
        return latitude;
    }
    public float getLongitude() {
        return longitude;
    }
    public String toString() {
        return "Airport{ " + name + "\nCode: " + code + "\nLocation: " + location + "\nGMT: " + GMT + "\nLatitude: " + latitude + "\nLongitude: " + longitude + "}";
    }
}
