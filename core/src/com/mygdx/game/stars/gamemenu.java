package com.mygdx.game.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.io.Serializable;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class gamemenu implements Screen, Serializable {
    private ShapeRenderer mShapeRenderer;
    private final tankstars game;
    private final Texture backgroundImage,tankImage;
    private final TextureRegion backgroundTexture;
    private final TextureRegion settings;
    private TextureRegion battle;
    private OrthographicCamera camera;
    private Stage stage;
    private window window1;
    private ImageButton button3,buttonBattle,buttonComputer,buttonBack,buttonWeapons;
    public SpriteBatch batch;

    public gamemenu(final tankstars game) {
        this.game = game;
        batch=new SpriteBatch();
        mShapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 580);
        this.stage = new Stage(new FitViewport(800, 600,camera));
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        backgroundImage = new Texture(Gdx.files.internal("garage.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 388, 290);
        settings=new TextureRegion(new Texture(Gdx.files.internal("settings.png")),80,80);
        tankImage = new Texture(Gdx.files.internal("tank3/tank3.png"));
//        play1.
    }
    @Override
    public void show() {
        window1 = new window(game);
        window1.setPosition(140,120);
        window1.row().pad(20,0,0,0);
        window1.exitb();
        Drawable drawable = new TextureRegionDrawable(settings);
        button3 = new ImageButton(drawable);
        button3.setSize(40, 40);
        button3.setPosition(10,550);
        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window1.setVisible(true);
            }
        });



        buttonBattle = new ImageButton(new TextureRegionDrawable(battle=new TextureRegion(new Texture(Gdx.files.internal("battle.png")))));
        buttonBattle.setScale(0.6f);
        buttonBattle.setPosition(530,450);
        buttonBattle.setTransform(true);
//        buttonExit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exit_down.png")),822,304));
        buttonBattle.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonBattle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new garage(game,0));
            }
        });
        buttonComputer = new ImageButton(new TextureRegionDrawable(battle=new TextureRegion(new Texture(Gdx.files.internal("computer.png")))));
        //buttonExit.setSize(200, 100);
        buttonComputer.setScale(0.6f);
        buttonComputer.setPosition(530,360);
        buttonComputer.setTransform(true);
//        buttonExit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exit_down.png")),822,304));
        buttonComputer.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonComputer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new garage(game,0));
            }
        });
        buttonBack = new ImageButton(new TextureRegionDrawable(battle=new TextureRegion(new Texture(Gdx.files.internal("back.png")))));
        //buttonExit.setSize(200, 100);
        buttonBack.setScale(0.6f);
        buttonBack.setPosition(530,180);
        buttonBack.setTransform(true);
//        buttonExit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exit_down.png")),822,304));
        buttonBack.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenu(game));
            }
        });
        buttonWeapons = new ImageButton(new TextureRegionDrawable(battle=new TextureRegion(new Texture(Gdx.files.internal("WEAPONS.png")))));
        //buttonExit.setSize(200, 100);
        buttonWeapons .setScale(0.6f);
        buttonWeapons .setPosition(530,270);
        buttonWeapons .setTransform(true);
//        buttonExit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exit_down.png")),822,304));
        buttonWeapons .addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonWeapons .addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new garage(game,0));
            }
        });
        stage.addActor(buttonWeapons);
        stage.addActor(buttonBack);
        stage.addActor(buttonComputer);
        stage.addActor(buttonBattle);
        stage.addActor(button3);
        stage.addActor(window1);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0,0, 800, 600);
        batch.draw(tankImage, 10, 145,450,250);
        batch.end();
        batch.flush();
        mShapeRenderer.setProjectionMatrix(camera.combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRenderer.setColor(Color.ROYAL);
        mShapeRenderer.rect(500, 0, 400, 600);
        mShapeRenderer.end();
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
        mShapeRenderer.dispose();
    }

}

