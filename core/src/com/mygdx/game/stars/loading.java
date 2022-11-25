package com.mygdx.game.stars;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.io.Serializable;


public class loading implements Screen, Serializable {
    private long progress = 0;
    private long startTime = 0;
    private final ShapeRenderer mShapeRenderer;
    private final tankstars game;
    private final Texture backgroundImage,missiletexture;
    private final TextureRegion backgroundTexture,logo;
    public loading(final tankstars game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("background5.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1024, 500);
        missiletexture= new Texture(Gdx.files.internal("missile.png"));
        logo= new TextureRegion(new Texture(Gdx.files.internal("logo.png")));
        mShapeRenderer = new ShapeRenderer();
        startTime = TimeUtils.nanoTime();

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 580);
        game.font.getData().setScale(2);
        game.font.draw(game.batch, "Loading!", 350, 60);
        game.batch.draw(logo, 20,300, 360, 360);
        game.batch.end();
        game.batch.flush();
        showLoadProgress();
        game.camera.update();
    }
    private void showLoadProgress() {

        long currentTimeStamp = TimeUtils.nanoTime();
        if (currentTimeStamp - startTime > TimeUtils.millisToNanos(500)) {
            startTime = currentTimeStamp;
            progress = progress + 20;
        }
        float progressBarWidth = (600 / 100) * progress;

        mShapeRenderer.setProjectionMatrix(game.camera.combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRenderer.setColor(Color.GOLD);
        mShapeRenderer.rect(100, 15, progressBarWidth, 10);
        mShapeRenderer.circle(100,20,5);
        mShapeRenderer.end();
        game.batch.begin();
        game.batch.draw(missiletexture, 87+6*progress,-3,50, 40);
        game.batch.end();
        if (progress == 100) {
            game.setScreen(new MainMenu(game));
            dispose();
        }

    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mShapeRenderer.dispose();
    }

}

