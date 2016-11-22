package Model;

import Framework.GameEntity;

public class Princess extends GameEntity {
	
	

	private static int count = 0;

	private void init() {
		name = "Her Highness"+ count;
		type_ = "Princess";
		count++;
		setMax_move(1);
	}
	
	public Princess(int x, int d) {
		super(x,d);
		init();
	}
	public Princess(int x) {
		super(x);
		init();
	}
	
}//princess


