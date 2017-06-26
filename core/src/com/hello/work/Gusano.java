package com.hello.work;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Gusano implements ApplicationListener {
	
	public static final int COLS = 12, ROW = 1;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureRegion region;
	private Texture img;
	Animation<TextureRegion> attack, movel, mover;
	private int h;
	private int w;
	float stateTime;
	private int positionx, positiony;
	boolean moveR, moveUP, moveL; 

	@Override
	public void create() {
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		img = new Texture(Gdx.files.absolute("/home/vauxoo/workspace/texturas/gussan.png"));
		TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth()/COLS,
				                                    img.getHeight()/ROW);
		
		region = tmp[0][0];
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
		
		batch.begin();
		batch.draw(currentFrame, positionx, positiony);
		batch.end();
		
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
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
