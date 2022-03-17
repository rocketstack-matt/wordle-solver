import React, { useEffect, useState } from "react";
import "./WordList.css";
import { useRecoilValue } from "recoil";
import { keyState } from "./StateStore";

export default function WordList() {
  const [isLoading, setIsLoading] = React.useState(true);
  const [words, updateWords] = useState([]);
  const keyPresses = useRecoilValue(keyState);

  useEffect(
    () => queryService(keyPresses.length === 0 ? "....." : keyPresses),
    [keyPresses]
  );

  const queryService = (match) => {
    while (match.length < 5) match += ".";
    fetch(
      process.env.REACT_APP_WORDLE_SOLVER_API + "match/" + match.toLowerCase()
    )
      .then((response) => response.json())
      .then((json) => updateWords(json))
      .then(() => setIsLoading(false))
      .catch((error) => console.log(error));
  };

  return (
    <div id="wordlist-cont">
      {isLoading ? (
        <h1>Loading...</h1>
      ) : (
        words.map((word, index) => <div key={"word-" + index}>{word}</div>)
      )}
    </div>
  );
}
