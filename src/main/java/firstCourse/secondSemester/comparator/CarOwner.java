package firstCourse.secondSemester.comparator;

public class CarOwner<T1, T2> {
    protected T1 obj1;
    protected T2 obj2;

    public CarOwner(T1 obj1, T2 obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T1 getObj1() {
        return obj1;
    }

    public void setObj1(T1 obj1) {
        this.obj1 = obj1;
    }

    public T2 getObj2() {
        return obj2;
    }

    public void setObj2(T2 obj2) {
        this.obj2 = obj2;
    }
}
