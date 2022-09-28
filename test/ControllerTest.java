import static org.junit.Assert.assertEquals;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.model.IViewModel;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for methods in controller class.
 */
public class ControllerTest {

  private IViewModel model1;

  /**
   * Set up the text view.
   *
   * @throws FileNotFoundException when the file reader could not find the specified file
   */
  @Before
  public void setup() throws FileNotFoundException {
    this.model1 = (IViewModel) AnimationReader.parseFile(
        new BufferedReader(new FileReader("smalldemo.txt")),
        new Builder());
  }

  /**
   * Tests run method with invalid speed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRunInvalidSpeed() {
    IView textView = new TextView(this.model1);
    textView.setAppendable(new StringBuilder());

    IController control = new Controller();
    control.setView(textView);

    control.run(-20);
  }

  /**
   * Tests run method using Text View.
   */
  @Test
  public void testRunTextView() {
    StringBuilder output = new StringBuilder();
    IView textView = new TextView(this.model1);
    textView.setAppendable(output);

    IController control = new Controller();
    control.setView(textView);

    control.run(30);

    String[] lines = output.toString().split("\n");
    assertEquals(19, lines.length);
    assertEquals("Shape R moves from (200, 300) to (300, 400) "
        + "from t=0.3s to t=1.7s", lines[12]);
    assertEquals("Shape C moves from (500, 100) to (500, 280) "
        + "from t=0.7s to t=1.7s", lines[13]);
  }

  /**
   * Tests run method using SVG View.
   */
  @Test
  public void testRunSVGView() {
    StringBuilder output = new StringBuilder();
    IView svgView = new SVGView(this.model1);
    svgView.setAppendable(output);

    IController control = new Controller();
    control.setView(svgView);

    control.run(30);

    String[] lines = output.toString().split("\n");
    assertEquals(18, lines.length);
    assertEquals("<rect id='R' x='200' y='200' width='50' height='100' "
        + "fill='rgb(255, 0, 0)' visibility='hidden'>", lines[1]);
    assertEquals("\t<animate attributeType='xml' begin='33.3ms' dur='0.1ms' "
        + "attributeName='visibility' from='hidden' to='visible' fill='freeze' />", lines[2]);
    assertEquals("\t<animate attributeType='xml' begin='333.3ms' dur='1333.3ms' "
        + "attributeName='x' from='200' to='300' fill='freeze' />", lines[3]);

    assertEquals("<ellipse id='C' cx='500' cy='100' "
        + "rx='60' ry='30' fill='rgb(0, 0, 255)' visibility='hidden'>", lines[10]);
    assertEquals("\t<animate attributeType='xml' begin='200.0ms' "
            + "dur='0.1ms' attributeName='visibility' from='hidden' to='visible' fill='freeze' />",
        lines[11]);
    assertEquals("\t<animate attributeType='xml' begin='666.7ms' dur='1000.0ms' "
        + "attributeName='cy' from='100' to='280' fill='freeze' />", lines[12]);
  }



}