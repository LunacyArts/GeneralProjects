import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
public class gameController extends Applet implements KeyListener {

	//And make sure to give it a name that you can yell at for not doing what it's told,
	//like "stinkyGoblin".
	String name;
	gameMap currentMap;
	Battle currentBattle;
	Renderer currentRenderer;
	//battleOptions, moveTrace, moveMenu, freeLook
	String currentMenuIdent = "battleOptions";
	ArrayList<String> currentMenu = new ArrayList<String>();
	//In currentMenuSuper, the array lists within are "pages" with up to six (default) String objects.
	//The page is identified with the "currentPage" int. Use currentMenuIndex as usual.
	ArrayList<ArrayList<String>> currentMenuSuper = new ArrayList<ArrayList<String>>();
	int currentMenuIndex = 0;
	int currentPage = 0;
	int actualID = 1;
	int gameScreen = 0;
	//Record the path of the cubes a unit would travel and the associated
	//directions of movement for each.
	ArrayList<gameCube> movementPath = new ArrayList<gameCube>();
	ArrayList<Integer> movementPathDirection = new ArrayList<Integer>();
	boolean disableFreelook=true;
	boolean isBattleActive=false;
	boolean isFreelookActive=false;
	boolean isMovingActive=false;
	public gameController(String name){
		this.name=name;
	}
	void setCurrentMap(gameMap map){
		this.currentMap=map;
	}
	void setCurrentBattle(Battle battle){
		this.currentBattle=battle;
	}
	void setScreen(int screenID){
		this.gameScreen=screenID;
	}
	void setRenderer(Renderer rend){
		this.currentRenderer=rend;
	}
	gameMap getCurrentMap(){
		return currentMap;
	}
	int getCurrentScreen(){
		return gameScreen;
	}
	Renderer getRenderer(){
		return this.currentRenderer;
	}
	void initiateBattle(gameMap targetMap, ArrayList<Player> playerList, ArrayList<Entity> unitList){
		currentBattle = new Battle(targetMap, playerList, unitList,this);
		for(double n = 99;n>=0;n--){
			for(int i = 0;i<currentBattle.getUnitList().size();i++){
				if (currentBattle.getUnitList().get(i).getSpeed()==n){
					currentBattle.getInitList().add(currentBattle.getUnitList().get(i));
					currentBattle.getUnitList().get(i).consolidateTraitsAndAbilities();
					currentBattle.getUnitList().get(i).calculateMaxGrowths();
					currentBattle.getUnitList().get(i).calculateAttributes();
					currentBattle.getUnitList().get(i).calculateGrowthRates();
				}
			}
		}
	}
	//Plays the current battle, which must be initiated first.
	void playBattle(){
		currentMap=currentBattle.getMap();
		gameScreen=1;
		this.isBattleActive=true;
		/*NOTE: Some sort of weird glitch involving memory addresses tends to afflict me at launch, causing
		 * the units to retain the number of movement points they were left with after the application
		 * closes. This is a glitch I cannot resolve any other way yet, but I will mitigate it by insuring
		 * each unit, starting with the first unit in the battle, gets their fair share of movement points.*/
		currentBattle.getUnitAtInitiativePosition(0).setMovementPointsLeft(currentBattle.getUnitAtInitiativePosition(0).movementPoints);
		currentBattle.startBattleRoutine();
	}
	boolean isBattleActive(){
		return this.isBattleActive;
	}
	void setBattleState(boolean tf){
		this.isBattleActive=tf;
	}
	//The double argument "counter" must always begin at the value -1, the boolean argument "master"
	//should always start as true, and the boolean argument "remove" should always start as false.
	//If remove is true, the minimum range will be used to count gameCubes instead of the maximum range.
	ArrayList<gameCube> rangeArea(gameCube targetCube, double maxRange, double minRange, double counter,boolean master,boolean remove){
		ArrayList<gameCube> spacesInRange = new ArrayList<gameCube>();
		double newCounter=counter;
		boolean newMaster=false;
		boolean newRemove=false;
		newCounter++;
		//if (newCounter>=minRange&&newCounter<=maxRange){
			spacesInRange.add(targetCube);
		//}
		targetCube.isAnalyzed=true;
		//Add all spaces up to the maximum range first.
		if (newCounter<maxRange&&remove==false){
			if (currentMap.getCubeNorth(targetCube)!=null&&currentMap.getCubeNorth(targetCube).isAnalyzed==false){
				spacesInRange.addAll(rangeArea(currentMap.getCubeNorth(targetCube),maxRange,minRange,newCounter,newMaster,remove));
				}
				if (currentMap.getCubeSouth(targetCube)!=null&&currentMap.getCubeSouth(targetCube).isAnalyzed==false){
					spacesInRange.addAll(rangeArea(currentMap.getCubeSouth(targetCube),maxRange,minRange,newCounter,newMaster,remove));
				}
				if (currentMap.getCubeEast(targetCube)!=null&&currentMap.getCubeEast(targetCube).isAnalyzed==false){
					spacesInRange.addAll(rangeArea(currentMap.getCubeEast(targetCube),maxRange,minRange,newCounter,newMaster,remove));
				}
				if (currentMap.getCubeWest(targetCube)!=null&&currentMap.getCubeWest(targetCube).isAnalyzed==false){
					spacesInRange.addAll(rangeArea(currentMap.getCubeWest(targetCube),maxRange,minRange,newCounter,newMaster,remove));
				}
		}
		if (newCounter<minRange-1&&remove==true){
			if (currentMap.getCubeNorth(targetCube)!=null&&currentMap.getCubeNorth(targetCube).isAnalyzed==false){
				spacesInRange.addAll(rangeArea(currentMap.getCubeNorth(targetCube),maxRange,minRange,newCounter,newMaster,remove));
				}
				if (currentMap.getCubeSouth(targetCube)!=null&&currentMap.getCubeSouth(targetCube).isAnalyzed==false){
					spacesInRange.addAll(rangeArea(currentMap.getCubeSouth(targetCube),maxRange,minRange,newCounter,newMaster,remove));
				}
				if (currentMap.getCubeEast(targetCube)!=null&&currentMap.getCubeEast(targetCube).isAnalyzed==false){
					spacesInRange.addAll(rangeArea(currentMap.getCubeEast(targetCube),maxRange,minRange,newCounter,newMaster,remove));
				}
				if (currentMap.getCubeWest(targetCube)!=null&&currentMap.getCubeWest(targetCube).isAnalyzed==false){
					spacesInRange.addAll(rangeArea(currentMap.getCubeWest(targetCube),maxRange,minRange,newCounter,newMaster,remove));
				}
		}
		for (int i=0;i<spacesInRange.size();i++){
			//Now allow the cube to be used again when this method is called.
			//if (master==true){
			spacesInRange.get(i).isAnalyzed=false;
			//}
		}
		//Now remove all spaces in the minimum range.
		if (master==true){
			ArrayList<gameCube> spacesToRemove = new ArrayList<gameCube>();
			newRemove=true;
			newCounter=-1;
			newCounter++;
			if (newCounter<minRange){
				spacesToRemove.add(targetCube);
				targetCube.isAnalyzed=true;
				if (currentMap.getCubeNorth(targetCube)!=null&&currentMap.getCubeNorth(targetCube).isAnalyzed==false){
					spacesToRemove.addAll(rangeArea(currentMap.getCubeNorth(targetCube),maxRange,minRange,newCounter,newMaster,newRemove));
					}
					if (currentMap.getCubeSouth(targetCube)!=null&&currentMap.getCubeSouth(targetCube).isAnalyzed==false){
						spacesToRemove.addAll(rangeArea(currentMap.getCubeSouth(targetCube),maxRange,minRange,newCounter,newMaster,newRemove));
					}
					if (currentMap.getCubeEast(targetCube)!=null&&currentMap.getCubeEast(targetCube).isAnalyzed==false){
						spacesToRemove.addAll(rangeArea(currentMap.getCubeEast(targetCube),maxRange,minRange,newCounter,newMaster,newRemove));
					}
					if (currentMap.getCubeWest(targetCube)!=null&&currentMap.getCubeWest(targetCube).isAnalyzed==false){
						spacesToRemove.addAll(rangeArea(currentMap.getCubeWest(targetCube),maxRange,minRange,newCounter,newMaster,newRemove));
					}
			}
			for (int i=0;i<spacesToRemove.size();i++){
				//Now allow the cube to be used again when this method is called.
				//if (master==true){
				spacesToRemove.get(i).isAnalyzed=false;
				//}
			}
			spacesInRange.removeAll(spacesToRemove);
		}
		return spacesInRange;
	}
	ArrayList<gameCube> getSpacesInMovementRange(gameCube sourceCube, double movementPointsLeft){
		ArrayList<gameCube> spacesInMovement = new ArrayList<gameCube>();
		double newMovementPointsLeft = movementPointsLeft;
		spacesInMovement.add(sourceCube);
		newMovementPointsLeft--;
		//Prevent the same space from being used again.
		sourceCube.isAnalyzed=true;
		if (newMovementPointsLeft>=0){
			if (currentMap.getCubeNorth(sourceCube)!=null&&currentMap.getCubeNorth(sourceCube).getEntity()==null&&currentMap.getCubeNorth(sourceCube).isAnalyzed==false){
			spacesInMovement.addAll(getSpacesInMovementRange(currentMap.getCubeNorth(sourceCube),newMovementPointsLeft));
			}
			if (currentMap.getCubeSouth(sourceCube)!=null&&currentMap.getCubeSouth(sourceCube).getEntity()==null&&currentMap.getCubeSouth(sourceCube).isAnalyzed==false){
				spacesInMovement.addAll(getSpacesInMovementRange(currentMap.getCubeSouth(sourceCube),newMovementPointsLeft));
			}
			if (currentMap.getCubeEast(sourceCube)!=null&&currentMap.getCubeEast(sourceCube).getEntity()==null&&currentMap.getCubeEast(sourceCube).isAnalyzed==false){
				spacesInMovement.addAll(getSpacesInMovementRange(currentMap.getCubeEast(sourceCube),newMovementPointsLeft));
			}
			if (currentMap.getCubeWest(sourceCube)!=null&&currentMap.getCubeWest(sourceCube).getEntity()==null&&currentMap.getCubeWest(sourceCube).isAnalyzed==false){
				spacesInMovement.addAll(getSpacesInMovementRange(currentMap.getCubeWest(sourceCube),newMovementPointsLeft));
			}
		}
		for (int i=0;i<spacesInMovement.size();i++){
			//Now allow the cube to be used again when this method is called.
			spacesInMovement.get(i).isAnalyzed=false;
		}
		return spacesInMovement;
	}
	void addSpaceToPath(gameCube targetSpace, gameCube sourceSpace, double movementPointsLeft, ArrayList<gameCube> highlightedSpaces){
		if (highlightedSpacesTest(targetSpace, highlightedSpaces)==true&&targetSpace!=sourceSpace&&movementPointsLeft>=0){
			movementPath.add(targetSpace);
			if (targetSpace==sourceSpace.getMap().getCubeNorth(sourceSpace)){
				movementPathDirection.add(0);
			}
			if (targetSpace==sourceSpace.getMap().getCubeEast(sourceSpace)){
				movementPathDirection.add(1);
			}
			if (targetSpace==sourceSpace.getMap().getCubeSouth(sourceSpace)){
				movementPathDirection.add(2);
			}
			if (targetSpace==sourceSpace.getMap().getCubeWest(sourceSpace)){
				movementPathDirection.add(3);
			}
		}
	}
	void removeSpacesFromPath(){
		movementPath.clear();
		movementPathDirection.clear();
	}
	boolean highlightedSpacesTest(gameCube targetSpace,ArrayList<gameCube> highlightedSpaces){
		boolean targetSpaceIsInHighlightedSpaces=false;
		for (int i=0;i<highlightedSpaces.size();i++){
			if (targetSpace==highlightedSpaces.get(i)){
				targetSpaceIsInHighlightedSpaces=true;
			}
		}
		return targetSpaceIsInHighlightedSpaces;
	}
	//To be used with highlightedSpaces.
	boolean duplicateSpacesTest(gameCube targetSpace){
		boolean targetSpaceIsADuplicate=false;
		for (int i=0;i<movementPath.size();i++){
			if (targetSpace==movementPath.get(i)){
				targetSpaceIsADuplicate=true;
			}
		}
		return targetSpaceIsADuplicate;
	}
	void exportMovementPathToDraw(Entity target){
		target.removeSpacesFromDrawingPath();
		for(int i = 0;i<movementPath.size();i++){
			target.drawingMovementPath.add(movementPath.get(i));
			target.drawingMovementPathDirection.add(movementPathDirection.get(i));
		}
	}
	public void keyPressed (KeyEvent e){
		
	}
	public void keyReleased(KeyEvent e){
		
	}
	public void keyTyped(KeyEvent e){
		
	}
	Battle getCurrentBattle(){
		return this.currentBattle;
	}
	void updateListStringsSpell(ArrayList<Spell> list){
		if (this.currentMenuSuper.get(this.currentPage).size()>=1){
			currentRenderer.ability1.setString(list.get(0).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=2){
			currentRenderer.ability2.setString(list.get(1).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=3){
			currentRenderer.ability3.setString(list.get(2).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=4){
			currentRenderer.ability4.setString(list.get(3).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=5){
			currentRenderer.ability5.setString(list.get(4).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=6){
			currentRenderer.ability6.setString(list.get(5).name);
		}
	}
	void updateListStringsAbility(ArrayList<Ability> list){
		if (this.currentMenuSuper.get(this.currentPage).size()>=1){
			currentRenderer.ability1.setString(list.get(0).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=2){
			currentRenderer.ability2.setString(list.get(1).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=3){
			currentRenderer.ability3.setString(list.get(2).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=4){
			currentRenderer.ability4.setString(list.get(3).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=5){
			currentRenderer.ability5.setString(list.get(4).name);
		}
		if (this.currentMenuSuper.get(this.currentPage).size()>=6){
			currentRenderer.ability6.setString(list.get(5).name);
		}
	}
}
