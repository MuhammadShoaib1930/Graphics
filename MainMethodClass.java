import java.awt.Color;
import java.math.*;

public class MainMethodClass {
    public static void graphUserDefineMethod(int[] xArr, int[] yArr) {
        GraphPlotter.graphUserDefine(
                Color.LIGHT_GRAY,
                40,
                15,
                Color.GRAY,
                Color.BLACK,
                15,
                xArr,
                yArr,
                Color.YELLOW,
                Color.RED,
                Color.BLACK);

    }

    static int[] xPoints;
    static int[] yPoints;

    public static void main(String[] args) {
        dda(2, 2, 5, 4);
        
        graphUserDefineMethod(null, null);
        // Calling the method with Integer arrays
    }

    public static void dda(int x1, int y1, int x2, int y2) {
        xPoints = new int[x2];
        yPoints = new int[y2];
        int m = (y2 - y1) / ((x2 - x1 != 0) ? (x2 - x1) : 1);
        int dy = m * (x2 - x1);
        int dx = (y2 - y1) / ((m != 0) ? m : 1);
        int xIndex = 0;
        int yIndex = 0;
        // case 1
        if (absUser(dx) >= absUser(dy)) {
            xIndex = 1;
            yIndex = 1;
            dx = 1;
            xPoints[0] = x1;
            while (xPoints[xIndex - 1] != x2 && yPoints[xIndex - 1] != y2) {
                xPoints[xIndex] = xPoints[xIndex - 1] + dx;
                yPoints[yIndex] = yPoints[yIndex - 1] + m * dx;
                xIndex++;
                yIndex++;
            }
        } else if (absUser(dx) < absUser(dy)) {
            dy = 1;
            yIndex = 1;
            xIndex = 1;
            while (yPoints[yIndex - 1] != y2 && xPoints[xIndex - 1] != x2) {
                yPoints[yIndex] = yPoints[yIndex - 1] + dy;
                xPoints[xIndex] = xPoints[xIndex - 1] + dy / ((m != 0) ? m : 1);
            }
        }

    }

    public static int absUser(int x) {
        if (x < 0) {
            return (x * -1);
        } else
            return x;
    }
}
