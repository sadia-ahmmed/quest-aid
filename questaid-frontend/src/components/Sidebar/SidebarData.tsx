import React from "react";
import HomeIcon from "@mui/icons-material/Home";
import AssignmentIcon from "@mui/icons-material/Assignment";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import EmailIcon from "@mui/icons-material/Email";
import HelpIcon from "@mui/icons-material/Help";
import PersonAddAlt1Icon from '@mui/icons-material/PersonAddAlt1';
import EventIcon from '@mui/icons-material/Event';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
export interface SidebarItem {
  title: string;
  path: string;
  icon: React.ReactElement;
  cName: string;
}

export const ClubSidebarData: SidebarItem[] = [
  {
    title: "Dashboard",
    path: "/",
    icon: <HomeIcon />,
    cName: "nav-text",
  },
  {
    title: "Treasurey",
    path: "/reports",
    icon: <AccountBalanceIcon />,
    cName: "nav-text",
  },
  {
    title: "Departments",
    path: "/announcements",
    icon: <ShoppingCartIcon />,
    cName: "nav-text",
  },
  {
    title: "Members",
    path: "/team",
    icon: <PeopleAltIcon />,
    cName: "nav-text",
  },
  {
    title: "Announcements",
    path: "/announcements",
    icon: <EmailIcon />,
    cName: "nav-text",
  },
  {
    title: "Events",
    path: "/events",
    icon: <EventIcon />,
    cName: "nav-text",
  },
  {
    title: "Recruitement",
    path: "/recruitment",
    icon: < PersonAddAlt1Icon />,
    cName: "nav-text",
  },
];
