# slogo

Names: Marley Zelinger (mpz5) , Ryan Piersma (rrp17) , Ethan Ho (eh154) , Janice Liu (jl691)

Date Started: Oct. 4, 2018
Date Finished: Oct. 28, 2018
Hours Estimate: Roughly 15-20 hours per week of implementation per person

Resources Used In Completing the Project:
JavaFX API
Using Model-View-Controller Design Pattern: https://developer.mozilla.org/en-US/docs/Web/Apps/Fundamentals/Modern_web_app_architecture/MVC_architecture

File containing Main class: Main (path: slogo_team06/src/Main)

Files used to test the project, expected error handling:
** Code examples used to debug backend **
slogo_team06/data/examples

#### Data/Resource files:

** Sources for images that can be chosen in our SLogo project **
slogo_team06/data/images/* 
slogo_team06/resources/images/* (contains turtle.png, the default image)


** File used for printing list of available commands when 'help' is typed **
slogo_team06/data/commandlist.txt


** Files for identifying the commands in different languages **
slogo_team06/resources/languages/*.properties


** File for identifying node properties **
slogo_team06/resources/nodes/nodes.properties


#### Information about using the program:
* Run program using the Main class
* GUI allows for selecting a background color, new image for turtle, new pen color, language selection
* Run a single command with the "single line input" button, run multiple commands with the "multi-line input option"
* Prev/Next buttons cycle through the command history box
* Click on commands in command history to execute them
* In order to save all current saved functions and variables to a file type "save" then run the command.
* In order to load all previously saved functions and variables from the save.slogo file, write load then run.

#### Decisions/Assumptions/Simplifications:
* Use an overall Model-View-Controller design pattern as the structure for the project

#### Bugs/Crashes/Problems:
* Front End-

* Help command structure only supported in English
* "Clearscreen" command does not erase lines from screen

Back End-

* After loading in variables and functions at least one other command must be run before they appear in the front end.

#### Extra Features:
* Execution of commands in command line by clicking on them in history
* Save/Load files with SLogo commands
* Defined "help" structure for English commands
* Support for multiple turtles

#### Impressions of the Assignment:

Well designed and especially helpful in the area of developing code in an environment
where you aren't aware of how other parts of the project are implemented and 
inter-team cooperation is absolutely essential in order to make any real progress


