<%--
  Created by IntelliJ IDEA.
  User: The丶Alanx
  Date: 2022/4/24
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改</title>
</head>
<script>
    function changeJob() {
        let val1 = document.getElementById("job2").value;
        let val2 = document.getElementById("job1");
        val2.value = val1;
    }
</script>
<body>
<form action="emp" method="post">
    <input type="hidden" name="type" value="update">
    <input type="hidden" name="empNo" value="${EMP.empNo}">
    姓名：<input type="text" name="ename" value="${EMP.ename}"><br>
    职位：
    <input type="text" name="job" value="${EMP.job}" id="job1">
    <select id="job2" onchange="changeJob()">
        <c:forEach var="j" items="${JOB}">
            <c:choose>
                <c:when test="${j==EMP.job}">
                    <option selected>${j}</option>
                </c:when>
                <c:otherwise>
                    <option>${j}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select><br>
    上司：
    <select name="mgr">
        <c:forEach var="emp" items="${EMP_LIST_1}">
            <c:choose>
                <c:when test="${emp.empNo==EMP.empNo}">
                    <option value="${emp.empNo}" selected>${emp.ename}</option>
                </c:when>
                <c:otherwise>
                    <option value="${emp.empNo}">${emp.ename}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select> <br>
    工资：<input type="number" name="sal" value="${EMP.sal}"><br>
    奖金：<input type="number" name="comm" value="${EMP.comm}"><br>
    部门：
    <select name="deptNo">
        <c:forEach var="dept" items="${DEPT}">
            <c:choose>
                <c:when test="${dept.deptNo==EMP.deptNo}">
                    <option value="${dept.deptNo}" selected>${dept.dname}</option>
                </c:when>
                <c:otherwise>
                    <option value="${dept.deptNo}">${dept.dname}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <br>
    <button>修改员工</button>
</form>
</body>
</html>
