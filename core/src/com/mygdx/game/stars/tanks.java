package com.mygdx.game.stars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;

public interface tanks {
    public void move();
    public void aim();
    public void shoot();
    public Texture getTankImage();
    public Texture getTurretImage();
    public Texture getTankbottomImage();
    public Sprite getTanksprite();
    public Sprite getTurretsprite();
    public Body getTankbody();

    public Body getLeftwheelbody();

    public Body getRightwheelbody();
    public void setposition(float x,float y);

    BodyDef getTankbdef();

    FixtureDef getTankfdef();
    public int getid();
    public float getxoffset();
    public float getyoffset();


}
