package sungil2023_06_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class BaseBall {
	
	// 난수를 뽑는 메서드
	public static int playGame() throws IOException {
		int x, y, z;
		Random rand = new Random();

		x = Math.abs(rand.nextInt() % 9) + 1;
		do {
			y = Math.abs(rand.nextInt() % 9) + 1;
		} while (y == x);

		do {
			z = Math.abs(rand.nextInt() % 9) + 1;
		} while (z == x || z == y);

		return playGame(x, y, z);
	}

	// 게임을 진행하는 메서드
	public static int playGame(int x, int y, int z) throws IOException {
		int count, strike, ball;

		int[] usr = new int[3];
		int[] com = { x, y, z };

		System.out.printf("%d %d %d\n", x, y, z);
		System.out.println("숫자 야구 게임");

		count = 0;
		do {
			count++;
			System.out.println("\n카운트: " + count);

			do {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String user;

				System.out.print("1번째 숫자: ");
				user = br.readLine();
				usr[0] = Integer.parseInt(user);
				System.out.print("2번째 숫자: ");
				user = br.readLine();
				usr[1] = Integer.parseInt(user);
				System.out.print("3번째 숫자: ");
				user = br.readLine();
				usr[2] = Integer.parseInt(user);

				// 사용자가 입력해야 하는 숫자의 제어, 0 또는 10 이상의 숫자, 같은 숫자 금지
				if (usr[0] == 0 || usr[1] == 0 || usr[2] == 0) {
					System.out.println("0은 입력하지 마세요. 다시 입력해주세요.");
				} else if (usr[0] > 9 || usr[1] > 9 || usr[2] > 9) {
					System.out.println("1부터 9까지의 숫자 중 하나를 입력하세요. 다시 입력해주세요.");
				} else if (usr[0] == usr[1] || usr[0] == usr[2] || usr[1] == usr[2]) {
					System.out.println("모두 다른 숫자를 입력해주세요. 다시 입력해주세요.");
				}
			} while ((usr[0] == 0) || (usr[1] == 0) || (usr[2] == 0) || // 입력하는 값이 0일 경우
					(usr[0] > 9) || (usr[1] > 9) || (usr[2] > 9) || // 입력하는 값이 9보다 큰 숫자일 경우
					(usr[0] == usr[1]) || (usr[0] == usr[2]) || (usr[1] == usr[2])); // 입력하는 값이 서로 같을 경우

			strike = ball = 0;

			// strike 경우의 수
			if (usr[0] == com[0])
				strike++;
			if (usr[1] == com[1])
				strike++;
			if (usr[2] == com[2])
				strike++;

			// ball 경우의 수
			if (usr[0] == com[1])
				ball++;
			if (usr[0] == com[2])
				ball++;
			if (usr[1] == com[0])
				ball++;
			if (usr[1] == com[2])
				ball++;
			if (usr[2] == com[0])
				ball++;
			if (usr[2] == com[1])
				ball++;

			System.out.printf("Strike: %d  Ball: %d", strike, ball);
		} while (strike < 3 && count < 11);

		return count;
	}
	
	// 메서드를 호출하는 메서드
	public static void main(String[] args) throws IOException {
		int result;

		if (args.length == 3) { // 게임실행 주어진 3개인 경우, 각각 정수형으로 형변환을 통해 x,y,z 에저장
			int x = Integer.parseInt(args[0]);
			int y = new Integer(args[1]).intValue();
			int z = Integer.valueOf(args[2]).intValue();
			result = playGame(x, y, z);
		} else {
			result = playGame();
		}

		System.out.println();
		if (result <= 2) {
			System.out.println("참 잘했어요!"); // 시도횟수가 1, 2
		} else if (result <= 5) {
			System.out.println("잘했어요!");// 시도횟수가 3, 4, 5
		} else if (result <= 9) {
			System.out.println("보통이네요!"); // 시도횟수가 6, 7, 8, 9
		} else {
			System.out.println("분발하세요!");// 시도횟수가 10, 11
		}
	}
}
