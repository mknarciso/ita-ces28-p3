package View;
import java.util.Observable;
import java.util.Observer;

import Framework.Data;

public class StringView implements Observer {
	public void printCharArray(String header, char [][] array) {
		System.out.println(header);
		for (int i=0; i < array.length ; i++)
			System.out.println(new String(array[i]));
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof Data){
			Data dados = (Data)arg;
			printCharArray("*********** turn "+ dados.getTurn() +"     ***********",dados.getBoard());
			}
		
	}
}
