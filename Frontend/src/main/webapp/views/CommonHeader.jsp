<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container">

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
    <li class="active"><a href="#">Home</a></li>
    <c:if test="${!sessionScope.loggedIn}">	
      		<li><a href="login">Login</a></li>
      		<li><a href="register">Register</a></li>
      		<li><a href="contactUs">Contact Us</a></li>
    </c:if>
    <c:if test="${sessionScope.loggedIn}">
    	<c:if test="${sessionScope.role=='ROLE_ADMIN'}">
      		<li><a href="category">Manage Category</a></li>
      		<li><a href="product">Manage Product</a></li>
      	</c:if>
    </c:if>
    <c:if test="${sessionScope.loggedIn}">
    	<c:if test="${sessionScope.role=='ROLE_USER'}">
      		<li><a href="productdisplay">Product </a></li>
      		<li><a href="cart">Cart</a></li>
      	</c:if>
    </c:if>
    </ul>
    
    <ul class="nav navbar-nav navbar-right">
		<c:if test="${!sessionScope.loggedIn}">
			<li><a href="<c:url value="/register"/>"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			<li><a href="<c:url value="/login"/>"><span	class="glyphicon glyphicon-log-in"></span> Login</a></li>
		</c:if>
		<c:if test="${sessionScope.loggedIn}">
			<li><a href="#"><span class="glyphicon glyphicon-user">${sessionScope.username}</span></a></li>
			<li><a href="<c:url value="/perform_logout"/>"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
		</c:if>
	</ul>
    
  </div>
</nav>
</div>
</body>
</html>