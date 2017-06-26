package com.hello.work;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Figura implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shrend;
	private int h;
	private int w;
	private int positionx, positiony;
	boolean moveR, moveUP, moveDW,moveL; 

	@Override
	public void create() {
		
		h = Gdx.graphics.getHeight();
		w = Gdx.graphics.getWidth();
		shrend = new ShapeRenderer();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		positionx = 0;
		positiony = 0;

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
	    shrend.begin(ShapeType.Line);
        shrend.setColor(Color.BLUE);
		moveR = Gdx.input.isKeyPressed(Keys.RIGHT);
		moveUP = Gdx.input.isKeyPressed(Keys.UP);
		moveDW = Gdx.input.isKeyPressed(Keys.DOWN);
		moveL = Gdx.input.isKeyPressed(Keys.LEFT);
		if (moveR){
			positionx +=1;
		}
		if (moveL){
			positionx -=1;
		}
		if (moveUP){
			positiony +=1;
		}
		if (moveDW){
			positiony -=1;
		}
		
        shrend.rect(positionx, positiony, 30, 30);
        //shrend.rotate(0, 0, 1, 90);
        shrend.end();
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
	      shrend.dispose();
		
	}
	

}
