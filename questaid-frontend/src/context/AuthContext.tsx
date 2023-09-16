import React, { ReactNode, useState, useContext } from 'react'
import IContextProps from './IContextProps'

const AuthContext = React.createContext<IContextProps | undefined>(undefined)

interface IContextProviderProps {
    children: ReactNode
}

const AuthProvider: React.FC<IContextProviderProps> = ({ children }) => {
    const [isLoggedIn, setLoggedIn] = useState<boolean>(false)
    const [userCache, setUserCache] = useState<any>({})
    const [jwtToken, setJwtToken] = useState<any>("")


    return (
        <AuthContext.Provider
            value={{
                isLoggedIn,
                setLoggedIn,
                userCache,
                setUserCache,
                jwtToken,
                setJwtToken
            }}
        >
            {children}
        </AuthContext.Provider>
    )
}

const useAuthContext = () => {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error('useAppContext must be used within an AppProvider');
    }
    return context;
};

export { AuthProvider, AuthContext, useAuthContext }