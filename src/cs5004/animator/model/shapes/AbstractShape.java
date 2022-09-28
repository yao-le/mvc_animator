package cs5004.animator.model.shapes;

import cs5004.animator.model.helpers.ColorRGB;
import cs5004.animator.model.helpers.Point2D;
import cs5004.animator.model.helpers.State;
import cs5004.animator.model.motions.IViewMotion;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class which implements the IShape and IViewShape Interface.
 */
public abstract class AbstractShape implements IShape, IViewShape {

  protected String name;
  protected State state;
  protected int appearTime;
  protected int disappearTime;

  protected List<IViewMotion> lstOfMotion;

  /**
   * Creates a new shape object.
   *
   * @param name the name of the current shape.
   */
  protected AbstractShape(String name) {
    this.name = name;
    this.appearTime = Integer.MAX_VALUE;
    this.disappearTime = -1;
    this.state = new State(1, 1, 1, 1, 1, 1, 1);
    this.lstOfMotion = new ArrayList<>();
  }

  /**
   * Creates a new shape object and initializes it to the given inputs.
   *
   * @param name      the name of the shape
   * @param x         the x-coordinate of the top-left corner of the shape
   * @param y         the y-coordinate of the top-left cornet of the shape
   * @param w         width
   * @param h         height
   * @param r         red color-value
   * @param g         green color-value
   * @param b         blue color-value
   * @param appear    the time when the shape appears
   * @param disappear the time when the shape disappears
   */
  protected AbstractShape(String name, int x, int y,
      int w, int h, int r, int g, int b, int appear, int disappear) {
    this.name = name;
    this.state = new State(x, y, w, h, r, g, b);
    this.appearTime = appear;
    this.disappearTime = disappear;

    this.lstOfMotion = new ArrayList<>();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Point2D getReferencePos() {
    return this.state.getReferencePos();
  }

  @Override
  public ColorRGB getColor() {
    return this.state.getColor();
  }

  @Override
  public int getWidth() {
    return this.state.getWidth();
  }

  @Override
  public int getHeight() {
    return this.state.getHeight();
  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }


  @Override
  public void setState(int x, int y, int w, int h, int r, int g, int b) {
    this.state = new State(x, y, w, h, r, g, b);
  }

  @Override
  public void setWidth(int width) {
    this.state.setWidth(width);
  }

  @Override
  public void setHeight(int height) {
    this.state.setHeight(height);
  }

  @Override
  public void setReferencePos(int x, int y) {
    this.state.setReferencePos(x, y);
  }

  @Override
  public void setColor(int r, int g, int b) {
    this.state.setColor(r, g, b);
  }

  @Override
  public void setAppearTime(int time) {
    this.appearTime = time;
  }

  @Override
  public void setDisappearTime(int time) {
    this.disappearTime = time;
  }

  @Override
  public String toString() {
    return this.toString(1);
  }

  @Override
  public List<IViewMotion> getMotion() {
    return this.lstOfMotion;
  }

  @Override
  public void addMotion(IViewMotion motion) {
    this.lstOfMotion.add(motion);
  }


}
