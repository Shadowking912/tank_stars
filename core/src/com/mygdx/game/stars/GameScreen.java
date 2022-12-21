package com.mygdx.game.stars;
import java.io.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.mygdx.game.stars.GameScreen.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

class MycontactListener implements ContactListener{

    public static player p[];

    public MycontactListener(player[] players) {
        p=players;
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        if (a.getUserData().equals("ammo6")){
            if(b.getUserData().equals("tank"+(tu+1)%2)){
                contact.setEnabled(false);
            }
            else {
                if (Vector2.dst(p[tu].getTankbody().getPosition().x * 100, p[tu].getTankbody().getPosition().y * 100, b.getPosition().x * 100, b.getPosition().y * 100) < 70) {
                    if (p[tu].damage(GameScreen.ammo6.getDamage()) == -1) {
                        change=1;
                    }
                }
                GameScreen.deleted = a;
            }


        } else if (b.getUserData().equals("ammo6")){
            if(a.getUserData().equals("tank"+(tu+1)%2)){
                contact.setEnabled(false);
            }
            else {
                if (Vector2.dst(p[tu].getTankbody().getPosition().x * 100, p[tu].getTankbody().getPosition().y * 100, b.getPosition().x * 100, b.getPosition().y * 100) < 70) {
                    if (p[tu].damage(GameScreen.ammo6.getDamage()) == -1) {
                        GameScreen.change=1;
                    }
                }
                GameScreen.deleted = b;
            }
        }
        else if(a.getUserData().equals("box")){
            if(b.getUserData().equals("tank0") || b.getUserData().equals("tank1")){
                ammo6=ammo4;
            }
            deleted2=a;

        }
        else if(b.getUserData().equals("box")){
            if(a.getUserData().equals("tank0") || a.getUserData().equals("tank1")){
                ammo6=ammo4;
                deleted2=b;
            }
        }

    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
class CustomInputProcessorOne implements InputProcessor {

//
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
//
//    {
//        turn.getLeft().setMotorSpeed(2.5f);
//        turn.getRight().setMotorSpeed(2.5f);
//    }
//        else
//
//    {
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            turn.getLeft().setMotorSpeed(-2.5f);
//            turn.getRight().setMotorSpeed(-2.5f);
//        } else {
//            turn.getLeft().setMotorSpeed(0);
//            turn.getRight().setMotorSpeed(0);
//        }
//    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.LEFT:
                if(fuel2==1) {
                    turn.getLeft().setMotorSpeed(2.5f);
                    turn.getRight().setMotorSpeed(2.5f);
                    move = true;
                }
                break;
            case Input.Keys.RIGHT:
                if(fuel2==1) {
                    turn.getLeft().setMotorSpeed(-2.5f);
                    turn.getRight().setMotorSpeed(-2.5f);
                    GameScreen.move = true;
                }
                break;
        }
        return true;
    }
    @Override
    public boolean keyUp(int keycode) {
        if(keycode==Input.Keys.LEFT || keycode== Input.Keys.RIGHT){
                turn.getLeft().setMotorSpeed(0f);
                turn.getRight().setMotorSpeed(0f);
                move=false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button==Input.Buttons.LEFT && GameScreen.sprite3==null) {
        Sprite sprite2 = turn.getTank().getTurretsprite();
        GameScreen.shoot((float) (sprite2.getX() + sprite2.getWidth() * cos(Math.toRadians(sprite2.getRotation()))), (float) (sprite2.getY() + sprite2.getWidth() * sin(Math.toRadians(sprite2.getRotation()))), sprite2.getRotation(),slider.getValue());
        tu = (tu + 1) % 2;
        players[tu].setFuel(100);
        turn = GameScreen.players[(tu)];
        rounds++;
        ammo6=ammo1;
        spawn=1;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
class TestRunner
{
    public static void test()
    {
        Result result= JUnitCore.runClasses(Mytest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
public class GameScreen implements Screen, Serializable {
    private BitmapFont font;
    public final tankstars game;
    public static ammo ammo1,ammo2,ammo3,ammo6,ammo4;
    private final Texture backgroundImage,terrainImage;
    private static Texture tankImage,turretImage;
    private TextureRegion backgroundTexture;
    private TextureRegion settings;
    private TextureRegion logo;
    private TextureRegion health_bar,health_bar2;
    private TextureRegion health_bar_mirror;
//    private  RevoluteJoint rightwheel,leftwheel;
    private TextureRegion pause;
    private TextureRegion resume;
    private TextureRegion pausesign;
    private TextureRegion play1,right;
    private OrthographicCamera camera;
    private final Stage stage;
    private float fuel1;
    private window window1;
    private ImageButton button3,buttonRight,buttonPause,buttonResume,buttonSave1,buttonAmmo1,buttonAmmo2,buttonAmmo3;
    public static player[] players =new player[2];
    private boolean paused;
    public static World world;
    public static Body deleted=null,deleted2=null;
    public static Box2DDebugRenderer bd;
    private Sprite sprite1,sprite2;
    public static Sprite sprite3=null,sprite4=null;
    private Vector2 pos;
    private float angle,angle3;
    private SpriteBatch batch;
    public static final float ppm=100;
    private static Body b1;
    private Body b2;
    private Body b3;
    private Body b4;
    private Body b5;
    private static Vector2 startingVelocity;
    private Vector2 out;
    private Vector2 posturret;
    private CatmullRomSpline<Vector2> myCatmull;
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmr;
    private static PolygonShape shapeammo;
    private FixtureDef fixtureDefright,fixtureDefleft;
    public static player turn;
    public static int tu;
    private ShapeRenderer mShapeRenderer;
    private static GameScreen ref;
    public static boolean move=false;
    public static int fuel2=1;
    public InputProcessor inputProcessorOne;
    public static int rounds=0;
    public static int spawn=1;
    public static int change=0;
    public static Slider slider;

    public GameScreen(final tankstars game,player p1,player p2,Gamesave g) {

        font=new BitmapFont();
        world = new World(new Vector2(0, -9.8f), true);
        bd = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        if(g!=null){
            tanks list[]={new tank1(),new tank2(),new tank3()};
            tanks list2[]={new tank1(),new tank2(),new tank3()};
            players= new player[]{new player(list[g.getTankid1()]), new player(list2[g.getTankid2()])};
            players[0].setHealth(g.getHealth1());
            players[1].setHealth(g.getHealth2());
            players[0].setposition(g.getPlayer1pos());
            players[1].setposition(g.getPlayer2pos());
            tu=g.getTurn();
        }
        else{
            players[0]=p1;
            players[1]=p2;
            tu=0;
        }
        turn=players[tu];
        TestRunner.test();
        this.game = game;
        mShapeRenderer = new ShapeRenderer();
        // load the images for the droplet and the bucket, 64x64 pixels each
        backgroundImage = new Texture(Gdx.files.internal("bg1.png"));
        backgroundTexture = new TextureRegion(backgroundImage, 0, 0, 1280, 720);
        terrainImage = new Texture(Gdx.files.internal("terrain2.png"));
        settings=new TextureRegion(new Texture(Gdx.files.internal("settings.png")),80,80);
        logo= new TextureRegion(new Texture(Gdx.files.internal("logo.png")));
        health_bar= new TextureRegion(new Texture(Gdx.files.internal("health.png")));
        health_bar2=new TextureRegion(new Texture(Gdx.files.internal("health.png")));
//        health_bar_mirror= new TextureRegion(new Texture(Gdx.files.internal("health_bar_mirror.png")));
        right=new TextureRegion(new Texture(Gdx.files.internal("right.png")));
        pause=new TextureRegion(new Texture(Gdx.files.internal("pause.png")));
        pausesign=new TextureRegion(new Texture(Gdx.files.internal("pausesign.png")));
        ammo1=ammo.getInstance("ammo1",20,70, new Texture(Gdx.files.internal("ammo/ammo1.png")));
        ammo2=ammo.getInstance("ammo2",30,50, new Texture(Gdx.files.internal("ammo/ammo2.png")));
        ammo3=ammo.getInstance("ammo3",40,40, new Texture(Gdx.files.internal("ammo/ammo3.png")));
        ammo4=ammo.getInstance("ammo4",60,40, new Texture(Gdx.files.internal("ammo/ammo4.png")));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 580);
        ammo6=ammo1;

        this.stage = new Stage(new FillViewport(800,580,camera));
        pos = new Vector2(0,0);
        posturret=new Vector2();
        rendertanka();
        rendertankb();
//
        map=new TmxMapLoader().load("ground/untitled.tmx");
        tmr =new OrthogonalTiledMapRenderer(map);
        tiledrenderer(this.world,map.getLayers().get("collision").getObjects());
        world.setContactListener(new MycontactListener(players));
        ref=this;
        inputProcessorOne = new CustomInputProcessorOne();
        InputProcessor inputProcessorTwo = this.stage;
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputProcessorTwo);
        inputMultiplexer.addProcessor(inputProcessorOne);
        Gdx.input.setInputProcessor(inputMultiplexer);
//        Gdx.input.setInputProcessor(this.stage);


    }
    public static void serialize(int save_number)
            throws IOException
    {
        players[0].setposition(players[0].getTankbody().getPosition());
        Gamesave g=new Gamesave(players[0].getTankbody().getPosition(),players[1].getTankbody().getPosition(),tu,players[0].getTank().getid(),players[1].getTank().getid(),players[0].getHealth(),players[1].getHealth(),players[0].getFuel(),players[1].getFuel());
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(new File("C:\\Users\\gupta\\Desktop\\saves\\save_" + save_number + ".bin")));
            out.writeObject(g);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally{
            out.close();
        }
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        tmr.setView(camera);
        fuel1=players[tu].getFuel();
        players[0].getTank().getTanksprite().setPosition(players[0].getTankbody().getPosition().x*ppm-39,players[0].getTankbody().getPosition().y*ppm-28);
        players[0].getTank().getTurretsprite().setPosition(players[0].getTank().getTanksprite().getX()+players[0].getTank().getxoffset(), players[0].getTank().getTanksprite().getY()+players[0].getTank().getyoffset());
        players[1].getTank().getTanksprite().setPosition(players[1].getTankbody().getPosition().x*ppm-39,players[1].getTankbody().getPosition().y*ppm-28);
        players[1].getTank().getTurretsprite().setPosition(players[1].getTank().getTanksprite().getX()+players[1].getTank().getxoffset(), players[1].getTank().getTanksprite().getY()+players[1].getTank().getyoffset());
        if(rounds%3==0 && spawn==1){
            spawn=0;
            spawnbox(300,600);
        }
        if(sprite3!=null) {
            if(b1.getPosition().x*ppm<0 || b1.getPosition().x*ppm>800){
                deleted=b1;
            }
            else {
                float angle1 = (float) Math.toDegrees(Math.atan2(b1.getLinearVelocity().y, b1.getLinearVelocity().x));
                b1.setTransform(b1.getPosition(), (float) Math.toRadians(angle1));
                sprite3.setRotation(angle1);
                sprite3.setPosition(b1.getPosition().x * ppm - 25, b1.getPosition().y * ppm - 7.5f);
            }
        }
        camera.update();
        pos.x=Gdx.input.getX();
        pos.y=Gdx.input.getY();
//        System.out.println(b2.getAngle()*sprite2.getHeight());
//        double result = Math.atan2(pos.y - sprite2.getY(), pos.x - sprite2.getX())-Math.atan2(sprite2.getY()+b2.getAngle()*sprite2.getHeight() - sprite2.getY(), sprite2.getX()+b2.getAngle()*sprite2.getWidth() - sprite2.getX());
//        System.out.println(Math.toDegrees(result));
        angle=(float) Math.toDegrees(Math.atan2(580-pos.y-(turn.getTank().getTurretsprite().getY()),pos.x - turn.getTank().getTurretsprite().getX()));
//        angle3=angle-(float) Math.toDegrees(b2.getAngle());
//        System.out.println((float) Math.toDegrees(b2.getAngle()));
//        if(angle3>170||angle3<-90){
//            angle=170;
//        }
//        else if(angle3<0){
//            angle=0;
//        }
        players[0].getTank().getTanksprite().setRotation((float) Math.toDegrees(players[0].getTankbody().getAngle()));
        players[1].getTank().getTanksprite().setRotation((float) Math.toDegrees(players[1].getTankbody().getAngle()));
        turn.getTank().getTurretsprite().setRotation(angle);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();


        batch.draw(backgroundTexture, 0,50, 800, 580);
        batch.draw(health_bar,100,525,200,33);
        batch.draw(health_bar2,550,525,200,33);

        if(move){
            if(turn.setFuel(turn.getFuel()-0.2f)==-1){
                fuel2=0;
                inputProcessorOne.keyUp(Input.Keys.RIGHT);
            }
        }
        if(paused){
            batch.draw(pausesign, 220, 400,400,43);
        }
        batch.draw(logo,350,470,150,150);
        players[0].getTank().getTurretsprite().draw(batch);
        players[0].getTank().getTanksprite().draw(batch);
        players[1].getTank().getTurretsprite().draw(batch);
        players[1].getTank().getTanksprite().draw(batch);

        if(sprite3!=null) {
            sprite3.draw(batch);
        }
        if(sprite4!=null) {
            sprite4.setPosition(b5.getPosition().x * ppm - 25, b5.getPosition().y * ppm - 7.5f);
            sprite4.draw(batch);
        }
        batch.end();
        if(paused){
            stage.addActor(buttonResume);
        }
        tmr.render();

        batch.begin();
        if(tu==0){
            batch.draw(health_bar,83,75,150,25);
            font.getData().setScale(1.8f);
            font.draw(batch, "FUEL", 13, 100);
        }
        else{
            batch.draw(health_bar,580,75,150,25);
            font.getData().setScale(1.8f);
            font.draw(batch, "FUEL", 730, 100);
        }
        batch.end();
        mShapeRenderer.setProjectionMatrix(camera.combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        showhealth( players[0].getHealth(),players[1].getHealth());
        if(tu==0){
            showfuel(93,fuel1);
        }
        else{
            showfuel(591,fuel1);
        }
        mShapeRenderer.end();
        bd.render(world,camera.combined.scl(ppm));


        stage.act();

        stage.draw();
        if(change==1){
            game.setScreen(new victory(game,(tu+1)%2));
//            dispose();
        }
        world.step(1/60f, 6, 2);
        if(deleted!=null){
//            sprite3.getTexture().dispose();
            sprite3=null;
            world.destroyBody(deleted);
            deleted=null;
        }
        if(deleted2!=null){
//            sprite3.getTexture().dispose();
            sprite4=null;
            world.destroyBody(deleted2);
            deleted2=null;
//            spawn=1;
        }
    }
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,800,580);
    }
    @Override
    public void show() {
        stage.clear();
        window1 = new window(game);
        window1.setPosition(140,120);
        Drawable drawable = new TextureRegionDrawable(settings);
        button3 = new ImageButton(drawable);
        button3.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("settings.png")),75,75));
        button3.setSize(40, 40);
        button3.setTransform(true);
        button3.setPosition(20,540);
//        button3.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!paused){
                window1.setVisible(true);
                }
            }
        });

        buttonPause = new ImageButton(new TextureRegionDrawable(pause));
        buttonPause.setSize(20, 20);
        buttonPause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window1.setVisible(false);
                paused=true;
            }
        });
        play1=new TextureRegion(new Texture(Gdx.files.internal("save1.png")));
        buttonSave1 = new ImageButton(new TextureRegionDrawable(play1));
        buttonSave1.setSize(20,20);
        buttonSave1.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonSave1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    int length = (new File("C:\\Users\\gupta\\Desktop\\saves").listFiles().length+1)%3;
                    if(length==0){
                        length=3;
                    }
                    GameScreen.serialize(length);
                    System.out.println("saved");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        window1.row().pad(20,0,0,0);
        window1.add(buttonPause).size(200,50);
        window1.exitb();
        window1.row().pad(20,0,0,0);
        window1.add(buttonSave1).size(200,50);
        resume=new TextureRegion(new Texture(Gdx.files.internal("resume_up.png")));
        Drawable drawable4 = new TextureRegionDrawable(resume);

        buttonResume = new ImageButton(drawable4);
        buttonResume.setScale(0.6f);
        buttonResume.setPosition(290,180);
        buttonResume.setTransform(true);
        buttonResume.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("resume_down.png"))));
        buttonResume.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonResume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                paused=false;
                show();
//                game.setScreen(new GameScreen(game));

            }
        });
        buttonAmmo1 = new ImageButton((new TextureRegionDrawable(ammo1.getAmmoImage())));
//        buttonAmmo1.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("settings.png")),75,75));
        buttonAmmo1.setSize(40, 40);
        buttonAmmo1.setTransform(true);
        buttonAmmo1.setPosition(300,75);
        buttonAmmo1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ammo6=ammo1;
//                game.setScreen(new GameScreen(game));

            }
        });
        buttonAmmo2 = new ImageButton((new TextureRegionDrawable(ammo2.getAmmoImage())));
//        buttonAmmo1.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("settings.png")),75,75));
        buttonAmmo2.setSize(40, 40);
        buttonAmmo2.setTransform(true);
        buttonAmmo2.setPosition(370,75);
        buttonAmmo3 = new ImageButton((new TextureRegionDrawable(ammo3.getAmmoImage())));
//        buttonAmmo1.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("settings.png")),75,75));
        buttonAmmo2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ammo6=ammo2;
//                game.setScreen(new GameScreen(game));

            }
        });
        buttonAmmo3.setSize(40, 40);
        buttonAmmo3.setTransform(true);
        buttonAmmo3.setPosition(440,75);
        buttonAmmo3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ammo6=ammo3;
//                game.setScreen(new GameScreen(game));

            }
        });
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        slider = new Slider(0, 10, 1, false, skin);
        slider.setAnimateDuration(0.5f);
        slider.setPosition(300,20);
        stage.addActor(slider);
        stage.addActor(buttonAmmo1);
        stage.addActor(buttonAmmo2);
        stage.addActor(buttonAmmo3);
        stage.addActor(window1);
        stage.addActor(button3);
        stage.setDebugAll(true);
    }
    public void tiledrenderer(World world, MapObjects objects){
        Iterator<MapObject> iter=objects.iterator();
//        for(MapObject m:objects){
        while(iter.hasNext()){
            MapObject m=iter.next();
            Shape shape2;
            System.out.println(m);
            if(m instanceof PolygonMapObject){
                System.out.println("yes");
                shape2=createpoly((PolygonMapObject) m);
                Body b2;
                BodyDef bdef2=new BodyDef();
                bdef2.type= BodyDef.BodyType.StaticBody;
                b2=world.createBody(bdef2);
                b2.setUserData("ground");
                FixtureDef fdef3=new FixtureDef();
                fdef3.shape=shape2;
                b2.createFixture(fdef3);
                shape2.dispose();
            }
        }
    }
    private static ChainShape createpoly(PolygonMapObject object){
        float[] vertices=object.getPolygon().getTransformedVertices();
        Vector2[] worldv=new Vector2[vertices.length/2];
        for(int i=0;i<worldv.length;i++){
            worldv[i]=new Vector2(vertices[i*2]/ppm,vertices[i*2+1]/ppm);
        }
        ChainShape c=new ChainShape();
        c.createChain(worldv);
        return c;
    }
    private void rendertanka() {
//        players[0].getTank().render_tank(world);

        BodyDef tankbdef = new BodyDef();
        tankbdef.position.set(150 / ppm, 400 / ppm);
        tankbdef.type = BodyDef.BodyType.DynamicBody;
        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(35 / ppm, 5 / ppm);
        FixtureDef tankfdef = new FixtureDef();
        tankfdef.shape = shape2;
        tankfdef.friction = 2;
        tankfdef.density = 1.0F;
        Body tankbody = world.createBody(tankbdef);
        tankbody.createFixture(tankfdef);
        tankbody.setUserData("tank0");

        BodyDef leftwheelbdef = new BodyDef();
        leftwheelbdef.position.set(150 / ppm, 300 / ppm);
        leftwheelbdef.type = BodyDef.BodyType.DynamicBody;
        leftwheelbdef.fixedRotation = false;
        Body leftwheelbody;
        leftwheelbody = world.createBody(leftwheelbdef);
        leftwheelbody.setUserData("tank0");

        BodyDef rightwheelbdef = new BodyDef();
        rightwheelbdef.position.set(180 / ppm, 310 / ppm);
        rightwheelbdef.type = BodyDef.BodyType.DynamicBody;
        rightwheelbdef.fixedRotation = false;
        Body rightwheelbody = world.createBody(rightwheelbdef);
        rightwheelbody.setUserData("tank0");

        CircleShape shapec = new CircleShape();
        shapec.setRadius(14 / ppm);

        fixtureDefright = new FixtureDef();
        fixtureDefright.shape = shapec;
        fixtureDefright.density = 40.0f;
        fixtureDefright.friction = 30;

        fixtureDefleft = new FixtureDef();
        fixtureDefleft.shape = shapec;
        fixtureDefleft.density = 40.0f;
        fixtureDefleft.friction = 30;

        leftwheelbody.createFixture(fixtureDefleft);
        rightwheelbody.createFixture(fixtureDefright);

        RevoluteJointDef rdef = new RevoluteJointDef();
        rdef.bodyA = tankbody;
        rdef.bodyB = rightwheelbody;
        rdef.collideConnected = false;
        rdef.enableMotor = true;
        rdef.motorSpeed = 0;
        rdef.maxMotorTorque = 130;
        rdef.localAnchorA.set(28 / ppm, -15 / ppm);


        RevoluteJointDef rdef2 = new RevoluteJointDef();
        rdef2.bodyA = tankbody;
        rdef2.bodyB = leftwheelbody;
        rdef2.collideConnected = false;
        rdef2.enableMotor = true;
        rdef2.motorSpeed = 0;
        rdef2.maxMotorTorque = 130;
        rdef2.localAnchorA.set(-28 / ppm, -15 / ppm);
//
        RevoluteJoint rightwheel = (RevoluteJoint) world.createJoint(rdef);
        RevoluteJoint leftwheel = (RevoluteJoint) world.createJoint(rdef2);
        players[0].rendertank(tankbody,rightwheel,leftwheel);

    }
    public void rendertankb(){
        BodyDef tankbdef = new BodyDef();
        tankbdef.position.set(550 / ppm, 400 / ppm);
        tankbdef.type = BodyDef.BodyType.DynamicBody;
        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(35 / ppm, 5 / ppm);
        FixtureDef tankfdef = new FixtureDef();
        tankfdef.shape = shape2;
        tankfdef.friction = 2;
        tankfdef.density = 1.0F;
        Body tankbody = world.createBody(tankbdef);
        tankbody.createFixture(tankfdef);
        tankbody.setUserData("tank1");

        BodyDef leftwheelbdef = new BodyDef();
        leftwheelbdef.position.set(550 / ppm, 300 / ppm);
        leftwheelbdef.type = BodyDef.BodyType.DynamicBody;
        leftwheelbdef.fixedRotation = false;
        Body leftwheelbody;
        leftwheelbody = world.createBody(leftwheelbdef);
        leftwheelbody.setUserData("tank1");

        BodyDef rightwheelbdef = new BodyDef();
        rightwheelbdef.position.set(570 / ppm, 310 / ppm);
        rightwheelbdef.type = BodyDef.BodyType.DynamicBody;
        rightwheelbdef.fixedRotation = false;
        Body rightwheelbody = world.createBody(rightwheelbdef);
        rightwheelbody.setUserData("tank1");

        CircleShape shapec = new CircleShape();
        shapec.setRadius(14 / ppm);

        fixtureDefright = new FixtureDef();
        fixtureDefright.shape = shapec;
        fixtureDefright.density = 40.0f;
        fixtureDefright.friction = 30;

        fixtureDefleft = new FixtureDef();
        fixtureDefleft.shape = shapec;
        fixtureDefleft.density = 40.0f;
        fixtureDefleft.friction = 30;

        leftwheelbody.createFixture(fixtureDefleft);
        rightwheelbody.createFixture(fixtureDefright);

        RevoluteJointDef rdef = new RevoluteJointDef();
        rdef.bodyA = tankbody;
        rdef.bodyB = rightwheelbody;
        rdef.collideConnected = false;
        rdef.enableMotor = true;
        rdef.motorSpeed = 0;
        rdef.maxMotorTorque = 130;
        rdef.localAnchorA.set(28 / ppm, -15 / ppm);

        RevoluteJointDef rdef2 = new RevoluteJointDef();
        rdef2.bodyA = tankbody;
        rdef2.bodyB = leftwheelbody;
        rdef2.collideConnected = false;
        rdef2.enableMotor = true;
        rdef2.motorSpeed = 0;
        rdef2.maxMotorTorque = 130;
        rdef2.localAnchorA.set(-28 / ppm, -15 / ppm);
//
        RevoluteJoint rightwheel = (RevoluteJoint) world.createJoint(rdef);
        RevoluteJoint leftwheel = (RevoluteJoint) world.createJoint(rdef2);
            players[1].rendertank(tankbody, rightwheel, leftwheel);
    }
    public static void shoot(float x, float y, float deg,float power){

        BodyDef bdef1=new BodyDef();
        bdef1.position.set(x/ppm,y/ppm);
        bdef1.type= BodyDef.BodyType.DynamicBody;
        b1=world.createBody(bdef1);

        shapeammo=new PolygonShape();
        shapeammo.setAsBox(26/ppm,7.5f/ppm);

        FixtureDef fdef=new FixtureDef();
        fdef.shape=shapeammo;
        fdef.restitution=0;
        b1.createFixture(fdef);
        b1.setUserData("ammo6");


        sprite3 = new Sprite(new TextureRegion(ammo6.getAmmoImage()));
        sprite3.setSize(50, 15);
        sprite3.setOrigin(25,7.5f);
        startingVelocity =new Vector2(3,4);
        startingVelocity.rotateDeg(deg -45);
        b1.setTransform(b1.getPosition(), 90);
        b1.setLinearVelocity(startingVelocity.scl(power/4));
    }
    @Override
    public void hide() {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void dispose() {
        batch.dispose();
        mShapeRenderer.dispose();
        stage.dispose();
        world.dispose();
        bd.dispose();
        font.dispose();
    }
    private void showhealth(float x,float y) {
        float healthbar1 = x*1.7f ;
        float healthbar2=y*1.7f;

        mShapeRenderer.setColor(Color.GREEN);
        mShapeRenderer.rect(115, 532, healthbar1, 18);
        mShapeRenderer.circle(118,541,9);
        mShapeRenderer.rect(565, 532, healthbar2, 18);
        mShapeRenderer.circle(568,541,9);
    }
    public void spawnbox(float x,float y){
        Texture box = new Texture(Gdx.files.internal("box.png"));
        BodyDef bdef1=new BodyDef();
        bdef1.position.set(x/ppm,y/ppm);
        bdef1.type= BodyDef.BodyType.DynamicBody;
        b5=world.createBody(bdef1);

        PolygonShape shapebox = new PolygonShape();
        shapebox.setAsBox(26/ppm,7.5f/ppm);

        FixtureDef fdef=new FixtureDef();
        fdef.shape=shapebox;
        fdef.restitution=0;
        b5.createFixture(fdef);
        b5.setUserData("box");

        sprite4 = new Sprite(new TextureRegion(box));
        sprite4.setSize(50, 50);
        sprite4.setOrigin(25,7.5f);
    }
    public void showfuel(float x,float v){
        float fuelbar1 = 1.26f*v;
        mShapeRenderer.setColor(Color.ORANGE);
        mShapeRenderer.rect(x, 81, fuelbar1, 13);
        mShapeRenderer.circle(x+3,87.5f,7);

    }
    public void powerbar(){



    }
}
