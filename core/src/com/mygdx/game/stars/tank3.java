package com.mygdx.game.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

import java.io.Serializable;

public class tank3 extends Tank implements tanks, Serializable {
    private float health;
    private float fuel;
    private ammo weapon;
    private float speed;

    private final Texture tankbottom= new Texture(Gdx.files.internal("tank3/tank_bottom.png"));
    private final Texture tankImage= new Texture(Gdx.files.internal("tank3/tank3.png"));
    private final Texture tankturret= new Texture(Gdx.files.internal("tank3/turret.png"));

    private Music music=Gdx.audio.newMusic(Gdx.files.internal("tank1/idle.mp3"));
    private static float xoffset=10;
    private static float yoffset=25;

    @Override
    public Sprite getTanksprite() {
        return tanksprite;
    }

    @Override
    public Sprite getTurretsprite() {
        return turretsprite;
    }

    private Sprite tanksprite,turretsprite;
    private int id=2;
    public tank3(){
        health=100;
        this.tanksprite = new Sprite(new TextureRegion(getTankbottomImage()));
        this.tanksprite.setSize(80, 51);
        this.tanksprite.setOrigin(tanksprite.getWidth()/2,tanksprite.getHeight()/2);

        this.turretsprite=new Sprite(getTurretImage());
        this.turretsprite.setSize(40, 11);
        this.turretsprite.setOrigin(2,2);
    }
    @Override
    public void move(){

    }
    @Override
    public void aim(){

    }
    @Override
    public void shoot(){

    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public ammo getWeapon() {
        return weapon;
    }

    public void setWeapon(ammo weapon) {
        this.weapon = weapon;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Texture getTankImage() {
        return tankImage;
    }
    public Texture getTurretImage() {
        return tankturret;
    }
    public Texture getTankbottomImage() {
        return tankbottom;
    }
    public void setposition(float x,float y){
        tankbody.setTransform(x,y,0);
    }
    public int getid() {
        return id;
    }
    public float getxoffset() {
        return xoffset;
    }
    public float getyoffset() {
        return yoffset;
    }
}
