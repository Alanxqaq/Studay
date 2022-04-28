package service;

import bean.Dept;
import dao.DeptDAO;

import java.util.List;

public class DeptService {

    private DeptDAO ddao = new DeptDAO();

    public List<Dept> findAll() {
        return ddao.findAll();
    }
}
