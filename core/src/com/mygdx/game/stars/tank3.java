package com.mygdx.game.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

public class tank3 implements tanks, Serializable {
    private float health;
    private float fuel;
    private ammo weapon;
    private float speed;
    private final Texture tankImage= new Texture(Gdx.files.internal("tank3/tank3.png"));
    private Music music=Gdx.audio.newMusic(Gdx.files.internal("tank1/idle.mp3"));
    public tank3(){
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

    public float getHealth() {
        return health;
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
}
