import static org.junit.Assert.assertEquals;

import cs5004.animator.model.helpers.ColorRGB;
import org.junit.Test;

/**
 * Test cases for methods in ColorRGB class.
 */
public class ColorRGBTest {

  /**
   * Tests the constructor with invalid input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidInput1() {
    new ColorRGB(-2, 32, 126);
  }

  /**
   * Tests the constructor with invalid input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidInput2() {
    new ColorRGB(23, 328, 126);
  }

  /**
   * Tests getRed.
   */
  @Test
  public void testGetRed() {
    ColorRGB color = new ColorRGB(16, 209, 125);
    assertEquals(16, color.getRed());
  }

  /**
   * Tests getGreen.
   */
  @Test
  public void testGetGreen() {
    ColorRGB color = new ColorRGB(16, 209, 125);
    assertEquals(209, color.getGreen());
  }

  /**
   * Tests getBlue.
   */
  @Test
  public void testGetBlue() {
    ColorRGB color = new ColorRGB(16, 209, 125);
    assertEquals(125, color.getBlue());
  }

  /**
   * Tests toString method.
   */
  @Test
  public void testToString() {
    ColorRGB color = new ColorRGB(16, 209, 125);
    assertEquals("rgb(16, 209, 125)", color.toString());
  }
}
