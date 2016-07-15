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
	ArrayList<String> currentMenu = new ArrayList<String>();
	int currentMenuIndex = 0;
	int gameScreen = 0;
	boolean isBattleActive=false;
	boolean isFreelookActive=false;
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
				}
			}
		}
	}
	//Plays the current battle, which must be initiated first.
	void playBattle(){
		currentMap=currentBattle.getMap();
		gameScreen=1;
		this.isBattleActive=true;
		currentBattle.startBattleRoutine();
	}
	boolean isBattleActive(){
		return this.isBattleActive;
	}
	void setBattleState(boolean tf){
		this.isBattleActive=tf;
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
}
