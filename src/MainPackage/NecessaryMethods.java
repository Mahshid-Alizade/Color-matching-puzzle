package MainPackage;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NecessaryMethods {

	static int helpI = -1;
	static int helpJ = -1 ;
	static ArrayList<String> readArrayList = new ArrayList<String>();

	//this method find neighbour and make them unVisible
	void findNeighbour(int i, int j) {

		SecondPage.notChecked[i][j] = false;

		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i][j + 1].getBackground())
					&& SecondPage.notChecked[i][j + 1]) {

				findNeighbour(i, j + 1);

			}
		} catch (Exception e) {
		}
		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i - 1][j + 1].getBackground())
					&& SecondPage.notChecked[i - 1][j + 1]) {

				findNeighbour(i - 1, j + 1);

			}
		} catch (Exception e) {
		}
		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i - 1][j].getBackground())
					&& SecondPage.notChecked[i - 1][j]) {

				findNeighbour(i - 1, j);

			}
		} catch (Exception e) {
		}
		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i - 1][j - 1].getBackground())
					&& SecondPage.notChecked[i - 1][j - 1]) {

				findNeighbour(i - 1, j - 1);

			}
		} catch (Exception e) {
		}
		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i][j - 1].getBackground())
					&& SecondPage.notChecked[i][j - 1]) {

				findNeighbour(i, j - 1);

			}
		} catch (Exception e) {
		}
		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i + 1][j - 1].getBackground())
					&& SecondPage.notChecked[i + 1][j - 1]) {

				findNeighbour(i + 1, j - 1);

			}
		} catch (Exception e) {
		}
		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i + 1][j].getBackground())
					&& SecondPage.notChecked[i + 1][j]) {

				findNeighbour(i + 1, j);

			}
		} catch (Exception e) {
		}
		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i + 1][j + 1].getBackground())
					&& SecondPage.notChecked[i + 1][j + 1]) {

				findNeighbour(i + 1, j + 1);

			}
		} catch (Exception e) {
		}

		SecondPage.alizade[i][j].setVisible(false);

	}

	//////////////////////////////////////////////////////////////////////////////////////////

	//check button has more than 3 neighbour
	static boolean checkMoreThan3(int i, int j) {
		// SecondPage.moreThan3 = 1;
		SecondPage.notChecked[i][j] = false;
		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i][j + 1].getBackground()
					&& SecondPage.alizade[i][j + 1].isVisible()) && SecondPage.notChecked[i][j + 1]) {
				SecondPage.moreThan3++;

				checkMoreThan3(i, j + 1);
			}

		} catch (Exception e) {
		}

		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i - 1][j + 1].getBackground()
					&& SecondPage.alizade[i - 1][j + 1].isVisible()) && SecondPage.notChecked[i - 1][j + 1]) {
				SecondPage.moreThan3++;

				checkMoreThan3(i - 1, j + 1);
			}

		} catch (Exception e) {
		}

		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i - 1][j].getBackground())
					&& SecondPage.alizade[i - 1][j].isVisible() && SecondPage.notChecked[i - 1][j]) {
				SecondPage.moreThan3++;

				checkMoreThan3(i - 1, j);
			}
		} catch (Exception e) {
		}

		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i - 1][j - 1].getBackground())
					&& SecondPage.alizade[i - 1][j - 1].isVisible() && SecondPage.notChecked[i - 1][j - 1]) {
				SecondPage.moreThan3++;

				checkMoreThan3(i - 1, j - 1);
			}
		} catch (Exception e) {
		}

		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i][j - 1].getBackground())
					&& SecondPage.alizade[i][j - 1].isVisible() && SecondPage.notChecked[i][j - 1]) {
				SecondPage.moreThan3++;

				checkMoreThan3(i, j - 1);
			}
		} catch (Exception e) {
		}

		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i + 1][j - 1].getBackground())
					&& SecondPage.alizade[i + 1][j - 1].isVisible() && SecondPage.notChecked[i + 1][j - 1]) {
				SecondPage.moreThan3++;

				checkMoreThan3(i + 1, j - 1);
			}
		} catch (Exception e) {
		}

		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i + 1][j].getBackground())
					&& SecondPage.alizade[i + 1][j].isVisible() && SecondPage.notChecked[i + 1][j]) {
				SecondPage.moreThan3++;

				checkMoreThan3(i + 1, j);
			}
		} catch (Exception e) {
		}

		try {
			if ((SecondPage.alizade[i][j].getBackground() == SecondPage.alizade[i + 1][j + 1].getBackground())
					&& SecondPage.alizade[i + 1][j + 1].isVisible() && SecondPage.notChecked[i + 1][j + 1]) {
				SecondPage.moreThan3++;

				checkMoreThan3(i + 1, j + 1);
			}
		} catch (Exception e) {
		}

		if (SecondPage.moreThan3 >= 4)
			return true;
		else
			return false;

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	public void firstGravity(int i, int j) {

		for (int ii = i; ii >= 0; ii--) {

			try {
				if (!SecondPage.alizade[ii][j].isVisible() && SecondPage.alizade[ii - 1][j].isVisible()) {
					SecondPage.alizade[ii][j].setBackground(SecondPage.alizade[ii - 1][j].getBackground());
					SecondPage.alizade[ii][j].setVisible(true);
					SecondPage.alizade[ii - 1][j].setVisible(false);
				}
			} catch (Exception e) {
			}
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	public void secondGraviti(int i, int j) {

		for (int jj = j; jj < FirstPage.getM(); jj++) {
			try {
				if (SecondPage.alizade[i][jj + 1].isVisible()) {

					SecondPage.alizade[i][jj].setBackground(SecondPage.alizade[i][jj + 1].getBackground());
					SecondPage.alizade[i][jj].setVisible(true);
					SecondPage.alizade[i][jj + 1].setVisible(false);

				}
			} catch (Exception e) {
			}

		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	//get seconds
	static long getTime(String s) {

		int second = Integer.parseInt(s.split(" : ")[2].trim());
		int minute = Integer.parseInt(s.split(" : ")[1].trim());
		int hour = Integer.parseInt(s.split(" : ")[0].trim());

		second += minute * 60 + hour * 3600;

		return second;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	static int getRecord(Boolean enterTheGame) {

		int record;
		int a = FirstPage.getM() * FirstPage.getN();
		int c = Integer.parseInt(SecondPage.cLabel.getText());
		int t = 0;
		if (enterTheGame)
			t = (int) getTime(SecondPage.timeLabel.getText());
		int h = deletedButton(FirstPage.getN(), FirstPage.getM());

		record = (int) ((a * c) / (h * (Math.pow(t, 1 / 3))));

		return record;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	//count deleted method
	static int deletedButton(int i, int j) {

		int counter = 0;

		for (int ii = 0; ii < i; ii++) {
			for (int jj = 0; jj < j; jj++) {
				if (!SecondPage.alizade[ii][jj].isVisible())
					counter++;
			}
		}

		return counter;

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean isLoss(int x) {

		if (x == 0)
			return true;
		else
			return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean isWin(int x, int y) {

		if (x == y)
			return true;
		else
			return false;

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	static void sendToFile(String sendToFile) {
		BufferedWriter output = null;

		try {
			File file = new File("Records.txt");
			output = new BufferedWriter(new FileWriter(file, true));
			output.write(sendToFile);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException e) {
				}
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean isLoss() {

		boolean yesThereIs = false;

		here: for (int i = 0; i < FirstPage.getN(); i++) {
			for (int j = 0; j < FirstPage.getM(); j++) {

				SecondPage.moreThan3 = 1;
				NecessaryMethods.makeAllTrue();

				if (SecondPage.alizade[i][j].isVisible() && checkMoreThan3(i, j)) {
					
					helpI = i;
					helpJ = j;
					yesThereIs = true;
					break here;
				}
			}
		}

		if (yesThereIs)
			return false;
		else {
			helpI = -1;
			helpJ = -1;
			return true;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	static void makeAllTrue() {

		for (int ii = 0; ii < FirstPage.getN(); ii++) {
			for (int jj = 0; jj < FirstPage.getM(); jj++) {

				SecondPage.notChecked[ii][jj] = true;

			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean checkLoss() {
		if (NecessaryMethods.isLoss(Integer.parseInt(SecondPage.pLabel.getText())) || NecessaryMethods.isLoss()) {
			return true;
		} else
			return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean checkWin() {
		if (NecessaryMethods.isWin(Integer.parseInt(SecondPage.hLabel.getText()), FirstPage.getM() * FirstPage.getN()))
			return true;
		else
			return false;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	static boolean no = false;
	static void readFromFile() throws IOException {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("Records.txt"));
			
			for(int i = 0 ; i < NecessaryMethods.readArrayList.size() ; i++)	
				NecessaryMethods.readArrayList.removeAll(readArrayList);
			
			String c ;
		while((c = in.readLine()) != null ) {
		
				readArrayList.add(c);
			}
			
		if(readArrayList.isEmpty())
			no = true;
		
		} catch (FileNotFoundException e) {
			no = true;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
