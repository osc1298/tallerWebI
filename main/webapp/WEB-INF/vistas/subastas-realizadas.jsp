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
			<h2>Su Historial de Subastas</h2>
			<div class="row pt-5">
				<div class="col-md-12">
					<table width="100%" cellpadding="12">
						<tr class="table-info">
							<th>Nombre</th>
							<th>Descripcion</th>
							<th>Fecha</th>
							<th>Puja Inicial</th>
							<th>Estado</th>
							<th>Usuario Ganador</th>
							<th>Suma Ofertada</th>
							<th></th>
						</tr>
						<c:if test="${not empty listadoSubastas}">
						<c:forEach var="subastas" items="${listadoSubastas}">
							<tr>
								<td>${subastas.nombreSubasta}</td>
								<td>${subastas.descripcionSubasta}</td>
								<td><fmt:formatDate type="date" value="${subastas.fechaCreacion}" /></td>
								<td>$ ${subastas.pujaInicial}</td>
								<td><c:if test="${subastas.estaActiva == true}">Activa</c:if>
									<c:if test="${subastas.estaActiva == false}">Inactiva</c:if></td>
								<td><c:if test="${ empty subastas.ofertaGanadora}">No hay un Ganador</c:if>
									<c:if test="${not empty subastas.ofertaGanadora}"> ${subastas.ofertaGanadora.usuario.nombre} </c:if>
									</td>
									<td><c:if test="${ empty subastas.ofertaGanadora}">No hay Suma Ofertada </c:if>
									<c:if test="${not empty subastas.ofertaGanadora}"> ${subastas.ofertaGanadora.precioOferta} </c:if></td>
								<td><a href="detalle-subasta/${subastas.idSubasta}" class="btn btn-primary">Ver Detalle</a></td>
							</tr>
						</c:forEach>
						</c:if>
					</table>
					<c:if test="${empty listadoSubastas}">
					<p>No ha creado Subastas. Cree una!</p>
					</c:if>
				</div>
			</div>
		<div class="d-flex justify-content-between">
		<a href=${pageContext.servletContext.contextPath}/perfil class="btn btn-primary">Volver al Perfil</a>
		<c:if test="${not empty listadoSubastas}">
		<a href="excel-subastas" class="btn btn-primary">Descargar Listado en Excel</a>
		</c:if>
		</div>
		</div>
	</body>
</html>