-- 코드를 입력하세요
-- 자동차 종류가 '세단'인 자동차들 중
-- 10월에 대여를 시작한 기록이 있는 자동차 ID
-- 자동차 ID를 기준으로 내림차순
SELECT c.CAR_ID AS CAR_ID FROM 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY AS h
    INNER JOIN 
    CAR_RENTAL_COMPANY_CAR AS c
    ON h.CAR_ID = c.CAR_ID
WHERE c.CAR_TYPE = '세단' AND MONTH(START_DATE) = 10
GROUP BY c.CAR_ID
ORDER BY CAR_ID DESC;