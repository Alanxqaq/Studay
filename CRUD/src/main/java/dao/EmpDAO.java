package dao;

import bean.Dept;
import bean.Emp;
import db.DBManger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Emp> findAll() {
        List<Emp> list = new ArrayList<>();
        String sql = "select * from emp where empstate = 1";
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                String b = rs.getString("ename");
                String c = rs.getString(3);
                int d = rs.getInt(4);
                String e = rs.getString("hireDate");
                double f = rs.getDouble(6);
                double g = rs.getDouble(7);
                int h = rs.getInt(8);
                int i = rs.getInt(9);
                Emp emp = new Emp(a, b, c, d, e, f, g, h, i);
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps, rs);
        }
        return list;
    }

    public List<Emp> findByPage(int start) {
        List<Emp> list = new ArrayList<>();
        String sql = "select * from emp e,dept d,emp e2 where e.DEPTNO=d.DEPTNO and e.MGR=e2.EMPNO and e.EmpSTATE=1 limit ?,5";
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                String b = rs.getString("ename");
                String c = rs.getString(3);
                int d = rs.getInt(4);
                String e = rs.getString("hireDate");
                double f = rs.getDouble(6);
                double g = rs.getDouble(7);
                int h = rs.getInt(8);
                int i = rs.getInt(9);
                int j = rs.getInt(10);
                String k = rs.getString(11);
                String l = rs.getString(12);
                String m = rs.getString(14);
                Dept dept = new Dept(j,k,l);
                Emp emp = new Emp(a, b, c, d, e, f, g, h, i,dept);
                list.add(emp);
                emp.setMgrName(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps, rs);
        }
        return list;
    }

    //统计所有的数据总数
    public int findAllCount() {
        String sql = "select count(empNo) from emp where empstate = 1";
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps, rs);
        }
        return 0;
    }

    public void remove(int id) {
        String sql = "update emp set empstate = 0 where empNo=" + id;
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps);
        }
    }

    public List<Emp> findByEname(String ename) {
        List<Emp> list = new ArrayList<>();
        String sql = "select * from emp e,dept d,emp e2 where e.DEPTNO=d.DEPTNO and e.MGR=e2.EMPNO and e.EmpSTATE=1 like ?";
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ename + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                String b = rs.getString("ename");
                String c = rs.getString(3);
                int d = rs.getInt(4);
                String e = rs.getString("hireDate");
                double f = rs.getDouble(6);
                double g = rs.getDouble(7);
                int h = rs.getInt(8);
                int i = rs.getInt(9);
                int j = rs.getInt(10);
                String k = rs.getString(11);
                String l = rs.getString(12);
                String m = rs.getString(14);
                Dept dept = new Dept(j,k,l);
                Emp emp = new Emp(a, b, c, d, e, f, g, h, i,dept);
                emp.setMgrName(m);
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps, rs);
        }
        return list;
    }

    public void save(Emp emp) {
        String sql = "insert into emp values(null,?,?,?,now(),?,?,?,1)";
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getEname());
            ps.setString(2, emp.getJob());
            ps.setInt(3, emp.getMgr());
            ps.setDouble(4, emp.getSal());
            ps.setDouble(5, emp.getComm());
            ps.setInt(6, emp.getDeptNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps);
        }
    }

    public List<String> findAlljob() {
        List<String> list = new ArrayList<>();
        String sql = "select distinct job from emp";
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps, rs);
        }
        return list;
    }

    public Emp findById(int id) {
        String sql = "select * from emp where empstate=1 and empno=" + id;
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int a = rs.getInt(1);
                String b = rs.getString("ename");
                String c = rs.getString(3);
                int d = rs.getInt(4);
                String e = rs.getString("hireDate");
                double f = rs.getDouble(6);
                double g = rs.getDouble(7);
                int h = rs.getInt(8);
                int i = rs.getInt(9);
                Emp emp = new Emp(a, b, c, d, e, f, g, h, i);
                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps, rs);
        }
        return null;
    }

    public void update(Emp emp) {
        String sql = "update emp set ename=?,job=?,mgr=?,sal=?,comm=?,deptNo=? where empNo=?";
        conn = DBManger.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getEname());
            ps.setString(2, emp.getJob());
            ps.setInt(3, emp.getMgr());
            ps.setDouble(4, emp.getSal());
            ps.setDouble(5, emp.getComm());
            ps.setInt(6, emp.getDeptNo());
            ps.setInt(7, emp.getEmpNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps);
        }
    }
}
