import createMuiTheme from "@material-ui/core/styles/createMuiTheme";

const primaryColor = "#f44336";
const lightPrimaryColor = "#f44336";
const brightPrimaryColor = "#ef5350";
 const disableBackgroundColor = "#ef5350";
const secondaryColor = "#ffeb3b";
const lightSecondaryColor = "#ffee58";
const darkSecondaryColor = "#fdd835";
const brightSecondaryColor = "#fff59d";
const contrastTextSecondaryColor = "#4fc3f7";
const inheritColor = "#81d4fa";
const lightInheritColor = "#b3e5fc";
const brightInheritColor = "#03a9f4";
const linkColor = "#0277bd";
const errorColor = "#d90000";
const lightErrorColor = "#fbe5e5";
const white = "#ffffff";
const black = "#000000";
const gray = "#707070";
const red = "#D90000";
const lightGray = "#F9F9F9";
const inactive = "#e7edf2";
const basicFontSize = 16;
const labelFontSize = 14;
const titleFontSize = 18;
const svgIconFontSize = 24;
const basicFontFamily = "Arial";
const boldBasicFontFamily = "arimo-bold, Arial";

const customTheme = createMuiTheme({
  direction: "rtl",
  textAlign:"center",
  palette: {
    primary: {
      main: primaryColor,
      light: lightPrimaryColor,
      bright: brightPrimaryColor,
    },
    secondary: {
      main: secondaryColor,
      light: lightSecondaryColor,
      dark: darkSecondaryColor,
      bright: brightSecondaryColor,
      contrastText: contrastTextSecondaryColor,
    },
    inherit: {
      main: inheritColor,
      light: lightInheritColor,
      bright: brightInheritColor,
    },
    error: { main: errorColor, light: lightErrorColor },
    disable: { main: disableBackgroundColor },
    background: {
      default: lightGray,
    },
    focus: lightPrimaryColor,
    gray,
    white,
    black,
    red,
  },

  typography: {
    useNextVariants: true,
    fontSize: basicFontSize,
    fontFamily: basicFontFamily,
    subtitle1: {
      fontFamily: boldBasicFontFamily,
      fontSize: basicFontSize + 2,
      color: primaryColor,
    },
  },
  additionalStyles: {
    consts: {
      primaryColor,
      lightPrimaryColor,
      brightPrimaryColor,
      disableBackgroundColor,
      secondaryColor,
      lightSecondaryColor,
      brightSecondaryColor,
      linkColor,
      errorColor,
      white,
      basicFontSize,
      labelFontSize,
      titleFontSize,
      svgIconFontSize,
      basicFontFamily,
      boldBasicFontFamily,
      inactive,
    },
    header: {
      primaryHeader: {
        height: 60,
        flip: false,
      },
      upToolbarHeader: {
        height: 50,
      },
    },
    focused: {
      fields: {
        border: `solid 2px ${primaryColor}`,
        outline: primaryColor,
      },
      outline: `solid 1px ${primaryColor}`,
    },
    formContent: { width: 900, margin: "0 auto" },
    linkButton: {
      backgroundColor: white,
      fontSize: basicFontSize,
      border: "none",
      color: linkColor,
      boxShadow: "none",
      padding: "0 5px",
      textDecoration: "underline",
      "&:hover": {
        backgroundColor: white,
        border: "none",
      },
    },
    svgIcon: {
      fontSize: svgIconFontSize,
      width: "1em",
      height: "1em",
      fill: "currentColor",
    },
    hidden: {
      display: "none",
    },
  },
});

export default customTheme;