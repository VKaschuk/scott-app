
package my.scott.model;

import java.util.Objects;


public class Department {
    
    private int deptno;
    private String dname;
    private String loc;

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
    
    public Department(){}

    public Department(int deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.deptno;
        hash = 53 * hash + Objects.hashCode(this.dname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (this.deptno != other.deptno) {
            return false;
        }
        if (!Objects.equals(this.dname, other.dname)) {
            return false;
        }
        return Objects.equals(this.loc, other.loc);
    }
    
    
    
    
}
