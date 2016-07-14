import javax.swing.JFrame;

public class Start {

	public static void main(String[] args) {
		text_handler text_hndl = new text_handler();
		cut_scene scene_hndl = new cut_scene();
		JFrame frame = new JFrame("My Game Window");
		game game = new game(text_hndl, frame, scene_hndl);
		game.new_game();
	}
}
