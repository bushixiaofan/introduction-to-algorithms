package com.song.samples.fft;

/**
 * @author: songzeqi
 * @Date: 2019-06-26 4:30 PM
 */

public class ComplexNumber {
    private double realPart;
    private double imaginaryPart;

    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public double getRealPart() {
        return realPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNumber multiply(ComplexNumber another) {
        return new ComplexNumber(this.realPart * another.getRealPart() - this.imaginaryPart * another.getImaginaryPart(),
                this.realPart * another.getImaginaryPart() + this.imaginaryPart * another.getRealPart());
    }

    public ComplexNumber plus(ComplexNumber another) {
        return new ComplexNumber(this.realPart + another.getRealPart(), this.imaginaryPart + another.getImaginaryPart());
    }

    public ComplexNumber subtract(ComplexNumber another) {
        return new ComplexNumber(this.realPart - another.getRealPart(), this.imaginaryPart - another.getImaginaryPart());
    }

    @Override
    public String toString() {
        return "ComplexNumber{" +
                "realPart=" + realPart +
                ", imaginaryPart=" + imaginaryPart +
                '}';
    }
}
