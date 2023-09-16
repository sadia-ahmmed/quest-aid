import React from "react";

export default interface IContextProps {
    isLoggedIn: boolean,
    setLoggedIn: React.Dispatch<React.SetStateAction<boolean>>,
    userCache: any,
    setUserCache: React.Dispatch<React.SetStateAction<any | null>>,
    jwtToken: string,
    setJwtToken: React.Dispatch<React.SetStateAction<string | null>>
}