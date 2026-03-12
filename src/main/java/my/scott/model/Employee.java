
package my.scott.model;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private LocalDate hiredate;
    private int salary;
    private int commision;
    private int deptno;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMgr() {
        return mgr;
    }

    public void setMgr(int mgr) {
        this.mgr = mgr;
    }

    public LocalDate getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getCommision() {
        return commision;
    }

    public void setCommision(int commision) {
        this.commision = commision;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public Employee(){}
    
    public Employee(int empno, String ename, String job, int mgr, LocalDate hiredate, int salary, int commision, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.salary = salary;
        this.commision = commision;
        this.deptno = deptno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.empno;
        hash = 71 * hash + Objects.hashCode(this.ename);
        hash = 71 * hash + Objects.hashCode(this.job);
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
        final Employee other = (Employee) obj;
        if (this.empno != other.empno) {
            return false;
        }
        if (!Objects.equals(this.ename, other.ename)) {
            return false;
        }
        return Objects.equals(this.job, other.job);
    }
    
    
    
}
