-- 코드를 입력하세요
-- 2022년 1월 기준, 
SELECT j3.AUTHOR_ID, j3.AUTHOR_NAME, j3.CATEGORY, SUM(j3.PRICE * j3.SALES) AS SALES FROM 
(
    SELECT * FROM 
    (
        SELECT  
            j1.BOOK_ID, j1.CATEGORY, j1.AUTHOR_ID, j1.AUTHOR_NAME, 
            j1.PRICE, j1.PUBLISHED_DATE, bs.SALES_DATE, bs.SALES
        FROM 
        (
            SELECT 
                b.BOOK_ID, b.CATEGORY, b.AUTHOR_ID, a.AUTHOR_NAME, 
                b.PRICE, b.PUBLISHED_DATE
            FROM 
            BOOK AS b INNER JOIN
            AUTHOR AS a ON b.AUTHOR_ID = a.AUTHOR_ID
        ) AS j1
        INNER JOIN
        BOOK_SALES AS bs
        ON j1.BOOK_ID = bs.BOOK_ID
    ) AS j2
    WHERE DATE_FORMAT(j2.SALES_DATE, '%Y-%m') = '2022-01'
) AS j3
GROUP BY j3.AUTHOR_ID, j3.CATEGORY
ORDER BY j3.AUTHOR_ID, j3.CATEGORY DESC;



