import * as React from "react";

import { login } from "./loginPromise";
import qa from '../../images/qa.png'

interface LoginState {
  password: string;
  username: string;
  isLoading: boolean;
  error: string;
  isLoggedIn: boolean;
}

type LoginAction =
  | { type: "login" | "success" | "error" | "logout" }
  | { type: "field"; fieldName: string; payload: string };

const loginReducer = (state: LoginState, action: LoginAction): LoginState => {
  switch (action.type) {
    case "field": {
      return {
        ...state,
        [action.fieldName]: action.payload
      };
    }
    case "login": {
      return {
        ...state,
        error: "",
        isLoading: true
      };
    }
    case "success": {
      return { ...state, error: "", isLoading: false, isLoggedIn: true };
    }
    case "error": {
      return {
        ...state,
        isLoading: false,
        isLoggedIn: false,
        username: "",
        password: "",
        error: "Incorrect username or password!"
      };
    }
    case "logout": {
      return {
        ...state,
        isLoggedIn: false
      };
    }
    default:
      return state;
  }
};

const initialState: LoginState = {
  password: "",
  username: "",
  isLoading: false,
  error: "",
  isLoggedIn: false
};

const Login = () => {
  const [state, dispatch] = React.useReducer(loginReducer, initialState);
  const { username, password, isLoading, error, isLoggedIn } = state;

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    dispatch({ type: "login" });

    try {
      await login({ username, password });
      dispatch({ type: "success" });
    } catch (error) {
      dispatch({ type: "error" });
    }
  };

  return (
    
    <div className="flex min-h-full">
    <div className="w-1/2 bg-cover" style={{ backgroundImage: `url(https://img.freepik.com/premium-vector/education-distance-education-internet-studying-elearning-remote-learning-flat-vector_1200-847.jpg?w=4000)`, 
          backgroundSize: 'cover' ,  height: '90vh', width: '120vh',}}>
      {/* Right-hand side image */}
    </div>
    <div className="w-1/2 bg-white flex flex-col justify-center px-6 py-12 lg:px-8" >

      <img
        
            className="mx-auto h-30 w-auto"
            src={qa} 
            alt="Your Company"
          />
           <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-teal-800">
            Sign in to your account
          </h2>
        {isLoggedIn ? (
          <>
            <p>{`Hello ${username}`}</p>
            <button type="button" onClick={() => dispatch({ type: "logout" })}>
              Log out
            </button>
          </>
        ) : (
          <>

          <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm"  >
          <form className="space-y-6" onSubmit={onSubmit} >
            {error && <p className="error">{error}</p>}
            <div>
              <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900" style={{marginLeft:-300}}>
                Email address
              </label>
              <div className="mt-2">
            <input
              type="text"
              placeholder="username"
              value={username}
              className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              onChange={(e) =>
                dispatch({
                  type: "field",
                  fieldName: "username",
                  payload: e.currentTarget.value
                })
              }
            />
          </div>
          </div>

           <div>
              <div className="flex items-center justify-between">
                <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                  Password
                </label>
                <div className="text-sm">
                  <a href="#" className="font-semibold text-teal-600 hover:text-teal-500">
                    Forgot password?
                  </a>
                </div>
              </div>
            <div className="mt-2">
            <input
             className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              type="password"
              placeholder="password"
              autoComplete="new-password"
              value={password}
              onChange={(e) =>
                dispatch({
                  type: "field",
                  fieldName: "password",
                  payload: e.currentTarget.value
                })
              }
            />

            </div>
            </div>

            <div>
            <button type="submit" 
            className="flex w-full justify-center rounded-md bg-teal-800 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-teal-600 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-teal-600" disabled={isLoading}>
              {isLoading ? "Loggin in....." : "Log In"}
            </button>
            </div>
          </form>
          </div>
          </>
        )}
          <p className="mt-10 text-center text-sm text-gray-500">
            Not a member?     {'     '}   
            <a href="#" className="font-semibold leading-6 text-teal-600 hover:text-teal-500">
                 SignUp
            </a>
          </p>
        </div>
      </div>
    
  );
}
export default Login ;