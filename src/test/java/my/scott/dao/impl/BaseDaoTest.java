package my.scott.dao.impl;

import javax.sql.DataSource;
import my.scott.test.utils.JdbcUtil;
import my.scott.test.utils.SqlTestUtils;
import org.junit.jupiter.api.BeforeEach;

public abstract  class BaseDaoTest {
    
    protected DataSource dataSource;
    
    @BeforeEach
    void initDatabase(){
        dataSource = JdbcUtil.createDefaultInMemoryH2DataSource();
        
        SqlTestUtils.runSql(dataSource, "sql/create-dept.sql","sql/insert-dept.sql");
    }
    
}
