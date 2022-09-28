Easy Animator

Structure

Class Easy Animator
  entrance of the program

Model
  Interface IModel (mutable)
      + setBounds：sets the drawing context for the animation
      + declareShape: adds a new shape
      + addMotion: adds new transformation

  Interface IViewModel (immutable)
      + getCanvas: gets the drawing context
      + getEndTime: gets the end time of the animation

      + getAllShapeSorted: gets a list of immutable shapes sorted by the time they appear
      + getAllMotionSorted: gets a list of immutable transformation sorted by the time
                            transformation start
      + getShapesToPaint: get a list of immutable shape which should be painted at a specific time

     [Change] Initially, all the public methods that the Model class implements are in
     the IModel interface. In order to make view only have access to an immutable animation model,
     IViewModel interface is created to have only accessor methods. And the Model class is
     designed to implement both IModel and IViewModel interface. When the model interface
     is provided to the view, a IViewModel interface is provided. Therefore, the view only
     has access to a restricted model interface.


  Class Model implements IModel and IViewModel
      stores shapes in a name-to-shape hashmap, so a shape could be easily retrieved by its name
      stores shapes in a list in the input order
      uses a name-to-shape hashmap to store the current state of shapes in animation


   Package helpers:
      1) Class ColorRGB (immutable): represents the color of the shape
      2) Class Point2D (immutable): represents the reference position(top-left corner) of the shape
      3) Class Canvas (immutable): store the drawing context for the animation
      4) Class State (mutable):
                      stores the state of the shape before and after transformation
                      (time-point, x-coordinate, y-coordinate, width, height,
                       red color-value, green color-value, blue color-value)

   Package shapes:
      Interface IShape (mutable): contains setter methods.
      Interface IViewShape (immutable): contains getter methods,
                                        toString, and toSVG method.

      Class AbstractShape implements IShape and IViewShape.

      Concrete Classes:
        Class Rectangle extends AbstractShape
        Class Oval extends AbstractShape

   Package motions:
      Interface IMotion (mutable):
        + getStartState: returns the initial state of the shape before animation
                         return type State is mutable
        + getEndState: returns the end state of the shape after transformation
                       return type State is mutable
      Interface IViewMotion (immutable): contains getter methods, toString, and
                                         toSVG method

      Class AbstractMotion implement IMotion and IViewMotion

      Concrete Classes:
         Class Move extends AbstractMotion
         Class Scale extends AbstractMotion
         Class ChangeColor extends AbstractMotion
         Class InActive extends AbstractMotion


----------------------------------------------------------------------
View
  Interface IView:
    + display: displays the animation
    + setAppendable

  Interface GUI:
    includes some features that a playback view should support.

  Concrete Classes:
      Class TextView implements IView
        - has access to the animation data through interface IViewModel
        - renders the animation in text format

      Class SVGView implements IView
        - has access to the animation data through interface IViewModel
        - renders the animation in SVG format
        - cannot loop

      Class VisualView implements IView
        - has access to the animation data through interface IViewModel
        - delegates the operation of displaying animation to the
          component AnimationPanel
        - does not support setAppendable method

      Class GUIView (playback view) implements IView and GUI
        - has access to the animation data through interface IViewModel
        - delegates the operation of displaying animation and GUI features
          to the component AnimationPanel
        - does not support setAppendable method

        [Guide]
          Button
          1) Click the start/pause button to start, pause or resume the animation.
          2) Click the restart button to restart the animation.
             All the removed shapes would be restored.
          3) Click the enable/disable loop button.
             The animation is set to loop by default.
          4) Select a factor in JComboBox to change the animation speed.
             The animation speed would be set to factor * initial speed provided by the user.
          5) Input the name of the shape in the text field, then click the remove button,
             the shape with the given name would be removed.
             example: when displaying buildings.txt, input "moon" in the text field,
             then the moon would be removed

          KeyBoard
          6) type 'k' to start, pause or resume the animation
          7) type 'r' to restart the animation

          Mouse
          8) left click mouse on the panel to start, pause or resume animation
          9) right click mouse on the panel to restart

  Package listeners:
    Concrete classes:
      ButtonListener
      SpeedOptionListener (listen to JComboBox)
      KeyboardListener
      MouseListener

--------------------------------------------------------------------
Controller

  Interface IController:
    + run: runs the animation at the given speed
    + setView: sets the view for the animation

  Class Controller implements IController
      The controller prepares four maps:
        - button action command -> runnable-to-be-executed
        - key-typed -> runnable-to-be-executed
        - selected speed option -> runnable-to-be-executed
        - clicked mouse button -> runnable-to-be-executed

      The controller creates these map objects and uses them to initialize listeners,
      and finally pass these listeners to the GUIView.
