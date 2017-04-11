package main.java.solutions.infinity.task.test.dovgan.servlets

import java.io.IOException;

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController;
import main.java.solutions.infinity.task.test.dovgan.servlets.listeners.AppListener;

class BowlingMainServlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response)
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameController gameController = (GameController) request.getServletContext().getAttribute(AppListener.GAME_CONTROLLER)
		gameController.initiateNewGame()		
		request.setAttribute("resultsByFrames", Collections.nCopies(10, ""))
		request.setAttribute("framesToDisplay", Collections.nCopies(10, ""))
		request.getRequestDispatcher("game.jsp").forward(request, response)
		
	}
}
