package cs5004.animator.view;

import cs5004.animator.model.IViewModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * Represents a visual view.
 */
public class VisualView extends JFrame implements IView {

  private final AnimationPanel animationPanel;

  /**
   * Creates a new visual view.
   * @param viewModel an immutable animation model
   */
  public VisualView(IViewModel viewModel) {
    super("Animation");

    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.animationPanel = new AnimationPanel(viewModel);
    JScrollPane scrollBar = new JScrollPane(this.animationPanel,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(scrollBar);

    this.pack();
    this.setVisible(true);
  }


  @Override
  public void display(int speed) {
    this.animationPanel.setTimer(speed);
    this.animationPanel.toggle();
  }

  @Override
  public boolean isGUI() {
    return false;
  }

  @Override
  public boolean isGraphic() {
    return true;
  }


  @Override
  public void setAppendable(Appendable output) {
    throw new UnsupportedOperationException();
  }

}
