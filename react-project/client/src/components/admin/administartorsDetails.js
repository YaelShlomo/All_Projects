import React, { useEffect, useState } from "react";
import '../../App.css';
import './details.css';
import { get } from '../../services/httpHandler‏';

import { MDBTable, MDBTableBody, MDBTableFoot, MDBTableHead } from 'mdbreact';

function Details({title1, type1}) {
    const [admins, setAdmins] = useState();
    useEffect(async () => {
        var data = await get(type1);
        console.log("data:",data)
        setAdmins(data);
    }, []);

    return (
        <div>
            <h1 className="table-title">{title1}</h1>
            <br />
            <div className="tbl">
                <MDBTable class="table" autoWidth striped scrollY maxHeight="40%" >
                    <MDBTableHead>
                        <tr>
                            <th scope="col">שם</th>
                            <th scope="col">מייל</th>
                            {type1 == "/getAdmin" && <th scope="col"> סיסמה </th>}
                            {type1 == "/getOrders" && <th scope="col"> עיר </th>}
                            {type1 == "/getOrders" && <th scope="col"> תאריך </th>}
                            {type1 == "/getContact" && <th scope="col"> פלאפון </th>}


                        </tr>
                    </MDBTableHead>
                    <MDBTableBody>
                        {admins?.map(item =>
                            <tr>
                                {/* <th scope="row" >{item.id}</th> */}
                                <td> {item.name} </td>
                                <td> {item.email} </td>
                                {type1 == "/getAdmin" && <td> {item.password} </td>}
                                {type1 == "/getContact" && <td> {item.phone_number} </td>}
                                {type1 == "/getOrders" && <td>{item.city}</td>}
                                {type1 == "/getOrders" && <td>{item.date}</td>}
            
                            </tr>
                        )}
                    </MDBTableBody>

                    <MDBTableFoot>

                    </MDBTableFoot>
                </MDBTable>
                <div className="home-button">
                    <br />
                    <div id="home-button" >
                        <a href="/" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Home &nbsp;  &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a>

                    </div>
                </div>
            </div>
        </div>
    );
};
export default Details;


