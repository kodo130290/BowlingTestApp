package test.java.solutions.infinity.unit.tests

import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController
import org.junit.*;
import groovy.util.GroovyTestCase
class BowlingGameTests extends GroovyTestCase {
	
	GameController controller = GameController.getEntity()
	
	void testGutterGame(){
		controller.initiateNewGame()
		bulkSet(0, 20)
		assertToString(controller.getTotalScore(), "0")	
	}
	
	void testRollSpare(){
		controller.initiateNewGame()
		controller.addRollToGame(7)
		controller.addRollToGame(3)
		controller.addRollToGame(1)
		bulkSet(0, 17)
		assertToString(controller.getTotalScore(), "12")
	}
	
	void testRollStrike(){
		controller.initiateNewGame()
		controller.addRollToGame(10)
		controller.addRollToGame(3)
		controller.addRollToGame(1)
		bulkSet(0, 16)
		assertToString(controller.getTotalScore(), "18")
	}
	
	void testBeforeLastFrame(){
		controller.initiateNewGame()
		bulkSet(0, 16)
		controller.addRollToGame(10)
		controller.addRollToGame(3)
		controller.addRollToGame(1)
		assertToString(controller.getTotalScore(), "18")
		
	}
	
	void testLastFrame(){
		controller.initiateNewGame()
		bulkSet(0, 16)
		controller.addRollToGame(7)
		controller.addRollToGame(3)
		controller.addRollToGame(5)
		controller.addRollToGame(5)
		controller.addRollToGame(10)
		assertToString(controller.getTotalScore(), "35")
	}
	
	void testPerfectGame(){
		controller.initiateNewGame()
		bulkSet(10, 12)
		assertToString(controller.getTotalScore(), "300")
	}
	
	def bulkSet(pins, rolls){
		for (def i = 0; i < rolls ; i++){
			controller.addRollToGame(pins)
		}
	}
	
}
