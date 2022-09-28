package cs5004.animator.view;

import cs5004.animator.view.listeners.MouseListener;
import cs5004.animator.model.IViewModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Represents a GUI view.
 */
public class GUIView extends JFrame implements GUI, IView {

  private final AnimationPanel animationPanel;

  private JButton toggleButton;
  private JButton restartButton;
  private JButton loopButton;

  private JComboBox<String> speedList;

  private JButton removeButton;
  private JTextField inputText;

  /**
   * Creates a new GUI view.
   * @param viewModel an immutable animation model
   */
  public GUIView(IViewModel viewModel) {
    super("Animation");

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    JPanel buttonPane = new JPanel(true);
    buttonPane.setBackground(Color.WHITE);
    this.add(buttonPane, BorderLayout.PAGE_START);

    createButtons();
    createSpeedList();

    buttonPane.add(this.toggleButton);
    buttonPane.add(this.restartButton);
    buttonPane.add(this.loopButton);

    // speed options
    JPanel speedPane = new JPanel();
    speedPane.setBackground(Color.WHITE);
    speedPane.setBorder(BorderFactory.createTitledBorder("Speed"));
    speedPane.add(this.speedList);

    buttonPane.add(speedPane);

    // remove shapes
    JPanel removePane = new JPanel();
    removePane.setLayout(new BoxLayout(removePane, BoxLayout.Y_AXIS));
    removePane.setBackground(Color.WHITE);
    removePane.add(this.inputText);
    removePane.add(this.removeButton);

    buttonPane.add(removePane);

    // animation Panel
    this.animationPanel = new AnimationPanel(viewModel);
    JScrollPane scrollBar = new JScrollPane(this.animationPanel,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(scrollBar, BorderLayout.CENTER);

    this.pack();
    this.resetFocus();
    this.setVisible(true);
  }


  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void toggle() {
    this.animationPanel.toggle();
  }

  @Override
  public void restart() {
    this.animationPanel.restart();
  }

  @Override
  public void setLoop() {
    this.animationPanel.setLoop();
  }

  @Override
  public void setSpeedFactor(double factor) {
    this.animationPanel.setSpeedFactor(factor);
  }

  @Override
  public void deleteShape() {
    String name = this.inputText.getText();
    this.animationPanel.deleteShape(name);
  }

  @Override
  public void setInputText(String input) {
    this.inputText.setText(input);
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    this.toggleButton.addActionListener(actionListener);
    this.restartButton.addActionListener(actionListener);
    this.loopButton.addActionListener(actionListener);

    this.removeButton.addActionListener(actionListener);
  }

  @Override
  public void addSpeedListener(ActionListener actionListener) {
    this.speedList.addActionListener(actionListener);
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    this.animationPanel.addMouseListener(mouseListener);
  }

  @Override
  public boolean isGUI() {
    return true;
  }

  @Override
  public boolean isGraphic() {
    return true;
  }

  @Override
  public void setAppendable(Appendable output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void display(int speed) {
    this.animationPanel.setTimer(speed);
  }

  /**
   * Initialize the buttons.
   */
  private void createButtons() {

    this.toggleButton = new JButton("Start/Pause");
    this.toggleButton.setActionCommand("toggle Button");

    this.restartButton = new JButton("Restart");
    this.restartButton.setActionCommand("restart Button");

    this.loopButton = new JButton("Enable/Disable Loop");
    this.loopButton.setActionCommand("loop Button");

    this.inputText = new JTextField(3);
    this.removeButton = new JButton("Remove");
    this.removeButton.setActionCommand("remove Button");

  }

  /**
   * Initialize the speed list.
   */
  private void createSpeedList() {
    this.speedList = new JComboBox<>();

    this.speedList.addItem("Normal");
    this.speedList.addItem("0.25");
    this.speedList.addItem("0.5");
    this.speedList.addItem("0.75");
    this.speedList.addItem("1.25");
    this.speedList.addItem("1.5");
    this.speedList.addItem("1.75");
    this.speedList.addItem("2");
  }


}
