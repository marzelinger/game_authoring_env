# Design of SLogo: Team 6

### High Level Design Goals
- Effectively use the Model-View-Controller design pattern to implement a computer
language/GUI combination known as SLogo
- Make use of pre-designed APIs in order to communicate between and within the front 
and back end of the project
- Avoid code smells
- Utilize inheritance and other object oriented concepts (polymorphism, abstract classes)
in order to enhance the flexibility of the project code

### How to Add New Features
##### Adding to the project (copied from Analysis, rrp17)

To add a new command to the language:
* The command must be added to all relevant language files
* A TokenType needs to be added for the command
* A Node class for the new command is created
* Node resources file must have the new TokenType
* TokenType needs to be added to the necessary maps in the Token class
as an input saying how many arguments it has

To add a new component to the front end:
* Determine which of the five portions of the BorderPane the new component should be added to (top,
bottom, left, or right - center is reserved for the Turtle screen)
* If needed, extend a JavaFX class to encapsulate any state the new component would need to extend 
from JavaFX
* If the component is adding to the top menus, go to the SetupView class, if the component is adding to
the bottom command history/input section go to MainView, if the component is adding to the left, edit
the MainView command to initialize a non-null object, if adding to the right component go to SetupView

To add a new feature from the specification that wasn't implemented (Clicking to edit the value of
user-defined variables):

* Add a MouseClick event listener to the Text objects in the user-defined variables box (implemented
in the SetupView class) that
   - records which variable is being modified
   - records the new value of the variable
   - indexes into the Compiler class' map of user-defined variables to their values and rewrites
   the value (probably involving a setter method the Compiler would have to set the map)

### Major Design Choices (Copied from analysis - rrp17)
#### 2 Design Decisions Made

One important design decision that we made was in apportioning the graphic and state information of the
turtle between the front and back ends. For example, our original idea was that the back end would pass
complete graphical objects back to the front end that the front end would simply display. However, this
was decided against because the back end's functionality has nothing to do with the graphics, thus, we
decided to create the custom **TurtleStatus** class to encapsulate the state of a turtle object, while allowing
the front end to work with a JavaFX **ImageView** object whose position/visibility would be changed based
on the TurtleStatus objects received from the backend. Although the result of this decision was the necessity
of creating the TurtleStatus class, etc. I think that our original idea was flawed and that our new decision
incorporated much better decision making into the project. Divorcing the graphics of the turtle from its 
actual state allowed for ease in implementing many features, like selecting the image for the turtle. This
would have been a huge pain if we would have had to pass from the front end to the back end which image the
turtle contained- it would be information that was purely useless to the back end in this regard.

A second important design decision made was in implementing the command history for our project. The end result
was that it was encapsulated by extending the JavaFX **ScrollPane** object and then storing its state inside this, 
but we had discussed separating the information and the graphics- keeping the information stored in data structure
like a map of commands to results and the graphical object without any state. This would allow for greater
flexibility in the sense that the data in the command history could be easily manipulated without having to 
worry about interactions with the graphical aspect of the command history. I think that the current implementation
is good in the sense that it locks in how the command history is tied to the graphics of the GUI, but leaves
a very closed situation in terms of trying to make changes to how the history might be displayed or modified.
However, I would still pick the design we have now because I am of the opinion that our closing of this aspect of
the implementation gives a nice defined structure to this part of the project.

### Assumptions/Decisions
See our analyses
Pi = 3.15