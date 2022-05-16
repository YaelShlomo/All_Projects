
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));

export default function ColorTextFields(props) {
  
  const classes = useStyles();
const{caption,id,variant,size, onChange}=props;
  return (
    <form className={classes.root} noValidate autoComplete="off">
      <TextField id="standard-secondary" label={caption} color="primary" size={size}  onChange={onChange} variant={variant}
/>
    </form>
  );
}

