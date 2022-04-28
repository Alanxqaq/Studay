<%--
  Created by IntelliJ IDEA.
  User: The丶Alanx
  Date: 2022/4/22
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加员工</title>
</head>
<script>
    //默认标记  当为true时允许提交
    let flag1 = false, flag2 = false;

    function checkName(obj) {
        let val = obj.value;
        let regex = /^[a-zA-Z0-9\u4e00-\u9fa5]{2,8}$/;
        let result = regex.test(val);
        let enameMsg = document.getElementById("enameMsg");
        if (result) {
            enameMsg.innerText = "你的姓名可以使用";
            enameMsg.style.color = "green";
            flag1 = true;
        } else {
            enameMsg.innerText = "你的姓名不符合要求";
            enameMsg.style.color = "red";
        }
    }

    function checkSal(obj) {
        let val = parseInt(obj.value, 10);//将工资转化成int类型，10进制
        let salMsg = document.getElementById("salMsg");
        if (val > 1250) {
            salMsg.innerText = "你的工资可以使用";
            salMsg.style.color = "green";
            flag2 = true;
        } else {
            salMsg.innerText = "最低工资不得低于1250";
            salMsg.style.color = "red";
        }
    }

    //通过下拉菜单给文本框赋值
    function changeJob() {
        let val1 = document.getElementById("job2").value;
        let val2 = document.getElementById("job1");
        val2.value = val1;
    }

    //提交按钮事件
    function sub() {
        return flag1 && flag2;
    }
</script>
<body>
<form action="emp" method="post">
    <input type="hidden" name="type" value="save"/>
    姓名：<input type="text" name="ename" id="ename" onblur="checkName(this)"/>
    <label id="enameMsg"></label><br>
    职位：
    <input type="text" name="job" id="job1" value="${JOB[0]}" placeholder="请选择或直接输入职位" style="width: 120px">
    <select id="job2" onchange="changeJob()">
        <c:forEach var="j" items="${JOB}">
            <option>${j}</option>
        </c:forEach>
    </select> <br/>
    上司：
    <select name="mgr">
        <c:forEach var="emp" items="${EMP_LIST_1}">
            <option value="${emp.empNo}">${emp.ename}</option>
        </c:forEach>
    </select>
    <br/>
    工资：<input type="number" name="sal" onblur="checkSal(this)"/>
    <label id="salMsg"></label>
    <br/>
    奖金：<input type="number" name="comm"/><br/>
    部门ID： <select name="deptNo">
    <c:forEach var="dept" items="${DEPT}">
        <option value="${dept.deptNo}">${dept.dname}</option>
    </c:forEach>
</select><br/>
    <button onclick="return sub()">添加员工</button>
</form>
</body>
</html>
