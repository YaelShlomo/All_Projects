import 'date-fns';
import React from 'react';
import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import {
  MuiPickersUtilsProvider,
  KeyboardTimePicker,
  KeyboardDatePicker,
} from '@material-ui/pickers';


export default function MaterialUIPickers(props) {
  // The first commit of Material-UI
   const [selectedDate, setSelectedDate] = React.useState(new Date());
  const handleDateChange = (date) => {
    
    setSelectedDate(date);
    const newDate=new Date(date);
    newDate.setMonth(newDate.getMonth()+1);
    // I raised the number of the month by 1 in order to have a regular representation
    // I built from the date a string in a readable and standard way
     props.onChange('date', newDate.getMonth()+"/"+newDate.getDate()+"/"+newDate.getFullYear());
   
  };
  
  function shoulddisbaledate(date) {
    
    let check=false;
    props.notAvailableDats.forEach(element => 
       {
        // All calendar dates listed in JSON will not be enabled
      if(date.getDate()==element.getDate() && date.getMonth()==element.getMonth() && date.getYear()==element.getYear()){
        check=true
        return true;
      }
      
    });
    return check;
  }
  return (
    <MuiPickersUtilsProvider utils={DateFnsUtils}>
      <Grid >
        <KeyboardDatePicker
          format="MM/dd/yyyy"
          id="date-picker-inline"
          label={props.text}
          value={selectedDate}
          onChange={handleDateChange}
          shouldDisableDate={shoulddisbaledate}
          KeyboardButtonProps={{
            'aria-label': 'change date',
          }}
          minDate={Date()}
        />
      </Grid>
    </MuiPickersUtilsProvider>
  );
}
