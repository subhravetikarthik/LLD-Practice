package design_patterns.Singleton.EagerLoading;

/** Eager Loading Implementation **/
public class DBConnection {
    private static DBConnection dbConn = new DBConnection();
    private DBConnection() { }
    public static DBConnection getInstance() {
        return dbConn;
    }
}