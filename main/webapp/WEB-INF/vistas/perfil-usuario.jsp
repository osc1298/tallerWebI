<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
	    <title>Perfil </title>
	</head>
	<body>
		
		<%@include file='templates/header.jsp'%>
	
		<div class="container text-center pb-5">
			<div class="row">
				<div class="col">
					<h1>${nick} </h1>
					<div class="container text-center pb-5">
					<c:if test="${not empty imageNombre}">
					<img src=${pageContext.servletContext.contextPath}/getImage/${imageNombre} alt="myImage" class="img-fluid" />
					</c:if>
					<c:if test="${empty imageNombre}">
					<img src=${pageContext.servletContext.contextPath}/img/noavatar.jpg alt="NoAvatar" class="img-fluid"/>
					</c:if>
					</div>
					<p><b>NOMBRE:</b> ${nombre}</p>
					<p><b>APELLIDO:</b> ${apellido}</p>
				</div>
				<div class="col text-left">
					<div class="pt-1">
						<div class="row">
							<div class="col">
								<b>SUBASTAS REALIZADAS:</b> ${numeroDeSubastas}
							</div>
							<div class="col text-right">
								<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/subastas-realizadas>Mis Subastas</a>
							</div>
						</div>
					</div>
					<div class="pt-1">
						<div class="row">
							<div class="col">			
								<b>PERMUTACIONES REALIZADAS:</b> ${numeroDePermutaciones}
							</div>
							<div class="col text-right">
								<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/permutaciones-realizadas>Mis Permutaciones</a>
							</div>
						</div>
					</div>
					<div class="pt-1">
						<div class="row">
							<div class="col">			
								<b>OFERTAS REALIZADAS:</b> ${numeroDeOfertas}
							</div>
							<div class="col text-right">
								<a class="btn btn-primary" href=${pageContext.servletContext.contextPath}/ofertas-realizadas>Mis Ofertas Realizadas</a>
							</div>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</body>
</html>