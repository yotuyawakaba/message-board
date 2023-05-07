import React from 'react';
import { Link } from "react-router-dom";
import "./Navbar.css";

const Navbar = () => {
  return (
    <nav>
        <Link to="/createpost">
            投稿する
        </Link>
    </nav>
  )
};

export default Navbar