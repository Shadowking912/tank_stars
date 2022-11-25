package com.mygdx.game.stars;

import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

public interface tanks {
    public void move();
    public void aim();
    public void shoot();
    public Texture getTankImage();
}
