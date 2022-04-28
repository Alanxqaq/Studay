package service;

import bean.Emp;
import dao.EmpDAO;

import java.util.List;

public class EmpService {
    private EmpDAO edao = new EmpDAO();

    public List<Emp> findAll() {
        return edao.findAll();
    }

    public void remove(int id) {
        edao.remove(id);
    }

    public List<Emp> findByEname(String ename) {
        //手动拆箱 标记红色的字体（搜索的字）
        List<Emp> list = edao.findByEname(ename);
        for (Emp emp : list) {
            String name = emp.getEname();
            name = name.replace(ename, "<label class='c1'>" + ename + "</label>");
            emp.setEname(name);
        }
        return list;
    }

    public void save(Emp emp) {
        edao.save(emp);
    }

    public List<String> job() {
        return edao.findAlljob();
    }

    public int findAllPageNum() {
        int allCount = edao.findAllCount();
        return allCount % 5 == 0 ? (allCount / 5) : (allCount / 5 + 1);  //返回总页数
    }

    public List<Emp> findByPage(int page) {
        return edao.findByPage((page - 1) * 5);
    }


    public Emp findById(int id) {
        return edao.findById(id);
    }
    public void update(Emp emp){
        edao.update(emp);
    }
}
