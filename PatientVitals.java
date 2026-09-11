import java.util.Arrays;

public class PatientVitals {
    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {
        this.readings = new double[500];
        this.count = 0;
        if (initialReadings != null) {
            for (double reading : initialReadings) {
                this.recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {
        if (reading > 0 && reading <= 45 && count < this.readings.length) {
            this.readings[count++] = reading;
        }
    }

    public double getAverage() {
        if (count == 0) return 0.0;
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += this.readings[i];
        }
        return sum / count;
    }

    public double[] getAllReadings() {
        double[] copy = new double[count];
        System.arraycopy(this.readings, 0, copy, 0, count);
        return copy;
    }

    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        System.out.println(Arrays.toString(v.getAllReadings()));

        double[] copy = v.getAllReadings();
        copy[0] = 999;
        System.out.println(v.getAllReadings()[0]);
    }
}