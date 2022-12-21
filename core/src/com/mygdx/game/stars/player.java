package com.mygdx.game.stars;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

import java.io.Serializable;

public class player implements Serializable {
    private int points;
    private tanks tank;

    public float getHealth() {
        return health;
    }

    private float health;

    public int setFuel(float fuel) {
        this.fuel = fuel;
        if(fuel<=0){
            return -1;
        }
        return 0;
    }

    public float getFuel() {
        return fuel;
    }

    private float fuel;
    private Vector2 position;

    public Body getTankbody() {
        return tankbody;
    }

    public RevoluteJoint getRight() {
        return right;
    }

    public RevoluteJoint getLeft() {
        return left;
    }

    private Body tankbody;
    private RevoluteJoint right;
    private RevoluteJoint left;
    public player(tanks t1){
        this.health=100;
        this.tank=t1;
        this.points=0;
        this.fuel=100;
    }
    public void setposition(Vector2 v){

        position=v;
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
    public void rendertank(Body b, RevoluteJoint r, RevoluteJoint l){
        this.tankbody=b;
        this.left=l;
        this.right=r;
    }
    public int damage(float f){
        this.health-=f;
        if(health<=0){
            return -1;
        }
        else{
            return 0;
        }
    }

    public void setHealth(float health1) {
        health=health1;
    }
    public int getfuels(){
        return (int)fuel;
    }
}
