package cs5004.animator.model;

import cs5004.animator.model.helpers.Canvas;
import cs5004.animator.model.helpers.State;
import cs5004.animator.model.motions.IViewMotion;
import cs5004.animator.model.motions.InActive;
import cs5004.animator.model.motions.Move;
import cs5004.animator.model.motions.TypeOfMotion;
import cs5004.animator.model.shapes.IShape;
import cs5004.animator.model.shapes.IViewShape;
import cs5004.animator.model.motions.ChangeColor;
import cs5004.animator.model.motions.IMotion;
import cs5004.animator.model.motions.Scale;
import cs5004.animator.model.shapes.Oval;
import cs5004.animator.model.shapes.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A concrete class which implements IModel and IViewModel interface.
 */
public class Model implements IModel, IViewModel {

  private Canvas canvas;
  private int endTime;

  private final Map<String, IViewShape> nameToShapeMap;
  private final List<IViewShape> lstOfShape;
  private final Map<String, IViewShape> newestState;

  /**
   * Creates a new model for animation.
   */
  public Model() {
    this.nameToShapeMap = new HashMap<>();
    this.newestState = new HashMap<>();
    this.lstOfShape = new ArrayList<>();
  }

  @Override
  public void setBounds(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height should be positive");
    }
    this.canvas = new Canvas(x, y, width, height);
  }

  @Override
  public Canvas getCanvas() {
    return this.canvas;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public void declareShape(String name, String type) throws IllegalArgumentException {
    IViewShape newShape;

    switch (type) {
      case "rectangle":
        newShape = new Rectangle(name);
        break;
      case "ellipse" :
        newShape = new Oval(name);
        break;
      default :
        throw new IllegalArgumentException(
            String.format("Sorry, the model does not support type %s.", type));
    }
    this.lstOfShape.add(newShape);
    this.nameToShapeMap.put(name, newShape);
  }


  @Override
  public void addMotion(String name,
      int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2)
      throws IllegalArgumentException {

    IViewShape currShape = this.nameToShapeMap.get(name);

    // if shape with the input name does not exit, then throw the error.
    if (currShape == null) {
      throw new IllegalArgumentException("The shape does not exit");
    }

    this.endTime = Math.max(this.endTime, t2);

    // initialize the attributes of the current shape
    if (t1 < currShape.getAppearTime()) {
      ((IShape)currShape).setState(x1, y1, w1, h1, r1, g1, b1);
      ((IShape)currShape).setAppearTime(t1);
    }

    if (t2 > currShape.getDisappearTime()) {
      ((IShape)currShape).setDisappearTime(t2);
    }

    // if the reference position of the shape changes
    if (x1 != x2 || y1 != y2) {
      IViewMotion motion = new Move(currShape,
          t1, x1, y1, w1, h1, r1, g1, b1,
          t2, x2, y2, w2, h2, r2, g2, b2);
      ((IShape)currShape).addMotion(motion);
    }

    // if the color of the shape changes
    if (r1 != r2 || g1 != g2 || b1 != b2) {
      IViewMotion motion = new ChangeColor(currShape,
          t1, x1, y1, w1, h1, r1, g1, b1,
          t2, x2, y2, w2, h2, r2, g2, b2);
      ((IShape)currShape).addMotion(motion);
    }

    // if the size of the shape changes
    if (w1 != w2 || h1 != h2) {
      IViewMotion motion = new Scale(currShape,
          t1, x1, y1, w1, h1, r1, g1, b1,
          t2, x2, y2, w2, h2, r2, g2, b2);
      ((IShape)currShape).addMotion(motion);
    }

    // if the state of the shape does not change
    if (x1 == x2 && y1 == y2 && r1 == r2 && g1 == g2
        && b1 == b2 && w1 == w2 && h1 == h2) {
      IViewMotion motion = new InActive(currShape,
          t1, x1, y1, w1, h1, r1, g1, b1,
          t2, x2, y2, w2, h2, r2, g2, b2);
      ((IShape)currShape).addMotion(motion);
    }
  }

  @Override
  public List<IViewShape> getAllShapeSorted() {
    List<IViewShape> allShapes = new ArrayList<>(this.lstOfShape);
    allShapes.sort(Comparator.comparingInt(IViewShape::getAppearTime));
    return allShapes;
  }

  @Override
  public List<IViewMotion> getAllMotionSorted() {
    List<IViewMotion> allMotion = new ArrayList<>();
    for (IViewShape shape : this.lstOfShape) {
      allMotion.addAll(shape.getMotion());
    }
    allMotion.sort(Comparator.comparingInt(IViewMotion::getStartTime));
    return allMotion;
  }

  /**
   * Gets the state of the specified shape at a specified time.
   * @param name the name of the shape
   * @param time the specified of the shape
   * @return an immutable shape
   */
  private IViewShape getState(String name, int time) {
    IViewShape currShape = null;

    // get the newest state of the shape if this shape has already appeared
    if (this.newestState.containsKey(name)) {
      currShape = this.newestState.get(name).copy();
    }

    IViewShape originalShape = this.nameToShapeMap.get(name);

    for (IViewMotion motion : originalShape.getMotion()) {

      int t1 = motion.getStartTime();
      int t2 = motion.getEndTime();

      if (time < t1 || time > t2) {
        continue;
      }

      State start = ((IMotion) motion).getStartState();
      State end = ((IMotion) motion).getEndState();

      if (currShape == null) {
        currShape = originalShape.copy();
      }

      IShape curr = (IShape) currShape; // changes currShape to mutable type
      if (motion.getType() == TypeOfMotion.SCALE) {
        int w = tween(time, t1, t2, start.getWidth(), end.getWidth());
        int h = tween(time, t1, t2, start.getHeight(), end.getHeight());
        curr.setWidth(w);
        curr.setHeight(h);
      }

      if (motion.getType() == TypeOfMotion.CHANGE_COLOR) {
        int r = tween(time, t1, t2, start.getColor().getRed(), end.getColor().getRed());
        int g = tween(time, t1, t2, start.getColor().getGreen(), end.getColor().getGreen());
        int b = tween(time, t1, t2, start.getColor().getBlue(), end.getColor().getBlue());
        curr.setColor(r, g, b);
      }

      if (motion.getType() == TypeOfMotion.MOVE) {
        int x = tween(time, t1, t2, start.getReferencePos().getX(),
            end.getReferencePos().getX());
        int y = tween(time, t1, t2, start.getReferencePos().getY(),
            end.getReferencePos().getY());
        curr.setReferencePos(x, y);
      }

    }

    if (currShape != null) {
      this.newestState.put(name, currShape);
    }

    return currShape;
  }

  /**
   * Clears the newest state of all shapes.
   */
  private void clearCurrState() {
    this.newestState.clear();
  }

  @Override
  public List<IViewShape> getShapesToPaint(int time) {
    // restart
    if (time == 1) {
      this.clearCurrState();
    }

    List<IViewShape> shapesToPaint = new ArrayList<>();
    for (IViewShape shape : this.lstOfShape) {
      if (shape.getDisappearTime() < time) {
        continue;
      }

      IViewShape shapeState = this.getState(shape.getName(), time);
      if (shapeState != null) {
        shapesToPaint.add(shapeState);
      }
    }
    return shapesToPaint;
  }

  /**
   * Uses linear interpolation to compute the intermediate state of
   * a shape at any time between t1 and t2.
   * @param t the intermediate time
   * @param t1 the start time
   * @param t2 the end time
   * @param v1 the start value
   * @param v2 the end value
   * @return the intermediate state of an attribute of the shape
   */
  private int tween(int t, int t1, int t2, int v1, int v2) {
    double range = t2 - t1;
    return (int) (v1 * (t2 - t) / range + v2 * (t - t1) / range);
  }

}
