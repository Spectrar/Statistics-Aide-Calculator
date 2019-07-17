package com.fracturedscale.statisticsaidecalculator;

import org.apache.commons.math3.stat.inference.BinomialTest;
import org.apache.commons.math3.stat.interval.BinomialConfidenceInterval;
import org.apache.commons.math3.distribution.NormalDistribution;

public class StatsHelper {

    BinomialConfidenceInterval binomPDF;

    private NormalDistribution nD;

    public double normalCDF(double lower, double upper, double mean, double sd){
        nD = new NormalDistribution(mean,sd);
        return nD.probability(lower,upper);
    }

    public double invNorm(double area, double mean, double sd){
        nD = new NormalDistribution(mean,sd);
        return nD.inverseCumulativeProbability(area);
    }

    public double invNormConfidenceInterval(double invN, double p, double n){
        return (invN*Math.sqrt((p*(1-p))/n));
    }

}
