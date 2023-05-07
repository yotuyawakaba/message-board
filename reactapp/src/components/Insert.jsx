import React, {useState, useEffect} from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Grid from '@mui/material/Grid';
import CustomizedTables from './BodyMain';

export const Home = () => {

  const [content, setContent] = useState("");
  const [text, setText] = useState("");
  const apiUrl = "http://localhost:8080/api/insert";
  const navigate = useNavigate();
  const [refresh, setRefresh] = useState(0); // リフレッシュ用のstate
  const [post, setPosts] = useState([])

  useEffect(() => {
    if(content !== ""){
      axios.post(apiUrl, {
        contents: content
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
    
    axios.get('http://localhost:8080/api/list')
        .then(res => {
            setPosts(res.data)
        })


  },[refresh]);

//   const getContent = getObj => {
//     // console.log(getObj);
//     return (
//         <div>
//   <ul>
//     <li>{getObj.content}</li>
//     <li>{getObj.created}</li>
//     <li>{getObj.updated}</li>
//   </ul>
// </div>
//         // <Grid item xs={12} sm={4} key={getObj.id}>
//         //     <CustomizedTables {...getObj} />
//         // </Grid>
//     );
// };
  
  return (
    
      <div>
        {/* <Grid container spacing={2}>
            {post.map(contentObj => getContent(contentObj))}
        </Grid> */}
        <CustomizedTables post={post} />
        <p>home page.</p>
        <input type="text" value={text} onChange={(event) => setText(event.target.value)} />
        <button onClick={() => {
          setContent(text);
          setText("");
          setRefresh(Date.now()); // リフレッシュ用stateの値を更新
          navigate("/Insert");
          }}>
          Click me
        </button>
      </div>
  );
}
  
