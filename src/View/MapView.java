package View;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Framework.GameEntity;

public class MapView implements Observer{
	public void printAllEntities(Map<String,GameEntity> entities){
		// debug. print all entities.
		System.out.println("------------------------- ");
		for (GameEntity ent : entities.values()) {
			
			System.out.println(ent);
			
		}//for
	}//printAllEntities

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Map<?,?>){
			printAllEntities((Map<String, GameEntity>) arg);
			}
		
	}
}
