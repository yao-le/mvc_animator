package cs5004.animator.view;

import cs5004.animator.model.IViewModel;
import cs5004.animator.model.motions.IViewMotion;
import cs5004.animator.model.shapes.IViewShape;
import java.io.IOException;

/**
 * Represents a text view.
 */
public class TextView implements IView {

  private final IViewModel model;
  private Appendable output;

  /**
   * Constructs a new text view.
   * @param model an immutable animation model for this view
   */
  public TextView(IViewModel model) {
    this.model = model;
  }

  @Override
  public void setAppendable(Appendable output) {
    this.output = output;
  }

  @Override
  public void display(int speed)
      throws IllegalStateException {
    try {
      for (IViewShape shape : this.model.getAllShapeSorted()) {
        this.output.append(shape.toString(speed));
        this.output.append("\n");
      }

      for (IViewMotion motion : this.model.getAllMotionSorted()) {
        this.output.append(motion.toString(speed));
      }
    } catch (IOException e) {
      throw new IllegalStateException("Could not render the animation.");
    }

  }

  @Override
  public boolean isGUI() {
    return false;
  }

  @Override
  public boolean isGraphic() {
    return false;
  }

}
