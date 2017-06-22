package framework.math;

public class MyMath {

	public MyMath() {
		// TODO Auto-generated constructor stub
	}
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
