<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
	
		<title></title>
	
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.standalone.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css">
	
		<script type="text/javascript">
	    	var context="${pageContext.request.contextPath}";
	    </script>
	
	</head>
	
	<body>
		<div class="container-fluid wrapper">
	  		<div class="row">
	    		<div class="col-md-12 col-xs-12">
	        		<h2 class="text-center text-warning"><i class="fa fa-user fa-fw"></i> Profilo <i class="fa fa-user fa-fw"></i></h2>
	    		</div>
	  		</div>
	  		<div class="verticalSpacer"></div>
		  	<form id="accountInformationForm" class="form-horizontal" action='' method="POST">
		        <fieldset>
		        	<div class="form-group required">
		                <label class="col-md-4 control-label" for="Username">Username</label>
		                <div class="col-md-4">
		                    <input id="username" name="username" type="text" placeholder="Inserisci username" class="form-control input-md" value="${loggedUser.username}" disabled>
		                </div>
		            </div>
		            <div class="form-group required">
		                <label class="col-md-4 control-label" for="password">Password</label>
		                <div class="col-md-4">
		                    <input id="password" name="password" type="password" placeholder="Inserisci password" class="form-control input-md" value="${loggedUser.password}" required="required">
		                </div>
		            </div>
		            <div class="form-group required">
		                <label class="col-md-4 control-label" for="nome">Nome</label>
		                <div class="col-md-4">
		                    <input id="nome" name="nome" type="text" placeholder="Inserisci nome" class="form-control input-md" value="${loggedUser.nome}" required="required">
		                </div>
		            </div>
		            <div class="form-group required">
		                <label class="col-md-4 control-label" for="cognome">Cognome</label>
		                <div class="col-md-4">
		                    <input id="cognome" name="cognome" type="text" placeholder="Inserisci cognome" class="form-control input-md" value="${loggedUser.cognome}" required="required">
		                </div>
		            </div>
		            <div class="form-group required">
		                <label class="col-md-4 control-label" for="email">Email</label>
		                <div class="col-md-4">
		                    <input id="email" name="email" type="email" placeholder="Inserisci email" class="form-control input-md" value="${loggedUser.email}" required="required">
		                </div>
		            </div>
		            <div class="form-group">
		                <label class="col-md-4 control-label" for="singlebutton"></label>
		                <div class="col-md-4">
		                    <button id="saveInfoButton" name="saveInfoButton" class="btn btn-primary" disabled>Save</button>
		                </div>
		            </div>
		             <div id="profile-information-auto-close-notification" class="alert alert-success hidden">
		                <p id="profile-information-auto-message"></p>
		            </div>
		        </fieldset>
	    	</form>
	   		<div class="verticalSpacer"></div>
	    	<div class="push"></div>
		</div>
	
		<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	    <script src="<%=request.getContextPath() %>/scripts/bootstrap-dropdownhover.min.js"></script>
	    <script src="<%=request.getContextPath() %>/scripts/header.js"></script>
	    <script src="<%=request.getContextPath() %>/scripts/account.js"></script>
	</body>
</html>
