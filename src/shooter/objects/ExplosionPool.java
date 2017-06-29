package shooter.objects;

import java.util.ArrayList;
import java.util.List;

import framework.math.Pair;

public  class ExplosionPool {
	static private int Size = 60 ; 
	static private List<Pair<Boolean,Explosion>> m_Explosions = new ArrayList<Pair<Boolean,Explosion>>() ; 
	static private List<Boolean> m_readyStates = new ArrayList<Boolean>() ; 
	
	static public Explosion getExplosion(){
		for (int i = 0 ; i < m_Explosions.size();i++ ){
			if (m_Explosions.get(i).getFirst()){
				m_Explosions.get(i).setFirst(false);
				m_Explosions.get(i).getSecond().setActive(true);
				return m_Explosions.get(i).getSecond() ; 
			}
		}
		return null ; 
	}
	static public void returnExplosionToPool(Explosion e ){
		for (int i = 0 ; i < m_Explosions.size() ; i++){
			if (m_Explosions.get(i).getSecond() == e){
				m_Explosions.get(i).setFirst(true);
				m_Explosions.get(i).getSecond().setActive(false);
				m_Explosions.get(i).getSecond().myAnimator.Reset();
			}
		}
	}
	static public void init() {
		for (int i = 0 ; i < Size ; i++){
			Explosion e = new Explosion("Explo") ;
			e.setActive(false);
			m_Explosions.add(new Pair<Boolean,Explosion>(true,e)) ; 
			
			
		}
	}
	
}
