-- 코드를 작성해주세요
SELECT emp.EMP_NO, emp.EMP_NAME, (
        CASE
            WHEN g.SCORE < 80 THEN 'C'
            WHEN g.SCORE < 90 THEN 'B'
            WHEN g.SCORE < 96 THEN 'A'
            ELSE 'S'
        END
    ) AS GRADE, (
        CASE
            WHEN g.SCORE < 80 THEN emp.SAL * 0
            WHEN g.SCORE < 90 THEN emp.SAL * 0.1
            WHEN g.SCORE < 96 THEN emp.SAL * 0.15
            ELSE emp.SAL * 0.2
        END
    ) AS BONUS 
FROM
    HR_EMPLOYEES AS emp
    JOIN (
        SELECT EMP_NO, (SUM(SCORE) / 2) AS 'SCORE' FROM HR_GRADE
        GROUP BY EMP_NO
    ) AS g
    ON emp.EMP_NO = g.EMP_NO
ORDER BY emp.EMP_NO;

/*
*/
