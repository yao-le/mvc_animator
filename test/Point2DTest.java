import static org.junit.Assert.assertEquals;

import cs5004.animator.model.helpers.Point2D;
import org.junit.Test;

/**
 * Test cases for methods in Point2D class.
 */
public class Point2DTest {

  /**
   * Tests getX method.
   */
  @Test
  public void testGetX() {
    Point2D point = new Point2D(12, 26);
    assertEquals(12, point.getX());
  }

  /**
   * Tests getY method.
   */
  @Test
  public void testGetY() {
    Point2D point = new Point2D(12, 26);
    assertEquals(26, point.getY());
  }

  /**
   * Tests toString() method.
   */
  @Test
  public void testToString() {
    Point2D point2D = new Point2D(12, 26);
    assertEquals("(12, 26)", point2D.toString());
  }
}
