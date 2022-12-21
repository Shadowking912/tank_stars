package com.mygdx.game.stars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.Serializable;


public class tankstars extends Game implements Serializable {
	public SpriteBatch batch;
	public BitmapFont font;
	public OrthographicCamera camera;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		camera=new OrthographicCamera();
		camera.setToOrtho(false, 800, 580);
//		this.setScreen(new garage(this,0));
//		this.setScreen(new victory(this,0));
		this.setScreen(new MainMenu(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
