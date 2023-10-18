## Code Review

Reviewed by: Andy Chih, u7574003

Reviewing code written by: Haochen Gong, u7756923

Component: isGameOver

### Comments 
You did a great job about judging if the game is over. It's works. However, some optimizations can still be made.
Feedback and Recommendations:
Add a boundary check in the loop to avoid potential out-of-bounds access.
Consider adding comments to explain the logic, especially the significance of the 'P' and 'i' characters and the rug value extraction.
Ensure that the rug value extraction logic handles all possible cases (e.g., single-digit rug values).
The variable playerNum is incremented but never used. If you don't need it, consider removing it.

