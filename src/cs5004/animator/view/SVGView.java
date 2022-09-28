package cs5004.animator.view;


import cs5004.animator.model.IViewModel;
import cs5004.animator.model.helpers.Canvas;
import cs5004.animator.model.motions.IViewMotion;
import cs5004.animator.model.shapes.IViewShape;
import java.io.IOException;

/**
 * Represents an SVG view.
 */
public class SVGView implements IView {

  private final IViewModel viewModel;
  private Appendable output;

  /**
   * Creates a new SVG view.
   *
   * @param viewModel an immutable animation model for this view
   */
  public SVGView(IViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @Override
  public void setAppendable(Appendable output) {
    this.output = output;
  }


  @Override
  public void display(int speed)
      throws IllegalArgumentException {
    try {
      Canvas canvas = this.viewModel.getCanvas();
      this.output.append(String.format("<svg viewBox='%d %d %d %d' version='1.1' "
              + "xmlns='http://www.w3.org/2000/svg' >",
          canvas.getX(), canvas.getY(),
          canvas.getWidth(), canvas.getHeight()));

      for (IViewShape shape : this.viewModel.getAllShapeSorted()) {
        boolean isVisible = false;
        StringBuilder animation = new StringBuilder();

        for (IViewMotion motion : shape.getMotion()) {
          if (!motion.isActive() && isVisible) {
            continue;
          }

          if (!motion.isActive()) {
            isVisible = true;
          }

          animation.append(motion.toSVG(speed));
        }
        output.append(shape.toSVG().replace("***", animation.toString()));
      }

      output.append("</svg>");
    } catch (IOException e) {
      throw new IllegalStateException("Could not render the animation");
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
