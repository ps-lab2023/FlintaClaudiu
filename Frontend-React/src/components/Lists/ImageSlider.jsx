import React, { useState } from 'react';

import { useNavigate, useLocation } from 'react-router-dom';
import "../../style/Lists/ImageSlider.css";
import HotelsPage from '../HotelsPage';

const images = [
  {
    url: 'https://th.bing.com/th/id/OIP.qkCiAzfsbOZDAdLyT7HzYgHaEw?pid=ImgDet&rs=1',
    infoUrl: 'https://example.com/info1',
    region: "Brasov",
  },
  {
    url: 'https://th.bing.com/th/id/OIP.0bu9sd_ziVqg1EYqHGqIMgHaE8?pid=ImgDet&rs=1',
    infoUrl: 'https://example.com/info2',
    region: "Cluj",
  },
  {
    url: 'https://d1bvpoagx8hqbg.cloudfront.net/originals/experience-bucharest-romania-nicolai-72e135eff29733f9d1331dbfe067044a.jpg',
    infoUrl: 'https://example.com/info3',
    region: "Bucuresti",
  },
  {
    url: 'https://th.bing.com/th/id/OIP.XYlKfUgWWKDo9h0FgzvuQAHaE8?pid=ImgDet&rs=1',
    infoUrl: 'https://example.com/info4',
    region: "Timisoara",
  },
  {
    url: 'https://psnews.ro/wp-content/uploads/2017/08/Cazinoul-din-Constanta.jpg',
    infoUrl: 'https://example.com/info5',
    region: "Constanta",
  },
  {
    url: 'https://th.bing.com/th/id/R.8489f97487a5d0846da09c9c215549ee?rik=Nj7%2fmB6bKsBg0A&riu=http%3a%2f%2fromaniatourism.com%2fimages%2fiasi%2fiasi-romania.jpg&ehk=eAhYNgHzZMHzVfXpbECm%2fJqQFi2JU2esYHOTK1CgeX0%3d&risl=&pid=ImgRaw&r=0',
    infoUrl: 'https://example.com/info6',
    region: "Iasi",
  },
];


const ImageSlider = () => {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  
  const navigate = useNavigate();

  const handleClickPrev = () => {
    const newIndex = currentImageIndex === 0 ? images.length - 1 : currentImageIndex - 1;
    setCurrentImageIndex(newIndex);
  };

  const handleClickNext = () => {
    const newIndex = currentImageIndex === images.length - 1 ? 0 : currentImageIndex + 1;
    setCurrentImageIndex(newIndex);
  };

  const handleImageClick = (infoUrl) => {
    window.open(infoUrl, '_blank');
  };

  const renderImages = () => {
    const imagesToRender = [];

    for (let i = -1; i <= 1; i++) {
      const index = currentImageIndex + i;

      if (index < 0) {
        imagesToRender.push(images[images.length + index]);
      } else if (index >= images.length) {
        imagesToRender.push(images[index - images.length]);
      } else {
        imagesToRender.push(images[index]);
      }
    }

    function handleImageClick(region) {
        navigate("/hotels_unlogged", { state: { region: region } });
      }

    return imagesToRender.map((image, index) => (
      <div
        key={index}
        className="image-container"
        onClick={() => handleImageClick(image.region)}
      >
        <img src={image.url} alt={`Image ${index + 1}`} className="rounded-image" />
      </div>
    ));
  };

  return (
    <div className="slider-container">
      <div className="slider-images">{renderImages()}</div>
      <button className="prev-button" onClick={handleClickPrev}>
        Prev
      </button>
      <button className="next-button" onClick={handleClickNext}>
        Next
      </button>
    </div>
  );
};

export default ImageSlider;
