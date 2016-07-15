import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

public class Entity {

	gameController gameKernel;
	gameMap activeMap = null;
	gameCube hostCube = null;
	int ID;
	int battleID;
	Player controllingPlayer;
	String name;
	int level;
	double HP;
	double MP;
	double speed;
	//Facing determines which way the unit is pointing. 0 is north, 1 is east,
	//2 is south, and 3 is west.
	int facing = 0;
	TransformGroup associatedRenderingObject = null;
	Transform3D associatedTransform3D = null;
	Vector3d associatedTransformVector = null;
	SpriteHandler associatedSpriteHandler = null;
	Point3f spritePoint = null;
	public Entity(int ID, String name, Player controllingPlayer, gameController kernel){
		this.ID=ID;
		this.name=name;
		this.controllingPlayer=controllingPlayer;
		this.gameKernel = kernel;
	}
	//Use this constructor if you want to copy an existing entity.
	public Entity(Entity cloneFromEntity){
		this.name=cloneFromEntity.getName();
		this.controllingPlayer=cloneFromEntity.getControllingPlayer();
		this.gameKernel=cloneFromEntity.gameKernel;
		this.facing=cloneFromEntity.facing;
		this.speed=cloneFromEntity.speed;
		this.spritePoint=cloneFromEntity.spritePoint;
	}
	int getID(){
		return ID;
	}
	String getName(){
		return name;
	}
	gameMap getCurrentMap(){
		return activeMap;
	}
	gameCube getCurrentCube(){
		return hostCube;
	}
	Player getControllingPlayer(){
		return controllingPlayer;
	}
	int getBattleID(){
		return battleID;
	}
	void setName(String newName){
		name=newName;
	}
	void setCube(gameCube targetCube){
		hostCube=targetCube;
	}
	void setControllingPlayer(Player targetPlayer){
		controllingPlayer=targetPlayer;
	}
	void setSpeed(double newSpeed){
		this.speed=newSpeed;
	}
	double getSpeed(){
		return this.speed;
	}
	int getLevel(){
		return this.level;
	}
	void setAttributes(int level, double HP, double MP, double speed){
		this.level=level;
		this.HP=HP;
		this.MP=MP;
		this.speed=speed;
	}
	int getFacing(){
		return this.facing;
	}
	void setFacing(int newFacing){
		this.facing=newFacing;
	}
	void setTransformGroup(TransformGroup tg, Transform3D threeD, Vector3d vec){
		this.associatedRenderingObject=tg;
		this.associatedTransform3D=threeD;
		this.associatedTransformVector=vec;
	}
	TransformGroup getAssociatedRenderingObject(){
		return this.associatedRenderingObject;
	}
	Transform3D getAssociatedTransformThreeD(){
		return this.associatedTransform3D;
	}
	Vector3d getAssociatedTransformVector(){
		return this.associatedTransformVector;
	}
	SpriteHandler getSpriteHandler(){
		return this.associatedSpriteHandler;
	}
	void setSpriteHandler(SpriteHandler sh){
		this.associatedSpriteHandler=sh;
	}
	void updateTransform(Renderer renderer){
		if (spritePoint!=null){
		spritePoint.setX((float)this.getCurrentCube().getAssociatedTransformVector().x-0.05f);
		spritePoint.setY((float)this.getCurrentCube().getAssociatedTransformVector().y-0.05f);
		spritePoint.setZ((float)this.getCurrentCube().getAssociatedTransformVector().z+0.075f);
		this.associatedSpriteHandler.getNativeRaster().setPosition(spritePoint);
		this.associatedSpriteHandler.getShape3D().setRotationPoint(spritePoint);
		}
	}
	void setSpritePoint(Point3f point){
		this.spritePoint=point;
	}
}
