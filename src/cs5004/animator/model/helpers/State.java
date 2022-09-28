package cs5004.animator.model.helpers;

/**
 * Used to represent the state of shape before and after motion.
 */
public class State {

  private int timePoint;
  private int width;
  private int height;
  private Point2D referencePos;
  private ColorRGB color;

  /**
   * Creates a new state for a shape. The value of time point is automatically set to 1.
   *
   * @param x the x-coordinate of the reference position
   * @param y the y-coordinate of the reference position
   * @param w the width of the shape
   * @param h the height of the shape
   * @param r the red color-value of the shape
   * @param g the green color-value of the shape
   * @param b the blue color-value of the shape
   */
  public State(int x, int y, int w, int h, int r, int g, int b)
      throws IllegalArgumentException {
    this(1, x, y, w, h, r, g, b);
  }

  /**
   * Creates a new state for a shape.
   *
   * @param t the time-point of the state
   * @param x the x-coordinate of the reference position
   * @param y the y-coordinate of the reference position
   * @param w the width of the shape
   * @param h the height of the shape
   * @param r the red color-value of the shape
   * @param g the green color-value of the shape
   * @param b the blue color-value of the shape
   */
  public State(int t, int x, int y, int w, int h, int r, int g, int b)
      throws IllegalArgumentException {
    if (t < 0) {
      throw new IllegalArgumentException("time point should not be negative");
    }
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("width and height should be positive");
    }
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("rgb value should between 0 and 255");
    }

    this.timePoint = t;
    this.referencePos = new Point2D(x, y);
    this.width = w;
    this.height = h;
    this.color = new ColorRGB(r, g, b);
  }

  /**
   * Returns the time-point of the state.
   *
   * @return the time-point
   */
  public int getTimePoint() {
    return this.timePoint;
  }

  /**
   * Returns the reference position of the shape.
   *
   * @return the reference position
   */
  public Point2D getReferencePos() {
    return this.referencePos;
  }

  /**
   * Sets the reference position of the shape.
   *
   * @param x the x-coordinate of the reference position
   * @param y the y-coordinate of the reference position
   */
  public void setReferencePos(int x, int y) {
    this.referencePos = new Point2D(x, y);
  }

  /**
   * returns the rgb color of the shape.
   *
   * @return the rgb color
   */
  public ColorRGB getColor() {
    return this.color;
  }

  /**
   * Sets the rgb color of the shape.
   *
   * @param r red color-value
   * @param g green color-value
   * @param b blue color-value
   */
  public void setColor(int r, int g, int b) {
    this.color = new ColorRGB(r, g, b);
  }

  /**
   * Returns the width of the shape.
   *
   * @return the width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Sets the width of the shape.
   *
   * @param w the width
   */
  public void setWidth(int w) throws IllegalArgumentException {
    if (w < 0) {
      throw new IllegalArgumentException("width should be positive");
    }
    this.width = w;
  }

  /**
   * Returns the height of the shape.
   *
   * @return the height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Sets the height of the shape.
   *
   * @param h the height
   */
  public void setHeight(int h) throws IllegalArgumentException {
    if (h < 0) {
      throw new IllegalArgumentException("height should be positive");
    }
    this.height = h;
  }


}
