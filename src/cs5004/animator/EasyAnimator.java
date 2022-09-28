package cs5004.animator;


import cs5004.animator.controller.IController;
import cs5004.animator.util.Builder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.controller.Controller;
import cs5004.animator.model.IViewModel;
import cs5004.animator.view.IView;
import cs5004.animator.view.ViewFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The main method in this class runs the animation.
 */
public class EasyAnimator {

  /**
   * Parse the user inputs and then run the animation.
   * @param args the arguments given by users
   */
  public static void main(String[] args) {

    JFrame frame = new JFrame();

    String input = "";
    String out = "";
    String viewType = "";
    int speed = 1;

    IViewModel viewModel;
    IController control;
    IView view;
    Appendable output = System.out;

    // parse user inputs
    for (int i = 0; i < args.length; i++) {
      String cmd = args[i];

      switch (cmd) {
        case "-in":
          input = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
          break;
        case "-out":
          out = args[i + 1];
          break;
        case "-speed":
          try {
            speed = Integer.parseInt(args[i + 1]);
            break;
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                e.getMessage(),
                "number format error",
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
          }
          break;
        default:
          break;
      }
    }

    if (input.isEmpty() || viewType.isEmpty()) {
      JOptionPane.showMessageDialog(frame,
          "Providing an input file and a view are mandatory",
          "Error",
          JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }

    try {
      viewModel = (IViewModel) AnimationReader.parseFile(
          new BufferedReader(new FileReader(input)),
          new Builder());

      control = new Controller();

      view = ViewFactory.createView(viewType, viewModel);
      if (!out.isEmpty()) {
        output = new BufferedWriter(new FileWriter(out));
      }
      if (!view.isGraphic()) {
        view.setAppendable(output);
      }

      control.setView(view);

      control.run(speed);

      if (!out.isEmpty()) {
        ((BufferedWriter) output).flush();
        ((BufferedWriter) output).close();
      }

    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(frame,
          e.getMessage(),
          "File Not Found",
          JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(frame,
          e.getMessage(),
          "Invalid Input",
          JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(frame,
          e.getMessage(),
          "I/O Error",
          JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
  }

}



