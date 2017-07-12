package shooter.options;

public class Options {
static boolean Muted = true ;
static public void ToggleAudio(){
	Muted = !Muted ; 
}
static public boolean getMuted(){
	return Muted ; 
}
}
