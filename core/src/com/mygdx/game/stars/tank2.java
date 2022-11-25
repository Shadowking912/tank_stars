package com.mygdx.game.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

public class tank2 implements tanks, Serializable {
    private float health;
    private float fuel;
    private ammo weapon;
    private float speed;
    private final Texture tankImage= new Texture(Gdx.files.internal("tank2/frost.png"));
    private Music music=Gdx.audio.newMusic(Gdx.files.internal("tank1/idle.mp3"));
    public tank2(){
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
}
