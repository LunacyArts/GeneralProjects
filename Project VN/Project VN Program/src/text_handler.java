import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class text_handler {
	String[] Scene_Text;
	String Current_Line;
	public text_handler(){
		
	}

	public void display_text(){
		
	}
	
	public void cut_scene_text(int line){
		System.out.println(Scene_Text[line]);
	}
		
	public boolean load_file(String path, int scenes) throws IOException {
	    Scene_Text = new String[scenes];
	    BufferedReader input = new BufferedReader(new FileReader(path));
	    for(int i = 0; i < scenes; i++){
	    	Scene_Text[i] = input.readLine();
	    }
	    input.close();
		return true;
	}
}