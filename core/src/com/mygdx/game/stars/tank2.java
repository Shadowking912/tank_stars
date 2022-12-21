package com.mygdx.game.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

import java.io.Serializable;

public class tank2 extends Tank implements tanks, Serializable {
    private float health;
    private float fuel;
    private ammo weapon;
    private float speed;
    private final Texture tankbottom= new Texture(Gdx.files.internal("tank2/tank_bottom.png"));
    private final Texture tankImage= new Texture(Gdx.files.internal("tank2/frost.png"));
    private final Texture tankturret= new Texture(Gdx.files.internal("tank2/turret.png"));
    private Music music=Gdx.audio.newMusic(Gdx.files.internal("tank1/idle.mp3"));
    private int id=1;
    private static float xoffset=28;
    private static float yoffset=30;

    @Override
    public Sprite getTanksprite() {
        return tanksprite;
    }

    @Override
    public Sprite getTurretsprite() {
        return turretsprite;
    }

    private Sprite tanksprite,turretsprite;
    public tank2(){
        health=100;
        fuel=100;
        this.tanksprite = new Sprite(new TextureRegion(getTankbottomImage()));
        this.tanksprite.setSize(80, 51);
        this.tanksprite.setOrigin(tanksprite.getWidth()/2,tanksprite.getHeight()/2);

        this.turretsprite=new Sprite(getTurretImage());
        this.turretsprite.setSize(40, 11);
        this.turretsprite.setOrigin(4,4);
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

    @Override
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
