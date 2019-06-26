package com.song.samples.fft;

import java.math.BigDecimal;

/**
 * 一元多项式
 *
 * @author: songzeqi
 * @Date: 2019-06-26 4:46 PM
 */

public class Polynomial {
    /**
     * 多项式的次数
     */
    private int degree;
    /**
     * 系数
     */
    private double[] coefficients;
    /**
     * 多项式在n次单位负数根下的值向量
     */
    private ComplexNumber[] complexRootValues;

    public Polynomial(int degree, double[] coefficients) {
        this.degree = degree;
        this.coefficients = coefficients;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public ComplexNumber[] fftGetComplexNumberAndAssignmentValue() {
        complexRootValues = new ComplexNumber[degree];
        ComplexNumber[] reverseCopy = new ComplexNumber[degree];
        for(int i = 0;i < degree;i++) {
            reverseCopy[i] = new ComplexNumber(coefficients[i], 0);
        }
        this.reverseCopy(reverseCopy);
        for(int s = 1;s<= Math.log(degree)/Math.log(2); s++) {
            int m = (int)Math.pow(2, s);
            ComplexNumber omegam = new ComplexNumber(Math.cos(2 * Math.PI / m), Math.sin(2 * Math.PI / m));
            for(int k = 0;k < degree;k+=m) {
                ComplexNumber omega = new ComplexNumber(1, 0);
                for(int j = 0;j<m/2;j++) {
                    ComplexNumber t = omega.multiply(reverseCopy[k + j + m / 2]);
                    ComplexNumber u = reverseCopy[k + j];
                    reverseCopy[k + j] = u.plus(t);
                    reverseCopy[k + j + m / 2] = u.subtract(t);
                    omega = omega.multiply(omegam);
                }
            }

        }
        this.complexRootValues = reverseCopy;
        return this.complexRootValues;
    }

    public void reverseCopy(ComplexNumber[] complexNumbers) {
        ComplexNumber t;
        int n = complexNumbers.length;
        int p = (int) (Math.log(n) / Math.log(2));
        for (int i = 0; i < n; i++) {
            int a = i;
            int b = 0;
            for (int j = 0; j < p; j++) {
                b = (b << 1) + (a & 1);
                a >>= 1;
                if (b > i) {
                    t = complexNumbers[i];
                    complexNumbers[i] = complexNumbers[b];
                    complexNumbers[b] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        Polynomial polynomial = new Polynomial(4, new double[]{1, 1, 1, 1});
        polynomial.fftGetComplexNumberAndAssignmentValue();
        for(int i = 0; i< polynomial.fftGetComplexNumberAndAssignmentValue().length; i++) {
            System.out.println(polynomial.complexRootValues[i]);
        }
    }
}
