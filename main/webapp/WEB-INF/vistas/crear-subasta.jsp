<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Crear tu Subasta</title>
	</head>
	<body>

		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">

					<form:form action="guardar-subasta" modelAttribute="subasta" method="POST" enctype="multipart/form-data">
				    	<h2 class="form-signin-heading">Crear Subasta</h2>
						<hr class="colorgraph"><br>
						<div class="py-2">
							<h4>Nombre Subasta</h4>
							<form:input path="nombreSubasta" id="nombreSubasta" type="text" class="form-control" />
						</div>
						<div class="py-2">
 							 <form:errors path="nombreSubasta" cssClass="alert alert-danger"/>
						</div>
						<div class="py-2">
							<h4>Descripcion de la subasta</h4>
							<form:textarea path="descripcionSubasta" id="descripcionSubasta" class="form-control" />
						</div>
						<div class="py-2">
 							 <form:errors path="descripcionSubasta" cssClass="alert alert-danger"/>
						</div>
						<div class="py-2">
							<h4>Precio Inicial</h4>
							<form:input type="number" step="0.01" path="pujaInicial" id="precioInicial" class="form-control" />
						</div>
						<div class="py-2">
 							 <form:errors path="pujaInicial" cssClass="alert alert-danger"/>
						</div>
						<div class="py-2">
							<h4>Fecha de  Finalizacion</h4>
							<form:input type="date" path="fechaFinalizacion" id="fechaFinalizacion" class="form-control" />
						</div>
						<div class="py-2">
 							 <form:errors path="fechaFinalizacion" cssClass="alert alert-danger"/>
						</div>
						
						<c:if test="${not empty falloFechaFin }">
						<div class="py-2">
 							<div class="alert alert-danger">La Fecha debe ser actual o posterior</div>
						</div>	 
 						</c:if>
 						
						<div class="py-2">
							<h4>Subida de Imagenes</h4>
							<form:input type="file" path="fotoSubasta" id="fotoSubasta" class="form-control-file" accept="image/jpeg,image/png" />
						</div>
				        <div class="py-2"> 
							<form:errors path="fotoSubasta" cssClass="alert alert-danger" />
						</div>
						<div class="py-2">
							<button type="submit" class="btn btn-primary">Crear Subasta</button>
						</div>
					</form:form>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
		
		<%@include file='templates/footer.html'%>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	</body>
</html>
	
	