package com.mygdx.game.stars;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Tank {
    public BodyDef getTankbdef() {
        return tankbdef;
    }

    protected BodyDef tankbdef,rightwheelbdef,leftwheelbdef;
    private static int ppm=100;
    protected FixtureDef tankfdef,fixtureDefright,fixtureDefleft;

    public FixtureDef getTankfdef() {
        return tankfdef;
    }

    protected Body tankbody,leftwheelbody,rightwheelbody;
    private RevoluteJoint leftwheel,rightwheel;
    public Tank() {
        System.out.println("yes");
    }
    public void rendertank(Body b1,RevoluteJoint r,RevoluteJoint l) {
        this.tankbody=b1;
        this.rightwheel=r;
        this.leftwheel=l;
    }
    public Body getTankbody() {
        return tankbody;
    }

    public Body getLeftwheelbody() {
        return leftwheelbody;
    }

    public Body getRightwheelbody() {
        return rightwheelbody;
    }
}
