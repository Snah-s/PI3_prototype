import React, { useEffect, StrictMode } from "react";
import * as ReactDOM from "react-dom/client";

import {
  createBrowserRouter,
  RouterProvider,
  Route,
  Navigate,
} from "react-router-dom";

import Access from "./pages/access/access";
import Dashboard from "./pages/dashboard/dashboard";
import Post from "./pages/post/post";

import "./index.css";
import Cookies from "js-cookie";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Access />,
  },
  {
    path: "access",
    element: <Access />,
  },
  {
    path: "dashboard",
    element: <Dashboard />,
  },
  {
    path: "post/:id",
    element: <Post />,
  },
]);

function checkSession() {
  const token = Cookies.get("token");
  console.log("Session checked...");
  if (!token) {
    localStorage.removeItem("displayName");
    router.navigate("/access");
  }
}

function App() {
  const loginCheckInterval = 30; // Time in seconds between periodic JWT validations
  useEffect(() => {
    checkSession();
    const intervalId = setInterval(checkSession, loginCheckInterval * 1000);
    return () => clearInterval(intervalId);
  }, []);
  return (
    <StrictMode>
      <RouterProvider router={router} />
    </StrictMode>
  );
}

ReactDOM.createRoot(document.getElementById("root")).render(<App />);
