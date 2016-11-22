package Presenter;

import java.util.HashMap;
import java.util.Map;

import Framework.GameEngine;
import Framework.GameEntity;
import Model.Dragon;
import Model.Knight;
import Model.Princess;
import View.MapView;
import View.StringView;

public class GamePresenter {
	// Has access to models and Views
	private int number_knights;
	private int number_princesses;
	private int number_dragons;
	private int number_turns;
	private MapView view1;
	private StringView view2;
	private GameEngine game;
	// brought here to control the models in game
	private Map<String,GameEntity> game_entities_ = new HashMap<String,GameEntity>();
	public GamePresenter(int knights, int princess, int dragons, int turns){
		number_knights = knights;
		number_princesses= princess;
		number_dragons=dragons;
		number_turns=turns;
	}
	
	public void beginGame() {
		for (int i=0; i < number_knights; i++) {
			Knight k = new Knight(i);
			register(k.getName(),k);
		}
		for (int i=0; i < number_princesses; i++) {
			Princess p = new Princess(i+5);
			register(p.getName(),p);
		}
		for (int i=0; i < number_dragons; i++) {
			Dragon d = new Dragon(i+10);
			register(d.getName(),d);
		}

		for (int i=0; i < number_turns ; i++)
			game.gameTurn(game_entities_);
	}
	
	public void register(String name, GameEntity o){ // problem: it is wrong to accept any object!!!
		game_entities_.put(name, o);
	}

	public void addModel(GameEngine controller) {
		game = controller;
		
	}

	public void addView(StringView view22) {
		view2 = view22;
		game.addObserver(view2);
	}
	public void addView(MapView view11) {
		view1 = view11;
		game.addObserver(view1);
	}
}
