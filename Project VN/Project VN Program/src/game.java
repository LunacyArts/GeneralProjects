import java.io.IOException;
import javax.swing.JFrame;

public class game {
	text_handler text_hndl;
	JFrame game_frame;
	cut_scene scene_hndl;
	public game(text_handler text, JFrame frame, cut_scene scene){
		text_hndl = text;
		game_frame = frame;
		scene_hndl = scene;
	}
	public void new_game() {
		
	}
}
