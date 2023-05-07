import './App.css';
import { BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import Home from './components/Home';
import CreatePost from './components/CreatePost';
import Edit from './components/Edit';
import Navbar from './components/Navbar';
import { useState } from "react"; 

function App() {
  const [content, setContent] = useState("");
  const [mid, setMid] = useState();

  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home setContent={setContent} setMid={setMid}/>}></Route>
        <Route path="/createpost" element={<CreatePost />}></Route>
        <Route path="/edit" element={<Edit content={content} mid={mid} />}></Route>
      </Routes>
    </Router>
  );
}

export default App;