import java.awt.List;
import java.util.ArrayList;

public class gameMap {
	gameController gameKernel;
	int length;
	int width;
	int height;
	String name;
	int ID;
	ArrayList<gameCube> gameSpace = new ArrayList<gameCube>();
	ArrayList<Entity> unitsOnMap = new ArrayList<Entity>();
	public gameMap(int length, int width, int height, String name, int ID, gameController kernel){
		this.height = height;
		this.length = length;
		this.width = width;
		this.name = name;
		this.ID = ID;
		this.gameKernel=kernel;
		for (int i = 0;i < length;i++){
			for (int j = 0; j < width;j++){
				for (int k = 0; k < height;k++){
					gameCube newGameCube = new gameCube(i,j,k,this);
					gameSpace.add(newGameCube);
				}
			}
		}
	}
	int getMapHeight(){
		return height;
	}
	int getMapLength(){
		return length;
	}
	int getMapWidth(){
		return width;
	}
	String getMapName(){
		return name;
	}
	int getMapID(){
		return ID;
	}
	void setMapName(String newName){
		name=newName;
	}
	gameCube getCubeNorth(gameCube space){
		gameCube returnedSpace = null;
		for (int n = 0;n<this.gameSpace.size();n++){
			if (gameSpace.get(n).getX()==space.getX()&&gameSpace.get(n).getY()==space.getY()+1&&gameSpace.get(n).getZ()==space.getZ()){
				returnedSpace=gameSpace.get(n);
			}
		}
		return returnedSpace;
	}
	gameCube getCubeSouth(gameCube space){
		gameCube returnedSpace = null;
		for (int n = 0;n<this.gameSpace.size();n++){
			if (gameSpace.get(n).getX()==space.getX()&&gameSpace.get(n).getY()==space.getY()-1&&gameSpace.get(n).getZ()==space.getZ()){
				returnedSpace=gameSpace.get(n);
			}
		}
		return returnedSpace;
	}
	gameCube getCubeEast(gameCube space){
		gameCube returnedSpace = null;
		for (int n = 0;n<this.gameSpace.size();n++){
			if (gameSpace.get(n).getX()==space.getX()+1&&gameSpace.get(n).getY()==space.getY()&&gameSpace.get(n).getZ()==space.getZ()){
				returnedSpace=gameSpace.get(n);
			}
		}
		return returnedSpace;
	}
	gameCube getCubeWest(gameCube space){
		gameCube returnedSpace = null;
		for (int n = 0;n<this.gameSpace.size();n++){
			if (gameSpace.get(n).getX()==space.getX()-1&&gameSpace.get(n).getY()==space.getY()&&gameSpace.get(n).getZ()==space.getZ()){
				returnedSpace=gameSpace.get(n);
			}
		}
		return returnedSpace;
	}
	gameCube getCubeUp(gameCube space){
		gameCube returnedSpace = null;
		for (int n = 0;n<this.gameSpace.size();n++){
			if (gameSpace.get(n).getX()==space.getX()&&gameSpace.get(n).getY()==space.getY()&&gameSpace.get(n).getZ()==space.getZ()+1){
				returnedSpace=gameSpace.get(n);
			}
		}
		return returnedSpace;
	}
	gameCube getCubeDown(gameCube space){
		gameCube returnedSpace = null;
		for (int n = 0;n<this.gameSpace.size();n++){
			if (gameSpace.get(n).getX()==space.getX()&&gameSpace.get(n).getY()==space.getY()&&gameSpace.get(n).getZ()==space.getZ()-1){
				returnedSpace=gameSpace.get(n);
			}
		}
		return returnedSpace;
	}
	ArrayList<gameCube> getCubeList(){
		return this.gameSpace;
	}
	void addEntityToEntityList(Entity entity){
		unitsOnMap.add(entity);
	}
	ArrayList<Entity> getEntitiesOnMap(){
		return unitsOnMap;
	}
	gameCube getCubeByCoordinates(int x, int y, int z){
		gameCube referredCube = null;
		//System.out.println("ASKING FOR SPACE WITH COORDINATES: "+x+","+y+","+z);
		for (int n=0;n<gameSpace.size();n++){
			//gameCube troubleshoot = gameSpace.get(n);
			//System.out.println(n+"--"+troubleshoot+"--"+troubleshoot.getX()+","+troubleshoot.getY()+","+troubleshoot.getZ());
			if (gameSpace.get(n).getX()==x&&gameSpace.get(n).getY()==y&&gameSpace.get(n).getZ()==z){
				referredCube=gameSpace.get(n);
			}
		}
		return referredCube;
	}
	Entity getEntityAtCube(gameCube cube){
		try{
		return cube.getEntity();
		}
		finally{
			System.out.println("Error: There is no entity at that cube.");
			return null;
		}
	}
	void placeEntityAtCube(Entity targetEntity, gameCube targetCube){
		//System.out.println(targetEntity.getName());
		//System.out.println(targetCube.getX()+","+targetCube.getY()+","+targetCube.getZ());
		try{
		if (targetCube.getEntity()==null){
			if (targetEntity.getCurrentCube()==null){
				
			}
			else{
				targetEntity.getCurrentCube().setEntity(null);
			}
		targetCube.setEntity(targetEntity);
		targetEntity.setCube(targetCube);
		}
		else {
			System.out.println("Error: Could not place entity at that cube. There is already an entity at that cube.");
		}
		}
		finally{
			//System.out.println("Error: Could not place entity at that cube. Is there already an entity there?");
		}
		
	}
}
