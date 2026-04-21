-- 코드를 작성해주세요
-- HR_EMPLOYEES <- HR_DEPARTMENT join
-- 부서별 그룹화
-- 평균연봉, 첫쨰 자리에서 반올림 ROUND
-- 평균연봉 내림차순
SELECT 
    dep.DEPT_ID AS DEPT_ID, 
    dep.DEPT_NAME_EN AS DEPT_NAME_EN, 
    ROUND(AVG(emp.SAL)) AS AVG_SAL
FROM 
    HR_EMPLOYEES AS emp
INNER JOIN
    HR_DEPARTMENT AS dep
ON emp.DEPT_ID = dep.DEPT_ID
GROUP BY dep.DEPT_ID
ORDER BY AVG_SAL DESC;