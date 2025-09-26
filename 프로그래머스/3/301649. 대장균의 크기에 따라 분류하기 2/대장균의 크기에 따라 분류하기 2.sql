SELECT ID, 
    CASE 
        WHEN B.percent <= 0.25 THEN 'LOW'
        WHEN B.percent <= 0.5 THEN 'MEDIUM'
        WHEN B.percent <= 0.75 THEN 'HIGH'
        ELSE 'CRITICAL'
    END as 'COLONY_NAME'
        
FROM
    (
        SELECT ID, PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY) AS 'percent'
        FROM ECOLI_DATA
    ) B
ORDER BY ID;