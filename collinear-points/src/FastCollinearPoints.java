import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
  private final ArrayList<LineSegment> segments = new ArrayList<>();

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] arg) {
    checkIfContainsNull(arg);
    Point[] points = arg.clone();

    for (int j = 0; j < points.length; j++) {
      Comparator<Point> pointComporator = arg[j].slopeOrder();
      Arrays.sort(points, pointComporator);

      System.out.println(Arrays.toString(points));
      int quantity = 0;
      Point last = null;
      for (int i = 1; i < points.length - 1; i++) {
        if (arg[j].slopeTo(points[i]) == arg[j].slopeTo(points[i + 1])) {
          quantity++;
          last = points[i + 1];
        }
      }

      if (quantity >= 3 && last != null) {
        segments.add(new LineSegment(arg[j], last));
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

    FastCollinearPoints bf = new FastCollinearPoints(arr);
    System.out.println(bf.numberOfSegments());
  }
}
