--상품---------------------------------------------------------------------------------------------------
INSERT INTO PRODUCT
(ID, SEASON_TYPE, `NAME`, TYPE_CODE, PRICE, CREATED_DATE, UPDATED_DATE, CREATED_BY, UPDATED_BY, IS_DELETED)
VALUES (1, 0, '셔츠(하계)', '10', 50000 , current_timestamp, current_timestamp, 1, 1, false),
       (2, 1, '셔츠(동계)', '11', 40000, current_timestamp, current_timestamp, 1, 1, false),
       (3, 0, '블라우스(하계)', '12', 40000, current_timestamp, current_timestamp, 1, 1, false),
       (4, 1, '블라우스(동계)', '13', 40000, current_timestamp, current_timestamp, 1, 1, false),
       (5, 0, '남자바지(하계)', '20', 40000, current_timestamp, current_timestamp, 1, 1, false),
       (6, 1, '남자바지(동계)', '21', 40000, current_timestamp, current_timestamp, 1, 1, false),
       (7, 0, '치마(하계)', '22', 40000, current_timestamp, current_timestamp, 1, 1, false),
       (8, 1, '치마(동계)', '23', 40000, current_timestamp, current_timestamp, 1, 1, false),
       (9, 1, '남자자켓(동계)', '30', 40000, current_timestamp, current_timestamp, 1, 1, false),
       (10, 1, '여자자켓(동계)', '31', 40000, current_timestamp, current_timestamp, 1, 1, false);
--------------------------------------------------------------------------------------------------------
