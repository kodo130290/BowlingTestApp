package main.java.solutions.infinity.task.test.dovgan.servlets.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController;
import main.java.solutions.infinity.task.test.dovgan.model.utils.Constants;

public class AppListener implements ServletRequestListener {
	
	public void requestInitialized(ServletRequestEvent event) {
		
		GameController gameController =  GameController.getEntity();
		event.getServletContext().setAttribute(Constants.GAME_CONTROLLER, gameController);

	}

	public void requestDestroyed(ServletRequestEvent event) {
	}

}
