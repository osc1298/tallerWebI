<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<%@include file='templates/head.html'%>
		<title>Ofertar tu Permutacion</title>
	</head>
	<body>
		<%@include file='templates/header.jsp'%>
		<div class="container text-center pb-5">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
				
					<form:form action="../guardar-oferta-permutacion" method="POST" modelAttribute="ofertaPermutacion" enctype="multipart/form-data">
				    	<h3 class="form-signin-heading">Ofertar Permutacion</h3>
						<hr class="colorgraph"><br>
						<div class="py-2">
							<label>Descripcion</label>
							<form:textarea path="descripcion" id="descripcion" class="form-control" />
							<h4>Subida de Imagenes</h4>
							<form:input type="file" path="fotoOfertaPermutacion" id="fotoOfertaPermutacion" class="form-control-file" accept="image/jpeg" />
						</div>
						<div class="py-2">
							<button type="submit" class="btn btn-primary">Ofertar</button>
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