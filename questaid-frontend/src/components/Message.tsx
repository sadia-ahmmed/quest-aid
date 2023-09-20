import React, { useState } from 'react';
import { Avatar } from '@mui/material';
import demoUser from './demoUser';
import VideocamIcon from '@mui/icons-material/Videocam';
import PhotoLibraryIcon from '@mui/icons-material/PhotoLibrary';
import InsertEmoticonIcon from '@mui/icons-material/InsertEmoticon';

interface MessageSenderProps { }

const MessageSender: React.FC<MessageSenderProps> = () => {
  const user = demoUser;

  const [input, setInput] = useState<string>('');
  const [inputContent, setInputContent] = useState<string>('');
  const [imageUrl, setImageUrl] = useState<string>('');
  const token = localStorage.getItem("token")
  const entityType = localStorage.getItem("entityType")
  const entityId = localStorage.getItem("entityId")

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // You can add your desired action here for frontend purposes
    // For example, displaying a message or updating the UI.

    setInput('');
    setImageUrl('');
  };

  return (
    <div className="messageSender bg-white p-5 rounded shadow-md mt-3 w-1/2">
      <div className="messageSender_top flex items-top border-b border-gray-300 pb-3">
        <Avatar src={user?.photoURL || ''} />
        <form onSubmit={handleSubmit} className="flex-1 ml-2 w-full">
          <input
            value={input}
            onChange={(e) => setInput(e.target.value)}
            className="messageSender_input flex-1 p-2 rounded-lg bg-gray-100 py-3 px-3 border-gray-100 w-full"
            placeholder={`Write your Title ${user?.displayName || ''}`}
          />
          <textarea
            value={inputContent}
            onChange={(e) => setInputContent(e.target.value)}
            className="messageSender_input flex-1 p-2 rounded-lg bg-gray-100 py-3 px-3 border-gray-100 w-full mt-2"
            placeholder={`Write your Content ${user?.displayName || ''}`}
          />

        </form>
      </div>
      
      <div className="messageSender_bottom flex justify-between mt-3">
        {
          entityType === "organization" ?
           <>
          </>
          :
          <>
          
          </>

        }
        <div className="messageSender_option flex items-center">
          <VideocamIcon style={{ color: 'red' }} />
          <h3 className="ml-2 text-medium cursor-pointer">Privacy</h3>
        </div>

        <div className="messageSender_option flex items-center">
          <PhotoLibraryIcon style={{ color: 'teal-900' }} />
          <h3 className="ml-2 text-medium cursor-pointer">Photo</h3>
        </div>

        <div className="messageSender_option flex items-center">
          <button
            type="submit"
            className="flex px-10 rounded bg-teal-800 justify-center py-1 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600 mt-2"
          >
            Post
          </button>
        </div>
      </div>
    </div>
  );
};

export default MessageSender;