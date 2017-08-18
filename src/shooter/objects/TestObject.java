package shooter.objects;

import framework.main.GameObject;
import framework.main.SceneManager;
import shooter.scenes.TestScene;

public  class TestObject extends GameObject {
	private TestScene myScene ;

	/**
	 * Constructor for test object.
	 * @param Name The name of this game-object.
	 * @param testScene The scene to test in.
	 */
	public TestObject(String Name,TestScene testScene){
		super(Name) ; 
		this.myScene = testScene ; 
	}

	/**
	 * Update-function for this game-object.
	 * Gets called by the Timer and manages all behavior of this object.
	 */
	public void Update(){
		if (SceneManager.getInstance().getGameObjectByName("Enemy") == null){
			TestScene.EnemyDead();
			this.Destroy(); 
		}
	}


}
