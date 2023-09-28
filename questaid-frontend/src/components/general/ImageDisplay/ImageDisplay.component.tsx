import React from 'react';
import "./ImageDisplay.css"
import { Avatar } from '@mui/material';

interface IImageDisplayProps {
    imageData: any,
    width: number,
    height: number,
}

const ImageDisplay: React.FC<IImageDisplayProps> = ({ imageData, width, height }) => {
    return (
        <div>
            <Avatar sx={{ width: width, height: height }}>
                <img
                    src={`data:image/jpeg;base64,${imageData}`}
                    alt="Club logo"
                />
            </Avatar>
        </div>
    );
};

export default ImageDisplay;
