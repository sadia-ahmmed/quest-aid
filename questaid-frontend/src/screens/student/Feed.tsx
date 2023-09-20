import React, { useEffect, useState } from 'react';

import MessageSender from '../../components/general/Message/Message.component';
import Post from '../../components/general/Post/Post.component';

interface PostData {
  id: string;
  data: {
    profilePic: string;
    message: string;
    timestamp: string;
    username: string;
    image: string;
  };
}

function Feed() {
  const [posts, setPosts] = useState<PostData[]>([]);

  useEffect(() => {
    // In a real application, you would fetch posts from an API or other data source.
    // For demonstration purposes, we'll initialize an empty array.
    const dummyPosts: PostData[] = [];
    setPosts(dummyPosts);
  }, []);

  return (
    <div className="flex-1 p-10 flex flex-col justify-center items-center">
      {/* Message Sender */}
      <MessageSender />
      {/* Post */}
      {posts.map((post) => (
        <Post
          key={post.id}
          profilePic={post.data.profilePic}
          message={post.data.message}
          timestamp={post.data.timestamp}
          username={post.data.username}
          image={post.data.image}
        />
      ))}
    </div>
  );
}

export default Feed;


