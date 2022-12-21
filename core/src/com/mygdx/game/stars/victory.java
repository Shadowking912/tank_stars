package com.mygdx.game.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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


public class victory implements Screen, Serializable {
    private ShapeRenderer mShapeRenderer;
    private final tankstars game;
    private final Texture backgroundImage;
    private Texture tankImage ,turretImage;
    private final TextureRegion backgroundTexture,exit;
    private TextureRegion select,right,left;
    private OrthographicCamera camera;

    private Stage stage;
    private window window1;
    private ImageButton button3,buttonSelect,buttonRight,buttonLeft,buttonExit;
    public SpriteBatch batch;
    private tanks tankslist[]={new tank1(),new tank2(),new tank3()};
    private int current=0;
    private int pl;
    private Music music;
    private player p1,p2;
    private Sprite sprite1,sprite2;
    private static player players[]=new player[2];
    private String s;
    private BitmapFont font;
    public victory(final tankstars game,int p) {
        this.pl=p;
        this.game = game;
        font=new BitmapFont();
        batch=new SpriteBatch();
        mShapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 580);
        this.stage = new Stage(new FitViewport(800, 580,camera));
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        backgroundImage = new Texture(Gdx.files.internal("garage.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 388, 290);

        exit=new TextureRegion(new Texture(Gdx.files.internal("exit_up.png")));
        this.s=String.format("PLAYER %d VICTORY",p);
        music=Gdx.audio.newMusic(Gdx.files.internal("tank1/idle.mp3"));
        music.setLooping(true);
        music.play();

    }
    @Override
    public void show() {

        buttonExit = new ImageButton(new TextureRegionDrawable(exit));
        buttonExit.setScale(0.6f);
        buttonExit.setPosition(270,100);
        buttonExit.setTransform(true);
        buttonExit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenu(game));
            }
        });
        stage.addActor(buttonExit);

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundTexture, 0,0, 800, 600);
        font.getData().setScale(4);
        font.draw(batch, s, 150, 300);

        batch.end();
        batch.flush();
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
        batch.dispose();
        stage.dispose();
        mShapeRenderer.dispose();
        music.dispose();
        font.dispose();
    }

}


