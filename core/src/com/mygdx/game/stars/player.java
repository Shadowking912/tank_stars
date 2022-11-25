package com.mygdx.game.stars;

import java.io.Serializable;

public class player implements Serializable {
    private int points;
    private tanks tank;
    public player(tanks t1){
        this.tank=t1;
        this.points=0;
    }
    public void play(){

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public tanks getTank() {
        return tank;
    }

    public void setTank(tanks tank) {
        this.tank = tank;
    }
}
