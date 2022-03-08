# HarmonyOS-Curriculum-Design
鸿蒙开发课程设计：基于JSoup的鸿蒙教务查询软件。

# 一、内容介绍
本次大作业主要使用JSoup和鸿蒙开发来实现一个教务查询软件。软件分为两个部分：教务查询和个人中心。用户只需要知道自己的学号、教务系统密码以及内网VPN密码，无论是否使用校园网，都能登录该应用。教务查询部分全部用JSoup实现并展示在鸿蒙手机应用中，该部分包括成绩查询、考试查询、GPA查询以及成绩总表查询四个功能。个人中心部分包含用户的个人信息以及退出登录等操作。

# 二、工具介绍

JSoup是一款Java的HTML解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。本次大作业利用JSoup来模拟登录进而爬取教务系统内的各种信息，获取到的成绩等信息将在鸿蒙手机应用上进行展示。

# 三、JSoup处理细节
## 1.需求分析
场景：需要访问教务系统，爬取出各种个人教务信息，并在自己所写的手机应用上进行展示。由于访问教务系统需要连接校园网，为了方便用户在校外也能使用本应用，所以本次爬取采用了&ldquo;内网-教务系统&rdquo;的两级爬取策略，即先模拟登录校园内网，然后携带内网cookies登录教务系统，最终爬取出相关信息。

## 2.模拟登录内网
内网登录界面如图1所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/6122a9c9ca5f4ceab977c04e19eff058.png#pic_center)

主要登录步骤如下：
1. 在登录界面输入用户名以及登录密码，按下F12，并在Elements中搜索action，查找表单数据最终被提交到哪里，如图2所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/0d15bcc8be4f4f80ad38c7892d2365f9.png#pic_center)

可以看到，输入的表单数据最终被提交到了"/users/sign_in"里。
2. 点击登录，在Network里面找到sign_in，可以看到模拟登录需要的各种信息，如图3所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/8160af419ef4498fb2c84c2e76974d83.png#pic_center)

3. 写代码模拟登录。

## 3.模拟登录教务系统
模拟登录进入到了内网界面后，点击教务系统，进入到教务系统的登录界面，利用同样的方式获取模拟登录所需的信息，登录到教务系统中。

# 四、界面
## 1.登录界面
登录界面如图4所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/b58d5f541cc04cce81cce56407ffd217.png#pic_center)

用户输入自己的学号、教务系统密码以及内网VPN密码后，点击Login按钮，即可成功登录。登录后，鸿蒙开发提供的工具包Preferences将自动保存用户的所有登录信息，信息存储在用户的SD卡中。当再次打开APP时，系统将自动登录。

## 2.查询主界面
查询主界面如图5所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/fef2646b33694b2f9abce4fec2880fd3.png#pic_center)

在查询主界面，利用JSoup爬取到的网页信息设计了多个查询功能，下面将依次介绍。

### 2.1 成绩查询
点击查询主界面的&ldquo;成绩查询&rdquo;按钮，进入到成绩查询界面，点击学年、学期以及查询性质三个按钮，会弹出相应选项，如图6所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/b02d3dd7711549ab829adc313aac0b7e.png#pic_center)

学年、学期以及课程性质都选择完毕后，点击查询按钮，结果如图7所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/85d98345b472442c978f950f4728022a.png#pic_center)

成绩信息中只包含课程名和成绩分数两项信息。点击其中任意一条成绩信息，随后会弹出该门课程更加详细的信息，如图8所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/624f13902e42489eb143dc4a5126b652.png#pic_center)

详细信息中包括课程性质、考试性质以及开课学院等信息。

### 2.2 考试安排
点击查询主界面的&ldquo;考试安排&rdquo;按钮，进入到考试安排查询界面，选择学年、学期选项，然后点击查询，结果如图9所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/7801983eb2f84a82b7ecae15982ec1d2.png#pic_center)

考试信息中只包括考试科目以及考试地点两项信息。任意点击其中一条信息，会弹出更加详细的考试信息，如图10所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/d515c519ae8e47a6b9635db8ffc1ab4e.png#pic_center)

详细信息中包括考试时间、教学班组成以及考试校区等信息。

### 2.3 GPA查询
点击查询主界面的&ldquo;GPA&rdquo;按钮，进入到GPA查询界面。选择学年、学期以及课程性质三个选项，然后点击查询，结果如图11所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/5237aabdde9743ad8d222e8e53e91748.png#pic_center)

可以看出该名学生2019-2020学年第2学期的GPA是4.37。
### 2.4 成绩总表
点击查询主界面的&ldquo;成绩总表&rdquo;按钮，进入到成绩总表查询界面，结果如图12所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/568cc708924c4105b673e08029475777.png#pic_center)

成绩总表中包含该用户截止目前的所有学分以及课程的详细情况。

## 3.个人中心界面
个人中心界面显示了用户的个人信息，所有信息均通过JSoup爬取教务系统网页获得。当用户在登录界面点击登录后，获取到的用户个人信息将用于个人中心界面的初始化操作。用户登录成功后，个人中心界面如图13所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/669731093486497e9c15aeee3c515d49.png#pic_center)

用户在个人中心界面可以完成查看个人信息、修改个性签名、查看作者博客、退出登录等操作。
### 3.1 个性签名
点击个人中心界面的&ldquo;个性签名&rdquo;按钮，进入到个性签名修改界面，如图14所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/2a659ded20ab4964861756a4f2e532b4.png#pic_center)

重新输入个性签名后，点击确定，即可成功修改。如果用户并未改变原有的内容，点击返回键可正常返回，否则系统会提示用户点击&ldquo;确认&rdquo;键来进行修改操作。修改后的个性签名如图15所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/ae5c9701760b428f82167bd1b2841b03.png#pic_center)

### 3.2 作者博客
点击个人中心界面的&ldquo;作者博客&rdquo;按钮，即可进入到开发者的CSDN博客主页，如图16所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/a050aa969bdd46ebb4ab9c6a050b3460.png#pic_center)
### 3.3 退出登录
点击个人中心界面的&ldquo;退出登录&rdquo;按钮，会弹出一个提示窗口，如图17所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/765230ebf75f4c70baed0ca951fbb45b.png#pic_center)

点击确认后，系统将清除掉用户的所有数据并跳转到登录界面（图4），重新进行登录。