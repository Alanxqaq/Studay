<%--
  Created by IntelliJ IDEA.
  User: The丶Alanx
  Date: 2022/4/21
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工信息表</title>
</head>
<script>
    function deleteEmp(empNo, ename) {
        if (confirm("你确定要删除") + ename + "吗？") {
            location.href = "emp?type=remove&id=" + empNo;
        }
    }
</script>
<style>
    table {
        border: 1px palegreen solid;
        border-collapse: collapse;
        margin: auto;
        width: 80%;
    }

    th, td {
        border: 1px springgreen solid;
    }

    th {
        color: white;
        background-color: slategrey;
    }

    tr:nth-child(odd) {
        background-color: azure;
    }

    tr:hover {
        background-color: deepskyblue;
    }

    .c1 {
        color: red;
    }
</style>
<body>
<table>
    <div style="text-align: center">
        <form action="emp" method="post">
            <input type="text" name="ename"/>
            <input type="hidden" name="type" value="like"/>
            <button>搜索</button>
        </form>
        <a href="save.jsp">添加员工</a>
    </div>
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>职位</th>
        <th>上司ID</th>
        <th>入职时间</th>
        <th>工资</th>
        <th>奖金</th>
        <th>部门名</th>
        <th>办公地</th>
        <th>操作</th>
    </tr>
    <c:forEach var="emp" items="${EMP_LIST}" varStatus="j">
        <tr>
            <td>${j.count}</td>
            <td>${emp.ename}</td>
            <td>${emp.job}</td>
            <td>${emp.mgrName}</td>
            <td>${emp.hireDate}</td>
            <td>${emp.sal}</td>
            <td>${emp.comm}</td>
            <td>${emp.dept.dname}</td>
            <td>${emp.dept.loc}</td>
            <td>
                <a href="javascript:deleteEmp(${emp.empNo},'${emp.ename}')">删除</a>
                <a href="emp?type=byid&id=${emp.empNo}">修改</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <th colspan="10">
            <c:forEach var="i" begin="1" step="1" end="${ALL_PAGE_NUM}">
                <a href="emp?type=find&page=${i}">${i}</a>&nbsp;&nbsp;
            </c:forEach>
        </th>
    </tr>
</table>
</body>
</html>
