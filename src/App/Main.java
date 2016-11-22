package App;

import Framework.GameEngine;
import Presenter.GamePresenter;
import View.MapView;
import View.StringView;

public class Main {

	public static void main(String[] args) {
		MapView view1 = new MapView();
		StringView view2 = new StringView();
		GameEngine controller = new GameEngine(123);
		GamePresenter presenter = new GamePresenter(3,1,5,100);
		presenter.addModel(controller);
		presenter.addView(view1);
		presenter.addView(view2);
		// Omitindo a Camada View que seria responsável por capturar a interação do usuário (conforme o enunciado)
		presenter.beginGame();
	}

}//class 
