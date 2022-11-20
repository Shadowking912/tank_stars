package com.mygdx.game.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Button {
    final Drop game;
    public Button(Drop game){
        this.game=game;
    }
    void addButton(String text, String ui_path, float position_x, float position_y)
    {
        Skin skin = new Skin(Gdx.files.internal(ui_path));
        TextButton buttonPlay = new TextButton(text, skin, "default");
        buttonPlay.setPosition(position_x, position_y);
        buttonPlay.setSize(280, 60);
        buttonPlay.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new gamemenu(game));
            }
        });

    }
}
