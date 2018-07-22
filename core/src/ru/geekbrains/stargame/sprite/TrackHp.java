package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.Sprite;

public class TrackHp extends Sprite {

    private static final float HEIGHT = 0.07f;

    public TrackHp(TextureAtlas atlas) {
        super(atlas.findRegion("HP_S"));
        setHeightProportion(HEIGHT);
        pos.y = 0.5f;
    }
}