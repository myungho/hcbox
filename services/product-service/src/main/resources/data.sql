--상품---------------------------------------------------------------------------------------------------
INSERT INTO PRODUCT
(ID, GENDER, SEASON_TYPE, `NAME`, TYPE_CODE, PRICE, SCHOOL_ID, CREATED_DATE, UPDATED_DATE, CREATED_BY, UPDATED_BY, IS_DELETED)
VALUES (1, 0, 0, 'Shirts', '10', 50000 , 1, current_timestamp, current_timestamp, 1, 1, false),
       (2, 0, 1, 'Shirts', '11', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (3, 1, 0, 'Blouse', '12', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (4, 1, 1, 'Blouse', '13', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (5, 0, 0, 'Pants', '20', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (6, 0, 1, 'Pants', '21', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (7, 1, 0, 'Skirt', '22', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (8, 1, 1, 'Skirt', '23', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (9, 0, 1, 'Jacket', '30', 40000, 1, current_timestamp, current_timestamp, 1, 1, false),
       (10, 1, 1, 'Jacket', '31', 40000, 1, current_timestamp, current_timestamp, 1, 1, false);
--------------------------------------------------------------------------------------------------------
