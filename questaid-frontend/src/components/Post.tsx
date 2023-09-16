import React from 'react';
import { Avatar } from '@mui/material';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import NearMeIcon from '@mui/icons-material/NearMe';
import { ExpandMoreOutlined } from '@mui/icons-material';

interface PostProps {
  profilePic: string;
  image: string;
  username: string;
  timestamp: string;
  message: string;
}

const Post: React.FC<PostProps> = ({ profilePic, image, username, timestamp, message }) => {
  return (
    <div className="w-full mt-15 rounded-15 bg-white shadow-md">
      <div className="flex relative items-center p-15">
        <Avatar src={profilePic} className="ml-10" />
        <div className="flex-1">
          <h3 className="text-medium ml-10" >{username}</h3>
          <p className="text-small text-gray-500 ml-10">{timestamp}</p>
        </div>
      </div>
      <div className="mt-10 mb-10 p-15 md:p-25">
        <p>{message}</p>
      </div>
      <div  className="w-full">
        <img src={image} alt="" />
      </div>
      <div className="pt-10 border-t border-gray-300 flex justify-between text-medium text-gray-500 cursor-pointer p-5 md:p-15 post_options">
  <div className="flex-1 post_option hover:bg-gray-100 rounded-10">
    <ThumbUpIcon />
    <p className="ml-2">Like</p>
  </div>
  <div className="flex-1 post_option hover:bg-gray-100 rounded-10">
    <ChatBubbleOutlineIcon />
    <p className="ml-2">Comment</p>
  </div>
  <div className="flex-1 post_option hover:bg-gray-100 rounded-10">
    <NearMeIcon />
    <p className="ml-2">Share</p>
  </div>
  <div className="flex-1 post_option hover:bg-gray-100 rounded-10">
    <AccountCircleIcon />
    <ExpandMoreOutlined />
  </div>
</div>

    </div>
  );
};

export default Post;
