import java.io.IOException;

public class cut_scene {
	public cut_scene(){}
	
	public boolean load_scene(String fldr){
		String path = "assests/" + fldr;
		String dia = path + "/dia.txt";
		String img = path + "/img.txt";
		try {
			text_hndl.load_file(dia, 4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	public void play_scene(){
		
	}
}