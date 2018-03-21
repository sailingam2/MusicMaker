package mainPackage;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Tuba extends Instrument{
	private Player player;
	private Pattern p;
	public Tuba() {
		super();
		player = new Player();
		p=new Pattern().setInstrument("Tuba");
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
