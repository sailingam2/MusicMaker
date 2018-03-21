package mainPackage;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Trumpet extends Instrument{
	private Player player;
	private Pattern p;
	public Trumpet() {
		super();
		player = new Player();
		p=new Pattern().setInstrument("Trumpet");
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
