import { useState } from "react";
import React from "react";
import { useAuthContext } from "../../../context/AuthContext";

const notAuthNavLinks = [
  {
    id: "home",
    title: "Home",
  },
  {
    id: "login",
    title: "Login",
  },
]

const authNavLinks = [
  {
    id: "feed",
    title: "Feed",
  },
]

// TODO: fix navbar
const Navbar = () => {
  const [active, setActive] = useState("Home");
  const [toggle, setToggle] = useState(false);
  const { isLoggedIn } = useAuthContext()

  return (
    <nav className="w-full flex py-2 justify-between items-center navbar">
      {/* Logo */}
      <h1 className="text-3xl text-white" style={{ marginLeft: 30, }}>Logo</h1>

      {/* Desktop Navigation */}
      <ul className="list-none sm:flex hidden justify-end items-center flex-1">
        {isLoggedIn ?
          authNavLinks.map((nav, index) => (
            <li
              key={nav.id}
              className={`font-poppins font-normal cursor-pointer text-[16px] ${active === nav.title ? "text-cyan-500" : "text-dimWhite"
                } ${index === authNavLinks.length - 1 ? "mr-0" : "mr-10"}`}
              onClick={() => setActive(nav.title)}
              style={{ marginRight: 40, }}
            >
              <a href={`/${nav.id}`}>{nav.title}</a>
            </li>
          ))
          :
          notAuthNavLinks.map((nav, index) => (
            <li
              key={nav.id}
              className={`font-poppins font-normal cursor-pointer text-[16px] ${active === nav.title ? "text-cyan-500" : "text-dimWhite"
                } ${index === notAuthNavLinks.length - 1 ? "mr-0" : "mr-10"}`}
              onClick={() => setActive(nav.title)}
              style={{ marginRight: 40, }}
            >
              <a href={`/${nav.id}`}>{nav.title}</a>
            </li>
          ))}
      </ul>
    </nav>
  );
};

export default Navbar;