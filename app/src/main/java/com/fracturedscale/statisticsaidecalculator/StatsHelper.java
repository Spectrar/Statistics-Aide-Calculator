package com.fracturedscale.statisticsaidecalculator;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.TDistribution;
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

    public double binomPDF(double trials, double p, double x){
        BinomialDistribution bD = new BinomialDistribution((int) trials,p);
        return bD.probability((int) x);
    }

    public double invNorm(double area, double mean, double sd){
        nD = new NormalDistribution(mean,sd);
        return nD.inverseCumulativeProbability(area);
    }

    public double invNormConfidenceInterval(double invN, double p, double n){
        return (invN*Math.sqrt((p*(1-p))/n));
    }

    public double invT(double area, double df){
        TDistribution tD = new TDistribution(df);
        return tD.inverseCumulativeProbability(area);
    }

    public double invTConfidenceInterval(double invT, double sd, double n){
        return (invT*(sd/Math.sqrt(n)));
    }
}
