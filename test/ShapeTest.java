import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;


import cs5004.animator.model.motions.Move;
import cs5004.animator.model.shapes.IViewShape;
import cs5004.animator.model.shapes.Oval;
import cs5004.animator.model.shapes.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for methods in AbstractShape, Oval and Rectangle class.
 */
public class ShapeTest {

  private Rectangle rectangle1;
  private Rectangle rectangle2;

  private Oval oval1;
  private Oval oval2;

  /**
   * sets up ovals and rectangles.
   */
  @Before
  public void setUp() {
    this.oval1 = new Oval("C", 500, 100, 16, 7,
        1, 60, 30, 6, 100);
    this.oval2 = new Oval("C2");

    this.rectangle1 = new Rectangle("R", 200, 200,
        1, 9, 12, 50, 100, 1, 100);
    this.rectangle2 = new Rectangle("R2");
  }

  /**
   * Test get Methods.
   */
  @Test
  public void testGet() {
    assertEquals("C2", oval2.getName());
    assertEquals("R2", rectangle2.getName());

    assertEquals("C", this.oval1.getName());
    assertEquals(500, this.oval1.getReferencePos().getX());
    assertEquals(100, this.oval1.getReferencePos().getY());
    assertEquals(16, this.oval1.getWidth());
    assertEquals(7, this.oval1.getHeight());
    assertEquals(1, this.oval1.getColor().getRed());
    assertEquals(60, this.oval1.getColor().getGreen());
    assertEquals(30,this.oval1.getColor().getBlue());
    assertEquals(6, this.oval1.getAppearTime());
    assertEquals(100, this.oval1.getDisappearTime());
    assertEquals("ellipse", this.oval1.getType());

    assertEquals("R", this.rectangle1.getName());
    assertEquals(200, this.rectangle1.getReferencePos().getX());
    assertEquals(200, this.rectangle1.getReferencePos().getY());
    assertEquals(1, this.rectangle1.getWidth());
    assertEquals(9, this.rectangle1.getHeight());
    assertEquals(12, this.rectangle1.getColor().getRed());
    assertEquals(50, this.rectangle1.getColor().getGreen());
    assertEquals(100,this.rectangle1.getColor().getBlue());
    assertEquals(1, this.rectangle1.getAppearTime());
    assertEquals(100, this.rectangle1.getDisappearTime());
    assertEquals("rectangle", this.rectangle1.getType());
  }

  /**
   * Tests set methods.
   */
  @Test
  public void testSet() {
    oval2.setWidth(16);
    assertEquals(16, this.oval2.getWidth());

    oval2.setHeight(19);
    assertEquals(19, oval2.getHeight());

    oval2.setReferencePos(18, 24);
    assertEquals(18, oval2.getReferencePos().getX());
    assertEquals(24, oval2.getReferencePos().getY());

    oval2.setColor(23, 45, 212);
    assertEquals(23, oval2.getColor().getRed());
    assertEquals(45, oval2.getColor().getGreen());
    assertEquals(212, oval2.getColor().getBlue());

    oval2.setAppearTime(10);
    assertEquals(10, oval2.getAppearTime());

    oval2.setDisappearTime(200);
    assertEquals(200, oval2.getDisappearTime());

    rectangle2.setState(10, 90, 12, 32 ,9, 45, 201);
    assertEquals(10, rectangle2.getReferencePos().getX());
    assertEquals(90, rectangle2.getReferencePos().getY());
    assertEquals(12, rectangle2.getWidth());
    assertEquals(32, rectangle2.getHeight());
    assertEquals(9, rectangle2.getColor().getRed());
    assertEquals(45, rectangle2.getColor().getGreen());
    assertEquals(201, rectangle2.getColor().getBlue());
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    String expected = "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (508, 103), X radius: 8, Y radius: 3, Color: rgb(1, 60, 30)\n"
        + "Appears at t=6.0s\n"
        + "Disappears at t=100.0s\n";
    assertEquals(expected, oval1.toString());

    expected = "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200, 209), Width: 1, Height: 9, Color: rgb(12, 50, 100)\n"
        + "Appears at t=1.0s\n"
        + "Disappears at t=100.0s\n";
    assertEquals(expected, rectangle1.toString());
  }

  /**
   * Tests getMotion and addMotion method.
   */
  @Test
  public void testGetAndAddMotion() {
    this.rectangle1.addMotion(new Move(this.rectangle2, 32, 3,4, 6, 13,
        12,54,65, 45, 64, 45, 4, 13, 12, 54, 65));
    assertNotNull(this.rectangle1.getMotion());
    assertEquals(1, this.rectangle1.getMotion().size());
  }

  /**
   * Test copy method.
   */
  @Test
  public void testCopy() {
    IViewShape r = this.rectangle2.copy();
    assertEquals(this.rectangle2.getName(), r.getName());
    assertNotSame(this.rectangle2, r);

    IViewShape e = this.oval2.copy();
    assertEquals(this.oval2.getName(), e.getName());
    assertNotSame(this.oval2, e);
  }

  /**
   * Tests toSVG() method.
   */
  @Test
  public void testToSVG() {
    String expected = "\n<ellipse id='C' cx='508' cy='103' rx='8' ry='3' fill='rgb(1, 60, 30)' "
        + "visibility='hidden'>\n"
        + "***</ellipse>\n";
    assertEquals(expected, this.oval1.toSVG());

    expected = "\n<rect id='R' x='200' y='200' width='1' height='9' "
        + "fill='rgb(12, 50, 100)' visibility='hidden'>\n"
        + "***</rect>\n";
    assertEquals(expected, this.rectangle1.toSVG());
  }

  /**
   * Tests toString(speed) method.
   */
  @Test
  public void testToStringSpeed() {
    int speed = 20;
    String expected = "Name: C\n"
        + "Type: ellipse\n"
        + "Center: (508, 103), X radius: 8, Y radius: 3, Color: rgb(1, 60, 30)\n"
        + "Appears at t=0.3s\n"
        + "Disappears at t=5.0s\n";
    assertEquals(expected, this.oval1.toString(speed));

    expected = "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200, 209), Width: 1, Height: 9, Color: rgb(12, 50, 100)\n"
        + "Appears at t=0.1s\n"
        + "Disappears at t=5.0s\n";
    assertEquals(expected, this.rectangle1.toString(speed));
  }

}
