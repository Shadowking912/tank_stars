package com.mygdx.game.stars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ammo implements Serializable {

    private static Map<String, ammo> instances = new HashMap<String, ammo>();

    private final float damage;
    private final float damageradius;

    public float getDamageradius() {
        return damageradius;
    }

    public Texture getAmmoImage() {
        return ammoImage;
    }

    private final Texture ammoImage;
    private ammo(float d, float dr, Texture image){
        this.damage=d;
        this.damageradius=dr;
        this.ammoImage=image;
    }
    public static ammo getInstance(String key, float d,float dr,Texture a1) {
        if (!instances.containsKey(key)) {
            instances.put(key, new ammo(d,dr,a1));
        }
        return instances.get(key);
    }
    public float getDamage(){
        return this.damage;
    }
}
