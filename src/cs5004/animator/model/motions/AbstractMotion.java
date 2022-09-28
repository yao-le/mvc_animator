package cs5004.animator.model.motions;

import cs5004.animator.model.helpers.State;
import cs5004.animator.model.shapes.IViewShape;

/**
 * An abstract class which implements the IMotion and IViewMotion interface.
 */
public abstract class AbstractMotion implements IMotion, IViewMotion {

  protected IViewShape shape;
  protected State startState;
  protected State endState;

  /**
   * Creates a type of motion object.
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
  protected AbstractMotion(IViewShape shape,
      int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.shape = shape;
    this.startState = new State(t1, x1, y1, w1, h1, r1, g1, b1);
    this.endState = new State(t2, x2, y2, w2, h2, r2, g2, b2);
  }

  /**
   * Returns the start state of the shape.
   * @return the start state
   */
  @Override
  public State getStartState() {
    return this.startState;
  }

  /**
   * Returns the end state of the shape.
   * @return the end state
   */
  @Override
  public State getEndState() {
    return this.endState;
  }

  /**
   * Returns the start time of this transformation.
   * @return the start time
   */
  @Override
  public int getStartTime() {
    return this.startState.getTimePoint();
  }

  /**
   * Returns the end time of this transformation.
   * @return the end time
   */
  @Override
  public int getEndTime() {
    return this.endState.getTimePoint();
  }

  /**
   * Returns the name of the shape in transformation.
   * @return the name of the shape
   */
  @Override
  public String getName() {
    return this.shape.getName();
  }

  /**
   * Returns the string representation of this transformation.
   * @return the string representation
   */
  @Override
  public String toString() {
    return this.toString(1);
  }

  /**
   * Returns the SVG representation of this transformation.
   * @return the SVG representation
   */
  @Override
  public String toSVG() {
    return this.toSVG(1);
  }


}
