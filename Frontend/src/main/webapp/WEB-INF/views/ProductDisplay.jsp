<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">Product Catalog</h3>

<div class="container">
<div  class="row text-center text-lg-left">
<c:forEach items="${productList}" var="product">
<div class="col-md-3 col-sm-4 col-xs-12">
<a href="<c:url value="/totalProductDisplay/${product.productId}"/>" class="d-block mb-4 h-100">
<img class="img-fluid img-thumbnail" src="<c:url value="/resources/images/${product.productId}.jpg"/>" width="100" height="100"/>
<p>Price :${product.price}</p>
<p>Stock :${product.stock}</p>
</a>

</div>
</c:forEach>

</div>
</div>


</body>
</html>