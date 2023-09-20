import React from "react";
import HomeIcon from "@mui/icons-material/Home";
import AssignmentIcon from "@mui/icons-material/Assignment";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import EmailIcon from "@mui/icons-material/Email";
import HelpIcon from "@mui/icons-material/Help";

export interface SidebarItem {
  title: string;
  path: string;
  icon: React.ReactElement;
  cName: string;
}

export const SidebarData: SidebarItem[] = [
  {
    title: "Home",
    path: "/",
    icon: <HomeIcon />,
    cName: "nav-text",
  },
  {
    title: "Reports",
    path: "/reports",
    icon: <AssignmentIcon />,
    cName: "nav-text",
  },
  {
    title: "Products",
    path: "/products",
    icon: <ShoppingCartIcon />,
    cName: "nav-text",
  },
  {
    title: "Team",
    path: "/team",
    icon: <PeopleAltIcon />,
    cName: "nav-text",
  },
  {
    title: "Messages",
    path: "/",
    icon: <EmailIcon />,
    cName: "nav-text",
  },
  {
    title: "Support",
    path: "/support",
    icon: <HelpIcon />,
    cName: "nav-text",
  },
];
