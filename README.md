# My Personal Project

## A Custom bingo Game

---

**References:**

See bottom of page for full reference list.

---

### What will the application do?

This application will let users generate custom bingo boards to then play by themselves or against friends. Users 
will be able to create custom "boards" with a list of items they want to include. The application will then generate 
a board with around 24<sup>1</sup> of those items for players to play with. The player can then mark off items on 
the board and save their progress to a file. The player can also export an image of the board to share with others.

**Gameplay Examples**
- Item Spotting Boards
    - **Solo Play:** Player will make a list of things<sup>2</sup> that they hope to spot. The player wins when they
      have spotted enough items based on the game type<sup>3</sup>.
    - **Competitive Play:** Players will work together to generate a list of things that they hope to spot. Boards
      will be generated from the same source and a player wins when they have spotted enough items based on the game
      type<sup>3</sup>. Depending on the settings, all players may have the same set of selected items, or they may
      have a random set of items from the full list.


- Prediction Based Boards
    - **Solo Play:** Player will make a list of events<sup>4</sup> that they predict will occur within a certain time
      frame. The player wins when they have spotted enough items based on the game type<sup>3</sup>.
    - **Competitive Play:** Players will individually make lists of events<sup>4</sup> that they predict will occur
      within a certain time frame. The player wins when they have spotted enough items based on the game type<sup>3</sup>.

---


### Who will use it?

This will be a desktop based game that can be used by anyone that wants either to make a custom bingo board or to 
track their progress on a board. Currently, it will only be a Java desktop application, but it may someday be ported 
to mobile devices (ex. Android).

---

### Why is this project of interest to you?

This application is based off an idea for a "Dog Spotting bingo" game I played with a friend. We thought it 
would be an entertaining way to keep in touch throughout the school year. In the original design, we made an image 
file for the board, and we would update the board using the image editing "pen" tool in messaging apps. When someone 
would "spot" a dog, they would send a photo to the other person and mark off that breed on the board. Eventually it 
became difficult to find the latest board in our chat and the image lost quality after repeated copying. 

Eventually with the pandemic it became harder to keep in touch with others when we weren't able to see each other 
in-person. This game, or variations of it, may be a good way for others to also keep in touch with their friends and 
family. It creates a shared activity for the players to complete together, and it can be adapted for a variety of 
settings. It can also be a way to get people outside and exploring in their community, and it can be a fun way to 
share interesting landmarks and natural life in a region. 

After reading through the project requirements for my CPSC 210 personal project, I remembered this game idea and 
felt that it would be a good way to demonstrate the skills presented in the requirements. Users would be able to 
create lists of an arbitrary size with which they can build the boards. Users could then use the boards with a 
graphical interface. 

---

**Additional Details:**

<sup>1</sup> - The number of items that get used on the board will depend on the type of gameplay and the selected 
grid size. For now, the game will only include a basic 5x5 grid with a "FREE" spot in the middle. 



<sup>2</sup> - A few "Item Spotting Board" examples include:
- Spotting different breeds of dogs (ex. _Pomeranian_, _Chihuahua_, _Dachshund_, _Great Dane_, _Samoyed_, etc.)
- Exploring and spotting local flora (ex. Local trees, such as _Douglas Fir_, _Western Red Cedar_, _Sitka Spruce_, etc.) 
- Exploring and spotting local fauna (ex. _Red squirrels_, _Douglas squirrels_, _Rufous Hummingbird_, 
  _Glaucous-winged Gull_, etc.)
- Spotting the makes / models / colors of vehicles driving by on the highway or past a certain location

<sup>3</sup> - Example games include basic bingo rules (completing a row, column or diagonal) or blackout bingo 
(completing the entire board). At first, the game will only include a blackout bingo option.

<sup>4</sup> - A few "Prediction Based Board" examples include:
- Events that will occur during an election (ex. "Party X will win electoral district Y", "Candidate P will be 
  nominated for Q", etc.)
- Events that will occur during the Olympics or during a sports season (ex. "Canada will win gold in 'Ice Hockey'", 
  "Canada will win gold in 'Freestyle skiing'", "A player from team T will be injured during the World Cup", etc.)
- Events that will occur during a movie, TV show episode, etc. (ex. "Character A won't survive", "Character B will say 
  a specific phrase", "Character C will end up with character D", etc.)
- Events that will occur during a family reunion (play this in front of your family members at your own risk...)

&nbsp;

---
---

**References:**

Created with assistance from following CPSC 210 UBC projects and course pages
* https://github.students.cs.ubc.ca/CPSC210/TellerApp
* https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
* https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabSolution
* https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
* https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
* https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
* https://learning.edge.edx.org/course/course-v1:UBC+CPSC210+all/block-v1:UBC+CPSC210+all+type@sequential+block@2319e011dd3848d5940b8d7aa19ad5d9/block-v1:UBC+CPSC210+all+type@vertical+block@45c6cfa614d8417ebcf74d1fed323c24

Also made with assistance from the following:
* https://stackoverflow.com/questions/15746984/how-to-run-jframe-maximized-in-java   (for window formatting tips)
* https://docs.oracle.com/javase/tutorial/uiswing/components/html.html               (for font editing)
* https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton     (for button sizing)
* https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html#create  (for menu bar)
* https://stackhowto.com/how-to-change-jbutton-text-on-click/    (for changing jbutton on click)
* https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui  (for general splash screen tips)
* https://stackoverflow.com/questions/8701716/how-to-remove-title-bar-in-jframe     (for removing title bars)
* https://stackoverflow.com/questions/11844927/java-transparent-window              (for making window transparent)
* https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed   (for printing log 
  when closing application)


Used resources from:
* The splash screen image has been designed using resources from Flaticon.com (https://www.flaticon.com/free-icon/bingo_2038237)

---
---


&nbsp;

###Phase P1

**User Stories:**
* As a user, I want to be able to add an arbitrary number of items to my bingo board deck.
* As a user, I want to be able to be able to edit the description of each card.
* As a user, I want to be able to toggle favourite status of each card.
* As a user, I want to edit the number of points a card is worth based on difficulty (3 levels).
* As a user, I want to be able to view the titles of the items added so far.


###Phase P2

**User Stories:**
* As a user, I want to be able to save my deck building progress to a file
* As a user, I want to be able to load my deck building progress from a file
* As a user, I want to be able to load a custom preset deck of cards from a file

###Phase P3

**Selected User Stories:**
* As a user, I want to be able to use the GUI to add a card to the deck
* As a user, I want to be able to use the GUI to edit a card's description, difficulty and favourite status
* As a user, I want to be able to use the GUI to save my deck building progress to a file
* As a user, I want to be able to use the GUI to load my deck building progress from a file
* As a user, I want to be able to use the GUI to load a custom preset deck of cards from a file

###Phase P4

**Selected User Stories:**
* As a user, I want to be able to log when a card is added to the deck
* As a user, I want to be able to log when the deck is cleared
* As a user, I want to be able to log when a card is edited

####Phase 4: Task 2

Tue Mar 29 17:50:37 PDT 2022
Added Card 'Apple'


Tue Mar 29 17:50:38 PDT 2022
Deck has been cleared.


Tue Mar 29 17:50:43 PDT 2022
Added Card 'Orange'


Tue Mar 29 17:50:48 PDT 2022
Added Card 'Nectarine'


Tue Mar 29 17:51:06 PDT 2022
Updated Description of 'Nectarine' to 'This fruit is red and orange.'


Tue Mar 29 17:51:08 PDT 2022
Set difficulty of 'Nectarine' to 'Medium'


Tue Mar 29 17:51:09 PDT 2022
Set favourite status of 'Nectarine' to 'true'



Process finished with exit code 0

####Phase 4: Task 3

A lot of this project was built while I was still learning about Java. If I had gone through more of the course 
materials before starting the project, I would likely have refactored my project differently. A few of the classes 
are currently commented out, and I would likely have structured my project differently to minimize duplication. In 
particular, I would have used proper exceptions to handle the duplicate errors that a user could generate. I would 
also have redesigned the UI classes so that the various viewing and editing windows would either fall under a 
superclass, or they would be put into the main DeckBuilder class entirely. I also considered potentially refactoring 
to have AppDeckBuilderGUI and BingoGameGUI be under a superclass, but I wanted to add functionality so that the 
programs can be run independently in the future. I would like the user to be able to choose to either make a deck, 
or to play the game out of the decks that were already made. This functionality, and other future functionality, 
made it so that I want to keep these classes separate.