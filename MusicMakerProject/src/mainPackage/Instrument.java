package mainPackage;

import java.util.Random;

public class Instrument {
	
	private String pattern;
	
	public Instrument() {
		// TODO Auto-generated constructor stub
		
	}

	public void playPattern(String actionCommand) {
		// TODO Auto-generated method stub
		
	}

	public void playRandom() {
		Random random = new Random();
		String[] s={"G A A A B D F F C E","F C E B D F F C E","D D D E F F E F A D D D A  A A F F F",
					"G C C  C D E E E D C D E C E E F G G F E F",
					"V0 C5q F#5q CmajQ V1 C3q+E3q E3q+G3q Ri C2majI"};
		pattern=s[random.nextInt(s.length)];
		playPattern(pattern);
		
	}
	
	public String getrandomPattern(){
		return pattern;
	}

}
