import javax.swing.*;
import java.awt.*;

public class GraphPlotter extends JPanel {

    private Color backgroundColor;
    private int gridSize;
    private int maxRange;
    private Color gridLinesColor;
    private Color axesColor;
    private int fontSize;
    private float[] xPoints;
    private float[] yPoints;
    private Color pointsColor;
    private Color linesColor;
    private Color pointLabelsColor; // New attribute for point label font color

    // Method to set graph attributes and repaint
    public void drawGraph(Color backgroundColor, int gridSize, int maxRange, Color gridLinesColor,
                          Color axesColor, int fontSize, float[] xPoints, float[] yPoints,
                          Color pointsColor, Color linesColor, Color pointLabelsColor) {
        this.backgroundColor = backgroundColor;
        this.gridSize = gridSize;
        this.maxRange = maxRange;
        this.gridLinesColor = gridLinesColor;
        this.axesColor = axesColor;
        this.fontSize = fontSize;
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.pointsColor = pointsColor;
        this.linesColor = linesColor;
        this.pointLabelsColor = pointLabelsColor; // Set the point labels color

        // Repaint the panel with the new settings
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Set the background color
        setBackground(backgroundColor);

        // Draw the grid lines
        g2.setColor(gridLinesColor);
        for (int i = -maxRange; i <= maxRange; i++) {
            int x = getWidth() / 2 + i * gridSize;
            int y = getHeight() / 2 - i * gridSize;
            g2.drawLine(x, 0, x, getHeight()); // vertical lines
            g2.drawLine(0, y, getWidth(), y);  // horizontal lines
        }

        // Draw the x and y axes
        g2.setColor(axesColor);
        g2.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight()); // y-axis
        g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); // x-axis

        // Set the font for drawing numbers on the axes
        g2.setFont(new Font("Arial", Font.PLAIN, fontSize));
        for (int i = -maxRange; i <= maxRange; i++) {
            if (i != 0) {
                int x = getWidth() / 2 + i * gridSize;
                int y = getHeight() / 2 - i * gridSize;
                g2.drawString(String.valueOf(i), x - 5, getHeight() / 2 + 15); // x-axis labels
                g2.drawString(String.valueOf(i), getWidth() / 2 - 25, y + 5);  // y-axis labels
            }
        }

        // Label the origin
        g2.drawString("0", getWidth() / 2 + 5, getHeight() / 2 + 15);

        // Draw lines connecting the points
        g2.setColor(linesColor);
        for (int i = 0; i < xPoints.length; i++) {
            int x1 = getWidth() / 2 + Math.round(xPoints[i] * gridSize);
            int y1 = getHeight() / 2 - Math.round(yPoints[i] * gridSize);
            int x2 = getWidth() / 2 + Math.round(xPoints[(i + 1) % xPoints.length] * gridSize);
            int y2 = getHeight() / 2 - Math.round(yPoints[(i + 1) % yPoints.length] * gridSize);
            g2.drawLine(x1, y1, x2, y2);
        }

        // Draw and label each point
        g2.setColor(pointsColor);
        for (int i = 0; i < xPoints.length; i++) {
            int x = getWidth() / 2 + Math.round(xPoints[i] * gridSize);
            int y = getHeight() / 2 - Math.round(yPoints[i] * gridSize);
            g2.fillOval(x - 3, y - 3, 6, 6);

            // Set the color for the point labels
            g2.setColor(pointLabelsColor);
            g2.drawString("(" + xPoints[i] + "," + yPoints[i] + ")", x + 5, y - 5);

            // Reset color to pointsColor for drawing points
            g2.setColor(pointsColor);
        }
    }

    public static void graph_calling(float[] xPoints, float[] yPoints) {
        JFrame frame = new JFrame("Graph Plotter");
        GraphPlotter panel = new GraphPlotter();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Example settings with point label font color
        panel.drawGraph(Color.WHITE, 30, 15, Color.LIGHT_GRAY, Color.BLACK, 15,
                        xPoints, yPoints, Color.GREEN, Color.RED, Color.BLUE);
    }

    public static void main(String[] args) {
        float[] xarr = {-3.0f, -2.0f, -1.0f, 1.0f, 2.0f, 3.0f};
        float[] yarr = {-2.0f, -1.0f, 0.0f, 2.0f, 3.0f, 4.0f};

        graph_calling(xarr, yarr);
    }
}
