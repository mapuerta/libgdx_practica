package com.hello.work;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyActor extends Actor {
	 public int MX = 0;
	 public int MY = 0;
	 public TextureRegion keyframe;
	 public TextureRegion[] sequence;
	 public static Animation ultron;
	 public TextureAtlas textureAtlas;
	 public float transition = 0.2f;
	 public float stateTime = 0; 

	  
	  public MyActor(){
		  textureAtlas = new TextureAtlas("/home/vauxoo/workspace/ultron/ansinble.atlas");
		  ultron = new Animation(transition, textureAtlas.findRegion("I2"));
		 
		  
      }
	  
	  @Override
      public void draw(Batch batch, float alpha){
		  
		  batch.draw(keyframe, MX, 0);
		  
      }
	  
	 
	   @Override
       public void act(float delta){
		   stateTime +=delta;
		   ultron = new Animation(transition, textureAtlas.findRegion("I2"));
		   if (Gdx.input.isKeyPressed(Keys.RIGHT)){
			   MX +=1;
			   get_sequence(3, 'M');
		   }
		   if (Gdx.input.isKeyPressed(Keys.LEFT)){
			   MX -=1;
			   get_sequence(3, 'M');
		   }
		   if (Gdx.input.isKeyPressed(Keys.UP)){
			   MY +=1;
			   get_sequence(2, 'I');
		   }
		   if (Gdx.input.isKeyPressed(Keys.A)){
			   get_sequence(3, 'A');
		   }
		   keyframe = (TextureRegion) ultron.getKeyFrame(stateTime, true);
       }
	   
	   public void get_sequence(int num, char c){
		   
		   int index = 0;
		   sequence = new TextureRegion[num * 1];
		   for (int i=1; i<=num; i++){
			   String seq = c+Integer.toString(i);
			   sequence[index++] =  textureAtlas.findRegion(seq);
			   System.out.println(seq);
		   }
		   ultron = new Animation(transition, sequence);
	   }
	
	
}