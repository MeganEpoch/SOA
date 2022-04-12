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
   
6. StudentList.xsd

   定义了studentList标签对应的学生列表信息数据类型，包括：学号（9位），个人信息类型

### XML实例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 确定名空间 要注意区分 -->
<studentInfo xmlns="http://jw.nju.edu.cn/schema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:nju="http://www.nju.edu.cn/schema" xsi:schemaLocation="http://jw.nju.edu.cn/schema Student.xsd">
    <!-- 学号 -->
    <studentId>181250014</studentId>

    <!-- 个人信息 -->
    <personInfo>
        <nju:name>陈文龙</nju:name>
        <nju:departmentId>000322</nju:departmentId>
    </personInfo>

    <!-- 课程列表 -->
    <performanceList>
        <!-- 具体课程 -->
        <course courseId="000122">
            <!-- 成绩 -->
            <performance performanceType="平时成绩">
                <score>96</score>
            </performance>
            <performance performanceType="作业成绩">
                <score>94</score>
            </performance>
            <performance performanceType="期中成绩">
                <score>94</score>
            </performance>
            <performance performanceType="期末成绩">
                <score>90</score>
            </performance>
            <performance performanceType="总评成绩">
                <score>96</score>
            </performance>
        </course>

        <course courseId="000147">
            <performance performanceType="平时成绩">
                <score>90</score>
            </performance>
            <performance performanceType="作业成绩">
                <score>89</score>
            </performance>
            <performance performanceType="期中成绩">
                <score>98</score>
            </performance>
            <performance performanceType="期末成绩">
                <score>97</score>
            </performance>
            <performance performanceType="总评成绩">
                <score>96</score>
            </performance>
        </course>
    </performanceList>
</studentInfo>
```

