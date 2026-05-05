-- 코드를 입력하세요
-- 입양을 못간 동물 중 
-- 가장 보호기간이 긴 동물 -> 가장 빨리 들어온 동물
-- 3 마리
SELECT ai.NAME, ai.DATETIME FROM 
    ANIMAL_INS AS ai
    LEFT JOIN
    ANIMAL_OUTS AS ao
    ON ai.ANIMAL_ID = ao.ANIMAL_ID
WHERE ao.ANIMAL_ID IS NULL -- 입양 못간 동물
ORDER BY ai.DATETIME
LIMIT 3;
