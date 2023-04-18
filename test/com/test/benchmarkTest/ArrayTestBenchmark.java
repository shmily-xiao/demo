package com.test.benchmarkTest;

import org.openjdk.jmh.annotations.*;

/**
 * 矩阵 C = AB 的计算
 *
 * @author wzj
 * @date 2023/02/09
 */
@BenchmarkMode(Mode.AverageTime)
@State(value = Scope.Benchmark)
// 预热3次
@Warmup(iterations = 3, time = 1)
// 循环 10 次
@Measurement(iterations = 10, time = 1)
public class ArrayTestBenchmark {

    private final int N = 1100;

    private final int[][] arrays_A = new int[N][N];
    private final int[][] arrays_B = new int[N][N];

    @Setup
    public void setUp() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arrays_A[i][j] = i + j;
                arrays_B[i][j] = i + j;
            }
        }
    }


    @Benchmark
    public void ijk() {
        final int[][] arrays_C = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += arrays_A[i][k] * arrays_B[k][j];
                }
                arrays_C[i][j] += sum;
            }
        }
        assert arrays_C.length > 0;
    }

    @Benchmark
    public void jik() {
        final int[][] arrays_C = new int[N][N];
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += arrays_A[i][k] * arrays_B[k][j];
                }
                arrays_C[i][j] += sum;
            }
        }
        assert arrays_C.length > 0;
    }

    @Benchmark
    public void jki() {
        final int[][] arrays_C = new int[N][N];
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                int r_B = arrays_B[k][j];
                for (int i = 0; i < N; i++) {
                    arrays_C[i][j] += arrays_A[i][k] * r_B;
                }
            }
        }
        assert arrays_C.length > 0;
    }

    @Benchmark
    public void kji() {
        final int[][] arrays_C = new int[N][N];
        for (int k = 0; k < N; k++) {
            for (int j = 0; j < N; j++) {
                int r_B = arrays_B[k][j];
                for (int i = 0; i < N; i++) {
                    arrays_C[i][j] += arrays_A[i][k] * r_B;
                }
            }
        }
        assert arrays_C.length > 0;
    }

    @Benchmark
    public void kij() {
        final int[][] arrays_C = new int[N][N];
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                int r_A = arrays_A[k][i];
                for (int j = 0; j < N; j++) {
                    arrays_C[i][j] += r_A * arrays_B[k][j];
                }
            }
        }
        assert arrays_C.length > 0;
    }

    @Benchmark
    public void ikj() {
        final int[][] arrays_C = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                int r_A = arrays_A[k][i];
                for (int j = 0; j < N; j++) {
                    arrays_C[i][j] += r_A * arrays_B[k][j];
                }
            }
        }
        assert arrays_C.length > 0;
    }
}
