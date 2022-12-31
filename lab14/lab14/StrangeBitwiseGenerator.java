package lab14;
import lab14lib.Generator;

public class StrangeBitwiseGenerator {
    private int period;
    private int state;
    private int weirdState;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        weirdState = state & (state >> 3) % period;
        this.period = period;
    }

    @Override
    public double next() {
        if (state % period != 0) {
            state += 1;
            weirdState = state & (state >> 3) % period;
            return normalize(weirdState);
        }
        state = 1;
        weirdState = state & (state >> 3) % period;
        return normalize(weirdState);
    }

    private double normalize(int value) {
        double half = (period - 1) / 2.0;
        return (value - half) / half;
    }
}
