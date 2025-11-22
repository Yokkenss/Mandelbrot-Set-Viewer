import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int width = 800;
            int height = 800;
            int maxIterations = 50;
            double zoom = 300;
            double centeringX = -0.5; // -2.5 to 1
            double centeringY = 0; // -1 to 1

            MandelbrotPanel panel = new MandelbrotPanel(width, height, maxIterations, zoom, centeringX, centeringY);

            JFrame frame = new JFrame("Mandelbrot UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

