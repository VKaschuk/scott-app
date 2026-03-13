package my.scott.dao.impl;

import java.util.List;
import java.util.Optional;
import my.scott.model.Department;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DepartmentDaoImplTest extends BaseDaoTest{
    
    @Test
    void shouldReturnDepartments(){
        
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        
        List<Department> depts = dao.getAll();
        
        assertNotNull(depts);
        assertEquals(1,depts.size());
        
        
        Department d = depts.get(0);
        
        assertEquals(10, d.getDeptno());
        assertEquals("ACCOUNTING", d.getDname());
        assertEquals("NEW YORK", d.getLoc());
    }
    
    @Test
    void shouldReturnDepartmentById(){
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        
        Department dept = dao.getById(10).orElseThrow();
        
        assertNotNull(dept);
        assertEquals(10, dept.getDeptno());
        assertEquals("ACCOUNTING", dept.getDname());
        assertEquals("NEW YORK", dept.getLoc());
        
    }
    
    @Test
    void shouldReturnOneAfterSuccessInsert(){
        Department dept = new Department(20,"TEST","TEST VALLEY");
        DepartmentDaoImpl dao =  new DepartmentDaoImpl(dataSource);
        int result = dao.insert(dept);
        assertEquals(1, result);
        Department testDept = dao.getById(20).orElseThrow();
        System.out.println(testDept.toString());
        assertEquals(20, testDept.getDeptno());
        assertEquals("TEST", testDept.getDname());
        assertEquals("TEST VALLEY", testDept.getLoc());
        
    }
    @Test
    void shouldReturnZeroAfterInsertError(){
        Department dept = new Department();
        
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        int result = dao.insert(dept);
        assertEquals(0,result);
    }
    
    @Test
    void shouldUpdateDepartment(){
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        Department dept = new Department(10, "FINANCE", "BOSTON");
        
        boolean updated = dao.update(dept);
        
        assertTrue(updated);
        
        Department result = dao.getById(10).orElseThrow();
        
        assertEquals("FINANCE", result.getDname());
        assertEquals("BOSTON", result.getLoc());
    }
    
    @Test
    void shouldReturnFalseWhenDepartmentNotExists(){
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        Department dept = new Department(999, "TEST", "TEST");
        
        boolean result = dao.update(dept);
        
        assertFalse(result);
        
    }
    @Test
    void shouldReturnSuccesAfterDelete(){
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        boolean result = dao.delete(new Department(10,"ACCOUNTING","NEW YORK"));
        assertTrue(result);
        
        Optional<Department> dept = dao.getById(10);
        assertTrue(dept.isEmpty());
    }
    
    @Test
    void shouldReturnFalseAfterDeptNotFoundWhileDelete(){
        DepartmentDaoImpl dao = new DepartmentDaoImpl(dataSource);
        boolean result = dao.delete(new Department(999,"TEST","TEST"));
        assertFalse(result);
        
    }
}
