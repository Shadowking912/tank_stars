package com.mygdx.game.drop;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GameScreen implements Screen {
    final Drop game;
    private final Texture backgroundImage,terrainImage,tankImage;
    private final TextureRegion backgroundTexture,settings,logo,health_bar,health_bar_mirror;
    OrthographicCamera camera;
    private final Stage stage;
    private window window1;
    private ImageButton button3;

    public GameScreen(final Drop game) {
        this.game = game;
        // load the images for the droplet and the bucket, 64x64 pixels each
        backgroundImage = new Texture(Gdx.files.internal("bg1.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1280, 720);
        terrainImage = new Texture(Gdx.files.internal("terrain2.png"));
        tankImage = new Texture(Gdx.files.internal("tank3/tank3.png"));
        settings=new TextureRegion(new Texture(Gdx.files.internal("settings.png")),80,80);
        logo= new TextureRegion(new Texture(Gdx.files.internal("logo.png")));
        health_bar= new TextureRegion(new Texture(Gdx.files.internal("health_bar.png")));
        health_bar_mirror= new TextureRegion(new Texture(Gdx.files.internal("health_bar_mirror.png")));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.stage = new Stage(new FillViewport(800,580,camera));

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,50, 800, 580);
        game.batch.draw(terrainImage, 0, 0,800,200);
        game.batch.draw(tankImage, 150, 110,50,37);
        game.batch.draw(health_bar,120,505,250,63);
        game.batch.draw(health_bar_mirror,480,505,250,63);
        game.batch.draw(logo,350,470,150,150);
        game.batch.end();
        stage.act();
        stage.draw();

//        if (Gdx.input.isTouched()) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
//            bucket.x = touchPos.x - 64 / 2;
//        }
//        if (Gdx.input.isKeyPressed(Keys.LEFT))
//            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Keys.RIGHT))
//            bucket.x += 200 * Gdx.graphics.getDeltaTime();
//
//        // make sure the bucket stays within the screen bounds
//        if (bucket.x < 0)
//            bucket.x = 0;
//        if (bucket.x > 800 - 64)
//            bucket.x = 800 - 64;
//        // check if we need to create a new raindrop
//        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
//            spawnRaindrop();
    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void show() {
        window1 = new window(game);
        window1.setSize(500, 382);
        window1.setModal(true);
        window1.setVisible(false);
        window1.setMovable(true);
        window1.setPosition(140,120);

        Drawable drawable = new TextureRegionDrawable(settings);
        button3 = new ImageButton(drawable);
        button3.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("settings.png")),75,75));
        button3.setSize(40, 40);
        button3.setTransform(true);
        button3.setPosition(20,550);
        button3.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window1.setVisible(true);
            }
        });
        stage.addActor(window1);
        stage.addActor(button3);
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
