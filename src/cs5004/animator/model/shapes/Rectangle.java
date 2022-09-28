package cs5004.animator.model.shapes;

import cs5004.animator.model.helpers.Point2D;
import java.util.ArrayList;

/**
 * Represents a rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Creates a new rectangle object.
   * @param name the name of this rectangle
   */
  public Rectangle(String name) {
    super(name);
  }

  /**
   * Creates a new rectangle object.
   * @param name the name of this rectangle
   * @param x the x-coordinate of the top-left corner
   * @param y the y-coordinate of the top-left corner
   * @param w the width of the shape
   * @param h the height of the shape
   * @param r the red color-value
   * @param g the green color-value
   * @param b the blue color-value
   * @param appear the time when this rectangle appears
   * @param disappear the time when this rectangle disappears.
   */
  public Rectangle(String name, int x, int y,
      int w, int h, int r, int g, int b, int appear, int disappear) {
    super(name, x, y, w, h, r, g, b, appear, disappear);
  }

  @Override
  public String getType() {
    return "rectangle";
  }


  @Override
  public IViewShape copy() {
    Rectangle rect = new Rectangle(this.getName(), this.getReferencePos().getX(),
        this.getReferencePos().getY(), this.getWidth(), this.getHeight(),
        this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(),
        this.getAppearTime(), this.getDisappearTime());
    rect.lstOfMotion = new ArrayList<>(this.lstOfMotion);
    return rect;
  }

  @Override
  public String toSVG() {
    return String.format("\n<rect id='%s' x='%d' y='%d' width='%d' "
            + "height='%d' fill='%s' visibility='hidden'>\n***</rect>\n",
        this.getName(),
        this.getReferencePos().getX(),
        this.getReferencePos().getY(),
        this.getWidth(), this.getHeight(),
        this.getColor().toString());
  }

  @Override
  public String toString(int speed) {
    Point2D minCorner = new Point2D(
        this.getReferencePos().getX(),
        this.getReferencePos().getY() + this.getHeight());
    return String.format("Name: %s\nType: %s\n"
            + "Min corner: %s, Width: %d, Height: %d, Color: %s\n"
            + "Appears at t=%.1fs\n"
            + "Disappears at t=%.1fs\n",
        this.getName(), this.getType(),
        minCorner.toString(),
        this.getWidth(), this.getHeight(),
        this.getColor().toString(),
        this.getAppearTime() * 1.0 / speed,
        this.getDisappearTime() * 1.0 / speed);
  }

}
