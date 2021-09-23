<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Subasta Creada</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<h4>Felicitaciones, ha creado con &eacute;xito la Subasta</h4>
			<h3>${nombre}</h3>
			<div>
				<a href="home" class="btn btn-success">Volver al Home</a>
			</div>
		</div>
		
		<%@include file='templates/footer.html'%>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	</body>
</html>