import java.util.*;

public class Ice {
    Map<Double, Double> yearIcyDaysCount;
    int yearsCount = 0;

    public Ice() {
        yearIcyDaysCount = new TreeMap<Double, Double>();
        yearIcyDaysCount.put(1855.0,118.0);
        yearIcyDaysCount.put(1856.0,151.0);
        yearIcyDaysCount.put(1857.0,121.0);
        yearIcyDaysCount.put(1858.0,96.0);
        yearIcyDaysCount.put(1859.0,110.0);
        yearIcyDaysCount.put(1860.0,117.0);
        yearIcyDaysCount.put(1861.0,132.0);
        yearIcyDaysCount.put(1862.0,104.0);
        yearIcyDaysCount.put(1863.0,125.0);
        yearIcyDaysCount.put(1864.0,118.0);
        yearIcyDaysCount.put(1865.0,125.0);
        yearIcyDaysCount.put(1866.0,123.0);
        yearIcyDaysCount.put(1867.0,110.0);
        yearIcyDaysCount.put(1868.0,127.0);
        yearIcyDaysCount.put(1869.0,131.0);
        yearIcyDaysCount.put(1870.0,99.0);
        yearIcyDaysCount.put(1871.0,126.0);
        yearIcyDaysCount.put(1872.0,144.0);
        yearIcyDaysCount.put(1873.0,136.0);
        yearIcyDaysCount.put(1874.0,126.0);
        yearIcyDaysCount.put(1875.0,91.0);
        yearIcyDaysCount.put(1876.0,130.0);
        yearIcyDaysCount.put(1877.0,62.0);
        yearIcyDaysCount.put(1878.0,112.0);
        yearIcyDaysCount.put(1879.0,99.0);
        yearIcyDaysCount.put(1880.0,161.0);
        yearIcyDaysCount.put(1881.0,78.0);
        yearIcyDaysCount.put(1882.0,124.0);
        yearIcyDaysCount.put(1883.0,119.0);
        yearIcyDaysCount.put(1884.0,124.0);
        yearIcyDaysCount.put(1885.0,128.0);
        yearIcyDaysCount.put(1886.0,131.0);
        yearIcyDaysCount.put(1887.0,113.0);
        yearIcyDaysCount.put(1888.0,88.0);
        yearIcyDaysCount.put(1889.0,75.0);
        yearIcyDaysCount.put(1890.0,111.0);
        yearIcyDaysCount.put(1891.0,97.0);
        yearIcyDaysCount.put(1892.0,112.0);
        yearIcyDaysCount.put(1893.0,101.0);
        yearIcyDaysCount.put(1894.0,101.0);
        yearIcyDaysCount.put(1895.0,91.0);
        yearIcyDaysCount.put(1896.0,110.0);
        yearIcyDaysCount.put(1897.0,100.0);
        yearIcyDaysCount.put(1898.0,130.0);
        yearIcyDaysCount.put(1899.0,111.0);
        yearIcyDaysCount.put(1900.0,107.0);
        yearIcyDaysCount.put(1901.0,105.0);
        yearIcyDaysCount.put(1902.0,89.0);
        yearIcyDaysCount.put(1903.0,126.0);
        yearIcyDaysCount.put(1904.0,108.0);
        yearIcyDaysCount.put(1905.0,97.0);
        yearIcyDaysCount.put(1906.0,94.0);
        yearIcyDaysCount.put(1907.0,83.0);
        yearIcyDaysCount.put(1908.0,106.0);
        yearIcyDaysCount.put(1909.0,98.0);
        yearIcyDaysCount.put(1910.0,101.0);
        yearIcyDaysCount.put(1911.0,108.0);
        yearIcyDaysCount.put(1912.0,99.0);
        yearIcyDaysCount.put(1913.0,88.0);
        yearIcyDaysCount.put(1914.0,115.0);
        yearIcyDaysCount.put(1915.0,102.0);
        yearIcyDaysCount.put(1916.0,116.0);
        yearIcyDaysCount.put(1917.0,115.0);
        yearIcyDaysCount.put(1918.0,82.0);
        yearIcyDaysCount.put(1919.0,110.0);
        yearIcyDaysCount.put(1920.0,81.0);
        yearIcyDaysCount.put(1921.0,96.0);
        yearIcyDaysCount.put(1922.0,125.0);
        yearIcyDaysCount.put(1923.0,104.0);
        yearIcyDaysCount.put(1924.0,105.0);
        yearIcyDaysCount.put(1925.0,124.0);
        yearIcyDaysCount.put(1926.0,103.0);
        yearIcyDaysCount.put(1927.0,106.0);
        yearIcyDaysCount.put(1928.0,96.0);
        yearIcyDaysCount.put(1929.0,107.0);
        yearIcyDaysCount.put(1930.0,98.0);
        yearIcyDaysCount.put(1931.0,65.0);
        yearIcyDaysCount.put(1932.0,115.0);
        yearIcyDaysCount.put(1933.0,91.0);
        yearIcyDaysCount.put(1934.0,94.0);
        yearIcyDaysCount.put(1935.0,101.0);
        yearIcyDaysCount.put(1936.0,121.0);
        yearIcyDaysCount.put(1937.0,105.0);
        yearIcyDaysCount.put(1938.0,97.0);
        yearIcyDaysCount.put(1939.0,105.0);
        yearIcyDaysCount.put(1940.0,96.0);
        yearIcyDaysCount.put(1941.0,82.0);
        yearIcyDaysCount.put(1942.0,116.0);
        yearIcyDaysCount.put(1943.0,114.0);
        yearIcyDaysCount.put(1944.0,92.0);
        yearIcyDaysCount.put(1945.0,98.0);
        yearIcyDaysCount.put(1946.0,101.0);
        yearIcyDaysCount.put(1947.0,104.0);
        yearIcyDaysCount.put(1948.0,96.0);
        yearIcyDaysCount.put(1949.0,109.0);
        yearIcyDaysCount.put(1950.0,122.0);
        yearIcyDaysCount.put(1951.0,114.0);
        yearIcyDaysCount.put(1952.0,81.0);
        yearIcyDaysCount.put(1953.0,85.0);
        yearIcyDaysCount.put(1954.0,92.0);
        yearIcyDaysCount.put(1955.0,114.0);
        yearIcyDaysCount.put(1956.0,111.0);
        yearIcyDaysCount.put(1957.0,95.0);
        yearIcyDaysCount.put(1958.0,126.0);
        yearIcyDaysCount.put(1959.0,105.0);
        yearIcyDaysCount.put(1960.0,108.0);
        yearIcyDaysCount.put(1961.0,117.0);
        yearIcyDaysCount.put(1962.0,112.0);
        yearIcyDaysCount.put(1963.0,113.0);
        yearIcyDaysCount.put(1964.0,120.0);
        yearIcyDaysCount.put(1965.0,65.0);
        yearIcyDaysCount.put(1966.0,98.0);
        yearIcyDaysCount.put(1967.0,91.0);
        yearIcyDaysCount.put(1968.0,108.0);
        yearIcyDaysCount.put(1969.0,113.0);
        yearIcyDaysCount.put(1970.0,110.0);
        yearIcyDaysCount.put(1971.0,105.0);
        yearIcyDaysCount.put(1972.0,97.0);
        yearIcyDaysCount.put(1973.0,105.0);
        yearIcyDaysCount.put(1974.0,107.0);
        yearIcyDaysCount.put(1975.0,88.0);
        yearIcyDaysCount.put(1976.0,115.0);
        yearIcyDaysCount.put(1977.0,123.0);
        yearIcyDaysCount.put(1978.0,118.0);
        yearIcyDaysCount.put(1979.0,99.0);
        yearIcyDaysCount.put(1980.0,93.0);
        yearIcyDaysCount.put(1981.0,96.0);
        yearIcyDaysCount.put(1982.0,54.0);
        yearIcyDaysCount.put(1983.0,111.0);
        yearIcyDaysCount.put(1984.0,85.0);
        yearIcyDaysCount.put(1985.0,107.0);
        yearIcyDaysCount.put(1986.0,89.0);
        yearIcyDaysCount.put(1987.0,87.0);
        yearIcyDaysCount.put(1988.0,97.0);
        yearIcyDaysCount.put(1989.0,93.0);
        yearIcyDaysCount.put(1990.0,88.0);
        yearIcyDaysCount.put(1991.0,99.0);
        yearIcyDaysCount.put(1992.0,108.0);
        yearIcyDaysCount.put(1993.0,94.0);
        yearIcyDaysCount.put(1994.0,74.0);
        yearIcyDaysCount.put(1995.0,119.0);
        yearIcyDaysCount.put(1996.0,102.0);
        yearIcyDaysCount.put(1997.0,47.0);
        yearIcyDaysCount.put(1998.0,82.0);
        yearIcyDaysCount.put(1999.0,53.0);
        yearIcyDaysCount.put(2000.0,115.0);
        yearIcyDaysCount.put(2001.0,21.0);
        yearIcyDaysCount.put(2002.0,89.0);
        yearIcyDaysCount.put(2003.0,80.0);
        yearIcyDaysCount.put(2004.0,101.0);
        yearIcyDaysCount.put(2005.0,95.0);
        yearIcyDaysCount.put(2006.0,66.0);
        yearIcyDaysCount.put(2007.0,106.0);
        yearIcyDaysCount.put(2008.0,97.0);
        yearIcyDaysCount.put(2009.0,87.0);
        yearIcyDaysCount.put(2010.0,109.0);
        yearIcyDaysCount.put(2011.0,57.0);
        yearIcyDaysCount.put(2012.0,87.0);
        yearIcyDaysCount.put(2013.0,117.0);
        yearIcyDaysCount.put(2014.0,91.0);
        yearIcyDaysCount.put(2015.0,62.0);
        yearIcyDaysCount.put(2016.0,65.0);
        yearsCount = yearIcyDaysCount.size();
    }

    public void printMap() {
        Iterator<Map.Entry<Double, Double>> it = yearIcyDaysCount.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Double, Double> pair = it.next();
            System.out.println(pair.getKey().intValue() + " " + pair.getValue().intValue());
        }
    }

    public void printMeanAndSD() {
        double mean = 0, stdDeviation = 0, sum = 0;
        Collection<Double> values = yearIcyDaysCount.values();
        for(double val: values)
            sum += val;
        mean = sum / yearsCount;

        sum = 0;
        for(double val: values)
            sum += Math.pow(val - mean, 2);
        stdDeviation = Math.sqrt(sum / (yearsCount - 1));

        System.out.println(yearsCount);
        System.out.println(String.format("%.2f", mean));
        System.out.println(String.format("%.2f", stdDeviation));
    }

    public double meanSquareError(double beta0, double beta1) {
        double intermediateVal = 0, sum = 0;
        Iterator<Map.Entry<Double, Double>> it = yearIcyDaysCount.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Double, Double> pair = it.next();
            intermediateVal = beta0 + beta1 * pair.getKey() - pair.getValue();
            sum += Math.pow(intermediateVal, 2);
        }
        return sum / yearsCount;
    }

    public double gradientDescent(double beta0, double beta1, boolean isBeta1) {
        double gradient = 0, sum = 0, intermediateVal = 0;
        Iterator<Map.Entry<Double, Double>> it = yearIcyDaysCount.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Double, Double> pair = it.next();
            intermediateVal = beta0 + beta1 * pair.getKey() - pair.getValue();
            if(isBeta1)
                intermediateVal *= pair.getKey();
            sum += intermediateVal;
        }
        gradient = 2 * sum / yearsCount;
        return gradient;
    }

    public double stochasticGradientDescent(int randomIndex, double beta0, double beta1, boolean isBeta1) {
        double gradient = 0, sum = 0;

        Object[] keys = yearIcyDaysCount.keySet().toArray();
        Double randomKey = (Double) keys[randomIndex];
        Double randomValue = yearIcyDaysCount.get(randomKey);

        sum = beta0 + beta1 * randomKey - randomValue;
        if(isBeta1) sum *= randomKey;

        gradient = 2 * sum;
        return gradient;
    }

    public void performGradientDescent(double eta, int totalT) {
        double beta0 = 0, beta1 = 0, dup_0 = 0, dup_1 = 0, mse = 0;
        for(int i = 1; i <= totalT; i++) {
            dup_0 = beta0;
            dup_1 = beta1;
            beta0 = beta0 - eta * gradientDescent(dup_0, dup_1, false);
            beta1 = beta1 - eta * gradientDescent(dup_0, dup_1, true);
            mse = meanSquareError(beta0, beta1);
            System.out.print(i + " ");
            System.out.print(String.format("%.2f", beta0) + " ");
            System.out.print(String.format("%.2f", beta1) + " ");
            System.out.println(String.format("%.2f", mse));
        }
    }

    public void computeClosedFormSolution(int flag, int year) {
        double xMean = 0, yMean = 0, numerator = 0, denominator = 0;
        Iterator<Map.Entry<Double, Double>> it = yearIcyDaysCount.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Double, Double> pair = it.next();
            xMean += pair.getKey();
            yMean += pair.getValue();
        }
        xMean /= yearsCount;
        yMean /= yearsCount;
        it = yearIcyDaysCount.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Double, Double> pair = it.next();
            double x = pair.getKey();
            double y = pair.getValue();
            numerator += (x - xMean) * (y - yMean);
            denominator += Math.pow(x - xMean, 2);
        }
        double betaCap1 = numerator / denominator;
        double betaCap0 = yMean - betaCap1 * xMean;
        if(flag == 600) {
            double mse = meanSquareError(betaCap0, betaCap1);
            System.out.print(String.format("%.2f", betaCap0) + " ");
            System.out.print(String.format("%.2f", betaCap1) + " ");
            System.out.println(String.format("%.2f", mse));
        } else {
            double prediction = betaCap0 + betaCap1 * year;
            System.out.println(String.format("%.2f", prediction));
        }
    }

    public void normalizeInput() {
        double xMean = 0, xStdDeviation = 0;
        Iterator<Map.Entry<Double, Double>> it = yearIcyDaysCount.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Double, Double> pair = it.next();
            xMean += pair.getKey();
        }
        xMean /= yearsCount;

        it = yearIcyDaysCount.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Double, Double> pair = it.next();
            xStdDeviation += Math.pow(pair.getKey() - xMean, 2);
        }
        xStdDeviation /= (yearsCount - 1);
        xStdDeviation = Math.sqrt(xStdDeviation);

        Map<Double, Double> yearIcyDaysCountDup = new TreeMap<Double, Double>();
        it = yearIcyDaysCount.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Double, Double> pair = it.next();
            double x = (pair.getKey() - xMean) / xStdDeviation;
            yearIcyDaysCountDup.put(x, pair.getValue());
        }
        yearIcyDaysCount.clear();
        it = yearIcyDaysCountDup.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Double, Double> pair = it.next();
            yearIcyDaysCount.put(pair.getKey(), pair.getValue());
        }
    }

    public void performStochasticGradientDescent(double eta, int totalT) {
        double beta0 = 0, beta1 = 0, dup_0 = 0, dup_1 = 0, mse = 0;
        Random r = new Random();
        for(int i = 1; i <= totalT; i++) {
            int rand = r.nextInt(yearsCount);
            dup_0 = beta0;
            dup_1 = beta1;
            beta0 = beta0 - eta * stochasticGradientDescent(rand, dup_0, dup_1, false);
            beta1 = beta1 - eta * stochasticGradientDescent(rand, dup_0, dup_1, true);
            mse = meanSquareError(beta0, beta1);
            System.out.print(i + " ");
            System.out.print(String.format("%.2f", beta0) + " ");
            System.out.print(String.format("%.2f", beta1) + " ");
            System.out.println(String.format("%.2f", mse));
        }
    }
    public static void main(String args[]) {
        if (args.length > 3) {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }
        Ice ice = new Ice();
        int flag = Integer.valueOf(args[0]);
        double beta0 = 0, beta1 = 0, dup_0 = 0, dup_1 = 0, gradient1 = 0, gradient2 = 0, eta = 0, mse = 0;
        int totalT = 0;
        switch (flag) {
            case 100:
                ice.printMap();
                break;
            case 200:
                ice.printMeanAndSD();
                break;
            case 300:
                beta0 = Double.valueOf(args[1]);
                beta1 = Double.valueOf(args[2]);
                mse = ice.meanSquareError(beta0, beta1);
                System.out.println(String.format("%.2f", mse));
                break;
            case 400:
                beta0 = Double.valueOf(args[1]);
                beta1 = Double.valueOf(args[2]);
                gradient1 = ice.gradientDescent(beta0, beta1, false);
                gradient2 = ice.gradientDescent(beta0, beta1, true);
                System.out.println(String.format("%.2f", gradient1));
                System.out.println(String.format("%.2f", gradient2));
                break;
            case 500:
                eta = Double.valueOf(args[1]);
                totalT = Integer.valueOf(args[2]);
                ice.performGradientDescent(eta, totalT);
                break;
            case 600:
                ice.computeClosedFormSolution(flag, 0);
                break;
            case 700:
                int year = Integer.valueOf(args[1]);
                ice.computeClosedFormSolution(flag, year);
                break;
            case 800:
                eta = Double.valueOf(args[1]);
                totalT = Integer.valueOf(args[2]);
                ice.normalizeInput();
                ice.performGradientDescent(eta, totalT);
                break;
            case 900:
                eta = Double.valueOf(args[1]);
                totalT = Integer.valueOf(args[2]);
                ice.normalizeInput();
                ice.performStochasticGradientDescent(eta, totalT);
                break;
        }
    }
}
