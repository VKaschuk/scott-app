package my.scott.dao.impl;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.ObjectUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import my.scott.dao.Dao;
import my.scott.model.Department;

@ApplicationScoped
public class DepartmentDaoImpl implements Dao<Department>{

    private static final Logger LOG = Logger.getLogger(DepartmentDaoImpl.class.getName());
    
    private DataSource scottDS;
    
    public DepartmentDaoImpl(){
    }
    
    public DepartmentDaoImpl(DataSource ds){
        this.scottDS = ds;
    }
    
    @Resource(lookup="java:openejb/Resource/ScottDS")
    public void setDataSource(DataSource ds){
        this.scottDS = ds;
    }
            
    @Override
    public List<Department> getAll() {
        List<Department> depts = new ArrayList<>();
        String sqlQuery = "SELECT a.deptno, a.dname, a.loc FROM dept a";
        try(Connection conn = scottDS.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlQuery);
                ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                depts.add(getDepartment(rs));
            }
        }catch(SQLException e){
            LOG.log(Level.SEVERE, "Error while Getting Departments from db: ",e);
        }
        return depts;
    }
    
    private Department getDepartment(ResultSet rs) throws SQLException{
            Department dept = new Department();
            
            dept.setDeptno(rs.getInt("deptno"));
            dept.setDname(rs.getString("dname"));
            dept.setLoc(rs.getString("loc"));
            
            return dept;
        
    }

    @Override
    public Department getById(int id){
        String sqlQuery = "select deptno, dname, loc from dept where deptno = ?";
        Department dept = null;
        try(Connection conn = scottDS.getConnection();
            PreparedStatement prst = conn.prepareStatement(sqlQuery);){
           
          prst.setInt(1, id);
          try( ResultSet rs = prst.executeQuery()){
            while(rs.next()){
             dept = getDepartment(rs);
            }
          }
       }catch(SQLException e){
           LOG.log(Level.SEVERE,"Errot while Getting Department by id: ",e);
       }
        return dept;
    }

    @Override
    public int insert(Department t) {
        if(t == null||ObjectUtils.anyNull(t.getDeptno(),t.getDname(),t.getLoc())){
            LOG.log(Level.INFO, "Can't insert empty Department object.");
            return 0;
        }
        String sqlQuery = "insert into dept(deptno, dname, loc) values(?, ?, ?);";
        try(Connection conn = scottDS.getConnection();
            PreparedStatement prst = conn.prepareStatement(sqlQuery);){
            prst.setInt(1, t.getDeptno());
            prst.setString(2, t.getDname());
            prst.setString(3, t.getLoc());
            return prst.executeUpdate();
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"Error while insert value into Dept table: ",e);
            return 0;
        }
    }

    @Override
    public void update(Department t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Department t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
