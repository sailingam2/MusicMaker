package mainPackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.io.*;

import mainPackage.FrameClass;

public class MainClass implements ActionListener{

	String recordedSequence="";
	boolean startRecording=false;
	BufferedWriter out = null;
	String fileName;
	GenericStack<JFrame> framestack = new GenericStack<JFrame>();

	public static void main(String[] args){

		MainClass obj1 =new MainClass();
		obj1.makeGUI();
	}

	private void makeGUI(){
		final FrameClass frame1= new FrameClass();
		final JButton button = new JButton("Start Here");
		final JButton close = new JButton("CLOSE");
		
		JLabel label = new JLabel();
		label.setText("MusicMaker");
		Font labelFont = label.getFont();
		label.setFont(new Font(labelFont.getName(), Font.PLAIN, 60));

		frame1.add(label);
		frame1.add(button);
		frame1.add(close);
		frame1.setVisible(true);
		frame1.setSize(400,400);
		frame1.setTitle("First Frame");
		
		ActionListener actions = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == button){
					
					framestack.push(frame1);
					setUpInstrument();
					frame1.dispose();
				}
				
				if (e.getSource() == close){
					System.exit(0);
				}
			}
		};

		button.addActionListener(actions);
		close.addActionListener(actions);
	}

	private void setUpInstrument(){
		
		final String [] instrumentNames={"Piano","Guitar","Harmonica","Violin","Flute","Gunshot",
				"Bird_Tweet","Clarinet","Sitar","Trumpet","Tuba","Whistle"};

		final int noOfInstruments = instrumentNames.length;

		final JRadioButton ib[] = new JRadioButton[noOfInstruments];

		final String [] notes = {"A","B","C","D","E","F","G","Bb","Db","Eb","Gb","Ab"};

		final int noOfNotes = notes.length;
		
		final JFrame frame2= new JFrame();
		frame2.setVisible(true);
		frame2.setSize(800,400);
		
		Box box1 = Box.createVerticalBox();
		Box box3=Box.createVerticalBox();
		Box box4=Box.createVerticalBox();
		Box box5=Box.createVerticalBox();
		Box box6=Box.createHorizontalBox();
		Box box7=Box.createHorizontalBox();
		Box box8=Box.createHorizontalBox();
		Box box9=Box.createVerticalBox();
		
		final JButton[] note=new JButton[noOfNotes];

		final JButton random= new JButton("Random");

		final JButton record = new JButton("Record");
		final JButton stoprecord = new JButton("Stop Recording");
		final JButton playrecord = new JButton("Play Recording");
		final JButton newrecord = new JButton("Reset Recording");
		final JButton loadsavedtune = new JButton("Load/Save");

		final JButton back=new JButton("back");

		for(int i=0;i<noOfInstruments;i++){
			ib[i]=new JRadioButton(instrumentNames[i]);
			box1.add(ib[i]);
		}
		for(int i=0;i<noOfNotes;i++){
			note[i]=new JButton(notes[i]);
			note[i].setSize(30,40);
		}
/*setting the notes in a vertical order*/		
		for(int i=0;i<4;i++){
		box3.add(note[i]);
		box4.add(note[i+4]);
		box5.add(note[i+8]);
		}
/*setting all the vertically ordered notes in horizontal order*/			
		box6.add(box3);
		box6.add(box4);
		box6.add(box5);
		
		box8.add(box1);
		box8.add(box6);
/*setting all the functional buttons on the top of the frame*/
		box7.add(record);
		box7.add(stoprecord);
		box7.add(playrecord);
		box7.add(newrecord);
		box7.add(loadsavedtune);
		box7.add(random);
		box7.add(back);
/*setting all the boxes into the main box*/	
		box9.add(box7);
		box9.add(box8);
/*setting the box into the frame*/	
		frame2.add(box9);

		ActionListener actions1 = new ActionListener(){
			private String currentInstrument;
			private int noteNumber;
			private Instrument I;

			public void actionPerformed(ActionEvent e){
				for(int i=0;i<noOfNotes;i++){
					if(e.getSource()==note[i]){
						try{
							I.playPattern(e.getActionCommand());
							if(noteNumber == 0){
							addtoSequence("I["+currentInstrument+"]");
							noteNumber++;
							}
							addtoSequence(notes[i]);
						}catch (Exception e1){
							System.err.format("No instrument selected \n");		
						}

					}
				}
				
				for(int i=0;i<noOfInstruments;i++){
					if(e.getSource() == ib[i]){
						for(int j=0;j<noOfInstruments;j++){
							if(j!=i)
								ib[j].setSelected(false);
						}
						frame2.setTitle(instrumentNames[i]);
						I=setSoundButtons(instrumentNames[i]);
						currentInstrument = instrumentNames[i];
						noteNumber = 0;
					}
				}
				
				if (e.getSource() == random){
					
					I.playRandom();
					addtoSequence(I.getrandomPattern());

				}
				
				if (e.getSource() == loadsavedtune){
					
					final FrameClass frame = new FrameClass();
					frame.setDefaultCloseOperation(0);

					JButton saveBtn = new JButton("Save");
					JButton openBtn = new JButton("Open");

					saveBtn.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							JFileChooser saveFile = new JFileChooser();
							saveFile.setCurrentDirectory(new java.io.File("."));
							saveFile.showSaveDialog(null);
							try {
								File file = new File(saveFile.getSelectedFile().getName());
								FileWriter fileWriter = new FileWriter(file);
								fileWriter.write(recordedSequence);
								fileWriter.flush();
								fileWriter.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							frame.dispose();
						}
					});

					openBtn.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							JFileChooser openFile = new JFileChooser();
							openFile.setCurrentDirectory(new java.io.File("."));
							int returnVal=openFile.showOpenDialog(null);
							if(returnVal == JFileChooser.APPROVE_OPTION) {
								
								fileName = openFile.getSelectedFile().getName();
								recordedSequence = getTune(fileName);
								System.out.println("the loaded sequence is "+recordedSequence);
							}
							frame.dispose();
						}
					});

					frame.add(new JLabel("File Chooser"));
					frame.add(saveBtn);
					frame.add(openBtn);
					frame.setTitle("File Chooser");
					frame.pack();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);

				}
				
				if (e.getSource() == record){
					startRecording = true;
				}
				
				if (e.getSource()==back){
					
					FrameClass frame1 = (FrameClass) framestack.pop();
					frame2.dispatchEvent(new WindowEvent(frame2, WindowEvent.WINDOW_CLOSING));
					frame1.setVisible(true);
				}
				
				if (e.getSource() == stoprecord){
					
					startRecording = false;
				}
				
				if (e.getSource()== playrecord){
					
					I.playPattern(recordedSequence);
				}
				
				if (e.getSource() == newrecord){
					
					recordedSequence="";
				}
			}
		};
		/*assigns action handlers to the buttons*/
		for(int i=0;i<noOfNotes;i++){
			note[i].addActionListener(actions1);
		}
		for(int i=0;i<noOfInstruments;i++){
			ib[i].addActionListener(actions1);
		}
		random.addActionListener(actions1);
		record.addActionListener(actions1);
		stoprecord.addActionListener(actions1);
		playrecord.addActionListener(actions1);
		newrecord.addActionListener(actions1);
		loadsavedtune.addActionListener(actions1);

		back.addActionListener(actions1);
	}
/*reads the tune from the file selected*/
	private String getTune(String fileName) {
		String line;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			line = reader.readLine();
			reader.close();
			return line;
		}catch (Exception e){
			System.err.format("Exception occurred trying to read '%s'.", fileName);
			e.printStackTrace();
			return null;			
		}
	}
/*This function will setup the instrument depending on the user's choice*/
	private Instrument setSoundButtons(String s){

		Instrument I = null;

		switch(s){
		case "Piano":I = new Piano();
		break;
		case "Guitar":I = new Guitar();
		break;
		case "Harmonica":I = new Harmonica();
		break;
		case "Violin":I = new Violin();
		break;
		case "Flute":I = new Flute();
		break;
		case "Gunshot":I = new Gunshot();
		break;	
		case "Bird_Tweet":I = new Bird_Tweet();
		break;
		case "Clarinet":I = new Clarinet();
		break;
		case "Sitar":I = new Sitar();
		break;
		case "Trumpet":I = new Trumpet();
		break;
		case "Tuba":I = new Tuba();
		break;
		case "Whistle":I = new Whistle();
		break;
		}
		return I;
	}

	private void addtoSequence(String note){
		if(startRecording)
			recordedSequence+=note+" ";
		System.out.println(recordedSequence);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
	}
}