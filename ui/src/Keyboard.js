import React, { useEffect } from "react";
import "./Keyboard.css";
import { useRecoilState } from "recoil";
import { keyState } from "./StateStore";

export default function Keyboard() {
  const [keyPressed, setKeyPressed] = useRecoilState(keyState);
  if (keyPressed === undefined) setKeyPressed("");

  const handleKeyEvent = (event) => {
    registerKeyUpdate(event.key);
  };

  const registerKeyUpdate = (key) => {
    if (key === "Backspace" || key === "Delete" || key === "Del") {
      setKeyPressed(keyPressed.substring(0, keyPressed.length - 1));
    } else if (
      keyPressed.length < 25 &&
      key.match("[a-zA-z]") &&
      key.length === 1
    ) {
      setKeyPressed(keyPressed + key);
    }
  };

  useEffect(() => {
    window.addEventListener("keyup", handleKeyEvent);

    return () => {
      window.removeEventListener("keyup", handleKeyEvent);
    };
  });

  const firstRow = ["q", "w", "e", "r", "t", "y", "u", "i", "o", "p"];
  const secondRow = ["a", "s", "d", "f", "g", "h", "j", "k", "l"];
  const thirdRow = ["Del", "z", "x", "c", "v", "b", "n", "m", "Enter"];

  return (
    <div id="keyboard-cont" onKeyUp={handleKeyEvent}>
      <div className="first-row">
        {firstRow.map((letter, index) => (
          <button
            className="keyboard-button"
            key={"button-" + index}
            onClick={() => registerKeyUpdate(letter)}
          >
            {letter}
          </button>
        ))}
      </div>
      <div className="second-row">
        {secondRow.map((letter, index) => (
          <button
            className="keyboard-button"
            key={"button-" + index}
            onClick={() => registerKeyUpdate(letter)}
          >
            {letter}
          </button>
        ))}
      </div>
      <div className="third-row">
        {thirdRow.map((letter, index) => (
          <button
            className="keyboard-button"
            key={"button-" + index}
            onClick={() => registerKeyUpdate(letter)}
          >
            {letter}
          </button>
        ))}
      </div>
    </div>
  );
}
