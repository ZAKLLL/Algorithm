package testpackage;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MethodDemo {

    public static void main(String[] args) {
        SampleClass<String> stringSampleClass = new SampleClass<>();
        Method[] methods = stringSampleClass.getClass().getMethods();

        Type returnType2 = methods[0].getParameterTypes()[0];
        Type returnType3 = methods[0].getGenericParameterTypes()[0];
        System.out.println(returnType2);
        System.out.println(returnType3);
        //在后方加入一个{} 实现一个匿名内部类,对泛型参数进行了限制,通过定义类的方式保留了泛型信息
        List<SampleClass<String>> list = new ArrayList<SampleClass<String>>() {};
        ParameterizedType parameterizedType = (ParameterizedType) list.getClass().getGenericSuperclass();
        for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
            System.out.println(actualTypeArgument);
        }

    }
}

class SampleClass<E> {
    public void T(List<SampleClass<String>> list) {
        return;
    }

}


