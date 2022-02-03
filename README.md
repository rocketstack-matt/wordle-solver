# wordle-solver

Need a little help to solve your daily [Wordle](https://www.powerlanguage.co.uk/wordle/)?

| What we know                                                                                                                                   |                                                           |
|------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| Find words containing letters 'a' 't' 'h' in any position                                                                                      | https://wordle-solver-wordle.azuremicroservices.io/contains/ath                  |
| Find words containing letters 'a' 't' 'h' in any position but not letters 'b' or 's'                                                           | https://wordle-solver-wordle.azuremicroservices.io/contains/ath/not/bs           |
| Find words containing letters 'a' 't' 'h' in specific positions (use '.' for unknown positions)                                                | https://wordle-solver-wordle.azuremicroservices.io/match/ha..t                   | |
| Find words containing letters 'a' 't' 'h' in specific positions but not letters 'b' or 's'                                                     | https://wordle-solver-wordle.azuremicroservices.io/match/ha..t/not/bs            | |
| Find words containing letters 'a' 't' 'h' in specific positions, contains 'l' but we're not sure where but does contain not letters 'b' or 's' | https://wordle-solver-wordle.azuremicroservices.io/match/ha..t/contains/l/not/bs | |
