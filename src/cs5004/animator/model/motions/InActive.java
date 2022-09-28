package cs5004.animator.model.motions;


import cs5004.animator.model.shapes.IViewShape;

/**
 * Represents no transformation.
 */
public class InActive extends AbstractMotion {

  /**
   * Creates an object representing no transformation.
   * @param shape the current shape
   * @param t1   The start time
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   */
  public InActive(IViewShape shape, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    super(shape, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
  }

  @Override
  public TypeOfMotion getType() {
    return TypeOfMotion.INACTIVE;
  }

  @Override
  public boolean isActive() {
    return false;
  }

  @Override
  public String toSVG(int speed) {
    return String.format("\t<animate attributeType='xml' "
        + "begin='%.1fms' dur='0.1ms' attributeName='visibility' "
            + "from='hidden' to='visible' fill='freeze' />\n",
        this.startState.getTimePoint() * 1000.0 / speed);
  }

  @Override
  public String toString(int speed) {
    return "";
  }

}
