import moment from "moment";

const BaseSearchCondition = {
  getSearchState: condition => {
    let pageNo = 0;
    if (
      condition !== undefined &&
      condition.search !== undefined &&
      condition.search.pageNo !== undefined
    ) {
      pageNo = condition.search.pageNo;
    }

    let pageSize = 20;
    if (
      condition !== undefined &&
      condition.search !== undefined &&
      condition.search.pageSize !== undefined
    ) {
      pageSize = condition.search.pageSize;
    }

    let sortDirection = "";
    if (
      condition !== undefined &&
      condition.search !== undefined &&
      condition.search.sortDirection !== undefined
    ) {
      sortDirection = condition.search.sortDirection;
    }

    let sortField = "";
    if (
      condition !== undefined &&
      condition.search !== undefined &&
      condition.search.sortField !== undefined
    ) {
      sortField = condition.search.sortField;
    }

    return {
      pageNo: pageNo,
      pageSize: pageSize,
      sortDirection: sortDirection,
      sortField: sortField
    };
  },

  getPageOption: condition => {
    let rowsPerPage = BaseSearchCondition.getSearchState(condition).pageSize;
    if (
      condition !== undefined &&
      condition.pageOption !== undefined &&
      condition.pageOption.rowsPerPage
    ) {
      rowsPerPage = condition.pageOption.rowsPerPage;
    }

    let page = BaseSearchCondition.getSearchState(condition).pageNo;
    if (
      condition !== undefined &&
      condition.pageOption !== undefined &&
      condition.pageOption.page
    ) {
      page = condition.pageOption.page;
    }

    let count = 0;
    if (
      condition !== undefined &&
      condition.pageOption !== undefined &&
      condition.pageOption.count
    ) {
      count = condition.pageOption.count;
    }

    let totalPages = 0;
    if (
      condition !== undefined &&
      condition.pageOption !== undefined &&
      condition.pageOption.totalPages
    ) {
      totalPages = condition.pageOption.totalPages;
    }

    return {
      rowsPerPage: rowsPerPage,
      page: page,
      count: count,
      totalPages: totalPages
    };
  },

  getSearchDateState: condition => {
    let startDate = moment().format("YYYY-MM-DD");
    if (
      condition !== undefined &&
      condition.searchDate !== undefined &&
      condition.searchDate.startDate
    ) {
      startDate = condition.searchDate.startDate;
    }

    let startTime = "00:00";
    if (
      condition !== undefined &&
      condition.searchDate !== undefined &&
      condition.searchDate.startTime
    ) {
      startTime = condition.searchDate.startTime;
    }

    let endDate = moment().format("YYYY-MM-DD");
    if (
      condition !== undefined &&
      condition.searchDate !== undefined &&
      condition.searchDate.endDate
    ) {
      endDate = condition.searchDate.endDate;
    }

    let endTime = "23:59";
    if (
      condition !== undefined &&
      condition.searchDate !== undefined &&
      condition.searchDate.endTime
    ) {
      endTime = condition.searchDate.endTime;
    }

    return {
      startDate: startDate,
      startTime: startTime,
      endDate: endDate,
      endTime: endTime
    };
  },

  getSearchDateStateBeforeDays: (condition, days) => {
    let startDate = moment()
      .subtract(days, "days")
      .format("YYYY-MM-DD");
    if (
      condition !== undefined &&
      condition.searchDate !== undefined &&
      condition.searchDate.startDate
    ) {
      startDate = condition.searchDate.startDate;
    }

    let startTime = "00:00";
    if (
      condition !== undefined &&
      condition.searchDate !== undefined &&
      condition.searchDate.startTime
    ) {
      startTime = condition.searchDate.startTime;
    }

    let endDate = moment().format("YYYY-MM-DD");
    if (
      condition !== undefined &&
      condition.searchDate !== undefined &&
      condition.searchDate.endDate
    ) {
      endDate = condition.searchDate.endDate;
    }

    let endTime = "23:59";
    if (
      condition !== undefined &&
      condition.searchDate !== undefined &&
      condition.searchDate.endTime
    ) {
      endTime = condition.searchDate.endTime;
    }
    return {
      startDate: startDate,
      startTime: startTime,
      endDate: endDate,
      endTime: endTime
    };
  },

  getSearchConditionSelect: (condition, defaultValue) => {
    let conditionSelect = defaultValue;
    if (
      condition !== undefined &&
      condition.searchConditionSelect !== undefined
    ) {
      conditionSelect = condition.searchConditionSelect;
    }
    return conditionSelect;
  },

  getSearchConditionValue: condition => {
    let searchConditionValue = "";
    if (
      condition !== undefined &&
      condition.searchConditionValue !== undefined
    ) {
      searchConditionValue = condition.searchConditionValue;
    }
    return searchConditionValue;
  },

  getSearchAdditionalInfoValue: (condition, propName, defaultValue) => {
    let value = defaultValue;
    if (
      condition !== undefined &&
      condition.searchAdditionalInfoValue !== undefined
    ) {
      if (condition.searchAdditionalInfoValue[propName]) {
        value = condition.searchAdditionalInfoValue[propName];
      }
    }
    return value;
  }
};

export default {
  BaseSearchCondition
};
