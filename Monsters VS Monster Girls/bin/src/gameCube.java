import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Box;
import java.util.ArrayList;
public class gameCube {

	gameController gameKernel;
	gameMap hostMap;
	int xCoordinate;
	int yCoordinate;
	int zCoordinate;
	ArrayList<spaceEffect> activeSpaceEffects = new ArrayList<spaceEffect>();
	Entity guestEntity = null;
	boolean isHighlighted=false;
	boolean isColorChanged=false;
	//The bool below is for the purposes of the game kernel.
	boolean isAnalyzed=false;
	//blue, red, cyan, or green
	String cubeColor="blue";
	int colorRenderAttempts=0;
	TransformGroup associatedRenderingObject = null;
	Transform3D associatedTransform3D = null;
	Vector3d associatedTransformVector = null;
	Box associatedBox = null;
	Sphere associatedSphereOfHelp = null;
	public gameCube(int x, int y, int z, gameMap host){
		xCoordinate = x;
		yCoordinate = y;
		zCoordinate = z;
		this.hostMap = host;
	}
	gameMap getMap(){
		return hostMap;
	}
	int getX(){
		return xCoordinate;
	}
	int getY(){
		return yCoordinate;
	}
	int getZ(){
		return zCoordinate;
	}
	Entity getEntity(){
		return guestEntity;
	}
	void setEntity(Entity targetEntity){
		guestEntity=targetEntity;
	}
	//The below method decrements the duration of all current spaceEffects. If the duration becomes less than 0 this way,
	//it is removed from the gameCube.
	//The below method should be called at the start of the Entity's turn who placed it there. Every time the method
	//is called, it only decrements the duration of the effects from the sourceEntity, which should always be
	//the Entity whose turn it is.
	void updateEffectDurations(Entity sourceEntity){
		for (int i=0;i<this.activeSpaceEffects.size();i++){
			if (activeSpaceEffects.get(i).effectSource==sourceEntity){
				activeSpaceEffects.get(i).duration--;
			}
			if (activeSpaceEffects.get(i).duration<0){
				activeSpaceEffects.remove(i);
			}
		}
	}
	void setTransformGroup(TransformGroup tg, Transform3D threeD, Vector3d vec, Box box, Sphere sphereOfHelp){
		this.associatedRenderingObject=tg;
		this.associatedTransform3D=threeD;
		this.associatedTransformVector=vec;
		this.associatedBox=box;
		this.associatedSphereOfHelp=sphereOfHelp;
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
	Box getAssociatedBox(){
		return this.associatedBox;
	}
	Sphere getAssociatedSphereOfHelp(){
		return this.associatedSphereOfHelp;
	}
}
