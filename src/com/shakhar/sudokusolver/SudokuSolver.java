package com.shakhar.sudokusolver;

import java.util.Scanner;

public class SudokuSolver {
	private int[][] sudoku;
	private boolean solved;

	public SudokuSolver() {
		sudoku = new int[9][9];
		solved = false;
	}

	public void input() {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			String s = sc.nextLine();
			Scanner sc1 = new Scanner(s);
			for (int j = 0; j < 9; j++)
				sudoku[i][j] = sc1.nextInt();
			sc1.close();
		}
		sc.close();
	}

	public boolean isValid() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (sudoku[i][j] != 0
						&& !isValidDigit(sudoku, i, j, sudoku[i][j]))
					return false;
		return true;
	}

	public void solve() {
		solve(sudoku);
	}

	public boolean isSolved() {
		return solved;
	}

	private void print(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++)
				System.out.print(mat[i][j] + " ");
			System.out.println();
		}
	}

	private void solve(int[][] mat) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (mat[i][j] == 0) {
					for (int k = 1; k < 10; k++)
						if (isValidDigit(mat, i, j, k)) {
							mat[i][j] = k;
							solve(mat);
							mat[i][j] = 0;
						}
					return;
				}
		System.out.println("Solution:");
		print(mat);
		solved = true;
	}

	private boolean isValidDigit(int[][] mat, int r, int c, int k) {
		for (int i = 0; i < 9; i++)
			if (mat[r][i] == k && i != c)
				return false;
		for (int i = 0; i < 9; i++)
			if (mat[i][c] == k && i != r)
				return false;
		for (int i = r / 3 * 3; i < r / 3 * 3 + 3; i++)
			for (int j = c / 3 * 3; j < c / 3 * 3 + 3; j++)
				if (mat[i][j] == k && !(i == r && j == c))
					return false;
		return true;
	}

	public static void main(String[] args) {
		SudokuSolver ss = new SudokuSolver();
		System.out.println("Enter Sudoku Puzzle:");
		ss.input();
		if (ss.isValid()) {
			ss.solve();
			if (!ss.isSolved())
				System.out.println("No Solution.");
		} else
			System.out.println("Invalid Puzzle.");
	}
}
