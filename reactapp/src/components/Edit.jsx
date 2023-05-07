import React, { useEffect, useState } from 'react'
import "./Edit.css";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

//CORS問題の解決方法を探す
const Edit = ({ content, mid }) => {
  const apiUrl = "http://localhost:8080/api/update";
  const [postText, setEditText] = useState();
  const [postId, setId] = useState(mid);
  const navigate = useNavigate();

  useEffect(() => {
    setEditText(content);
    setId(mid);
  }, [content])

  const editPost = () => {
    console.log(postText)
    if(postId !== ""){
      axios.put(apiUrl, {
        contents: postText,
        id: postId
      },{
        headers: {
          'Content-Type': "application/json" //ここapplication/jsonにすること
        }
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    }
console.log(postText);
    navigate("/");
  }

  return (
    <div className="editPage">
      <div className="editContainer">
        <h1>Edit message</h1>
        <div className="inputPost">
          <div>Edit</div>
          <textarea value={postText} onChange={(e) => setEditText(e.target.value)}></textarea>
        </div>
        <button className="postButton" onClick={editPost}>
          更新する
        </button>
      </div>
    </div>
  )
}

export default Edit