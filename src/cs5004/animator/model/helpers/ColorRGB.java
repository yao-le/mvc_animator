package cs5004.animator.model.helpers;

/**
 * Represents a color in the rgb format.
 */
public class ColorRGB {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Creates a new immutable RGB color object.
   *
   * @param r red value in the range (0 - 255)
   * @param g green value in the range (0 - 255)
   * @param b blue value in the range (0 - 255)
   * @throws IllegalArgumentException when input rgb values are out of range.
   */
  public ColorRGB(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255
        || g < 0 || g > 255
        || b < 0 || b > 255) {
      throw new IllegalArgumentException("rgb value should between 0 and 255");
    }
    this.red = r;
    this.green = g;
    this.blue = b;
  }

  /**
   * Returns the red value.
   *
   * @return the red value
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Returns the blue value.
   *
   * @return the blue value.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Returns the green value.
   *
   * @return the green value
   */
  public int getGreen() {
    return this.green;
  }


  @Override
  public String toString() {
    return "rgb(" + this.red + ", " + this.green + ", " + this.blue + ")";
  }

}
