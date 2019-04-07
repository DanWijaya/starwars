package starwars.coding.com.ParkLah.Entity;

public class Review {
    private String userName;
    private String carparkID;
    private double rating;
    private String text;

    public Review(String userName, String carparkID, double rating, String text) {
        this.userName = userName;
        this.carparkID = carparkID;
        this.rating = rating;
        this.text = text;
    }

    public Review(){
        
    }


    public String getCarparkID() {
        return carparkID;
    }

    public void setCarparkID(String carparkID) {
        this.carparkID = carparkID;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
