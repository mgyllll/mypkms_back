use myPKMS;

DROP PROCEDURE IF EXISTS p_addCategory;
DELIMITER $$
CREATE PROCEDURE p_addCategory(
	in $pid varchar(50),
	in $cname varchar(50),
    in $userid int,
    out $result int,
    out $r2 varchar(50)
)
begin
	declare cid nvarchar(50);
    declare ancester nvarchar(100) default '';
    declare lev int;
    IF NOT EXISTS (SELECT * FROM t_categories WHERE parentnodeid=$pid) THEN
		set cid = concat($pid,'01');
        UPDATE t_categories SET isParentFlag=1 WHERE CategoryID=$pid;
	ELSE
		select convert(max(cast(categoryID as SIGNED))+1, char) into cid from t_categories where parentnodeid=$pid;
	END IF;
    IF $pid=0 then
        set lev=1;
        set ancester='';
	ELSE
		select  level+1 into lev from t_categories where categoryid=$pid;
        select concat(ancester, cid, '#') into ancester from t_categories where categoryid=$pid;
	END IF;
    INSERT INTO t_categories (CategoryID, CayegoryName, ParentNodeID, IsParentFlag, Level, Ancester)
    VALUES (cid, $cname, $pid, 0, lev, ancester);
    set $result=1;
    INSERT INTO t_users_categories (UserID, CategoryID)
    VALUES ($userid, cid);
    set $r2=cid;
end $$
DELIMITER ;
#CALL p_addCategory('4','新生儿护理', 1025, @flag, @categoryid);

#select * from t_categories where categoryid like concat('5', '%');
#select * from t_users_categories where userID=1027;
#UPDATE t_categories SET isParentFlag=1 WHERE CategoryID='4';

DROP PROCEDURE IF EXISTS p_deleteCategory;
DELIMITER $$
CREATE PROCEDURE p_deleteCategory(
	in $id varchar(50),
	in $userid int,
	out $result int
)
begin
	declare pid varchar(30);
    Set $result=0;
    IF NOT EXISTS (select * from t_categories where categoryid=$id) THEN
		Set $result=2;
	ELSE
		select parentnodeid into pid from t_categories where categoryid=$id;
		SET SQL_SAFE_UPDATES=0;
		delete from t_files where ResourceID in (select resourceid from t_resources where categoryid like concat($id,'%'));
		delete from t_resources where categoryid like concat($id,'%');
		delete from t_users_categories where userID=$userid and CategoryID like concat($id, '%');
		IF NOT EXISTS (SELECT * FROM t_users_categories WHERE CategoryID like concat($id, '%')) THEN
			SET SQL_SAFE_UPDATES=0;
			delete from t_categories where CategoryID like concat($id, '%');
			IF NOT EXISTS (SELECT * FROM t_categories WHERE ParentNodeID=pid) THEN
				UPDATE t_categories SET isParentFlag=0 WHERE CategoryID=pid;
			END IF;
		END IF;
		Set $result=1;
	END IF;
end $$
DELIMITER ;
#CALL p_deleteCategory('12', 1027, @result);

SET GLOBAL log_bin_trust_function_creators = 1; #一次MySQL会话连接只需要设置一次。
drop function if exists f_deleteCategory;
delimiter $$
create function f_deleteCategory(
	$id varchar(50),
	$userid int
) returns int DETERMINISTIC
begin
	declare pid varchar(30);
    declare $result int default 0;
    IF NOT EXISTS (select * from t_categories where categoryid=$id) THEN
		Set $result=2;
	ELSE
		select parentnodeid into pid from t_categories where categoryid=$id;
		SET SQL_SAFE_UPDATES=0;
		delete from t_files where ResourceID in (select resourceid from t_resources where categoryid like concat($id,'%'));
		delete from t_resources where categoryid like concat($id,'%');
		delete from t_users_categories where userID=$userid and CategoryID like concat($id, '%');
		IF NOT EXISTS (SELECT * FROM t_users_categories WHERE CategoryID like concat($id, '%')) THEN
			SET SQL_SAFE_UPDATES=0;
			delete from t_categories where CategoryID like concat($id, '%');
			IF NOT EXISTS (SELECT * FROM t_categories WHERE ParentNodeID=pid) THEN
				UPDATE t_categories SET isParentFlag=0 WHERE CategoryID=pid;
			END IF;
		END IF;
		set $result=1;
        return $result;
	END IF;
end$$
delimiter ;

#select * from t_categories;
#select * from t_users_categories where userid=1027;


DROP PROCEDURE IF EXISTS p_addRes;
DELIMITER $$
CREATE PROCEDURE p_addRes(
	in $Titile varchar(40) ,
    in $CategoryID varchar(20) ,
    in $UserID int ,
	in $Tag nvarchar(100) ,
    in $Description text,
	in $isShared nvarchar(20),
    in $photo varchar(255),
	out $result int,
    out $ResourceID bigint
)
begin
	declare pid varchar(30);
	INSERT INTO t_resources (Titile, CategoryID, UserID, Tag, CreateDate, LastModifyDate, Description, isShared, photo)
	VALUES
	($Titile, $CategoryID, $UserID, $Tag, current_date(), current_date(), $Description, $isShared, $photo);
	Set $result=1;
    select max(resourceid) into $ResourceID from t_resources;
end $$
DELIMITER ;

#call p_addRes('3333333333', '30402', 1027, '1;2;3;4',  '12344555555', 'true', '', @result, @rid );
#select * from t_resources;
#select max(resourceid) from t_resources;


DROP PROCEDURE IF EXISTS p_updateRes;
DELIMITER $$
CREATE PROCEDURE p_updateRes(
	in $ResourceID bigint,
	in $Titile varchar(40) ,
    in $CategoryID varchar(20) ,
	in $Tag nvarchar(100) ,
    in $Description text,
	in $isShared nvarchar(20),
    in $photo varchar(255),
	out $result int
)
begin
	declare pid varchar(30);
    UPDATE t_resources set 
		titile = $Titile, 
		categoryID = $CategoryID, 
		Tag = $Tag, 
		lastModifyDate = current_date(), 
		Description = $Description, 
		isShared = $isShared,
        photo = $photo
    where resourceid = $resourceid;
	Set $result=1;
end $$
DELIMITER ;

#call p_updateRes(2017333504058, '上海理工大学2.0', '30402', '3;4',  '发的感受到费工费时', 'true', 'sssss', @result );
#select * from t_resources order by Resourceid desc;


DROP PROCEDURE IF EXISTS p_addFile;
DELIMITER $$
CREATE PROCEDURE p_addFile(
	in $FileName nvarchar(40),
	in $ResourceID bigint ,
	in $FileSize bigint ,
    in $FilePath nvarchar(100),
	out $result int
)
begin
	declare $typeid int;
    IF NOT EXISTS (select * from t_types where suffix=lower(substring_index($FilePath, '.', -1))) THEN
		set $result=-1;
	ELSE 
		select typeid into $typeid from t_types where suffix=lower(substring_index($FilePath, '.', -1));
        INSERT INTO t_files (FileName, ResourceID, TypeID, FileSize, FilePath, UploadTime, Description)
		VALUES($FileName, $ResourceID, $typeid, $FileSize, $FilePath, current_date(), '');
		Set $result=1;
	END IF;

end $$
DELIMITER ;
#delete from t_files where fileid=12 ;
#call p_addFile('宝宝发烧1223', 2017333504045,  15254, '/pkms_file/1027/2017333504045/hhhh.PNG', @r);
#call p_addFile('宝宝发烧2', 2017333504045, 15254, '/pkms_file/1027/2017333504045/hhhh.exe', @r);
#select @r;
#select * from t_files;
#select * from t_types;

DROP PROCEDURE IF EXISTS p_addUser;
DELIMITER $$
CREATE PROCEDURE p_addUser(
	in $username nvarchar(255),
	in $password nvarchar(255),
	in $birthdate nvarchar(255),
	in $gender nvarchar(10),
	in $phone nvarchar(255),
	in $email nvarchar(255),
	in $qq nvarchar(255),
	in $wechat nvarchar(255),
	in $address nvarchar(255),
	in $city nvarchar(255),
    in $roletype int,
	out $result int,
    out $userID int
)
begin
	declare $c1 nvarchar(255);
	declare $c2 nvarchar(255);
	declare $c3 nvarchar(255);
	declare $c4 nvarchar(255);
	declare $c5 nvarchar(255);
	declare $c6 nvarchar(255);
	INSERT INTO t_user (username, password, birthdate, gender, phone, email, qq, wechat, address, city, zip, roletype, photo, notes)
    VALUES ($username, $password, $birthdate, $gender, $phone, $email, $qq, $wechat, $address, $city, '',$roletype, 'default.jpg', '');
    SET $result = 1;
    SELECT MAX(userid) INTO $userID FROM t_user;
    CALL p_addCategory('0','备孕', $userID, @flag, @categoryid);
    set $c1 = @categoryid;
		CALL p_addCategory($c1,'备孕疾病', $userID, @flag, @categoryid);
		CALL p_addCategory($c1,'备孕技巧', $userID, @flag, @categoryid);
		CALL p_addCategory($c1,'备孕饮食', $userID, @flag, @categoryid);
		CALL p_addCategory($c1,'难孕难育', $userID, @flag, @categoryid);
		CALL p_addCategory($c1,'二胎高龄', $userID, @flag, @categoryid);
    CALL p_addCategory('0','孕期', $userID, @flag, @categoryid);
    set $c2 = @categoryid;
		CALL p_addCategory($c2,'胎儿发育', $userID, @flag, @categoryid);
		CALL p_addCategory($c2,'孕期疾病', $userID, @flag, @categoryid);
		CALL p_addCategory($c2,'孕期饮食', $userID, @flag, @categoryid);
		CALL p_addCategory($c2,'孕期护理', $userID, @flag, @categoryid);
		CALL p_addCategory($c2,'孕期产检', $userID, @flag, @categoryid);
		CALL p_addCategory($c2,'临时分娩', $userID, @flag, @categoryid);
		CALL p_addCategory($c2,'孕期安全', $userID, @flag, @categoryid);
		CALL p_addCategory($c2,'胎教时光', $userID, @flag, @categoryid);
		CALL p_addCategory($c2,'妈妈成长', $userID, @flag, @categoryid);
    CALL p_addCategory('0','0-1月', $userID, @flag, @categoryid);
    set $c3 = @categoryid;
		CALL p_addCategory($c3,'坐月子', $userID, @flag, @categoryid);
		CALL p_addCategory($c3,'新生儿护理', $userID, @flag, @categoryid);
		CALL p_addCategory($c3,'新生儿喂养', $userID, @flag, @categoryid);
		CALL p_addCategory($c3,'新生儿疾病', $userID, @flag, @categoryid);
		CALL p_addCategory($c3,'宝宝早教', $userID, @flag, @categoryid);
    CALL p_addCategory('0','1-12月', $userID, @flag, @categoryid);
    set $c4 = @categoryid;
		CALL p_addCategory($c4,'宝宝发育', $userID, @flag, @categoryid);
		CALL p_addCategory($c4,'宝宝喂养', $userID, @flag, @categoryid);
		CALL p_addCategory($c4,'宝宝护理', $userID, @flag, @categoryid);
		CALL p_addCategory($c4,'宝宝疾病', $userID, @flag, @categoryid);
		CALL p_addCategory($c4,'宝宝安全', $userID, @flag, @categoryid);
		CALL p_addCategory($c4,'宝宝早教', $userID, @flag, @categoryid);
		CALL p_addCategory($c4,'早产儿', $userID, @flag, @categoryid);
		CALL p_addCategory($c4,'妈妈成长', $userID, @flag, @categoryid);
    CALL p_addCategory('0','1-3岁', $userID, @flag, @categoryid);
    set $c5 = @categoryid;
		CALL p_addCategory($c5,'宝宝早教', $userID, @flag, @categoryid);
		CALL p_addCategory($c5,'宝宝饮食', $userID, @flag, @categoryid);
		CALL p_addCategory($c5,'宝宝疾病', $userID, @flag, @categoryid);
		CALL p_addCategory($c5,'宝宝安全', $userID, @flag, @categoryid);
		CALL p_addCategory($c5,'宝宝护理', $userID, @flag, @categoryid);
		CALL p_addCategory($c5,'妈妈成长', $userID, @flag, @categoryid);
    CALL p_addCategory('0','3-6岁', $userID, @flag, @categoryid);
    set $c6 = @categoryid;
		CALL p_addCategory($c6,'宝宝早教', $userID, @flag, @categoryid);
		CALL p_addCategory($c6,'宝宝饮食', $userID, @flag, @categoryid);
		CALL p_addCategory($c6,'宝宝疾病', $userID, @flag, @categoryid);
		CALL p_addCategory($c6,'宝宝安全', $userID, @flag, @categoryid);
		CALL p_addCategory($c6,'宝宝护理', $userID, @flag, @categoryid);
		CALL p_addCategory($c6,'妈妈成长', $userID, @flag, @categoryid);
end $$
DELIMITER ;

#CALL p_addUser('张三', '$2a$10$oFVZHf17KeypgLYKNg8E0.gUQZy8nFCJY.EPfIsEvsS18.wnmwxWK',
 #'2017-01-17', '男', '13777887554', '123456@163.com', '1121581284', '','浙江省杭州市江干区下沙高教园区浙江理工大学', '杭州市', 0, @r, @uid);

#select * from t_user;
 
DROP PROCEDURE IF EXISTS p_deleteUser;
DELIMITER $$
CREATE PROCEDURE p_deleteUser(
	in $userid int,
    out $result int
)
begin
	declare $cid nvarchar(255);
	declare $flag boolean default 1;
    DECLARE cur1 CURSOR FOR select categoryid from t_users_categories where userid =  $userid order by categoryid desc;
    declare continue handler for not found set $flag=0;
	SET SQL_SAFE_UPDATES=0;
	delete from t_files where resourceID in (select distinct resourceid from t_resources where userID = $userid);
    SET SQL_SAFE_UPDATES=0;
	delete from t_resources where userID = $userid;
	open cur1;
	fetch cur1 into $cid;
    WHILE ($flag=1) DO
		call p_deleteCategory($cid,  $userid, @r);
		fetch cur1 into $cid;
	END WHILE;
	close cur1;
    delete from t_user where userid = $userid;
    set $result = 1;
end $$
DELIMITER ;

#call p_deleteUser(1067, @r)

DROP PROCEDURE IF EXISTS p_updateUser;
DELIMITER $$
CREATE PROCEDURE p_updateUser(
	in $UserID int,
	in $UserName varchar(255),
    in $Password varchar(100),
	in $BirthDate date,
	in $Gender nvarchar(10),
    in $Phone varchar(30),
    in $Email varchar(30),
    in $qq varchar(30),
    in $WeChat varchar(30),
	in $Address varchar(100),
	in $City varchar(25),
	in $RoleType int,
	in $Photo varchar(100),
    out $result int
)
begin
	IF ($Photo = '') THEN 
		SET $Photo = 'default.jpg';
	END IF;
    IF ($Password = '') THEN 
		 select password into $Password from t_user where userid = $UserID;
	END IF;
	SET SQL_SAFE_UPDATES=0;
    UPDATE t_user SET 
		UserName = $UserName,
		Password = $Password,
		BirthDate = $BirthDate,
		Gender = $Gender,
		Phone = $Phone,
		Email = $Email,
		qq = $qq,
		WeChat = $WeChat,
		Address = $Address,
		City = $City,
        RoleType = $RoleType,
		Photo = $Photo
        WHERE UserID = $UserID;
	SET $result = 1;
end $$
DELIMITER ;
-- CALL p_updateUser('1077', '刘惜君1', '$2a$10$7TA2/H9xefLNyAxNIPQyhuiu5qjBoamGQ4VFTv9objwtz9Pd/hmPq', '2000-04-23', '女',
--  '13777887554', '', '', '', '山西省长治市平顺县地对地导弹', '平顺县', '', 0, 'default.jpg', @result);

select * from t_user;


DROP PROCEDURE IF EXISTS p_auditRes;
DELIMITER $$
CREATE PROCEDURE p_auditRes(
	in $ResourceID bigint,
	in $Audited int,
    in $Auditor int,
    in $AuditOpinion nvarchar(100),
    out $result int
)
begin
	DECLARE $nowDate date default current_date();
	IF ($Audited = 0) THEN 
		SET $nowDate = null;
        SET $Auditor = null;
        SET $AuditOpinion  = '';
	END IF;
	SET SQL_SAFE_UPDATES=0;
	UPDATE 
		t_resources 
    SET 
		Audited = $Audited, 
        Auditor = $Auditor, 
        AuditorName= (SELECT UserName FROM t_user WHERE UserID = $Auditor), 
        AuditTime = $nowDate, 
        AuditOpinion = $AuditOpinion 
	WHERE 
		resourceid = $ResourceID;
	SET $result = 1;
end $$
DELIMITER ;

#CALL p_auditRes(2017333504025, 0, null, '', @r);

#select * from t_resources order by resourceid;


DROP PROCEDURE IF EXISTS p_addComment;
DELIMITER $$
CREATE PROCEDURE p_addComment(
	in $UserID int,
	in $ResourceID bigint,
	in $CommentContent text,
    out $result int
)
begin
	INSERT INTO t_comments (
	ReviewerID,
    ReviewerName,
    photo,
	ResourceID ,
    CommentLength,
    CommentContent,
    CreatedDate,
	ParentCommentID)
	VALUES (
    $UserID, 
    (select userName from t_user where userID = $UserID), 
    (select photo from t_user where userID = $UserID), 
    $ResourceID, 
    length($CommentContent), 
    $CommentContent, 
    current_date(), 
    0);
    set $result = 1;
end $$
DELIMITER ;

DROP PROCEDURE IF EXISTS p_likeComment;
DELIMITER $$
CREATE PROCEDURE p_likeComment(
	in $LikerID int,
    in $CommentID bigint
)
begin
	IF NOT EXISTS (SELECT * FROM t_likeComments WHERE CommentID =$CommentID and LikerID = $LikerID) THEN
		INSERT INTO t_likeComments
		VALUES ($CommentID, $LikerID, current_date());
	ELSE
		DELETE FROM t_likeComments WHERE CommentID =$CommentID and LikerID = $LikerID;
    END IF;
end $$
DELIMITER ;

DROP PROCEDURE IF EXISTS p_likeRes;
DELIMITER $$
CREATE PROCEDURE p_likeRes(
	in $LikerID int,
    in $ResourceID bigint
)
begin
	IF NOT EXISTS (SELECT * FROM t_likeRess WHERE ResourceID =$ResourceID and LikerID = $LikerID) THEN
		INSERT INTO t_likeRess
		VALUES ($ResourceID, $LikerID, current_date());
	ELSE
		DELETE FROM t_likeRess WHERE ResourceID =$ResourceID and LikerID = $LikerID;
    END IF;
end $$
DELIMITER ;

DROP PROCEDURE IF EXISTS p_readingQuantity;
DELIMITER $$
CREATE PROCEDURE p_readingQuantity(
    in $ResourceID bigint
)
begin
	SET SQL_SAFE_UPDATES=0;
	UPDATE 
		t_resources 
    SET 
		ReadingQuantity = ReadingQuantity + 1
	WHERE 
		resourceid = $ResourceID;
end $$
DELIMITER ;




