<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Crea tu Permutacion</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
				<c:if test="${not empty limite}">
					<form:form action="guardar-permutacion" modelAttribute="permutacion" method="POST" enctype="multipart/form-data">
				    	<h2 class="form-signin-heading">Crear Permutacion</h2>
						<hr class="colorgraph"><br>
						<div class="py-2">
							<h4>Nombre Permutacion</h4>
							<form:input path="nombrePermutacion" id="nombre" type="text" class="form-control" />							
						</div>
						<div class="py-2">
 							 <form:errors path="nombrePermutacion" cssClass="alert alert-danger"/>
						</div>
						
						<div class="py-2">
							<h4>Descripcion Permutacion</h4>
							<form:textarea path="descripcionPermutacion" id="descripcion" class="form-control" />
						</div>
						<div class="py-2">
							<form:errors path="descripcionPermutacion" cssClass="alert alert-danger" />
						</div>
						
						<div class="py-2">
							<h4>Fecha de  Finalizacion</h4>
							<form:input type="date" path="fechaFinalizacion" id="fechaFinalizacion" class="form-control" />
						</div>
						<div class="py-2">
							<form:errors path="fechaFinalizacion" cssClass="alert alert-danger" />
						</div>
						<c:if test="${not empty falloFechaFin }">
						<div class="py-2">
 							<div class="alert alert-danger">La Fecha debe ser actual o posterior</div>
						</div>	 
 						</c:if>
						
						
						<div class="py-2">
							<h4>Subida de Imagenes</h4>
							<form:input type="file" path="fotoPermutacion" id="fotoPermutacion" class="form-control-file" accept="image/jpeg,image/png" />
						</div>
				        <div class="py-2"> 
							<form:errors path="fotoPermutacion" cssClass="alert alert-danger" />
						</div>
						<div class="py-2">
						
							<button type="submit" class="btn btn-primary">Crear Permutacion</button>
							
						</div>
					</form:form>
					</c:if>
					<c:if test="${empty limite}">
							Ha alcanzado el maximo de permutaciones creadas
					</c:if>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
		
		<%@include file='templates/footer.html'%>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	</body>
</html>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
