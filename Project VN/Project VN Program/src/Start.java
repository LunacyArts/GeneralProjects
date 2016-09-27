import javax.swing.JFrame;

import threads.image_thread;
import threads.sound_thread;

public class Start {

	public static void main(String[] args) {
		
		/*-----Not Sure if I want to continue using these-----*/
		//Start Script Handlers
		text_handler text_hndl = new text_handler();
		cut_scene scene_hndl = new cut_scene();
		/*----------------------------------------------------*/
		
		//Start Threads to control audio and visuals
		sound_thread snd_thrd = new sound_thread();
		image_thread img_thrd = new image_thread();
		
		//Create the window to control game
		JFrame frame = new JFrame("My Game Window");
		
		//Start the game class and send Frame, Threads, and Script Handlers
		game game = new game(text_hndl, scene_hndl, snd_thrd, img_thrd, frame);
		game.new_game();
	}
}
