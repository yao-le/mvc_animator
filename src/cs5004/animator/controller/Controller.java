package cs5004.animator.controller;

import cs5004.animator.view.GUI;
import cs5004.animator.view.IView;
import cs5004.animator.view.listeners.ButtonListener;
import cs5004.animator.view.listeners.KeyboardListener;
import cs5004.animator.view.listeners.MouseListener;
import cs5004.animator.view.listeners.SpeedOptionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an animation controller.
 */
public class Controller implements IController {

  IView view;

  /**
   * Creates a new animation controller.
   */
  public Controller() {
    // empty default constructor
  }

  @Override
  public void setView(IView view) {
    this.view = view;
    if (this.view.isGUI()) {
      configureButtonListener();
      configureSpeedOptionListener();
      configureKeyBoardListener();
      configureMouseListener();
    }
  }

  @Override
  public void run(int speed) throws IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed should be positive");
    }
    this.view.display(speed);
  }

  /**
   * configure a button clicked listener.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    GUI gui = (GUI) this.view;

    buttonClickedMap.put("toggle Button", () -> {
      gui.toggle();
      gui.resetFocus();
    });

    buttonClickedMap.put("restart Button", () -> {
      gui.restart();
      gui.resetFocus();
    });

    buttonClickedMap.put("loop Button", () -> {
      gui.setLoop();
      gui.resetFocus();
    });

    buttonClickedMap.put("remove Button", () -> {
      gui.deleteShape();
      gui.setInputText("");
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    gui.addActionListener(buttonListener);
  }

  /**
   * configure a keyboard listener.
   */
  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<Character, Runnable>();
    GUI gui = (GUI) this.view;

    keyTypes.put('k', () -> gui.toggle());
    keyTypes.put('r', () -> gui.restart());
    keyTypes.put('l', () -> gui.setLoop());

    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);

    gui.addKeyListener(kbd);

  }

  /**
   * configure a JComboBox selection changed listener.
   */
  private void configureSpeedOptionListener() {
    Map<String, Runnable> factorOptionsMap = new HashMap<String, Runnable>();
    SpeedOptionListener speedListener = new SpeedOptionListener();

    GUI gui = (GUI) this.view;

    String[] factors = new String[] {"Normal", "0.25", "0.5", "0.75",
        "1.25", "1.5", "1.75", "2"};

    for (String factorStr : factors) {
      double factor = factorStr.equals("Normal") ? 1 : Double.parseDouble(factorStr);

      factorOptionsMap.put(factorStr, () -> {
        gui.setSpeedFactor(factor);
        gui.resetFocus();
      });

    }

    speedListener.setFactorSelectedActionMap(factorOptionsMap);
    gui.addSpeedListener(speedListener);
  }

  /**
   * configure a mouse listener.
   */
  private void configureMouseListener() {
    GUI gui = (GUI) this.view;
    gui.addMouseListener(new MouseListener());
  }

}
