import java.util.Scanner;
class Lesson{
    String LessonName;
    Boolean LessonIsRequired;
    String LessonTeacher;
    int LessonTime;
    int LessonScore;
    Lesson(String lessonName,Boolean lessonIsRequired,String lessonTeacher,int lessonTime,int lessonScore){
        LessonName = lessonName;
        LessonIsRequired = lessonIsRequired;
        LessonTeacher = lessonTeacher;
        LessonTime = lessonTime;
        LessonScore = lessonScore;
    }
    void PrintOut(){
        System.out.println(LessonName + " 必修课：" + LessonIsRequired + " 教师：" + LessonTeacher + " 学时：" + LessonTime + "课时 学分：" + LessonScore);
    }
}
class LessonFunction{
    int LessonNum;
    Lesson[] lesson;
    LessonFunction(){
        lesson = new Lesson[50];
        LessonNum = 0;
    }
    void LessonAdd(){
        System.out.print("输入课程名：");
        String lessonName = new Scanner(System.in).nextLine();
        System.out.print("输入教师名：");
        String lessonTeacher = new Scanner(System.in).nextLine();
        System.out.print("输入学时：");
        int lessonTime = new Scanner(System.in).nextInt();
        System.out.print("输入学分：");
        int lessonScore = new Scanner(System.in).nextInt();
        System.out.print("是否是必修课(是输入1，否输入0)：");
        Boolean lessonIsRequired = false;
        if(new Scanner(System.in).nextInt() == 1)
            lessonIsRequired = true;
        lesson[LessonNum] = new Lesson(lessonName,lessonIsRequired,lessonTeacher,lessonTime,lessonScore);
        LessonNum++;
    }
    int LessonSearchName(String Name, int Start){
        for(int i = Start;i < LessonNum;i++){
            if(Name.equals(lesson[i].LessonName))
                return i;
        }
        return -1;
    }
    int LessonSearchIsRequired(Boolean required,int Start){
        for (int i = Start; i < LessonNum; i++) {
            if (lesson[i].LessonIsRequired == required){
                return i;
            }
        }
        return -1;
    }
    int LessonSearchTeacherName(String teacherName, int Start){
        for(int i = Start;i < LessonNum;i++){
            if(teacherName.equals(lesson[i].LessonTeacher))
                return i;
        }
        return -1;
    }
    void LessonSearch(){
        System.out.print("输入：\n1：按课程名查询，2：查询必修课，3：查询选修课，4：按教师名查询 :");
        switch (new Scanner(System.in).nextInt()){
            case 1:
                System.out.print("输入课程名：");
                String name = new Scanner(System.in).nextLine();
                for(int i = 0;i < LessonNum && i != -1;){
                    if((i = LessonSearchName(name , i )) != -1) {
                        lesson[i].PrintOut();
                        i++;
                    }
                }
                System.out.print("查找完成\n");
                break;
            case 2:
                for(int i = 0;i < LessonNum && i != -1;){
                    if((i = LessonSearchIsRequired(true,i)) != -1){
                        lesson[i].PrintOut();
                        i++;
                    }
                }
                System.out.print("查找完成\n");
                break;
            case 3:
                for(int i = 0;i < LessonNum && i != -1;){
                    if((i = LessonSearchIsRequired(false,i)) != -1){
                        lesson[i].PrintOut();
                        i++;
                    }
                }
                System.out.print("查找完成\n");
                break;
            case 4:
                System.out.print("输入课程名：");
                String teacherName = new Scanner(System.in).nextLine();
                for(int i = 0;i < LessonNum && i != -1;){
                    if((i = LessonSearchTeacherName(teacherName , i )) != -1) {
                        lesson[i].PrintOut();
                        i++;
                    }
                }
                System.out.print("查找完成\n");
                break;
        }
    }
    void LessonDeleteLocation(int location){
        for(int i = location;i < LessonNum - 1;i++)
            lesson[location] = lesson[location + 1];
        LessonNum--;
    }
    void LessonDelete(){
        System.out.print("输入课程名");
        int deleteLocation = LessonSearchName(new Scanner(System.in).nextLine(),0);
        System.out.print("课程为");
        lesson[deleteLocation].PrintOut();
        System.out.print("输入 1 继续，输入 2 返回：");
        if(new Scanner(System.in).nextInt() == 1) {
            LessonDeleteLocation(deleteLocation);
            System.out.print("已删除\n");
        }
    }
    void LessonChange(){
        System.out.print("输入课程名");
        int deleteLocation = LessonSearchName(new Scanner(System.in).nextLine(),0);
        System.out.print("课程为");
        lesson[deleteLocation].PrintOut();
        System.out.print("输入 1 继续，输入 2 返回：");
        if(new Scanner(System.in).nextInt() == 1) {
            LessonDeleteLocation(deleteLocation);
            LessonAdd();
            System.out.print("已修改\n");
        }
    }
    void LessonOutAll(){
        for(int i = 0;i < LessonNum; i++){
            lesson[i].PrintOut();
        }
        System.out.print("已全部输出\n");
    }
}
public class LessonsManage {
    public static void main(String [] args){
        LessonFunction LF = new LessonFunction();
        Boolean b = true;
        while (b){
            System.out.print("输入 1 添加课程         输入 2 查询课程           输入 3 删除课程\n");
            System.out.print("输入 4 修改课程信息      输入 5 输出全部课程信息     输入 0 退出\n");
            switch (new Scanner(System.in).nextInt()){
                case 1:
                    LF.LessonAdd();
                    break;
                case 2:
                    LF.LessonSearch();
                    break;
                case 3:
                    LF.LessonDelete();
                    break;
                case 4:
                    LF.LessonChange();
                    break;
                case 5:
                    LF.LessonOutAll();
                    break;
                case 0:
                    b = false;
                    break;
                default:
                    break;
            }
        }
    }
}