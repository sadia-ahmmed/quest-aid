import React, { useState } from "react";

// MUI Icons
import MenuIcon from "@mui/icons-material/Menu";
import CloseIcon from "@mui/icons-material/Close";


// ROUTING
import { Link } from "react-router-dom";

// DATA FILE
import { AdminSidebarData, ClubSidebarData } from "./SidebarData";

// STYLES
import "./Sidebar.css";

interface NavbarProps { }

interface SidebarItem {
  path: string;
  icon: React.ReactElement;
  title: string;
  cName: string;
}

export default function Sidebar(props: NavbarProps): JSX.Element {

  const [sidebar, setSidebar] = useState(true);

  const entityType = localStorage.getItem("entityType")

  const showSidebar = () => setSidebar(true);

  return (
    <>
      <nav className={sidebar ? "nav-menu active" : "nav-menu"}>
        <ul className="nav-menu-items" onClick={showSidebar}>
          {/* <li className="navbar-toggle">
              <Link to="#" className="menu-bars">
                <CloseIcon />
              </Link>
            </li> */}

          {entityType === "club" && ClubSidebarData.map((item: SidebarItem, index: number) => {
            return (
              <li key={index} className={item.cName}>
                <Link to={item.path}>
                  {item.icon}
                  <span>{item.title}</span>
                </Link>
              </li>
            );
          })}

          {entityType === "admin" && AdminSidebarData.map((item: SidebarItem, index: number) => {
            return (
              <li key={index} className={item.cName}>
                <Link to={item.path}>
                  {item.icon}
                  <span>{item.title}</span>
                </Link>
              </li>
            );
          })}

        </ul>
      </nav>
    </>
  );
}
