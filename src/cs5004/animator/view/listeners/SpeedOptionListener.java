package cs5004.animator.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Objects;
import javax.swing.JComboBox;

/**
 * A JComboBox listener.
 */
public class SpeedOptionListener implements ActionListener  {
  Map<String, Runnable> factorSelectedActions;

  /**
   * Empty default constructor.
   */
  public SpeedOptionListener() {
    // empty default constructor
  }

  /**
   * Set the map for JComboBox selection changed event.
   */
  public void setFactorSelectedActionMap(Map<String, Runnable> map) {
    this.factorSelectedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
    String factor = Objects.requireNonNull(comboBox.getSelectedItem()).toString();

    if (this.factorSelectedActions.containsKey(factor)) {
      this.factorSelectedActions.get(factor).run();
    }
  }
}
