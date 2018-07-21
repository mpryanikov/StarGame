package ru.geekbrains.stargame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pools.BulletPool;
import ru.geekbrains.stargame.pools.ExplosionPool;
import ru.geekbrains.stargame.sprite.Bullet;
import ru.geekbrains.stargame.sprite.Explosion;

public class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;
    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    protected Vector2 v = new Vector2(); // вектор скорости
    protected Rect worldBounds;

    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion bulletRegion;

    protected final Vector2 bulletV = new Vector2();//скорость пули
    protected float bulletHeight;//высота пули
    protected int bulletDamage;//урон корабля

    protected float reloadInterval;//интервал перезарядки
    protected float reloadTimer;

    protected int hp;//количество жизней

    //будет 2 вида звуков
    protected Sound shootSound;

    //конструктор для вражескиx кораблей
    public Ship(BulletPool bulletPool, Rect worldBounds, ExplosionPool explosionPool, Sound sound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.shootSound = sound;
    }

    public Ship(TextureRegion region, int rows, int cols, int frames, Sound sound, Rect worldBounds) {
        super(region, rows, cols, frames);
        this.shootSound = sound;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        //тикает до бесконечности
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    //выстрел
    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        shootSound.play();
    }

    //взрыв корабля
    public void boom() {
        Explosion explosion = explosionPool.obtain();
        //взрыв больше в 1.5 размеров корабля
        explosion.set(getHeight()*(float)1.5, pos);
    }

    public void damage(int damage) {
        frame = 1;
        damageAnimateTimer = 0f;
        hp -= damage;
        if (hp <= 0) {
            boom();
            destroy();
        }
    }

    public int getHp() {
        return hp;
    }

}
