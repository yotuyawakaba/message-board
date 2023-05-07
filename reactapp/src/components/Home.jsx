import React, { useEffect, useState } from 'react'
import "./Home.css";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

const Home = ({ setContent, setMid }) => {
  const [contentList, setContentList] = useState([]);
  const navigate = useNavigate();
  const apiUrl = "http://localhost:8080/api/";
  const [id, setId] = useState("");

  useEffect(() => {
    const getContent = () => {
      axios.get('http://localhost:8080/api/list')
    .then(res => {
      setContentList(res.data);
    })
    }
    getContent();    
}, []);

  const edit = (id) => {
    if(id !== "") {
      
      axios.get(apiUrl + id)
      .then(res => {
        setContent(res.data.contents)
        setMid(res.data.id)
      });
      }

    navigate("/edit");
  }

  const remove = (id) => {
    if(id !== "") {
      axios.delete(apiUrl + "remove/" + id)
    }
    window.location.href = "/";
  }

  
  return (
    <div className="homePage">
      {contentList.map((post) => {
        return(
          <div className="postContents" key={post.id}>
        <div className="postHeader">
          <h1>Message content</h1>
        </div>
        <div className="postTextContainer">{post.content}</div>
        <div className="nameAndDeleteButton">
        <button className="delete" onClick={() => remove(post.id)}>削除</button>
        <button className="edit" onClick={() => edit(post.id)}>更新</button>
        </div>
      </div>
        );
      })};
      
    </div>
  )
}

export default Home