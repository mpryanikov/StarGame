package ru.geekbrains.stargame.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.math.Rnd;

public class Star extends Sprite {

    private Vector2 v = new Vector2(); // вектор скорости
    private Rect worldBounds; //граница мира, чтобы возвращать одну и туже звезду (иначе утечка памяти)

    public Star(TextureRegion region, float vx, float vy, float height) {
        super(region);
        v.set(vx, vy);
        setHeightProportion(height);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(posX, posY);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndHandleBounds(); //проверка границ экрана
    }

    private void checkAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()){
            setBottom(worldBounds.getTop());
            pos.x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());;
        }
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

}
