package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.Sprite;

public class TrackHp extends Sprite {

    private static final float HEIGHT = 0.07f;

    public TrackHp(TextureAtlas atlas) {
        super(atlas.findRegion("HP_S"));
        float lHEIGHT =  HEIGHT;
        setHeightProportion(lHEIGHT);
        pos.y = 0.5f - lHEIGHT / 2;
    }

    public void hp(float value){
        if (value > 0){
            float lHEIGHT =  HEIGHT * value * 0.01f;
            System.out.println("hp " + (value * 0.01f) +"/" + lHEIGHT);
            setHeightProportion(lHEIGHT);
            pos.y = 0.5f - lHEIGHT / 2;
        }
    }

}