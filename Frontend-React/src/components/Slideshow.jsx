import React, { useState, useEffect } from "react";
import "../style/Slideshow.css"; // Import CSS styles

const images = [
  "https://static3.mclcm.net/iod/images/v2/69/photo/346781/1200x630_100_300_000000x30x0.jpg?ts=20200701181510",
  "https://q-xx.bstatic.com/xdata/images/xphoto/2880x868/204282764.jpeg?k=3f0ffe291e89da1e7c60842711e831fc83b21d236bd5711dbad9d718e49a1275&o=",
  "https://th.bing.com/th/id/R.717ce207d65fc678124e185e3c49257f?rik=Q%2bsFR9%2bzL8gv1g&riu=http%3a%2f%2fwww.smart-extranet.fr%2flEZfQFzblBi%2fvacance-16.jpg&ehk=G1EJpB2Kbqm%2fBLRFdUZo0uvWLU07HJwRWwk%2f3MbFUiw%3d&risl=&pid=ImgRaw&r=0"
];

function Slideshow() {
  const [activeIndex, setActiveIndex] = useState(0);

  console.log("activeIndex:", activeIndex);

  useEffect(() => {
    const intervalId = setInterval(() => {
      setActiveIndex((activeIndex) =>
        activeIndex === images.length - 1 ? 0 : activeIndex + 1
      );
    }, 6000);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <div className="slideshow">
      <div className="slideshow-wrapper">
        {images.map((imageUrl, index) => (
          <img
            key={index}
            className={`slideshow-image ${
              index === activeIndex ? "active" : ""
            }`}
            src={imageUrl}
            alt={`Image ${index + 1}`}
          />
        ))}
      </div>
    </div>
  );
}

export default Slideshow;