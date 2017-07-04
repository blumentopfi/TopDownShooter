package framework.components;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import shooter.options.Options;
public class Audio extends Component {
	Map<String,Clip> Sounds = new HashMap<String,Clip>() ; 
	private String path ; 
	public Audio(){
		super() ; 
	}
	@Override
	public void ComponentUpdate() {
	}
	public void AddSound(String Name, String PathToSound) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream audio =  AudioSystem.getAudioInputStream(new File(PathToSound).getAbsoluteFile());
		Clip c = AudioSystem.getClip() ; 
		c.open(audio);
		Sounds.put(Name, c) ; 
	}
	public boolean PlaySound(String name){
		if (!Options.getMuted()){
		Clip c;
		c = Sounds.get(name) ;
		if (c.isActive()) return false ; 
		Sounds.get(name).setMicrosecondPosition(0);
		Sounds.get(name).start();
		}
		return true ; 
	}

}
