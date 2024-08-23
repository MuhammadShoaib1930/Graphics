import java.awt.Color;

public class MainMethodClass {
    public static void main(String[] args) {
        Number[] xarrFloat = {-2.0f, -1.0f, 0.0f, 1.0f, 2.0f, 3.0f};
        Number[] yarrFloat = {-2.0f, -1.0f, 0.0f, 1.0f, 2.0f, 3.0f};
        Number[] xarrInt = {1, 2, 3, 4, 5, 6};
        Number[] yarrInt = {1, 2, 3, 4, 5, 6};
    
        // Calling the method with Integer arrays
        GraphPlotter.graphUserDefine(Color.WHITE, 30, 15, Color.LIGHT_GRAY, Color.BLACK, 15, xarrInt, yarrFloat, Color.GREEN, Color.RED, Color.BLUE);
    }
}