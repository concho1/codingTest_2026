-- 코드를 작성해주세요
-- 부모의 형질을 모두 보유한 대장균의 ID(ID)
-- 자식 : 0011, 부모 : 0111 이면 ? 자식 and 부모 = 부모
SELECT sub_ed.ID, sub_ed.GENOTYPE, (
        pr_ed.GENOTYPE
    ) AS PARENT_GENOTYPE
FROM 
ECOLI_DATA AS sub_ed
INNER JOIN
ECOLI_DATA AS pr_ed
ON 
    sub_ed.PARENT_ID = pr_ed.ID AND 
    ((sub_ed.GENOTYPE & pr_ed.GENOTYPE) = pr_ed.GENOTYPE)
ORDER BY sub_ed.ID;