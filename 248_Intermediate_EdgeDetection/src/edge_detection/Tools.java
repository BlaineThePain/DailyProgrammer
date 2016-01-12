package edge_detection;

public class Tools {
    
    public static double closestOctant(double angle) {
        double step = Math.PI / 4;
        
        if (angle < 0) {
            angle += Math.PI;
        }
        
        double lowerBound = Math.floor(angle / step) * step;
        double upperBound = lowerBound + step;
        
        if ((upperBound - angle) < (angle - lowerBound)) {
            return upperBound;
        } else {
            return lowerBound;
        }
    }
    
    public static boolean areClose(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }

}
