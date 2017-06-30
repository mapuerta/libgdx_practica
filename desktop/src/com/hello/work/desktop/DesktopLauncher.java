package com.hello.work.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hello.work.Figura;
import com.hello.work.Gusano;
import com.hello.work.HelloWork;
import com.hello.work.MyActor;
import com.hello.work.SceneDemo;
import com.hello.work.Ultron;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SceneDemo(), config);
	}
}
