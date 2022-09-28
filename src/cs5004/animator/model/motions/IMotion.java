package cs5004.animator.model.motions;

import cs5004.animator.model.helpers.State;

/**
 * Represents a type of mutable motion. (State object is mutable)
 */
public interface IMotion {

  /**
   * Returns the state of the shape before motion.
   * @return the state of the shape before motion
   */
  State getStartState();

  /**
   * Returns the state of the shape after motion.
   * @return the state of the shape after motion
   */
  State getEndState();
}
