package contents;

public class Product {
    
    private String id;
    private String asin;
    private String title;
    private String group;
    private String rank;
    
    public Product(String id, String asin, String name, String group, String rank) {
        this.id = id;
        this.asin = asin;
        this.title = name;
        this.group = group;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public String getAsin() {
        return asin;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup() {
        return group;
    }

    public String getRank() {
        return rank;
    }
}
