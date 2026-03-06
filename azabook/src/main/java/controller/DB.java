package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	private static final String URL = 
			"jdbc:h2:tcp://localhost/~/test";
    private static final String USER = "sa";
    private static final String PASS = "";
    
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
