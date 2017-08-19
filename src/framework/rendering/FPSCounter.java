package framework.rendering;
/**
 * Quick  Class to count our FPS
 * @author Fin
 *
 */
class FPSCounter extends Thread{
    private double fps; //could be int or long for integer values
    private int Counter ; 
    private double checkfps ; 

    public void run(){
        while (!this.isInterrupted()){
            long lastTime = System.nanoTime();
            try{
                Thread.sleep(1000); // longer than one frame
            }
            catch (InterruptedException ignored){
            }
            checkfps += 1000000000.0 / (System.nanoTime() - lastTime); //one second(nano) divided by amount of time it takes for one frame to finish
            lastTime = System.nanoTime();
            Counter++ ; 
            if (Counter >= 60){
            	
            	fps = checkfps/60 ; 
            	checkfps = 0 ; 
            	Counter = 0; 
            }
        }
    }
    public double fps(){
        return fps;
    } 
}
