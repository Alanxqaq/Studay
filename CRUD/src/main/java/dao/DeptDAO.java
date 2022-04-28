package dao;

import bean.Dept;
import db.DBManger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Dept> findAll() {
        List<Dept> list = new ArrayList<>();
        conn = DBManger.getConnection();
        String sql = "select * from dept";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                Dept dept = new Dept(a, b, c);
                list.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManger.close(conn, ps, rs);
        }
        return list;
    }
}