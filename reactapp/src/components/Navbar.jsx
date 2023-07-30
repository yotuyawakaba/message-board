import React from 'react';
import { Link } from "react-router-dom";
import "./Navbar.css";

const Navbar = () => {
  return (
    <nav>
        <Link to="/createpost">
            Post
        </Link>
    </nav>
  )
};

export default Navbar