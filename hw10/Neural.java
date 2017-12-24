import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Neural {
    public static double RELU(double z) {
        if(z >= 0) return z;
        return 0;
    }

    public static double uAuBuC(double v1, double v2, double w1, double w2, double w3, boolean print) {
        double u = w1 + v1 * w2 + v2 * w3;
        if(print)
            System.out.print(String.format("%.5f", u) + " ");
        return u;
    }

    public static double sigmoid(double z) {
        return (1 / (1 + Math.exp(-z)));
    }

    public static double processAB(double v1, double v2, double w1, double w2, double w3, boolean print) {
        double u = uAuBuC(v1, v2, w1, w2, w3, print);
        double v = RELU(u);
        if(print)
            System.out.print(String.format("%.5f", v) + " ");
        return v;
    }

    public static double processC(double v1, double v2, double w1, double w2, double w3, boolean print) {
        double u = uAuBuC(v1, v2, w1, w2, w3, print);
        double v = sigmoid(u);
        if(print)
            System.out.println(String.format("%.5f", v) + " ");
        return v;
    }

    public static double error(double vc, double y) {
        return Math.pow(vc - y, 2) / 2;
    }

    public static double dEvjdEuj(double uj) {
        return (uj >= 0 ? 1 : 0);
    }

    public static void derivativeWrtEdgeWeights(double dEdw[], double x1, double x2, double[] v, double[] dEdu, boolean print) {
        dEdw[0] = dEdu[0];
        dEdw[1] = x1 * dEdu[0];
        dEdw[2] = x2 * dEdu[0];
        dEdw[3] = dEdu[1];
        dEdw[4] = x1 * dEdu[1];
        dEdw[5] = x2 * dEdu[1];
        dEdw[6] = dEdu[2];
        dEdw[7] = v[0] * dEdu[2];
        dEdw[8] = v[1] * dEdu[2];
        if(print) {
            for (int i = 0; i < 8; i++)
                System.out.print(String.format("%.5f", dEdw[i]) + " ");
            System.out.println(String.format("%.5f", dEdw[8]));
        }
    }

    public static void calculateV(double[] v, double[] w, double x1, double x2, boolean print) {
        v[0] = processAB(x1, x2, w[1], w[2], w[3], print);
        v[1] = processAB(x1, x2, w[4], w[5], w[6], print);
        v[2] = processC(v[0], v[1], w[7], w[8], w[9], print);
    }

    public static void calculatedEdvC(double errorVal, double[] dEdu, double[] dEdv, double[] v, double y, boolean printAll, boolean printErrorVal) {
        errorVal = error(v[2], y);
        dEdv[2] = v[2] - y;
        dEdu[2] = dEdv[2] * v[2] * (1 - v[2]);
        if(printErrorVal)
            System.out.println(String.format("%.5f", errorVal));
        if(printAll) {
            System.out.print(String.format("%.5f", errorVal) + " ");
            System.out.print(String.format("%.5f", dEdv[2]) + " ");
            System.out.println(String.format("%.5f", dEdu[2]));
        }
    }

    public static void calculatedEdvAB(double[] dEdu, double[] dEdv, double[] w, double x1, double x2, boolean print) {
        dEdv[0] = dEdu[2] * w[8];
        dEdv[1] = dEdu[2] * w[9];
        dEdu[0] = dEdv[0] * dEvjdEuj(uAuBuC(x1, x2, w[1], w[2], w[3], false));
        dEdu[1] = dEdv[1] * dEvjdEuj(uAuBuC(x1, x2, w[4], w[5], w[6], false));
        if(print) {
            System.out.print(String.format("%.5f", dEdv[0]) + " ");
            System.out.print(String.format("%.5f", dEdu[0]) + " ");
            System.out.print(String.format("%.5f", dEdv[1]) + " ");
            System.out.println(String.format("%.5f", dEdu[1]));
        }
    }

    public static void SGD(double[] w, double[] dEdw, double eta) {
        for(int loop = 1; loop <= 9; loop++) {
            w[loop] = w[loop] - eta * dEdw[loop - 1];
        }
    }

    public static double calculateWeights(double[] w, double[] v, double[] dEdu, double[] dEdv, double[] dEdw, double eta, Scanner scanner) throws Exception {
        double x1, x2, y, errorVal = 0, sum = 0;
        x1 = scanner.nextDouble();
        x2 = scanner.nextDouble();
        y = scanner.nextDouble();
        calculateV(v, w, x1, x2, false);
        calculatedEdvC(errorVal, dEdu, dEdv, v, y, false, false);
        calculatedEdvAB(dEdu, dEdv, w, x1, x2, false);
        derivativeWrtEdgeWeights(dEdw, x1, x2, v, dEdu, false);
        SGD(w, dEdw, eta);
        sum = evaluationSetErrors(v, w, false);
        return sum;
    }

    public static double evaluationSetErrors(double[] v, double[] w, boolean print) throws Exception {
        double sum = 0, x1k, x2k, yk;
        URL evalUrl;
        evalUrl = new File("hw2_midterm_A_eval.txt").toURI().toURL();
//        evalUrl = new URL("http://pages.cs.wisc.edu/~jerryzhu/cs540/handouts/hw10files/hw2_midterm_A_eval.txt");
        Scanner evalScanner = new Scanner(evalUrl.openStream());
        for(int k = 0; k < 25; k++) {
            x1k = evalScanner.nextDouble();
            x2k = evalScanner.nextDouble();
            yk = evalScanner.nextDouble();
            calculateV(v, w, x1k, x2k, false);
            sum += Math.pow(v[2] - yk, 2) / 2;
        }
        if(print)
            System.out.println(String.format("%.5f", sum));
        return sum;
    }

    public static void printWeights(double[] w) {
        for(int loop = 1; loop <= 8; loop++)
            System.out.print(String.format("%.5f", w[loop]) + " ");
        System.out.println(String.format("%.5f", w[9]));
    }

    public static void printXY(double x1, double x2, double y) {
        System.out.print(String.format("%.5f", x1) + " ");
        System.out.print(String.format("%.5f", x2) + " ");
        System.out.println(String.format("%.5f", y));
    }

    public static void main(String args[]) throws Exception {
        double x1, x2, y, errorVal = 0, eta, times, prevSum = Double.MAX_VALUE, sum = 0;
        double[] dEdw, w, v, dEdu, dEdv;
        int loop, epoch;
        URL trainingUrl, testUrl;
        Scanner scanner;

        v = new double[3];
        dEdu = new double[3];
        dEdv = new double[3];
        w = new double[10];
        dEdw = new double[10];

        int flag = Integer.valueOf(args[0]);
        for(loop = 1; loop <= 9; loop++)
            w[loop] = Double.parseDouble(args[loop]);

        switch (flag) {
            case 100:
                x1 = Double.parseDouble(args[10]);
                x2 = Double.parseDouble(args[11]);
                calculateV(v, w, x1, x2, true);
                break;
            case 200:
                x1 = Double.parseDouble(args[10]);
                x2 = Double.parseDouble(args[11]);
                y = Double.parseDouble(args[12]);
                calculateV(v, w, x1, x2, false);
                calculatedEdvC(errorVal, dEdu, dEdv, v, y, true, false);
                break;
            case 300:
                x1 = Double.parseDouble(args[10]);
                x2 = Double.parseDouble(args[11]);
                y = Double.parseDouble(args[12]);
                calculateV(v, w, x1, x2, false);
                calculatedEdvC(errorVal, dEdu, dEdv, v, y, false, false);
                calculatedEdvAB(dEdu, dEdv, w, x1, x2, true);
                break;
            case 400:
                x1 = Double.parseDouble(args[10]);
                x2 = Double.parseDouble(args[11]);
                y = Double.parseDouble(args[12]);
                calculateV(v, w, x1, x2, false);
                calculatedEdvC(errorVal, dEdu, dEdv, v, y, false, false);
                calculatedEdvAB(dEdu, dEdv, w, x1, x2, false);
                derivativeWrtEdgeWeights(dEdw, x1, x2, v, dEdu, true);
                break;
            case 500:
                x1 = Double.parseDouble(args[10]);
                x2 = Double.parseDouble(args[11]);
                y = Double.parseDouble(args[12]);
                eta = Double.parseDouble(args[13]);
                printWeights(w);
                calculateV(v, w, x1, x2, false);
                calculatedEdvC(errorVal, dEdu, dEdv, v, y, false, true);
                calculatedEdvAB(dEdu, dEdv, w, x1, x2, false);
                derivativeWrtEdgeWeights(dEdw, x1, x2, v, dEdu, false);
                SGD(w, dEdw, eta);
                printWeights(w);
                calculateV(v, w, x1, x2, false);
                calculatedEdvC(errorVal, dEdu, dEdv, v, y, false, true);
                break;
            case 600:
                eta = Double.parseDouble(args[10]);
                trainingUrl = new File("hw2_midterm_A_train.txt").toURI().toURL();
                // trainingUrl = new URL("http://pages.cs.wisc.edu/~jerryzhu/cs540/handouts/hw10files/hw2_midterm_A_train.txt");
                scanner = new Scanner(trainingUrl.openStream());
                for(int j = 1; j <= 67; j++) {
                    x1 = scanner.nextDouble();
                    x2 = scanner.nextDouble();
                    y = scanner.nextDouble();
                    calculateV(v, w, x1, x2, false);
                    calculatedEdvC(errorVal, dEdu, dEdv, v, y, false, false);
                    calculatedEdvAB(dEdu, dEdv, w, x1, x2, false);
                    derivativeWrtEdgeWeights(dEdw, x1, x2, v, dEdu, false);
                    SGD(w, dEdw, eta);
                    printXY(x1, x2, y);
                    printWeights(w);
                    sum = evaluationSetErrors(v, w, true);
                }
                break;
            case 700:
                eta = Double.parseDouble(args[10]);
                times = Double.parseDouble(args[11]);
                trainingUrl = new File("hw2_midterm_A_train.txt").toURI().toURL();
//                trainingUrl = new URL("http://pages.cs.wisc.edu/~jerryzhu/cs540/handouts/hw10files/hw2_midterm_A_train.txt");
                for(epoch = 0; epoch < times; epoch++) {
                    scanner = new Scanner(trainingUrl.openStream());
                    for(int j = 1; j <= 67; j++)
                        sum = calculateWeights(w, v, dEdu, dEdv, dEdw, eta, scanner);
                    printWeights(w);
                    System.out.println(String.format("%.5f", sum));
                }
                break;
            case 800:
                eta = Double.parseDouble(args[10]);
                times = Double.parseDouble(args[11]);
                trainingUrl = new File("hw2_midterm_A_train.txt").toURI().toURL();
//                trainingUrl = new URL("http://pages.cs.wisc.edu/~jerryzhu/cs540/handouts/hw10files/hw2_midterm_A_train.txt");
                testUrl = new File("hw2_midterm_A_test.txt").toURI().toURL();
//                testUrl = new URL("http://pages.cs.wisc.edu/~jerryzhu/cs540/handouts/hw10files/hw2_midterm_A_test.txt");
                for(epoch = 0; epoch < times; epoch++) {
                    scanner = new Scanner(trainingUrl.openStream());
                    for(int j = 1; j <= 67; j++)
                        sum = calculateWeights(w, v, dEdu, dEdv, dEdw, eta, scanner);
                    if(prevSum < sum) break;
                    prevSum = sum;
                }
                System.out.println(epoch + 1);
                printWeights(w);
                System.out.println(String.format("%.5f", sum));
                scanner = new Scanner(testUrl.openStream());
                sum = 0;
                for(int j = 1; j <= 25; j++) {
                    x1 = scanner.nextDouble();
                    x2 = scanner.nextDouble();
                    y = scanner.nextDouble();
                    calculateV(v, w, x1, x2, false);
                    double actualY = v[2] >= 0.5 ? 1 : 0;
                    if(actualY == y) sum++;
                }
                System.out.println(String.format("%.5f", sum / 25));
                break;
        }
    }
}
