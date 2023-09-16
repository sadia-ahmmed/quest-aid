
import React from 'react';

const newsFeedData = [
  {
    id: 1,
    user: {
      name: 'John Doe',
      profilePicture: 'https://via.placeholder.com/50',
    },
    content: 'This is a sample post!',
    timestamp: '2 hours ago',
  },
  // Add more feed items as needed
];

const NewsFeed = () => {
  return (
    
    <div className="max-w-2xl mx-auto mt-8">
      {newsFeedData.map((item) => (
        <div key={item.id} className="bg-white p-4 rounded-md shadow-md mb-4">
          <div className="flex items-center">
            <img
              src={item.user.profilePicture}
              alt={item.user.name}
              className="w-10 h-10 rounded-full"
            />
            <div className="ml-2">
              <p className="font-semibold">{item.user.name}</p>
              <p className="text-gray-500">{item.timestamp}</p>
            </div>
          </div>
          <div className="mt-4">{item.content}</div>
        </div>
      ))}
    </div>
  );
};

export default NewsFeed;