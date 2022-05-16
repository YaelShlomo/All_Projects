import React, { useEffect } from 'react';
import Comments from './comments'
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import Divider from '@material-ui/core/Divider';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Avatar from '@material-ui/core/Avatar';
import Typography from '@material-ui/core/Typography';
import { get } from '../../services/httpHandler‏';
import Rating from '@material-ui/lab/Rating';
import Box from '@material-ui/core/Box';
const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
    maxWidth: '50%',
    backgroundColor: theme.palette.background.paper,

  },

  inline: {
    display: 'inline',
  },
}));
const MediaObjectPage = (props) => {
  const classes = useStyles();

  const [commentList, setCommentList] = React.useState([]);
  useEffect(async () => {
    var data = await get("/getRespondsList");
    setCommentList(data);
  }, []);

  const addToCommentList = (newComment) => {
    const commentListToUpdate = commentList;
    setCommentList([newComment, ...commentListToUpdate]);
  }
  //‏
  return (
    <List className={classes.root}>
      <ListItem alignItems="flex-start">
        <Comments commentList={commentList} addToCommentList={addToCommentList}></Comments>
      </ListItem>
      <Divider variant="inset" component="li" />

      {commentList && commentList.map((item, key) => {
        // Go through all the responses on the server and tap into the screen
        return (
          <div key={key}>
            <ListItem alignItems="flex-start">
              <ListItemAvatar>
                <Avatar alt="Generic placeholder image" src={item.imgSrc} />
              </ListItemAvatar>
              <ListItemText
                secondary={
                  <React.Fragment>
                    {item.comment}
                    <Box component="fieldset" mb={3} borderColor="transparent">
                      <Typography component="legend">{item.desc} </Typography>
                      <Rating name="read-only" value={item.value} readOnly />
                    </Box>
                  </React.Fragment>
                }
              />
            </ListItem>
            <Divider variant="inset" component="li" />
          </div>

        );
      })}
    </List>
  );


}

export default MediaObjectPage;