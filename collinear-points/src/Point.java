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

      return that.x - this.x;
    }

    return that.y - this.y;
  }

  public double slopeTo(Point that) {
    if (that.x == this.x) {
      return 0;
    }

    return (that.y - this.y) / (that.x - this.x);
  }

  public Comparator<Point> slopeOrder() {
    return new PointComparator<Point>()
  }

  class PointComparator implements Comparator<Point> {
    public int compare(Point point1, Point point2) {
      return point1.slopeTo(this) - point2.slopeTo(this)
    }
  }
}
