<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Historial de subastas</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<h2>Su Historial de Permutaciones</h2>

			<div class="row pt-5">
				 <div class="col-md-12">
					<table width="100%" cellpadding="12">
						<tr class="table-info">
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Fecha</th>
							<th>Estado</th>
							<th>Usuario Ganador</th>
							<th>Oferta Ganadora</th>
							<th></th>
						</tr>
						<c:if test="${not empty listadoPermutacion}">
						<c:forEach var="permutaciones" items="${listadoPermutacion}">
							<tr>
								<td>${permutaciones.nombrePermutacion}</td>
								<td>${permutaciones.descripcionPermutacion}</td>
								<td><fmt:formatDate type="date" value="${permutaciones.fechaCreacion}" /></td>
								<td><c:if test="${permutaciones.estadoPublicacion == true}">Activa</c:if>
									<c:if test="${permutaciones.estadoPublicacion == false}">Inactiva</c:if></td>
								<td><c:if test="${empty permutaciones.ofertaGanadora}">No hay ganador</c:if>
									<c:if test="${not empty permutaciones.ofertaGanadora}"> ${permutaciones.ofertaGanadora.usuario.nombre}</c:if></td>
								<td><c:if test="${empty permutaciones.ofertaGanadora}">No hay oferta ganadora</c:if>
									<c:if test="${not empty permutaciones.ofertaGanadora}"> ${permutaciones.ofertaGanadora.descripcion}</c:if></td>
								<td><a href="detalle-permutacion/${permutaciones.idPermutacion}" class="btn btn-primary">Ver Detalle</a></td>
							</tr>
						</c:forEach>
						</c:if>
					</table>
					<c:if test="${empty listadoPermutacion}">
					<p>No ha creado Permutaciones. Cree una!</p>
					</c:if>
				</div>

			</div>
		
		<div class="d-flex justify-content-between">
		<a href=${pageContext.servletContext.contextPath}/perfil class="btn btn-primary">Volver al Perfil</a>
		<c:if test="${not empty listadoPermutacion}">
		<a href="excel-permutaciones" class="btn btn-primary">Descargar Listado en Excel</a>
		</c:if>
		</div>
	</body>
</html>