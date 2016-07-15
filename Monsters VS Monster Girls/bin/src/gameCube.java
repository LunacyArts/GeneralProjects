import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Box;
public class gameCube {

	gameController gameKernel;
	gameMap hostMap;
	int xCoordinate;
	int yCoordinate;
	int zCoordinate;
	Entity guestEntity = null;
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
