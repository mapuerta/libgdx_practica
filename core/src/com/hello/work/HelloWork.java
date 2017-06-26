package com.hello.work;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class HelloWork extends ApplicationAdapter implements InputProcessor {
	  SpriteBatch batch;
	  Sprite sprite;
	  Texture img;
	  World world;
	  Body body;
	  Box2DDebugRenderer debugRenderer;
	  Matrix4 debugMatrix;
	  OrthographicCamera camera;  
	  float torque = 0.0f;
	  boolean drawSprite = true; 
	  final float PIXELS_TO_METERS = 100f;
	

	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture img = new Texture(Gdx.files.absolute("/home/vauxoo/workspace/projects/jet.png"));
		sprite = new Sprite(img);

        sprite.setPosition(-sprite.getWidth()/2,-sprite.getHeight()/2);

        world = new World(new Vector2(0, 0f),true);
		BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyDef.BodyType.DynamicBody;
	    bodyDef.position.set(sprite.getX(), sprite.getY());
	    body = world.createBody(bodyDef);
	    PolygonShape shape = new PolygonShape();
	    shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);
	    FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
        Gdx.input.setInputProcessor(this);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
                 getHeight());
	}

	@Override
	public void resize(int width, int height) {
	
	}
	
	@Override
	public void render () {
		 camera.update();  
	     world.step(1f/60f, 6, 2);
	     body.applyTorque(torque,true);
	     sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.
                  getWidth()/2 , 
       (body.getPosition().y * PIXELS_TO_METERS) -sprite.getHeight()/2 );
	     sprite.setRotation((float)Math.toDegrees(body.getAngle()));

	        Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
	        batch.setProjectionMatrix(camera.combined);
	        
	        // Scale down the sprite batches projection matrix to box2D size
	        debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, 
	                      PIXELS_TO_METERS, 0);
	        
	        batch.begin();
	        
	        if(drawSprite)
	            batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getOriginX(),
	                       sprite.getOriginY(),
	                sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.
	                                getScaleY(),sprite.getRotation());
	                        
	        batch.end();
		
		}
			
	@Override
	public void dispose () {
		 img.dispose();
	      world.dispose();
	
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
	        if(keycode == Input.Keys.RIGHT) 
	            body.setLinearVelocity(1f, 0f);
	        if(keycode == Input.Keys.LEFT)
	            body.setLinearVelocity(-1f,0f);

	        if(keycode == Input.Keys.UP)
	            body.applyForceToCenter(0f,10f,true);
	        if(keycode == Input.Keys.DOWN)
	            body.applyForceToCenter(0f, -10f, true);
	        
	        // On brackets ( [ ] ) apply torque, either clock or counterclockwise
	        if(keycode == Input.Keys.RIGHT_BRACKET)
	            torque += 0.1f;
	        if(keycode == Input.Keys.LEFT_BRACKET)
	            torque -= 0.1f;
	        
	        if(keycode == Input.Keys.BACKSLASH)
	            torque = 0.0f;
	 
	        if(keycode == Input.Keys.SPACE) {
	            body.setLinearVelocity(0f, 0f);
	            body.setAngularVelocity(0f);
	            torque = 0f;
	            sprite.setPosition(0f,0f);
	            body.setTransform(0f,0f,0f);
	        }
	        

	        if(keycode == Input.Keys.ESCAPE)
	            drawSprite = !drawSprite;

		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		 body.applyForce(1f,1f,screenX,screenY,true);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
