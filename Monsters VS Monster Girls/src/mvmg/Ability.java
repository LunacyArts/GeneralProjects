package mvmg;

import java.util.ArrayList;
/*An ability is anything that can be used as either an attack, ability, or spell. Weapons will be found
 * in the Attack section, Abilities will be found in the Ability section, and Spells will be found
 * in the Spell section. All Abilities are linked by having attributes in common with other Abilities.
 * These include the subclasses of Abilities.*/
public class Ability {
	String name;
	String desc;
	//physical or magical
	public static final int PHYSICAL = 0;
	public static final int MAGICAL = 1;
	int type = PHYSICAL;
	public static final int BLUNT = 0;
	public static final int PIERCING = 1;
	public static final int SLASHING = 2;
	public static final int EVOCATIONAL = 3;
	int damageType = BLUNT;
	//None, Fire, Ice, Electricity, Acid, Sound, Holy, Unholy, Light, Dark
	//The two ArrayLists below are linked.
	ArrayList<String> damageElements = new ArrayList<String>();
	ArrayList<Double> bonusDamage = new ArrayList<Double>();
	//Most abilities are found in the Ability section.
	String mainDamageElement = "None";
	ArrayList<String> abilityDescriptors = new ArrayList<String>();
	boolean treatAsAttack=false;
	boolean divineAbility=false;
	ArrayList<Effect> abilityEffects = new ArrayList<Effect>();
	ArrayList<spaceEffect> abilitySpaceEffects = new ArrayList<spaceEffect>();
	//baseRange determines the base range, in number of squares, to designate the target for the ability.
	double baseMinRange=0;
	double baseMaxRange=1;
	//baseArea determines how many squares from the target that the ability affects.
	double baseMinArea=0;
	double baseMaxArea=0;
	//baseStrength determines how much damage (or healing) the ability normally deals.
	double baseStrength;
	//baseMagnitude is only used for spells in most cases.
	double baseMagnitude;
	//An ability with a cooldown cannot be used again for a number of rounds equal to the double below
	//after it has been used.
	double baseCooldown = 0;
	double baseMPCost = 0;
	double baseDPCost = 0;
	double baseHPCost = 0;
	public Ability (String name,String desc,int type){
		this.name=name;
		this.desc=desc;
		this.type=type;
	}
	void setAbilityStats(String mainDamageElement,int damageType,double baseMinRange,double baseMaxRange,double baseMinArea,double baseMaxArea,double baseStrength, double baseMagnitude){
		this.mainDamageElement=mainDamageElement;
		this.damageType=damageType;
		this.baseMinRange=baseMinRange;
		this.baseMaxRange=baseMaxRange;
		this.baseMinArea=baseMinArea;
		this.baseMaxArea=baseMaxArea;
		this.baseStrength=baseStrength;
		this.baseMagnitude=baseMagnitude;
	}
}
