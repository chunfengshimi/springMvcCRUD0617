<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
  Created by IntelliJ IDEA.
  User: wst
  Date: 2017/6/17
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath }/emp" method="POST" modelAttribute="employee">
    <c:if test="${employee.id==null}">
        LastName:<form:input path="lastName"/>
    </c:if>

    <br>
    <c:if test="${employee.id!=null}">
        <form:hidden path="id"/>
        <input type="hidden" name="_method" value="PUT"/>
    </c:if>
    Email:<form:input path="email"/>
    <br>
    <%
        Map<String,Object> genders=new HashMap();
        genders.put("1","Male");
        genders.put("0","Female");
        request.setAttribute("genders",genders);
    %>
    Gender:<form:radiobuttons path="gender" items="${genders }"/>
    <br>
    Department: <form:select path="department.id"
                             items="${departments }" itemLabel="departmentName" itemValue="id"></form:select>
    <br>
    Birth:<form:input path="birth"/>
    <br>
    <input type="submit" value="submit"/>
</form:form>

</body>
</html>
