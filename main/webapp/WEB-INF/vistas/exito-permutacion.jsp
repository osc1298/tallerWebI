<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Permutaci&oacute;n Creada</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<h4>Felicitaciones, ha creado con &eacute;xito la Permutaci&oacute;n</h4>
			<h3>${nombre}</h3>
			<div class="container text-center pb-5">
			<c:if test="${not empty imageNombre}">
				<img src=${pageContext.servletContext.contextPath}/getImage/${imageNombre} alt="myImage" class="img-fluid" />
			</c:if>
			<c:if test="${empty imageNombre}">
				<img src=${pageContext.servletContext.contextPath}/img/noimage.jpg alt="myImage" class="img-fluid" />
			</c:if>
			</div>
			
			<div>
				<a href="home" class="btn btn-success">Volver al Home</a>
			</div>
		</div>
		
		<%@include file='templates/footer.html'%>
	</body>
</html>