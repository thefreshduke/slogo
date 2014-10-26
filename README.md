slogo
=====

Provides a development environment that supports users to write SLogo programs.

=======
Jack Baskin, Rahul Harikrishnan, Duke Kim, Petra Ronald, $cotty $haw

Start Date: September 29

Finish Date: October 26

Estimated Work Hours: 600

Responsibilities
=======

Jack: Frontend, interaction with controller

Petra: Frontend, frontend design, GUI, buttons, grids, command history, Turtle, frontend refactoring

Duke: Backend/Middle, containers, gateways, controller, ControlCommands, VariableCommands, JUnit tests, recursive parsing, resource bundles, overall design

Rahul: Backend/Middle, save/load, controller, ViewCommands, TurtleCommands, ControlCommands, JUnit tests, middle design, translators

Scotty: Backend/Middle, parser, factories, command hierarchies, ExpressionCommands, linguistics, JUnit tests, backend and middle design

Resources
=======
Dr. Duvall, Cody Lieu

Start File: Slogo.java (View package)

Resource Files: The entire resources package

Important Information
=======
The user may type in commands or click on buttons to interact with the program. There is the ability to add extra grids and turtles under the Add option. There are also options for changing the background, the pen, the languages, etc. The frontend also allows keyboard interactions. With the backend, the user to add and create turtles, commands, etc. In fact, the user is allowed to enter nested commands, or even absurdly long inputs full of internal commands.

Problems
=======
We were not able to maintain our original design and therefore have some blurring of the frontend and backend. Our powerful middle does help separate the two, but some aspects of the design, such as the location of the turtle (originally in the backend as a model), changed during the last week of the project.

Redeeming Qualities and Extra Features
=======
Our project is anchored by a powerful reflective middle, which consists of the parser, the translators, the factories, the gateways, the containers, and the controller. They work to convert the user input into commands that allow the user to interact with the program.

The parser breaks down the user input, including valid examples in which commands may be freely substituted in place of arguments of a command, into separate words. The translators then convert the words into keys that correspond to command classes so the factories can build the command objects recursively. This process relies on a strong command hierarchy designed early in the project and maintained in essence throughout the implementation of SLOGO.

The gateways hold all the information related to the backend of the program. Most of this information is required for commands. The gateways also limit information access in the extending commands to minimize the risks of errors. Method command execution can remain general, and data access is streamlined to ideally limit repeated data. The containers unify all containers (turtles, grids, variables) to one specific type. This allows the extension of methods in the interface that share behavior and maintain a sense of scope in the “active” and “inactive” regardless of turtles, grids, variables, etc. Our controller handles communication between the frontend and backend. It also checks for errors, allows the saving and loading of files, and throws exceptions that may arise. In short, it acts as the brain of our program.

Our JUnit tests show that our commands are all functioning correctly, and much of that owes credit to our robust command hierarchy design. This very simply and nearly organizes all commands, including future additions. Finally, our additional linguistic translations can help expand SLOGO to many others around the world, if the user chooses to.

Impressions
=======
Peer programming was extremely useful throughout this project. Communication was also extremely important. Much of the divergence and later issues during our implementation resulted from not working, or at least not communicating, as a team. Increased interaction and communication did, however, build stronger team chemistry.

Design Document
=======
Our Design Document for Cell Society has been added as a PDF named “PartOnePlan.pdf" to the src folder. It has not been altered since its original submission, so any current information at the time of submitting the entire Cell Society project will be found above in this README.
