package com.mygdx.game.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import java.io.Serializable;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GameScreen implements Screen, Serializable {
    private final tankstars game;
    private final Texture backgroundImage,terrainImage;
    private TextureRegion backgroundTexture,settings,logo,health_bar,health_bar_mirror,right,pause,resume,pausesign,play1;
    private OrthographicCamera camera;
    private final Stage stage;
    private window window1;
    private ImageButton button3,buttonRight,buttonPause,buttonResume,buttonSave1;
    private player p1,p2;
    private boolean paused;
    public GameScreen(final tankstars game,player p1,player p2) {
        this.p1=p1;
        this.game = game;
        // load the images for the droplet and the bucket, 64x64 pixels each
        backgroundImage = new Texture(Gdx.files.internal("bg1.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1280, 720);
        terrainImage = new Texture(Gdx.files.internal("terrain2.png"));
        settings=new TextureRegion(new Texture(Gdx.files.internal("settings.png")),80,80);
        logo= new TextureRegion(new Texture(Gdx.files.internal("logo.png")));
        health_bar= new TextureRegion(new Texture(Gdx.files.internal("health_bar.png")));
        health_bar_mirror= new TextureRegion(new Texture(Gdx.files.internal("health_bar_mirror.png")));
        right=new TextureRegion(new Texture(Gdx.files.internal("right.png")));
        pause=new TextureRegion(new Texture(Gdx.files.internal("pause.png")));
        pausesign=new TextureRegion(new Texture(Gdx.files.internal("pausesign.png")));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.stage = new Stage(new FillViewport(800,580,camera));
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,50, 800, 580);
        game.batch.draw(terrainImage, 0, 0,800,200);
        game.batch.draw(p1.getTank().getTankImage(),150, 110,50,37);
        game.batch.draw(health_bar,120,505,250,63);
        if(paused){
            game.batch.draw(pausesign, 220, 400,400,43);
        }
        game.batch.draw(health_bar_mirror,480,505,250,63);
        game.batch.draw(logo,350,470,150,150);
        game.batch.end();
        if(paused){

            stage.addActor(buttonResume);

        }
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void show() {
        stage.clear();
        window1 = new window(game);
        window1.setPosition(140,120);
        Drawable drawable = new TextureRegionDrawable(settings);
        button3 = new ImageButton(drawable);
        button3.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("settings.png")),75,75));
        button3.setSize(40, 40);
        button3.setTransform(true);
        button3.setPosition(20,540);
//        button3.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!paused){
                window1.setVisible(true);}
            }
        });

        buttonRight = new ImageButton(new TextureRegionDrawable(right));
        buttonRight.setSize(40, 40);
        buttonRight.setPosition(750,300);
        buttonRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!paused){
                System.out.println("");}
            }
        });
        buttonPause = new ImageButton(new TextureRegionDrawable(pause));
        buttonPause.setSize(20, 20);
        buttonPause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window1.setVisible(false);
                paused=true;
            }
        });
        play1=new TextureRegion(new Texture(Gdx.files.internal("save1.png")));
        buttonSave1 = new ImageButton(new TextureRegionDrawable(play1));
        buttonSave1.setSize(20,20);
        buttonSave1.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonSave1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
            }
        });

        window1.row().pad(20,0,0,0);
        window1.add(buttonPause).size(200,50);
        window1.exitb();
        window1.row().pad(20,0,0,0);
        window1.add(buttonSave1).size(200,50);
        resume=new TextureRegion(new Texture(Gdx.files.internal("resume_up.png")));
        Drawable drawable4 = new TextureRegionDrawable(resume);
        buttonResume = new ImageButton(drawable4);
        buttonResume.setScale(0.6f);
        buttonResume.setPosition(290,180);
        buttonResume.setTransform(true);
        buttonResume.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("resume_down.png"))));
        buttonResume.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonResume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                paused=false;
                show();
//                game.setScreen(new GameScreen(game));

            }
        });
        stage.addActor(buttonRight);
        stage.addActor(window1);
        stage.addActor(button3);
        stage.setDebugAll(true);
    }
    @Override
    public void hide() {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void dispose() {
    }
}
