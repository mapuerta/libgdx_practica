package com.hello.work;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Assets {
    Properties properties;
	public Assets() throws FileNotFoundException, IOException{
		Properties properties = new Properties();
		properties.load(new FileInputStream("../texturas/ultron.properties"));
		System.out.println(properties);
		System.out.println(properties.getProperty("live"));
		System.out.println(properties.getProperty("move"));
		System.out.println(properties.getProperty("attack").split(",")[0]);
	}
	
	public void get_animate(Texture img){
		
	}
	
	public static void main (String[] arg) throws FileNotFoundException, IOException {
		Assets assets = new Assets();

	}
}
