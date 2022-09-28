package cs5004.animator.view;

/**
 * Represents a view.
 */
public interface IView {

  /**
   * Displays an animation at the specific speed.
   * @param speed the speed of the animation
   */
  void display(int speed);

  /**
   * Returns true if this view is a playback view, false otherwise.
   * @return true if this view is a playback view, false otherwise
   */
  boolean isGUI();

  /**
   * Returns true if this view is a playback view or a visual view, false otherwise.
   * @return true if this view is a playback view or a visual view, false otherwise
   */
  boolean isGraphic();

  /**
   * Sets an appendable object for the view.
   * @param output an appendable object
   */
  void setAppendable(Appendable output);

}
