package framework.components;
import shooter.options.Options;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * The component used for audio output of an object.
 * @author Fin
 * @version 1.0
 */
public class Audio extends Component {
	private final Map<String,Clip> Sounds = new HashMap<>() ; //Sounds we hold
	/**
	 * Just initializes it
	 */
	public Audio(){
		super() ; 
	}
	/**
	 * Adds the desired Sound with a Path To a File and a Name so we can call it later
	 * @param Name Name of the sound
	 * @param PathToSound Path to the sound file
	 * @throws UnsupportedAudioFileException Wrong File-Format.
	 * @throws IOException	File not found.
	 * @throws LineUnavailableException Line Unavailable.
	 */
	public void AddSound(String Name, String PathToSound) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream audio =  AudioSystem.getAudioInputStream(this.getClass().getResource(PathToSound));
		
		Clip c = AudioSystem.getClip() ; 
		c.open(audio);
		Sounds.put(Name, c) ; 
	}
	/**
	 * Plays the before desired Sound, 
	 * returns true if all goes well, 
	 * returns false if the Sound is already playing
	 * @param name Name of the Sound you want to play
	 * @return True if sound played.
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
