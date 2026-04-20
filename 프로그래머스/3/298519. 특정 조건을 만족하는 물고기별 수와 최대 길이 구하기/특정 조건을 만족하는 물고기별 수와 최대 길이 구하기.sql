-- 코드를 작성해주세요
-- 1. 10cm이하나 NULL인 물고기들은 10cm로
-- 2. 종류별로 그룹화 및 max 길이
-- 3. 평균 길이가 33cm 이상
-- 4. 결과는 물고기 종류에 대해 오름차순
SELECT 
    COUNT(*) AS FISH_COUNT, 
    MAX(fi.LENGTH) AS MAX_LENGTH, 
    fi.FISH_TYPE AS FISH_TYPE
FROM (
    SELECT FISH_TYPE, 
    CASE
        WHEN LENGTH <= 10 THEN 10
        WHEN LENGTH IS NULL THEN 10
        ELSE LENGTH
    END AS LENGTH
    FROM FISH_INFO
) AS fi
GROUP BY fi.FISH_TYPE
HAVING AVG(fi.LENGTH) >= 33 
ORDER BY FISH_TYPE;