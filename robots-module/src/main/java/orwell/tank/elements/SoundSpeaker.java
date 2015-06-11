package orwell.tank.elements;

import lejos.nxt.Sound;
import orwell.tank.IActionVisitor;
import orwell.tank.actions.Fire;
import orwell.tank.actions.Move;

import java.util.Queue;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public class SoundSpeaker {
    private static final int VOLUME = 75;
    private SoundPlayer player;
    private boolean shouldContinue;
    private Queue<Tone> toneQueue;

    public SoundSpeaker() {
        toneQueue = new Queue<>();
        startSoundSpeaker();
    }

    private void startSoundSpeaker() {
        if (! shouldContinue) {
            player = new SoundPlayer();
            player.setDaemon(true);
            shouldContinue = true;
            player.start();
        }
    }

    public void stopSoundSpeaker() {
        shouldContinue = false;
    }

    public void playTone(int frequency, int duration) {
        toneQueue.addElement(new Tone(frequency, duration, VOLUME));
    }

    public void playActionTone(Move move) {
        playTone(700, 50);
    }

    public void playActionTone(Fire fire) {
        if (fire.hasLeftWeaponFired())
            playTone(150, 100);
        if (fire.hasRightWeaponFired())
            playTone(250, 150);
    }

    private class SoundPlayer extends Thread {

        @Override
        public void run() {
            while(shouldContinue()) {
                playNextSound();
            }
        }

        private void playNextSound() {
            if (!toneQueue.empty()) {
                Tone tone = (Tone) toneQueue.pop();
                Sound.playTone(tone.getFrequency(), tone.getDuration(), tone.getVolume());
            }
        }
    }

    private boolean shouldContinue() {
        return shouldContinue;
    }
}
