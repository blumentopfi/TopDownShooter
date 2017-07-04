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
/**
 * The component used for audio output of an object.
 * @author Fin
 * @version 1.0
 */
public class Audio extends Component {
	Map<String,Clip> Sounds = new HashMap<String,Clip>() ; //Sounds we hold
	/**
	 * Just inializes it
	 */
	public Audio(){
		super() ; 
	}
	/**
	 * Adds the desired Sound with a Path To a File and a Name so we can call it later
	 * @param Name
	 * @param PathToSound
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void AddSound(String Name, String PathToSound) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream audio =  AudioSystem.getAudioInputStream(new File(PathToSound).getAbsoluteFile());
		Clip c = AudioSystem.getClip() ; 
		c.open(audio);
		Sounds.put(Name, c) ; 
	}
	/**
	 * Plays the before desired Sound, 
	 * returns true if all goes well, 
	 * returns false if the Sound is already playing
	 * @param name
	 * @return
	 */
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
	@Override
	public void ComponentUpdate() {
		//Stub because the Audio doesn't need ComponentUpdates 
		
	}

}
