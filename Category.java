package contents;

public class Category {
    private String asin;
    private String c;
    private String cid;
    public Category(String asin, String c, String cid) {
        this.asin = asin;
        this.c = c;
        this.cid = cid;
    }
    public String getAsin() {
        return asin;
    }
    public String getC() {
        return c;
    }
    public String getCid() {
        return cid;
    }
}
