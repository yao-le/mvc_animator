import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs5004.animator.model.IViewModel;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for methods in TextView class.
 */
public class TextViewTest {


  private IView textView;

  /**
   * Set up the text view.
   *
   * @throws FileNotFoundException when the file reader could not find the specified file
   */
  @Before
  public void setup() throws FileNotFoundException {
    IViewModel model1 = (IViewModel) AnimationReader.parseFile(
        new BufferedReader(new FileReader("smalldemo.txt")),
        new Builder());
    this.textView = new TextView(model1);
  }

  /**
   * Tests display method.
   */
  @Test
  public void testDisplay() {
    StringBuilder output = new StringBuilder();
    this.textView.setAppendable(output);

    this.textView.display(20);
    String[] lines = output.toString().split("\n");

    assertEquals(19, lines.length);
    assertEquals("Shape R moves from (200, 300) to (300, 400) "
            + "from t=0.5s to t=2.5s", lines[12]);
    assertEquals("Shape C moves from (500, 100) to (500, 280) "
        + "from t=1.0s to t=2.5s", lines[13]);


    output = new StringBuilder();
    this.textView.setAppendable(output);
    this.textView.display(50);
    lines = output.toString().split("\n");


    assertEquals(19, lines.length);
    assertEquals("Shape R moves from (200, 300) to (300, 400) "
        + "from t=0.2s to t=1.0s", lines[12]);
    assertEquals("Shape C moves from (500, 100) to (500, 280) "
        + "from t=0.4s to t=1.0s", lines[13]);
  }

  /**
   * Tests isGUI method.
   */
  @Test
  public void testIsGUI() {
    assertFalse(this.textView.isGUI());
  }

  /**
   * Tests isGraphic method.
   */
  @Test
  public void testIsGraphic() {
    assertFalse(this.textView.isGraphic());
  }

}
