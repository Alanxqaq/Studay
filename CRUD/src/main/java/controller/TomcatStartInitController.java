package controller;

import service.DeptService;
import service.EmpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/tomcat", loadOnStartup = 1)
public class TomcatStartInitController extends HttpServlet {
    private EmpService empService = new EmpService();
    private DeptService deptService = new DeptService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext application = config.getServletContext();
        application.setAttribute("EMP_LIST_1", empService.findAll());
        application.setAttribute("JOB", empService.job());
        application.setAttribute("DEPT", deptService.findAll());
    }
}
