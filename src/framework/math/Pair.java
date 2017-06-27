package framework.math;

public class Pair<T,Z> {
		T FirstComponent ; 
		Z SecondComponent ; 
		public Pair(T e, Z i){
			this.FirstComponent = e ; 
			this.SecondComponent = i ; 
		}
		public T getFirst(){
			return this.FirstComponent ; 
		}
		public Z getSecond(){
			return this.SecondComponent ; 
		}
		public void setFirst(T e){
			this.FirstComponent = e ; 
		}
		public void setSecond(Z e){
			this.SecondComponent = e ; 
		}
}
