import './index.css';

function App() {
 const letters = [
  ["B", "B", "R", "I", "S", "O", "A", "E", "C", "G", "K", "A", "P", "E", "L", "L", "E"],
  ["R", "E", "A", "H", "S", "C", "F", "U", "E", "H", "R", "U", "N", "G", "A", "Z", "O"],
  ["G", "E", "R", "G", "E", "C", "H", "F", "D", "O", "F", "D", "E", "M", "E", "I", "F"],
  ["G", "N", "F", "G", "H", "K", "A", "L", "M", "I", "Y", "E", "K", "W", "P", "M", "E"],
  ["R", "N", "U", "U", "E", "E", "R", "R", "A", "N", "O", "N", "N", "E", "U", "M", "R"],
  ["U", "E", "D", "T", "N", "T", "A", "E", "A", "F", "E", "G", "T", "S", "P", "E", "O"],
  ["B", "T", "G", "G", "S", "M", "F", "S", "V", "D", "G", "H", "U", "F", "T", "R", "B"],
  ["R", "S", "N", "M", "W", "E", "T", "A", "H", "S", "C", "E", "A", "I", "U", "E", "E"],
  ["E", "I", "U", "I", "U", "I", "F", "E", "H", "I", "F", "N", "M", "T", "D", "E", "R"],
  ["S", "R", "R", "T", "E", "T", "C", "W", "H", "C", "G", "F", "K", "A", "H", "E", "U"],
  ["S", "U", "E", "T", "R", "A", "C", "C", "S", "S", "S", "E", "I", "H", "C", "S", "N"],
  ["A", "O", "I", "E", "D", "H", "S", "C", "R", "C", "T", "R", "A", "H", "A", "H", "G"],
  ["W", "T", "R", "L", "I", "E", "M", "A", "A", "I", "H", "L", "R", "E", "C", "S", "K"],
  ["C", "D", "U", "A", "G", "M", "U", "H", "H", "M", "P", "W", "U", "E", "O", "S", "A"],
  ["L", "B", "A", "L", "K", "M", "I", "C", "N", "E", "S", "L", "E", "F", "H", "O", "M"],
  ["E", "S", "T", "T", "E", "B", "R", "O", "N", "Z", "E", "Z", "E", "I", "T", "L", "I"],
  ["S", "T", "S", "E", "I", "A", "M", "A", "R", "O", "N", "A", "P", "E", "Z", "H", "N"],
  ["N", "V", "E", "R", "T", "E", "I", "D", "I", "G", "U", "N", "G", "V", "E", "C", "N"],
  ["I", "E", "R", "E", "L", "A", "M", "D", "N", "A", "W", "N", "O", "L", "A", "S", "H"]
];
const numRows = letters.length;
const numCols = Math.max(...letters.map(row => row.length));
const containerInsideStyle = {
  gridTemplateColumns: `repeat(${numCols}, 1fr)`,
  gridTemplateRows: `repeat(${numRows}, 1fr)`,
 };
 
 return (
  <div className='app'>
    <div className="container big">
      <div className='container-inside big-inside' style={containerInsideStyle}>
        {letters.map((row, i) => (
          row.map((letter, j) => (
            <div
              key={`${i}-${j}`}
              className='letter'
            >
              {letter}
            </div>
          ))
        ))}
      </div>
    </div>
    <div className="container small">
      <div className='container-inside small-inside'></div>
      <div className="menu-buttons">
        <button class="submitbtn">Button</button>
        <div class="icons">
          <i class="fa-solid fa-trash fa-2xl"></i>
        </div>
        <div class="icons">
          <i class="fa-solid fa-gear fa-2xl"></i>
        </div>
      </div>
    </div>
  </div>
 );
}

export default App;
