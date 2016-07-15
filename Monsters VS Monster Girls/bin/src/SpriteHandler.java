/*The SpriteHandler class is designed to handle the sprites associated with an
 * entity, which is ultimately passed into the rendering engine. A SpriteHandler
 * object is attached to an entity and contains the entity's sprite map. The
 * SpriteHandler works with the rendering engine to determine which portion of the
 * sprite sheet is mapped.*/
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;

import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.image.*;
import java.io.*;
public class SpriteHandler {
	Entity attachedEntity = null;
	File spriteMap = null;
	/*The array lists below are all attached by holding a single common number
	 * in which related elements are held. Each section of the sprite sheet
	 * is associated with a string identifier for when it should be used, which
	 * is checked by the renderer. The array list nested within the array list
	 * contains the actual sprite sections which form part of one animation.*/
	ArrayList<ArrayList<SpriteMapSection>> spriteSections = new ArrayList<ArrayList<SpriteMapSection>>();
	/*Since this is an isometric game where each unit typically has four facings,
	 * there must be four sets of each sprite state, one for each facing. The
	 * identifiers are numbered accordingly with a number '0' (for north), '1'
	 * (for east), '2' (for south), and '3' (for west) accordingly.
	 * For now, the only identifiers that should exist are stand0, stand1,
	 * stand2, and stand3.*/
	ArrayList<String> spriteSectionIdentifiers = new ArrayList<String>();
	java.awt.image.BufferedImage BI = null;
	ImageComponent2D pImg = null;
	Raster nativeRaster = null;
	OrientedShape3D attachedShape3D = null;
	public SpriteHandler(Entity targetEntity, File spriteSheet){
		this.attachedEntity=targetEntity;
		this.attachedEntity.setSpriteHandler(this);
		this.spriteMap=spriteSheet;
		try{
			BI = ImageIO.read(spriteMap);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		pImg = new ImageComponent2D(ImageComponent2D.FORMAT_RGBA,BI);
		pImg.setCapability(ImageComponent2D.ALLOW_IMAGE_READ);
		pImg.setCapability(ImageComponent2D.ALLOW_IMAGE_WRITE);
		pImg.setCapability(ImageComponent2D.ALLOW_SIZE_READ);
		//NOTE: Make sure to set the point of this raster before using it!!
		nativeRaster = new Raster(new Point3f(0.0f,0.0f,0.0f),Raster.RASTER_COLOR,0,0,0,0,pImg,null);
		nativeRaster.setCapability(Raster.ALLOW_IMAGE_READ);
		nativeRaster.setCapability(Raster.ALLOW_IMAGE_WRITE);
		nativeRaster.setCapability(Raster.ALLOW_SIZE_READ);
		nativeRaster.setCapability(Raster.ALLOW_SIZE_WRITE);
		nativeRaster.setCapability(Raster.ALLOW_OFFSET_WRITE);
		nativeRaster.setCapability(Raster.ALLOW_OFFSET_READ);
		nativeRaster.setCapability(Raster.ALLOW_POSITION_READ);
		nativeRaster.setCapability(Raster.ALLOW_POSITION_WRITE);
	}
	//Use this constructor if you want to clone from an existing sprite handler.
	//Useful if you're trying to make an army of Vampire Girls.
	public SpriteHandler(Entity targetExportEntity, SpriteHandler cloneFromSpriteHandler){
		this.attachedEntity=targetExportEntity;
		this.attachedEntity.setSpriteHandler(this);
		this.spriteMap=cloneFromSpriteHandler.getSpriteMap();
		this.BI=cloneFromSpriteHandler.BI;
		pImg = new ImageComponent2D(ImageComponent2D.FORMAT_RGBA,BI);
		pImg.setCapability(ImageComponent2D.ALLOW_IMAGE_READ);
		pImg.setCapability(ImageComponent2D.ALLOW_IMAGE_WRITE);
		pImg.setCapability(ImageComponent2D.ALLOW_SIZE_READ);
		//NOTE: Make sure to set the point of this raster before using it!!
		nativeRaster = new Raster(new Point3f(0.0f,0.0f,0.0f),Raster.RASTER_COLOR,0,0,0,0,pImg,null);
		nativeRaster.setCapability(Raster.ALLOW_IMAGE_READ);
		nativeRaster.setCapability(Raster.ALLOW_IMAGE_WRITE);
		nativeRaster.setCapability(Raster.ALLOW_SIZE_READ);
		nativeRaster.setCapability(Raster.ALLOW_SIZE_WRITE);
		nativeRaster.setCapability(Raster.ALLOW_OFFSET_WRITE);
		nativeRaster.setCapability(Raster.ALLOW_OFFSET_READ);
		nativeRaster.setCapability(Raster.ALLOW_POSITION_READ);
		nativeRaster.setCapability(Raster.ALLOW_POSITION_WRITE);
		this.spriteSectionIdentifiers=cloneFromSpriteHandler.spriteSectionIdentifiers;
		this.spriteSections=cloneFromSpriteHandler.spriteSections;
	}
	Entity getEntity(){
		return this.attachedEntity;
	}
	/*Perhaps on the off-chance that a unit shapeshifts or otherwise uses someone else's
	 * spriteset, this method makes that possible.*/
	void setEntity(Entity targetEntity){
		this.attachedEntity=targetEntity;
		this.attachedEntity.setSpriteHandler(this);
	}
	File getSpriteMap(){
		return this.spriteMap;
	}
	//This step must be done before adding any sprite sections to the SpriteHandler object.
	void addSpriteSectionSet(ArrayList<SpriteMapSection> spriteSectionSet, String sectionIdentifier){
		this.spriteSectionIdentifiers.add(sectionIdentifier);
		this.spriteSections.add(spriteSectionSet);
	}
	//Make sure the sprite section set exists before adding a sprite section to it.
	void createNewSpriteSectionAndAddToSet(int targetSet, int startX, int startY, int width, int height){
		spriteSections.get(targetSet).add(new SpriteMapSection(startX,startY,width,height));
	}
	/*The method below will export the selected section of the sprite to the native
	 * raster.*/
	void exportSpriteSectionToRaster(int spriteSet, int spriteSection){
		SpriteMapSection referredSection = spriteSections.get(spriteSet).get(spriteSection);
		nativeRaster.setSrcOffset(referredSection.startPositionX, referredSection.startPositionY);
		nativeRaster.setSize(referredSection.width, referredSection.height);
		nativeRaster.setDstOffset(-referredSection.width/2, -referredSection.height);
	}
	Raster getNativeRaster(){
		return this.nativeRaster;
	}
	OrientedShape3D getShape3D(){
		return this.attachedShape3D;
	}
	void attachOrientedShape3D(OrientedShape3D shape3D){
		this.attachedShape3D=shape3D;
	}
}
