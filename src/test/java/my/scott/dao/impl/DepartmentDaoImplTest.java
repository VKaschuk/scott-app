package my.scott.dao.impl;

import java.util.List;
import my.scott.model.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DepartmentDaoImplTest extends BaseDaoTest{
    
    @Test
    void shouldReturnDepartments(){
        
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        
        List<Department> depts = dao.getAll();
        
        Assertions.assertNotNull(depts);
        Assertions.assertEquals(1,depts.size());
        
        
        Department d = depts.get(0);
        
        Assertions.assertEquals(10, d.getDeptno());
        Assertions.assertEquals("ACCOUNTING", d.getDname());
        Assertions.assertEquals("NEW YORK", d.getLoc());
    }
    
    @Test
    void shouldReturnDepartmentById(){
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        
        Department dept = dao.getById(10);
        
        Assertions.assertNotNull(dept);
        Assertions.assertEquals(10, dept.getDeptno());
        Assertions.assertEquals("ACCOUNTING", dept.getDname());
        Assertions.assertEquals("NEW YORK", dept.getLoc());
        
    }
}
