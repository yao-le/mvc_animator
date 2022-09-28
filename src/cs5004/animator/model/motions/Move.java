package cs5004.animator.model.motions;

import cs5004.animator.model.helpers.Point2D;
import cs5004.animator.model.helpers.State;
import cs5004.animator.model.shapes.IViewShape;

/**
 * Represents position transformation.
 */
public class Move extends AbstractMotion {

  /**
   * Creates an object representing position transformation.
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
  public Move(IViewShape shape,
      int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    super(shape, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
  }


  @Override
  public TypeOfMotion getType() {
    return TypeOfMotion.MOVE;
  }


  @Override
  public boolean isActive() {
    return true;
  }

  @Override
  public String toString(int speed) {
    Point2D startPos = this.startState.getReferencePos();
    Point2D endPos = this.endState.getReferencePos();

    Point2D startMinCorner = new Point2D(startPos.getX(),
        startPos.getY() + this.startState.getHeight());

    Point2D endMinCorner = new Point2D(endPos.getX(),
        endPos.getY() + this.endState.getHeight());

    Point2D startCenter = new Point2D(
        startPos.getX() + this.startState.getWidth() / 2,
        startPos.getY() + this.startState.getHeight() / 2
    );

    Point2D endCenter = new Point2D(
        endPos.getX() + this.endState.getWidth() / 2,
        endPos.getY() + this.endState.getHeight() / 2
    );

    return String.format("Shape %s moves from %s "
        + "to %s from t=%.1fs to t=%.1fs\n",
        this.getName(),
        this.shape.getType().equals("rectangle") ? startMinCorner : startCenter,
        this.shape.getType().equals("rectangle") ? endMinCorner : endCenter,
        this.getStartTime() * 1.0 / speed,
        this.getEndTime() * 1.0 / speed);
  }

  @Override
  public String toSVG(int speed) {
    String result = "";

    State startState = this.startState;
    State endState = this.endState;
    Point2D startPos = startState.getReferencePos();
    Point2D endPos = endState.getReferencePos();

    double beginTime = startState.getTimePoint() * 1000.0 / speed;
    double duration = (endState.getTimePoint() - startState.getTimePoint()) * 1000.0 / speed;
    String attributeNameX = this.shape.getType().equals("rectangle") ? "x" : "cx";
    String attributeNameY = this.shape.getType().equals("rectangle") ? "y" : "cy";

    int fromX = this.shape.getType().equals("rectangle")
        ? startPos.getX() : startPos.getX() + this.shape.getWidth() / 2;
    int toX = this.shape.getType().equals("rectangle")
        ? endPos.getX() : endPos.getX() + this.shape.getWidth() / 2;

    int fromY = this.shape.getType().equals("rectangle")
        ? startPos.getY() : startPos.getY() + this.shape.getHeight() / 2;
    int toY = this.shape.getType().equals("rectangle")
        ? endPos.getY() : endPos.getY() + this.shape.getHeight() / 2;

    if (startPos.getX() != endPos.getX()) {
      result += String.format("\t<animate attributeType='xml' begin='%.1fms' "
              + "dur='%.1fms' attributeName='%s' from='%d' to='%d' fill='freeze' />\n",
          beginTime, duration, attributeNameX, fromX, toX);
    }

    if (startPos.getY() != endPos.getY()) {
      result += String.format("\t<animate attributeType='xml' begin='%.1fms' "
              + "dur='%.1fms' attributeName='%s' from='%d' to='%d' fill='freeze' />\n",
          beginTime, duration, attributeNameY, fromY, toY);
    }

    return result;
  }
}
