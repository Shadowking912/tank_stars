package com.mygdx.game.stars;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.io.Serializable;

/**
     * Window which features the close button in top right corner (button moved outside of the window bounds).
     *
     * @author serhiy
     */
    public class window extends Window implements Serializable {
        private static final WindowStyle windowStyle;
        private static Skin skin;
        private static Slider slider;
        private static Slider slider2;
        private static TextureRegion exit;

        private static ImageButton closeButton,buttonExit;
        static {
            TextureRegion settings = new TextureRegion(new Texture(Gdx.files.internal("window/window2.png")), 500, 382);
            Drawable drawable = new TextureRegionDrawable(settings);
            windowStyle = new WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(settings));
            closeButton=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("close.png")))));
            skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
            slider = new Slider(0, 10, 1, false, skin);
            slider2=new Slider(0, 10, 1, false, skin);
            slider.setAnimateDuration(0.5f);
            slider2.setAnimateDuration(0.5f);
            exit=new TextureRegion(new Texture(Gdx.files.internal("exit_up.png")));
        }

        /**
         * Default constructor.
         */
        public window(final tankstars game) {
            super("SETTINGS", windowStyle);
            this.getTitleLabel().setAlignment(1);
//                super.draw(game.batch, 1);
            closeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setVisible(false);
                }
            });
            getTitleTable().add(closeButton).size(38, 38).padRight(10).padTop(10);
            setClip(false);
            setTransform(true);
            this.row().fill().expandX();
            Label.LabelStyle labelStyle = new Label.LabelStyle();
            labelStyle.font = new BitmapFont();
            Label x=new Label("MUSIC:",new Label.LabelStyle(labelStyle));
            this.add(x);
            this.add(slider);
            this.row().fill().expandX();
            Label y=new Label("SOUND:",new Label.LabelStyle(labelStyle));
            this.add(y);
            this.add(slider2);
            this.row().fill().expandX();
            this.setSize(500, 382);
            this.setModal(true);
            this.setVisible(false);
            this.setMovable(true);
            this.setLayoutEnabled(true);
            buttonExit = new ImageButton(new TextureRegionDrawable(exit));
            buttonExit.setSize(20, 20);
            buttonExit.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new MainMenu(game));
                }
            });

        }
        public void exitb(){
            this.add(buttonExit).size(200,50);;
        }
    }
