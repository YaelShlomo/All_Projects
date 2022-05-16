
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';
const useStyles = makeStyles((theme) => ({
    paper: {
      padding:'3%',
      color: '#2bbbad',
      margin:"2%",

    "& p":{
      textAlign:'center'
    }
      
    },

  }));
function AboutMe() {
    const classes = useStyles();

    return (
       
        <Paper className={classes.paper} elevation={3} >
            <p>שלום כאן יהודית שלומוביץ,</p>
            <p>צלמת מיקצועית בירושלים </p>
            <p>אין! </p>
            <p>אין בעולם משהו מדהים יותר</p>
            <p>מלראות את הילד שלי בתמונה כשהוא עם עיניים קונדסיות וחיוך ענק,</p>
            <p>לאכול את הילד יחד עם התמונה...</p>
            <p>אני אוהבת לתת את הנחת הזו לאימהות בסט צילומי משפחה מרהיבים.</p>
            <p>או להעביר הלאה בקורסים מגוונים</p>
            <p>את הידע והניסיון שצברתי במשך השנים</p>
            <p>כדי שיוכלו גם הם להפוך לצלמות או לצלם במשפחה</p>
            <p>אני מזמינה אתכם לסט צילומים,</p>
            <p>לקבל תמונות שכיף להסתכל ולהשוויץ בהן שוב ושוב,</p>
            <p>ויום שכולו כיף,</p>
        </Paper>
  
    )
}
export default AboutMe