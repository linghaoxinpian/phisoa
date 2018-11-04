backend------------------------------------- 后台处理（待定）

organization/photo/机构id/uuid.jpg ----------- 机构的相册,目录以机构名命名,数据库存储的依旧是文件名，即使全部都是一样的(扩展性)
organization/logo/id.jpg--------------- 机构的logo，因为只有一张，所以不再细分文件夹

organization/lecturer/讲师id/时间.jpg------------------ 机构讲师的自拍照
organization/comments/机构id/uuid.jpg-----------------对机构的评论
organization/lecturer/讲师id/uuid.jpg------------------ 机构讲师的其它玩意照片

organization/course/课程id/时间.jpg---------课程封面图片
organization/course/课程id/uuid.jpg---------课程宣传图片
organization/course/课程id/uuid.jpg---------对课程的评论图片

user/photo/求学者id/时间.jpg-----------------求学者头像

organization/video/视频名+视频id/1.mp3----------公开课视频

user/photo/用户id/时间命名(201810251023).jpg --------------------------------- 求学者的头像用户id