# wordle-solver
Need a little help to solve your daily [Wordle](https://www.powerlanguage.co.uk/wordle/)? 

With wordle-solver you can:

Check which words contain known letters or groups of letters 

e.g. 
- words containg 'a': https://wordle-solver-wordle.azuremicroservices.io/contains/a
- words containing 'a' and 'bh': https://wordle-solver-wordle.azuremicroservices.io/contains/a/and/th

Check a specific pattern where you know the position of letters and use a full stop '.' for the ones you don't know

e.g.
- starts with 'ch' and ends with 'p': https://wordle-solver-wordle.azuremicroservices.io/match/ch..p

Check the words where you know the position of some letters and just know other letters but not where they are

e.g.
- starts with 'l' has 'h' in position 4 and also contains 'g' and 't' https://wordle-solver-wordle.azuremicroservices.io/match/l..h./contains/g/and/t