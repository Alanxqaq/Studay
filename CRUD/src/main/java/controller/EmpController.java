package controller;

import bean.Emp;
import service.EmpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/emp")
public class EmpController extends HttpServlet {
    private EmpService empService = new EmpService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String type = req.getParameter("type");
        if (type == null) {
            find(req, resp);
            return;
        }
        Class c = getClass();
        try {
            Method method = c.getDeclaredMethod(type, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受到了页面
        String p = request.getParameter("page");
        //根据页码进行了类型转换
        int page = p == null ? 1 : Integer.parseInt(p);
        //调用service的查询方法  返回结果
        List<Emp> list = empService.findByPage(page);
        request.getSession().setAttribute("ALL_PAGE_NUM", empService.findAllPageNum());
        request.getSession().setAttribute("EMP_LIST", list);
        response.sendRedirect("emp.jsp");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        empService.remove(id);
        find(request, response);
    }

    private void like(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ename = request.getParameter("ename");
        List<Emp> list = empService.findByEname(ename);
        request.getSession().setAttribute("EMP_LIST", list);
        response.sendRedirect("emp.jsp");
    }

    private void save(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 接收页面送过来的数据
        String ename = request.getParameter("ename");
        String job = request.getParameter("job");
        int mgr = Integer.parseInt(request.getParameter("mgr"));
        double sal = Double.parseDouble(request.getParameter("sal"));
        double comm = Double.parseDouble(request.getParameter("comm"));
        int deptNo = Integer.parseInt(request.getParameter("deptNo"));
        //封装
        Emp emp = new Emp(ename, job, mgr, sal, comm, deptNo);
        //调用service的添加方法
        empService.save(emp);
        //跳转到查询方法
        response.sendRedirect("emp?type=find");
    }

    private void byid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Emp emp = empService.findById(id);
        request.getSession().setAttribute("EMP", emp);
        response.sendRedirect("update.jsp");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //接受页面送过来的数据
        int empNo = Integer.parseInt(request.getParameter("empNo"));
        String ename = request.getParameter("ename");
        String job = request.getParameter("job");
        int mgr = Integer.parseInt(request.getParameter("mgr"));
        double sal = Double.parseDouble(request.getParameter("sal"));
        double comm = Double.parseDouble(request.getParameter("comm"));
        int deptNo = Integer.parseInt(request.getParameter("deptNo"));
        Emp emp = new Emp(empNo, ename, job, mgr, null, sal, comm, deptNo, 1);
        //调用service的修改方法
        empService.update(emp);
        //跳转到查询方法
        response.sendRedirect("emp?type=find");
    }
}

