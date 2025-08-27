package com.concurrent_sharing_issues;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;


/**
 * Unsafe_CAS
 */
public class test_unsafe {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Field name = Person.class.getDeclaredField("name");
        Field age = Person.class.getDeclaredField("age");

        long name_off = unsafe.objectFieldOffset(name);
        long age_off = unsafe.objectFieldOffset(age);

        Person p = new Person();

        unsafe.compareAndSwapObject(p,name_off,null,"小红");
        unsafe.compareAndSwapInt(p,age_off,0,25);

        System.out.println(p);

    }
}



@Data
class Person{
    private String name;
    private int age;

}
