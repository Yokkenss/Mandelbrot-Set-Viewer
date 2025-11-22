import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class MandelbrotPanel extends JPanel {
    private final int width;
    private final int height;
    private final int maxIterations;
    private double zoom;
    private double offsetX;
    private double offsetY;
    private BufferedImage image;

    public MandelbrotPanel(int width, int height, int maxIterations, double zoom, double offsetX, double offsetY) {
        this.width = width;
        this.height = height;
        this.maxIterations = maxIterations;
        this.zoom = zoom;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        setPreferredSize(new Dimension(width, height));
        WindowUI();

        addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            double zoomFactor = 1.05;

            double mouseX = e.getX();
            double mouseY = e.getY();

            double complexMouseX = (mouseX - width / 2.0) / zoom + offsetX;
            double complexMouseY = (mouseY - height / 2.0) / zoom + offsetY;

            if (notches < 0) {
                MandelbrotPanel.this.zoom *= zoomFactor;
            } else {
                MandelbrotPanel.this.zoom /= zoomFactor;
            }
            MandelbrotPanel.this.offsetX = complexMouseX - (mouseX - width / 2.0) / MandelbrotPanel.this.zoom;
            MandelbrotPanel.this.offsetY = complexMouseY - (mouseY - height / 2.0) / MandelbrotPanel.this.zoom;

            WindowUI();
            repaint();
        });
    }

    private void WindowUI() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Complex c = new Complex(-0.70176, -0.3842);

        for (int px = 0; px < width; px++) {
            for (int py = 0; py < height; py++) {
                double zx = (px - width / 2.0) / zoom + offsetX;
                double zy = (py - height / 2.0) / zoom + offsetY;
                Complex z = new Complex(zx, zy);

                int iterations = MandelbrotAlg.mandelbrotIterations(c, maxIterations, z);

                int color;
                if (iterations == maxIterations) {
                    color = Color.BLACK.getRGB();
                } else {
                    float hue = (float) iterations / maxIterations;
                    float saturation = 0.7f;
                    float brightness = 1.0f;
                    color = Color.HSBtoRGB(hue, saturation, brightness);
                }

                image.setRGB(px, py, color);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}

