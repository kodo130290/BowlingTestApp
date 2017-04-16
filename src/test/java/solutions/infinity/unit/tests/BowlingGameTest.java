package test.java.solutions.infinity.unit.tests;

import junit.framework.TestCase;
import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController;
import org.junit.*;

public class BowlingGameTest extends TestCase{

	GameController controller = GameController.getEntity();

    @Test
    public void testGutterGame(){
        String gameId = controller.initiateNewGame();
        bulkSet(0, 20, gameId);
        assertEquals( 0, controller.getTotalScore(gameId) );
    }

    @Test
    public void testRollSpare(){
        String gameId = controller.initiateNewGame();
        controller.addRollToGame(gameId, 7);
        controller.addRollToGame(gameId, 3);
        controller.addRollToGame(gameId, 1);
        bulkSet(0, 17, gameId);
        assertEquals( 12, controller.getTotalScore(gameId) );
    }

    @Test
    public void testRollStrike(){
        String gameId = controller.initiateNewGame();
        controller.addRollToGame(gameId, 10);
        controller.addRollToGame(gameId, 3);
        controller.addRollToGame(gameId, 1);
        bulkSet(0, 16, gameId);
        assertEquals( 18, controller.getTotalScore(gameId) );
    }

    @Test
    public void testBeforeLastFrame(){
        String gameId = controller.initiateNewGame();
        bulkSet(0, 16, gameId);
        controller.addRollToGame(gameId, 10);
        controller.addRollToGame(gameId, 3);
        controller.addRollToGame(gameId, 1);
        assertEquals( 18, controller.getTotalScore(gameId) );

    }

    @Test
    public void testLastFrame(){
        String gameId = controller.initiateNewGame();
        bulkSet(0, 16, gameId);
        controller.addRollToGame(gameId, 7);
        controller.addRollToGame(gameId, 3);
        controller.addRollToGame(gameId, 5);
        controller.addRollToGame(gameId, 5);
        controller.addRollToGame(gameId, 10);
        assertEquals( 35, controller.getTotalScore(gameId) );
    }

    @Test
    public void testPerfectGame(){
        String gameId = controller.initiateNewGame();
        bulkSet(10, 12, gameId);
        assertEquals( 300, controller.getTotalScore(gameId) );
    }

    private void bulkSet(Integer pins, Integer rolls, String gameId){
        for (int i = 0; i < rolls ; i++){
            controller.addRollToGame(gameId,pins);
        }
    }
}
