<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <div class="content-wrapper">
<div class="item-container">
<div class="container">
<div class="row">
<div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
<div class="product col-md-3 service-image-left">
<center>
<img id="item-display" src="<c:url value="/resources/images/${product.productId}.jpg"/>" alt=""></img>
</center>
</div>
</div>
<form action="<c:url value="/addToCart/${product.productId}"/>" method="get">
<div class="col-md-6 col-lg-6 col-sm-6 col-xs-12">
<div class="product-title">${product.productName}</div>
<div class="product-desc">${product.productDesc}</div>
<hr>
<div class="product-price">INR ${product.price}/-</div>
<div class="product-stock">In Stock</div>
<div class="product-price">Quantity
<select name="quantity">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
</select>
</div>
<hr>
<div class="btn-group cart">
<input type="submit" value="Add to Cart" class="btn btn-success">
</div>
<div class="btn-group wishlist">
<button type="button" class="btn btn-danger">
Buy Now
</button>
</div>
</div>
</form>
 </div>
 

</div>
</div>

</div>

</body>
</html>