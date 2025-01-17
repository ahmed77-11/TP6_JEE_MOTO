<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="header.jsp"%>
	<p></p>
	<div class="container">
		<div class="card">
			<div class="card-header">Modification des Moto</div>
			<div class="card-body">
				<form action="update.do" method="post">
					<div hidden class="form-group">
						<label class="control-label">ID Moto :</label> <input type="text"
							name="id" class="form-control" value="${moto.idMoto}" />
					</div>
					<div class="form-group">
						<label class="control-label">Nom Moto :</label> <input type="text"
							name="nom" class="form-control" value="${moto.nomMoto}" />
					</div>
					<div class="form-group">
						<label class="control-label">Prix :</label> <input type="text"
							name="prix" class="form-control" value="${moto.prix}" />
					</div>
					<div class="form-group">
						<select name="model" class="form-control">
							<option value="${moto.model.idModel}" selected>${moto.model.nomModel}</option>
							<c:forEach items="${moModele.models}" var="modele">
								<c:if test="${modele.idModel != moto.model.idModel}">
									<option value="${modele.idModel}">${modele.nomModel}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div>
						<button type="submit" class="btn btn-primary">Modifier</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>