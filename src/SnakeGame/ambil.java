package SnakeGame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// Inheritance
public class ambil extends loadImages {
	private static String cepetan =  "./tut.wav";

	public ambil() {
		
	}

	public static void gambar() {
		loadGambar();
	}

	public static Clip LoadSound() {
		String direction = cepetan;
		// exception handling
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(ambil.class.getResource(direction)));
			return clip;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	
}


