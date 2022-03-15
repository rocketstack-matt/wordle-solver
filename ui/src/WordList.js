import React, { Component } from "react";

class WordList extends Component {
  constructor(props) {
    super(props);
    this.state = { words: [] };
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    this.queryService("");
  }

  handleChange(event) {
    let match = event.target.value ? event.target.value : ".....";
    this.queryService(match);
  }

  queryService(match) {
    while (match.length < 5) match += ".";
    fetch("http://wordle-solver.azurewebsites.net/match/" + match)
      .then((response) => response.json())
      .then((data) => this.setState({ words: data }))
      .catch((error) => console.log("Error:", error));
  }

  render() {
    const { words, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const wordList = words.map((word) => {
      return <div className="list-inline-item">{word}</div>;
    });

    return (
      <div className="container bg-faded">
        <div className="form-group w-100">
          <h3 className="text-center">Wordle Solver</h3>
          <div>
            <div className="form-outline w-25 mx-auto">
              <input
                type="text"
                id="formControlLg"
                className="form-control form-control-lg"
                onChange={this.handleChange}
              />
            </div>
          </div>
          <div className="col-4 mx-auto">
            <center>{wordList}</center>
          </div>
        </div>
      </div>
    );
  }
}

export default WordList;
