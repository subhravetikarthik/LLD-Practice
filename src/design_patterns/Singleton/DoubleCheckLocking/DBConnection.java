package design_patterns.Singleton.DoubleCheckLocking;

/** Double-Checked Locking Mechanism  **/
public class DBConnection {
    private static DBConnection dbConn = null;
    private DBConnection() { }
    public static DBConnection getInstance() {
        if(dbConn == null) {
            synchronized (DBConnection.class) {
                if(dbConn == null) {
                    System.out.println("Entering critical section : " + Thread.currentThread().getName());
                    dbConn = new DBConnection();
                    System.out.println("DBConnection instance created! " + dbConn);
                }
            }
        }
        return dbConn;
    }
}