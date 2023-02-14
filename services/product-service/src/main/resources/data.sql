--상품---------------------------------------------------------------------------------------------------
INSERT INTO PRODUCT
(ID, GENDER, SEASON_TYPE, `NAME`, TYPE_CODE, PRICE, SCHOOL_ID, CREATED_DATE, UPDATED_DATE, CREATED_BY, UPDATED_BY, IS_DELETED)
VALUES (1, 0, 0, '셔츠', '10', 50000 , 1, current_timestamp, current_timestamp, 1, 1, false),
       (2, 0, 1, '셔츠', '11', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (3, 1, 0, '블라우스', '12', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (4, 1, 1, '블라우스', '13', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (5, 0, 0, '남자바지', '20', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (6, 0, 1, '남자바지', '21', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (7, 1, 0, '치마', '22', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (8, 1, 1, '치마', '23', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (9, 0, 1, '남자자켓', '30', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (10, 1, 1, '여자자켓', '31', 40000, 1, current_timestamp, current_timestamp, 1, 1, false);
--------------------------------------------------------------------------------------------------------
