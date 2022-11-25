package com.mygdx.game.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
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


public class garage implements Screen, Serializable {
    private ShapeRenderer mShapeRenderer;
    private final tankstars game;
    private final Texture backgroundImage;
    private Texture tankImage;
    private final TextureRegion backgroundTexture;
    private final TextureRegion settings;
    private TextureRegion select,right,left;
    private OrthographicCamera camera;

    private Stage stage;
    private window window1;
    private ImageButton button3,buttonSelect,buttonRight,buttonLeft;
    public SpriteBatch batch;
    private tanks tankslist[]={new tank1(),new tank2(),new tank3()};
    private int current=0;
    private int pl;
    private Music music;
    private player p1,p2;

    public garage(final tankstars game,int p) {
        this.pl=p;
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
        tankImage=tankslist[current].getTankImage();
        settings=new TextureRegion(new Texture(Gdx.files.internal("settings.png")),80,80);
        right=new TextureRegion(new Texture(Gdx.files.internal("right.png")));
        left=new TextureRegion(new Texture(Gdx.files.internal("left.png")));
        select=new TextureRegion(new Texture(Gdx.files.internal("select.png")));
        music=Gdx.audio.newMusic(Gdx.files.internal("tank1/idle.mp3"));
        music.setLooping(true);
        music.play();

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

        buttonRight = new ImageButton(new TextureRegionDrawable(right));
        buttonRight.setSize(40, 40);
        buttonRight.setPosition(750,300);
        buttonRight.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectTankright();
            }
        });
        buttonLeft = new ImageButton(new TextureRegionDrawable(left));
        buttonLeft.setSize(40, 40);
        buttonLeft.setPosition(10,295);
        buttonLeft.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectTankleft();
            }
        });

        Drawable drawable3 = new TextureRegionDrawable(select);
        buttonSelect = new ImageButton(drawable3);
        buttonSelect.setScale(0.6f);
        buttonSelect.setPosition(250,50);
        buttonSelect.setTransform(true);
//        buttonExit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exit_down.png")),822,304));
        buttonSelect.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonSelect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //dispose();
                p1=new player(tankslist[current]);
                p2=new player(tankslist[current]);
                music.stop();
                game.setScreen(new GameScreen(game,p1,p2));

            }
        });
        stage.addActor(buttonLeft);
        stage.addActor(buttonRight);
        stage.addActor(buttonSelect);
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
        batch.draw(tankImage, 180, 145,450,250);
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
        stage.dispose();
        mShapeRenderer.dispose();
    }
    public void selectTankright(){
        if(current==2){
            current=-1;
        }
        tankImage=tankslist[current+1].getTankImage();
        current++;
    }
    public void selectTankleft(){
        if(current==0){
            current=3;
        }
        tankImage=tankslist[current-1].getTankImage();
        current--;
    }
}


