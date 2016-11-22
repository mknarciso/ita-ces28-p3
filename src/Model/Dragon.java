package Model;

import Framework.GameEntity;

public class Dragon extends GameEntity {

	private static int count = 0;

	private void init() {
		name = "Smaug"+ count;
		type_ = "Dragon";
		count++;
		setMax_move(4);
	}
	
	public Dragon(int x, int d) {
		super(x,d);
		init();
	}
	public Dragon(int x) {
		super(x);
		init();
	}
	
}//dragon
