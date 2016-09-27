import java.io.IOException;

import javax.swing.JFrame;

import threads.image_thread;
import threads.sound_thread;

public class game {
	text_handler text_hndl;
	JFrame game_frame;
	cut_scene scene_hndl;
	public game(text_handler text_hndl, cut_scene scene_hndl,
			sound_thread snd_thrd, image_thread img_thrd, JFrame frame) {
		text_hndl = text;
		game_frame = frame;
		scene_hndl = scene;
	}
	public void new_game() {
		
	}
}
