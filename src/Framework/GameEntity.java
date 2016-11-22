package Framework;

public abstract class GameEntity {

	@Override
	public String toString() {
		return "[ "+ type_ + " " + name + " x=" + x + " d=" + d + "]";
	}
	
	protected String type_;
	public String getType() {
		return type_;
	}

	protected String name;
	protected int x;
	protected int d;
	protected int max_move;
	
	public int getMax_move() {
		return max_move;
	}

	protected void setMax_move(int max_move) {
		this.max_move = max_move;
	}

	private void init(int x, int d) {
		setX(x);
		setD(1);
		name = "undefined name";
		type_ = "undefined type";
		setMax_move(0);// can not move unless enabled by subclass
	}
	
	protected GameEntity (int x) {
		init(x,1);
	}
	protected GameEntity (int x, int d) {
		init(x,d);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		if (d == 0) d = 1;
		this.d = d;
	}
	
	public void reverse() {
		d = -d;
	}
	
	public String getName() {
		return name;
	}
	
	
}
