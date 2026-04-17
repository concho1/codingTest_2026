-- 코드를 입력하세요
-- 1. 진료과 코드(MCDP_CD)로 그룹화
-- 2. 예약한 환자 수 select
-- 3. 컬럼명은 '진료과 코드', '5월예약건수'
-- 4. 예약한 환자 수 오름차, 예약한 환자 수 오름차
-- where 2022년 5월에 예약 and APNT_CNCL_YN 취소 N
SELECT MCDP_CD AS '진료과코드', COUNT(APNT_NO) AS '5월예약건수' FROM APPOINTMENT 
WHERE DATE_FORMAT(APNT_YMD, '%Y-%m') = '2022-05'
GROUP BY MCDP_CD
ORDER BY COUNT(APNT_NO), MCDP_CD;