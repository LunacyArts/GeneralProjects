
public class SpriteMapSection {
	//The start positions refer to where, from the top left of the sprite map,
	//the offset is to begin selection of a section of the sprite map.
	int startPositionX;
	int startPositionY;
	//The width and height here is how many columns or rows from the start position
	//will be copied.
	int width;
	int height;
	public SpriteMapSection(int startX, int startY, int width, int height){
		this.startPositionX=startX;
		this.startPositionY=startY;
		this.width=width;
		this.height=height;
	}
}
