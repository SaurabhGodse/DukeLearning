import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
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
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for(Point p : s.getPoints()){
            count += 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return getPerimeter(s) / getNumPoints(s);
        
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largest = 0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist > largest)
            {
                largest = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here

        Point prevPt = s.getLastPoint();
        double largest = prevPt.getX();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            if(largest < currPt.getX()){
                largest = currPt.getX();
            }
        }
        
        return largest;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largest = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double p = getPerimeter(s);
            if(p > largest)
            {
                largest = p;
            }
        }
        return largest;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        // replace this code
        double largest = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double p = getPerimeter(s);
            if(p > largest)
            {
                largest = p;
                temp = f;
            }
        }
        
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points in shape = " + getNumPoints(s));
        System.out.println("The average length is = " + getAverageLength(s));
        System.out.println("Largest side length = " + getLargestSide(s));
        System.out.println("Largest x is = " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        System.out.println("Largest perimeter among selected files is = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("File with Largest Perimeter is  = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testFileWithLargestPerimeter();
    }
}
