import java.util.ArrayList;

public class Battle {

	gameController gameKernel;
	//The battle class handles both maps and the entities within maps, as well as structures
	//such as teams, players, and the initiative order.
	gameMap battleMap;
	ArrayList<Player> playerList = new ArrayList<Player>();
	ArrayList<Entity> unitList = new ArrayList<Entity>();
	ArrayList<Entity> initiativeList = new ArrayList<Entity>();
	int initiativeTurnCount = 0;
	public Battle(gameMap targetMap, ArrayList<Player> playerList, ArrayList<Entity> unitList, gameController kernel){
		battleMap = targetMap;
		this.playerList = playerList;
		this.unitList = unitList;
		this.gameKernel = kernel;
	}
	gameMap getMap(){
		return battleMap;
	}
	ArrayList<Player> getPlayerList(){
		return playerList;
	}
	ArrayList<Entity> getUnitList(){
		return unitList;
	}
	ArrayList<Entity> getInitList(){
		return initiativeList;
	}
	Entity getUnitByBattleID(int ID){
		return unitList.get(ID);
	}
	Entity getUnitAtInitiativePosition(int initiativePosition){
		return initiativeList.get(initiativePosition);
	}
	void setTurnCount(int turnCount){
		this.initiativeTurnCount=turnCount;
	}
	int getTurnCount(){
		return this.initiativeTurnCount;
	}
	void nextTurn(){
		this.initiativeTurnCount++;
		if (this.initiativeTurnCount>=initiativeList.size()){
			this.initiativeTurnCount=0;
		}
	}
	Entity getCurrentUnitTurn(){
		return this.initiativeList.get(this.initiativeTurnCount);
	}
	void startBattleRoutine(){
		
	}
}
