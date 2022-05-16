
import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Accordion from '@material-ui/core/Accordion';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import customTheme from '../layout/customTheme'
import { get } from '../../services/httpHandler‏';
import Add from '../q&a/question'
const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    heading: {
        fontSize: theme.typography.pxToRem(15),
        flexBasis: '33.33%',
        flexShrink: 0,
        color: customTheme.palette.primary.main,

    },
    space: {
        marginTop: 60,
    }
}));

export default function ControlledAccordions() {
    const classes = useStyles();
    const [expanded, setExpanded] = React.useState(false);

    const handleChange = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };

    //
    const [questionsList, setQuesstionsList] = React.useState([]);
    useEffect(async () => {
        var data = await get("/getQuestionAns");
        setQuesstionsList(data);
    }, []);

    const addToQuestionsList = (newQuestion) => {
        const questionsListToUpdate = questionsList;
        setQuesstionsList([newQuestion, ...questionsListToUpdate]);
    }
    let i=1;
    //
    return (
        <div className={classes.root} className={classes.space}>
            {questionsList && questionsList.map((item) => {
                const penal = "panel"+`${i}`; 
                {console.log(penal)}
                i++;
                return(
                <div>
                    <Accordion expanded={expanded === penal} onChange={handleChange(penal)}>
                        <AccordionSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls="panel1bh-content"
                            id="panel1bh-header"
                        >
                            <Typography className={classes.heading}>{item.question}</Typography>
                            <Typography className={classes.secondaryHeading}></Typography>
                        </AccordionSummary>
                        <AccordionDetails>
                            <Typography>
                                {item.answer}
                    </Typography>
                        </AccordionDetails>
                    </Accordion>
                    <br></br>
                </div>
                )
            })}
               <Add questionsList={questionsList} addToQuestionsList={addToQuestionsList}></Add>
        </div>
     
    );
}
