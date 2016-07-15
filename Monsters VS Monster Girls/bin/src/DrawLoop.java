import javax.vecmath.Color3f;
import java.applet.Applet;
import java.awt.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.VirtualUniverse;
import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.geometry.Box;
import javax.swing.Timer;
import javax.media.j3d.OrientedShape3D;
import com.sun.j3d.utils.image.*;
import java.io.*;
import javafx.scene.*;

public class DrawLoop implements Runnable {
	gameController gameKernel = null;
	Renderer gameRenderer = null;
	int standPhase=0;
	int count=0;
	int nonDrawCount=0;
	boolean changeText1=false;
	boolean changeText2=false;
	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	Color3f blue = new Color3f(0.0f, .0f, 1.0f);
	Color3f red = new Color3f(1.0f,0.0f,0.0f);
	boolean scrollingOn = false;
	double RNG = Math.random();
	int RNGi = 0;
	public void setGameControllerAndRenderer(gameController kernel, Renderer renderer){
		this.gameKernel=kernel;
		this.gameRenderer=renderer;
	}
	public void run(){
		while (true){
		try {
			Thread.sleep(1);
			//Have a pre-defined set of vars that are incremented and reset
			//for certain animations.
			if (standPhase>=4){
				standPhase=0;
			}
			//Pick every unit on the map, look at their sprite sets, and animate
			//them.
			for (int i = 0;i<gameKernel.getCurrentMap().getEntitiesOnMap().size();i++){
				Entity selectedUnit = gameKernel.getCurrentMap().getEntitiesOnMap().get(i);
				//The current conditionals are temporary until I can develop
				//a framework that will find the relevant sprite to select
				//for all units.
				if (standPhase==0){
					if (selectedUnit.getFacing()==3){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
					else if (selectedUnit.getFacing()==2){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 0);
					}
					else if (selectedUnit.getFacing()==1){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 0);
					}
					else if (selectedUnit.getFacing()==0){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 0);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
				}
				else if (standPhase==1){
					if (selectedUnit.getFacing()==3){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 1);
					}
					else if (selectedUnit.getFacing()==2){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 1);
					}
					else if (selectedUnit.getFacing()==1){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 1);
					}
					else if (selectedUnit.getFacing()==0){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 1);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 1);
					}
				}
				else if (standPhase==2){
					if (selectedUnit.getFacing()==3){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
					else if (selectedUnit.getFacing()==2){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 0);
					}
					else if (selectedUnit.getFacing()==1){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 0);
					}
					else if (selectedUnit.getFacing()==0){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 0);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
				}
				else if (standPhase==3){
					if (selectedUnit.getFacing()==3){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 2);
					}
					else if (selectedUnit.getFacing()==2){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 2);
					}
					else if (selectedUnit.getFacing()==1){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 2);
					}
					else if (selectedUnit.getFacing()==0){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 2);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 2);
					}
				}
				else{
					if (selectedUnit.getFacing()==3){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
					else if (selectedUnit.getFacing()==2){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 0);
					}
					else if (selectedUnit.getFacing()==1){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 0);
					}
					else if (selectedUnit.getFacing()==0){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 0);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
				}
				//Update the unit's transform to reflect their new position
				//if it has changed.
				selectedUnit.updateTransform(gameRenderer);
			}
			if (count==30){
			standPhase++;
			}
			//Pick all cubes and insure that a cube a unit is standing on is highlighted
			//correctly depending on whether or not it's that unit's turn.
			if (gameKernel.isBattleActive()==true){
				for (int i=0;i<gameKernel.getCurrentMap().getCubeList().size();i++){
					gameCube pickedCube=gameKernel.getCurrentMap().getCubeList().get(i);
					if (pickedCube.getEntity()==null){
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(blue);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(blue);
					}
					else if (gameKernel.getCurrentBattle().getCurrentUnitTurn()==pickedCube.getEntity()){
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(red);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(red);
						if (gameKernel.isFreelookActive==false){
							gameRenderer.observedSpace=pickedCube;
						}
						//System.out.println(pickedCube.getAssociatedSphereOfHelp());
						//System.out.println("Cube at "+pickedCube.getX()+","+pickedCube.getY()+","+pickedCube.getZ()+" should have been set to red.");
						//System.out.println("It is "+pickedCube.getEntity()+"'s turn.");
					}
					else {
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(blue);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(blue);
					}
					
				}
				/*gameRenderer.move.setString("Move");
				gameRenderer.attack.setString("Attack");
				gameRenderer.spell.setString("Spell");
				gameRenderer.endTurn.setString("End Turn");*/
				gameRenderer.nameLabel.setString(gameKernel.currentBattle.getCurrentUnitTurn().getName());
				gameRenderer.playerLabel.setString(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getName());
				gameRenderer.levelLabel.setString("Level: "+Integer.toString(gameKernel.currentBattle.getCurrentUnitTurn().getLevel()));
				gameRenderer.healthLabel.setString("HP: "+Double.toString(gameKernel.currentBattle.getCurrentUnitTurn().HP));
				gameRenderer.magicLabel.setString("MP: "+Double.toString(gameKernel.currentBattle.getCurrentUnitTurn().MP));
				if (gameKernel.isFreelookActive==true){
					if (changeText1==false){
					gameRenderer.bottomLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.move.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.attack.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.spell.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.endTurn.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.nameLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.playerLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.levelLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.healthLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.magicLabel.getAppearance().getRenderingAttributes().setVisible(false);
					changeText1=true;
					changeText2=false;
					}
				}
				else{
					if (changeText2==false){
					gameRenderer.bottomLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.move.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.attack.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.spell.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.endTurn.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.nameLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.playerLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.levelLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.healthLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.magicLabel.getAppearance().getRenderingAttributes().setVisible(true);
					changeText2=true;
					changeText1=false;
					}
				}
				//gameRenderer.nameLabel.getAppearance().setColoringAttributes(new ColoringAttributes(gameRenderer.menuWhite,ColoringAttributes.FASTEST));
				//gameRenderer.playerLabel.getAppearance().getColoringAttributes().setColor(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getColor());
				//gameRenderer.levelLabel.getAppearance().getColoringAttributes().setColor(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getColor());
				//gameRenderer.healthLabel.getAppearance().getColoringAttributes().setColor(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getColor());
				//gameRenderer.magicLabel.getAppearance().getColoringAttributes().setColor(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getColor());
				
			}
			//Now start updating the camera.
				if (gameRenderer.observedPoint2.x>gameRenderer.Point2.x){
					gameRenderer.observedPoint2.x-=0.01;
				}
				if (gameRenderer.observedPoint2.x<gameRenderer.Point2.x){
					gameRenderer.observedPoint2.x+=0.01;
				}
				if (gameRenderer.observedPoint2.y>gameRenderer.Point2.y){
					gameRenderer.observedPoint2.y-=0.01;
				}
				if (gameRenderer.observedPoint2.y<gameRenderer.Point2.y){
					gameRenderer.observedPoint2.y+=0.01;
				}
				if (gameRenderer.observedPoint2.z>gameRenderer.Point2.z){
					gameRenderer.observedPoint2.z-=0.01;
				}
				if (gameRenderer.observedPoint2.z<gameRenderer.Point2.z){
					gameRenderer.observedPoint2.z+=0.01;
				}
			gameRenderer.Point2.x=gameRenderer.observedSpace.getAssociatedTransformVector().x;
			gameRenderer.Point2.y=gameRenderer.observedSpace.getAssociatedTransformVector().y;
			gameRenderer.Point2.z=gameRenderer.observedSpace.getAssociatedTransformVector().z;
			gameRenderer.Point1.x=-2+gameRenderer.Point2.x;
			gameRenderer.Point1.y=-2+gameRenderer.Point2.y;
			gameRenderer.Point1.z=2+gameRenderer.Point2.z;
			//gameRenderer.observedPoint2.x=gameRenderer.observedSpace.getAssociatedTransformVector().x;
			//gameRenderer.observedPoint2.y=gameRenderer.observedSpace.getAssociatedTransformVector().y;
			//gameRenderer.observedPoint2.z=gameRenderer.observedSpace.getAssociatedTransformVector().z;
			gameRenderer.observedPoint1.x=-2+gameRenderer.observedPoint2.x;
			gameRenderer.observedPoint1.y=-2+gameRenderer.observedPoint2.y;
			gameRenderer.observedPoint1.z=2+gameRenderer.observedPoint2.z;
			gameRenderer.lookAt.lookAt(gameRenderer.observedPoint1, gameRenderer.observedPoint2, gameRenderer.lookingAngle);
			gameRenderer.lookAt.invert();
			gameRenderer.viewGroup.setTransform(gameRenderer.lookAt);
			count++;
			//nonDrawCount++;
			if (count>30){
				count=0;
			}
			if (nonDrawCount>180){
				RNG = Math.random();
				RNGi = (int)(RNG*gameKernel.currentMap.getCubeList().size()-1)+1;
				gameRenderer.observedSpace=gameKernel.currentMap.getCubeList().get(RNGi);
				nonDrawCount=0;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}

