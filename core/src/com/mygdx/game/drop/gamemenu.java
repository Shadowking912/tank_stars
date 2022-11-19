package com.mygdx.game.drop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class gamemenu implements Screen {
    private long progress = 0;
    private long startTime = 0;
    private ShapeRenderer mShapeRenderer;
    final Drop game;
    private final Texture backgroundImage;
    private final TextureRegion backgroundTexture;
    OrthographicCamera camera;
    private TextButton buttonPlay, buttonPlay2;
    private Skin skin;
    private TextureRegionDrawable textureBar;
    private ProgressBar.ProgressBarStyle barStyle;
    private ProgressBar bar;
    private Stage stage;
    private  FitViewport viewp;
    private BitmapFont bf_loadProgress;
    public gamemenu(final Drop game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("garage.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 259, 194);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 580);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 580);
        game.batch.end();
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
        stage.dispose();
    }

}

