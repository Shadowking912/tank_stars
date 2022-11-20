package com.mygdx.game.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenu implements Screen {

    final Drop game;
    private final Texture backgroundImage;
    private final TextureRegion backgroundTexture,settings;
    OrthographicCamera camera;
    private TextButton buttonPlay, buttonPlay2;
    private ImageButton button3;
    private Skin skin;
    private Stage stage;
    private  FitViewport viewp;
    private Sound sound;
    private Music music;
    private window window1;
    public MainMenu(final Drop game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(800, 600, game.camera));
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        backgroundImage = new Texture(Gdx.files.internal("background5.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1024, 500);
        settings=new TextureRegion(new Texture(Gdx.files.internal("settings.png")),80,80);
        music=Gdx.audio.newMusic(Gdx.files.internal("mainmenu.mp3"));
        music.setLooping(true);
        music.play();
    }
    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        buttonPlay = new TextButton("Play", skin, "default");
        buttonPlay.setPosition(110, 260);
        buttonPlay.setSize(280, 60);
        buttonPlay.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new gamemenu(game));


            }
        });
        buttonPlay2 = new TextButton("Exit", skin, "default");
        buttonPlay2.setPosition(400, 260);
        buttonPlay2.setSize(280, 60);
        buttonPlay2.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonPlay2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                System.exit(0);
            }
        });
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
        stage.addActor(buttonPlay);
        stage.addActor(buttonPlay2);
        stage.addActor(window1);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
//
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0, 800, 600);
//        game.font.draw(game.batch, "Welcome to Drop!", 300, 240);
//        game.font.draw(game.batch, "Click anywhere to begin!", 300, 140);
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
        music.stop();
        stage.dispose();
    }

}
