package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.Domanda;
import service.QuestionnaireService;
import service.Memento;

/**
 * Servlet implementation class QuestionnaireServlet
 */
@WebServlet("/QuestionnaireServlet")
public class QuestionnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionnaireServlet() {
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
		QuestionnaireService questionnaireService = new QuestionnaireService(getServletContext().getRealPath("/"));
		
		switch (action){
		
			case "createQuestionnaire":{
				
				List<String> errors = questionnaireService.createQuestionnaire(request.getParameter("usernameAmministratore"), request.getParameter("fileXml"));
				
				String json = new Gson().toJson(errors);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
				
				break;
			
			}
			
			case "showQuestionnaires":{
				
				String username = request.getParameter("username");
				List<String> questionnaires = questionnaireService.getQuestionnaires(username);
				
				String json = new Gson().toJson(questionnaires);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
				
				break;
			
			}
			
			case "showQuestionnaire":{
		
				String nomeQuestionario = request.getParameter("nomeQuestionario");
				String username = request.getParameter("username");
				String questionnaire = questionnaireService.getQuestionnaire(nomeQuestionario,username);
				
				if(questionnaire != null){
					toSend.addProperty("showQuestionnaire", "ok");
					toSend.addProperty("questionnaire", questionnaire);
				}
				else{
					toSend.addProperty("showQuestionnaire", "fail");
				}
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().println(toSend);

				break;
			
			}
			
			case "checkQuestionnaire":{
				
				String username = request.getParameter("username");
				
				HashMap<String,Boolean> checkQuestionnaire = questionnaireService.checkQuestionnaire(username);
				
				String gson = null;
				if(checkQuestionnaire != null){
					gson = new Gson().toJson(checkQuestionnaire);
				}
				else{
					gson = new Gson().toJson(checkQuestionnaire = new HashMap<>());
				}
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(gson);
				
				break;
				
			}
			
			case "checkUnckeckQuestionnaire":{
				
				String username = request.getParameter("username");
				String nameQuestionnaire = request.getParameter("nameQuestionnaire");
				
				toSend.addProperty("checkUnckeckQuestionnaire", questionnaireService.checkUnckeckQuestionnaire(username, nameQuestionnaire));
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().println(toSend);
				
				break;
				
			}
			
			case "modifyQuestionnaire":{
				
				String username = request.getParameter("usernameAmministratore");
				String fileXml = request.getParameter("fileXml");
				String nomeQuestionario = request.getParameter("nomeQuestionario");
				
				List<String> errors = questionnaireService.modifyQuestionnaire(username, fileXml, nomeQuestionario);
				
				String json = new Gson().toJson(errors);
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
				
				break;
				
			}
			
			case "modifyCSS":{
				
				String username = request.getParameter("username");
				String optionSelected = request.getParameter("optionSelected");
				String css = questionnaireService.fileCSS(optionSelected, username);
				
				if(css != null){
					toSend.addProperty("modifyCSS", "ok");
					toSend.addProperty("css", css);
				}
				else{
					toSend.addProperty("modifyCSS", "fail");
				}
				
				request.getSession().setAttribute("optionSelected", optionSelected);
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().println(toSend);
				
				
				break;
				
			}
			
			case "selectedCSS":{
				
				String username = request.getParameter("username");
				String nameQuestionnaire = request.getParameter("questionarioCSSSelezionto");
				String optionSelected = request.getParameter("optionSelected");
				
				if(optionSelected != null){
					toSend.addProperty("selectedCSS", "ok");
					toSend.addProperty("optionSelected", optionSelected);
					questionnaireService.selectedCSS(username, nameQuestionnaire, optionSelected, getServletContext().getContextPath());
//					request.getSession().setAttribute("optionSelected", optionSelected);
				}
				else{
					toSend.addProperty("selectedCSS", "fail");
				}
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().println(toSend);
				
				
				break;
				
			}
			
			case "saveCSS":{
				
				String username = request.getParameter("username");
				String nomeFileCSS = request.getParameter("nomeFileCSS");
				String contentCSS = request.getParameter("contentCSS");
				
				if(questionnaireService.saveCSS(username, nomeFileCSS, contentCSS).equals("non esiste")){
					toSend.addProperty("saveCSS", "ok");
				}
				else{
					toSend.addProperty("saveCSS", "fail");
				}
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().println(toSend);
				
				break;
				
			}
			
			case "overwriteFileCSS":{
				
				String username = request.getParameter("username");
				String nomeFileCSS = request.getParameter("nomeFileCSS");
				String contentCSS = request.getParameter("contentCSS");
				
				if(questionnaireService.overwriteFileCSS(username, nomeFileCSS, contentCSS)){
					toSend.addProperty("overwriteFileCSS", "ok");
				}
				else{
					toSend.addProperty("overwriteFileCSS", "fail");
				}
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().println(toSend);
				
				break;
				
			}
			
			case "deleteCSS":{
				
				String username = request.getParameter("username");
				String nomeFileCSS = request.getParameter("nomeFileCSS");
				
				if(questionnaireService.deleteCSS(username, nomeFileCSS)){
					toSend.addProperty("deleteCSS", "ok");
				}
				else{
					toSend.addProperty("deleteCSS", "fail");
				}
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().println(toSend);
				
				break;
				
			}
			
			case "removeQuestionnaire":{
				
				String username = request.getParameter("username");
				String nomeQuestionario = request.getParameter("nomeQuestionario");
				
				if(questionnaireService.removeQuestionnaire(username, nomeQuestionario)){
					toSend.addProperty("removeQuestionnaire", "ok");
				}
				else{
					toSend.addProperty("removeQuestionnaire", "fail");
				}
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().println(toSend);
				
				break;
				
			}
			
			case "initQuestionnaire":{
				
				String username = request.getParameter("username");
				String nameQuestionnaire = request.getParameter("nameQuestionnaire");
				
				request.getSession().setAttribute("output", null);
				
				Domanda domandaCorrente = questionnaireService.initQuestionnaire(username,nameQuestionnaire);
				
//				Stack<Memento> mementoStack = questionnaireService.getMementoStack();
//				Domanda domandaCorrente = mementoStack.get(mementoStack.size()-1).getDomandaCorrente();
				
				request.getSession().setAttribute("domandaCorrente", domandaCorrente);
				request.getSession().setAttribute("mementoStack", questionnaireService.getMementoStack());
				request.getSession().setAttribute("nameQuestionnaire", nameQuestionnaire);
				request.getSession().setAttribute("count", 1);
				
				String pathCSS = questionnaireService.getPathCSS(username, nameQuestionnaire);
				request.getSession().setAttribute("pathCSS", pathCSS);
				
				request.getSession().setAttribute("username", username);
				
				getServletContext().getRequestDispatcher("/jsp/motore1.jsp").forward(request, response);
				
				break;
				
			}
			
			case "response":{
				
//				int count = Integer.parseInt(String.valueOf(request.getParameter("count"))) + 1;
				request.getSession().setAttribute("count", (int) request.getSession().getAttribute("count")+1);
				
				
				String username = request.getParameter("username");
				String nameQuestionnaire = request.getParameter("nameQuestionnaire");
				String contentResponse = request.getParameter("contentResponse");
				Stack<Memento> mementoStack = (Stack<Memento>) request.getSession().getAttribute("mementoStack");
				
				Domanda domandaCorrente = questionnaireService.execute(username, nameQuestionnaire, contentResponse, mementoStack);
				
//				mementoStack = questionnaireService.getMementoStack();
//				Domanda domandaCorrente = mementoStack.get(mementoStack.size()-1).getDomandaCorrente();
				
				if(domandaCorrente.getCodiceDomanda() != null)
					request.getSession().setAttribute("domandaCorrente", domandaCorrente);
				else{
					List<String> output = mementoStack.get(mementoStack.size()-1).getOutput();
					request.getSession().setAttribute("output", output);
				}
					
				
				request.getSession().setAttribute("mementoStack", questionnaireService.getMementoStack());
				request.getSession().setAttribute("nameQuestionnaire", nameQuestionnaire);
				
//				System.out.println(questionnaireService.toString());
				
				getServletContext().getRequestDispatcher("/jsp/motore1.jsp").forward(request, response);
				
				//RENDERE LA PAGINA DEL MOTORE CON AJAX IMPOSTANDO SIA LA PAGINA DI OUTPUT SIA LE DOMANDE CHE LE RISPOSTE CON AJAX STESSO.
				//SUL PULSANTE ESEGUI RICORDARSI DI FARE WINDOW.LOCATION.HREF = URL, DOVE URL E' LA PAGINA DEL MOTORE
				
				break;
				
			}
			
			case "restoreStoricoDomande":{
				
				Stack<Memento> mementoStack = (Stack<Memento>) request.getSession().getAttribute("mementoStack");
//				int sizeCorrenteMementoStack = mementoStack.size();
				
				String codiceDomandaStoricoDomande = request.getParameter("id");
				Domanda domandaCorrente = questionnaireService.restoreState(mementoStack,codiceDomandaStoricoDomande);
				
//				int nuovaSizeCorrenteMementoStack = sizeCorrenteMementoStack - mementoStack.size();
//				int count = Integer.parseInt(String.valueOf(request.getParameter("count"))) - nuovaSizeCorrenteMementoStack;
//				request.getSession().setAttribute("count", count);
				
//				Domanda domandaCorrente = questionnaireService.getMementoStack().get(mementoStack.size()-1).getDomandaCorrente();
				
				request.getSession().setAttribute("domandaCorrente", domandaCorrente);
				request.getSession().setAttribute("mementoStack", questionnaireService.getMementoStack());

//				System.out.println(questionnaireService.toString());
				
				getServletContext().getRequestDispatcher("/jsp/motore1.jsp").forward(request, response);
				
				break;
				
			}
			
//			case "correct":{
//				
//				int count = Integer.parseInt(String.valueOf(request.getParameter("count"))) - 1;
//				request.getSession().setAttribute("count", count);
//				
//				questionnaireService.restoreState((Stack<Memento>) request.getSession().getAttribute("mementoStack"));
//				
//				Stack<Memento> mementoStack = questionnaireService.getMementoStack();
//				Domanda domandaCorrente = mementoStack.get(mementoStack.size()-1).getDomandaCorrente();
//				
//				request.getSession().setAttribute("domandaCorrente", domandaCorrente);
//				request.getSession().setAttribute("mementoStack", questionnaireService.getMementoStack());
//
////				System.out.println(questionnaireService.toString());
//				
//				getServletContext().getRequestDispatcher("/jsp/motore.jsp").forward(request, response);
//				
//				break;
//				
//			}
		
		}
		
	}

}