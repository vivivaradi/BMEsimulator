package test;

import org.junit.Before;
import org.junit.Test;
import source.Game;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    @Test
    public void TestGameSetup(){
        game = new Game("Vivi", "EASY");
        assertEquals("endtime_hour", 0, game.getEndTime().getHour());
        assertEquals("endtime day", 10, game.getEndTime().getDay());
    }
    @Test (expected = InvalidParameterException.class)
    public void TestGameSetupException() {
        game = new Game("Vivi", "dafuq");
    }
}
