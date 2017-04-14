package main.java.solutions.infinity.task.test.dovgan.servlets.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController;

public class AppListener implements ServletRequestListener {
		
	public static final String GAME_CONTROLLER= "bowling/gameController";
	
	public void requestInitialized(ServletRequestEvent event) {
		
		GameController gameController =  GameController.getEntity();
		event.getServletContext().setAttribute(GAME_CONTROLLER, gameController);

	}

	public void requestDestroyed(ServletRequestEvent event) {
	}

}
