import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
  private final int x;
  private final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void draw() {
    StdDraw.point(x, y);
  }

  public void drawTo(Point that) {
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public int compareTo(Point that) {
    if (that.y == this.y) {
      if (that.x == this.x) {
        return 0;
      }

      return this.x - that.x;
    }

    return this.y - that.y;
  }

  public double slopeTo(Point that) {
    if (that.x == this.x && that.y == this.y) {
      return Double.NEGATIVE_INFINITY;
    }

    if (that.x == this.x) {
      return Double.POSITIVE_INFINITY;
    }

    if (that.y == this.y) {
      return +0.0;
    }


    return (double) (that.y - this.y) / (that.x - this.x);
  }

  public Comparator<Point> slopeOrder() {
    return new PointComparator();
  }

  private class PointComparator implements Comparator<Point> {
    public int compare(Point point1, Point point2) {
      double slope1 = Point.this.slopeTo(point1);
      double slope2 = Point.this.slopeTo(point2);
      if (slope1 < slope2) {
        return -1;
      }

      if (slope1 > slope2) {
        return 1;
      }

      return 0;
    }
  }
}
