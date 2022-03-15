# wordle-solver

Need a little help to solve your daily [Wordle](https://www.nytimes.com/games/wordle/index.html)?

You can use the REST service that implements the solver directly using the below URLs

| What you're looking for                                                                                                                                  |                                                           |
|------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| Find words containing letters 'a' 't' 'h' in any position                                                                                      | https://wordle-solver.azurewebsites.net/contains/ath                  |
| Find words containing letters 'a' 't' 'h' in any position but not letters 'b' or 's'                                                           | https://wordle-solver.azurewebsites.net/contains/ath/not/bs           |
| Find words containing letters 'a' 't' 'h' in specific positions (use '.' for unknown positions)                                                | https://wordle-solver.azurewebsites.net/match/ha..t                   | |
| Find words containing letters 'a' 't' 'h' in specific positions but not letters 'b' or 's'                                                     | https://wordle-solver.azurewebsites.net/match/ha..t/not/bs            | |
| Find words containing letters 'a' 't' 'h' in specific positions, contains 'l' but we're not sure where but does contain not letters 'b' or 's' | https://wordle-solver.azurewebsites.net/match/ha..t/contains/l/not/bs | |

Alternatively, access the React UI at http://wordle.rocketstack.co/

The REST service runs on a server which is provisioned on-demand, so if it doesn't return immediately please wait a couple of minutes for the server to spin up.