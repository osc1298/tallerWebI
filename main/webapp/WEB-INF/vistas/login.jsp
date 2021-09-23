<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
	    <title>Login</title>
	</head>
	<body>

		<div class = "container text-center">
			<div class="row">
				<div class="col-md-3"></div>
				<div id="loginbox" class="col-md-6">
					<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
					<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
					<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
					<form:form action="validar-login" method="POST" modelAttribute="usuario">
				    	<h3 class="form-signin-heading">Por favor, inicie sesi&oacute;n</h3>
						<hr class="colorgraph"><br>
						<div class="py-2">
							<h4>Ingrese su mail:</h4>
							<form:input path="email" id="email" type="email" class="form-control" />
						</div>
						
						<div class="py-2">
							<h4>Ingrese su password:</h4>
							<form:input path="password" type="password" id="password" class="form-control"/>  
						</div>
						<div class="pt-3">
							<button class="btn btn-lg btn-primary btn-block" Type="Submit">Ingresar</button>
						</div>
						
						<div class="pt-3">
						<%--Bloque que es visible si el elemento error no está vacío	--%>
						<c:if test="${not empty error}">
   						<div class="alert alert-danger">
   							${error}
   						</div>
			        	</c:if>
						</div>
					</form:form>
			        <div class="py-3">
			        	<a href="home" class="btn btn-secondary">Ir a Home</a>
			        </div>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
		
		<%@include file='templates/footer.html'%>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	</body>
</html>
