# My Personal Project

## A Custom bingo Game

---

**References:**

Main and DeckBuilderApp were created with assistance from TellerApp
* https://github.students.cs.ubc.ca/CPSC210/TellerApp

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

&nbsp;

###User Stories P1:

* As a user, I want to be able to add an arbitrary number of items to my bingo board deck.
* As a user, I want to be able to be able to edit the description of each card.
* As a user, I want to be able to toggle favourite status of each card.
* As a user, I want to edit the number of points a card is worth based on difficulty (3 levels).
* As a user, I want to be able to view the titles of the items added so far. 


###User Stories P2:

* As a user, I want to be able to save a deck of cards to a file
* As a user, I want to be able to load a deck of cards from a file