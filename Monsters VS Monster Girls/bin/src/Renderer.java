//This class controls what goes on the screen.
import java.applet.Applet;
import java.awt.List;
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
public class Renderer extends Applet implements KeyListener, ActionListener  {
	private static final long serialVersionUID = -5353540872979294434L;
	private Timer timer;
	private gameController gameKernel;
	Vector3d lookingAngle=null;
	Vector3f menu3D1Vec = new Vector3f(0.08f,0.04f,-1f);
	PlatformGeometry menuPG = new PlatformGeometry();
	TransformGroup menuTG1 = new TransformGroup();
	TransformGroup menuTG2 = new TransformGroup();
	TransformGroup menuTG3 = new TransformGroup();
	TransformGroup menuTG4 = new TransformGroup();
	TransformGroup menuTG5 = new TransformGroup();
	TransformGroup menuTG6 = new TransformGroup();
	TransformGroup menuTG7 = new TransformGroup();
	TransformGroup menuTG8 = new TransformGroup();
	TransformGroup menuTG9 = new TransformGroup();
	Transform3D menu3D1 = new Transform3D();
	Transform3D menu3D2 = new Transform3D();
	Transform3D menu3D3 = new Transform3D();
	Transform3D menu3D4 = new Transform3D();
	Transform3D menu3D5 = new Transform3D();
	Transform3D menu3D6 = new Transform3D();
	Transform3D menu3D7 = new Transform3D();
	Transform3D menu3D8 = new Transform3D();
	Transform3D menu3D9 = new Transform3D();
	Color3f menuWhite = new Color3f(1f,1f,1f);
	Text2D move;
	Text2D attack;
	Text2D spell;
	Text2D endTurn;
	Text2D nameLabel;
	Text2D playerLabel;
	Text2D levelLabel;
	Text2D healthLabel;
	Text2D magicLabel;
	TransformGroup bottomLabelTG = new TransformGroup();
	Transform3D bottomLabelT3D = new Transform3D();
	Text2D bottomLabel = new Text2D("You are now in freelook mode. Press the arrow keys to navigate, 5 to escape.",menuWhite,"Arial",10,1);
	//private float xValue;
	Transform3D lookAt = null;
	TransformGroup viewGroup = null;
	ArrayList<gameCube> activeSpaces = new ArrayList<gameCube>();
	ArrayList<TransformGroup> activeRendererSpaces = new ArrayList<TransformGroup>();
	ArrayList<TransformGroup> activeEntitiesRendered = new ArrayList<TransformGroup>();
	ArrayList<Box> activeBoxObjects = new ArrayList<Box>();
	ArrayList<Sphere> activeSphereObjects = new ArrayList<Sphere>();
	ArrayList<Vector3d> activeTransformVectors = new ArrayList<Vector3d>();
	Canvas3D currentCanvas3D = null;
	Label currentLabel = new Label();
	Point3d Point1 = new Point3d(0,0,0);
	Point3d Point2 = new Point3d(1,0,0);
	Point3d observedPoint1 = new Point3d(0,0,0);
	Point3d observedPoint2 = new Point3d(1,0,0);
	gameCube observedSpace = null;
	String messageOfTheInstance = "In Development!! Please wait warmly while I get everything set up! :)";
	ArrayList<String> messageOfTheInstanceA = new ArrayList<String>();
	BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	Background back = new Background();
	int standPhase = 0;
	Thread drawLoop = null;
	public BranchGroup createSceneGraph() {
		   // Create the root of the branch graph
		   BranchGroup objRoot = new BranchGroup();
		   // Create a simple shape leaf node, add it to the scene graph.
		   if (gameKernel.getCurrentScreen()==2){
		   Sphere sphere = new Sphere(0.25f);
		   Transform3D pos1 = new Transform3D();
		   pos1.setTranslation(new Vector3f(0.0f,0.0f,0.0f));
		   objRoot.addChild(sphere);
		   }
		   else if (gameKernel.getCurrentScreen()==1){
			   Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
			   Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
			   Color3f blue = new Color3f(0.0f, .0f, 1.0f);
			   for (int i=0;i<gameKernel.getCurrentMap().getMapLength();i++){
				   for (int j=0;j<gameKernel.getCurrentMap().getMapWidth();j++){
					   for (int k=0;k<gameKernel.getCurrentMap().getMapHeight();k++){
						   Appearance standardGridBox = new Appearance();
						   Material mat = new Material(blue,black,blue,white,30f);
						   mat.setCapability(Material.ALLOW_COMPONENT_READ);
						   mat.setCapability(Material.ALLOW_COMPONENT_WRITE);
						   standardGridBox.setMaterial(mat);
						   standardGridBox.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ);
						   standardGridBox.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
						   TransformGroup boxMove = new TransformGroup();
						   Transform3D arrowOfMoving = new Transform3D();
						   Box box = new Box(0.1f,0.1f,0.1f,standardGridBox);
						   box.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
						   Vector3d gridLocation = new Vector3d(i*0.2125,j*0.2125,k*0.2125);
						   boxMove.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
						   boxMove.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						   boxMove.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
						   arrowOfMoving.setTranslation(gridLocation);
						   boxMove.setTransform(arrowOfMoving);
						   boxMove.addChild(box);
						   //Associate transform group with its game object equivalents.
						   activeRendererSpaces.add(boxMove);
						   activeSpaces.add(gameKernel.getCurrentMap().getCubeByCoordinates(i, j, k));
						   //gameCube troubleshoot = gameKernel.getCurrentMap().getCubeByCoordinates(i, j, k);
						   //System.out.println(troubleshoot);
						   //System.out.println(boxMove);
						   //You know what? I think these so-called "spheres of help"
						   //actually look pretty cool, so I'll throw 'em in there.
						   TransformGroup sphereOfHelpMove = new TransformGroup();
						   Transform3D arrowOfMovingSphere = new Transform3D();
						   Sphere sphereOfHelp = new Sphere(0.125f,standardGridBox);
						   sphereOfHelp.setCapability(Sphere.ENABLE_APPEARANCE_MODIFY);
						   arrowOfMovingSphere.setTranslation(gridLocation);
						   sphereOfHelpMove.setTransform(arrowOfMovingSphere);
						   sphereOfHelpMove.addChild(sphereOfHelp);
						   objRoot.addChild(sphereOfHelpMove);
						   gameKernel.getCurrentMap().getCubeByCoordinates(i, j, k).setTransformGroup(boxMove,arrowOfMoving,gridLocation, box, sphereOfHelp);
						   objRoot.addChild(boxMove);
						   Color3f lightColor = new Color3f(1.0f,1.0f,1.0f);
						   Vector3f lightDirection = new Vector3f(0.0f,0.0f,-1.0f);
						   DirectionalLight newLight = new DirectionalLight(lightColor,lightDirection);
						   newLight.setInfluencingBounds(bounds);
						   Transform3D lightTransform = new Transform3D();
						   Vector3d lightTransformVector = new Vector3d(i*0.2125,j*0.2125,k*0.2125+1);
						   TransformGroup lightTG = new TransformGroup();
						   lightTransform.setTranslation(lightTransformVector);
						   lightTG.setTransform(lightTransform);
						   lightTG.addChild(newLight);
						   objRoot.addChild(lightTG);
						   activeBoxObjects.add(box);
						   activeSphereObjects.add(sphereOfHelp);
						   activeTransformVectors.add(gridLocation);
					   }
				   }
			   }
		   }
		   Color3f light1Color = new Color3f(1.0f, 0.0f, 0.2f);
		   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		   DirectionalLight light1
		      = new DirectionalLight(light1Color, light1Direction);
		   light1.setInfluencingBounds(bounds);
		   //objRoot.addChild(light1);
		   // Set up the ambient light
		   Color3f ambientColor = new Color3f(0.2f, 0.2f, 0.2f);
		   javax.media.j3d.AmbientLight ambientLightNode = new javax.media.j3d.AmbientLight(ambientColor);
		   ambientLightNode.setInfluencingBounds(bounds);
		   objRoot.addChild(ambientLightNode);
		   back.setApplicationBounds(bounds);
		   back.setColor(0.0f,0.0f,0.5f);
		   back.setCapability(Background.ALLOW_COLOR_READ);
		   back.setCapability(Background.ALLOW_COLOR_WRITE);
		   back.setCapability(Background.ALLOW_LOCAL_TO_VWORLD_READ);
		   objRoot.addChild(back);
		   for (int i = 0; i<gameKernel.getCurrentMap().getEntitiesOnMap().size();i++){
			   Entity pickedUnit = gameKernel.getCurrentMap().getEntitiesOnMap().get(i);
			   TransformGroup spriteTG = new TransformGroup();
			   Transform3D arrowOfMoving = new Transform3D();
			   java.awt.image.BufferedImage whatIsThis = new java.awt.image.BufferedImage(256,128,java.awt.image.BufferedImage.TYPE_INT_ARGB);
			   whatIsThis = null;
			   try {
				whatIsThis = ImageIO.read(new File("C:/Users/yoshi_000/Pictures/Vampire Girl (incomplete) (test).png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   //whatIsThis = "C:/Users/yoshi/Google Drive/Monsters VS Monster Girls/Pictures/Sprites/Vampire Girl (incomplete).png";
			   ImageComponent2D testSprite = new ImageComponent2D(ImageComponent2D.FORMAT_RGBA, whatIsThis);
			   testSprite.set(whatIsThis);
			   testSprite.setCapability(ImageComponent2D.ALLOW_IMAGE_READ);
			   testSprite.setCapability(ImageComponent2D.ALLOW_IMAGE_WRITE);
			   testSprite.setCapability(ImageComponent2D.ALLOW_SIZE_READ);
			   /*The spritePoint is the location where the sprite is constructed
			    * from the top-left of the sprite. It should be a fixed value for
			    * a sprite and auto-scale based on the size of the sprite's
			    * drawn section.*/
			   Point3f spritePoint = new Point3f((float)pickedUnit.getCurrentCube().getAssociatedTransformVector().x-0.05f,(float)pickedUnit.getCurrentCube().getAssociatedTransformVector().y-0.05f,(float)pickedUnit.getCurrentCube().getAssociatedTransformVector().z+0.075f);
			   Raster sprite = new Raster(spritePoint,Raster.RASTER_COLOR,0,0,256,128,testSprite,null);
			   //I CHANGED MY MIND LOL; ignore the instantiated objects above
			   sprite = null;
			   sprite = pickedUnit.getSpriteHandler().getNativeRaster();
			   sprite.setPosition(spritePoint);
			   pickedUnit.setSpritePoint(spritePoint);
			   pickedUnit.getSpriteHandler().exportSpriteSectionToRaster(3, 0);
			   /*sprite.setCapability(Raster.ALLOW_IMAGE_READ);
			   sprite.setCapability(Raster.ALLOW_IMAGE_WRITE);
			   sprite.setCapability(Raster.ALLOW_SIZE_READ);*/
			   //TextureLoader loader = new TextureLoader("C:/Users/yoshi/Google Drive/Monsters VS Monster Girls/Pictures/Sprites/Vampire Girl (incomplete).png","LUMINANCE",new Container());
			   Appearance appear = new Appearance();
			   RenderingAttributes giveItAlpha = new RenderingAttributes(true,true,1.0f,RenderingAttributes.EQUAL);
			   giveItAlpha.setCapability(RenderingAttributes.ALLOW_ALPHA_TEST_FUNCTION_READ);
			   giveItAlpha.setCapability(RenderingAttributes.ALLOW_ALPHA_TEST_FUNCTION_WRITE);
			   appear.setRenderingAttributes(giveItAlpha);
			   /*The rotation point is related to the sprite point. A sprite
			    * should always rotate about its center point.*/
			   Point3f rotationPoint = new Point3f(spritePoint.getX(), spritePoint.getY(),spritePoint.getZ());
			   OrientedShape3D spriteShape = new OrientedShape3D(sprite,appear, OrientedShape3D.ROTATE_ABOUT_POINT, rotationPoint, false, 0);
			   spriteShape.setCapability(OrientedShape3D.ALLOW_POINT_READ);
			   spriteShape.setCapability(OrientedShape3D.ALLOW_POINT_WRITE);
			   pickedUnit.getSpriteHandler().attachOrientedShape3D(spriteShape);
			   Vector3d gridLocation = new Vector3d(pickedUnit.getCurrentCube().getAssociatedTransformVector().x,pickedUnit.getCurrentCube().getAssociatedTransformVector().y,pickedUnit.getCurrentCube().getAssociatedTransformVector().z+0.5);
			   //arrowOfMoving.setTranslation(gridLocation);
			   //spriteTG.setTransform(arrowOfMoving);
			   //spriteTG.addChild(spriteShape);
			   pickedUnit.setTransformGroup(spriteTG, arrowOfMoving, gridLocation);
			   //objRoot.addChild(spriteTG);
			   objRoot.addChild(spriteShape);
		   }
		   return objRoot;
		}
	public Renderer(gameController kernel){
		this.gameKernel=kernel;
		setLayout(new BorderLayout());
		   GraphicsConfiguration config =
		      SimpleUniverse.getPreferredConfiguration();
		   Canvas3D c = new Canvas3D(config);
		   if (gameKernel.getCurrentScreen()!=0){
			   add("Center", c);
		   } else if (gameKernel.getCurrentScreen()==0){
			   double RNG = Math.random();
			   //System.out.println(RNG);
			   messageOfTheInstanceA.add("And God said: 'No, thou shalt not inherit this game yet!' Seems He doesn't care.");
			   messageOfTheInstanceA.add("And then there were none...OR WERE THERE!? PLOT TWIST!");
			   messageOfTheInstanceA.add("Vampire Girl: 'I'm half-dead, half-demon, and altogether half-pissed that I can't get a suntan :/ .'");
			   messageOfTheInstanceA.add("Cyclops Girl: 'I c u *) '");
			   messageOfTheInstanceA.add("Slime Girl: 'Huh? I am what I eat?...'");
			   messageOfTheInstanceA.add("Centaur Girl: 'Alright, time to quit horsing around.'");
			   messageOfTheInstanceA.add("Harpy Girl:'You'll get to be my wingman! How about that? 8D '");
			   messageOfTheInstanceA.add("Octopus Girl:'Ahh, I had an accident...you'll have to wait around til I get this mess sorted out ^^;'");
			   messageOfTheInstanceA.add("Hydra Girl:'I'm like the Neapolitan ice cream mix of monster girls: my cold breath is like the soothing vanilla, my lightning breath is like tangy strawberries, and my fire breath is like warm chocolate! :D '");
			   messageOfTheInstanceA.add("Scorpion Girl:'I am impressed you went this far to compile the game just to read my message! Congrats! What secrets may you find? :O '");
			   messageOfTheInstanceA.add("Zombie Girl:'Unnnhh...what is this place...?'");
			   messageOfTheInstanceA.add("Mushroom Girl:'Symptoms of infection include: nausea, heartburn, diarrhea, mania, dry mouth, leet programming skillz, and a newfound infatuation for monster girls! :D'");
			   messageOfTheInstanceA.add("Luciel: ...***.-***.-..***..-***-***.-***-***..***---***-.***... (She seems happy to see you!)");
			   messageOfTheInstanceA.add("Minotaur Girl:'Alright, girls, let me at 'em!'");
			   messageOfTheInstanceA.add("Gargoyle Girl:'You can't take me for granite. Not today!'");
			   messageOfTheInstanceA.add("In Development!! Please wait warmly while I get everything set up! :)");
			   int RNGi = (int)(RNG*messageOfTheInstanceA.size()-1)+1;
			   //System.out.println(RNGi);
			   messageOfTheInstance=messageOfTheInstanceA.get(RNGi);
			   currentLabel.setText(messageOfTheInstance);
			   add("Center",currentLabel);
		   }
		   c.addKeyListener(this);
		   // Create a simple scene and attach it to the virtual universe
		   BranchGroup scene = null;
		   if (gameKernel.getCurrentScreen()==1){
		   scene = createSceneGraph();
		   }
		   //BranchGroup viewScene = new BranchGroup();
		   SimpleUniverse u = new SimpleUniverse(c);
		   //Locale locale = new Locale(u);
		   //u.getViewingPlatform().setNominalViewingTransform();
		   //lookAt.lookAt(Point1, Point2, lookingAngle);
		   //lookAt.setTranslation(lookingAngle);
		   //ViewPlatform camera = new ViewPlatform();
		   TransformGroup viewingGroup = u.getViewingPlatform().getViewPlatformTransform();
		   this.viewGroup=viewingGroup;
		   //timer = new Timer(200,this);
		   //timer.start();
		   //viewingGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		   //viewingGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		   //viewingGroup.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		   //viewingGroup.addChild(camera);
		   //viewingGroup.setTransform(lookAt);
		   //viewScene.addChild(viewingGroup);
		   //View view = c.getView();
		   //view.setBackClipDistance(2000.0);
		   //view.setPhysicalBody(new PhysicalBody());
		   //view.setPhysicalEnvironment(new PhysicalEnvironment());
		   //view.addCanvas3D(c);
		   //view.attachViewPlatform(camera);
		   c.setBackground(new Color(0,0,255));
		   c.setForeground(new Color(0,0,255));
		   currentCanvas3D=c;
		   if (gameKernel.getCurrentScreen()==1){
		   u.addBranchGraph(scene);
		   }
		   u.getViewingPlatform().setNominalViewingTransform();
		   DrawLoop drawingEngine = new DrawLoop();
		   drawingEngine.setGameControllerAndRenderer(gameKernel,this);
		   //Begin testing stupid java 2d drawing
		   //Actually, I meant to rename these clips to "anchors" since that's
		   //what they are, but never got around to it.
		   float rightClip = 0.12f;
		   float topClip = 0.04f;
		   float leftClipX = -0.40f;
		   float leftClipY = 0.26f;
		   menu3D1.setTranslation(new Vector3f(rightClip,topClip,-1f));
		   menu3D2.setTranslation(new Vector3f(rightClip,topClip-0.04f,-1f));
		   menu3D3.setTranslation(new Vector3f(rightClip,topClip-0.08f,-1f));
		   menu3D4.setTranslation(new Vector3f(rightClip,topClip-0.12f,-1f));
		   menu3D5.setTranslation(new Vector3f(leftClipX,leftClipY,-1f));
		   menu3D6.setTranslation(new Vector3f(leftClipX,leftClipY-0.04f,-1f));
		   menu3D7.setTranslation(new Vector3f(leftClipX,leftClipY-0.08f,-1f));
		   menu3D8.setTranslation(new Vector3f(leftClipX,leftClipY-0.12f,-1f));
		   menu3D9.setTranslation(new Vector3f(leftClipX,leftClipY-0.16f,-1f));
		   menuTG1.setTransform(menu3D1);
		   menuTG2.setTransform(menu3D2);
		   menuTG3.setTransform(menu3D3);
		   menuTG4.setTransform(menu3D4);
		   menuTG5.setTransform(menu3D5);
		   menuTG6.setTransform(menu3D6);
		   menuTG7.setTransform(menu3D7);
		   menuTG8.setTransform(menu3D8);
		   menuTG9.setTransform(menu3D9);
		   move = new Text2D("Move", menuWhite,"Arial",10,1);
		   menuTG1.addChild(move);
		   attack = new Text2D("Attack", menuWhite,"Arial",10,1);
		   menuTG2.addChild(attack);
		   spell = new Text2D("Spell", menuWhite,"Arial",10,1);
		   menuTG3.addChild(spell);
		   endTurn = new Text2D("End Turn", menuWhite,"Arial",10,1);
		   menuTG4.addChild(endTurn);
		   nameLabel = new Text2D("                  ",menuWhite,"Arial",10,1);
		   menuTG5.addChild(nameLabel);
		   playerLabel = new Text2D("                  ",menuWhite,"Arial",10,1);
		   menuTG6.addChild(playerLabel);
		   levelLabel = new Text2D("                  ",menuWhite,"Arial",10,1);
		   menuTG7.addChild(levelLabel);
		   healthLabel = new Text2D("                  ",menuWhite,"Arial",10,1);
		   menuTG8.addChild(healthLabel);
		   magicLabel = new Text2D("                  ",menuWhite,"Arial",10,1);
		   menuTG9.addChild(magicLabel);
		   nameLabel.getAppearance().setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		   playerLabel.getAppearance().setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		   levelLabel.getAppearance().setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		   healthLabel.getAppearance().setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		   magicLabel.getAppearance().setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		   move.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   attack.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   spell.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   endTurn.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   nameLabel.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   playerLabel.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   levelLabel.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   healthLabel.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   magicLabel.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   move.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   attack.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   spell.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   endTurn.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   nameLabel.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   playerLabel.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   levelLabel.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   healthLabel.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   magicLabel.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   menuPG.addChild(menuTG1);
		   menuPG.addChild(menuTG2);
		   menuPG.addChild(menuTG3);
		   menuPG.addChild(menuTG4);
		   menuPG.addChild(menuTG5);
		   menuPG.addChild(menuTG6);
		   menuPG.addChild(menuTG7);
		   menuPG.addChild(menuTG8);
		   menuPG.addChild(menuTG9);
		   if (gameKernel.isBattleActive==false){
			   move.getAppearance().getRenderingAttributes().setVisible(false);
			   attack.getAppearance().getRenderingAttributes().setVisible(false);
			   spell.getAppearance().getRenderingAttributes().setVisible(false);
			   endTurn.getAppearance().getRenderingAttributes().setVisible(false);
			   nameLabel.getAppearance().getRenderingAttributes().setVisible(false);
			   playerLabel.getAppearance().getRenderingAttributes().setVisible(false);
			   levelLabel.getAppearance().getRenderingAttributes().setVisible(false);
			   healthLabel.getAppearance().getRenderingAttributes().setVisible(false);
			   magicLabel.getAppearance().getRenderingAttributes().setVisible(false);
		   }
		   //Alright, what's the other text we may need...
		   //The value for the Rectangle Scale Factor was originally 0.00390625.
		   bottomLabel.setRectangleScaleFactor(0.00190625f);
		   bottomLabel.getAppearance().setRenderingAttributes(new RenderingAttributes());
		   bottomLabel.getAppearance().getRenderingAttributes().setCapability(RenderingAttributes.ALLOW_VISIBLE_WRITE);
		   bottomLabelT3D.setTranslation(new Vector3d(leftClipX,-0.24f,-1f));
		   bottomLabelTG.setTransform(bottomLabelT3D);
		   bottomLabelTG.addChild(bottomLabel);
		   menuPG.addChild(bottomLabelTG);
		   u.getViewingPlatform().setPlatformGeometry(menuPG);
		   /*Graphics2D canvasGraphic = c.getGraphics2D();
		   javax.media.j3d.J3DGraphics2D newGraphic;
		   newGraphic = (javax.media.j3d.J3DGraphics2D)canvasGraphic;
		   newGraphic.setPaint(Color.WHITE);
		   newGraphic.draw3DRect(50, 100, 200, 200, true);
		   java.awt.image.BufferedImage BI = null;
			try{
				BI = ImageIO.read(new File("C:/Users/yoshi_000/Vampire Girl.png"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		   newGraphic.drawImage(BI, null, 400, 400);
		   newGraphic.drawAndFlushImage(BI, 0, 50, null);
		   Rectangle hi = new Rectangle(0,0,1000,1000);
		   newGraphic.fill(hi);
		   newGraphic.draw(hi);
		   newGraphic.flush(true);
		   c.getGraphics2D().draw(hi);
		   c.getGraphics2D().flush(true);
		   canvasGraphic.fill(hi);
		   canvasGraphic.draw(hi);*/
		   System.out.println(c.getDoubleBufferEnable());
		   if (gameKernel.getCurrentScreen()==1){
		   drawLoop = new Thread(drawingEngine);
		   drawLoop.start();
		   }
			if (this.lookAt==null){
				this.lookAt = new Transform3D();
				this.lookingAngle = new Vector3d();
				viewGroup.getTransform(lookAt);
	            lookAt.get(lookingAngle);
			}
			if (gameKernel.getCurrentScreen()==1){
				observedSpace=gameKernel.getCurrentMap().getCubeByCoordinates(0, 0, 0);
				/*I still do not fully understand the way the 'lookAt' transform method
				works, so what I know now is based completely on trial and error
				trying to get it to work the way I want it to.
				Both the point that the camera is based at and the point that the
				camera is looking at should move at the same time, and the distance
				between them should be fixed. That said, ideally, there should be a
				way to use a function that will move both the camera's point and the
				observed point at the same time, with the camera's point being
				dependent upon the observed point.*/
				//Update: Figured it out. No thanks to you, stinkyGoblin!
				Point2.x=observedSpace.getAssociatedTransformVector().x;
				Point2.y=observedSpace.getAssociatedTransformVector().y;
				Point2.z=observedSpace.getAssociatedTransformVector().z;
				Point1.x=-2+Point2.x;
				Point1.y=-2+Point2.y;
				Point1.z=2+Point2.z;
				observedPoint2.x=observedSpace.getAssociatedTransformVector().x;
				observedPoint2.y=observedSpace.getAssociatedTransformVector().y;
				observedPoint2.z=observedSpace.getAssociatedTransformVector().z;
				observedPoint1.x=-2+observedPoint2.x;
				observedPoint1.y=-2+observedPoint2.y;
				observedPoint1.z=2+observedPoint2.z;
				lookAt.lookAt(observedPoint1, observedPoint2, lookingAngle);
				lookAt.invert();
				viewGroup.setTransform(lookAt);
			}
		   //u.addBranchGraph(viewScene);
	}
	public void setGameController(gameController kernel){
		this.gameKernel=kernel;
	}
	
	public void keyPressed(KeyEvent e) {
		   //Invoked when a key has been pressed.
		//The menu options will wrap around to the last or first options, respectively,
		//upon exceeding the menu list size.
		if (e.getKeyCode()==KeyEvent.VK_DOWN){
			if (gameKernel.isFreelookActive==false&&gameKernel.currentMenu!=null){
				gameKernel.currentMenuIndex++;
				if (gameKernel.currentMenuIndex>gameKernel.currentMenu.size()-1){
					gameKernel.currentMenuIndex=0;
				}
			}
			if (gameKernel.getCurrentMap().getCubeSouth(observedSpace)==null){
				
			}
			else{
				if (gameKernel.isFreelookActive==true){
					observedSpace=gameKernel.getCurrentMap().getCubeSouth(observedSpace);
				}
				}
		}
		if (e.getKeyCode()==KeyEvent.VK_UP){
			if (gameKernel.isFreelookActive==false&&gameKernel.currentMenu!=null){
				gameKernel.currentMenuIndex--;
				if (gameKernel.currentMenuIndex<0){
					gameKernel.currentMenuIndex=gameKernel.currentMenu.size()-1;
				}
		}
			if (gameKernel.getCurrentMap().getCubeNorth(observedSpace)==null){
				
			}
			else{
				if (gameKernel.isFreelookActive==true){
					observedSpace=gameKernel.getCurrentMap().getCubeNorth(observedSpace);
				}
				}
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT){
			if (gameKernel.getCurrentMap().getCubeWest(observedSpace)==null){
				
			}
			else{
				if (gameKernel.isFreelookActive==true){
					observedSpace=gameKernel.getCurrentMap().getCubeWest(observedSpace);
				}
				}
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT){
			if (gameKernel.getCurrentMap().getCubeEast(observedSpace)==null){
				
			}
			else{
				if (gameKernel.isFreelookActive==true){
					observedSpace=gameKernel.getCurrentMap().getCubeEast(observedSpace);
				}
			}
		}
		}
	
		public void keyReleased(KeyEvent e){
		   // Invoked when a key has been released.
		}
		public void keyTyped(KeyEvent e){
		   //Invoked when a key has been typed.
			   /*if (e.getKeyChar()=='s') {xloc = xloc + .1f;}
			   if (e.getKeyChar()=='a') {xloc = xloc - .1f;}*/
			if (this.lookAt==null){
				this.lookAt = new Transform3D();
				this.lookingAngle = new Vector3d();
				viewGroup.getTransform(lookAt);
	            lookAt.get(lookingAngle);
			}
			if (e.getKeyChar()=='d'){
				//lookingAngle.x+=0.05;
				//Point1.x+=0.05;
				   /*if (!timer.isRunning()){
						timer.start();
				    }
				   else {
					   //System.out.println(gameKernel.getCurrentScreen());
				   }*/
				//Hydra Girl: "East? I thought you said Weest..."
				if (gameKernel.getCurrentMap().getCubeEast(observedSpace)==null){
					
				}
				else{
					if (gameKernel.isFreelookActive==true){
						observedSpace=gameKernel.getCurrentMap().getCubeEast(observedSpace);
					}
				}
			}
			if (e.getKeyChar()=='a'){
				//lookingAngle.x-=0.05;
				//Point1.x-=0.05;
				if (gameKernel.getCurrentMap().getCubeWest(observedSpace)==null){
					
				}
				else{
					if (gameKernel.isFreelookActive==true){
						observedSpace=gameKernel.getCurrentMap().getCubeWest(observedSpace);
					}
					}
				
			}
			if (e.getKeyChar()=='s'){
				//lookingAngle.y-=0.05;
				//Point1.y-=0.05;
				if (gameKernel.getCurrentMap().getCubeSouth(observedSpace)==null){
					
				}
				else{
					if (gameKernel.isFreelookActive==true){
						observedSpace=gameKernel.getCurrentMap().getCubeSouth(observedSpace);
					}
					}
			}
			if (e.getKeyChar()=='w'){
				//lookingAngle.y+=0.05;
				//Point1.y+=0.05;
				if (gameKernel.getCurrentMap().getCubeNorth(observedSpace)==null){
					
				}
				else{
					if (gameKernel.isFreelookActive==true){
						observedSpace=gameKernel.getCurrentMap().getCubeNorth(observedSpace);
					}
					}
			}
			if (e.getKeyChar()=='q'){
				//if (gameKernel.getCurrentMap().getCubeNorth(gameKernel.getCurrentMap().getEntitiesOnMap().get(0).getCurrentCube())==null){
					
				//}
				//else{
					//gameKernel.getCurrentMap().placeEntityAtCube(gameKernel.getCurrentMap().getEntitiesOnMap().get(0), gameKernel.getCurrentMap().getCubeNorth(gameKernel.getCurrentMap().getEntitiesOnMap().get(0).getCurrentCube()));
				//}
				//lookingAngle.z+=0.05;
				//Point1.z+=0.05;
			}
			if (e.getKeyChar()=='e'){
				//lookingAngle.z-=0.05;
				//Point1.z-=0.05;
			}
			if (e.getKeyChar()=='h'){
				//lookingAngle.x+=0.05;
				//Point2.x+=0.05;
				   /*if (!timer.isRunning()){
						timer.start();
				    }
				   else {
					   //System.out.println(gameKernel.getCurrentScreen());
				   }*/
				//menu3D1Vec.x+=0.05;
				//menu3D1.set(menu3D1Vec);
				//menuTG1.setTransform(menu3D1);
			}
			if (e.getKeyChar()=='f'){
				//lookingAngle.x-=0.05;
				//Point2.x-=0.05;
				//menu3D1Vec.x-=0.05;
			}
			if (e.getKeyChar()=='g'){
				//lookingAngle.y-=0.05;
				//Point2.y-=0.05;
				//menu3D1Vec.y-=0.05;
			}
			if (e.getKeyChar()=='t'){
				//lookingAngle.y+=0.05;
				//Point2.y+=0.05;
				//menu3D1Vec.y+=0.05;
			}
			if (e.getKeyChar()=='r'){
				//lookingAngle.z+=0.05;
				//Point2.z+=0.05;
				//menu3D1Vec.z-=0.05;
			}
			if (e.getKeyChar()=='y'){
				//lookingAngle.z-=0.05;
				//Point2.z-=0.05;
				//menu3D1Vec.z+=0.05;
			}
			if (e.getKeyChar()=='l'){
				//lookingAngle.x+=0.05;
				//lookingAngle.x+=0.05;
				   /*if (!timer.isRunning()){
						timer.start();
				    }
				   else {
					   //System.out.println(gameKernel.getCurrentScreen());
				   }*/
			}
			if (e.getKeyChar()=='j'){
				//lookingAngle.x-=0.05;
				//lookingAngle.x-=0.05;
			}
			if (e.getKeyChar()=='k'){
				//lookingAngle.y-=0.05;
				//lookingAngle.y-=0.05;
			}
			if (e.getKeyChar()=='i'){
				//lookingAngle.y+=0.05;
				//lookingAngle.y+=0.05;
			}
			if (e.getKeyChar()=='u'){
				//lookingAngle.z+=0.05;
				//lookingAngle.z+=0.05;
			}
			if (e.getKeyChar()=='o'){
				//lookingAngle.z-=0.05;
				//lookingAngle.z-=0.05;
			}
			//In most cases, the Z key confirms the selected option.
			if (e.getKeyChar()=='z'){ 
				if (gameKernel.isFreelookActive==false&&gameKernel.currentMenu!=null){
					//These will each do something eventually.
				System.out.println(gameKernel.currentMenuIndex);
					//Move
					if (gameKernel.currentMenuIndex==0){
						
					}
					//Attack
					else if (gameKernel.currentMenuIndex==1){
						
					}
					//Spell
					else if (gameKernel.currentMenuIndex==2){
						
					}
					//End Turn
					else if (gameKernel.currentMenuIndex==3){
						if (gameKernel.isBattleActive()==true){
							gameKernel.getCurrentBattle().nextTurn();
						}
					}
			}
				}
			//In most cases, the X key cancels to the previous menu, if any.
			if(e.getKeyChar()=='x'){ 
				
			}
			if (e.getKeyChar()=='4'){
				if (gameKernel.isBattleActive()==true&&gameKernel.isFreelookActive==false){
					gameKernel.getCurrentBattle().nextTurn();
				}
			}
			if (e.getKeyChar()=='5'){
				if (gameKernel.isFreelookActive==true){
					gameKernel.isFreelookActive=false;
				}
				else{
					gameKernel.isFreelookActive=true;
				}
			}
			//lookAt.set(lookingAngle);
			//Time to figure out what Point1's gonna be.
			Point2.x=observedSpace.getAssociatedTransformVector().x;
			Point2.y=observedSpace.getAssociatedTransformVector().y;
			Point2.z=observedSpace.getAssociatedTransformVector().z;
			Point1.x=-2+Point2.x;
			Point1.y=-2+Point2.y;
			Point1.z=2+Point2.z;
			//lookAt.lookAt(observedPoint1, observedPoint2, lookingAngle);
			//lookAt.invert();
			//viewGroup.setTransform(lookAt);
			//System.out.println("Point1: "+Point1.x+","+Point1.y+","+Point1.z+" -- Point2"+Point2.x+","+Point2.y+","+Point2.z);
			//Transform3D exampleTransform = null;
			//viewGroup.getTransform(exampleTransform);
		}
		public void actionPerformed(ActionEvent e ) {
			   // start timer when button is pressed
			   /*if (e.getSource()==go){
			      if (!timer.isRunning()) {
			         timer.start();
			      }
			   }
			   else {
			       //height += .1 * sign;
			      //if (Math.abs(height *2) >= 1 ) sign = -1.0f * sign;
			      //if (height<-0.4f) {
			      //trans.setScale(new Vector3d(1.0, .8, 1.0));
			   }
			   else {
			      //trans.setScale(new Vector3d(1.0, 1.0, 1.0));
			   }
			   //trans.setTranslation(new Vector3f(xloc,height,0.0f));
			   //objTrans.setTransform(trans);
			   }*/
			}
}
