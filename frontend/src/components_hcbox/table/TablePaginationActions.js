import React, { useEffect, useState } from "react";

import { makeStyles, useTheme } from "@material-ui/core/styles";
import { IconButton, Input } from "@material-ui/core";
import FirstPage from "@material-ui/icons/FirstPage";
import LastPage from "@material-ui/icons/LastPage";
import KeyboardArrowRight from "@material-ui/icons/KeyboardArrowRight";
import KeyboardArrowLeft from "@material-ui/icons/KeyboardArrowLeft";

const useStyles = makeStyles(theme => ({
  root: {
    flexShrink: 0,
    marginLeft: theme.spacing(2.5)
  }
}));

export default function TablePaginationActions(pros) {
  const classes = useStyles();
  const theme = useTheme();
  const { count, page, rowsPerPage, onChangePage } = pros;
  const [pageNo, setPageNo] = useState(1);

  useEffect(() => {
    setPageNo(1);
  }, [rowsPerPage]);

  useEffect(() => {
    setPageNo(page + 1);
  }, [page]);

  const handleFirstPageButtonClick = event => {
    onChangePage(event, 0);
  };

  const handleBackButtonClick = event => {
    onChangePage(event, page - 1);
  };

  const handleNextButtonClick = event => {
    onChangePage(event, page + 1);
  };

  const handleLastPageButtonClick = event => {
    onChangePage(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
  };

  const pageJump = event => {
    let newPage = event.target.value;

    const totalPage = calcPage();
    if (newPage <= 0 && verifyNumber(newPage)) {
      setPageNo(1);
      newPage = 1;
    } else if ((newPage - 1 > totalPage) & verifyNumber(newPage)) {
      setPageNo(totalPage + 1);
      newPage = totalPage + 1;
    } else {
      setPageNo(newPage);
    }

    if (!verifyNumber(newPage)) return;
    //   onChangePage(event, newPage - 1);
  };

  const handleKeyPress = event => {
    if (event.key === "Enter") {
      onChangePage(event, pageNo - 1);
    }
  };

  const verifyNumber = value => {
    var numberRex = new RegExp("^[0-9]+$");
    if (numberRex.test(value)) {
      return true;
    }
    return false;
  };

  const calcPage = () => {
    return Math.max(0, Math.ceil(count / rowsPerPage)) - 1;
  };

  return (
    <div className={classes.root}>
      <Input
        labelText={"of  ".concat(calcPage() + 1)}
        formControlProps={{
          size: "small"
        }}
        inputProps={{
          onChange: pageJump,
          onKeyPress: handleKeyPress,
          type: "text",
          value: pageNo
        }}
      />
      <IconButton
        onClick={handleFirstPageButtonClick}
        disabled={page === 0}
        aria-label="first page"
      >
        {theme.direction === "rtl" ? <LastPage /> : <FirstPage />}
      </IconButton>
      <IconButton
        onClick={handleBackButtonClick}
        disabled={page === 0}
        aria-label="prev page"
      >
        {theme.direction === "rtl" ? (
          <KeyboardArrowRight />
        ) : (
          <KeyboardArrowLeft />
        )}
      </IconButton>
      <IconButton
        onClick={handleNextButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="next page"
      >
        {theme.direction === "rtl" ? (
          <KeyboardArrowLeft />
        ) : (
          <KeyboardArrowRight />
        )}
      </IconButton>
      <IconButton
        onClick={handleLastPageButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="last page"
      >
        {theme.direction === "rtl" ? <FirstPage /> : <LastPage />}
      </IconButton>
    </div>
  );
}
