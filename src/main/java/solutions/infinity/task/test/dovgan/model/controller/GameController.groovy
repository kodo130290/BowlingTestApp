package main.java.solutions.infinity.task.test.dovgan.model.controller

import main.java.solutions.infinity.task.test.dovgan.model.entities.Frame;
import main.java.solutions.infinity.task.test.dovgan.model.entities.Game;


class GameController {
	
	private static def gameController
	private Game game
	
	private GameController(){
	}
	
	public static GameController getEntity(){
		if (gameController == null){
			gameController = new GameController()	
		}
		return gameController
	}
	
	def initiateNewGame(){
		this.game = new Game()
	}
	
	def addRollToGame(roll){
		game.addRoll(roll)
	}
	
	def isLastFrame(){
		return game.currentFrameNumber == 10
	}
	
	public ArrayList<String> getFramesToDisplay(){
		return game.getFramesToDisplay()
	}
	
	def getFrameList(){
		return game.frameList
	}
	
	def isValidNewPins(pins){
		return game.isValidNewPins(pins)
	}
	
	def isGameFinished(){
		return game.isGameFinished
	}
	
	def getResultsByFrames(){
		game.calculateResultsByFrames()
	}
	
	def getTotalScore(){
		game.calculateResultsByFrames()
		game.totalScore
	}
	

}
