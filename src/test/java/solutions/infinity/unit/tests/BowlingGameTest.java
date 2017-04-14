package test.java.solutions.infinity.unit.tests;

import junit.framework.TestCase;
import main.java.solutions.infinity.task.test.dovgan.model.controller.GameController;
import org.junit.*;

public class BowlingGameTest extends TestCase{

    GameController controller = GameController.getEntity();

    @Test
    public void testGutterGame(){
        controller.initiateNewGame();
        bulkSet(0, 20);
        assertEquals( 0, controller.getTotalScore() );
    }

    @Test
    public void testRollSpare(){
        controller.initiateNewGame();
        controller.addRollToGame(7);
        controller.addRollToGame(3);
        controller.addRollToGame(1);
        bulkSet(0, 17);
        assertEquals( 12, controller.getTotalScore() );
    }

    @Test
    public void testRollStrike(){
        controller.initiateNewGame();
        controller.addRollToGame(10);
        controller.addRollToGame(3);
        controller.addRollToGame(1);
        bulkSet(0, 16);
        assertEquals( 18, controller.getTotalScore() );
    }

    @Test
    public void testBeforeLastFrame(){
        controller.initiateNewGame();
        bulkSet(0, 16);
        controller.addRollToGame(10);
        controller.addRollToGame(3);
        controller.addRollToGame(1);
        assertEquals( 18, controller.getTotalScore() );

    }

    @Test
    public void testLastFrame(){
        controller.initiateNewGame();
        bulkSet(0, 16);
        controller.addRollToGame(7);
        controller.addRollToGame(3);
        controller.addRollToGame(5);
        controller.addRollToGame(5);
        controller.addRollToGame(10);
        assertEquals( 35, controller.getTotalScore() );
    }

    @Test
    public void testPerfectGame(){
        controller.initiateNewGame();
        bulkSet(10, 12);
        assertEquals( 300, controller.getTotalScore() );
    }

    private void bulkSet(Integer pins, Integer rolls){
        for (int i = 0; i < rolls ; i++){
            controller.addRollToGame(pins);
        }
    }
}
