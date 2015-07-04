package orwell.tank.elements;

import lejos.nxt.Sound;
import orwell.tank.inputs.Fire;
import orwell.tank.inputs.Move;

import java.util.Queue;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public class SoundSpeaker {
    private static final int VOLUME = 25;
    private static final int G5_FREQ = 784;
    private static final int G4_FREQ = 392;
    private static final int G3_FREQ = 196;
    private static final int G2_FREQ = 98;
    private static final int C4_FREQ = 262;
    private static final int C2_FREQ = 65;
    private static final int D4_FREQ = 294;
    private static final int E4_FREQ = 330;
    private SoundPlayer player;
    private volatile boolean shouldContinue;
    private final Queue<Tone> toneQueue;

    public SoundSpeaker() {
        toneQueue = new Queue<>();
        startSoundSpeaker();
    }

    private void startSoundSpeaker() {
        if (!shouldContinue) {
            player = new SoundPlayer();
            player.setDaemon(true);
            shouldContinue = true;
            player.start();
        }
    }

    public void stopSoundSpeaker() {
        shouldContinue = false;
    }

    protected void playTone(int frequency, int duration) {
        toneQueue.addElement(new Tone(frequency, duration, VOLUME));
    }

    public void playActionTone(Move move) {
        playTone(700, 50);
    }

    public void playActionTone(Fire fire) {
        if (fire.hasLeftWeaponFired())
            playTone(250, 300);
        if (fire.hasRightWeaponFired()) // bigger weapon
            playTone(150, 800);
    }

    public void playStopProgram() {
        for (int i = 4; i < 12; i++) {
            playTone(G5_FREQ * i / 4, 100);
            playTone(10000, 100);
        }
    }

    public void playStopTank() {
        for (int i = 4; i < 12; i++) {
            playTone(G3_FREQ * i / 4, 100);
            playTone(10000, 100);
        }
    }

    public void playWaitingForPC() {
        for (int i = 4; i < 8; i++) {
            playTone(G4_FREQ, 100);
        }
    }

    public void playConnectedToPC() {
        playTone(G4_FREQ, 300);
    }

    public void playStartTank() {
        for (int i = 4; i < 12; i++) {
            playTone(G2_FREQ * i / 4, 100);
            playTone(10000, 100);
        }
    }

    public void playRfidNewValue() {
        playTone(C4_FREQ, 100);
        playTone(10000, 100);
        playTone(C4_FREQ, 100);
    }

    private boolean shouldContinue() {
        return shouldContinue;
    }

    public void playNotHandled() {
        playTone(C2_FREQ, 200);
    }

    public void playVictory() {
        playTone(C4_FREQ, 100);
        playTone(10000, 100);
        playTone(C4_FREQ, 100);
        playTone(10000, 100);
        playTone(D4_FREQ, 100);
        playTone(10000, 100);
        playTone(E4_FREQ, 100);
    }

    private class SoundPlayer extends Thread {

        @Override
        public void run() {
            while (shouldContinue()) {
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
}
