import React from 'react';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'
export default function MyToast() {

  return (
    <div>
      { toast("!החבילה נוספה לסל בהצלחה", { position: "bottom-center" })}
      <ToastContainer position="bottom-center" />
    </div>
  );
}