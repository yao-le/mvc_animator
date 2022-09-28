package cs5004.animator.view;

import cs5004.animator.model.IViewModel;

/**
 * A factory of views.
 */
public class ViewFactory {

  /**
   * Takes in a String name for a view—“text”, “svg”, or “visual”,
   * and constructs an instance of the appropriate concrete view.
   * @param viewName a string name for a view
   * @param model an immutable model for the view
   * @return a new concrete view
   * @throws IllegalArgumentException when the view factory does not support the specified view
   */
  public static IView createView(String viewName, IViewModel model)
      throws IllegalArgumentException {
    IView view;
    switch (viewName) {
      case "text":
        view = new TextView(model);
        break;
      case "svg":
        view = new SVGView(model);
        break;
      case "visual":
        view = new VisualView(model);
        break;
      case "playback":
        view = new GUIView(model);
        break;
      default:
        throw new IllegalArgumentException("This type of view is not supported.");
    }
    return view;
  }

}
