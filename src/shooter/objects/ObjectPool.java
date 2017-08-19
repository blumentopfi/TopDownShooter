package shooter.objects;

import java.util.ArrayList;
import java.util.List;

import framework.math.Pair;

/**
 * Object Pool for game-objects to increase performance of the game.
 */
class ObjectPool {
	 private final int Size = 60 ;
	 private final List<Pair<Boolean,Explosion>> m_Explosions = new ArrayList<>() ;

	 Explosion getExplosion(){
		 for (Pair<Boolean, Explosion> m_Explosion : m_Explosions) {
			 if (m_Explosion.getFirst()) {
				 m_Explosion.setFirst(false);
				 m_Explosion.getSecond().setActive(true);
				 return m_Explosion.getSecond();
			 }
		 }
		return null ; 
	}
	 void returnExplosionToPool(Explosion e){
		 for (Pair<Boolean, Explosion> m_Explosion : m_Explosions) {
			 if (m_Explosion.getSecond() == e) {
				 m_Explosion.setFirst(true);
				 m_Explosion.getSecond().setActive(false);
				 m_Explosion.getSecond().getAnimator().Reset();
			 }
		 }
	}
	 public void init(boolean SciFiExplo) {
		for (int i = 0 ; i < Size ; i++){
			Explosion e ; 
			if (SciFiExplo){
			e = new ExplosionSciFi() ;
			}else{
			e = new ExplosionPlane() ;
			}
			e.setActive(false);
			m_Explosions.add(new Pair<>(true,e)) ;
			
			
		}
	}
	
}
