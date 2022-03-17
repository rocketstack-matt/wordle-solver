import GameBoard from "./GameBoard";
import Keyboard from "./Keyboard";
import { RecoilRoot } from "recoil";
import WordList from "./WordList";

export default function App() {
  return (
    <RecoilRoot>
      <div>
        <h1>Wordle Solver</h1>
      </div>
      <GameBoard />
      <Keyboard />
      <WordList />
    </RecoilRoot>
  );
}
