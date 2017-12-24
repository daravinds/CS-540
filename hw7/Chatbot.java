import java.util.*;
import java.io.*;

class Segment {
	int index;
	double left;
	double right;
	
	public Segment(int i, int l, int r, int n) {
		index = i;
		left = (double)l / n;
		right = (double)r / n;
	}
	
	public Segment(int i, double l, double r) {
		index = i;
		left = l;
		right = r;
	}
}

public class Chatbot {
	private static String filename = "./WARC201709_wid.txt";
	private static int numberOfTokens = 4700;
	private static ArrayList<Integer> readCorpus(){
		ArrayList<Integer> corpus = new ArrayList<Integer>();
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			while(sc.hasNext()){
				if(sc.hasNextInt())
					corpus.add(sc.nextInt());
				else
					sc.next();
			}
		}
		catch(FileNotFoundException ex){
			System.out.println("File Not Found.");
		}
		return corpus;
	}
	
	public static int[] buildUnigramFrequency(ArrayList<Integer> corpus) {
		int[] tokenFrequency = new int[numberOfTokens];
		for(int i = 0; i < corpus.size(); i++) {
			int currentWord = corpus.get(i);
			tokenFrequency[currentWord]++;
		}
		return tokenFrequency;
	}
	
	public static int[] buildBigramFrequency(ArrayList<Integer> corpus, int history) {
		int[] tokenFrequency = new int[numberOfTokens];
		for(int i = 0; i < corpus.size() - 1; i++) {
			int currentWord = corpus.get(i);
			int nextWord = corpus.get(i + 1);
			if(currentWord == history)
				tokenFrequency[nextWord]++;
		}
		return tokenFrequency;
	}

	public static int[] buildTrigramFrequency(ArrayList<Integer> corpus, int history_1, int history_2) {
		int[] tokenFrequency = new int[numberOfTokens];
		for(int i = 0; i < corpus.size() - 2; i++) {
			int currentWord = corpus.get(i);
			int nextWord_1 = corpus.get(i + 1);
			int nextWord_2 = corpus.get(i + 2);
			if(currentWord == history_1 && nextWord_1 == history_2)
				tokenFrequency[nextWord_2]++;
		}
		return tokenFrequency;
	}

	public static int countToken(int[] tokenFrequency, int token) {
		return tokenFrequency[token];
	}
	
	public static Segment getSegment(int[] tokenFrequency, double randomNum) {
		int total = 0;
		for(int i = 0; i < tokenFrequency.length; i++)
			total += tokenFrequency[i];

		int segmentSum = 0;

		for(int i = 0; i < tokenFrequency.length; i++) {
			if(i != 0)	segmentSum += tokenFrequency[i - 1];
			if(tokenFrequency[i] != 0) {
				double leftLimit = (double) segmentSum / total;
				double rightLimit = (double) (segmentSum + tokenFrequency[i]) / total;
				if(randomNum == 0) {
					if((leftLimit <= randomNum) && (rightLimit > randomNum))
						return new Segment(i, segmentSum, segmentSum + tokenFrequency[i], total);
				}
				else {
					if((leftLimit < randomNum) && (rightLimit >= randomNum))
						return new Segment(i, segmentSum, segmentSum + tokenFrequency[i], total);
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);

		if(flag == 100) {
			int w = Integer.valueOf(args[1]);
			int[] tokenFrequency = buildUnigramFrequency(corpus);
			int count = countToken(tokenFrequency, w);

			System.out.println(count);
			System.out.println(String.format("%.7f",count / (double) corpus.size()));
		}
		else if(flag == 200){
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			double randomNum = (double) n1 / n2;
			int[] tokenFrequency = buildUnigramFrequency(corpus);
			Segment segment = getSegment(tokenFrequency, randomNum);

			System.out.println(segment.index);
			System.out.println(String.format("%.7f", segment.left));
			System.out.println(String.format("%.7f", segment.right));
		}
		else if(flag == 300){
			int h = Integer.valueOf(args[1]);
			int w = Integer.valueOf(args[2]);
			int[] tokenFrequency = buildBigramFrequency(corpus, h);
			int count = countToken(tokenFrequency, w);
			int word_count_after_h = 0;
			for(int wordCount: tokenFrequency)	word_count_after_h += wordCount;

			System.out.println(count);
			System.out.println(word_count_after_h);
			System.out.println(String.format("%.7f", count / (double) word_count_after_h));
		}
		else if(flag == 400){
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h = Integer.valueOf(args[3]);
			double randomNum = (double) n1 / n2;
			int[] tokenFrequency = buildBigramFrequency(corpus, h);		
			Segment segment = getSegment(tokenFrequency, randomNum);

			System.out.println(segment.index);
			System.out.println(String.format("%.7f", segment.left));
			System.out.println(String.format("%.7f", segment.right));
		}
		else if(flag == 500){
			int h1 = Integer.valueOf(args[1]);
			int h2 = Integer.valueOf(args[2]);
			int w = Integer.valueOf(args[3]);
			int[] tokenFrequency = buildTrigramFrequency(corpus, h1, h2);
			int count = countToken(tokenFrequency, w);
			int word_count_after_h1_h2 = 0;
			for(int wordCount: tokenFrequency)	word_count_after_h1_h2 += wordCount;

			System.out.println(count);
			System.out.println(word_count_after_h1_h2);
			if(word_count_after_h1_h2 == 0)
				System.out.println("undefined");
			else
				System.out.println(String.format("%.7f", count / (double) word_count_after_h1_h2));
		}
		else if(flag == 600){
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h1 = Integer.valueOf(args[3]);
			int h2 = Integer.valueOf(args[4]);
			double randomNum = (double) n1 / n2;
			int[] tokenFrequency = buildTrigramFrequency(corpus, h1, h2);
			Segment segment = getSegment(tokenFrequency, randomNum);
			
			if(segment == null) {
				System.out.println("undefined");
			} else {
				System.out.println(segment.index);
				System.out.println(String.format("%.7f", segment.left));
				System.out.println(String.format("%.7f", segment.right));
			}
		}
		else if(flag == 700){
			int seed = Integer.valueOf(args[1]);
			int t = Integer.valueOf(args[2]);
			int h1 = 0, h2 = 0;
			Segment segment = null;
			Random rng = new Random();

			if (seed != -1) rng.setSeed(seed);
			if(t == 0){
				double r = rng.nextDouble();
				int[] tokenFrequency = buildUnigramFrequency(corpus);
				segment = getSegment(tokenFrequency, r);
				h1 = segment.index;
				System.out.println(h1);
				if(h1 == 9 || h1 == 10 || h1 == 12)
					return;
				r = rng.nextDouble();
				tokenFrequency = buildBigramFrequency(corpus, h1);
				segment = getSegment(tokenFrequency, r);
				h2 = segment.index;
				System.out.println(h2);
			}
			else if(t == 1){
				h1 = Integer.valueOf(args[3]);
				double r = rng.nextDouble();
				int[] tokenFrequency = buildBigramFrequency(corpus, h1);
				segment = getSegment(tokenFrequency, r);
				h2 = segment.index;
				System.out.println(h2);
			}
			else if(t == 2){
				h1 = Integer.valueOf(args[3]);
				h2 = Integer.valueOf(args[4]);
			}

			while(h2 != 9 && h2 != 10 && h2 != 12){
				double r = rng.nextDouble();
				int w  = 0;
				int[] tokenFrequency = buildTrigramFrequency(corpus, h1, h2);
				segment = getSegment(tokenFrequency, r);
				if(segment == null) {
					tokenFrequency = buildBigramFrequency(corpus, h2);
					segment = getSegment(tokenFrequency, r);
				}
				w = segment.index;
				System.out.println(w);
				h1 = h2;
				h2 = w;
			}
		}
		return;
	}
}