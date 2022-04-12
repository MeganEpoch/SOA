### Schema文件中定义的数据类型

1. Department.xsd

   定义了departmentInfo标签对应的部门信息数据类型，包含：部门编号（6位）、部门名称。

2. PersonInfo.xsd

   定义了personInfo标签对应的个人信息数据类型，包含：姓名、所属部门编号（6位）。

3. Student.xsd

   定义了studentInfo标签对应的学生信息数据类型，包含：学号（9位）、personInfo、课业成绩列表。

   课业成绩包含：课程编号（6位）、成绩性质、得分（0-100）。

4. Course.xsd

   定义了courseId标签对应的课程编号数据类型，是长度为6 的字符串类型。

5. ScoreList.xsd

   修改了引用其他xsd文件的路径，使其与目录结构兼容。

   定义了coursePerformanceList标签对应的课程信息数据类型，包含：课程编号（6位）、成绩性质、学号（9位）、得分（0-100）。