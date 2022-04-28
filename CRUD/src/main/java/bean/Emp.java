package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable {
    private Integer empNo;
    private String ename;
    private String job;
    private Integer mgr;
    private String hireDate;
    private Double sal;
    private Double comm;
    private Integer deptNo;
    private Integer empState;

    private Dept dept;
    private String mgrName;

    public Emp(String ename, String job, Integer mgr, Double sal, Double comm, Integer deptNo) {
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public Emp(Integer empNo, String ename, String job, Integer mgr, String hireDate, Double sal, Double comm, Integer deptNo, Integer empState) {
        this.empNo = empNo;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
        this.empState = empState;
    }

    public Emp(Integer empNo, String ename, String job, Integer mgr, String hireDate, Double sal, Double comm, Integer deptNo, Integer empState, Dept dept) {
        this.empNo = empNo;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
        this.empState = empState;
        this.dept = dept;
    }
}
