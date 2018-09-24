import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
  private final ArrayList<LineSegment> segments = new ArrayList<>();

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] arg) {
    Point[] points = arg.clone();
    this.checkIfContainsNull(points);

    int length = points.length;

    // sort points
    Arrays.sort(points);
    // go through all the 4 elements in the array
    for (int first = 0; first < length; first++) {
      for (int second = first + 1; second < length; second++) {
        double slopeFS = points[first].slopeTo(points[second]);
        for (int third = second + 1; third < length; third++) {
          double slopeFT = points[first].slopeTo(points[third]);
          if (slopeFS == slopeFT) {
            for (int fouth = third + 1; fouth < length; fouth++) {
              double slopeFF = points[first].slopeTo(points[fouth]);
              if (slopeFF == slopeFT) {
                LineSegment segment = new LineSegment(points[first], points[fouth]);
                this.segments.add(segment);
              }
            }
          }
        }
      }
    }
  }

  private void checkIfContainsNull(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("argument is null");
    }

    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) {
        throw new IllegalArgumentException("null is not allowed");
      }
    }

    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        if (points[i].compareTo(points[j]) == 0) {
          throw new IllegalArgumentException("repeated points are not allowed");

        }
      }
    }
  }

  // the number of line segments
  public int numberOfSegments() {
    return segments.size();
  }

  // the line segments
  public LineSegment[] segments() {
    return segments.toArray(new LineSegment[segments.size()]);
  }

  public static void main(String[] args) {
    Point[] arr = new Point[8];
    arr[0] = new Point(10000, 0);
    arr[1] = new Point(0, 10000);
    arr[2] = new Point(3000, 7000);
    arr[3] = new Point(7000, 3000);
    arr[4] = new Point(20000, 21000);
    arr[5] = new Point(3000, 4000);
    arr[6] = new Point(14000, 15000);
    arr[7] = new Point(6000, 7000);

    BruteCollinearPoints bf = new BruteCollinearPoints(arr);
    System.out.println(bf.numberOfSegments());
  }
}
