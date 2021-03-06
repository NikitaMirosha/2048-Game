package mirosha.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Leaders { // ����� ��� ��������� ������ �����������

	private static Leaders leads;
	private String path; // ���� � �����
	private String highScores;
	
	// ������ ���� �� ��� ����� 
	private ArrayList<Integer> topScores;
	private ArrayList<Integer> topCubes;
	
	private Leaders() {
		try {
			path = new File("").getAbsolutePath(); // ���������� ���� � �����
			System.out.println(path);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		highScores = "Scores";
		topScores = new ArrayList<Integer>();
		topCubes = new ArrayList<Integer>();
	}
	
	public static Leaders getInstance() { // ��������� ���������, ���� ��� ���
		if(leads == null){
			leads = new Leaders();
		}
		return leads;
	}
	
	public void addScore(int score) { // ��������� ������ ����
		for(int i = 0; i < topScores.size(); i++) {
			if(score >= topScores.get(i)) {
				topScores.add(i, score);
				topScores.remove(topScores.size() - 1);
				return;
			}
		}
	}

	public void addCube(int cubeValue) { // ��������� ������ ���
		for(int i = 0; i < topCubes.size(); i++) {
			if(cubeValue >= topCubes.get(i)) {
				topCubes.add(i, cubeValue);
				topCubes.remove(topCubes.size() - 1);
				return;
			}
		}
	}
	
	private void formSaveData() { // ��������� ������
		try {
			File file = new File(path, highScores);
			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write("0-0-0-0-0"); // �������� ������
			writer.newLine();
			writer.write("0-0-0-0-0");
			writer.newLine(); // ������������� ������������ �������� �����
			writer.write(Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE + "-" + Integer.MAX_VALUE);
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void saveScores() { // ��������� ���� �� ����
		FileWriter output = null;
		try {
			File file = new File(path, highScores);
			output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			// ��������� ����� ��������� ����� ������ � ������ �����������, �������� ��������
			writer.write(topScores.get(0) + "-" + topScores.get(1) + "-" + topScores.get(2) + "-" + topScores.get(3) + "-" + topScores.get(4));
			writer.newLine();
			writer.write(topCubes.get(0) + "-" + topCubes.get(1) + "-" + topCubes.get(2) + "-" + topCubes.get(3) + "-" + topCubes.get(4));
			writer.newLine();
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void loadScores() { // ��������� ���� � �������
		try {
			File file = new File(path, highScores);
			if (!file.isFile()) {
				formSaveData();
			}
			// ������ ����� �� ������ ����� ��������
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			// ������� ���������� ����
			topScores.clear();
			topCubes.clear();
			
			String[] scores = reader.readLine().split("-");
			String[] cubes = reader.readLine().split("-");

			for (int i = 0; i < scores.length; i++) {
				topScores.add(Integer.parseInt(scores[i]));
			}
			for (int i = 0; i < cubes.length; i++) {
				topCubes.add(Integer.parseInt(cubes[i]));
			}
	
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// ������� ������ �����������:
	public ArrayList<Integer> getTopScores() {
		return topScores;
	}

	public ArrayList<Integer> getTopCubes() {
		return topCubes;
	}

	public int getHighScore() {
		return topScores.get(0);
	}
}
