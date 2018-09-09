package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.HomeService;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		
		String action = request.getParameter("action");
		HomeService homeservice = new HomeService(getServletContext().getRealPath("/"));
		
		switch(action){
		
			case "mostraHome":{
				HashMap<String,List<String>> questionnaires = homeservice.getQuestionnaires();
				request.getSession().setAttribute("questionnaires", questionnaires);
				getServletContext().getRequestDispatcher("/jsp/homePage.jsp").forward(request, response);
				break;
				
			}
			
			case "cercaQuestionario":{
				String wordMatch = request.getParameter("wordMatch");
				HashMap<String,List<String>> questionnaires = homeservice.getQuestionnaires(wordMatch);
				request.getSession().setAttribute("questionnaires", questionnaires);
				getServletContext().getRequestDispatcher("/jsp/homePage.jsp").forward(request, response);
				
				break;
				
			}
		
		}
		
	}

}