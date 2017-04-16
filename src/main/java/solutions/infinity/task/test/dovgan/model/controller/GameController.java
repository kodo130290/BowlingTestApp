package main.java.solutions.infinity.task.test.dovgan.model.controller;

import main.java.solutions.infinity.task.test.dovgan.model.entities.Game;
import main.java.solutions.infinity.task.test.dovgan.model.utils.GenerateId;
import main.java.solutions.infinity.task.test.dovgan.servlets.BowlingMainServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GameController {
	
	private static final Logger log = LogManager.getLogger(GameController.class);
	private static GameController gameController;
	private Map<String, Game> gamesList = new HashMap<String, Game>();
	
	private GameController(){
	}
	
	public static GameController getEntity(){
		if (gameController == null){
			gameController = new GameController();
		}
		return gameController;
	}
	
	public String initiateNewGame(){
		String gameId = GenerateId.generateId().toString();
		gamesList.put(gameId, new Game());
		log.info("Game with id = " + gameId + " was initiated!");
		return gameId;
	}

	public void addRollToGame(String gameId, Integer roll){
        Game game = gamesList.get(gameId);
		game.addRoll(roll);
	}

	
	public ArrayList<String> getFramesToDisplay(String gameId){
        Game game = gamesList.get(gameId);
		return game.getFramesToDisplay();
	}

	public boolean isValidNewPins(String gameId, Integer pins){
        Game game = gamesList.get(gameId);
		return game.isValidNewPins(pins);
	}

	public boolean isGameFinished(String gameId){
        Game game = gamesList.get(gameId);
		return game.isGameFinished();
	}

	public ArrayList<String> getResultsByFrames(String gameId){
        Game game = gamesList.get(gameId);
		return game.calculateResultsByFrames();
	}

	public int getTotalScore(String gameId){
        Game game = gamesList.get(gameId);
		game.calculateResultsByFrames();
		if (isGameFinished(gameId)){ removeGameFromList(gameId); }
		return game.getTotalScore();
	}

	public void removeGameFromList(String gameId){
        gamesList.remove(gameId);
        log.info("Game with id = " + gameId + " has been finished and removed!");
    }
	
}
