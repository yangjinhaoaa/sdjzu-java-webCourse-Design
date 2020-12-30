<%--
  Created by IntelliJ IDEA.
  User: yjhm
  Date: 2020/12/20
  Time: 9:14 下午
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
    <title>SELECT 操作</title>
</head>
<body>
<!--
JDBC 驱动名及数据库 URL
数据库的用户名与密码，需要根据自己的设置
useUnicode=true&characterEncoding=utf-8 防止中文乱码
 -->
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/car_data?useUnicode=true&characterEncoding=utf-8"
                   user="root"  password="191109yjh"/>

<sql:query dataSource="${snapshot}" var="result">
    SELECT * from fixed_car where status="固定";
</sql:query>
<h1>车辆黑名单</h1>
<table border="1" width="100%">
    <tr>
        <th>车牌号</th>
        <th>颜色</th>
        <th>所有者</th>
        <th>品牌</th>
        <th>状态</th>
    </tr>
    <c:forEach var="row" items="${result.rows}">
        <tr>
            <td><c:out value="${row.car_id}"/></td>
            <td><c:out value="${row.car_color}"/></td>
            <td><c:out value="${row.car_owner}"/></td>
            <td><c:out value="${row.car_brand}"/></td>
            <td><c:out value="${row.status}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
