package com.concurrent_tools;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 用fork/join 实现begin-end 的求和
 */
public class test_fork_join {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);

        Integer sum = pool.invoke(new Sum(1, 10));

        System.out.println(sum);


    }
}

class Sum extends RecursiveTask<Integer>{
    private int begin;
    private int end;

    public Sum(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        if (begin == end) return begin;

        if (end - begin == 1) return begin + end;

        int mid = (begin + end) /2;

        // 拆分-分治
        Sum t1 = new Sum(begin, mid);
        t1.fork();

        Sum t2 = new Sum(mid + 1, end);
        t2.fork();

        int result = t1.join() + t2.join();

        return result;
    }
}


