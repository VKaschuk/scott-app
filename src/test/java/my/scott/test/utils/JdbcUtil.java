package my.scott.test.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

public class JdbcUtil {
    
    static String DEFAULT_DATABASE_NAME = "test_scott_db";
    static String DEFAULT_USER = "scott";
    static String DEFAULT_PASSWORD = "";
    
    public static DataSource createDefaultInMemoryH2DataSource(){
        String url = formatH2InMemoryDbUrl(DEFAULT_DATABASE_NAME);
        return createInMemoryH2DataSource(url, DEFAULT_USER,DEFAULT_PASSWORD);
        
    }

    private static String formatH2InMemoryDbUrl(String dataBaseName) {
        return String.format("jdbc:h2:mem:%s;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false;DATABASE_TO_UPPER=false",dataBaseName);
    }

    private static DataSource createInMemoryH2DataSource(String url, String userName, String pass) {
        JdbcDataSource h2ds = new JdbcDataSource();
        h2ds.setUrl(url);
        h2ds.setUser(userName);
        h2ds.setPassword(pass);
        return h2ds;
    }
    
    public static void executeSql(DataSource ds, String sql) throws SQLException{
        try(Connection conn = ds.getConnection()){
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }
    }
    
}
