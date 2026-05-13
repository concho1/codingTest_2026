-- 코드를 입력하세요
-- 중고 거래 게시물 3건 이상 등록한 사람 찾기
SELECT 
    u.USER_ID, u.NICKNAME, 
    CONCAT(u.CITY," ",u.STREET_ADDRESS1, " ", u.STREET_ADDRESS2) AS '전체주소',
    CONCAT(SUBSTRING(TLNO, 1, 3),"-", SUBSTRING(TLNO, 4, 4),"-", SUBSTRING(TLNO, 8, 4)) 
    AS '전화번호'
FROM 
    USED_GOODS_USER AS u
    INNER JOIN (
        SELECT WRITER_ID, COUNT(*) AS cnt FROM USED_GOODS_BOARD
        GROUP BY WRITER_ID
        HAVING cnt >= 3
    ) AS c
    ON u.USER_ID = c.WRITER_ID
ORDER BY u.USER_ID DESC
