-- 코드를 입력하세요
SELECT c.CAR_ID, (
    CASE
        WHEN
            c.CAR_ID IN (
                SELECT CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                WHERE START_DATE <= '2022-10-16' and '2022-10-16' <= END_DATE
            )
        THEN '대여중'
        ELSE '대여 가능'
    END
) AS AVAILABILITY FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY AS c
GROUP BY c.CAR_ID
ORDER BY c.CAR_ID DESC;
