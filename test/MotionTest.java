import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import cs5004.animator.model.helpers.State;
import cs5004.animator.model.motions.ChangeColor;
import cs5004.animator.model.motions.InActive;
import cs5004.animator.model.motions.Move;
import cs5004.animator.model.motions.Scale;
import cs5004.animator.model.motions.TypeOfMotion;
import cs5004.animator.model.shapes.IViewShape;
import cs5004.animator.model.shapes.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests cases for methods in AbstractMotion, Move, Scale, ChangeColor and InActive class.
 */
public class MotionTest {

  IViewShape rect;
  Move move;
  Scale scale;
  ChangeColor changeColor;
  InActive inactive;

  /**
   * set up the shape and transformations.
   */
  @Before
  public void setUp() {
    this.rect = new Rectangle("R", 200, 200,
        16, 12, 20, 50, 100, 1, 100);
    this.move = new Move(rect,
        9, 200, 200, 16, 12, 20, 50, 100,
        100,300, 350, 16, 12, 20, 50, 100);
    this.changeColor = new ChangeColor(rect,
        90, 300, 300, 16, 12, 20, 50, 100,
        200,300, 350, 16, 12, 25, 123, 235);
    this.scale = new Scale(rect,
        6, 200, 200, 20, 35, 20, 50, 100,
        150,200, 250, 16, 12, 20, 50, 100);
    this.inactive = new InActive(rect,
        1, 200, 200, 16, 12, 20, 50, 100,
        200,200, 200, 16, 12, 20, 50, 100);
  }

  /**
   * Tests getStartState method.
   */
  @Test
  public void testGetStartState() {
    State start = this.move.getStartState();
    assertEquals(9, start.getTimePoint());
    assertEquals(200, start.getReferencePos().getX());
    assertEquals(200, start.getReferencePos().getY());
  }

  /**
   * Tests getEndState method.
   */
  @Test
  public void testGetEndState() {
    State end = this.changeColor.getEndState();
    assertEquals(200, end.getTimePoint());
    assertEquals(25, end.getColor().getRed());
    assertEquals(123, end.getColor().getGreen());
    assertEquals(235, end.getColor().getBlue());
  }

  /**
   * Tests getStartTime method.
   */
  @Test
  public void testGetStartTime() {
    assertEquals(6, scale.getStartTime());
    assertEquals(9, move.getStartTime());
    assertEquals(90, changeColor.getStartTime());
    assertEquals(1, inactive.getStartTime());
  }

  /**
   * Tests getEndTime method.
   */
  @Test
  public void testGetEndTime() {
    assertEquals(150, scale.getEndTime());
    assertEquals(100, move.getEndTime());
    assertEquals(200, changeColor.getEndTime());
    assertEquals(200, inactive.getEndTime());
  }

  /**
   * Tests getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("R", move.getName());
    assertEquals("R", scale.getName());
    assertEquals("R", changeColor.getName());
    assertEquals("R", inactive.getName());
  }

  /**
   * Tests getType method.
   */
  @Test
  public void testGetType() {
    assertEquals(TypeOfMotion.MOVE, move.getType());
    assertEquals(TypeOfMotion.SCALE, scale.getType());
    assertEquals(TypeOfMotion.CHANGE_COLOR, changeColor.getType());
    assertEquals(TypeOfMotion.INACTIVE, inactive.getType());
  }

  /**
   * Tests isActive method.
   */
  @Test
  public void testIsActive() {
    assertTrue(move.isActive());
    assertTrue(changeColor.isActive());
    assertTrue(scale.isActive());
    assertFalse(inactive.isActive());
  }

  /**
   * Tests toString method.
   */
  @Test
  public void testToString() {
    String expected = "Shape R moves from (200, 212) to (300, 362) "
        + "from t=9.0s to t=100.0s\n";
    assertEquals(expected, move.toString());

    expected = "Shape R scales from Width: 20, Height: 35 to Width: 16, Height: 12 "
        + "from t=6.0s to t=150.0s\n";
    assertEquals(expected, scale.toString());

    expected = "Shape R changes color from rgb(20, 50, 100) to rgb(25, 123, 235) "
        + "from t=90.0s to t=200.0s\n";
    assertEquals(expected, changeColor.toString());

    expected = "";
    assertEquals(expected, inactive.toString());
  }

  /**
   * Tests toString(speed) method.
   */
  @Test
  public void testToStringSpeed() {
    int speed = 30;
    String expected = "Shape R moves from (200, 212) to (300, 362) "
        + "from t=0.3s to t=3.3s\n";
    assertEquals(expected, move.toString(speed));

    expected = "Shape R scales from Width: 20, Height: 35 to "
        + "Width: 16, Height: 12 from t=0.2s to t=5.0s\n";
    assertEquals(expected, scale.toString(speed));

    expected = "Shape R changes color from rgb(20, 50, 100) to rgb(25, 123, 235) "
        + "from t=3.0s to t=6.7s\n";
    assertEquals(expected, changeColor.toString(speed));

    expected = "";
    assertEquals(expected, inactive.toString(speed));
  }

  /**
   * Tests toSVG method.
   */
  @Test
  public void testToSVG() {
    String expected = "\t<animate attributeType='xml' begin='9000.0ms' dur='91000.0ms' "
        + "attributeName='x' from='200' to='300' fill='freeze' />\n"
        + "\t<animate attributeType='xml' begin='9000.0ms' dur='91000.0ms' "
        + "attributeName='y' from='200' to='350' fill='freeze' />\n";
    assertEquals(expected, move.toSVG());

    expected = "\t<animate attributeType='xml' begin='6000.0ms' dur='144000.0ms' "
        + "attributeName='width' from='20' to='16' fill='freeze' />\n"
        + "\t<animate attributeType='xml' begin='6000.0ms' dur='144000.0ms' "
        + "attributeName='height' from='35' to='12' fill='freeze' />\n";
    assertEquals(expected, scale.toSVG());

    expected = "\t<animate attributeType='xml' begin='90000.0ms' dur='110000.0ms' "
        + "attributeName='fill' from='rgb(20, 50, 100)' to='rgb(25, 123, 235)' fill='freeze' />\n";
    assertEquals(expected, changeColor.toSVG());

    expected = "\t<animate attributeType='xml' begin='1000.0ms' dur='0.1ms' "
        + "attributeName='visibility' from='hidden' to='visible' fill='freeze' />\n";
    assertEquals(expected, inactive.toSVG());

  }

  /**
   * Tests toSVG(speed) method.
   */
  @Test
  public void testToSVGSpeed() {
    int speed = 20;
    String expected = "\t<animate attributeType='xml' begin='450.0ms' dur='4550.0ms' "
        + "attributeName='x' from='200' to='300' fill='freeze' />\n"
        + "\t<animate attributeType='xml' begin='450.0ms' dur='4550.0ms' "
        + "attributeName='y' from='200' to='350' fill='freeze' />\n";
    assertEquals(expected, move.toSVG(speed));

    expected = "\t<animate attributeType='xml' begin='300.0ms' dur='7200.0ms' "
        + "attributeName='width' from='20' to='16' fill='freeze' />\n"
        + "\t<animate attributeType='xml' begin='300.0ms' dur='7200.0ms' "
        + "attributeName='height' from='35' to='12' fill='freeze' />\n";
    assertEquals(expected, scale.toSVG(speed));

    expected = "\t<animate attributeType='xml' begin='4500.0ms' dur='5500.0ms' "
        + "attributeName='fill' from='rgb(20, 50, 100)' to='rgb(25, 123, 235)' fill='freeze' />\n";
    assertEquals(expected, changeColor.toSVG(speed));

    expected = "\t<animate attributeType='xml' begin='50.0ms' dur='0.1ms' "
        + "attributeName='visibility' from='hidden' to='visible' fill='freeze' />\n";
    assertEquals(expected, inactive.toSVG(speed));
  }


}
