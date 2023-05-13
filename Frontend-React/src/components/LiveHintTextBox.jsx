import React, { useState } from "react";

const LiveHintTextBox = () => {
  const [inputValue, setInputValue] = useState("");
  const hintOptions = ["Hint 1", "Hint 2", "Hint 3", "Hint 4", "Hint 5"];

  const handleInputChange = (event) => {
    const value = event.target.value;
    setInputValue(value);
  };

  const filterOptions = () => {
    // Filter the hint options based on the current input value
    return hintOptions.filter((option) =>
      option.toLowerCase().startsWith(inputValue.toLowerCase())
    );
  };

  return (
    <div>
      <input type="text" value={inputValue} onChange={handleInputChange} />
      <ul>
        {filterOptions().map((option, index) => (
          <li key={index}>{option}</li>
        ))}
      </ul>
    </div>
  );
};

export default LiveHintTextBox;
