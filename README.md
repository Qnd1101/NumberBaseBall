# 숫자 야구 게임

## 자바로 하는 숫자 야구 게임 룰

>
    숫자로 하는 야구 게임

    1. 사용되는 숫자는 0에서 9까지 서로 다른 숫자이다
    (경우에 따라 0은 사용하지 않기도 하며 0이 첫번째 숫자로 
    올 수 없게하는 룰도 있지만 이 게임은 없다)

    2. 숫자는 맞지만 위치가 틀렸을 때는 ball
    숫자와 위치가 맞았을 때는 strike
    숫자와 위치가 전부 **틀리면** Out

    ex) 컴퓨터가 내는 숫자가 [3] [6] [9],  사용자가 내는 숫자가 [6] [3] [9] 이면 
      strike : 1, ball : 2
> 

## 메서드 특징

 
💡 **`playGame()` :** 서로 다른 숫자 3개를 랜덤으로 뽑고, 인수가 있는 **`playGame(,,)`** 에게 랜덤으로 뽑은 3개의 숫자를 넘기는 메서드이다.
 




💡 **`playGame(,,)` :** 게임을 진행하고, 사용자가 입력하는 숫자와 **`playGame()`**에게 매개변수로 넘겨받은 숫자와 비교하여 숫자와 위치가 맞으면? strike 증가, 숫자는 맞지만 위치가 틀렸을 떄는 ball 증가를 시켜주고 최대 시도 횟수는 11번까지 이다. 그리고 **`main()`**에게 **시도 횟수를 return** 해주는 메서드이다.

💡 **`main()` :** 인자(argument)를 받아와 게임을 실행하고, 게임의 결과에 따라 메세지를 출력하는 기능을 수행하는 메서드이다.


## Code

- Java Code
    
    ```java
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
    				usr[0] = new Integer(user).intValue();
    				System.out.print("2번째 숫자: ");
    				user = br.readLine();
    				usr[1] = new Integer(user).intValue();
    				System.out.print("3번째 숫자: ");
    				user = br.readLine();
    				usr[2] = new Integer(user).intValue();
    
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
    			int x = Integer.valueOf(args[0]).intValue();
    			int y = Integer.valueOf(args[1]).intValue();
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
    ```
    
- Code 결과
    
    아래의 코드를 실행하여 인자 값이 3개인 경우에는 각각 정수형으로 형변환을 통해서 x, y, z에 저장하고 `**result**`라는 변수에 인수가 있는 **`playGame(,,)`** 를 호출하여 시도 횟수를 받아오고**,** 인자 값이 3개가 아닌 경우엔  **`playGame()`**를 호출하여 시도 횟수를 받아온다.
    
    ```java
    if (args.length == 3) { // 게임실행 주어진 3개인 경우, 각각 정수형으로 형변환을 통해 x,y,z 에저장
    			int x = Integer.parseInt(args[0]);
    			int y = new Integer(args[1]).intValue();
    			int z = Integer.valueOf(args[2]).intValue();
    			result = playGame(x, y, z);
    } else {
    			result = playGame();
    }
    ```
    
    아래의 코드는 실행 결과를 출력하는 코드이다.
    
    ```java
    if (result <= 2) {
    	System.out.println("참 잘했어요!"); // 시도횟수가 1, 2
    } else if (result <= 5) {
    	System.out.println("잘했어요!");// 시도횟수가 3, 4, 5
    } else if (result <= 9) {
    	System.out.println("보통이네요!"); // 시도횟수가 6, 7, 8, 9
    } else {
    	System.out.println("분발하세요!");// 시도횟수가 10, 11
    }
    ```
    
    ### 게임 종료 후 결과 화면
    
    ex ) 카운트 : 1
    
![image](https://github.com/Qnd1101/NumberBaseBall/assets/107795830/ecd7b68c-7703-44d7-90b2-3562a1455d68)

    
- Code 해석
    
    ## **`playGame()`**
    
    ```java
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
    ```
    
    ※ 위 코드는 서로 다른 숫자 3개를 랜덤으로 뽑고, 인수가 있는 **`playGame( , , )` 에 값을 넘겨줌**
    
    1. 정수형 타입의 x, y, z 라는 변수를 생성
    2. Random 클래스의 인스턴스를 생성하고, ‘rand’라는 이름의 변수로 생성 
    3. x는 랜덤한 값을 미리 뽑아둔 후에 do-while문을 통해서 서로 다른 숫자 3개를 뽑는다.
    4. 인수가 있는 **`playGame(,,)`**로 return한다.
    
    ## **`playGame( , , )`**
    
    ```java
    public static int playGame(int x, int y, int z){
    		int count, strike, ball;
    
    		int[] usr = new int[3];
    		int[] com = { x, y, z };
    		System.out.printf("%d %d %d\n", x, y, z);
    		System.out.println("숫자 야구 게임");
    
    		count = 0;
    }
    ```
    
    ※ 위 코드는 변수를 생성하는 코드이다.
    
    정수형 타입의 count, strike, ball 변수 생성
    
    사용자가 입력한 숫자를 집어넣을 정수형 타입의 usr 배열과
    파라미터 값으로 집어넣는 com 배열을 생성한다. 
    
    usr 배열은 크기를 3으로 지정하고,
    com 배열은 매개변수를 통해서 값을 넣는다.
    
    ```java
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
    ```
    
    ※ 위 코드는 사용자에게 입력을 받아서 usr배열에 넣는 **`do-while`**문인데 조건이 있다.
    
    입력한 값이 0일 경우, 9보다 큰 숫자일 경우,
    1번째 입력과 2번째 입력이 같을 경우 혹은
    1번째 입력과 3번째 입력이 같을 경우 혹은
    2번째 입력과 3번째 입력이 같은 경우엔 **`do-while`**문을 다시 반복한다.
    
    ```java
    do {
    	count++;
    	System.out.println("\n카운트: " + count);	
    
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
    ```
    
    ※ 위 코드는 strike와 ball을 카운트 하는 기능을 가지고 있고, 시도 횟수를 **`main()`**넘겨준다.
    
    결과적으로 위 코드 내용을 합치면 아래와 같은 코드가 완성된다.
    
    - 합친 코드 (주석 있음)
        
        ```java
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
        ```
        
    - 합친 코드 (주석 없음)
        
        ```java
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
        
        				if (usr[0] == 0 || usr[1] == 0 || usr[2] == 0) {
        					System.out.println("0은 입력하지 마세요. 다시 입력해주세요.");
        				} else if (usr[0] > 9 || usr[1] > 9 || usr[2] > 9) {
        					System.out.println("1부터 9까지의 숫자 중 하나를 입력하세요. 다시 입력해주세요.");
        				} else if (usr[0] == usr[1] || usr[0] == usr[2] || usr[1] == usr[2]) {
        					System.out.println("모두 다른 숫자를 입력해주세요. 다시 입력해주세요.");
        				}
        			} while ((usr[0] == 0) || (usr[1] == 0) || (usr[2] == 0) || 
        					(usr[0] > 9) || (usr[1] > 9) || (usr[2] > 9) || 
        					(usr[0] == usr[1]) || (usr[0] == usr[2]) || (usr[1] == usr[2])); 
        
        			strike = ball = 0;
        
        			if (usr[0] == com[0])
        				strike++;
        			if (usr[1] == com[1])
        				strike++;
        			if (usr[2] == com[2])
        				strike++;
        
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
        ```
        
    
    ## **`main()`**
    
    ```java
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
    ```
    
    ※ 위 코드는 게임의 결과에 따라 메시지를 출력하는 기능을 수행한다.
