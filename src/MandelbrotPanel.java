import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotPanel extends JPanel {
    private final int width;
    private final int height;
    private final int maxIterations;
    private final double zoom;
    private final double offsetX;
    private final double offsetY;
    private BufferedImage image;

    public MandelbrotPanel(int width, int height, int maxIterations, double zoom, double offsetX, double offsetY) {
        this.width = width;
        this.height = height;
        this.maxIterations = maxIterations;
        this.zoom = zoom;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        WindowUI();
        setPreferredSize(new Dimension(width, height));
    }

    private void WindowUI() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Complex c = new Complex(-0.70176 , -0.3842);

        for (int px = 0; px < width; px++) {
            for (int py = 0; py < height; py++) {
                double zx = (px - width / 2.0) / zoom + offsetX; /// convert in complex
                double zy = (py - height / 2.0) / zoom + offsetY;//convert in complex
                Complex z = new Complex(zx, zy);

                int iterations = MandelbrotAlg.mandelbrotIterations(c, maxIterations, z);

            int color;

            if (iterations == maxIterations) {
                color = Color.BLACK.getRGB();
            } else {
                float hue = (float) iterations / maxIterations; //gradieent
                float saturation = 0.7f; //vivid
                float brightness = 1.0f;
                color = Color.HSBtoRGB(hue, saturation, brightness);
            }

            image.setRGB(px, py, color);

            }
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }



}

