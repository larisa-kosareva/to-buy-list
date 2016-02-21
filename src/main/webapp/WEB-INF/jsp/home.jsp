<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ru">
<head>
<title>Maven + Spring MVC</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<script type="text/javascript">
root = '<spring:url value="/" />';
</script>

</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Spring 3 MVC Project</a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h1>${title}</h1>
		<p>
			<c:if test="${not empty name}">
			Hello ${name}
		</c:if>

			<c:if test="${empty name}">
			Welcome Welcome!
		</c:if>
		</p>
		<p>
			<a class="btn btn-primary btn-lg" href="#" role="button">Learn
				more</a>
		</p>
	</div>
</div>

<div class="container">
	<div class="row">
		<c:forEach var="productList" items="${productLists}">
			<div class="col-md-4" id="productList${productList.productListId}">
				<h2>${productList.date}</h2>
				<c:if test="${productList.done == true}">
					<input type="checkbox" checked="checked" value="done">done
				</c:if>
				<c:if test="${productList.done != true}">
					<input type="checkbox" value="done"
						onclick="markAsDone(${productList.productListId});">done
				</c:if>
				<c:forEach var="product" items="${productList.products}">
					<p>${product.name}</p>
				</c:forEach>
				<p id="productListInput${productList.productListId}">
					<input maxlength="25" size="40" value="fuck" class="productListInput"
					 data-listId="${productList.productListId}">
				</p>
			</div>
		</c:forEach>

		<div class="col-md-4" id="add-new-product-div">

			<h2>Добавить</h2>
			<a href="javascript:void(0);" onclick="addNewProductList();">+</a>


		</div>
	</div>

	<hr>
	<footer>
		<p>© ololo Corp. 2016</p>
	</footer>
</div>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<script src='<spring:url value="/resources/dupa.js" />'></script>

</body>
</html>