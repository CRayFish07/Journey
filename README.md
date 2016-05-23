
#接口文档


## 一些约定
1. 所有接口全部参数都有时间戳，时间戳参数名为r

2. 所有接口统一后面加校验参数：?verify=XXXX（现在在初期开发阶段，先不加这个参数）  
	这个值根据一个算法从url计算出来（不包括时间戳）  
	eg:	url：user/register?r=1459155712357  
		就根据“user/register”和提供的时间戳和密钥计算出一个值然后添加到url后面，POST时不用管实体中的数据
	
	（上面两个参数加在最后面，先r后verify）

3. 密码使用md5加密后存储

## (一)登陆注册
### 1. 获取验证码：getcaptcha  
返回的是图片。返回消息其中有Set-Cookie信息，其中有JSESSIONID=CC4ECD7EBFED1F96307E5AE61D1840BB;  
将其取出，放在cookie中，在接下来的一次请求中（注册）使用以验证验证码是否正确（重新请求验证码时一直带着这个JSESSIONID）

### 2. 普通用户注册（POST）user/register  

post数据：  

	 - phone=XXXX
	 - passwd=XXXX
	 - nickname=XXXX
	 - captcha=XXXX（验证码）

返回：
```		
		{
			"errcode":"XX",		//状态码  
			"msg":"XX"			//解释errcode
		}
```

errcode： 
 
	 - 0-正常  
	 - 1-手机已经注册过  
	 - 2-验证码错误  
	 - 3-参数错误  


### 3. 用户登陆（POST）（先使用这两种方式登陆）
 - 手机登陆：	user/login  
	post数据：  
	 - phone=XXXX
	 - passwd=XXXX

 - 用户名登陆：	user/login  
	post数据：
	 - username=XXXX
	 - passwd=XXXX

	返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）",
			"user":{
				"userId":"XX（用户id，唯一）",
				"username":"XX",
  				"nickname":"XX（昵称）",
  				"authority":"XX（账号分类，普通用户为0，暂时只有0）",
  				"registTime":"XX（13位时间戳表示注册时间）",
  				"score":"XX（积分）",
  				"level":"XX（账号等级）",
  				"sex":"XX（性别m/f/s 分别代表男/女/保密）",
  				"birthday":"XX（生日）",
  				"phone":"XX（手机号）",
  				"email":"XX（邮箱地址）",
  				"image":"XX（头像地址）"
			}
		}
```

	errcode：	
	 - 0-正常
	 - 1-账号或密码错误
	 - 2-参数错误


## (二)关注/取消关注
### 1. 关注user/follow?userid=XXXX&other=XXXX  
userid是当前用户id，other是被关注用户id

	返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
		}
```

	errcode：
	 - 0-正常
	 - 1-错误
### 2. 取消关注user/deletefollow?userid=XXXX&other=XXXX  
userid是当前用户id，other是被关注用户id

	返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
		}
```

	errcode：
	 - 0-正常
	 - 1-错误


## (三)摇一摇:
 - 方法:get
 - 地址:/user/shake
 - 参数:
	 - userid
	 - spoint	起点
	 - epoint	终点
	 - stime		起始日期
	 - etime	截止日期

	参数解释:时间格式最好是2016-5-17这种，起点终点就是城市名字

 - 返回:
	 - 没有匹配时:
```	{
		errcode: "1",
		msg: "没有用户计划匹配"
	}
```	
	 - 有匹配时:
```	{
		plans: [
			{
				spoint: "西安",
				stime: "2016-5-1",
				etime: "2016-5-2",
				epoint: "北京",
				userid: "1",
				addTime: 1463475972985
			},
			{
				spoint: "西安",
				stime: "2016-5-1",
				etime: "2016-5-2",
				epoint: "北京",
				userid: "2",
				addTime: 1463476042268 /*表示这条记录什么时间到达后台，也就是用户什么时候摇的*/
			}
		]
	}
```

## (四)游记：
### 0.１ 获得“人物”枚举类型: /note/getHumanType
返回：
```
{"humanTypes":[{"ddlName":"一个人","ddlCode":1},{"ddlName":"小两口","ddlCode":2},{"ddlName":"带孩子","ddlCode":3},{"ddlName":"家族出游","ddlCode":4},{"ddlName":"和朋友","ddlCode":5},{"ddlName":"其他","ddlCode":5}]}
```

### 0.2 上传图片：/file/uploadImg
参数：

		 - * img(type=file)
		 - * userId(用户Id)

返回：
```		{
			"errcode":"XX",
			"msg":"XX"	//解释errcode
			"url":"http://XX/XXXX.×××"	//显示该图片的url
			"path":"http://XX/XXXX.×××"	//保存游记时，往数据库中存的对应的图片的path
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败

### 0.3 上传背景音乐：/file/uploadMusic
参数：

		 - * music(type=file)
		 - * userId(用户Id)

返回：
```		{
			"errcode":"XX",
			"msg":"XX"	//解释errcode
			"url":"http://XX/XXXX.×××"	//显示该图片的url
			"path":"http://XX/XXXX.×××"	//保存游记时，往数据库中存的相对路径
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败


### 0.4 删除游记文件(图片/背景音乐)：/file/deleteNoteFile

post数据：

	 - * fileId		//文件Id
返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败


### 0.5 批量删除游记文件(图片/背景音乐)：/file/deleteNoteFiles

post数据：

	 - * fileIds		//文件Ids
返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败



### 1. 添加游记：/note/addJourneyNote

post数据：  (带'*'为必选数据)　　

	 - * title			//文章标题  
	 - * userId			//作者  
	 - * content			//正文
	 - departureDate		//出发日期
	 - travelDays		//出行天数
	 - human				//人物
	 - fee				//人均费用

		 
返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败


### 2. 查询游记：/note/getJourneyNote
post数据：  (带'*'为必选数据)　　

	 - * userId			//作者Id  
	 - pageNo			//页号（默认第1页）
	 - pageSize			//每页大小（默认10）

返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
			"userId":"×××"			//作者Id  
			"pageNo":1			//页号（默认第1页）
			"pageSize":10			//每页大小（默认10）
			"totalRows":234     //游记总篇数
			"journeyNotes":[{"content": "asdf",
"title": "asdf",
"updateTime": "Sun May 22 21:14:11 CST 2016",
"userId": 1,
"noteFiles": [],
"noteId": "b3967b3b-191d-4178-9734-2100d366a14b",
"human": 0},{"content": "asdf",
"title": "asdf",
"updateTime": "Sun May 22 21:14:11 CST 2016",
"userId": 1,
"noteFiles": [],
"noteId": "b3967b3b-191d-4178-9734-21sadf6a14b",
"human": 0}]
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败


### 3. 修改游记：/note/updateJourneyNote

post数据：

	 - * noteId		//游记Id
	 - title			//文章标题  
	 - content			//正文
	 - departureDate		//出发日期
	 - travelDays		//出行天数
	 - human				//人物
	 - fee				//人均费用
		 
返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败


### 4. 删除游记：/note/deleteJourneyNote

post数据：

	 - * noteId		//游记Id

返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败


### 5. 批量删除游记：/note/deleteJourneyNotes

post数据：

	 - * noteIds	=["b3967b3b-191d-4178-9734-2100d366a14b","b3967b3b-191d-4178-9734-2100d366a14b"]	//游记Ids

返回：
```		{
			"errcode":"XX",
			"msg":"XX（解释errcode）"
		}
```

errcode：　　

	 - 0-成功	
	 - 1-失败




