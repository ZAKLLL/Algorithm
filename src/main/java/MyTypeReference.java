import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author ZhangJiaKui
 * @classname MyTypeReference
 * @description TODO
 * @date 12/16/2020 11:41 AM
 */
public class MyTypeReference extends TypeReference {
    public MyTypeReference(Class c) throws NoSuchFieldException, IllegalAccessException {
        Type genericClass = c.getGenericSuperclass();
        Class<?> superclass = this.getClass().getSuperclass();
//        classTypeCache
//        type
        Field type = superclass.getField("type");
        Field classTypeCache = superclass.getField("classTypeCache");
        type.setAccessible(true);
        classTypeCache.setAccessible(true);
        type.set(this,genericClass);
//        classTypeCache.getClass().getMethod("put",Type.class,Type.class);
    }

}
