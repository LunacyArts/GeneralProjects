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

/*This thread functions to update the screen of the Canvas3D of the
 * Renderer, and all nodes
 * within. Restrict its usage only to this purpose, if possible.*/
public class DrawLoop implements Runnable {
	gameController gameKernel = null;
	Renderer gameRenderer = null;
	int standPhase=0;
	int count=0;
	int nonDrawCount=0;
	int countEndInt=90;
	boolean changeCubeColor1=false;
	boolean changeCubeColor2=false;
	boolean changeCubeColor3=false;
	boolean changeText2=false;
	int colorChangeTestR=0;
	int colorChangeTestC=0;
	int colorChangeTestB=0;
	boolean colorChangeTestComplete=false;
	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	Color3f blue = new Color3f(0.0f, .0f, 1.0f);
	Color3f red = new Color3f(1.0f,0.0f,0.0f);
	Color3f cyan = new Color3f(0.0f,1.0f,1.0f);
	boolean scrollingOn = false;
	Entity movingUnit=null;
	boolean unitIsMoving=false;
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
				Point3f currentSpritePoint = gameKernel.getCurrentMap().getEntitiesOnMap().get(i).spritePoint;
				Point3f destinationSpritePoint = gameKernel.getCurrentMap().getEntitiesOnMap().get(i).destinationSpritePoint;
				//The current conditionals are temporary until I can develop
				//a framework that will find the relevant sprite to select
				//for all units.
				if (standPhase==0){
					if (selectedUnit.getFacing()==Entity.WEST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
					else if (selectedUnit.getFacing()==Entity.SOUTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 0);
					}
					else if (selectedUnit.getFacing()==Entity.EAST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 0);
					}
					else if (selectedUnit.getFacing()==Entity.NORTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 0);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
				}
				else if (standPhase==1){
					if (selectedUnit.getFacing()==Entity.WEST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 1);
					}
					else if (selectedUnit.getFacing()==Entity.SOUTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 1);
					}
					else if (selectedUnit.getFacing()==Entity.EAST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 1);
					}
					else if (selectedUnit.getFacing()==Entity.NORTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 1);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 1);
					}
				}
				else if (standPhase==2){
					if (selectedUnit.getFacing()==Entity.WEST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
					else if (selectedUnit.getFacing()==Entity.SOUTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 0);
					}
					else if (selectedUnit.getFacing()==Entity.EAST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 0);
					}
					else if (selectedUnit.getFacing()==Entity.NORTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 0);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
				}
				else if (standPhase==3){
					if (selectedUnit.getFacing()==Entity.WEST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 2);
					}
					else if (selectedUnit.getFacing()==Entity.SOUTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 2);
					}
					else if (selectedUnit.getFacing()==Entity.EAST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 2);
					}
					else if (selectedUnit.getFacing()==Entity.NORTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 2);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 2);
					}
				}
				else{
					if (selectedUnit.getFacing()==Entity.WEST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
					else if (selectedUnit.getFacing()==Entity.SOUTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(2, 0);
					}
					else if (selectedUnit.getFacing()==Entity.EAST){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(1, 0);
					}
					else if (selectedUnit.getFacing()==Entity.NORTH){
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(0, 0);
					}
					else {
						selectedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
					}
				}
				if (selectedUnit.unitIsMoving==true){
					unitIsMoving=true;
					movingUnit=selectedUnit;
					destinationSpritePoint.setX((float)selectedUnit.drawingMovementPath.get(0).getAssociatedTransformVector().x-0.05f);
					destinationSpritePoint.setY((float)selectedUnit.drawingMovementPath.get(0).getAssociatedTransformVector().y-0.05f);
					destinationSpritePoint.setZ((float)selectedUnit.drawingMovementPath.get(0).getAssociatedTransformVector().z+0.075f);
					selectedUnit.setFacing(selectedUnit.drawingMovementPathDirection.get(0));
					//System.out.println(currentSpritePoint.getX()+" compare to "+destinationSpritePoint.getX());
					//System.out.println(currentSpritePoint.getY()+" compare to "+destinationSpritePoint.getY());
					//System.out.println(currentSpritePoint.getZ()+" compare to "+destinationSpritePoint.getZ());
					if (currentSpritePoint.getX()>destinationSpritePoint.getX()){
						currentSpritePoint.setX(currentSpritePoint.getX()-0.005f);
					}
					if (currentSpritePoint.getX()<destinationSpritePoint.getX()){
						currentSpritePoint.setX(currentSpritePoint.getX()+0.005f);
					}
					if (currentSpritePoint.getY()>destinationSpritePoint.getY()){
						currentSpritePoint.setY(currentSpritePoint.getY()-0.005f);
					}
					if (currentSpritePoint.getY()<destinationSpritePoint.getY()){
						currentSpritePoint.setY(currentSpritePoint.getY()+0.005f);
					}
					if (currentSpritePoint.getZ()>destinationSpritePoint.getZ()){
						currentSpritePoint.setZ(currentSpritePoint.getZ()-0.005f);
					}
					if (currentSpritePoint.getZ()<destinationSpritePoint.getZ()){
						currentSpritePoint.setZ(currentSpritePoint.getZ()-0.005f);
					}
					if (currentSpritePoint.getX()>=destinationSpritePoint.getX()-0.005f&&currentSpritePoint.getX()<=destinationSpritePoint.getX()+0.005f){
						currentSpritePoint.setX(destinationSpritePoint.getX());
					}
					if (currentSpritePoint.getY()>=destinationSpritePoint.getY()-0.005f&&currentSpritePoint.getY()<=destinationSpritePoint.getY()+0.005f){
						currentSpritePoint.setY(destinationSpritePoint.getY());
					}
					if (currentSpritePoint.getZ()>=destinationSpritePoint.getZ()-0.005f&&currentSpritePoint.getZ()<=destinationSpritePoint.getZ()+0.005f){
						currentSpritePoint.setZ(destinationSpritePoint.getZ());
					}
					if (currentSpritePoint.getX()==destinationSpritePoint.getX()&&currentSpritePoint.getY()==destinationSpritePoint.getY()&&currentSpritePoint.getZ()==destinationSpritePoint.getZ()){
						if (selectedUnit.drawingMovementPath.size()<=1){
							unitIsMoving=false;
							selectedUnit.unitIsMoving=false;
							gameKernel.currentMenuIdent="battleOptions";
						}
						else {
							selectedUnit.drawingMovementPath.remove(0);
							selectedUnit.drawingMovementPathDirection.remove(0);
						}
					}
				}
				else{
					currentSpritePoint.setX((float)selectedUnit.getCurrentCube().getAssociatedTransformVector().x-0.05f);
					currentSpritePoint.setY((float)selectedUnit.getCurrentCube().getAssociatedTransformVector().y-0.05f);
					currentSpritePoint.setZ((float)selectedUnit.getCurrentCube().getAssociatedTransformVector().z+0.075f);
				}
				//Update the unit's transform to reflect their new position
				//if it has changed.
				selectedUnit.updateTransformAtTargetPoint(gameRenderer,selectedUnit.spritePoint);
			}
			if (count==countEndInt){
			standPhase++;
			}
			//Pick all cubes and insure that a cube a unit is standing on is highlighted
			//correctly depending on whether or not it's that unit's turn.
			if (colorChangeTestComplete==false){
				if (colorChangeTestR<1){
				for (int i=0;i<gameKernel.getCurrentMap().getCubeList().size();i++){
					gameCube pickedCube=gameKernel.getCurrentMap().getCubeList().get(i);
					pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(red);
					pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(red);
				}
				colorChangeTestR++;
				}
				else if (colorChangeTestC<1){
					for (int i=0;i<gameKernel.getCurrentMap().getCubeList().size();i++){
						gameCube pickedCube=gameKernel.getCurrentMap().getCubeList().get(i);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(cyan);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(cyan);
					}
					colorChangeTestC++;
				}
				else if (colorChangeTestB<1){
					for (int i=0;i<gameKernel.getCurrentMap().getCubeList().size();i++){
						gameCube pickedCube=gameKernel.getCurrentMap().getCubeList().get(i);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(blue);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(blue);
					}
					colorChangeTestB++;
				}
				else {
					System.out.println("Color test complete.");
					colorChangeTestComplete=true;
				}
			}
			if (gameKernel.isBattleActive()==true){
				if(colorChangeTestComplete==true){
				for (int i=0;i<gameKernel.getCurrentMap().getCubeList().size();i++){
					gameCube pickedCube=gameKernel.getCurrentMap().getCubeList().get(i);
					/*else if (pickedCube.isHighlighted==false&&pickedCube.isColorChanged==true){
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(blue);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(blue);
						pickedCube.isColorChanged=false;
						changeCubeColor3=false;
					}*/
					/*else if (pickedCube.getEntity()==null){
						if (pickedCube.isColorChanged==false){
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(blue);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(blue);
						pickedCube.isColorChanged=false;
						changeCubeColor1=true;
						changeCubeColor2=false;
						changeCubeColor3=false;
						}
					}*/
					if (gameKernel.getCurrentBattle().getCurrentUnitTurn()==pickedCube.getEntity()){
						if (pickedCube.cubeColor!="red"){
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(red);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(red);
						pickedCube.cubeColor="red";
						//pickedCube.isColorChanged=true;
						//changeCubeColor2=true;
						//changeCubeColor1=false;
						//changeCubeColor3=false;
						pickedCube.colorRenderAttempts=0;
						}
						if (gameKernel.currentMenuIdent=="battleOptions"||gameKernel.currentMenuIdent=="attackMenu"||gameKernel.currentMenuIdent=="spellMenu"){
							gameRenderer.observedSpace=pickedCube;
						}
						//System.out.println(pickedCube.getAssociatedSphereOfHelp());
						//System.out.println("Cube at "+pickedCube.getX()+","+pickedCube.getY()+","+pickedCube.getZ()+" should have been set to red.");
						//System.out.println("It is "+pickedCube.getEntity()+"'s turn.");
					}
					else {
						if (pickedCube.isHighlighted==true&&(pickedCube.cubeColor!="cyan")){
							//pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(cyan);
							//pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(cyan);
							pickedCube.colorRenderAttempts++;
							/*KNOWN ISSUE: The color displayed on-screen for cubes
							 * changed to this color is not necessarily the same as
							 * the color shown in the game's data. The cause of the
							 * issue is as of yet unknown.*/
							if (pickedCube.colorRenderAttempts>1){
							//pickedCube.cubeColor="cyan";
							pickedCube.colorRenderAttempts=0;
							}
							//gameRenderer.currentCanvas3D.update(gameRenderer.currentCanvas3D.getGraphics());;
							//pickedCube.isColorChanged=true;
							//changeCubeColor3=true;
							//changeCubeColor2=false;
							//changeCubeColor1=false;
							
						}
						else if (pickedCube.cubeColor!="blue"&&pickedCube.isHighlighted==false){
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(blue);
						pickedCube.getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(blue);
						pickedCube.cubeColor="blue";
						pickedCube.colorRenderAttempts=0;
						}
						//pickedCube.isColorChanged=false;
					}
					
				}
			}
				/*gameRenderer.move.setString("Move");
				gameRenderer.attack.setString("Attack");
				gameRenderer.spell.setString("Spell");
				gameRenderer.endTurn.setString("End Turn");*/
				/*NOTICE: I have discovered through trial and error that the
				 * method "setString()" from the class Text2D is buggy and does
				 * not perform proper garbage collection, resulting in a
				 * memory leak if overused. In light of this issue, refrain from
				 * using this method unless it is absolutely positively needed.
				 * The use of this method shall follow a similar scheme to the
				 * "switches" used below that are designed to prevent overuse
				 * of the method in the same DrawLoop thread.*/
				if (gameRenderer.unitIsChanged==false){
					gameRenderer.nameLabel.setString(gameKernel.currentBattle.getCurrentUnitTurn().getName());
					gameRenderer.playerLabel.setString(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getName());
					gameRenderer.levelLabel.setString("Level: "+Integer.toString(gameKernel.currentBattle.getCurrentUnitTurn().getLevel()));
					gameRenderer.healthLabel.setString("HP: "+Double.toString(gameKernel.currentBattle.getCurrentUnitTurn().HP));
					gameRenderer.magicLabel.setString("MP: "+Double.toString(gameKernel.currentBattle.getCurrentUnitTurn().MP));
					gameRenderer.unitIsChanged=true;
					
				}
				if (gameKernel.currentMenuIdent=="freeLook"){
					if (gameRenderer.changeText1==false){
					gameRenderer.updateSelectionBox();
					gameRenderer.bottomLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.move.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.attack.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.spell.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.endTurn.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.nameLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.playerLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.levelLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.healthLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.magicLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability1.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability2.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability4.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability5.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability6.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.selectionBoxOS3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.portraitOS3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.changeText1=true;
					}
				}
				if (gameKernel.currentMenuIdent=="battleOptions"){
					if (gameRenderer.changeText1==false){
					gameRenderer.updateSelectionBox();
					gameRenderer.bottomLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.move.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.attack.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.ability.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.spell.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.endTurn.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.nameLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.playerLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.levelLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.healthLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.magicLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.ability1.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability2.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability4.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability5.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability6.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.selectionBoxOS3.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.portraitOS3.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.changeText1=true;
					}
				}
				if (gameKernel.currentMenuIdent=="abilityMenu"||gameKernel.currentMenuIdent=="attackMenu"||gameKernel.currentMenuIdent=="spellMenu"){
					if (gameRenderer.changeText1==false){
					gameRenderer.updateSelectionBox();
					gameRenderer.bottomLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.move.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.attack.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.spell.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.endTurn.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.nameLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.playerLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.levelLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.healthLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.magicLabel.getAppearance().getRenderingAttributes().setVisible(true);
					if (gameKernel.currentMenuSuper.get(gameKernel.currentPage).size()>=1){
						gameRenderer.ability1.getAppearance().getRenderingAttributes().setVisible(true);
						if (gameKernel.currentMenuSuper.get(gameKernel.currentPage).size()>=2){
							gameRenderer.ability2.getAppearance().getRenderingAttributes().setVisible(true);
							if (gameKernel.currentMenuSuper.get(gameKernel.currentPage).size()>=3){
								gameRenderer.ability3.getAppearance().getRenderingAttributes().setVisible(true);
								if (gameKernel.currentMenuSuper.get(gameKernel.currentPage).size()>=4){
									gameRenderer.ability4.getAppearance().getRenderingAttributes().setVisible(true);
									if (gameKernel.currentMenuSuper.get(gameKernel.currentPage).size()>=5){
										gameRenderer.ability5.getAppearance().getRenderingAttributes().setVisible(true);
										if (gameKernel.currentMenuSuper.get(gameKernel.currentPage).size()>=6){
											gameRenderer.ability6.getAppearance().getRenderingAttributes().setVisible(true);
										} else {
											gameRenderer.ability6.getAppearance().getRenderingAttributes().setVisible(false);
										}
									} else {
										gameRenderer.ability5.getAppearance().getRenderingAttributes().setVisible(false);
									}
								} else {
									gameRenderer.ability4.getAppearance().getRenderingAttributes().setVisible(false);
								}
							} else {
								gameRenderer.ability3.getAppearance().getRenderingAttributes().setVisible(false);
							}
						} else {
							gameRenderer.ability2.getAppearance().getRenderingAttributes().setVisible(false);
						}
					} else {
						gameRenderer.ability1.getAppearance().getRenderingAttributes().setVisible(false);
					}
					gameRenderer.selectionBoxOS3.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.portraitOS3.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.changeText1=true;
					}
				}
				if (gameKernel.currentMenuIdent=="moveTrace"||gameKernel.currentMenuIdent=="attackTarget"){
					if (gameRenderer.changeText1==false){
					gameRenderer.updateSelectionBox();
					gameRenderer.bottomLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.move.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.attack.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.spell.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.endTurn.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.nameLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.playerLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.levelLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.healthLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.magicLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability1.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability2.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability4.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability5.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability6.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.selectionBoxOS3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.portraitOS3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.changeText1=true;
					}
				}
				else{
					if (gameRenderer.changeText1==false){
					gameRenderer.updateSelectionBox();
					gameRenderer.bottomLabel.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.move.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.attack.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.spell.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.endTurn.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.nameLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.playerLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.levelLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.healthLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.magicLabel.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.ability1.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability2.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability4.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability5.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.ability6.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.selectionBoxOS3.getAppearance().getRenderingAttributes().setVisible(false);
					gameRenderer.portraitOS3.getAppearance().getRenderingAttributes().setVisible(true);
					gameRenderer.changeText1=true;
					}
				}
				//gameRenderer.nameLabel.getAppearance().setColoringAttributes(new ColoringAttributes(gameRenderer.menuWhite,ColoringAttributes.FASTEST));
				//gameRenderer.playerLabel.getAppearance().getColoringAttributes().setColor(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getColor());
				//gameRenderer.levelLabel.getAppearance().getColoringAttributes().setColor(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getColor());
				//gameRenderer.healthLabel.getAppearance().getColoringAttributes().setColor(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getColor());
				//gameRenderer.magicLabel.getAppearance().getColoringAttributes().setColor(gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().getColor());
				
			}
			//Now start updating the camera.
			if (unitIsMoving==true){
				gameRenderer.observedPoint2.x=movingUnit.spritePoint.x;
				gameRenderer.observedPoint2.y=movingUnit.spritePoint.y;
				gameRenderer.observedPoint2.z=movingUnit.spritePoint.z;
			}
			else{
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
			}
			gameRenderer.Point2.x=gameRenderer.observedSpace.getAssociatedTransformVector().x;
			gameRenderer.Point2.y=gameRenderer.observedSpace.getAssociatedTransformVector().y;
			gameRenderer.Point2.z=gameRenderer.observedSpace.getAssociatedTransformVector().z;
			gameRenderer.Point1.x=-2+gameRenderer.Point2.x;
			gameRenderer.Point1.y=-2+gameRenderer.Point2.y;
			gameRenderer.Point1.z=2+gameRenderer.Point2.z;
			if (gameKernel.currentMenuIdent=="attacking"&&gameRenderer.observedPoint2.x>=gameRenderer.Point2.x-0.01&&gameRenderer.observedPoint2.x<=gameRenderer.Point2.x+0.01&&gameRenderer.observedPoint2.y>=gameRenderer.Point2.y-0.01&&gameRenderer.observedPoint2.y<=gameRenderer.Point2.y+0.01&&gameRenderer.observedPoint2.z>=gameRenderer.Point2.z-0.01&&gameRenderer.observedPoint2.z<=gameRenderer.Point2.z+0.01){
				gameKernel.currentMenuIdent="battleOptions";
			}
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
			if (count>countEndInt){
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

