package design_patterns.Singleton.Basic;

/** Single-Threaded Basic Implementation **/
public class DBConnection {
    private static DBConnection dbConn = null;
    private DBConnection() { }
    public static DBConnection getInstance() {
        if(dbConn == null) {
            dbConn = new DBConnection();
        }
        return dbConn;
    }
}