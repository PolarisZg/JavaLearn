import java.util.Scanner;
import java.util.Vector;
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
public class LessonsManage {
    int LessonNum;
    Lesson[] lesson;
    LessonsManage(){
        lesson = new Lesson[50];
        LessonNum = 0;
    }
    Boolean LessonAdd(){
        Scanner scanner = new Scanner(System.in);
        if(LessonNum >= 50){
            return false;
        }
        System.out.print("输入课程名：");
        lesson[LessonNum].LessonName = scanner.nextLine();
        System.out.print("输入教师名：");
        lesson[LessonNum].LessonTeacher = scanner.nextLine();
        System.out.print("输入学时：");
        lesson[LessonNum].LessonTime = scanner.nextInt();
        System.out.print("输入学分：");
        lesson[LessonNum].LessonScore = scanner.nextInt();
        System.out.print("是否是必修课(是输入1，否输入0)：");
        lesson[LessonNum].LessonIsRequired = false;
        if(scanner.nextInt() == 1)
            lesson[LessonNum].LessonIsRequired = true;
        LessonNum++;
        return true;
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

    }
    void LessonDeleteLocation(int location){
        for(int i = location;i < LessonNum - 1;i++)
            lesson[location] = lesson[location + 1];
        LessonNum--;
    }
    void LessonDelete(){

   }
}
