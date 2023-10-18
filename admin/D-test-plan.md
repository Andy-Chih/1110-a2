
# Test plan

## List of classes

* List below all classes in your implementation that should have unit tests.
* For each class, list methods that can be tested in isolation.
* For each class, if there are conditions on the class' behaviour that cannot
  be tested by calling one method in isolation, give at least one example of
  a test for such a condition.

Do **not** include in your test plan the `Marrakech` class or the predefined
static methods for which we have already provided unit tests.

* GameInfo
  * updateFromString(gameString) can be tested in isolation
* Assam
  * moveAssam(amount) can be tested in isolation
  * initialiseFromString(assamString) can be tested in isolation
* Board
  * initialiseFromString(boardStrings) can be tested in isolation
* AllPlayers
  * initialiseFromStringAll(playerStrings) can be tested in isolation
  * checkForWin() can be tested in isolation
* Player
  * initialiseFromString(playerString) can be tested in isolation
* RugsOnBoard
  * addRug(rug) can be tested in isolation
  * initialiseFromString(rugStrings) can be tested in isolation
* Rug
  * initialiseFromString(rugString) can be tested in isolation
* RugAbreviated
  * initialiseFromString(rugString) can be tested in isolation