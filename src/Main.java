import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Singleton Design Pattern Implementation. Select an option to execute!");
        System.out.println("1. Basic");
        System.out.println("2. Eager Loading");
        System.out.println("3. Lazy Initialization");
        System.out.println("4. Double Checked Locking");
        System.out.println("5. Exit");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int value = Integer.parseInt(br.readLine());
            if(value == 1) {
                executeV1();
            } else if(value == 2) {
                executeV2();
            } else if(value == 3) {
                executeV3();
            } else if(value == 4) {
                executeV4();
            } else {
                System.out.println("Quitting!");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * In absence of synchronization, there’s a possibility that two threads interleave their executions in such a way
     * that the expression dbConnection == null evaluates to true for both threads,
     * and as a result, two instances of `DBConnection` get created.
     * !!! NOT THREAD SAFE !!!
     * If multiple threads invoke getInstance() method, multiple objects can get created!

     Sample Output:
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-1
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-9
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-8
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-3
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-4
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-6
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-7
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-5
     design_patterns.Singleton.Basic.DBConnection@5dfa963a ::: Thread-2
     design_patterns.Singleton.Basic.DBConnection@66818d72 ::: Thread-0
     **/
    private static void executeV1() {
        System.out.println("Executing Basic Singleton Implementation...");
        for(int i = 1; i <= 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    design_patterns.Singleton.Basic.DBConnection dbConn = design_patterns.Singleton.Basic.DBConnection.getInstance();
                    System.out.println(dbConn + " ::: " + Thread.currentThread().getName());
                }
            });
            t.start();
        }
    }

    /**
     * The Eager initialization approach involves creating the design_patterns.Singleton instance at the time of class loading.
     * This ensures that the instance is always available and eliminates the need for synchronization.
     * If multiple threads invoke getInstance() method, same dbConn instance is returned!

     Sample Output:
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-2
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-3
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-8
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-4
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-7
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-9
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-1
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-6
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-0
     design_patterns.Singleton.EagerLoading.DBConnection@77b22370 ::: Thread-5
     **/
    private static void executeV2() {
        System.out.println("Executing Eager Loading Implementation...");
        for(int i = 1; i <= 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    design_patterns.Singleton.EagerLoading.DBConnection dbconn = design_patterns.Singleton.EagerLoading.DBConnection.getInstance();
                    System.out.println(dbconn + " ::: " + Thread.currentThread().getName());
                }
            });
            t.start();
        }
    }

    /**
     * The Lazy initialization approach defers the creation of the design_patterns.Singleton instance until it is actually needed.
     * This approach is particularly useful when the design_patterns.Singleton object is resource-intensive or when its creation involves complex initialization logic.
     * While this approach ensures thread safety, it introduces synchronization overhead, which can impact performance, especially in highly concurrent systems.
     * !!! THREAD SAFE BUT PERFORMANCE IMPACT DUE TO USE OF SYNCHRONIZATION !!!

     Sample Output:
     Entering critical section by Thread-7
     DBConnection instance created! design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-0
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-3
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-9
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-1
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-2
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-8
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-4
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-6
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-7
     design_patterns.Singleton.LazyInitialization.DBConnection@7bb4a329 ::: Thread-5
     **/
    private static void executeV3() {
        System.out.println("Executing Lazy Initialization implementation using Synchronized...");
        for(int i = 1; i <= 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    design_patterns.Singleton.LazyInitialization.DBConnection dbConn = design_patterns.Singleton.LazyInitialization.DBConnection.getInstance();
                    System.out.println(dbConn + " ::: " + Thread.currentThread().getName());
                }
            });
            t.start();
        }
    }

    /**
     * Double-checked locking is a design pattern used to reduce the overhead of acquiring locks
     * by first testing the locking condition without holding the lock.
     * If the condition passes, the lock is acquired, and the condition is checked again within the critical section.

     Sample Output:
     Entering critical section : Thread-8
     DBConnection instance created! design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-8
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-3
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-5
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-9
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-1
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-4
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-7
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-2
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-0
     design_patterns.Singleton.DoubleCheckLocking.DBConnection@1cc653f6 ::: Thread-6
     **/
    private static void executeV4() {
        System.out.println("Executing double check locking Implementation...");
        for(int i = 1; i <= 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    design_patterns.Singleton.DoubleCheckLocking.DBConnection dbConn = design_patterns.Singleton.DoubleCheckLocking.DBConnection.getInstance();
                    System.out.println(dbConn + " ::: " + Thread.currentThread().getName());
                }
            });
            t.start();
        }
    }
}