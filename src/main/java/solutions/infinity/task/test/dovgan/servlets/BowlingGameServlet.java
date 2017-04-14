package main.java.solutions.infinity.task.test.dovgan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController;
import main.java.solutions.infinity.task.test.dovgan.servlets.listeners.AppListener;

public class BowlingGameServlet extends HttpServlet {
	
	private static final Logger log = LogManager.getLogger(BowlingGameServlet.class);
	
	private static final String RESULT_MESSAGE_NON_FINISHED = "Results: ";
	private static final String RESULT_MESSAGE_FINISHED_CAME = "Final results: ";
	private static final String WARN_MESSAGE_PINS_NUMBER = "Please set correct number of pins!!!";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameController gameController = (GameController) request.getServletContext().getAttribute(AppListener.GAME_CONTROLLER);
		String pins = request.getParameter("pins");
		if (pins.isEmpty()) {
			pins = "0";
		}
		
		if (!gameController.isValidNewPins(new Integer(pins))){
			request.setAttribute("warn", WARN_MESSAGE_PINS_NUMBER);
			request.setAttribute("resultsByFrames", gameController.getResultsByFrames());
			request.setAttribute("framesToDisplay", gameController.getFramesToDisplay());
			request.setAttribute("resultMessage", RESULT_MESSAGE_NON_FINISHED);
			
		}else{
			gameController.addRollToGame(new Integer(pins));

			request.setAttribute("resultsByFrames", gameController.getResultsByFrames());
			request.setAttribute("framesToDisplay", gameController.getFramesToDisplay());
			if (gameController.isGameFinished()){
				request.setAttribute("isTextFieldHidden", "none");
				request.setAttribute("resultMessage", RESULT_MESSAGE_FINISHED_CAME);
				log.info("Game has been finished!");
			}else{
				request.setAttribute("resultMessage", RESULT_MESSAGE_NON_FINISHED);
			}
		}
		
		request.getRequestDispatcher("game.jsp").forward(request, response);
		
	}
}
