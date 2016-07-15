import java.awt.Color;
import java.util.ArrayList;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Player {

	gameController gameKernel;
	int ID;
	int battleID;
	String name;
	//The playerController variable identifies what type of player controls the player.
	//0 is a local human player, 1 is an AI player, 2 is a remote human player,
	//3 is a replay player, and 4 is a neutral player (special case scenario).
	int playerController;
	Color3f playerColor;
	ArrayList<Entity> controlledUnits = new ArrayList<Entity>();
	public Player(int ID, String name, Color3f color, int playerControllerType, gameController kernel){
		this.ID=ID;
		this.name=name;
		this.playerColor=color;
		playerController=playerControllerType;
		this.gameKernel=kernel;
	}
	int getID(){
		return ID;
	}
	int getBattleID(){
		return battleID;
	}
	String getName(){
		return name;
	}
	int getPlayerControllerType(){
		return playerController;
	}
	Color3f getColor(){
		return playerColor;
	}
	void setBattleID(int newBattleID){
		battleID = newBattleID;
	}
	void setName(String newName){
		name=newName;
	}
	void changePlayerControllerType(int newPlayerControllerType){
		playerController=newPlayerControllerType;
	}
	ArrayList<Entity> getUnitListUnderPlayerControl(){
		return controlledUnits;
	}
	Entity getUnitOwnedAtListIndex(int n){
		return controlledUnits.get(n);
	}
	void addUnitToControlledUnits(Entity unit){
		controlledUnits.add(unit);
		if (unit.getControllingPlayer()==null){
			
		}
		else {
			unit.getControllingPlayer().removeUnitFromControlledList(unit);
		}
		unit.setControllingPlayer(this);
	}
	void removeUnitFromControlledList(Entity unit){
		controlledUnits.remove(unit);
	}
}
