package main.java.solutions.infinity.task.test.dovgan.servlets;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController;
import main.java.solutions.infinity.task.test.dovgan.model.utils.Constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BowlingMainServlet extends HttpServlet {
	
	private static final Logger log = LogManager.getLogger(BowlingMainServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameController gameController = (GameController) request.getServletContext().getAttribute(Constants.GAME_CONTROLLER);
		String gameId = gameController.initiateNewGame();
		request.getSession(true).setAttribute(Constants.ATTR_GAME_ID, gameId);
		request.setAttribute(Constants.ATTR_RESULTS_BY_FRAMES, Collections.nCopies(10, ""));
		request.setAttribute(Constants.ATTR_FRAMES_TO_DISPLAY, Collections.nCopies(10, ""));
		request.getRequestDispatcher("game.jsp").forward(request, response);
		
	}
}
