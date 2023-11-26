import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

import "./product.css";
import loadingIcon from "../../assets/sync_FILL0_wght400_GRAD0_opsz24.svg";
import backButton from "../../assets/chevron_right_FILL0_wght400_GRAD0_opsz24.svg";
import noComments from "../../assets/sentiment_neutral_FILL0_wght400_GRAD0_opsz24.svg";

const apiIp = process.env.REACT_APP_API_IP;

function fetchProductData({ id, setProductData }) {
  const apiUrl = `${apiIp}/api/products/${id}`;
  const fetcher = axios.create({
    baseURL: apiUrl,
    withCredentials: false,
  });
  fetcher
    .get(apiUrl)
    .then((response) => {
      setProductData(response.data);
      console.log(response.data);
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}

function LoadingScreen() {
  return (
    <div className="loadingScreen">
      <img className="loadingIcon" src={loadingIcon} />
      <span>Loading...</span>
    </div>
  );
}

export default function Product() {
  const { id } = useParams();
  const [theme, setTheme] = useState();
  const [productData, setProductData] = useState(); // Fetched product data to display

  function setDarkTheme() {
    console.log("set Dark");
    setTheme("dark");
    localStorage.setItem("theme", "dark");
  }

  function setLightTheme() {
    console.log("set Light");
    setTheme("light");
    localStorage.setItem("theme", "light");
  }

  function themeSwitcher() {
    const storedTheme = localStorage.getItem("theme");
    switch (storedTheme) {
      case "dark": {
        setDarkTheme();
        break;
      }
      default: {
        setLightTheme();
        break;
      }
    }
  }

  useEffect(() => {
    themeSwitcher();
    fetchProductData({ id, setProductData });
  }, []);

  return (
    <div className={`body product${theme === "light" ? "" : " dark"}`}>
      <div className="appWrapper">
        <div className="contentWrapper">
          {productData ? (
            <PageContent productData={productData} />
          ) : (
            <LoadingScreen />
          )}
        </div>
      </div>
    </div>
  ); // Wait until content is loaded
}

function PageContent({ productData }) {
  const goHome = () => {
    window.location.href = "/dashboard";
  };

  return (
    <div className="content">
      <div className="dataWrapper">
        <div className="banner">
          <img className="displayImage" src={productData.imageUrl} />
          <div className="bannerContent">
            <div className="mainInfoDisplay">
              <div className="titleAndTags">
                <div className="left">
                  <span className="title">{productData.name}</span>
                  <span className="title">
                    {productData.brand}, {productData.country}
                  </span>
                </div>
                <div className="right">
                  <div className="dateWrapper">
                    <span className="title">{productData.price}</span>
                  </div>
                  <div
                    className="tagWrapper"
                    style={
                      productData.tags.length === 0 ? {} : { marginTop: "2vmin" }
                    }
                  >
                    <TagDisplayer tags={productData.tags} />
                  </div>
                </div>
              </div>
            </div>
            <div className="bannerInput">
              <button className="homeButton" onClick={goHome}>
                <img className="homeImage" src={backButton}></img>
              </button>
            </div>
          </div>
        </div>
        <div className="basicInfoWrapper">
          <div className="description">
            <span>{productData.description}</span>
          </div>
        </div>
      </div>
      <CommentSection productData={productData} />
    </div>
  );
}

function CommentSection({ productData }) {
  const [written, setWritten] = useState("");
  const [sent, setSent] = useState(false);

  const postComment = async () => {
    if (!written) {
      alert("Comment must have something written inside of it!");
      return;
    }
    setSent(true);

    const productController = `${apiIp}/api/products/${productData.id}/addcomment`;

    const fetcher = axios.create({
      baseURL: productController,
      withCredentials: false,
    });

    const displayName = localStorage.getItem("displayName");

    fetcher
      .patch(productController, {
        content: written,
        author: displayName,
      })
      .then((response) => {
        console.log(response.data);
        location.reload();
      })
      .catch((error) => {
        console.error("Error:", error);
        setSent(false);
      });
  };

  const handleChange = (product) => {
    setWritten(product.target.value);
  };

  return (
    <div className="commentsWrapper">
      <div className="writeWrapper">
        <div className="left">
          <textarea
            className="inputField"
            placeholder="Write your comment here!"
            maxLength={1000}
            onChange={handleChange}
          ></textarea>
        </div>
        <div className="right">
          <button
            className="uploadButton"
            onClick={sent ? null : postComment}
            style={sent ? { opacity: 0.5 } : {}}
          >
            <span className="title">UPLOAD</span>
          </button>
        </div>
      </div>
      <div className="userComments">
        <span className="title">User Comments</span>
        <div className="content">
          {productData.comments.length !== 0 ? (
            <CommentFetcher comments={productData.comments} />
          ) : (
            <NoComments />
          )}
        </div>
      </div>
    </div>
  );
}

function NoComments() {
  return (
    <div className="noComments">
      <img src={noComments} className="image"></img>
      <span>It's kinda empty in here...</span>
    </div>
  );
}

function CommentFetcher({ comments }) {
  const commentDivs = comments.map((comment) => (
    <div key={comment.id} className="comment">
      <span className="commentDate">Posted at: {comment.date}</span>
      <span className="commentDate">{comment.author} says:</span>
      <span className="commentText">{comment.content}</span>
    </div>
  ));
  return <>{commentDivs}</>;
}

function TagDisplayer({ tags }) {
  const tagDivs = tags.map((tag) => (
    <div
      style={{
        background: `${tag.color}`,
      }}
      key={tag.id}
      className="tag"
    >
      {tag.name}
    </div>
  ));
  return <>{tagDivs}</>;
}
