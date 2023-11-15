import { useState, useEffect } from 'react';
import axios from 'axios';

import './access.css';

function LoginBox({ isLogin, switchAccess }) {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    console.log('Submitted login');
    e.preventDefault();

    const apiUrl = 'http://localhost:8080/api/auth/login';

    axios
      .post(apiUrl, formData)
      .then((response) => {
        const token = response.data.token;
        console.log('Received token:', token);
        localStorage.setItem('token', token);
        window.location.href = '/discover';
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  return (
    <div
      className="loginBox"
      style={{
        opacity: isLogin ? '1' : '0',
        pointerEvents: isLogin ? 'auto' : 'none',
      }}
    >
      <div className="title">NO GLUTEN</div>

      <form onSubmit={handleSubmit}>
        <input
          className="inputField"
          placeholder="Email"
          name="email"
          value={formData.email}
          onChange={handleChange}
        />

        <input
          className="inputField"
          placeholder="Password"
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
        />

        <button className="accessButton loginButton" type="submit">
          Log In
        </button>
      </form>

      <div className="promptWrapper">
        <span className="prompt">Don't have an account yet?</span>
        <a
          className="prompt"
          onClick={switchAccess}
          style={{ color: 'var(--promptColor)', fontWeight: 'bold' }}
        >
          &nbsp;Sign up.
        </a>
      </div>
    </div>
  );
}

function SignUpBox({ isLogin, switchAccess }) {
  const [formData, setFormData] = useState({
    displayName: '',
    email: '',
    password: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    console.log('Submitted signup');
    e.preventDefault();

    const apiUrl = 'http://localhost:8080/api/auth/signup';
    axios
      .post(apiUrl, formData)
      .then((response) => {
        const token = response.data.token;
        console.log('Received token:', token);
        localStorage.setItem('token', token);
        window.location.href = '/discover';
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  return (
    <div
      className="signUpBox"
      style={{
        opacity: isLogin ? '0' : '1',
        pointerEvents: isLogin ? 'none' : 'auto',
      }}
    >
      <div className="title">NO GLUTEN</div>

      <form onSubmit={handleSubmit}>
        <input
          className="inputField"
          placeholder="Username"
          type="text"
          name="displayName"
          value={formData.displayName}
          onChange={handleChange}
        />

        <input
          className="inputField"
          placeholder="Email"
          name="email"
          value={formData.email}
          onChange={handleChange}
        />

        <input
          className="inputField"
          placeholder="Password"
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
        />

        <button className="accessButton signUpButton" type="submit">
          Sign Up
        </button>
      </form>

      <div className="promptWrapper">
        <span className="prompt">Already have an account?</span>
        <a
          className="prompt"
          onClick={switchAccess}
          style={{ color: 'var(--promptColor)', fontWeight: 'bold' }}
        >
          &nbsp;Log in.
        </a>
      </div>
    </div>
  );
}

export default function Access() {
  const [isLogin, setIsLogin] = useState(true);
  function switchAccess() {
    setIsLogin(!isLogin);
    console.log('Set value to ' + (isLogin ? 'signup' : 'login'));
  }

  return (
    <div className='body'>
      <div className='background'>
        <div className='parallax'></div>
      </div>
      <div className='accessWrapper'>
        <LoginBox isLogin={isLogin} switchAccess={switchAccess} />
        <SignUpBox isLogin={isLogin} switchAccess={switchAccess} />
      </div>
    </div>
  );
}
