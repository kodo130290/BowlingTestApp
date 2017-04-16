package main.java.solutions.infinity.task.test.dovgan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController;
import main.java.solutions.infinity.task.test.dovgan.model.utils.Constants;

public class BowlingGameServlet extends HttpServlet {
	
	private static final Logger log = LogManager.getLogger(BowlingGameServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameController gameController = (GameController) request.getServletContext().getAttribute(Constants.GAME_CONTROLLER);
		String gameId = (String) request.getSession().getAttribute(Constants.ATTR_GAME_ID);
		if (gameId == null){
			log.debug("Session expired!");
			response.sendRedirect(request.getContextPath() + "/error");
			return;
		}
		String pins = request.getParameter(Constants.ATTR_NUMBER_OF_PINS);
		if (pins.isEmpty()) {
			pins = "0";
		}

		if (!gameController.isValidNewPins(gameId, new Integer(pins))){
			request.setAttribute(Constants.ATTR_WARNING, Constants.WARN_MESSAGE_PINS_NUMBER);
			request.setAttribute(Constants.ATTR_RESULTS_BY_FRAMES, gameController.getResultsByFrames(gameId));
			request.setAttribute(Constants.ATTR_FRAMES_TO_DISPLAY, gameController.getFramesToDisplay(gameId));
			request.setAttribute(Constants.ATTR_RESULT_MESSAGE, Constants.RESULT_MESSAGE_NON_FINISHED);
			
		}else{
			gameController.addRollToGame(gameId, new Integer(pins));

			request.setAttribute(Constants.ATTR_RESULTS_BY_FRAMES, gameController.getResultsByFrames(gameId));
			request.setAttribute(Constants.ATTR_FRAMES_TO_DISPLAY, gameController.getFramesToDisplay(gameId));
			if (gameController.isGameFinished(gameId)){
				request.setAttribute(Constants.ATTR_TEXT_FIELD_HIDDEN, "none");
				request.setAttribute(Constants.ATTR_RESULT_MESSAGE, Constants.RESULT_MESSAGE_FINISHED_CAME);
				if (gameController.isGameFinished(gameId)){ gameController.removeGameFromList(gameId); }
			}else{
				request.setAttribute(Constants.ATTR_RESULT_MESSAGE, Constants.RESULT_MESSAGE_NON_FINISHED);
			}
		}
		
		request.getRequestDispatcher("game.jsp").forward(request, response);
		
	}
}
