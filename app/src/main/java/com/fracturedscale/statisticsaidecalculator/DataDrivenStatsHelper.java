package com.fracturedscale.statisticsaidecalculator;

import org.apache.commons.math3.stat.Frequency;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.inference.TestUtils;

import java.util.*;

public class DataDrivenStatsHelper {

    private Frequency f = new Frequency();
    private SummaryStatistics ss = new SummaryStatistics();
    private ArrayList<Double> noDuplicates = new ArrayList<>();
    private ArrayList<Double> fullList = new ArrayList<>();


    public DataDrivenStatsHelper(HashMap<Double, Integer> map) {
        for (double d : map.keySet()) {
            noDuplicates.add(d);
            for (int i = 0; i < map.get(d); i++) {
                f.addValue(d);
                ss.addValue(d);
                fullList.add(d);
            }
        }
        Collections.sort(noDuplicates);
        Collections.sort(fullList);

    }

    public DataDrivenStatsHelper(Double[] list) {
        Arrays.sort(list);

        for (Double d : list) {
            f.addValue(d);
            ss.addValue(d);
            fullList.add(d);
            if (!noDuplicates.contains(d)) {
                noDuplicates.add(d);
            }
        }
        Collections.sort(noDuplicates);
        Collections.sort(fullList);
    }

    public HashMap relativeFrequency() {

        HashMap<Double, Double> frequencies = new HashMap<>();
        for (double d : noDuplicates) {
            frequencies.put(d, f.getPct(d));
        }
        return frequencies;
    }

    public HashMap singleFrequency() {

        HashMap<Double, Double> frequencies = new HashMap<>();
        for (double d : noDuplicates) {
            frequencies.put(d, (double) f.getCount(d));
        }
        return frequencies;
    }

    public List theMode() {
        return f.getMode();
    }

    public double theMean() {
        return ss.getMean();
    }

    public double theSampleStandardDeviation() {
        return ss.getStandardDeviation();
    }
    public double thePopulationStandardDeviation(){
        double sum=0;
        for(double d: fullList){
            sum+= Math.pow(d-ss.getMean(),2);
        }
        return Math.sqrt(sum/ss.getN());
    }

    public double theRange() {
        return ss.getMax() - ss.getMin();
    }

    public double theZScore(double x) {
        return ((x - ss.getMean()) / ss.getStandardDeviation());
    }

    public double theCount() {
        return (double) ss.getN();
    }

    public double theQ1() {
        double listSize = fullList.size();
        double q1Place = (listSize * .25);
        if(q1Place-(int)q1Place!=0){
            return (fullList.get((int)q1Place-1)+fullList.get((int)q1Place))/2;
        }else {
            return fullList.get((int) q1Place);
        }
    }

    public double theQ3() {
        double listSize = fullList.size();
        double q3Place = (listSize * .75);
        if(q3Place-(int)q3Place!=0){
            return (fullList.get((int)q3Place)+fullList.get((int)q3Place+1))/2;
        }else {
            return fullList.get((int) q3Place);
        }
    }

    public double lowerFence(){
        double q1=theQ1();
        double q3=theQ3();

        return q1-1.5*(q3-q1);
    }

    public double upperFence(){
        double q1=theQ1();
        double q3=theQ3();

        return q3+1.5*(q3-q1);
    }

    public double theMedian() {
        double listSize = fullList.size();
        double q1Place = (listSize * .5);
        if(q1Place-(int)q1Place!=0){
            return (fullList.get((int)q1Place-1)+fullList.get((int)q1Place))/2;
        }else {
            return fullList.get((int) q1Place);
        }
    }

    public double theSum(){
        return ss.getSum();
    }

    public double theSumSquared(){
        return ss.getSumsq();
    }

    public double theMax(){
        return  ss.getMax();
    }

    public double theMin(){
        return  ss.getMin();
    }
}
