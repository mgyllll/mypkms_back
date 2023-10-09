use myPKMS;
#######################################################################################################################
SELECT
	a.*,
	b.CategoryID AS categoryID1,
	b.cayegoryName AS cayegoryName1,
	b.parentNodeID AS parentNodeID1,
	b.IsParentFlag AS IsParentFlag1,
	b.level AS level1,
	b.Ancester AS Ancester1
FROM
	t_categories a,
	t_categories b,
	t_users_categories c
WHERE
	a.CategoryID=b.ParentNodeID
AND
	a.CategoryID=c.CategoryID
AND 
	UserID=1025;
#######################################################################################################################
SELECT
DISTINCT
	b.*,
	d.roletype as rtype,
	d.rolename as rname
FROM
	t_user a,
	t_categories b,
	t_users_categories c,
	t_role d
WHERE
	a.userID = c.userID
AND
	b.categoryid = c.categoryid
AND
	a.roletype = d.roletype
ORDER BY
	b.categoryid;
#######################################################################################################################
SELECT 
	a.roletype,
	a.rolename
FROM
	t_role as a
LEFT JOIN t_user as b on a.roletype = b.roletype
where b.userid = 1024;
#######################################################################################################################
SELECT
	a.*,
	b.AreaID AS AreaID2,
	b.AreaName AS AreaName2,
	b.parentNodeID AS parentNodeID2,
	b.IsParentFlag AS IsParentFlag2,
	b.level AS level2,
	b.Ancester AS Ancester2
FROM
	t_areas a,
	t_areas b
WHERE
	a.areaid=b.ParentNodeID;
 #######################################################################################################################   
 
SELECT 
	* 
FROM 
	t_resources 
WHERE 
	UserID=1027 
AND 
	CategoryID like concat('30401','%'); 
#######################################################################################################################
SELECT
	*
FROM
	t_user a
LEFT JOIN t_role b
ON
	a.roletype = b.roletype
WHERE
	a.userid<>1024
AND
	a.username LIKE CONCAT('%','','%')
ORDER BY a.UserID;
#######################################################################################################################

SElECT 
	* 
FROM 
	t_categories 
WHERE 
	categoryid 
IN (SELECT categoryid from t_users_categories WHERE userID=1024);
#######################################################################################################################   
SElECT
	*
FROM
	t_categories
WHERE
	categoryid IN (SELECT categoryid from t_users_categories WHERE userID=1025)
ORDER BY  ancester, cast(categoryID as SIGNED);
#######################################################################################################################
SELECT 
	a.*,
    b.suffix
FROM 
	t_files a, 
    t_types b 
WHERE  
	a.TypeID = b.TypeID;
#######################################################################################################################
SELECT 
	b.username,
    b.gender,
    b.photo as avatar,
	a.*
FROM
t_resources a
LEFT JOIN 
t_user b
ON 
a.userID =b.userID
WHERE 
a.isShared = 'true'
AND
concat(b.username, '#', a.titile, '#', a.tag, '#', a.description) like concat('%', '宝宝', '%');
#######################################################################################################################
SELECT 
	*
FROM
	t_user
WHERE
concat(userid, '#', username, '#', birthdate, '#', titile, '#', gender, '#',  phone, '#', email, '#', qq, '#', wechat, '#',  address) like concat('%', '', '%');
#######################################################################################################################
SELECT
a.UserID,
a.UserName,
a.Password,
a.BirthDate,
a.Age,
a.Gender,
a.Phone,
a.Email,
a.QQ,
a.WeChat,
a.Address,
a.City,
a.Zip,
a.RoleType,
a.Photo,
a.Notes,
b.RoleType as roleType,
b.RoleName as roleName
FROM
t_user a
LEFT JOIN t_role b
ON
a.roletype = b.roletype;

SELECT
	a.*,
    b.UserID as UserID1,
    b.UserName as AuditorName,
    b.Roletype,
    c.Roletype as roleType,
    c.roleName
FROM
	t_resources a
LEFT JOIN 
	t_user b
ON
	a.Auditor = b.UserID
LEFT JOIN
	t_role c
ON 
	c.roletype = b.roletype
WHERE
	a.UserID=1027
AND
	a.Audited = 1;
    

select 
	'0' as text1, 
	'育儿知识类别' as text2, 
	concat('知识总数：', (select count(*) from t_resources where userID = 1027)) as text3, 
	concat('父节点：', '') as text4, 
	1 as zb, 
	'-1' as ParentNodeID
union all
select 
	a.categoryid as text1,
    a.CayegoryName as text2,
    concat('知识总数：', (select count(*) from t_resources where CategoryID like concat(a.categoryid, '') and userID = 1027)) as text3,
    concat('父节点：', a.ParentNodeID) as text4,
    a.IsParentFlag as zb,
    a.ParentNodeID
from t_categories a where a.categoryID in (select categoryID from t_users_categories where userID = 1027);


select * from t_resources;
select * from t_categories;









