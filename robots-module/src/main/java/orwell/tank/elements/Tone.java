package orwell.tank.elements;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public class Tone {
    private int frequency;
    private int duration;
    private int volume;

    public Tone(int frequency, int duration, int volume) {
        this.frequency = frequency;
        this.duration = duration;
        this.volume = volume;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getDuration() {
        return duration;
    }

    public int getVolume() {
        return volume;
    }
}
