package cs5004.animator.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * A button listener.
 */
public class ButtonListener implements ActionListener {

  Map<String, Runnable> buttonClickedActions;

  /**
   * Empty default constructor.
   */
  public ButtonListener() {
    // empty default constructor
  }

  /**
   * Set the map for button clicked events.
   * @param map a map for button clicked events
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    this.buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if (this.buttonClickedActions.containsKey(cmd)) {
      this.buttonClickedActions.get(cmd).run();
    }
  }

}

