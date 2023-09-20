import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter(Shape s) {
        return getPerimeter(s, false);
    }

    public double getPerimeter(Shape s, boolean isOutput) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        if (isOutput)
            System.out.printf("Perimeter = %.2f%n", truncate2DecimalPlaces(totalPerim));
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        return getNumPoints(s, false);
    }
    public int getNumPoints(Shape s, boolean isOutput) {
        // Put code here
        int ptCount = 0;
        for (Point dummmy : s.getPoints()) {
            ptCount++;
        }
        if (isOutput)
            System.out.printf("Num of Points = %d%n", ptCount);
        return ptCount;
    }

    public double getAverageLength(Shape s) {
        return getAverageLength(s, false);
    }
    public double getAverageLength(Shape s, boolean isOutput) {
        // Put code here
        int sideCount = getNumPoints(s);
        double totalLength = getPerimeter(s);
        double averageLength = totalLength / sideCount;
        if (isOutput)
            System.out.printf("Average Length = %.2f%n",
                    truncate2DecimalPlaces(averageLength));
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        return getLargestSide(s, false);
    }
    public double getLargestSide(Shape s, boolean isOutput) {
        // Put code here
        double largestSide = 0.0;
        double currSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            currSide = prevPt.distance(currPt);
            if (currSide > largestSide)
                largestSide = currSide;
            prevPt = currPt;
        }
        if (isOutput)
            System.out.printf("Largest Side = %.2f%n",
                    truncate2DecimalPlaces(largestSide));
        return largestSide;
    }

    public double getLargestX(Shape s) {
        return getLargestX(s, false);
    }
    public double getLargestX(Shape s, boolean isOutput) {
        // Put code here
        double largestX = 0.0;
        double currX = 0.0;
        for (Point p : s.getPoints()) {
            currX = p.getX();
            if (currX > largestX)
                largestX = currX;
        }
        if (isOutput)
            System.out.printf("Largest X = %.2f%n",
                    truncate2DecimalPlaces(largestX));
        return 0.0;
    }

    public double getLargestPerimeterMultipleFiles() {
        return getLargestPerimeterMultipleFiles(false);
    }
    public double getLargestPerimeterMultipleFiles(boolean isOutput) {
        // Put code here
        double largestPerimeter = 0.0;
        double currPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File fr : dr.selectedFiles()) {
            currPerimeter = getPerimeter(new Shape(new FileResource(fr)));
            if (isOutput) {
                System.out.println("File: " + fr);
                System.out.printf("current Perimeter = %.2f%n",
                        truncate2DecimalPlaces(currPerimeter));
            }
            if (currPerimeter > largestPerimeter)
                largestPerimeter = currPerimeter;
        }
        if (isOutput)
            System.out.printf("Largest Perimeter = %.2f%n",
                    truncate2DecimalPlaces(largestPerimeter));
        return largestPerimeter;
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        for (Point p : s.getPoints()) {
            System.out.println(p);
        }
        getPerimeter(s, true);
        getNumPoints(s, true);
        getAverageLength(s, true);
        getLargestSide(s, true);
        getLargestX(s, true);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        getLargestPerimeterMultipleFiles(true);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.printf("Perimeter = %.2f%n", truncate2DecimalPlaces(peri));
    }

    // This method prints names of all files in a chosen folder that you can use to
    // test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static double truncate2DecimalPlaces(double number) {
        double factor = Math.pow(10, 2);
        return Math.floor(number * factor) / factor;
    }

    public static void main(String[] args) {
        clearScreen();
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        // pr.testPerimeter();
        pr.testFileWithLargestPerimeter();
    }
}
