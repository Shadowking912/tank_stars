package com.mygdx.game.stars;

import org.junit.Test;

import static com.mygdx.game.stars.GameScreen.turn;
import static org.junit.Assert.assertEquals;

public class Mytest
{
    @Test
    public void Fuel()
    {
        assertEquals(100,turn.getfuels());
    }
}
