# API_CellSociety.md

#### Team 15  - Simulation API
* The method should not be part of the API (i.e., it should not be public)
    * isOuterBoundary(int row, int col)
    * determineDirection(int row, int col) in the triangular grid class
    * createSugarPatch and createAgent should both not be public
    * getColor should not be public

* The method should be part of the external API because it helps people on the team working in other parts write their code
    * calculateCellSize
    * getActorNames
    * getThresholdRange
    * updateThreshold
    * step
    * stopPressed
    * startPressed
    * resetPressed
    * getType in grid
    * getPopulations
    * getMaxPopulation
    * getView()
    * determineDirection()
All used by the GUI
* The method should be part of the internal API because it helps future coders within its part extend it
    * getAllActors()
    * addActor()
    * removeActor()
    * getNeighborLocations
    * getEmptyLocations
    * fillGridRandomly()
    * fillGridFromArrayOfString()
    * createActor()
    * filLGrid()
    * act()
    * aveCurrentNeighborsState()
    * getIsTypeA()
    * 
 
#### Team 7 - XML Configuration API
* Method should not be part of API (shouldn't be public)
    * XMLParser.getRootElement()
    * XMLParser.isValidFile()
    * XMLParser.getAttribute()
    * XMLParser.getDocumentBuilder()
    
* Method should be part of external API
    * Initializer.getConfigFile()
    * Initializer.getHandle()
    * Initializer.handleInitialize()

* Method should be part of internal API 
    * Grid.getAdjacentNodesMooreNeighborhood()
    * Grid.getAdjacentNodesVonNeumannNeighborhood()
    * Grid.createShape() 
    * XMLParser.getData()
    * XMLParser.setDataFields()


##### Internal:
* Simulation:The internal API for the simulation section of this project encompassed interactions between the simulation, actor and grid classes. The methods included allow Actors to use grid methods in order to get their neighbors, as well as other Actor methods to access the state of these neighbors. Both the Actors and Grid class are created by the simulation class. The simulation sub class's createActors method is used by the grid to correctly fill itself in the fillGrid methods. 

* Configuration: The internal configuration API encompasses how the different classes necessary to initialize a simulation grid from an XML configuration file interact with each other. In this case, the Grid class contains the methods to initialize the grid type and the different neighbors that each cell will have given an XML file. The XMLParser's getData() method passes data from configuration files to the code that initializes a Grid.

* Visualization: No members of our group were on the visualization

##### External:
* Simulation: The external API for the simulation component should allow for interaction between other components of the project (configuration, visualization). For example, the getView and getPopulations methods are used by the GUI to correctly display the necessary information. The configuration class interacts with the simulation by passing a map of configuration properties into the simulation constructors. The simulation access the EnumParams in order to keep all variables consistent.

* Configuration: The external configuration API allows for interaction with other project components (i.e., simulation and visualization). It allows different cell types to be introduced into a specific simulation type, initializes neighbors based on simulation requirements, passes a JavaFX Group object with the correct cells initialized on it to the main visualization classes. The Initializer class returns an initialized Game object that contains a GUI object which can be displayed with the visualization code

* Visualization:No members of our group were on the visualization



 

