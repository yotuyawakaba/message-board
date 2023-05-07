import React, { useState } from 'react'
import "./CreatePost.css";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

const CreatePost = () => {
  const [postText, setPostText] = useState();
  const navigate = useNavigate();
  const apiUrl = "http://localhost:8080/api/insert";
  

  const createPost = () => {
    if(postText !== ""){
      axios.post(apiUrl, {
        contents: postText
      },{
        headers: {
          'Content-Type': "application/json" //ここapplication/jsonにすること
        },
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    }

    navigate("/");
  }

  return (
    <div className="createPostPage">
      <div className="postContainer">
        <h1>Post message</h1>
        <div className="inputPost">
          <div>Post</div>
          <textarea placeholder="Fill out message content" 
                    onChange={(e) => setPostText(e.target.value)}></textarea>
        </div>
        <button className="postButton" onClick={createPost}>
          投稿
        </button>
      </div>
    </div>
  )
}

export default CreatePost