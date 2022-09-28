package cs5004.animator.model.helpers;

/**
 * Represents canvas for animation.
 */
public class Canvas {

  private final int x;
  private final int y;
  private final int width;
  private final int height;

  /**
   * Creates a new immutable canvas and initialize it to the given inputs.
   *
   * @param x      x coordinate of top-left corner of the canvas
   * @param y      y coordinate of top-left corner of the canvas
   * @param width  width of this canvas
   * @param height height of this canvas
   */
  public Canvas(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height should be positive.");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the x coordinate of top-left corner of the canvas.
   *
   * @return x coordinate of top-left corner
   */
  public int getX() {
    return this.x;
  }


  /**
   * Returns the y coordinate of top-left corner of the canvas.
   *
   * @return y coordinate of top-left corner
   */
  public int getY() {
    return this.y;
  }


  /**
   * Returns the width of this canvas.
   *
   * @return the width
   */
  public int getWidth() {
    return this.width;
  }


  /**
   * Returns the height of this canvas.
   *
   * @return the height
   */
  public int getHeight() {
    return this.height;
  }


}
