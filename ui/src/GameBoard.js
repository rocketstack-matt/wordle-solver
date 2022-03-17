import React from "react";
import "./GameBoard.css";
import { keyState } from "./StateStore";
import { useRecoilValue } from "recoil";

export default function GameBoard() {
  const keyPresses = useRecoilValue(keyState);

  const getStyle = (value) => {
    if (value === undefined) return "letter-box";
    return "letter-box filled-box";
  };

  const rows = [0, 1, 2, 3, 4];
  const boxes = [0, 1, 2, 3, 4];

  return (
    <div id="game-board">
      {rows.map((row, rowIndex) => (
        <div key={"row-" + rowIndex} className={"letter-row"}>
          {boxes.map((box, boxIndex) => (
            <div
              key={"box-" + rowIndex * 5 + boxIndex}
              className={getStyle(keyPresses[rowIndex * 5 + boxIndex])}
            >
              {keyPresses[rowIndex * 5 + boxIndex]}
            </div>
          ))}
        </div>
      ))}
    </div>
  );
}
