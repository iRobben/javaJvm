import java.lang.invoke.MethodHandle;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodType;

/**
 * <pre>
 *     methodHandle  字节码层面
 *     invokedynamic 与 invoke* 最大区别自在于分派规则是前者由程序员控制，后者由虚拟机控制
 * </pre>
 *
 * Created by zhangrenhua on 2017/8/27.
 */
class TestMethodHandle {


    class GrandFather {
        void thinking(){
            System.out.println("I am grandFather!");
        }
    }

    class Father extends GrandFather{
        void thinking(){
            System.out.println("I am father!");
        }
    }

    class Son extends Father{
        //尝试在这里调用祖父的thinking()方法
        void thinking(){
            //System.out.println("I am son!");
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = lookup().findSpecial(GrandFather.class,"thinking",mt,getClass());
                mh.invoke(this);
            }catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        (new TestMethodHandle().new Son()).thinking();
    }



}
