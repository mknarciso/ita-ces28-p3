package Model;

import Framework.GameEntity;

public class Knight extends GameEntity {
	

	private static int count = 0;

	private void init() {
		name = "Sir John"+ count;
		type_ = "Knight";
		count++;
		setMax_move(2);
	}
	
	public Knight(int x, int d) {
		super(x,d);
		init();
	}
	public Knight(int x) {
		super(x);
		init();
	}
	
}
