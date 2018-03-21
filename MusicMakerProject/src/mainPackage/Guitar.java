package mainPackage;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Guitar extends Instrument{
	private Player player;
	private Pattern p;
	public Guitar() {
		super();
		player = new Player();
		p=new Pattern().setInstrument("Guitar");
		// TODO Auto-generated constructor stub
	}
	
	public void playPattern(String pattern){
		super.playPattern(pattern);
		p.prepend(pattern);
		player.play(p);
		p.clear();
	}
	
public void playRandom(){
		
		super.playRandom();
	}
}
