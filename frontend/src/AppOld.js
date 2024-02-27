import './index.css';
import React, { useState, useEffect } from 'react';


function Apps() {
  const [data, setData] = useState([]);
  const [dataLoesung, setData2] = useState([]);
  const [loading, setLoading] = useState(true);

  const getvorlagebytitle = "http://localhost:8080/api/Vorlagen/650038c869969765198a4c8f";
  const getLoesungV1 = "http://localhost:8080/api/Vorlagen/foundwords/v1";
  const colors = ["#49CFAD", "#ED5564", "#FC6E51", "#A0D468", "#AC92EC", "#4FC1E9", "#FFCE53"];
  let letters = [];
  let woerter = [];
  let indexes = [];

  useEffect(() => {
    const fetchDataVorlage = async () => {
      try {
        const response = await fetch(getvorlagebytitle);
        const data = await response.json();
        setData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchDataVorlage();

    const fetchDataLoesung = async () => {
      try {
        const response = await fetch(getLoesungV1);
        const dataLoesung = await response.json();
        setData2(dataLoesung);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false); // Set loading to false regardless of success or failure
      }
    };
    fetchDataLoesung();

  }, []);

  const transformFormat = (input) => {
    const [spalte, richtung, startRow, endRow] = input;
    let start, end = 0;
    if (richtung === "Horizontal"){
      start = [spalte["Zeile"], startRow];
      end = [spalte["Zeile"], endRow];
    } else{
      start = [startRow, spalte["Spalte"]];
       end = [endRow, spalte["Spalte"]];
    }
    const color = colors[Math.floor(Math.random() * colors.length)];
    const direction = richtung;
    return { start, end, color, direction };
  };

  if (dataLoesung != 0 && data != 0){
    letters = data.grid?.map(row => row.split('')) || [];
    for (const [key, value] of Object.entries(dataLoesung || {})) {
      indexes.push(transformFormat(value));
    }
    for (const [key, value] of Object.entries(data?.loesungswoerter || {})) {
      if (dataLoesung.hasOwnProperty(value)){
        woerter.push(value);
      }
    }
  }
  
  const numRows = letters.length;
  const numCols = Math.max(...letters.map(row => row.length));
  const containerInsideStyle = {
    gridTemplateColumns: `repeat(${numCols}, 1fr)`,
    gridTemplateRows: `repeat(${numRows}, 1fr)`,
  };

  if (loading) {
    // Render a loading indicator or message while waiting for the API response
    return <div className='app'><h1>Loading...</h1></div>;
  }
  
  // Render your actual component once the API response is received
  return (
    <div className='app'>
      {data.length !== 0 ? (
        <div className='app'><div className="container big">
          <div className='container-inside big-inside' style={containerInsideStyle}>
            {letters.map((row, i) => (
              row.map((letter, j) => {
                const color = indexes.find(({ start, end }) => i >= start[0] && i <= end[0] && j >= start[1] && j <= end[1])?.color;
                const direction = indexes.find(({ start, end }) => i >= start[0] && i <= end[0] && j >= start[1] && j <= end[1])?.direction;
                
                let borderRadiusStart, borderRadiusEnd;
                if (direction === "Horizontal") {
                  borderRadiusStart = "50% 0 0 50%";
                  borderRadiusEnd = "0 50% 50% 0";
                } else {
                  borderRadiusStart = "50% 50% 0 0";
                  borderRadiusEnd = "0 0 50% 50%";
                }
                return (
                  <div
                    key={`${i}-${j}`}
                    className='letter'
                    style={{
                      ...(color ? { backgroundColor: color } : {}),
                      ...(i === indexes[0].start[0] && j === indexes[0].start[1] ? { borderRadius: borderRadiusStart } : {}),
                      ...(i === indexes[0].end[0] && j === indexes[0].end[1] ? { borderRadius: borderRadiusEnd } : {})
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
          {woerter.map((word, index) => (
            <span key={index} className='wort'>
              {word}
            </span>
          ))}
        </div>
        <div className="menu-buttons">
          <button className="submitbtn">Download</button>
          <div className="icons">
            <i className="fa-solid fa-trash fa-2xl"></i>
          </div>
          <div className="icons">
            <i className="fa-solid fa-gear fa-2xl"></i>
          </div>
        </div>
        </div></div>
      ) : (
        <h1>Error by API</h1>
      )}
    </div>
  );
  
}

export default Apps;
