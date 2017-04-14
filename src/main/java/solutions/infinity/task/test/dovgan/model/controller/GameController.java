package main.java.solutions.infinity.task.test.dovgan.model.controller;

import main.java.solutions.infinity.task.test.dovgan.model.entities.Game;

import java.util.ArrayList;


public class GameController {
	
	private static GameController gameController;
	private Game game;
	
	private GameController(){
	}
	
	public static GameController getEntity(){
		if (gameController == null){
			gameController = new GameController();
		}
		return gameController;
	}
	
	public void initiateNewGame(){
		this.game = new Game();
	}

	public void addRollToGame(Integer roll){
		game.addRoll(roll);
	}

	
	public ArrayList<String> getFramesToDisplay(){
		return game.getFramesToDisplay();
	}

	public boolean isValidNewPins(Integer pins){
		return game.isValidNewPins(pins);
	}

	public boolean isGameFinished(){
		return game.isGameFinished();
	}

	public ArrayList<String> getResultsByFrames(){
		return game.calculateResultsByFrames();
	}

	public int getTotalScore(){
		game.calculateResultsByFrames();
		return game.getTotalScore();
	}
	

}
