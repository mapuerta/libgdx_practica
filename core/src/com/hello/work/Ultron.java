package com.hello.work;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ultron implements ApplicationListener {
	public static Animation ultron;
	float stateTime;
	SpriteBatch sb;

	@Override
	public void create() {
		TextureAtlas textureAtlas = new TextureAtlas("/home/vauxoo/workspace/ultron/ansinble.atlas");
		ultron = new Animation(0.2f, textureAtlas.findRegion("01"), textureAtlas.findRegion("02"));
		stateTime = 0f;
		sb = new SpriteBatch();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion keyFrame = (TextureRegion) ultron.getKeyFrame(stateTime, true);
		sb.begin();
		sb.draw(keyFrame, 10, 10);
		sb.end();
		
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
		sb.dispose();
		
	}

}
