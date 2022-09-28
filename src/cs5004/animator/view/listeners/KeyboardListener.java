package cs5004.animator.view.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * A keyboard listener.
 */
public class KeyboardListener implements KeyListener {
  private Map<Character, Runnable> keyTypedMap;

  /**
   * Empty default constructor.
   */
  public KeyboardListener() {
    // empty default constructor
  }

  /**
   * Set the map for key typed events. Key typed events in Java Swing are characters
   *
   * @param map a key-typed -> runnable-to-be-executed map object
   */

  public void setKeyTypedMap(Map<Character, Runnable> map) {
    this.keyTypedMap = map;
  }


  /**
   * This is called when the view detects that a key has been typed.
   * Find if anything has been mapped to this key character and if so, execute it.
   *
   * @param e a key typed event
   */

  @Override
  public void keyTyped(KeyEvent e) {
    char ch = e.getKeyChar();
    if (this.keyTypedMap.containsKey(ch)) {
      this.keyTypedMap.get(ch).run();
    }
  }

  /**
   * This is called when the view detects that a key has been pressed.
   * Find if anything has been mapped to this key code and if so, execute it.
   *
   * @param e a key pressed event
   */

  @Override
  public void keyPressed(KeyEvent e) {
    // do nothing
  }

  /**
   * This is called when the view detects that a key has been released.
   * Find if anything has been mapped to this key code and if so, execute it.
   *
   * @param e a key released event
   */

  @Override
  public void keyReleased(KeyEvent e) {
    // do nothing
  }

}
