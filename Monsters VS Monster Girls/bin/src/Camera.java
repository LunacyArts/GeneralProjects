
public class Camera {

	//It is through the camera that you see the world.
	Point anchorPoint;
	double radius;
	double azimuth;
	double altitude;
	//Upon instantiation of the camera, the camera's pitch, roll, and yaw are assumed to point
	//towards the camera's anchor point.
	//Also, in most cases, those rotational bearings are fixed for the camera to point
	//at its anchor point.
	double pitch;
	double roll;
	double yaw;
}
