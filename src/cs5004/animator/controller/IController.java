package cs5004.animator.controller;

import cs5004.animator.view.IView;

/**
 * Represents a controller for animation.
 */
public interface IController {

  /**
   * Runs the animation.
   * @param speed the speed of the animation
   */
  void run(int speed);

  /**
   * Sets the view of the controller.
   * @param view a specified view
   */
  void setView(IView view);

}
