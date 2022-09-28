package cs5004.animator.model.motions;

/**
 * Represents a type of immutable motion.
 */
public interface IViewMotion {

  /**
   * Returns the start time of the motion.
   * @return the start time
   */
  int getStartTime();

  /**
   * Returns the end time of the motion.
   * @return the end time
   */
  int getEndTime();

  /**
   * Returns the name of the shape in motion.
   * @return the name of the shape
   */
  String getName();

  /**
   * Returns the type of the motion.
   * @return the type of motion.
   */
  TypeOfMotion getType();

  /**
   * Returns true if the motion changes the state of the shape, false otherwise.
   * @return true if the motion changes the state of the shape, false otherwise
   */
  boolean isActive();

  @Override
  String toString();

  /**
   * Returns the string representation.
   * 1 tick/second replaced by the given speed.
   * @param speed the speed of animation
   * @return the string representation of the motion
   */
  String toString(int speed);

  /**
   * Returns the string representation in svg format.
   * @return the SVG
   */
  String toSVG();

  /**
   * Returns the string representation in svg format.
   * 1 tick/second replaced by the given speed.
   * @param speed the speed of animation
   * @return the SVG
   */
  String toSVG(int speed);

}
