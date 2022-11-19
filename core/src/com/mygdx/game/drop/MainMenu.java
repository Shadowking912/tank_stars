package com.mygdx.game.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainMenu implements Screen {

    final Drop game;
    private final Texture backgroundImage;
    private final TextureRegion backgroundTexture;
    OrthographicCamera camera;
    private TextButton buttonPlay, buttonPlay2;
    private Skin skin;
    private Stage stage;
    private  FitViewport viewp;
    public MainMenu(final Drop game) {
        this.game = game;
        this.stage = new Stage(new FitViewport(800, 600, game.camera));
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        backgroundImage = new Texture(Gdx.files.internal("background5.jpg"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1024, 500);
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
                game.setScreen(new gamemenu(game));
                dispose();
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
        stage.addActor(buttonPlay);
        stage.addActor(buttonPlay2);
    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 0);

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
        stage.dispose();
    }

}
