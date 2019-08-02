# API_Slogo

__1. What is the result of parsing and who receives it?__
    User input is received, and the idea is that this input ('line of code') will be decoded, executed, and then return a new list of views containing lines and the new view for the turtle. This will be sent to the front end and change the GUI accordingly. 
    The back end, which recieves the original string will complete the act of decoding and executing. Decoding will consist of converting the string input into a list of Instruction objects. Excecution will conist of iterating through this list of instruction objects and acting upon them in the following way: 
        1. Creating an necessary lines (if the instruction has a distance)
        2. Updating a copy of the turtle view.

Each line created and the final version of the turtle view will then be returned. 
   
__2. When does parsing need to take place and what does it need to start properly?__

The parsing takes place the moment the user sends an input (a command in this case). Likely this will be triggered by a key press or a click of a run button. From there the parsing discussed above will occur. We will need the string to decode and the current position and orientation of the turtle (or unicorn).

__3. When are errors detected and how are they reported?__

Errors will be detected the moment a command is parsed by the back end. This can be something that is incorrectly formatted or sends an unrecognized (mispelled or just not a coded commanded). The front end ideally would do no logical processing and just take in the command while the back end will parse it and find out if something is wrong. In this case, it would send an error to the front end and cause the view to show an error message and tell the user what they did incorrectly.

__4. What do commands know, when do they know it, and how do they get it?__
Commands are just a string, they won't know anything. They will be parsed and converted into an Instruction which will have a direction and distance associated with it. 

__5. How is the GUI updated after a command has completed execution?__

After the backend receives the command in the form of a String, the backend will do all the work to decode. What will be returned is a new view, which the front end will utilize to update a GUI. The old turtle and lines drawn will be removed and the new view will be put into place.
