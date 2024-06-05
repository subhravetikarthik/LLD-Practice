package design_patterns.Singleton.LazyInitialization;

/** LazyInitialization **/
public class DBConnection {
    private static DBConnection dbConn = null;
    private DBConnection() { }
    public synchronized static DBConnection getInstance() {
        if(dbConn == null) {
            System.out.println("Entering critical section by " + Thread.currentThread().getName());
            dbConn = new DBConnection();
            System.out.println("DBConnection instance created! " + dbConn);
        }
        return dbConn;
    }
}