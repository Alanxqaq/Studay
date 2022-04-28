<%@ page language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty EMP_LIST}">
        <c:redirect url="emp">
            <c:param name="type" value="find"/>
            <c:param name="page" value="1"/>
        </c:redirect>
    </c:when>
    <c:otherwise>
        <c:redirect url="emp.jsp"/>
    </c:otherwise>
</c:choose>