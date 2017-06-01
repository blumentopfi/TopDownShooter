package framework.components;

public abstract class Component {
	/**
	 * Mother Class for all Components
	 */
	protected Component(){
	}
	/**
	 * Function if the Component should do something every game Tick
	 */
	public abstract void ComponentUpdate() ; 
}
