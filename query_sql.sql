SELECT tbl2.header, tbl1.title, tbl2.link, tbl2.salary FROM (
	SELECT a.t1 as title, CEIL((IFNULL(b.c2, 0) / a.t2) * 100) as coop, a.t2, a.t3
	FROM (
		SELECT title as t1, count(*) as t2, avg(salary) as t3
		FROM vacancy
		GROUP BY t1
	) a
	LEFT JOIN (
		SELECT title as c1, count(*) as c2
		FROM vacancy
		WHERE `text` like '%образование%'
		GROUP BY c1
	) b on a.t1 = b.c1
) as tbl1
INNER JOIN (
	SELECT title, header, link, salary
	FROM vacancy 
	WHERE 
		`text` not like "% php %" AND
		`text` not like "% регион %" AND
		`text` not like "% big data %" AND
		`text` not like "% большие данные %" AND
		`text` not like "% machine learning %" AND
		`text` not like "% ML %" AND
		`text` not like "% машинное обучение %" AND
		`text` not like "% lua %" AND
		`text` not like "% T-SQL %" AND
		`text` not like "% t-sql %" AND
		`text` not like "% c# %" AND
		`text` not like "%студент%" AND
		`text` not like "%выпускник%"
) as tbl2 ON tbl2.title = tbl1.title
WHERE
	tbl1.coop <= 50 AND
	
	tbl2.header not like "%lead%" AND
	tbl2.header not like "%senior%" AND
	tbl2.header not like "%devops%" AND
	tbl2.header not like "%DevOps%" AND
	tbl2.header not like "%ведущий%" AND
	tbl2.header not like "%руководитель%" AND
	tbl2.header not like "%Big Data%" AND
	tbl2.header like "%junior%" AND

	tbl2.title not like "%Skolkovo%" AND
	tbl2.title not like "%Ростелеком%" AND
	tbl2.title not like "%Яндекс%" AND
	tbl2.title not like "%Mail%" AND
	tbl2.title not like "%Eden Springs%" AND
	tbl2.title not like "%ЛАНИТ%" AND
	tbl2.title not like "%Luxoft%" AND
	tbl2.title not like "%НИИ%" AND
	tbl2.title not like "%ООО%" AND
	tbl2.title not like "%АО%" AND
	tbl2.title not like "%Группа компаний%" AND
	tbl2.title not like "%Агенство%" AND
	tbl2.title not like "%Сбербанк%"

ORDER BY tbl1.coop, tbl2.salary desc

;
