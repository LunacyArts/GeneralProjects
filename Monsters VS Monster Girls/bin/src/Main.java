import com.sun.j3d.utils.applet.MainFrame;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.io.*;
import java.util.ArrayList;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			float truthiness = 1;
			gameController gameKernel = new gameController("stinkyGoblin");
			gameKernel.addKeyListener(gameKernel);
			gameMap dumb = new gameMap(10,10,1,"Battlefield",0, gameKernel);
			gameMap stupid = new gameMap(20,20,1,"Warzone",1, gameKernel);
			gameMap imbecile = new gameMap(50,50,1,"No Man's Land",2,gameKernel);
			//Player1's default color is blue, Player2's is red, Player3's is green
			//(since they are usually the allies), and Player4's is yellow
			//(since they are usually the neutral faction or the second enemy
			//faction). In multiplayer, players get to choose these attributes for
			//themselves.
			//In singleplayer, Player1 always remains blue, but there may be
			//non-typical arrangements where they may find themselves allied with
			//either Player2, Player3, or Player4 and have the odd ones out as
			//enemies. These scenarios usually result from the player's choices
			//and allegiances.
			ArrayList<Player> playerList = new ArrayList<Player>();
			Player Player1 = new Player(0, "Monster Girls", new Color3f(0.0f,0.0f,1.0f), 0, gameKernel);
			//Obviously, Player2's name is tentative. It will be renamed to
			//"Monsters" once I have real monsters controlled by that player.
			Player Player2 = new Player(1,"Fake Monster Girls", new Color3f(1.0f,0.0f,0.0f), 0, gameKernel);
			Player Player3 = new Player(2,"Allies",new Color3f(0.0f,1.0f,0.0f),0,gameKernel);
			Player Player4 = new Player(3,"Neutral",new Color3f(1.0f,1.0f,0.0f),0,gameKernel);
			playerList.add(Player1);
			Entity VampireGirl = new Entity(0, "Vampire Girl",Player1,gameKernel);
			VampireGirl.setAttributes(1, 10, 10, 1);
			Player1.addUnitToControlledUnits(VampireGirl);
			new SpriteHandler(VampireGirl, new File("C:/Users/yoshi_000/Vampire Girl.png"));
			VampireGirl.getSpriteHandler().addSpriteSectionSet(new ArrayList<SpriteMapSection>(), "stand0");
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(0, 123, 181, 61, 127);
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(0, 49, 180, 66 , 127);
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(0, 194, 178, 61, 127);
			VampireGirl.getSpriteHandler().addSpriteSectionSet(new ArrayList<SpriteMapSection>(), "stand1");
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(1, 331, 181, 61, 127);
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(1, 399, 180, 66 , 127);
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(1, 260, 178, 61, 127);
			VampireGirl.getSpriteHandler().addSpriteSectionSet(new ArrayList<SpriteMapSection>(), "stand2");
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(2, 121, 20, 61, 162);
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(2, 49, 20, 66 , 162);
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(2, 192, 20, 61, 163);
			VampireGirl.getSpriteHandler().addSpriteSectionSet(new ArrayList<SpriteMapSection>(), "stand3");
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(3, 331, 21, 61, 162);
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(3, 397, 21, 66, 162);
			VampireGirl.getSpriteHandler().createNewSpriteSectionAndAddToSet(3, 260, 21, 61, 163);
			VampireGirl.setFacing(2);
			dumb.addEntityToEntityList(VampireGirl);
			//The name for the variable below is now obsolete. Never trust a lazy
			//programmer.
			int threeOrTwo = 3;
			Entity VampireGirlTWO = new Entity(VampireGirl);
			VampireGirlTWO.setAttributes(1, 10, 10, 1);
			VampireGirlTWO.setName("Vampire Girl's Evil Twin");
			Player2.addUnitToControlledUnits(VampireGirlTWO);
			new SpriteHandler(VampireGirlTWO,VampireGirl.getSpriteHandler());
			Player2.addUnitToControlledUnits(VampireGirlTWO);
			VampireGirlTWO.setFacing(1);
			dumb.addEntityToEntityList(VampireGirlTWO);
			dumb.placeEntityAtCube(VampireGirlTWO, dumb.getCubeByCoordinates(5, 5, 0));
			/*for (int i = 1; i < 99;i++){
				Entity vampireGirlClone = new Entity(VampireGirl);
				new SpriteHandler(vampireGirlClone,VampireGirl.getSpriteHandler());
				Player1.addUnitToControlledUnits(vampireGirlClone);
				vampireGirlClone.setFacing(threeOrTwo);
				dumb.addEntityToEntityList(vampireGirlClone);
				dumb.placeEntityAtCube(vampireGirlClone, dumb.getCubeList().get(i));
				threeOrTwo++;
				if (threeOrTwo>3){
					threeOrTwo=0;
				}
			}*/
			//gameCube troubleshoot = dumb.getCubeByCoordinates(0, 0, 0);
			dumb.placeEntityAtCube(VampireGirl, dumb.getCubeByCoordinates(0, 0, 0));
			gameKernel.setCurrentMap(dumb);
			//Screen ID '0' leads to the current "In Development" message.
			//Screen ID '1' leads to a battle screen of the current map. INDEV!
			//Screen ID '2' leads to a stupid ball. Please ignore it.
			gameKernel.setScreen(1);
			ArrayList<String> battleOptionsMenu = new ArrayList<String>();
			battleOptionsMenu.add(new String("Move"));
			battleOptionsMenu.add(new String("Attack"));
			battleOptionsMenu.add(new String("Spell"));
			battleOptionsMenu.add(new String("End Turn"));
			gameKernel.currentMenu=battleOptionsMenu;
			gameKernel.initiateBattle(dumb, playerList, dumb.getEntitiesOnMap());
			gameKernel.playBattle();
			Renderer MVMG = new Renderer(gameKernel);
			gameKernel.setRenderer(MVMG);
			MVMG.addKeyListener(MVMG);
			MainFrame frame = new MainFrame(MVMG,640,480);
			frame.setTitle("Monsters VS Monster Girls");
			System.out.println("Hello world!");
			/*while(truthiness==1){
				
			}*/
	}

}
