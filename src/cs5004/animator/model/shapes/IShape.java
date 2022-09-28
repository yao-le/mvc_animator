package cs5004.animator.model.shapes;

import cs5004.animator.model.motions.IViewMotion;

/**
 * Represents a mutable shape object.
 */
public interface IShape {

  /**
   * Sets the time when the shape appears.
   *
   * @param time time when the shape appears
   */
  void setAppearTime(int time);

  /**
   * Sets the time when the shape disappears.
   *
   * @param time time when the shape disappears
   */
  void setDisappearTime(int time);

  /**
   * Sets the width of the shape.
   *
   * @param width width of the current shape
   */
  void setWidth(int width);

  /**
   * Sets the height of the current shape.
   *
   * @param height height of the shape
   */
  void setHeight(int height);

  /**
   * Sets the reference position(top-left corner) of the shape.
   *
   * @param x the x-coordinate of the top-left corner
   * @param y the y-coordinate of the top-left corner
   */
  void setReferencePos(int x, int y);

  /**
   * Sets the rgb color of the current shape.
   *
   * @param r red color-value
   * @param g green color-value
   * @param b blue color-value
   */
  void setColor(int r, int g, int b);

  /**
   * Sets the state of the current shape object.
   *
   * @param x x-coordinate of reference position
   * @param y y-coordinate of reference position
   * @param w width
   * @param h height
   * @param r red color-value
   * @param g green color-value
   * @param b blue color-value
   */
  void setState(int x, int y, int w, int h, int r, int g, int b);

  /**
   * Stores the motion of the current shape object.
   *
   * @param motion the motion of the current shape object
   */
  void addMotion(IViewMotion motion);

}
