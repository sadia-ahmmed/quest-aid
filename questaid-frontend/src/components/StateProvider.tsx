import React, { createContext, useContext, useReducer, ReactNode } from "react";
import demoUser from "./demoUser";
interface StateProviderProps {
  reducer: (state: any, action: any) => any;
  initialState: any;
  children: ReactNode;
}

export const StateContext = createContext<any | undefined>(undefined);

export const StateProvider: React.FC<StateProviderProps> = ({ reducer, initialState, children }) => (
<StateContext.Provider value={{ state: useReducer(reducer, initialState), user: demoUser }}>
    {children}
  </StateContext.Provider>
);

export const useStateValue = () => useContext(StateContext);
