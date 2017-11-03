package mvmg;

import java.applet.Applet;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;

import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.geometry.Box;
import javax.swing.Timer;

import com.sun.j3d.utils.image.*;
import java.io.*;
import javafx.scene.*;
public class Renderer2D extends Canvas implements KeyListener, ActionListener {

	gameController gameKernel;
	Renderer hostRenderer;
	int testVarX = 40;
	int testVarY = 40;
	int testCount = 0;
	
	public Renderer2D(gameController kernel, Renderer renderer){
		this.gameKernel=kernel;
		this.hostRenderer=renderer;
	}
	
	public void keyPressed(KeyEvent e){
		
	}
	
	public void keyReleased(KeyEvent e){
		
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	public void actionPerformed(ActionEvent e ) {

	}
	
	@Override
	public void paint(Graphics g){
		/*g.setColor(new Color(0,0,0));
		if (testCount==1){
			g.drawString(hostRenderer.messageOfTheInstance, testVarX, testVarY);
		}
		else {
			g.drawString("ha", testVarX, testVarY);
		}
		testCount++;
		testVarX=testVarX+10;
		testVarY=testVarY+10;*/
	}

}
