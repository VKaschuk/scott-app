package my.scott.dao.impl;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import my.scott.dao.Dao;
import my.scott.model.Department;

@ApplicationScoped
public class DepartmentDaoImpl implements Dao<Department>{

    private static final Logger LOG = Logger.getLogger(DepartmentDaoImpl.class.getName());
    private static final String SELECT_ALL = "SELECT a.deptno, a.dname, a.loc FROM dept a";
    private static final String SELECT_BY_ID = "SELECT deptno, dname, loc FROM dept WHERE deptno = ?";
    private static final String INSERT_STATEMENT = "INSERT INTO dept(deptno, dname, loc) VALUES(?, ?, ?)";
    private static final String UPDATE_STATEMENT = "UPDATE dept SET dname = ?, loc = ? WHERE deptno = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM dept WHERE deptno = ?";
    
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
        try(Connection conn = scottDS.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
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
    public Optional<Department> getById(int id){
        
        try(Connection conn = scottDS.getConnection();
            PreparedStatement prst = conn.prepareStatement(SELECT_BY_ID);){
           
          prst.setInt(1, id);
          try( ResultSet rs = prst.executeQuery()){
            while(rs.next()){
             return Optional.of(getDepartment(rs));
            }
          }
       }catch(SQLException e){
           LOG.log(Level.SEVERE,"Errot while Getting Department by id: ",e);
       }
        return Optional.empty();
    }

    @Override
    public int insert(Department d) {
        if(d == null||isInvalid(d)){
            LOG.log(Level.INFO, "Can't insert empty Department object.");
            return 0;
        }
       
        try(Connection conn = scottDS.getConnection();
            PreparedStatement prst = conn.prepareStatement(INSERT_STATEMENT);){
            prst.setInt(1, d.getDeptno());
            prst.setString(2, d.getDname());
            prst.setString(3, d.getLoc());
            return prst.executeUpdate();
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"Error while insert value into Dept table: ",e);
            return 0;
        }
    }

    @Override
    public boolean update(Department d) {
        
        Objects.requireNonNull(d);
        
        try(Connection conn = scottDS.getConnection();
            PreparedStatement prst = conn.prepareStatement(UPDATE_STATEMENT)){
            prst.setString(1, d.getDname());
            prst.setString(2, d.getLoc());
            prst.setInt(3, d.getDeptno());
            int result = prst.executeUpdate();
            
            if(result == 0){
                LOG.log(Level.INFO, "Department not found for update {0}.",d.getDeptno());
            }
            return result > 0;
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"Error during update department. ",e);
            return false;
        }
    }

    @Override
    public boolean delete(Department d) {
        Objects.requireNonNull(d);
        try(Connection conn = scottDS.getConnection();
             PreparedStatement prst = conn.prepareStatement(DELETE_STATEMENT))
        {
            prst.setInt(1, d.getDeptno());
            return prst.executeUpdate()>0;
            
        }catch(SQLException e){
            LOG.log(Level.SEVERE, "Error during delete department "+d.getDeptno(),e);
            return false;
        }
    }
 
    private boolean isInvalid(Department d){
        return d.getDeptno() <= 0 || d.getDname() == null || d.getLoc() == null;
    }
    
}
