-- 코드를 입력하세요
-- 2022년 10월 5일에 등록된
-- 거래상태가 SALE 이면 판매중, RESERVED이면 예약중, DONE이면 거래완료
-- 게시글 ID를 기준으로 내림차순
SELECT u.BOARD_ID, u.WRITER_ID, u.TITLE, u.PRICE, (
    CASE
        WHEN u.STATUS = 'SALE' THEN '판매중'
        WHEN u.STATUS = 'RESERVED' THEN '예약중'
        WHEN u.STATUS = 'DONE' THEN '거래완료'
        ELSE '상태없음'
    END
) AS 'STATUS' FROM USED_GOODS_BOARD AS u
WHERE CREATED_DATE = '2022-10-05'
ORDER BY u.BOARD_ID DESC;