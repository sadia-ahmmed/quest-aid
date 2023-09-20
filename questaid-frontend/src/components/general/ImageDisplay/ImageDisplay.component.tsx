import React from 'react';
import "./ImageDisplay.css"

interface IImageDisplayProps {
    imageData: any
}

const ImageDisplay: React.FC<IImageDisplayProps> = ({ imageData }) => {
    return (
        <div>
            <img
                src={`data:image/jpeg;base64,${imageData}`}
                alt="img"
                style={{
                    height: 200,
                    width: 200
                }}
            />
        </div>
    );
};

export default ImageDisplay;
