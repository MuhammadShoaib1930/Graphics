import javax.swing.*;
import java.awt.*;

public class GraphPlotter extends JPanel {

    private Color backgroundColor;
    private int gridSize;
    private int maxRange;
    private Color gridLinesColor;
    private Color axesColor;
    private int fontSize;
    private int[] xPoints;
    private int[] yPoints;
    private Color pointsColor;
    private Color linesColor;
    private Color pointLabelsColor;

    // Constructor for integer data
    public GraphPlotter(Color backgroundColor, int gridSize, int maxRange, Color gridLinesColor,
            Color axesColor, int fontSize, int[] xPoints, int[] yPoints,
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
        this.pointLabelsColor = pointLabelsColor;
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
            g2.drawLine(0, y, getWidth(), y); // horizontal lines
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
                g2.drawString(String.valueOf(i), getWidth() / 2 - 25, y + 5); // y-axis labels
            }
        }

        // Label the origin
        g2.drawString("0", getWidth() / 2 + 5, getHeight() / 2 + 15);

        // Draw lines connecting the points
        g2.setColor(linesColor);
        drawLines(g2);

        // Draw and label each point
        g2.setColor(pointsColor);
        drawPoints(g2);
    }

    private void drawLines(Graphics2D g2) {
        for (int i = 0; i < xPoints.length; i++) {
            int x1 = getWidth() / 2 + convertToPixel(xPoints[i]);
            int y1 = getHeight() / 2 - convertToPixel(yPoints[i]);
            int x2 = getWidth() / 2 + convertToPixel(xPoints[(i + 1) % xPoints.length]);
            int y2 = getHeight() / 2 - convertToPixel(yPoints[(i + 1) % yPoints.length]);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private void drawPoints(Graphics2D g2) {
        for (int i = 0; i < xPoints.length; i++) {
            int x = getWidth() / 2 + convertToPixel(xPoints[i]);
            int y = getHeight() / 2 - convertToPixel(yPoints[i]);
            g2.fillOval(x - 3, y - 3, 6, 6);
            g2.setColor(pointLabelsColor);
            g2.drawString("(" + xPoints[i] + "," + yPoints[i] + ")", x + 5, y - 5);
            g2.setColor(pointsColor);
        }
    }

    private int convertToPixel(int value) {
        return value * gridSize;
    }

    // Static method to create a frame with a GraphPlotter
    public static void graphUserDefine(Color backgroundColor, int gridSize, int maxRange, Color gridLinesColor,
            Color axesColor, int fontSize, int[] xPoints, int[] yPoints,
            Color pointsColor, Color linesColor, Color pointLabelsColor) {
        JFrame frame = new JFrame("Graph Plotter");
        GraphPlotter panel = new GraphPlotter(backgroundColor, gridSize, maxRange, gridLinesColor, axesColor, fontSize,
                xPoints, yPoints, pointsColor, linesColor, pointLabelsColor);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
