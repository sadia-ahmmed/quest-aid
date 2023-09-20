import { useState } from "react";
import React from "react";
import { useAuthContext } from "../context/AuthContext";
import SearchIcon from '@mui/icons-material/Search';
const notAuthNavLinks = [
  {
    id: "home",
    title: "Home",
  },
  {
    id: "login",
    title: "Login",
  },
];

const authNavLinks = [
  {
    id: "feed",
    title: "Feed",
  },
];

// TODO: fix navbar
const Navbar = () => {
  const [active, setActive] = useState("Home");
  const { isLoggedIn } = useAuthContext();

  // State to store the search query
  const [searchQuery, setSearchQuery] = useState("");

  // Function to handle search
  const handleSearch = () => {
    // Perform the search based on the `searchQuery` state
    console.log("Performing search for:", searchQuery);
    // You can add your search logic here
  };

  return (
    <nav className="w-full flex py-2 justify-between items-center navbar bg-teal-600">
    {/* Left Side Search Bar */}
      {/* Left Side Search Bar */}
      <h4 className="text-m text-teal" style={{ marginLeft: 25 }}>
        Logo
      </h4>
      <div className="flex items-center">
      
        <input
        
          type="text"
          placeholder="Search"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          className="px-2 py-1 rounded-2xl  ml-5 mt-1 bg-teal-200 "
        />
      </div>

      {/* Logo */}
    

      {/* Desktop Navigation */}
      <ul className="list-none sm:flex hidden justify-end items-center flex-1">
        {isLoggedIn
          ? authNavLinks.map((nav, index) => (
              <li
                key={nav.id}
                className={`font-poppins font-normal cursor-pointer text-[16px] ${
                  active === nav.title ? "text-teal-400" : "text-dimWhite"
                } ${index === authNavLinks.length - 1 ? "mr-0" : "mr-10"}`}
                onClick={() => setActive(nav.title)}
                style={{ marginRight: 40 }}
              >
                <a href={`/${nav.id}`}>{nav.title}</a>
              </li>
            ))
          : notAuthNavLinks.map((nav, index) => (
              <li 
                key={nav.id}
                className={`font-poppins font-normal cursor-pointer text-[14px]${
                  active === nav.title ? "text-teal-400" : "text-dimWhite"
                } ${index === notAuthNavLinks.length - 1 ? "mr-0" : "mr-10"}`}
                onClick={() => setActive(nav.title)}
                style={{ marginRight: 40 }}
              >
                <a href={`/${nav.id}`}>{nav.title}</a>
              </li>
            ))}
      </ul>
    </nav>
  );
};

export default Navbar;
