package com.nl.multithreading;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        double[] nums = new double[100000];
        for (var i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        System.out.println("A portion of the original sequence");
        for (var i = 0; i < 10; i++) {
            System.out.println(nums[i]);
        }

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);

        fjp.invoke(task);
        System.out.println("A portion of the transformed sequence");
        for (var i = 0; i < 10; i++) {
            System.out.printf("%.4f", nums[i]);
            System.out.println();
        }
    }
}

class SqrtTransform extends RecursiveAction {
    double[] nums;
    int start;
    int end;
    final int seqThreshold = 1000;

    SqrtTransform(double[] nums, int start, int end) {
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < seqThreshold) {
            for (var i = start; i < end; i++) {
                this.nums[i] = Math.sqrt(nums[i]);
            }

        } else {
            // find the midpoint
            int mid = (start + end) / 2;
            invokeAll(new SqrtTransform(nums, start, mid), new SqrtTransform(nums, mid, end));
        }
    }
}