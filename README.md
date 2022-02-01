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
- starts with 'l' has 'h' in position 4 and also contains 'g' and 't': https://wordle-solver-wordle.azuremicroservices.io/match/l..h./contains/g/and/t

Check for words excluding letters you know aren't included 

e.g.
- doesn't contain 'a', 'e' or 'o': https://wordle-solver-wordle.azuremicroservices.io/not/aeo

What about when you know some letters but not others

e.g.
- contains 'e' and 'th' but not 'i', 'o' or 'u': https://wordle-solver-wordle.azuremicroservices.io/contains/e/and/th/not/iou

I know some exact positions and know other letters that aren't part of the word

e.g.
- starts with 'ph' and does not contain 'o': https://wordle-solver-wordle.azuremicroservices.io/match/ph.../not/o