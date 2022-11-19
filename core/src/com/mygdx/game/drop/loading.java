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


public class loading implements Screen {
    private long progress = 0;
    private long startTime = 0;
    private ShapeRenderer mShapeRenderer;
    final Drop game;
    private final Texture backgroundImage,missiletexture;
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
    public loading(final Drop game) {
        this.game = game;
        backgroundImage = new Texture(Gdx.files.internal("background5.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1024, 500);
        missiletexture= new Texture(Gdx.files.internal("missile.png"));
        mShapeRenderer = new ShapeRenderer();
        startTime = TimeUtils.nanoTime();
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
        //game.font.getData().setScale(0.2f);
        game.font.draw(game.batch, "Loading!", 350, 50);
        game.batch.end();
        game.batch.flush();
        showLoadProgress();
        camera.update();
    }
    private void showLoadProgress() {

        long currentTimeStamp = TimeUtils.nanoTime();
        if (currentTimeStamp - startTime > TimeUtils.millisToNanos(500)) {
            startTime = currentTimeStamp;
            progress = progress + 20;
        }
        // Width of progress bar on screen relevant to Screen width
        float progressBarWidth = (600 / 100) * progress;

        mShapeRenderer.setProjectionMatrix(camera.combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRenderer.setColor(Color.GOLD);
        mShapeRenderer.rect(100, 15, progressBarWidth, 10);
        mShapeRenderer.circle(100,20,5);
        mShapeRenderer.end();
        game.batch.begin();
        game.batch.draw(missiletexture, 87+6*progress,-3,50, 40);
        game.batch.end();
        if (progress == 100)
            game.setScreen(new MainMenu(game));

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

