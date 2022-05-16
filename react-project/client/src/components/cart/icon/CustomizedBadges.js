import React from 'react';
import Badge from '@material-ui/core/Badge';
import { withStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import { blue } from '@material-ui/core/colors';
const StyledBadge = withStyles((theme) => ({
  badge: {
    right: -3,
    top: 13,
    border: `2px solid ${theme.palette.background.paper}`,
    padding: '0 4px',
    color: blue,
  },
  
}))(Badge);

export default function CustomizedBadges(props) {
  return (
    <IconButton aria-label="cart">
      <StyledBadge badgeContent={props.itemsCounter} color="secondary">
        <ShoppingCartIcon />
      </StyledBadge>
    </IconButton>
  );
}