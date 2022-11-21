package com.mygdx.game.drop;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

    /**
     * Window which features the close button in top right corner (button moved outside of the window bounds).
     *
     * @author serhiy
     */
    public class window extends Window {
        private static final WindowStyle windowStyle;
        static {
            TextureRegion settings = new TextureRegion(new Texture(Gdx.files.internal("window/window2.png")), 500, 382);
            Drawable drawable = new TextureRegionDrawable(settings);
            windowStyle = new WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(settings));
        }

        /**
         * Default constructor.
         */
        public window(final Drop game) {
            super("", windowStyle);
//                super.draw(game.batch, 1);
//            closeButton.addListener(new ClickListener() {
//                @Override
//                public void clicked(InputEvent event, float x, float y) {
//                    setVisible(false);
//                }
//            });
//            getTitleTable().add(closeButton).size(38, 38).padRight(10).padTop(0);

            setClip(false);
            setTransform(true);
        }
    }
