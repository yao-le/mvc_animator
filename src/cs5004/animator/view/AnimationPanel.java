package cs5004.animator.view;

import cs5004.animator.model.IViewModel;
import cs5004.animator.model.helpers.Canvas;
import cs5004.animator.model.shapes.IViewShape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Represents a JPanel objects used to display the animation.
 */
public class AnimationPanel extends JPanel implements ActionListener {

  private double currTime;
  private boolean loop;
  private boolean running;

  private int initialDelay;
  private Timer timer;

  private final IViewModel viewModel;

  private List<IViewShape> lstOfShapes;
  private final Set<String> nameOfDeletedShapes;

  /**
   * Constructs an animation panel.
   * @param viewModel an immutable animation model used to transfer data from
   *                  model to view
   */
  public AnimationPanel(IViewModel viewModel) {
    super(true);

    this.viewModel = viewModel;

    Canvas canvas = this.viewModel.getCanvas();
    this.setBackground(Color.white);
    this.setPreferredSize(new Dimension(canvas.getWidth(),
        canvas.getHeight()));

    this.currTime = 0;
    this.loop = true;
    this.running = false;
    this.lstOfShapes = this.viewModel.getShapesToPaint(0);
    this.nameOfDeletedShapes = new HashSet<>();
  }

  /**
   * Set the timer.
   * @param speed the initial speed of the animation
   */
  public void setTimer(int speed) {
    this.initialDelay = 1000 / speed;
    this.timer = new Timer(this.initialDelay, this);
    this.timer.setInitialDelay(0);
  }

  // some GUI features
  /**
   * Start or pause the animation.
   */
  public void toggle() {
    if (this.running) {
      this.timer.stop();
    } else {
      this.timer.start();
    }
    this.running = !this.running;
  }

  /**
   * Restart the animation.
   */
  public void restart() {
    this.currTime = 0;

    this.nameOfDeletedShapes.clear();

    if (!running) {
      this.timer.start();
      this.running = true;
    }
  }

  /**
   * Enable or disable loop of the animation.
   */
  public void setLoop() {
    this.loop = !this.loop;
  }

  /**
   * Slow down or speed up the animation.
   * @param speedFactor a factor used to change the speed of the animation
   */
  public void setSpeedFactor(double speedFactor) {
    this.timer.setDelay((int) (this.initialDelay / speedFactor));
  }

  /**
   * Delete the shape with the specified name.
   * @param name the name of the shape
   */
  public void deleteShape(String name) {
    this.nameOfDeletedShapes.add(name);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    for (IViewShape shape: this.lstOfShapes) {

      if (this.nameOfDeletedShapes.contains(shape.getName())) {
        continue;
      }

      g2.setColor(new Color(shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue()));

      if (shape.getType().equals("rectangle")) {
        g2.fillRect(shape.getReferencePos().getX(), shape.getReferencePos().getY(),
            shape.getWidth(), shape.getHeight());
      } else if (shape.getType().equals("ellipse")) {
        g2.fillOval(shape.getReferencePos().getX(), shape.getReferencePos().getY(),
            shape.getWidth(), shape.getHeight());
      }
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    currTime++;

    if (this.currTime > this.viewModel.getEndTime()) {
      if (loop) {
        this.currTime = 1;
      } else {
        return;
      }
    }

    this.lstOfShapes = this.viewModel.getShapesToPaint((int) currTime);
    this.repaint();
  }
}
