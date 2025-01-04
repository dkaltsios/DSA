public class Airport {
    private String name;
    private String code;
    private String location;
    private String country;
    private int GMT;

    Airport(String name, String code, String location, String country, int GMT) {
        this.name = name;
        this.code = code;
        this.location = location;
        this.country = country;
        this.GMT = GMT;
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
    public String getCountry() {
        return country;
    }
    public int getGMT() {
        return GMT;
    }

    
}
