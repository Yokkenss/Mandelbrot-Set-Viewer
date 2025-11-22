public class MandelbrotAlg {

    public static int mandelbrotIterations(Complex c, int maxIterations, Complex z) {
        int iter = 0;

        while (z.magnitudeSquared() <= 4 && iter < maxIterations) { //<4 squared 2
            z = z.square().add(c);
            iter++;
        }

        return iter;
    }
}

