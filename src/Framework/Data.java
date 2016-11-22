package Framework;

public class Data {
	private int _turn;
	private char board[][] = new char[4][20];
	public void set(int turn,char [][]board){
		this._turn = turn;
		this.board = board;
	}
	public int getTurn(){
		return _turn;
	}
	public char[][] getBoard(){
		return board;
	}
}
