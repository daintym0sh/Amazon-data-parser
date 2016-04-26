package contents;

public class ParentCategory {
    private String cid;
    private String p;
    private String pid;
    
    public ParentCategory(String cid, String p, String pid) {
        super();
        this.cid = cid;
        this.p = p;
        this.pid = pid;
    }
    
    public String getCid() {
        return cid;
    }
    public String getP() {
        return p;
    }
    public String getPid() {
        return pid;
    }
}
