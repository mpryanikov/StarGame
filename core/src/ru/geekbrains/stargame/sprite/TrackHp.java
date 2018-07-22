package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.Sprite;

public class TrackHp extends Sprite {

    private static final float HEIGHT = 0.09f;

    private void printHP(float value){
        float lHEIGHT =  HEIGHT * value * 0.01f;
//        System.out.println("hp " + (value * 0.01f) +"/" + lHEIGHT);
        setHeightProportion(lHEIGHT);
        pos.y = 0.5f - lHEIGHT / 2;
    }

    public TrackHp(TextureAtlas atlas) {
        super(atlas.findRegion("HP_S"));
        printHP(100);
    }

    public void hp(float value){
        printHP(value);
        }

}