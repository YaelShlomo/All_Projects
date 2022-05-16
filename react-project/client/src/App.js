import React, { useState } from 'react'
import './App.css';
import NavbarPage from '../src/components/navbar/navbar'
import n1 from './asset/images/n1.png'
import s1 from './asset/images/s1.png'
import c1 from './asset/images/c1.png'
import p9 from './asset/images/p9.png'
import h1 from './asset/images/h1.jpg'
import RTL from './components/layout/rtl';
import customTheme from './components/layout/customTheme';
import {  ThemeProvider } from '@material-ui/core/styles';

function App() {
const [isAdmin, setIsAdmin]=useState(false);
const [cart, setCart] = useState([]);
const [itemCounter, setItemCounter]=useState(0);
const [sum,setSum]=useState(0);
const packageList=[{id:1, title:"ניו בורן",desc:"צילומי ניו בורן בסטודיו חדשני ומאובזר", galleryLink:"/gallery-newborn", imgSrc:n1, PackageSize:10, price:200},
{id:2, title:"סמאש קייק",desc:"צילומים חדשניים לגיל שנה, עם עוגת ויטרינה מפוארת", galleryLink:"/gallery-SmashCake", imgSrc:s1, PackageSize:20, price:300},
{id:3, title:"חלאקה",desc:"צילומי חלאקה עם מגוון אביזרים גדול", galleryLink:"/gallery-Halaka", imgSrc:c1, PackageSize:30, price:400},
{id:4, title:"צילמי חוץ",desc:"צילומים מרהיבים בעיר העתיקה, פארקים וכד'", galleryLink:"/gallery-children",imgSrc:p9,
PackageSize:40, price:500},{id:5, title:"צילמי אדריכלות",desc:"צילום דירות, פנטאוזים ביניינים ומגדלים לקטלוגים", galleryLink:"/gallery-dipartment",imgSrc:h1,
PackageSize:5, price:350}];
  return (
  <RTL>
     <ThemeProvider theme={customTheme}>
      <NavbarPage cart={cart} setCart={setCart} packageList={packageList} itemCounter={itemCounter} setItemCounter={setItemCounter} 
      itemCounter={itemCounter} setItemCounter={setItemCounter} sum={sum} setSum={setSum}
      isAdmin={isAdmin} setIsAdmin={setIsAdmin}></NavbarPage>
      {/* <CustomizedBadges itemsCounter={itemCounter}><Link to="/cart"></Link></CustomizedBadges> */}
      </ThemeProvider>
 </RTL>
  );
}

export default App;
