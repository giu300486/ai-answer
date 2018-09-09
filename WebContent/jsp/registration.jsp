<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Registrazione</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish.css">
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min.css">
    	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css">
   	 	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css">
		
	</head>
	
	<body>
		<jsp:include page="${request.contextPath}/fragments/header.jsp"></jsp:include>
		
		<div class="container-fluid wrapper">
			<div class="row">
    			<div class="col-md-12 col-xs-12">
        			<h2 class="text-center text-warning"><i class="fa fa-registered fa-fw"></i> Registrazione <i class="fa fa-registered fa-fw"></i></h2>
    			</div>
  			</div>
  			<form id="registrationForm" class="form-horizontal" action='' method="POST">
  				<fieldset>
  					<div class="form-group required">
                		<label class="col-md-4 control-label" for="Username">Username</label>
                		<div class="col-md-4">
                    		<input id="username" name="username" type="text" placeholder="Inserisci username" class="form-control input-md" required="required">
                		</div>
            		</div>
            		<div class="form-group required">
                		<label class="col-md-4 control-label" for="password">Password</label>
                		<div class="col-md-4">
                    		<input id="password" name="password" type="password" placeholder="Inserisci password" class="form-control input-md" value="" required="required">
                		</div>
            		</div>
            		<div class="form-group required">
                		<label class="col-md-4 control-label" for="nome">Nome</label>
                		<div class="col-md-4">
                    		<input id="nome" name="nome" type="text" placeholder="Inserisci nome" class="form-control input-md" value="" required="required">
                		</div>
            		</div>
            		<div class="form-group required">
                		<label class="col-md-4 control-label" for="cognome">Cognome</label>
                		<div class="col-md-4">
                    		<input id="cognome" name="cognome" type="text" placeholder="Inserisci cognome" class="form-control input-md" value="" required="required">
                		</div>
            		</div>
            		<div class="form-group required">
                		<label class="col-md-4 control-label" for="email">Email</label>
                		<div class="col-md-4">
                    		<input id="email" name="email" type="email" placeholder="Inserisci email" class="form-control input-md" value="" required="required">
                		</div>
            		</div>
            		<div class="form-group">
        				<label class="col-md-4 control-label" for="singlebutton"></label>
        				<div class="col-md-4">
            				<button id="submitRegistration" name="saveInfoButton" class="btn btn-primary">Register</button>
        				</div>
    				</div>
     				<div id="registration-auto-close-notification" class="alert alert-success hidden">
        				<p id="registration-auto-message"></p>
     				</div>
  				</fieldset>
  			</form>
  			<div class="verticalSpacer"></div>
  			<div class="verticalSpacer"></div>
  			<div class="push"></div>
		</div>
		
		<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    	<script src="<%=request.getContextPath() %>/js/bootstrap-dropdownhover.min.js"></script>
    	<script src="<%=request.getContextPath() %>/js/header.js"></script>
    	<script src="<%=request.getContextPath() %>/js/account.js"></script>
	</body>
</html>