package cs5004.animator.model.shapes;

import cs5004.animator.model.helpers.Point2D;
import java.util.ArrayList;

/**
 * Represents an oval.
 */
public class Oval extends AbstractShape {

  /**
   * Creates a new oval object.
   * @param name the name of this oval
   */
  public Oval(String name) {
    super(name);
  }


  /**
   * Creates a new Oval object.
   * @param name the name of this oval
   * @param x the x-coordinate of the top-left corner
   * @param y the y-coordinate of the top-left corner
   * @param w the width of the shape
   * @param h the height of the shape
   * @param r the red color-value
   * @param g the green color-value
   * @param b the blue color-value
   * @param appear the time when this oval appears
   * @param disappear the time when this oval disappears.
   */
  public Oval(String name, int x, int y,
      int w, int h, int r, int g, int b, int appear, int disappear) {
    super(name, x, y, w, h, r, g, b, appear, disappear);
  }


  @Override
  public String getType() {
    return "ellipse";
  }


  @Override
  public IViewShape copy() {
    Oval oval =  new Oval(this.getName(), this.getReferencePos().getX(),
        this.getReferencePos().getY(), this.getWidth(), this.getHeight(),
        this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(),
        this.getAppearTime(), this.getDisappearTime());
    oval.lstOfMotion = new ArrayList<>(this.lstOfMotion);
    return oval;
  }


  @Override
  public String toSVG() {
    return String.format("\n<ellipse id='%s' cx='%d' cy='%d' "
            + "rx='%d' ry='%d' fill='%s' visibility='hidden'>\n***</ellipse>\n",
        this.getName(),
        this.getReferencePos().getX() + getWidth() / 2,
        this.getReferencePos().getY() + getHeight() / 2,
        this.getWidth() / 2, this.getHeight() / 2, this.getColor().toString());
  }

  @Override
  public String toString(int speed) {
    Point2D center = new Point2D(
        this.getReferencePos().getX() + this.getWidth() / 2,
        this.getReferencePos().getY() + this.getHeight() / 2);
    return String.format("Name: %s\nType: %s\n"
            + "Center: %s, X radius: %d, Y radius: %d, Color: %s\n"
            + "Appears at t=%.1fs\n"
            + "Disappears at t=%.1fs\n",
        this.getName(), this.getType(),
        center,
        this.getWidth() / 2,
        this.getHeight() / 2,
        this.getColor(),
        this.getAppearTime() * 1.0 / speed,
        this.getDisappearTime() * 1.0 / speed);
  }

}
