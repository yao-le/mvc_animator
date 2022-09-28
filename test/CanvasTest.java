import static org.junit.Assert.assertEquals;

import cs5004.animator.model.helpers.Canvas;
import org.junit.Test;

/**
 * Test cases for methods in Canvas class.
 */
public class CanvasTest {

  /**
   * Tests the constructor with invalid width input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidWidth() {
    Canvas canvas = new Canvas(0, 0, -12,32);
  }

  /**
   * Tests the constructor with invalid height input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidHeight() {
    Canvas canvas = new Canvas(1, 2, 12, -32);
  }

  /**
   * Tests getX method.
   */
  @Test
  public void testGetX() {
    Canvas c = new Canvas(2, 3, 12, 16);
    assertEquals(2, c.getX());
  }

  /**
   * Tests getY method.
   */
  @Test
  public void testGetY() {
    Canvas c = new Canvas(2, 3, 12, 16);
    assertEquals(3, c.getY());
  }

  /**
   * Tests getWidth method.
   */
  @Test
  public void testGetWidth() {
    Canvas c = new Canvas(2, 3, 12, 16);
    assertEquals(12, c.getWidth());
  }

  /**
   * Tests getHeight method.
   */
  @Test
  public void testGetHeight() {
    Canvas c = new Canvas(2, 3, 12, 16);
    assertEquals(16, c.getHeight());
  }


}
