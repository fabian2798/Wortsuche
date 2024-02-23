import './index.css';
import React from 'react';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
      dataLoesung: [],
      loading: true,
      showOptions: false,
      vorlageOpen: "v4",
      showLoesung: false,
      letters: [],
      woerter: [],
    };
    this.serverSide = "http://localhost:8080/api/Vorlagen";
    this.getvorlagebyid = "http://localhost:8080/api/Vorlagen/";
    this.getLoesung = "http://localhost:8080/api/Vorlagen/foundwords/";
    this.colors = ["#49CFAD", "#ED5564", "#FC6E51", "#A0D468", "#AC92EC", "#4FC1E9", "#FFCE53"];
    this.indexes = [];
    this.vorlageId = {
      'v4': '65ae05b47a9fa9f407f8cc49',
      'v5': '65ae0646c31ee2442841817a',
      'v6': '65ae07b37a9fa9f407f8cc4f'
    };
  }

  //api get vorlage
  async fetchDataVorlage() {
    try {
      console.log(this.getvorlagebyid + this.vorlageId[this.state.vorlageOpen]);
      const response = await fetch(this.getvorlagebyid + this.vorlageId[this.state.vorlageOpen]);
      const data = await response.json();
      this.setState({ data: data});
    } catch (error) {
      console.error('Error fetching data:', error);
      this.setState({ loading: false });
    }
  }

  //api get loesung
  async fetchDataLoesung() {
    try {
      console.log(this.getLoesung + this.state.vorlageOpen);
      this.setState({ dataLoesung: [], loading: true});
      const response = await fetch(this.getLoesung + this.state.vorlageOpen);
      const dataLoesung = await response.json();
      this.setState({ dataLoesung: dataLoesung});
    } catch (error) {
      console.error('Error fetching data:', error);
      this.setState({ loading: false });
    }
  }

  componentDidMount() {
    this.fetchDataLoesung();
    this.fetchDataVorlage().then(() => {
      if (this.state.data !== 0) {
        let newLetters = this.state.data.grid?.map(row => row.split('')) || [];
        let newWoerter = this.state.data.loesungswoerter || [];
        this.setState({ letters: newLetters, woerter: newWoerter});
      }
      if (this.state.dataLoesung !== 0) {
        for (const [key, value] of Object.entries(this.state.dataLoesung || {})) {
          this.indexes.push(this.transformFormat(value));
        }
      }
      this.setState({ loading: false });
    });
  }

  //update data
  updateData = async () => {
    await this.fetchDataLoesung();
    if (this.state.data !== 0) {
      let newLetters = this.state.data.grid?.map(row => row.split('')) || [];
      let newWoerter = this.state.data.loesungswoerter || [];
      this.setState({ letters: newLetters, woerter: newWoerter});
    }
    await this.fetchDataVorlage();
    this.indexes = [];
    if (this.state.dataLoesung !== 0) {
      for (const [key, value] of Object.entries(this.state.dataLoesung || {})) {
        this.indexes.push(this.transformFormat(value));
      }
    }
    setTimeout(() => {
      this.setState({ loading: false });
    }, 3000);
  }

  //transform data to new format
  transformFormat(input) {
    const [spalte, richtung, startRow, endRow] = input;
    let start, end = 0;
    if (richtung === "Horizontal"){
      start = [spalte["Zeile"], startRow];
      end = [spalte["Zeile"], endRow];
    } else{
      start = [startRow, spalte["Spalte"]];
      end = [endRow, spalte["Spalte"]];
    }
    const color = this.colors[Math.floor(Math.random() * this.colors.length)];
    return { start, end, color, richtung };
  }

  //show chose options
  toggleOptions = () => {
    this.setState(prevState => ({ showOptions: !prevState.showOptions }));
  }

  //show loesung
  toggleLoesung = () => {
    this.setState(prevState => ({ showLoesung: !prevState.showLoesung }));
  };

  //change vorlage
  changeVorlage = (vorlage) => {
    this.setState({ vorlageOpen: vorlage, showLoesung: false, dataLoesung: [], loading: true }, this.updateData);
  }

    render() {
      let numRows = this.state.letters.length;
      let numCols = Math.max(...this.state.letters.map(row => row.length));
      let containerInsideStyle = {
        gridTemplateColumns: `repeat(${numCols}, 1fr)`,
        gridTemplateRows: `repeat(${numRows}, 1fr)`,
      };
      return (
        <div className='app'>
          {this.state.data.length !== 0 ? (
            <div className='app'>
              <div className="container big">
                <div className='container-inside big-inside' style={containerInsideStyle}>
                  {this.state.letters.map((row, i) => (
                    row.map((letter, j) => {
                      let borderRadiusStart, borderRadiusEnd, color;
                      const currentIndex = this.indexes.find(({ start, end }) => i >= start[0] && i <= end[0] && j >= start[1] && j <= end[1]);
                      if (this.state.showLoesung){
                        color = this.indexes.find(({ start, end }) => i >= start[0] && i <= end[0] && j >= start[1] && j <= end[1])?.color;
                        const direction = currentIndex?.richtung;
                        if (direction === "Horizontal") {
                          borderRadiusStart = "50% 0 0 50%";
                          borderRadiusEnd = "0 50% 50% 0";
                        } else {
                          borderRadiusStart = "50% 50% 0 0";
                          borderRadiusEnd = "0 0 50% 50%";
                        }
                      }
                      return (
                        <div
                          key={`${i}-${j}`}
                          className='letter'
                          style={{
                            ...(color ? { backgroundColor: color } : {}),
                            ...(currentIndex && i === currentIndex.start[0] && j === currentIndex.start[1] ? { borderRadius: borderRadiusStart } : {}),
                            ...(currentIndex && i === currentIndex.end[0] && j === currentIndex.end[1] ? { borderRadius: borderRadiusEnd } : {})
                          }}
                        >
                          {letter}
                        </div>
                      );
                    })
                  ))}
                </div>
              </div>
              <div className="container small">
                <div className='container-inside small-inside'>
                  {this.state.woerter.map((word, index) => (
                    <span key={index} className='wort'>
                      {word}
                    </span>
                  ))}
                </div>
                <div className="menu-buttons">
                  <button className="submitbtn" onClick={this.toggleLoesung}>{this.state.showLoesung ? "Verstecken" : "Lösen"}</button>
                  <div className="icons">
                    <i className="fa-solid fa-trash fa-2xl"></i>
                  </div>
                  <div className="icons">
                    <i className="fa-solid fa-gear fa-2xl" onClick={this.toggleOptions}></i>
                    {this.state.showOptions && (
                      <div className='options'>
                        <p className='vorlage-option' onClick={() => this.changeVorlage("v4")}>Vorlage 1</p>
                        <p className='vorlage-option' onClick={() => this.changeVorlage("v5")}>Vorlage 2</p>
                        <p className='vorlage-option' onClick={() => this.changeVorlage("v6")}>Vorlage 3</p>
                      </div>
                    )}
                  </div>
                </div>
              </div>
            </div>
          ) : (
            <h1>Error by API</h1>
          )}
          {this.state.loading ? (
            <div className='loading'><div className='loading-window'>Bitte warten...<i className="fa-solid fa-spinner fa-spin"></i></div></div>
          ) : null}
        </div>
      );
    }
  }

export default App;