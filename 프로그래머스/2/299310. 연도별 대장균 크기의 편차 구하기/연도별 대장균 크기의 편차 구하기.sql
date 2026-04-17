-- 코드를 작성해주세요
-- 연도로 파티셔닝
-- 편차를 구하고
-- 연도에 대해 오름차순
-- 대장균 크기의 편차에 대해 오름차순 unsignd
SELECT YEAR(DIFFERENTIATION_DATE) AS YEAR, 
    (MAX(SIZE_OF_COLONY) OVER(
        PARTITION BY YEAR(DIFFERENTIATION_DATE)
        ) - SIZE_OF_COLONY) AS YEAR_DEV,
    ID
FROM ECOLI_DATA
ORDER BY YEAR, YEAR_DEV;