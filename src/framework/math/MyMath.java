package framework.math;

/**
 * Little POD to hold math functions
 * @author Fin
 *@version 1.0
 */
public class MyMath {

	/**
	 * Clamp the Value between Min and Max
	 * @param UpperBounds Upper Bounds of the range
	 * @param LowerBounds Lower Bounds of the range
	 * @param value value to clamp
	 * @return Min when value < LowerBounds Max when value > UpperBounds otherwise value
	 */
	public static float Clamp (float UpperBounds,float LowerBounds,float value){
		if (value < LowerBounds){
			return LowerBounds ; 
		}
		if (value > UpperBounds){
			return UpperBounds ; 
		}
		return value ;
		
	}

}
