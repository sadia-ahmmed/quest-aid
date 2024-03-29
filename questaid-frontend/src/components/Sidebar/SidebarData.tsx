import React from "react";
import HomeIcon from "@mui/icons-material/Home";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import EmailIcon from "@mui/icons-material/Email";
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
    path: "/dashboard",
    icon: <HomeIcon />,
    cName: "nav-text",
  },
  {
    title: "Treasury",
    path: "/club/treasury",
    icon: <AccountBalanceIcon />,
    cName: "nav-text",
  },
  {
    title: "Departments",
    path: "/club/departments",
    icon: <ShoppingCartIcon />,
    cName: "nav-text",
  },
  {
    title: "Members",
    path: "/club/members",
    icon: <PeopleAltIcon />,
    cName: "nav-text",
  },
  {
    title: "Announcements",
    path: "/club/announcement/create",
    icon: <EmailIcon />,
    cName: "nav-text",
  },
  {
    title: "Events",
    path: "/club/events",
    icon: <EventIcon />,
    cName: "nav-text",
  },
];


export const AdminSidebarData: SidebarItem[] = [
  {
    title: "Dashboard",
    path: "/dashboard",
    icon: <HomeIcon />,
    cName: "nav-text",
  },
  {
    title: "Treasurey",
    path: "/admin/treasury",
    icon: <AccountBalanceIcon />,
    cName: "nav-text",
  },
  {
    title: "Add a Club",
    path: "/admin/create-club",
    icon: < PersonAddAlt1Icon />,
    cName: "nav-text",
  },
  {
    title: "Clubs",
    path: "/admin/clubs",
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
    title: "Events",
    path: "/events",
    icon: <EventIcon />,
    cName: "nav-text",
  },
];
