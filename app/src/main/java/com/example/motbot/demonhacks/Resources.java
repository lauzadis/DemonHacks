import com.example.motbot.demonhacks.*;
public class Resources {
    private double multiplier;
    private GeoCoordinate geo = GeoCoordinate(0,0,0);
    private double distance;
    public final double WALK_MULTIPLIER = 2.5;
    public final double BIKE_MULTIPLIER = 1.5;
    public final double CAR_MULTIPLIER = .75;

    //Constructor
    public Resources() {
        this.multiplier = WALK_MULTIPLIER;
    }

    //Getters and Setters

    public double getMultiplier() {
        return multiplier;
    }

    public GeoCoordinate getGeo() {
        return geo;
    }

    public void setGeo(GeoCoordinate geo) {
        this.geo = geo;
    }

    public double getDistance() {
        return distance;
    }


    public void setDistance(double distance) {
        this.distance = distance;
    }


    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
    //Methods
    public int calculate() {
        int random = (int) Math.ceil(Math.random()*100);
        int rmult = 1;
        if(random < 5) {
            rmult = 5;
        } else if(random < 15) {
            rmult = 3;
        }
        int amount = rmult*(int)getMultiplier()*((int) getDistance() + (int) getDistance()* ((int) (Math.random()*10)));
        return amount;
    }
}
