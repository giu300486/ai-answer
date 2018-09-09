<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title></title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lavish.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-dropdownhover.min.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/questionnaire.css">
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css">
		
	</head>

	<body>
		<form id="questionnaire-information-form" class="form-horizontal" action='' method="POST">
		<br>
			<div class="panel-heading panelHeader">
	            <div class="row">
	                <div class="col-xs-6 col-md-6">
	                    <span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Nome</span>
	                </div>
	                <div class="col-xs-6 col-md-6">
                    	<button id="id-btn-nuovo-nome-questionario" class="btn btn-primary pull-right showCollapse" type="button" name="btn-nuovo-nome-questionario" data-target="#nome-questionario" data-toggle="collapse" aria-expanded="false">Nome Questionario <i class="fa fa-plus-square" aria-hidden="true"></i></button>
	                </div>
	            </div>
	        </div>
	        <div id="nome-questionario" class="panel-body panelContainer collapse">
	        	<div id="id-div-questionario" class="row">
      				<div class="col-xs-8 col-md-10">
      			  		<div class="row infoRow">
      			  			<div class="col-xs-12 col-md-4 mainInfoRow">
      			  				<span><strong>Nome Questionario</strong></span>
      			  			</div>
      			  			<div class="col-xs-12 col-md-6 secondaryInfoRow">
      			  				<input id="input-nome-questionario" name="nome-questionario" type="text" placeholder="Inserisci Nome Questionario">
             				</div>
             			</div>
             		</div>
           		</div>
	        </div>
	       	<div class="verticalSpacer"></div> 
	        <br>
			<div class="panel-heading panelHeader">
	            <div class="row">
	                <div class="col-xs-6 col-md-6">
	                    <span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Oggetti</span>
	                </div>
	                <div class="col-xs-6 col-md-6">
                    	<button id="id-btn-nuovo-oggetto" style="margin-left: 10px;" class="btn btn-primary pull-right showCollapse" type="button" name="btn-nuovo-oggetto" >Aggiungi Un Nuovo Oggetto <i class="fa fa-plus-square" aria-hidden="true"></i></button>
                    	<button id="id-btn-mostra-meno-oggetto" class="btn btn-primary pull-right showCollapse" type="button" name="btn-mostra-meno-oggetto" data-target="#oggetto" data-toggle="collapse" aria-expanded="false">Mostra Meno <i class="fa fa-minus-square" aria-hidden="true"></i></button>
	                </div>
	            </div>
	        </div>
	        <div id="oggetto" class="panel-body panelContainer collapse"></div>
	       	<div class="verticalSpacer"></div> 
	        <br>
	        <div class="panel-heading panelHeader">
	            <div class="row">
	                <div class="col-xs-6 col-md-6">
	                    <span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Domande</span>
	                </div>
	                <div class="col-xs-6 col-md-6">
	                	<button id="id-btn-nuova-domanda" style="margin-left: 10px;" class="btn btn-primary pull-right showCollapse" type="button" name="btn-nuova-domanda" >Aggiungi Una Nuova Domanda <i class="fa fa-plus-square" aria-hidden="true"></i></button>
                    	<button id="id-btn-mostra-meno-domanda" class="btn btn-primary pull-right showCollapse" type="button" name="btn-mostra-meno-domanda" data-target="#domanda" data-toggle="collapse" aria-expanded="false">Mostra Meno <i class="fa fa-minus-square" aria-hidden="true"></i></button>
	                </div>
	            </div>
	        </div>
	        <div id="domanda" class="panel-body panelContainer collapse"></div>
	        <div class="verticalSpacer"></div>   
	        <br>
	        <div class="panel-heading panelHeader">
	            <div class="row">
	                <div class="col-xs-6 col-md-10">
	                    <span class="panel-title"><i class="fa fa-bars fa-fw" aria-hidden="true"></i>Categoria</span>
	                </div>
	                <div class="col-xs-6 col-md-2">
	                	<select id="input-categoria" class="form-control" name="categoria">
							<option value="azione">Azione</option>
						  	<option value="fantasy">Fantasy</option>
						  	<option value="horror">Horror</option>
						  	<option value="club-dance">Club & Dance</option>
						  	<option value="metal">Metal</option>
						  	<option value="rock">Rock</option>
						  	<option value="basket">Basket</option>
						  	<option value="calcio">Calcio</option>
						  	<option value="tennis">Tennis</option>
						</select>
	                </div>
	            </div>
	        </div>
	        <br>
	        <div class="form-group">
	            <label class="col-md-5 control-label" for="singlebutton"></label>
	            <div class="col-md-4">
	                <button id="saveInfoButton" name="saveInfoButton" class="btn btn-primary">Save</button>
	            </div>
		  	</div>
            <div id="profile-information-auto-close-notification" class="alert alert-success hidden">
               <p id="profile-information-auto-message"></p>
           </div>
		</form>
        
        <script src="<%=request.getContextPath() %>/js/jquery-3.1.1.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
	    <script src="<%=request.getContextPath() %>/js/bootstrap-dropdownhover.min.js"></script>
	    <script src="<%=request.getContextPath() %>/js/header.js"></script>
		<script src="<%=request.getContextPath() %>/js/account.js"></script>
        
	</body>
</html>