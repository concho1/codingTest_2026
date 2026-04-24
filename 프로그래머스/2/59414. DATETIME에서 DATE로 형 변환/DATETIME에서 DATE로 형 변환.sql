-- 코드를 입력하세요
-- 등록된 모든 레코드에 대해, 각 동물의 아이디와 이름, 들어온 날짜 조회
-- 시각(시-분-초)을 제외한 날짜(년-월-일)만 보여주세요
-- 아이디 순으로 조회해야 합니다.
-- dateformat
SELECT 
    ANIMAL_ID,
    NAME,
    DATE_FORMAT(DATETIME, '%Y-%m-%d') AS '날짜' 
FROM ANIMAL_INS;