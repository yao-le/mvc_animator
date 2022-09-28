import static org.junit.Assert.assertEquals;

import cs5004.animator.model.helpers.State;
import org.junit.Test;

/**
 * Test cases for methods in State class.
 */
public class StateTest {

  /**
   * Test constructor with invalid inputs.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidInput1() {
    new State(-9, 2, 4, 12, 34, 24, 53,123);
  }

  /**
   * Test constructor with invalid inputs.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidInput2() {
    new State(18, 2, 4, 25,
        -29, 24, 53,123);
  }

  /**
   * Test constructor with invalid inputs.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidInput3() {
    new State(18, 2, 4,
        25, 29, 298, 53,123);
  }

  /**
   * Tests getTimePoint method.
   */
  @Test
  public void testGetTimePoint() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    assertEquals(18, s.getTimePoint());
  }

  /**
   * Tests getReferencePos method.
   */
  @Test
  public void testGetReferencePos() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    assertEquals(2, s.getReferencePos().getX());
    assertEquals(4, s.getReferencePos().getY());
  }

  /**
   * Tests setReferencePos method.
   */
  @Test
  public void testSetReferencePos() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    s.setReferencePos(6, 9);
    assertEquals(6, s.getReferencePos().getX());
    assertEquals(9, s.getReferencePos().getY());
  }

  /**
   * Tests getColor method.
   */
  @Test
  public void testGetColor() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    assertEquals(201, s.getColor().getRed());
    assertEquals(53, s.getColor().getGreen());
    assertEquals(123, s.getColor().getBlue());
  }

  /**
   * Tests setColor method.
   */
  @Test
  public void testSetColor() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    s.setColor(12, 9, 8);
    assertEquals(12, s.getColor().getRed());
    assertEquals(9, s.getColor().getGreen());
    assertEquals(8, s.getColor().getBlue());
  }

  /**
   * Tests getWidth method.
   */
  @Test
  public void testGetWidth() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    assertEquals(25, s.getWidth());
  }

  /**
   * Tests setWidth method.
   */
  @Test
  public void testSetWidth() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    s.setWidth(16);
    assertEquals(16, s.getWidth());
  }

  /**
   * Tests getHeight method.
   */
  @Test
  public void testGetHeight() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    assertEquals(29, s.getHeight());
  }

  /**
   * Tests setHeight method.
   */
  @Test
  public void testSetHeight() {
    State s = new State(18, 2, 4, 25, 29, 201, 53,123);
    s.setHeight(32);
    assertEquals(32, s.getHeight());
  }

}
