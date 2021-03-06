package com.hello.work;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Gusano implements ApplicationListener {
	
	public static final int COLS = 12, ROW = 1;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureRegion region, explosion;
	private Texture img;
	Animation<TextureRegion> attack, movel, mover;
	private int h;
	private int w;
	float stateTime;
	private int positionx, positiony;
	boolean moveR, moveUP, moveL;
	private ShapeRenderer shrend;

	@Override
	public void create() {
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		String extRoot = Gdx.files.getLocalStoragePath();
		img = new Texture(Gdx.files.absolute("../texturas/gussan.png"));
		Texture explo = new Texture(Gdx.files.absolute("../texturas/explocion.jpg"));
		
		//img = new Texture(Gdx.files.classpath("/assets/gussan.png"));
		TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth()/COLS,
				                                    img.getHeight()/ROW);
		
		region = tmp[0][0];
		explosion = new TextureRegion(explo);
		TextureRegion[] Attack =  new TextureRegion[4 * 1];
		TextureRegion[] MoveLef = new TextureRegion[4 * 1];
		TextureRegion[] MoveRig = new TextureRegion[4 * 1];
		
		
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
				Attack[index] = tmp[i][j];
				MoveLef[index] = tmp[i][j+4];
				MoveRig[index] = tmp[i][j+4+4];
				index ++;
			}
		}

		attack = new Animation<TextureRegion>(0.25f, Attack);
		movel = new Animation<TextureRegion>(0.25f, MoveLef);
		mover = new Animation<TextureRegion>(0.25f, MoveRig);
		batch = new SpriteBatch();
		stateTime = 0f;
		positionx = 100;
		//camera = new OrthographicCamera();
		//camera.setToOrtho(false, w, h);
		
	}
		
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		moveR = Gdx.input.isKeyPressed(Keys.RIGHT);
		moveUP = Gdx.input.isKeyPressed(Keys.UP);
		moveL = Gdx.input.isKeyPressed(Keys.LEFT);
		TextureRegion currentFrame = region;
		if (moveR){
			positionx +=1;
			currentFrame = movel.getKeyFrame(stateTime, true);
		}
		if (moveL){
			positionx -=1;
			currentFrame = mover.getKeyFrame(stateTime, true);
		}
		if (moveUP){
			positiony +=1;
			currentFrame = attack.getKeyFrame(stateTime, true);
		}
		
		if (positionx == 10 || positionx <= 40){
			currentFrame = explosion;
			
		}
		
		batch.begin();
		batch.draw(currentFrame, positionx, positiony);
		batch.end();
		create_shape(10, 0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		 shrend.dispose();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	public void create_shape(int x, int y){
		shrend = new ShapeRenderer();
		shrend.begin(ShapeType.Filled);
	    shrend.setColor(Color.RED);
	    shrend.rect(x, y, 30, h);
	    shrend.end();
	   
		
	}
	

}
