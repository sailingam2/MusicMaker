package mainPackage;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Clarinet extends Instrument{
	private Player player;
	private Pattern p;
	public Clarinet() {
		super();
		player = new Player();
		p=new Pattern().setInstrument("Clarinet");
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
