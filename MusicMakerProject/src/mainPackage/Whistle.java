package mainPackage;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Whistle extends Instrument{
	private Player player;
	private Pattern p;
	public Whistle() {
		super();
		player = new Player();
		p=new Pattern().setInstrument("Whistle");
		// TODO Auto-generated constructor stub
	}
	
	public void playPattern(String pattern){
		p.prepend(pattern);
		player.play(p);
		p.clear();
	}
	
	public void playRandom(){

		super.playRandom();
	}
}
