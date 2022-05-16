import React from "react";
import '../../App.css';
import './adminEnterPage.css';


const Enter = () => {
    return (
        <div className="adminOptions">

            <a href="/" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; דף הבית &nbsp;  &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a>
            <br />
            <a href="/addadmin" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true"> &nbsp; &nbsp;הוספת מנהל לאתר &nbsp; &nbsp;</a>
            <br />
            <a href="/OrderDetails" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;פרטי הזמנות &nbsp; &nbsp; &nbsp; &nbsp; </a>
            <br />
            <a href="/administratorsdetails" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">מנהלי האתר</a>
            <br></br>
            <a href="/contacts" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">הלקוחות שלי</a>

        </div>
    );
};
export default Enter;

