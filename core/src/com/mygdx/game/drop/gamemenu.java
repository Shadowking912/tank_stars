package com.mygdx.game.drop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class gamemenu implements Screen {
    private long progress = 0;
    private long startTime = 0;
    private ShapeRenderer mShapeRenderer;
    final Drop game;
    private final Texture backgroundImage,tankImage;
    private final TextureRegion backgroundTexture,settings;
    OrthographicCamera camera;
    private TextButton buttonPlay, buttonPlay2;
    private Skin skin;
    private TextureRegionDrawable textureBar;
    private ProgressBar.ProgressBarStyle barStyle;
    private ProgressBar bar;
    private Stage stage;
    private  FitViewport viewp;
    private BitmapFont bf_loadProgress;
    private window window1;
    private ImageButton button3;

    public gamemenu(final Drop game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 580);
        this.stage = new Stage(new FitViewport(800, 600,camera));
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        backgroundImage = new Texture(Gdx.files.internal("garage.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 388, 290);
        settings=new TextureRegion(new Texture(Gdx.files.internal("settings.png")),80,80);
        tankImage = new Texture(Gdx.files.internal("tank3/tank3.png"));
    }
    @Override
    public void show() {
        window1 = new window();
        window1.setSize(500, 382);
        window1.setModal(true);
        window1.setVisible(false);
        window1.setMovable(true);
        window1.setPosition(140,120);
        Drawable drawable = new TextureRegionDrawable(settings);
        button3 = new ImageButton(drawable);
        button3.setSize(40, 40);
//        button3.setTransform(true);
//        button3.setScale(0.1f);
        button3.setPosition(10,550);
        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window1.setVisible(true);
//                stage.draw();
            }
        });
        stage.addActor(button3);
        stage.addActor(window1);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 600);
        game.batch.draw(tankImage, 10, 145,450,250);
        game.batch.end();
        stage.act();
        stage.draw();
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

