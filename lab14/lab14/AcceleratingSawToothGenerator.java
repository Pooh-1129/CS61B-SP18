package lab14;
import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator{
    private int period;
    private int state;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    private double normalize(int v) {
        double half = (period - 1) / 2.0;
        return (v - half) / half;
    }

    public double next() {
        if (state < period) {
            state += 1;
            return normalize(state);
        }
        state = 0;
        period = (int) (period * factor);
        return normalize(state);
    }
}
