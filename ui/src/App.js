import WordList from "./WordList";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { Component } from "react";

class App extends Component {
  render() {
    return (
      <BrowserRouter basename={process.env.PUBLIC_URL}>
        <Switch>
          <Route path="/" exact={true} component={WordList} />
        </Switch>
      </BrowserRouter>
    );
  }
}
export default App;
