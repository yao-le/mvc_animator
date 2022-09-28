package cs5004.animator.view.listeners;

import cs5004.animator.view.AnimationPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A mouse listener.
 */
public class MouseListener extends MouseAdapter {

  /**
   * Empty default constructor.
   */
  public MouseListener() {
    // empty default constructor
  }


  @Override
  public void mouseClicked(MouseEvent e) {
    AnimationPanel aPanel = (AnimationPanel) e.getSource();
    int button = e.getButton();
    if (button == MouseEvent.BUTTON1) {
      aPanel.toggle();
    } else if (button == MouseEvent.BUTTON3) {
      aPanel.restart();
    }
  }
}
