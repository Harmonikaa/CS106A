// TODO: comment this file

import java.util.*;
import java.io.*;
import stanford.cs106.audio.*;

public class Melody implements MelodyInterface {

	private String str_Title;
	private String str_Artist;
	private int notes_cnt;
	private Note [] notes;
	private double totalDuration = 0;
	
	public Melody(Scanner input) {
		// TODO: implement this constructor
		str_Title = input.nextLine();
		str_Artist = input.nextLine();
		notes_cnt = Integer.parseInt(input.nextLine());
		notes = new Note[notes_cnt];
		
		int cnt = 0;
		while(input.hasNextLine()){
			notes[cnt] = new Note(input.nextLine());
			++cnt;
		}
		
	}
	
	public void changeDuration(double ratio) {
		// TODO: implement this method
		for(int i=0;i<notes_cnt;++i){
			double duration = notes[i].getDuration();
			duration = duration * ratio;
			notes[i].setDuration(duration);
		}
		totalDuration = totalDuration * ratio;

	}
	
	public String getArtist() {
		// TODO: implement this method
		return str_Artist;
	}

	public String getTitle() {
		// TODO: implement this method
		return str_Title;
	}
	
	public double getTotalDuration() {
		// TODO: implement this method
		if(totalDuration == 0) {
			int repeat_start = 0, repeat_end = 0;
			boolean isRepeated = false;
			for(int i=0; i<notes_cnt; ++i){
				totalDuration += notes[i].getDuration();
				if(notes[i].isRepeat() && !isRepeated){
					isRepeated = true;
					repeat_start = i;
					continue;
				}
				if(notes[i].isRepeat() && isRepeated){
					isRepeated = false;
					repeat_end = i;
					for(int j=repeat_start; j<=repeat_end;++j){
						totalDuration += notes[j].getDuration();
					}
				}
			}
		}
		return totalDuration;
	}
	
	public boolean octaveDown() {
		// TODO: implement this method
		boolean isOctDownAvailable = true;
		for(int i=0;i<notes_cnt;++i){
			if(notes[i].getOctave() == 1){
				isOctDownAvailable = false;
				break;
			}			
		}
		if(isOctDownAvailable){
			for(int i=0;i<notes_cnt;++i){
				int octave = notes[i].getOctave();
				notes[i].setOctave(octave-1);
			}
			return true;
		} else
			return false;
	}
	
	public boolean octaveUp() {
		// TODO: implement this method
		boolean isOctUpAvailable = true;
		for(int i=0;i<notes_cnt;++i){
			if(notes[i].getOctave() == 10){
				isOctUpAvailable = false;
				break;
			}			
		}
		if(isOctUpAvailable){
			for(int i=0;i<notes_cnt;++i){
				int octave = notes[i].getOctave();
				notes[i].setOctave(octave+1);
			}
			return true;
		} else
			return false;
	}
	
	public void play() {
		// TODO: implement this method
		int repeat_start = 0, repeat_end = 0;
		boolean isInRepeat = false;
		
		for(int i=0; i<notes_cnt; ++i){
			notes[i].play();
			if(notes[i].isRepeat() && !isInRepeat){
				isInRepeat = true;
				repeat_start = i;
				continue;
			}
			
			if(notes[i].isRepeat() && isInRepeat){
				isInRepeat = false;
				repeat_end = i;
				for(int j=repeat_start; j<=repeat_end; ++j){
					notes[j].play();
				}
			}
			
		}
		
	}
	
	public void reverse() {
		// TODO: implement this method
		Note buffer;
		for(int i=0; i<notes_cnt/2;++i){
			int j = notes_cnt-1-i;
			buffer = notes[i];
			notes[i] = notes[j];
			notes[j] = buffer;			
		}
		
	}
	
	public String toString() {
		// TODO: implement this method (optional)
		return Arrays.toString(notes);
	}
}
