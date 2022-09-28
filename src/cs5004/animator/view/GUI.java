package cs5004.animator.view;

import cs5004.animator.view.listeners.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Includes features that a GUI need to support.
 */
public interface GUI {

  /**
   * Start or pause the animation.
   */
  void toggle();

  /**
   * Restart the animation.
   */
  void restart();

  /**
   * Enable or Disable the loop of the animation.
   */
  void setLoop();

  /**
   * Slow down or speed up the animation.
   * @param factor a factor which increases or decreases the initial speed.
   *               the speed of animation is set to (factor * initial speed);
   */
  void setSpeedFactor(double factor);

  /**
   * Deletes the shape.
   */
  void deleteShape();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener attached to
   * it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * Resets the input text field.
   * @param input the given input
   */
  void setInputText(String input);

  /**
   * Adds an action listener for buttons.
   * @param actionListener an action listener
   */
  void addActionListener(ActionListener actionListener);

  /**
   * Adds a key listener.
   * @param listener a key listener
   */
  void addKeyListener(KeyListener listener);

  /**
   * Adds an action listener for speed options.
   * @param actionListener an action listener
   */
  void addSpeedListener(ActionListener actionListener);

  /**
   * add a mouse listener.
   * @param mouseListener a mouse listener
   */
  void addMouseListener(MouseListener mouseListener);

}
