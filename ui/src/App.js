import React, {useEffect, useState} from 'react';
import './App.css';

function App() {
    const initialLetterStates = Array(5).fill().map(() => Array(5).fill({letter: '', state: ''}));
    const [letterStates, setLetterStates] = useState(initialLetterStates);
    const [results, setResults] = useState([]);
    const [activeRow, setActiveRow] = useState(0);
    const [activeCol, setActiveCol] = useState(0);

    useEffect(() => {
        const handleKeyDown = (event) => {
            const key = event.key.toUpperCase();
            if (key >= 'A' && key <= 'Z') {
                handleKeyClick(key);
            } else if (key === 'BACKSPACE') {
                handleBackspace();
            }
        };

        window.addEventListener('keydown', handleKeyDown);
        return () => {
            window.removeEventListener('keydown', handleKeyDown);
        };
    }, [activeRow, activeCol, letterStates]);

    const cycleLetterState = (rowIndex, colIndex) => {
        const states = ['', 'green', 'yellow', 'gray'];
        const currentCell = letterStates[rowIndex][colIndex];
        const nextState = states[(states.indexOf(currentCell.state) + 1) % states.length];
        const newLetterStates = letterStates.map((row, rIdx) =>
            row.map((cell, cIdx) =>
                rIdx === rowIndex && cIdx === colIndex ? {...cell, state: nextState} : cell
            )
        );
        setLetterStates(newLetterStates);
    };

    const handleKeyClick = (letter) => {
        const newLetterStates = letterStates.map((row, rowIndex) =>
            row.map((cell, colIndex) =>
                rowIndex === activeRow && colIndex === activeCol ? {...cell, letter: letter} : cell
            )
        );
        setLetterStates(newLetterStates);
        if (activeCol < 4) {
            setActiveCol(activeCol + 1);
        } else if (activeRow < 4) {
            setActiveRow(activeRow + 1);
            setActiveCol(0);
        }
    };

    const handleBackspace = () => {
        if (activeCol > 0 || activeRow > 0) {
            const newActiveCol = activeCol > 0 ? activeCol - 1 : 4;
            const newActiveRow = activeCol > 0 ? activeRow : activeRow - 1;
            setActiveRow(newActiveRow);
            setActiveCol(newActiveCol);
            const newLetterStates = letterStates.map((row, rowIndex) =>
                row.map((cell, colIndex) =>
                    rowIndex === newActiveRow && colIndex === newActiveCol ? {...cell, letter: ''} : cell
                )
            );
            setLetterStates(newLetterStates);
        }
    };

    const handleSubmit = () => {
        let matchPattern = Array(5).fill('.').join('');
        let containsLetters = new Set();
        let notLetters = new Set();

        // Iterate over all rows and collect green, yellow, and gray letters
        letterStates.forEach((row) => {
            row.forEach((cell, colIndex) => {
                if (cell.state === 'green' && cell.letter) {
                    matchPattern = matchPattern.split('').map((char, idx) => idx === colIndex ? cell.letter : char).join('');
                } else if (cell.state === 'yellow' && cell.letter) {
                    containsLetters.add(cell.letter);
                } else if (cell.state === 'gray' && cell.letter) {
                    notLetters.add(cell.letter);
                }
            });
        });

        // Convert sets to strings for query
        const containsPattern = Array.from(containsLetters).join('');
        const notPattern = Array.from(notLetters).join('');

        const matchQuery = matchPattern !== '.....' ? `/match/${matchPattern}` : '';
        const containsQuery = containsPattern ? `/contains/${containsPattern}` : '';
        const notQuery = notPattern ? `/not/${notPattern}` : '';

        const query = `https://wordle-solver.azurewebsites.net${matchQuery}${containsQuery}${notQuery}`;

        // Fetch the results from the API
        fetch(query)
            .then(response => response.json())
            .then(data => {
                if (data && data.words) {
                    setResults(data.words);
                } else {
                    setResults([]);
                }
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                setResults([]);
            });
    };


    return (
        <div className="App">
            <h1>Wordle Solver</h1>
            <div id="word-input" className="word-input">
                {letterStates.map((row, rowIndex) => (
                    <div key={rowIndex} className="word-row">
                        {row.map((cell, colIndex) => (
                            <div
                                key={colIndex}
                                className={`letter-box ${cell.state}`}
                                onClick={() => cycleLetterState(rowIndex, colIndex)}
                            >
                                {cell.letter || ''}
                            </div>
                        ))}
                    </div>
                ))}
            </div>
            <div className="keyboard">
                <div className="keyboard-row">
                    {'QWERTYUIOP'.split('').map((letter) => (
                        <button key={letter} className="key-button" onClick={() => handleKeyClick(letter)}>
                            {letter}
                        </button>
                    ))}
                </div>
                <div className="keyboard-row">
                    {'ASDFGHJKL'.split('').map((letter) => (
                        <button key={letter} className="key-button" onClick={() => handleKeyClick(letter)}>
                            {letter}
                        </button>
                    ))}
                </div>
                <div className="keyboard-row">
                    {'ZXCVBNM'.split('').map((letter) => (
                        <button key={letter} className="key-button" onClick={() => handleKeyClick(letter)}>
                            {letter}
                        </button>
                    ))}
                    <button className="key-button key-button-backspace" onClick={handleBackspace}>⌫</button>
                </div>
            </div>

            <button id="submit" onClick={handleSubmit}>Submit</button>
            <div id="results">
                <h2>Possible Words</h2>
                <ul>
                    {results.map((word, index) => (
                        <li key={index}>{word}</li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default App;