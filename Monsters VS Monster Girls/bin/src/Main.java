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
			System.out.println(dumb);
			gameMap stupid = new gameMap(20,20,1,"Warzone",1, gameKernel);
			gameMap imbecile = new gameMap(50,50,1,"No Man's Land",2,gameKernel);
			String stringBuffer = new String("A partially corrupted former human girl. Despite their differences, they resemble humans in many ways.");
			entityType monsterGirl = new entityType("Monster Girl",stringBuffer);
			stringBuffer = new String("A completely corrupted former human. Malicious and full of contempt for the world.");
			entityType monster = new entityType("Monster",stringBuffer);
			stringBuffer = new String("A partially corrupted former human girl, with further corruption by demons.");
			entityType abominationGirl = new entityType("Abomination Girl",stringBuffer);
			stringBuffer = new String("A creature driven by primal instincts as opposed to rational thought.");
			entityType beast = new entityType("Beast",stringBuffer);
			stringBuffer = new String("An amoral being created to manage Hell. Many have forgotten their original purpose.");
			entityType demon = new entityType("Demon",stringBuffer);
			stringBuffer = new String("A divine being created by God and entrusted with great power to carry out His will.");
			entityType angel = new entityType("Angel",stringBuffer);
			stringBuffer = new String("A humanoid monster related to devils and undeads. They boast many strengths, but many weaknesses as well.");
			entityClass vampireClass = new entityClass("Vampire",stringBuffer);
			entityRace vampireRace = new entityRace("Vampire",stringBuffer);
			stringBuffer = new String("The demonic claws of the vampire are apt at tearing mortal flesh.");
			Ability vampireClaw = new Ability("Vampire Claw",stringBuffer,Ability.PHYSICAL);
			vampireClaw.setAbilityStats("None", Ability.SLASHING, 0, 1, 0, 0, 4, 1);
			vampireClaw.treatAsAttack=true;
			vampireClass.addAbility(vampireClaw, 0);
			stringBuffer = new String("A creature with one central eye that has a strong affinity to magic. They may use the terrible racial ability 'Evil Eye'.");
			entityRace cyclopsRace = new entityRace("Cyclops",stringBuffer);
			stringBuffer = new String("A mindless mass of viscous semi-solid with the digested remnants of its prey suspended in its form. Slow, but numerous.");
			entityRace slimeRace = new entityRace("Slime",stringBuffer);
			stringBuffer = new String("A humanoid with the torso of a female human, but the talons, head, and wings of a bird. They are weak, fast, and annoying.");
			entityRace harpyRace = new entityRace("Harpy",stringBuffer);
			stringBuffer = new String("A monster with the torso of a powerfully built human and lower body of a horse. They are deadly in numbers.");
			entityRace centaurRace = new entityRace("Centaur",stringBuffer);
			stringBuffer = new String("A terrifying lizard-beast with numerous snake-like heads, each with powerful elemental breath. They are best known for their intractable regeneration.");
			entityRace hydraRace = new entityRace("Hydra",stringBuffer);
			stringBuffer = new String("A giant scorpion imbued with a modicum of intelligence. There are multiple types, but all have a lethal sting to behold.");
			entityRace scorpionoidRace = new entityRace("Scorpion",stringBuffer);
			stringBuffer = new String("A giant octopus imbued with a modicum of intelligence. They have transcended their instincts and become cunning creatures.");
			entityRace cephalopodRace = new entityRace("Octopus",stringBuffer);
			stringBuffer = new String("An infectious fungi has adapted to spread among any animal, taking control of its host's body.");
			entityRace fungoidRace = new entityRace("Mushroom",stringBuffer);
			stringBuffer = new String("An anomaly in spacetime that is construed by the senses to resemble a familiar creature or concept.");
			entityRace lightAberrationRace = new entityRace("Abomination of Light",stringBuffer);
			stringBuffer = new String("A powerfully built humanoid monster with the head of a bull. They boast great strength and can never get lost.");
			entityRace minotaurRace = new entityRace("Minotaur",stringBuffer);
			stringBuffer = new String("A humanoid monster with flesh covered in a layer of stone and bat-like features. They stalk their prey while disguised as statues.");
			entityRace gargoyleRace = new entityRace("Gargoyle",stringBuffer);
			stringBuffer = new String("A formerly dead creature re-animated by magic. They serve their masters without a will of their own.");
			entityRace zombieRace = new entityRace("Zombie",stringBuffer);
			stringBuffer = new String("A former human that involuntarily shapeshifts to and from a terrifying animalistic form. These creatures of the full moon bear qualities like those of their animal form.");
			entityRace lycanthropeRace = new entityRace("Lycanthrope",stringBuffer);
			stringBuffer = new String("An exceptionally powerful lizard-beast of great size, strength, and magical ability. They are among the strongest of monsters in existence.");
			entityRace dragonRace = new entityRace("Dragon",stringBuffer);
			stringBuffer = new String("A weak ice missile strikes unerringly at its target for ice damage.");
			Spell iceMissile = new Spell("Ice Missile",stringBuffer,1);
			iceMissile.setAbilityStats("Ice",Ability.EVOCATIONAL,0, 5, 0, 0, 3, 1);
			Spell iceSnipe = new Spell("Ice Snipe",stringBuffer,1);
			iceSnipe.setAbilityStats("Ice", Ability.EVOCATIONAL, 6, 10, 3, 5, 1, 1);
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
			Player Player1 = new Player(0, "Monster Girls", new Color3f(0.0f,0.0f,1.0f), Player.AI, gameKernel);
			//Obviously, Player2's name is tentative. It will be renamed to
			//"Monsters" once I have real monsters controlled by that player.
			Player Player2 = new Player(1,"Fake Monster Girls", new Color3f(1.0f,0.0f,0.0f), Player.HUMAN, gameKernel);
			Player Player3 = new Player(2,"Allies",new Color3f(0.0f,1.0f,0.0f),Player.AI,gameKernel);
			Player Player4 = new Player(3,"Neutral",new Color3f(1.0f,1.0f,0.0f),Player.AI,gameKernel);
			playerList.add(Player1);
			Entity VampireGirl = new Entity(0, "Vampire Girl",monsterGirl,vampireRace,vampireClass,Player1,gameKernel);
			VampireGirl.setAttributes(1, 10, 10, 1, 6);
			VampireGirl.addSpell(iceMissile);
			VampireGirl.addSpell(iceSnipe);
			//VampireGirl.addSpell(iceSnipe);
			//VampireGirl.addSpell(iceSnipe);
			//VampireGirl.addSpell(iceSnipe);
			//VampireGirl.addSpell(iceSnipe);
			//VampireGirl.addSpell(iceSnipe);
			Player1.addUnitToControlledUnits(VampireGirl);
			new SpriteHandler(VampireGirl, new File("C:/Users/yoshi/Vampire Girl.png"));
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
			VampireGirl.setFacing(Entity.SOUTH);
			dumb.addEntityToEntityList(VampireGirl);
			//The name for the variable below is now obsolete. Never trust a lazy
			//programmer.
			int threeOrTwo = 3;
			Entity VampireGirlTWO = new Entity(VampireGirl);
			VampireGirlTWO.setAttributes(1, 10, 10, 2, 6);
			VampireGirlTWO.setName("Vampire Girl's Evil Twin");
			Player2.addUnitToControlledUnits(VampireGirlTWO);
			new SpriteHandler(VampireGirlTWO,VampireGirl.getSpriteHandler());
			VampireGirlTWO.setFacing(Entity.EAST);
			dumb.addEntityToEntityList(VampireGirlTWO);
			dumb.placeEntityAtCube(VampireGirlTWO, dumb.getCubeByCoordinates(5, 5, 0));
			Entity vampireGirlClone = new Entity(VampireGirl);
			new SpriteHandler(vampireGirlClone,VampireGirl.getSpriteHandler());
			vampireGirlClone.setAttributes(1, 10, 10, 1, 6);
			vampireGirlClone.setName("Vampire Girl's Other Twin");
			Player1.addUnitToControlledUnits(vampireGirlClone);
			vampireGirlClone.setFacing(2);
			dumb.addEntityToEntityList(vampireGirlClone);
			dumb.placeEntityAtCube(vampireGirlClone, dumb.getCubeByCoordinates(1, 0, 0));
			vampireGirlClone = new Entity(VampireGirl);
			new SpriteHandler(vampireGirlClone,VampireGirl.getSpriteHandler());
			vampireGirlClone.setAttributes(1, 10, 10, 1, 6);
			vampireGirlClone.setName("Vampire Girl's Other Twin");
			Player1.addUnitToControlledUnits(vampireGirlClone);
			vampireGirlClone.setFacing(2);
			dumb.addEntityToEntityList(vampireGirlClone);
			dumb.placeEntityAtCube(vampireGirlClone, dumb.getCubeByCoordinates(2, 0, 0));
			vampireGirlClone = new Entity(VampireGirl);
			new SpriteHandler(vampireGirlClone,VampireGirl.getSpriteHandler());
			vampireGirlClone.setAttributes(1, 10, 10, 1, 6);
			vampireGirlClone.setName("Vampire Girl's Other Twin");
			Player1.addUnitToControlledUnits(vampireGirlClone);
			vampireGirlClone.setFacing(2);
			dumb.addEntityToEntityList(vampireGirlClone);
			dumb.placeEntityAtCube(vampireGirlClone, dumb.getCubeByCoordinates(3, 0, 0));
			vampireGirlClone = new Entity(VampireGirl);
			new SpriteHandler(vampireGirlClone,VampireGirl.getSpriteHandler());
			vampireGirlClone.setAttributes(1, 10, 10, 1, 6);
			vampireGirlClone.setName("Vampire Girl's Other Twin");
			Player1.addUnitToControlledUnits(vampireGirlClone);
			vampireGirlClone.setFacing(2);
			dumb.addEntityToEntityList(vampireGirlClone);
			dumb.placeEntityAtCube(vampireGirlClone, dumb.getCubeByCoordinates(4, 0, 0));
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
			battleOptionsMenu.add(new String("Ability"));
			battleOptionsMenu.add(new String("Spell"));
			battleOptionsMenu.add(new String("End Turn"));
			gameKernel.currentMenu=battleOptionsMenu;
			ArrayList<String> abilitiesMenu = new ArrayList<String>();
			if (gameKernel.getCurrentScreen()==1){
			gameKernel.initiateBattle(dumb, playerList, dumb.getEntitiesOnMap());
			gameKernel.playBattle();
			}
			Renderer MVMG = new Renderer(gameKernel);
			gameKernel.setRenderer(MVMG);
			MVMG.addKeyListener(MVMG);
			MainFrame frame = new MainFrame(MVMG,640,480);
			frame.setTitle("Monsters VS Monster Girls");
			System.out.println("Hello world!");
			/*while(truthiness==1){
				
			}*/
			/*Alright, silliness aside, let me explain some of the basic
			 * structure of the game. There are three main threads: the first
			 * is this Main thread, the second is the Renderer thread which
			 * processes key inputs and initializes everything, and the third is 
			 * the Draw Loop, which updates the Canvas3D of the game. This Main
			 * thread here will contain the core game code for menu hierarchy
			 * and game structure. Inputs from the Renderer will be processed
			 * here in the same fashion as I did in a previous game project,
			 * making use of While loops to accomplish everything.*/
			while (gameKernel.isBattleActive==true){
				if (gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().playerController==Player.AI){
					gameKernel.currentMenuIdent="nonPlayer";
					MVMG.observedSpace=gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube();
					//Initialize the relevant variables that will be used.
					gameCube chosenTarget=null;
					Ability chosenAbility=null;
					ArrayList<gameCube> chosenPath=null;
					ArrayList<Integer> chosenPathDirection=null;
					//Now begin analyzing choices. How this will work is: there will be four connected
					//array lists, and all are attached by virtue of a single integer. The first two array
					//lists will contain possible movement paths and their directions. The third array list
					//will contain target squares weighted based on decision effectiveness, and the fourth
					//array list will contain the combined decision weight for that decision.
					//These array lists will then be reorganized to choose the decision with the heighest
					//decision weight.
					ArrayList<ArrayList<gameCube>> possiblePaths = new ArrayList<ArrayList<gameCube>>();
					ArrayList<ArrayList<Integer>> possiblePathsDirection = new ArrayList<ArrayList<Integer>>();
					ArrayList<Ability> possibleAbilities = new ArrayList<Ability>();
					ArrayList<gameCube> possibleTargets = new ArrayList<gameCube>();
					ArrayList<Double> decisionWeight = new ArrayList<Double>();
					//Temporary Example
					ArrayList<gameCube> dumbTemporaryPath = new ArrayList<gameCube>();
					ArrayList<Integer> dumbTemporaryPathDirection = new ArrayList<Integer>();
					dumbTemporaryPath.add(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube());
					dumbTemporaryPathDirection.add(0);
					dumbTemporaryPath.add(gameKernel.currentMap.getCubeNorth(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube()));
					dumbTemporaryPathDirection.add(0);
					dumbTemporaryPath.add(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube())));
					dumbTemporaryPathDirection.add(0);
					dumbTemporaryPath.add(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube()))));
					dumbTemporaryPathDirection.add(0);
					dumbTemporaryPath.add(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube())))));
					dumbTemporaryPathDirection.add(0);
					dumbTemporaryPath.add(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube()))))));
					dumbTemporaryPathDirection.add(0);
					dumbTemporaryPath.add(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentMap.getCubeNorth(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube())))))));
					dumbTemporaryPathDirection.add(0);
					possibleAbilities.add(iceSnipe);
					possibleTargets.add(gameKernel.currentMap.getCubeEast(gameKernel.currentMap.getCubeEast(gameKernel.currentMap.getCubeEast(gameKernel.currentMap.getCubeEast(gameKernel.currentMap.getCubeEast(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube()))))));
					decisionWeight.add(new Double(0));
					possiblePaths.add(dumbTemporaryPath);
					possiblePathsDirection.add(dumbTemporaryPathDirection);
					ArrayList<ArrayList<gameCube>> reorganizedPaths = new ArrayList<ArrayList<gameCube>>();
					ArrayList<ArrayList<Integer>> reorganizedPathsDirection = new ArrayList<ArrayList<Integer>>();
					ArrayList<Ability> reorganizedAbilities = new ArrayList<Ability>();
					ArrayList<gameCube> reorganizedTargets = new ArrayList<gameCube>();
					ArrayList<Double> reorganizedDecisionWeight = new ArrayList<Double>();
					for (double i=10000;i>=-10000;i--){
						for (int j=0;j<decisionWeight.size();j++){
							if (decisionWeight.get(j)==i){
								reorganizedDecisionWeight.add(decisionWeight.get(j));
								reorganizedTargets.add(possibleTargets.get(j));
								reorganizedAbilities.add(possibleAbilities.get(j));
								reorganizedPathsDirection.add(possiblePathsDirection.get(j));
								reorganizedPaths.add(possiblePaths.get(j));
							}
						}
					}
					chosenTarget=reorganizedTargets.get(0);
					chosenAbility=reorganizedAbilities.get(0);
					chosenPath=reorganizedPaths.get(0);
					chosenPathDirection=reorganizedPathsDirection.get(0);
					//If the AI chose their actions, perform them here.
					if (chosenPath!=null&&chosenPath.size()>0){
						gameKernel.movementPath=chosenPath;
						gameKernel.movementPathDirection=chosenPathDirection;
						gameKernel.exportMovementPathToDraw(gameKernel.getCurrentBattle().getCurrentUnitTurn());
						gameKernel.getCurrentBattle().getCurrentUnitTurn().unitIsMoving=true;
						gameKernel.getCurrentMap().placeEntityAtCube(gameKernel.getCurrentBattle().getCurrentUnitTurn(), gameKernel.movementPath.get(gameKernel.movementPath.size()-1));
						gameKernel.getCurrentBattle().getCurrentUnitTurn().setFacing(gameKernel.movementPathDirection.get(gameKernel.movementPathDirection.size()-1));
						gameKernel.currentMenuIdent="moving";
					}
					while (gameKernel.currentMenuIdent=="moving"){
						
					}
					gameKernel.movementPath.clear();
					gameKernel.movementPathDirection.clear();
					if (chosenTarget!=null&&chosenAbility!=null){
						gameKernel.currentBattle.getCurrentUnitTurn().useAbilityOnTarget(chosenAbility, chosenTarget);
						MVMG.observedSpace=chosenTarget;
						gameKernel.currentMenuIdent="attacking";
					}
					boolean hmm=false;
					while (gameKernel.currentMenuIdent=="attacking"){
						if (hmm==false){
							MVMG.highlightYellowRevert();
							MVMG.highlightYellowFunction(gameKernel.rangeArea(chosenTarget, chosenAbility.baseMaxArea, chosenAbility.baseMinArea, -1, true, false));
							hmm=true;
						}
					}
					gameKernel.currentBattle.nextTurn();
					MVMG.unitIsChanged=false;
					MVMG.changeText1=false;
				}
				if (gameKernel.currentBattle.getCurrentUnitTurn().getControllingPlayer().playerController==Player.HUMAN){
					gameKernel.currentMenuIdent="battleOptions";
				if (MVMG.controlForward==true&&gameKernel.currentMenuIndex==0){
					MVMG.controlForward=false;
					System.out.println("Selected Move");
					//ArrayList<gameCube> debugRange = gameKernel.rangeArea(gameKernel.getCurrentBattle().getCurrentUnitTurn().getCurrentCube(), 5, 6, -1,true,false);
					//for (int i=0;i<debugRange.size();i++){
						//System.out.println(debugRange.get(i).getX()+","+debugRange.get(i).getY()+","+debugRange.get(i).getZ());
					//}
					/*There are two phases to moving; one where you select cubes in
					 * your path and confirm, and another to confirm the path. Cancelling
					 * at the second phase returns to the first phase and resets the
					 * movementPath.*/
					boolean pathConfirmed=false;
					gameKernel.currentMenuIdent="moveTrace";
					MVMG.changeText1=false;
					MVMG.highlightedSpaces=gameKernel.getSpacesInMovementRange(gameKernel.getCurrentBattle().getCurrentUnitTurn().getCurrentCube(),gameKernel.getCurrentBattle().getCurrentUnitTurn().getMovementPointsLeft());
					for(int i=0;i<MVMG.highlightedSpaces.size();i++){
						MVMG.highlightedSpaces.get(i).isHighlighted=true;
						MVMG.highlightedSpaces.get(i).cubeColor="cyan";
					}
					MVMG.highlightedSpaces = gameKernel.currentMap.getCubeList();
					for(int i = 0; i<MVMG.highlightedSpaces.size();i++){
						for (int r=0;r<10;r++){
						MVMG.highlightedSpaces.get(i).getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(MVMG.cyan);
						MVMG.highlightedSpaces.get(i).getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(MVMG.cyan);
						MVMG.highlightedSpaces.get(i).associatedBox.getAppearance().getMaterial().setAmbientColor(MVMG.cyan);
						MVMG.highlightedSpaces.get(i).associatedBox.getAppearance().getMaterial().setDiffuseColor(MVMG.cyan);
						}
						MVMG.highlightedSpaces.get(i).cubeColor="cyan";
					}
					for (int i = 0;i<MVMG.highlightedSpaces.size();i++){
						for (int r=0;r<30;r++){
							if (MVMG.highlightedSpaces.get(i).isHighlighted==false){
								MVMG.highlightedSpaces.get(i).getAssociatedSphereOfHelp().getAppearance().getMaterial().setAmbientColor(MVMG.blue);
								MVMG.highlightedSpaces.get(i).getAssociatedSphereOfHelp().getAppearance().getMaterial().setDiffuseColor(MVMG.blue);
								MVMG.highlightedSpaces.get(i).associatedBox.getAppearance().getMaterial().setAmbientColor(MVMG.blue);
								MVMG.highlightedSpaces.get(i).associatedBox.getAppearance().getMaterial().setDiffuseColor(MVMG.blue);
							}
						}
						if(MVMG.highlightedSpaces.get(i).isHighlighted==false){
							MVMG.highlightedSpaces.get(i).cubeColor="blue";
						}
					}
					MVMG.highlightedSpaces=gameKernel.getSpacesInMovementRange(gameKernel.getCurrentBattle().getCurrentUnitTurn().getCurrentCube(),gameKernel.getCurrentBattle().getCurrentUnitTurn().getMovementPointsLeft());
					MVMG.targetSpace = gameKernel.currentBattle.getCurrentUnitTurn().hostCube;
					gameKernel.movementPath.add(gameKernel.getCurrentBattle().getCurrentUnitTurn().getCurrentCube());
					gameKernel.movementPathDirection.add(gameKernel.getCurrentBattle().getCurrentUnitTurn().facing);
					while(gameKernel.currentMenuIdent=="moveTrace"&&pathConfirmed==false){
						System.out.print("Trace your path");
						MVMG.bottomLabel.setString("Trace your path with the arrow keys. Press z twice to confirm.");
						while(pathConfirmed==false){
							if(MVMG.controlBackward==true){
								MVMG.controlBackward=false;
								System.out.println("Going back");
								pathConfirmed=true;
							}
							else if (MVMG.controlForward==true){
								MVMG.controlForward=false;
								System.out.println("Press again to confirm movement");
								while(true){
									if(MVMG.controlBackward==true){
										MVMG.controlBackward=false;
										System.out.println("Going back");
										break;
									}
									else if (MVMG.controlForward==true){
										MVMG.controlForward=false;
										System.out.print("Movement confirmed");
										gameKernel.exportMovementPathToDraw(gameKernel.getCurrentBattle().getCurrentUnitTurn());
										gameKernel.getCurrentBattle().getCurrentUnitTurn().unitIsMoving=true;
										gameKernel.getCurrentMap().placeEntityAtCube(gameKernel.getCurrentBattle().getCurrentUnitTurn(), gameKernel.movementPath.get(gameKernel.movementPath.size()-1));
										gameKernel.getCurrentBattle().getCurrentUnitTurn().setFacing(gameKernel.movementPathDirection.get(gameKernel.movementPathDirection.size()-1));
										gameKernel.currentMenuIdent="moving";
										pathConfirmed=true;
										break;
									}
								}
							}
						}
					}
					gameKernel.removeSpacesFromPath();
					if (MVMG.highlightedSpaces!=null){
						for(int i = 0; i<MVMG.highlightedSpaces.size();i++){
							MVMG.highlightedSpaces.get(i).isHighlighted=false;
						}
						}
					while (gameKernel.currentMenuIdent=="moving"){
						
					}
					gameKernel.movementPath.clear();
					gameKernel.movementPathDirection.clear();
					gameKernel.currentMenuIdent="battleOptions";
					MVMG.changeText1=false;
					MVMG.controlBackward=false;
				}
				if (MVMG.controlForward==true&&gameKernel.currentMenuIndex==1){
					MVMG.controlForward=false;
					System.out.println("Selected Attack");
					gameKernel.currentMenuIdent="attackMenu";
					//The two methods below are in the process of being obsoleted. Delete them and this comment
					//when they are no longer needed.
					gameKernel.currentMenu=abilitiesMenu;
					gameKernel.currentMenu.clear();
					gameKernel.currentMenuSuper.clear();
					ArrayList<Ability> abilityList = gameKernel.currentBattle.getCurrentUnitTurn().allAttacks;
					MVMG.currentAbilityList=abilityList;
					//The variable name below is now obsolete, but I never bothered to change it.
					Ability chosenSpell;
					boolean attackConfirmed=false;
					//The arcane purposes of the below primitives and while loop can only be scried through
					//careful observation. Not even I will probably remember how it works after several months.
					int q = 0;
					int j = 6;
					int b = -1;
					while (q<abilityList.size()){
						if (j>=6){
							gameKernel.currentMenuSuper.add(new ArrayList<String>());
							j=0;
							b++;
						}
						gameKernel.currentMenuSuper.get(b).add("Ability");
						j++;
						q++;
					}
					for (int i=0;i<6&&i<abilityList.size();i++){
						abilitiesMenu.add(new String("Ability"));
					}
					gameKernel.updateListStringsAbility(abilityList);
					gameKernel.currentPage=0;
					gameKernel.currentMenuIndex=0;
					MVMG.changeText1=false;
					while(attackConfirmed==false){
							if (MVMG.controlForward==true){
								MVMG.controlForward=false;
								chosenSpell=abilityList.get(gameKernel.currentMenuIndex+6*gameKernel.currentPage);
								MVMG.highlightedSpaces=gameKernel.rangeArea(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube(), chosenSpell.baseMaxRange, chosenSpell.baseMinRange, -1,true,false);
								MVMG.highlightSpacesFunction(MVMG.red, "red");
								MVMG.highlightedSpaces=gameKernel.rangeArea(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube(), chosenSpell.baseMaxRange, chosenSpell.baseMinRange, -1,true,false);
								MVMG.targetSpace = gameKernel.currentBattle.getCurrentUnitTurn().hostCube;
								MVMG.maxRange=chosenSpell.baseMaxArea;
								MVMG.minRange=chosenSpell.baseMinArea;
								gameKernel.currentMenuIdent="attackTarget";
								MVMG.bottomLabel.setString("Designate your target in the yellow spaces. Press z to confirm your attack.");
								MVMG.changeText1=false;
								while(true){
									boolean fixFor=false;
									if (MVMG.controlBackward==true){
										MVMG.controlBackward=false;
										gameKernel.currentMenuIdent="spellMenu";
										break;
									}
									for (int i=0;i<MVMG.highlightedSpaces.size();i++){
									if (MVMG.controlForward==true&&MVMG.targetSpace==MVMG.highlightedSpaces.get(i)){
										MVMG.controlForward=false;
										gameKernel.currentBattle.getCurrentUnitTurn().useAbilityOnTarget(chosenSpell, MVMG.targetSpace);
										MVMG.observedSpace=MVMG.targetSpace;
										gameKernel.currentMenuIdent="attacking";
										fixFor=true;
										attackConfirmed=true;
										break;
									}
									}
									MVMG.controlForward=false;
									if (fixFor==true){
										attackConfirmed=true;
										break;
									}
								}
							}
							if (MVMG.controlBackward==true){
								MVMG.controlBackward=false;
								attackConfirmed=true;
								break;
							}
					}
					gameKernel.currentMenu=battleOptionsMenu;
					gameKernel.currentPage=0;
					gameKernel.currentMenuIndex=1;
					MVMG.maxRange=0;
					MVMG.minRange=0;
					if (MVMG.highlightedSpaces!=null){
						for(int i = 0; i<MVMG.highlightedSpaces.size();i++){
							MVMG.highlightedSpaces.get(i).isHighlighted=false;
						}
						}
					if (MVMG.highlightedYellow!=null){
						for(int i=0;i<MVMG.highlightedYellow.size();i++){
							MVMG.highlightedYellow.get(i).isHighlighted=false;
						}
					}
					while (gameKernel.currentMenuIdent=="attacking"){
						
					}
					gameKernel.currentMenuIdent="battleOptions";
					MVMG.changeText1=false;
					MVMG.controlBackward=false;
				}
				if (MVMG.controlForward==true&&gameKernel.currentMenuIndex==2){
					MVMG.controlForward=false;
					System.out.println("Selected Abilities");
					gameKernel.currentMenuIdent="abilityMenu";
					//The two methods below are in the process of being obsoleted. Delete them and this comment
					//when they are no longer needed.
					gameKernel.currentMenu=abilitiesMenu;
					gameKernel.currentMenu.clear();
					gameKernel.currentMenuSuper.clear();
					ArrayList<Ability> abilityList = gameKernel.currentBattle.getCurrentUnitTurn().allAbilities;
					MVMG.currentAbilityList=abilityList;
					//The variable name below is now obsolete, but I never bothered to change it.
					Ability chosenSpell;
					boolean attackConfirmed=false;
					//The arcane purposes of the below primitives and while loop can only be scried through
					//careful observation. Not even I will probably remember how it works after several months.
					int q = 0;
					int j = 6;
					int b = -1;
					while (q<abilityList.size()){
						if (j>=6){
							gameKernel.currentMenuSuper.add(new ArrayList<String>());
							j=0;
							b++;
						}
						gameKernel.currentMenuSuper.get(b).add("Ability");
						j++;
						q++;
					}
					for (int i=0;i<6&&i<abilityList.size();i++){
						abilitiesMenu.add(new String("Ability"));
					}
					gameKernel.updateListStringsAbility(abilityList);
					gameKernel.currentPage=0;
					gameKernel.currentMenuIndex=0;
					MVMG.changeText1=false;
					while(attackConfirmed==false){
							if (MVMG.controlForward==true){
								MVMG.controlForward=false;
								chosenSpell=abilityList.get(gameKernel.currentMenuIndex+6*gameKernel.currentPage);
								MVMG.highlightedSpaces=gameKernel.rangeArea(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube(), chosenSpell.baseMaxRange, chosenSpell.baseMinRange, -1,true,false);
								MVMG.highlightSpacesFunction(MVMG.red, "red");
								MVMG.highlightedSpaces=gameKernel.rangeArea(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube(), chosenSpell.baseMaxRange, chosenSpell.baseMinRange, -1,true,false);
								MVMG.targetSpace = gameKernel.currentBattle.getCurrentUnitTurn().hostCube;
								MVMG.maxRange=chosenSpell.baseMaxArea;
								MVMG.minRange=chosenSpell.baseMinArea;
								gameKernel.currentMenuIdent="attackTarget";
								MVMG.bottomLabel.setString("Designate your target in the yellow spaces. Press z to confirm your attack.");
								MVMG.changeText1=false;
								while(true){
									boolean fixFor=false;
									if (MVMG.controlBackward==true){
										MVMG.controlBackward=false;
										gameKernel.currentMenuIdent="spellMenu";
										break;
									}
									for (int i=0;i<MVMG.highlightedSpaces.size();i++){
									if (MVMG.controlForward==true&&MVMG.targetSpace==MVMG.highlightedSpaces.get(i)){
										MVMG.controlForward=false;
										gameKernel.currentBattle.getCurrentUnitTurn().useAbilityOnTarget(chosenSpell, MVMG.targetSpace);
										MVMG.observedSpace=MVMG.targetSpace;
										gameKernel.currentMenuIdent="attacking";
										fixFor=true;
										attackConfirmed=true;
										break;
									}
									}
									MVMG.controlForward=false;
									if (fixFor==true){
										attackConfirmed=true;
										break;
									}
								}
							}
							if (MVMG.controlBackward==true){
								MVMG.controlBackward=false;
								attackConfirmed=true;
								break;
							}
					}
					gameKernel.currentMenu=battleOptionsMenu;
					gameKernel.currentPage=0;
					gameKernel.currentMenuIndex=2;
					MVMG.maxRange=0;
					MVMG.minRange=0;
					if (MVMG.highlightedSpaces!=null){
						for(int i = 0; i<MVMG.highlightedSpaces.size();i++){
							MVMG.highlightedSpaces.get(i).isHighlighted=false;
						}
						}
					if (MVMG.highlightedYellow!=null){
						for(int i=0;i<MVMG.highlightedYellow.size();i++){
							MVMG.highlightedYellow.get(i).isHighlighted=false;
						}
					}
					while (gameKernel.currentMenuIdent=="attacking"){
						
					}
					gameKernel.currentMenuIdent="battleOptions";
					MVMG.changeText1=false;
					MVMG.controlBackward=false;
				}
				if (MVMG.controlForward==true&&gameKernel.currentMenuIndex==3){
					MVMG.controlForward=false;
					System.out.println("Selected Spells");
					gameKernel.currentMenuIdent="spellMenu";
					//The two methods below are in the process of being obsoleted. Delete them and this comment
					//when they are no longer needed.
					gameKernel.currentMenu=abilitiesMenu;
					gameKernel.currentMenu.clear();
					gameKernel.currentMenuSuper.clear();
					ArrayList<Spell> spellList = gameKernel.currentBattle.getCurrentUnitTurn().knownSpells;
					MVMG.currentSpellList=spellList;
					Spell chosenSpell;
					boolean attackConfirmed=false;
					//The arcane purposes of the below primitives and while loop can only be scried through
					//careful observation. Not even I will probably remember how it works after several months.
					int q = 0;
					int j = 6;
					int b = -1;
					while (q<spellList.size()){
						if (j>=6){
							gameKernel.currentMenuSuper.add(new ArrayList<String>());
							j=0;
							b++;
						}
						gameKernel.currentMenuSuper.get(b).add("Ability");
						j++;
						q++;
					}
					for (int i=0;i<6&&i<spellList.size();i++){
						abilitiesMenu.add(new String("Ability"));
					}
					gameKernel.updateListStringsSpell(spellList);
					gameKernel.currentPage=0;
					gameKernel.currentMenuIndex=0;
					MVMG.changeText1=false;
					while(attackConfirmed==false){
							if (MVMG.controlForward==true){
								MVMG.controlForward=false;
								chosenSpell=spellList.get(gameKernel.currentMenuIndex+6*gameKernel.currentPage);
								MVMG.highlightedSpaces=gameKernel.rangeArea(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube(), chosenSpell.baseMaxRange, chosenSpell.baseMinRange, -1,true,false);
								MVMG.highlightSpacesFunction(MVMG.red, "red");
								MVMG.highlightedSpaces=gameKernel.rangeArea(gameKernel.currentBattle.getCurrentUnitTurn().getCurrentCube(), chosenSpell.baseMaxRange, chosenSpell.baseMinRange, -1,true,false);
								MVMG.targetSpace = gameKernel.currentBattle.getCurrentUnitTurn().hostCube;
								MVMG.maxRange=chosenSpell.baseMaxArea;
								MVMG.minRange=chosenSpell.baseMinArea;
								gameKernel.currentMenuIdent="attackTarget";
								MVMG.bottomLabel.setString("Designate your target in the yellow spaces. Press z to confirm your attack.");
								MVMG.changeText1=false;
								while(true){
									boolean fixFor=false;
									if (MVMG.controlBackward==true){
										MVMG.controlBackward=false;
										gameKernel.currentMenuIdent="spellMenu";
										break;
									}
									for (int i=0;i<MVMG.highlightedSpaces.size();i++){
									if (MVMG.controlForward==true&&MVMG.targetSpace==MVMG.highlightedSpaces.get(i)){
										MVMG.controlForward=false;
										gameKernel.currentBattle.getCurrentUnitTurn().useAbilityOnTarget(chosenSpell, MVMG.targetSpace);
										MVMG.observedSpace=MVMG.targetSpace;
										gameKernel.currentMenuIdent="attacking";
										fixFor=true;
										attackConfirmed=true;
										break;
									}
									}
									MVMG.controlForward=false;
									if (fixFor==true){
										attackConfirmed=true;
										break;
									}
								}
							}
							if (MVMG.controlBackward==true){
								MVMG.controlBackward=false;
								attackConfirmed=true;
								break;
							}
					}
					gameKernel.currentMenu=battleOptionsMenu;
					gameKernel.currentPage=0;
					gameKernel.currentMenuIndex=3;
					MVMG.maxRange=0;
					MVMG.minRange=0;
					if (MVMG.highlightedSpaces!=null){
						for(int i = 0; i<MVMG.highlightedSpaces.size();i++){
							MVMG.highlightedSpaces.get(i).isHighlighted=false;
						}
						}
					if (MVMG.highlightedYellow!=null){
						for(int i=0;i<MVMG.highlightedYellow.size();i++){
							MVMG.highlightedYellow.get(i).isHighlighted=false;
						}
					}
					while (gameKernel.currentMenuIdent=="attacking"){
						
					}
					gameKernel.currentMenuIdent="battleOptions";
					MVMG.changeText1=false;
					MVMG.controlBackward=false;
				}
				if (MVMG.controlForward==true&&gameKernel.currentMenuIndex==4){
					MVMG.controlForward=false;
					System.out.println("Selected End Turn");
					gameKernel.getCurrentBattle().nextTurn();
					MVMG.unitIsChanged=false;
					MVMG.changeText1=false;
				}
				if (gameKernel.currentMenuIdent=="freeLook"){
					System.out.println("Freelook mode");
					//Redundant for now until I figure how what to do with this.
					gameKernel.currentMenuIdent="freeLook";
					MVMG.bottomLabel.setString("You are now in freelook mode. Press the arrow keys to navigate, 5 to escape.");
					MVMG.changeText1=false;
					while(MVMG.controlBackward==false&&gameKernel.currentMenuIdent=="freeLook"){
						
					}
					gameKernel.currentMenuIdent="battleOptions";
					MVMG.changeText1=false;
					MVMG.controlBackward=false;
				}
			}
				//Death-checking routine
				for (int i=0;i<gameKernel.currentBattle.getInitList().size();i++){
					if (gameKernel.currentBattle.getInitList().get(i).HP<=0){
						if (gameKernel.currentBattle.getInitList().get(i).isDead==false){
						gameKernel.currentBattle.getInitList().get(i).isDead=true;
						Entity deadEntity = gameKernel.currentBattle.getInitList().get(i);
						deadEntity.associatedSpriteHandler.getShape3D().getAppearance().getRenderingAttributes().setVisible(false);
						gameKernel.currentMap.getEntitiesOnMap().remove(deadEntity);
						gameKernel.currentBattle.getInitList().remove(i);
						}
					}
				}
			}
	}

}
