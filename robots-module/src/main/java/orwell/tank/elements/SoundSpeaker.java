package orwell.tank.elements;

import lejos.nxt.Sound;
import orwell.tank.actions.Fire;
import orwell.tank.actions.Move;
import orwell.tank.actions.StopProgram;
import orwell.tank.actions.StopTank;

import java.util.Queue;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public class SoundSpeaker {
    private static final int VOLUME = 75;
    private static final int G5_FREQ = 784;
    private static final int G3_FREQ = 196;
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
            playTone(250, 100);
        if (fire.hasRightWeaponFired()) // bigger weapon
            playTone(150, 150);
    }

    public void playActionTone(StopProgram stopProgram) {
        for (int i = 4; i < 12; i++)
        {
            playTone(G5_FREQ * i / 4, 100);
            playTone(10000, 100);
        }
    }

    public void playActionTone(StopTank stopTank) {
        for (int i = 4; i < 12; i++)
        {
            playTone(G3_FREQ * i / 4, 100);
            playTone(10000, 100);
        }
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
