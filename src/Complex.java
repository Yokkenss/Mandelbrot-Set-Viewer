public class Complex {
    private final double real;
    private final double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imag + other.imag);
    }

    public Complex square() {
        double newReal = real * real - imag * imag;
        double newImag = 2 * real * imag;
        return new Complex(newReal, newImag);
    }

    public double magnitudeSquared() {
        return real * real + imag * imag;
    }

    public String toString() {
        return real + " + " + imag + "i";
    }
}

