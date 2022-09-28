import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import cs5004.animator.model.Model;
import cs5004.animator.model.helpers.Canvas;
import cs5004.animator.model.motions.IViewMotion;
import cs5004.animator.model.shapes.IViewShape;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for methods in IModel and IViewModel interface.
 */
public class ModelTest {

  private Model model1;
  private Model model2;

  /**
   * Set up the model.
   *
   * @throws FileNotFoundException when the file reader could not find the specified file
   */
  @Before
  public void setup() throws FileNotFoundException {
    this.model1 = (Model) AnimationReader.parseFile(
        new BufferedReader(new FileReader("smalldemo.txt")),
        new Builder());

    this.model2 = (Model) AnimationReader.parseFile(
        new BufferedReader(new FileReader("buildings.txt")),
        new Builder());
  }

  /**
   * Tests setBounds method with invalid width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsWithInvalidWidth() {
    this.model1.setBounds(12, 26, -24, 10);
  }

  /**
   * Tests setBounds method with invalid height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetBoundsWithInvalidHeight() {
    this.model1.setBounds(12, 26, 24, -10);
  }

  /**
   * Tests setBound and getCanvas with valid inputs.
   */
  @Test
  public void testCanvas() {
    this.model1.setBounds(2, 3, 120, 560);
    Canvas canvas = this.model1.getCanvas();
    assertEquals(2, canvas.getX());
    assertEquals(3, canvas.getY());
    assertEquals(120, canvas.getWidth());
    assertEquals(560, canvas.getHeight());
  }

  /**
   * Tests getEndTime method.
   */
  @Test
  public void testGetEndTime() {
    assertEquals(100, this.model1.getEndTime());
    assertEquals(200, this.model2.getEndTime());
  }

  /**
   * Tests declareShape with invalid input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeclareShapeInvalid() {
    this.model1.declareShape("T", "triangle");
  }

  /**
   * Tests declareShape method.
   */
  @Test
  public void testDeclareShape() {
    int initialSize = this.model2.getAllShapeSorted().size();
    this.model2.declareShape("newRect", "rectangle");
    assertEquals(initialSize + 1,
        this.model2.getAllShapeSorted().size());
  }

  /**
   * Tests addMotion with invalid name of shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalid() {
    this.model2.addMotion("Invalid",
        1, 23, 23, 5, 23, 3, 123, 23,
        100, 29, 53, 5, 23, 3, 123, 23);
  }

  /**
   * Tests addMotion method.
   */
  @Test
  public void testAddMotion() {
    int initialSize = this.model2.getAllMotionSorted().size();
    this.model2.addMotion("moon",
        200, 200, 200, 100, 100, 229, 229, 255,
        307, 300, 150, 100, 100, 229, 229, 255);
    assertEquals(initialSize + 1, this.model2.getAllMotionSorted().size());
    assertEquals(307, this.model2.getEndTime());
  }

  /**
   * Tests getAllShapesSorted method.
   */
  @Test
  public void testGetAllShapesSorted() {
    List<IViewShape> lstOfShapes = this.model2.getAllShapeSorted();
    for (int i = 1; i < lstOfShapes.size(); i++) {
      assertTrue(lstOfShapes.get(i).getAppearTime()
          >= lstOfShapes.get(i - 1).getAppearTime());
    }
  }

  /**
   * Tests getAllMotionSorted method.
   */
  @Test
  public void testGetAllMotionSorted() {
    List<IViewMotion> lstOfMotion = this.model2.getAllMotionSorted();
    for (int i = 1; i < lstOfMotion.size(); i++) {
      assertTrue(lstOfMotion.get(i).getStartTime()
          >= lstOfMotion.get(i - 1).getStartTime());
    }
  }

  /**
   * Tests getShapesToPaint method.
   */
  @Test
  public void testGetShapesToPaint() {
    List<IViewShape> lstOfShapes = this.model1.getShapesToPaint(3);
    assertEquals(1, lstOfShapes.size());

    lstOfShapes = this.model1.getShapesToPaint(70);
    assertEquals(2, lstOfShapes.size());

    this.model1.addMotion("C",
        100, 440, 370, 120, 60, 0, 255, 0,
        200, 440, 370, 60, 30, 0, 255, 0);
    lstOfShapes = this.model1.getShapesToPaint(120);
    assertEquals(1, lstOfShapes.size());
  }


}
