export async function get(endpoint) {
   
    const  url = `http://localhost:5000${endpoint}`;
    
    const response = await fetch(url, {
      method: "GET",
    }).then((res) => {
      if (!res.ok) {
        res.text().then((text) => {
         return text.message
        });
      } else {
        return res.json();
      }
    });
    return response;
  }
  
  export async function post(endpoint, data) {
    const  url = `http://localhost:5000${endpoint}`;

    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    }).then((res) => {
      if (!res.ok) {
       console.log("err",res);
          return res.json(res)
       
      } else {
        console.log("res",res);
        return res.json(res);
      }
    });
    return response;
  }  