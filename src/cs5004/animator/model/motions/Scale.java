package cs5004.animator.model.motions;

import cs5004.animator.model.helpers.State;
import cs5004.animator.model.shapes.IViewShape;

/**
 * Represents size transformation.
 */
public class Scale extends AbstractMotion {

  /**
   * Creates an object representing size transformation.
   * @param shape the current shape in transformation
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   */
  public Scale(IViewShape shape, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    super(shape, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
  }


  @Override
  public TypeOfMotion getType() {
    return TypeOfMotion.SCALE;
  }


  @Override
  public boolean isActive() {
    return true;
  }


  @Override
  public String toString(int speed) {
    return String.format("Shape %s scales from Width: %d, Height: %d "
            + "to Width: %d, Height: %d from t=%.1fs to t=%.1fs\n",
        this.getName(),
        this.startState.getWidth(),
        this.startState.getHeight(),
        this.endState.getWidth(),
        this.endState.getHeight(),
        this.getStartTime() * 1.0 / speed,
        this.getEndTime() * 1.0 / speed);
  }

  @Override
  public String toSVG(int speed) {
    String result = "";

    State startState = this.startState;
    State endState = this.endState;

    double beginTime = startState.getTimePoint() * 1000.0 / speed;
    double duration = (endState.getTimePoint() - startState.getTimePoint()) * 1000.0 / speed;
    String attributeNameW = this.shape.getType().equals("rectangle") ? "width" : "rx";
    String attributeNameH = this.shape.getType().equals("rectangle") ? "height" : "ry";

    int fromW = this.shape.getType().equals("rectangle")
        ? startState.getWidth() : startState.getWidth() / 2;
    int toW = this.shape.getType().equals("rectangle")
        ? endState.getWidth() : endState.getWidth() / 2;
    int fromH = this.shape.getType().equals("rectangle")
        ? startState.getHeight() : startState.getHeight() / 2;
    int toH = this.shape.getType().equals("rectangle")
        ? endState.getHeight() : endState.getHeight() / 2;

    if (startState.getWidth() != endState.getWidth()) {
      result += String.format("\t<animate attributeType='xml' begin='%.1fms' "
              + "dur='%.1fms' attributeName='%s' from='%d' to='%d' fill='freeze' />\n",
          beginTime, duration, attributeNameW, fromW, toW);
    }

    if (startState.getHeight() != endState.getHeight()) {
      result += String.format("\t<animate attributeType='xml' begin='%.1fms' "
              + "dur='%.1fms' attributeName='%s' from='%d' to='%d' fill='freeze' />\n",
          beginTime, duration, attributeNameH, fromH, toH);
    }

    return result;
  }

}
