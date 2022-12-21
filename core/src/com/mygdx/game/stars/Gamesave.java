package com.mygdx.game.stars;

import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.Vector;

public class Gamesave implements Serializable {
    private Vector2 player1pos,player2pos;
    private int turn;

    public Vector2 getPlayer1pos() {
        return player1pos;
    }

    public Vector2 getPlayer2pos() {
        return player2pos;
    }

    public int getTankid1() {
        return tankid1;
    }

    public int getTankid2() {
        return tankid2;
    }

    public float getHealth1() {
        return health1;
    }

    public float getHealth2() {
        return health2;
    }

    private int tankid1,tankid2;
    private float health1,health2;

    private float fuel1,fuel2;
    public Gamesave(Vector2 p1,Vector2 p2, int tu,int id1,int id2,float h1,float h2,float f1,float f2){
        player1pos=p1;
        player2pos=p2;
        turn=tu;
        tankid1=id1;
        tankid2=id2;
        health1=h1;
        health2=h2;
        fuel1=f1;
        fuel2=f2;
    }
    public int getTurn() {
        return turn;
    }
}
