<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Historial de ofertas</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<h2>Su Historial de Ofertas Realizadas</h2>
			<div class="row pt-5">
			 	<div class="col-md-8">
					<table width="100%" cellpadding="12">
						<tr class="table-info">
							<th>Permutaci&oacute;n</th>
							<th>Descripci&oacute;n</th>
							<th>Fecha</th>
							<th>Ganador</th>
						</tr>
						<c:if test="${not empty listadoOfertasPermutacion}">
						<c:forEach var="ofertasPermutacion" items="${listadoOfertasPermutacion}">
							<tr>
								<td>${ofertasPermutacion.permutacion.nombrePermutacion}</td>
								<td>${ofertasPermutacion.descripcion}</td>
								<td>${ofertasPermutacion.fechaOferta}</td>
								<td><c:if test="${empty ofertasPermutacion.permutacion.ofertaGanadora}">No hay Ganador</c:if>
									<c:if test="${not empty ofertasPermutacion.permutacion.ofertaGanadora}">
									<c:if test="${ofertasPermutacion.permutacion.ofertaGanadora.idOfertaPermutacion == ofertasPermutacion.idOfertaPermutacion}"> Has Ganado!</c:if>
									<c:if test="${ofertasPermutacion.permutacion.ofertaGanadora.idOfertaPermutacion != ofertasPermutacion.idOfertaPermutacion}"> No fue elegida </c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</c:if>

					</table>
					<c:if test="${empty listadoOfertasPermutacion}">
						<div class="justify-content-md-center">No ha hecho ofertas en Permutaciones. Cree una!</div>
					</c:if>
				</div>
			</div>
			<div class="row pt-5">
			<div class="col-md-8">
					<table width="100%" cellpadding="12">
						<tr class="table-info">
							<th>Subasta</th>
							<th>Precio</th>
							<th>Fecha</th>
							<th>Ganador</th>
						</tr>
						<c:if test="${not empty listadoOfertasSubasta}">
						<c:forEach var="ofertasSubasta" items="${listadoOfertasSubasta}">
							<tr>
								<td>${ofertasSubasta.subasta.nombreSubasta}</td>
								<td>${ofertasSubasta.precioOferta}</td>
								<td>${ofertasSubasta.fechaOfertaSubasta}</td>
								<td><c:if test="${empty ofertasSubasta.subasta.ofertaGanadora}">No hay Ganador</c:if>
									<c:if test="${not empty ofertaSubasta.subasta.ofertaGanadora}">
									<c:if test="${ofertaSubasta.subasta.ofertaGanadora.idOperacionSubasta == ofertaSubasta.idOperacionSubasta}"> Has Ganado!</c:if>
									<c:if test="${ofertaSubasta.subasta.ofertaGanadora.idOperacionSubasta != ofertaSubasta.idOpertacionSubasta}"> No fue elegida </c:if>
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</c:if>
					</table>
					<c:if test="${empty listadoOfertasPermutacion}">
						<p>No ha hecho ofertas en Subastas. Cree una!</p>
					</c:if>
				</div>
			</div>
		</div>
	</body>
</html>