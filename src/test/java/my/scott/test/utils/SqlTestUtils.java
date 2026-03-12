package my.scott.test.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;

public class SqlTestUtils {
    
    public static void runSql(DataSource ds, String... files){
        
        try(Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement()){
            for(String file: files){
                InputStream is = SqlTestUtils.class.getClassLoader().getResourceAsStream(file);
                if(is == null){
                       throw new RuntimeException("SQL file not found: "+file);
                }
                
                String sql = new String(is.readAllBytes());
                stmt.execute(sql);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        
    }
    
}
