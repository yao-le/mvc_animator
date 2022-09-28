package cs5004.animator.model;

import cs5004.animator.model.helpers.Canvas;
import cs5004.animator.model.motions.IViewMotion;
import cs5004.animator.model.shapes.IViewShape;
import java.util.List;

/**
 * Represents an immutable model of the animation.
 */
public interface IViewModel {

  /**
   * Return the bounding box to be used for the animation.
   * @return the canvas.
   */
  Canvas getCanvas();

  /**
   * Returns the end time of the animation.
   * @return the end time
   */
  int getEndTime();

  /**
   * Return a list of shapes to be painted at a specific time.
   * @param time the specified time
   * @return a list of shapes
   */
  List<IViewShape> getShapesToPaint(int time);

  /**
   * Returns a list of shapes sorted by the time when they appear.
   * @return a sorted list of shapes
   */
  List<IViewShape> getAllShapeSorted();

  /**
   * Returns a list of motion sorted by the time when they start.
   * @return a sorted list of motion
   */
  List<IViewMotion> getAllMotionSorted();

}
