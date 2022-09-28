package cs5004.animator.model.helpers;

/**
 * Represents a 2D point.
 */
public class Point2D {

  private final int x;
  private final int y;

  /**
   * Creates a new immutable 2D Point Object.
   *
   * @param x x coordinate
   * @param y y coordinate
   */
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x-coordinate of the point.
   *
   * @return the x-coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y-coordinate of the point.
   *
   * @return the y-coordinate
   */
  public int getY() {
    return this.y;
  }


  @Override
  public String toString() {
    return String.format("(%d, %d)", this.x, this.y);
  }
}
