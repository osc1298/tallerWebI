<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
	    <title>Registrar</title>
	</head>
	<body>
	<%@include file='templates/head.html'%>
		<div class = "container text-center">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">			
					<form:form action="crear-usuario" method="POST" modelAttribute="usuario" enctype="multipart/form-data">
				    	<h3 class="form-signin-heading">Registro - Echo</h3>
						<hr class="colorgraph"><br>
						<label>Nick</label>
						<form:input path="userName" id="userName" type="text" class="form-control" required="required"/>
						<div class="py-2">
 							 <form:errors path="userName" cssClass="alert alert-danger"/>
						</div>
						<label>Nombre</label>
						<form:input path="nombre" id="nombre" type="text" class="form-control" required="required"/>
						<div class="py-2">
 							 <form:errors path="nombre" cssClass="alert alert-danger"/>
						</div>
						<label>Apellido</label>
						<form:input path="apellido" id="apellido" type="text" class="form-control" required="required"/>
						<div class="py-2">
 							 <form:errors path="apellido" cssClass="alert alert-danger"/>
						</div>
						<label>Email</label>
						<form:input path="email" id="email" type="email" class="form-control" required="required"/>
						<div class="py-2">
 							 <form:errors path="email" cssClass="alert alert-danger"/>
						</div>
						<label>Password</label>
						<form:input path="password" id="password" type="password" class="form-control" required="required"/>
						<div class="py-2">
 							 <form:errors path="password" cssClass="alert alert-danger"/>
						</div>  		  
						<label>Subida de Imagenes</label>
						<form:input type="file" path="fotoImagen" id="fotoImagen" class="form-control-file" accept="image/jpeg" required="required"/>
						<div class="py-5">
							<button class="btn btn-lg btn-primary btn-block" Type="Submit">Registrar</button>
						</div>
					</form:form>
	
					<c:if test="${not empty error}">
				        <h4><span>${error}</span></h4>
				        <br>
			        </c:if>
			        <div class="py-5">
			        	<a href="home" class="btn btn-secondary">Ir a Home</a>
			        </div>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
		
		<%@include file='templates/footer.html'%>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	</body>
</html>