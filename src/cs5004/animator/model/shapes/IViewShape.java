package cs5004.animator.model.shapes;

import cs5004.animator.model.helpers.ColorRGB;
import cs5004.animator.model.helpers.Point2D;
import cs5004.animator.model.motions.IViewMotion;
import java.util.List;

/**
 * Represents an immutable shape object.
 */
public interface IViewShape {

  /**
   * Returns the name of the current shape object.
   *
   * @return the name
   */
  String getName();

  /**
   * Returns the type of the current shape object.
   *
   * @return the type
   */
  String getType();

  /**
   * Returns the reference point of the current shape object.
   *
   * @return the reference position
   */
  Point2D getReferencePos();

  /**
   * Returns the rgb color of the current shape object.
   *
   * @return the rgb color
   */
  ColorRGB getColor();

  /**
   * Returns the width of the current shape object.
   *
   * @return the width
   */
  int getWidth();

  /**
   * Returns the height of the current shape object.
   *
   * @return the height
   */
  int getHeight();

  /**
   * Returns the time when the current shape object appears.
   *
   * @return appear time
   */
  int getAppearTime();

  /**
   * Returns the time when the current shape object disappears.
   *
   * @return disappear time
   */
  int getDisappearTime();

  /**
   * Returns the list of motions of the current shape.
   *
   * @return the list of motions
   */
  List<IViewMotion> getMotion();

  /**
   * Returns a copy of the current shape.
   *
   * @return a copy.
   */
  IViewShape copy();

  /**
   * Returns the string representation in svg format of the current shape.
   *
   * @return the SVG
   */
  String toSVG();

  /**
   * Returns the string representation of the current object, replacing 1 tick/sec with the given
   * speed of animation.
   *
   * @param speed the speed of animation
   * @return the string representation
   */
  String toString(int speed);

  @Override
  String toString(); // speed: 1 tick/second

}
