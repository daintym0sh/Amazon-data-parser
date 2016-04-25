package contents;

public class Customer {
    private String asin;
    private String date;
    private String customer;
    private String rating;
    private String votes;
    private String helpful;
    
    public Customer(String asin, String date, String customer, String rating, String votes, String helpful) {
        this.asin = asin;
        this.date = date;
        this.customer = customer;
        this.rating = rating;
        this.votes = votes;
        this.helpful = helpful;
    }
    
    public String getAsin() {
        return asin;
    }
    
    public String getDate() {
        return date;
    }

    public String getCustomer() {
        return customer;
    }

    public String getRating() {
        return rating;
    }

    public String getVotes() {
        return votes;
    }

    public String getHelpful() {
        return helpful;
    }


}
