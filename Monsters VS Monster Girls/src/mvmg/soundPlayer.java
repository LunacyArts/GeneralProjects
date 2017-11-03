package mvmg;

import java.io.*;
import javax.sound.midi.*;
public class soundPlayer {
	UI ui;
	static Sequencer playerSequencer;
	static MidiDevice selectedMidiDevice;
	static MidiDevice backupMidiDevice;
	static Transmitter selectedTransmitter;
	static Receiver selectedReceiver;
	static Synthesizer selectedSynth;
	static MidiDevice.Info[] midiDeviceInfo;
	public soundPlayer(UI ui){
		this.ui=ui;
		try {
			selectedMidiDevice=null;
			midiDeviceInfo = MidiSystem.getMidiDeviceInfo();
			for (int i = 0;i<midiDeviceInfo.length;i++){
				MVMG.printLn(midiDeviceInfo[i].toString());
				if (midiDeviceInfo[i].getName().equals("VirtualMIDISynth #1")){
					selectedMidiDevice=MidiSystem.getMidiDevice(midiDeviceInfo[i]);
				}
				if (midiDeviceInfo[i].getName().equals("Microsoft GS Wavetable Synth")){
					backupMidiDevice=MidiSystem.getMidiDevice(midiDeviceInfo[i]);
				}
			}
			if (selectedMidiDevice==null){
				selectedMidiDevice=backupMidiDevice;
			}
			selectedMidiDevice.open();
			selectedReceiver = selectedMidiDevice.getReceiver();
			playerSequencer = MidiSystem.getSequencer(false);
			playerSequencer.getTransmitter().setReceiver(selectedReceiver);
			playerSequencer.open();
		} catch (MidiUnavailableException e) {
			MVMG.printStackTrace(e);
			playerSequencer=null;
		}
		if (backupMidiDevice!=null&&playerSequencer==null){
			selectedMidiDevice=backupMidiDevice;
			try {
				selectedMidiDevice.open();
				selectedReceiver = selectedMidiDevice.getReceiver();
				playerSequencer = MidiSystem.getSequencer(false);
				playerSequencer.getTransmitter().setReceiver(selectedReceiver);
				playerSequencer.open();
			}
			catch (MidiUnavailableException e){
				MVMG.printStackTrace(e);
				playerSequencer=null;
			}
		}
		if (playerSequencer!=null){
			MVMG.printLn("Selected MIDI device: "+selectedMidiDevice.getDeviceInfo());
		}
		
	}
	
	//Plays a midi file once.
	void playMIDI(File midi){
		if (playerSequencer!=null){
			InputStream iStream = null;
			try {
				iStream = new BufferedInputStream(new FileInputStream(midi));
			} catch (FileNotFoundException e) {
				MVMG.printStackTrace(e);
			}
			if (iStream!=null){
				try {
					playerSequencer.setSequence(iStream);
					playerSequencer.start();
				} catch (IOException e) {
					MVMG.printStackTrace(e);
				} catch (InvalidMidiDataException e) {
					
				}
			}
		}
	}
	
	//Loops a midi file the specified number of times.
	void playMIDI(File midi, int numOfLoops){
		if (playerSequencer!=null){
			InputStream iStream = null;
			try {
				iStream = new BufferedInputStream(new FileInputStream(midi));
			} catch (FileNotFoundException e) {
				MVMG.printStackTrace(e);
			}
			if (iStream!=null){
				try {
					playerSequencer.setSequence(iStream);
					playerSequencer.setLoopCount(numOfLoops);
					playerSequencer.start();
				} catch (IOException e) {
					MVMG.printStackTrace(e);
				} catch (InvalidMidiDataException e) {
					
				}
			}
		}
	}
	
	//Loops a midi file the specified number of times, starting and stopping at the specified start and end positions
	//Remember that an endTick of 0 has no effect, and an endTick of -1 is the very last tick
	void playMIDI(File midi, int numOfLoops, long startTick, long endTick){
		if (playerSequencer!=null){
			InputStream iStream = null;
			try {
				iStream = new BufferedInputStream(new FileInputStream(midi));
			} catch (FileNotFoundException e) {
				MVMG.printStackTrace(e);
			}
			if (iStream!=null){
				try {
					playerSequencer.setSequence(iStream);
					playerSequencer.setLoopStartPoint(startTick);
					playerSequencer.setLoopEndPoint(endTick);
					playerSequencer.setLoopCount(numOfLoops);
					playerSequencer.start();
				} catch (IOException e) {
					MVMG.printStackTrace(e);
				} catch (InvalidMidiDataException e) {
					
				}
			}
		}
	}

}
