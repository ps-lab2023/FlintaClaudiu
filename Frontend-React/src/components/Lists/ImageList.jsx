import "../../style/Lists/ImageList.css";
import React, { useState } from "react";

const images = [
  { id: 1, src: "https://th.bing.com/th/id/OIP.qkCiAzfsbOZDAdLyT7HzYgHaEw?pid=ImgDet&rs=1", caption: "Image 1" },
  { id: 2, src: "https://th.bing.com/th/id/OIP.0bu9sd_ziVqg1EYqHGqIMgHaE8?pid=ImgDet&rs=1", caption: "Image 2" },
  { id: 3, src: "https://th.bing.com/th/id/OIP.0bu9sd_ziVqg1EYqHGqIMgHaE8?pid=ImgDet&rs=1", caption: "Image 3" },
  { id: 4, src: "image4.jpg", caption: "Image 4" },
  { id: 5, src: "image5.jpg", caption: "Image 5" },
  { id: 6, src: "image6.jpg", caption: "Image 6" },
];

const ImageList = () => {
  const [position, setPosition] = useState(0);

  const handlePrevClick = () => {
    setPosition((prevPosition) =>
      prevPosition === 0 ? images.length - 3 : prevPosition - 3
    );
  };

  const handleNextClick = () => {
    setPosition((prevPosition) =>
      prevPosition === images.length - 3 ? 0 : prevPosition + 3
    );
  };

  return (
    <div className="image-list-container">
      <button onClick={handlePrevClick}>{"<"}</button>
      {images.map((image, index) => (
        <div
          key={image.id}
          className="image-container"
          style={{
            display:
              index >= position && index <= position + 2 ? "block" : "none",
          }}
        >
          <img
            src={image.src}
            alt={image.caption}
            title={image.caption}
          />
        </div>
      ))}
      <button onClick={handleNextClick}>{">"}</button>
    </div>
  );
};

export default ImageList;

