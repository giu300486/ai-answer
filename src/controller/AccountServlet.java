package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Utente;
import service.AccountService;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JsonObject toSend = new JsonObject();
		
		String action = request.getParameter("action");
		AccountService service = new AccountService(getServletContext().getRealPath("/"));
		
		switch(action){
		
			case "registration":{
				
				Utente amministratore = new Utente();
				amministratore.setUsername(request.getParameter("username"));
				amministratore.setPassword(request.getParameter("password"));
				amministratore.setNome(request.getParameter("nome"));
				amministratore.setCognome(request.getParameter("cognome"));
				amministratore.setEmail(request.getParameter("email"));
				
				if(service.registrazioneAmministratore(amministratore)){
					toSend.addProperty("registrationResult", "ok");
					request.getSession().setAttribute("loggedUser", amministratore);
					
					Path path = Paths.get(getServletContext().getRealPath("/") + "/xml/" + request.getParameter("username") + "/");
			        //if directory exists?
			        if (!Files.exists(path)) {
			            try {
			                Files.createDirectories(path);
			            } catch (IOException e) {
			                //fail to create directory
			                e.printStackTrace();
			            }
			            
			            File source = new File(getServletContext().getRealPath("/") + "/css/default.css");
			            File destination = new File(getServletContext().getRealPath("/") + "/xml/" + request.getParameter("username") + "/default.css");
			            Files.copy(source.toPath(), destination.toPath());
			        }
				}
				else{
					toSend.addProperty("registrationResult", "fail");
				}
				
				response.getWriter().println(toSend);
				
				break;
			}
			
			case "login":{
				
				Utente amministratore = service.loginAmministratore(request.getParameter("username"), request.getParameter("password"));
				
				if(amministratore != null){
					toSend.addProperty("loginResult", "ok");
					request.getSession().setAttribute("loggedUser", amministratore);
				}
				else{
					toSend.addProperty("loginResult", "fail");
				}
				
				response.getWriter().println(toSend);
				
				break;
				
			}
			
			case "logout":{
				
				request.getSession().invalidate();
				String nextJSP = "/HomeServlet?action=mostraHome";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
				
				break;
				
			}
			
			case "myProfile":{
				
				List<String> fileCSS = service.filesCSS(request.getParameter("username"));
				
				String json = new Gson().toJson(fileCSS);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
				
				break;
				
			}
			
			case "modifyInfoProfile":{
				
				Utente amministratore = new Utente();
				amministratore.setUsername(request.getParameter("username"));
				amministratore.setPassword(request.getParameter("password"));
				amministratore.setNome(request.getParameter("nome"));
				amministratore.setCognome(request.getParameter("cognome"));
				amministratore.setEmail(request.getParameter("email"));
				
				if(service.modifyInfoProfile(amministratore)){
					toSend.addProperty("modifyInfoProfile", "ok");
					request.getSession().setAttribute("loggedUser", amministratore);
				}
				else{
					toSend.addProperty("modifyInfoProfile", "fail");
				}
				
				response.getWriter().println(toSend);
				
				break;
				
			}
			
//			case "response":{
//			
//				String idDomanda = request.getParameter("idDomanda");
//				String idRisposta = request.getParameter("idRisposta");
//				
//				String parse = "<div id='id-div-risposta" + idRisposta + "'>"+
//						"<div class='col-xs-12 col-md-4 mainInfoRow'>"+
//						"<span><strong>Risposta</strong></span>"+
//					"</div>"+
//					"<div class='col-xs-12 col-md-4 secondaryInfoRow'>"+
//						"<input id='input-risposta" + idRisposta + "' name='risposta' type='text' placeholder='Inserisci Risposta'>"+
//					"</div>"+
//					"<div class='col-xs-12 col-md-2 secondaryInfoRow'></div>"+
//					"<div class='col-xs-12 col-md-2 secondaryInfoRow'>"+
//						"<button id='remove-risposta" + idRisposta + "' style='margin-left: 30px;' type='button' class='btn btn-danger btn-sm'>Rimuovi Risposta</button>"+
//					"</div>"+
//					"<script type='text/javascript'>"+
//				        "$('#remove-risposta" + idRisposta + "').on('click', function () {"+
//					        "$.each(map.get(" + idDomanda + "), function(i){"+
//							    "if(map.get(" + idDomanda + ")[i] === " +  idRisposta + ") {"+
//							    	"map.get(" + idDomanda + ").splice(i,1);"+
//							        "return false;"+
//							    "}"+
//							"});"+
//							"map.forEach(function(value, key) {"+
//            					"alert(key + '  ' + value);"+
//            				"});"+
//				        	"$('#id-div-risposta" + idRisposta + "').remove();"+
//				        "});"+
//				    "</script>"+
//				    "</div>";
//				
//				toSend.addProperty("parse", parse);
//				
//				response.getWriter().println(toSend);
//				
//				break;
//			
//			}
		}
		
	}

}