import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs5004.animator.model.IViewModel;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for methods in SVGView class.
 */
public class SVGViewTest {

  private IView svgView;

  /**
   * Sets up the svg view.
   *
   * @throws FileNotFoundException when the file reader could not find the specified file
   */
  @Before
  public void setup() throws FileNotFoundException {
    IViewModel model = (IViewModel) AnimationReader.parseFile(
        new BufferedReader(new FileReader("smalldemo.txt")),
        new Builder());
    this.svgView = new SVGView(model);
  }

  /**
   * Tests display method.
   */
  @Test
  public void testDisplay() {
    StringBuilder output = new StringBuilder();
    this.svgView.setAppendable(output);

    this.svgView.display(30);
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

  /**
   * Tests isGUI method.
   */
  @Test
  public void testIsGUI() {
    assertFalse(this.svgView.isGUI());
  }

  /**
   * Tests isGraphic method.
   */
  @Test
  public void testIsGraphic() {
    assertFalse(this.svgView.isGraphic());
  }


}
