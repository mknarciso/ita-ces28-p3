package Framework;

//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

public class GameEngine extends Observable {

	int turn_;
	private Map<String,GameEntity> game_entities_;
	private Random rand;
	private ArrayList<String> commands_;
	private int board_limit;
	Set<String> markedToRemoval;
	private Data estado = new Data();

	public void markToRemoval(String name) {
		
	}
	
	private void init() {
		turn_=1;
		rand = new Random();
		commands_ = new ArrayList<String>();
		commands_.add("MOVE");
		commands_.add("NONE");
		commands_.add("REVERSE");
		commands_.add("FIRE");
		board_limit=20;
		markedToRemoval = new HashSet<String>();
	}
	
	public GameEngine() { init(); }
	
	public GameEngine(int seed) {
		init(); rand.setSeed(seed);
	}
	
	public int isOutsideBoard(int x) {
		if (x < 0) return -1;
		else if (x >= board_limit) return 1;
		else return 0;
	}
	public int getMinX() { return 0; }
	public int getMaxX() { return board_limit-1; }
	
	public int forceInsideBoard(int x ) {
		if (isOutsideBoard(x) >0) { return getMaxX(); } 
		else if (isOutsideBoard(x) < 0) { return getMinX(); } 
		else return x;
	}
	
	public void beforeTurn(Map<String,GameEntity> entities) {
		turn_++;
		markedToRemoval.clear();
		game_entities_ = entities;
	}
	public void afterTurn() {
		for (String s : markedToRemoval) { // kill all those doomed to die
			game_entities_.remove(s);
		}
	}
	// Model não acessa o controlador em nenhum momento, recebe todos os dados necessários como parametros
	public void gameTurn(Map<String,GameEntity> entities) {
		beforeTurn(entities);
		
		int fired_position[] = new int[board_limit];
		
		for (GameEntity ent : game_entities_.values()){
			String cmd = commands_.get(rand.nextInt(commands_.size()));
						
			if (cmd.equals("NONE")) continue;
			
			if (cmd.equals("REVERSE")) {		 
				ent.reverse();
			}
			
			if (cmd.equals("MOVE")) {
				ent.setX( ent.getX() + Integer.signum(ent.getD()) * rand.nextInt(ent.getMax_move()+1) );
				// check if it is outside of the board limit
				if (isOutsideBoard(ent.getX()) > 0) { ent.setX(getMaxX()); ent.reverse(); } else if (isOutsideBoard(ent.getX()) < 0) { ent.setX(getMinX()); ent.reverse(); }

			}
			
			if (cmd.equals("FIRE")  && ent.getType().equals("Dragon") ) {
				int position = ent.getX() + Integer.signum(ent.getD());
				if (isOutsideBoard(position) == 0)
					fired_position[position] = 1;; // fire in the next position, ahead of the dragon
			}
		}//for
		
		//////////////////// prepare the game state to print
		
				char board[][] = new char[4][board_limit];
				for (int l=0; l<4; l++)
					for (int c=0; c<board_limit; c++)
						board[l][c]='.'; 
				
				
				for (GameEntity ent : game_entities_.values()) {
					int line = 0;
					if (ent.getType().equals("Knight")) line = 2; 
					else if (ent.getType().equals("Princess")) line = 3;
					else if (ent.getType().equals("Dragon")) line = 0;
					else System.out.println("WARN: entity with no known type: " + ent);
					
					board[line][ent.getX()] = ent.getType().charAt(0);
				}
				for (int i=0; i < board_limit; i++){
					if (fired_position[i] > 0)
						board[1][i] = 'F';
				}
				/// ////////// print game state
				//printCharArray("*********** turn "+ turn_ +"     ***********",board);

				setChanged();
				estado.set(turn_, board);
				notifyObservers(estado);
		
		
		// end of actions, now need to update the game state.
		
		for (GameEntity ent : game_entities_.values()) { // can not actually remove elements during this loop.
				
			
			if (ent.getType().equals("Knight")) {
				
				
				// check if this knight has found fire. the knight is toast!!!
				if (fired_position[ent.getX()] > 0 ) {
					System.out.println("Unfortunatly, "+ ent.getName()+ " was killed by a Dragon. May his brave soul rest in peace !!");
					markedToRemoval.add(ent.getName());
				}
				
				for (GameEntity ent2 : game_entities_.values()) {
					if (ent2.getType().equals("Dragon")) {// knight finds dragon. dragon dies!
						if (ent2.getX() == ent.getX()) {
							System.out.println("The brave "+ ent.getName()+ " has killed the Dragon "+ ent2.getName()+". May all the kingdom rejoice !!");
							markedToRemoval.add(ent2.getName());
						}
					}
					if (ent2.getType().equals("Princess")) {
						if (ent.getX() == ent2.getX()) {// knight finds princess, saves her!
							System.out.println("The brave "+ ent.getName()+ " has saved the Princess "+ ent2.getName()+". May they live happy ever after !!");
							markedToRemoval.add(ent.getName());
							markedToRemoval.add(ent2.getName());						
						}
					}
					
				}// internal for
				
				
			} else if (ent.getType().equals("Princess")) {
				// already covered, nothing more to do
			} else if (ent.getType().equals("Dragon")) {
				// already covered, nothing more to do
			}
			
			
		}//external for
		setChanged();
		notifyObservers(game_entities_);
		afterTurn();
			
				
	}// gameTurn
	

	
	
	

	
}//class 
