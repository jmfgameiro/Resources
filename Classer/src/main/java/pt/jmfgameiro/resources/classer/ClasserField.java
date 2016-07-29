package pt.jmfgameiro.resources.classer;

public final class ClasserField {
	
	/***** CONSTANTS *****/
	private final Class< ? > clazz;
	private final int modifier;
	
	
	/***** CONSTRUCTOR *****/
	public ClasserField( Class< ? > clazz, int modifier ) {
		this.clazz = clazz;
		this.modifier = modifier;
	}
	
	
	/***** GETTERS *****/
	public Class< ? > getClazz() {
		return this.clazz;
	}
	public int getModifier() {
		return this.modifier;
	}
	
	
}
