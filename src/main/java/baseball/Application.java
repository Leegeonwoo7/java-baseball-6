package baseball;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args){
    	System.out.println("숫자 야구 게임을 시작합니다.");
    	List<Integer> computerNum = setComputerNum();
    	System.out.println(computerNum);
    	while(true) {
    	List<Integer> userNum = inputUserNum();
    	if(computerNum.equals(userNum)) {  //정답일경우
    		System.out.println("3스트라이크");
    		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    		int isReplay = Integer.parseInt(Console.readLine());
    		if(isReplay == 1) {
    			computerNum = setComputerNum(); //리플레이를 위한 computerNum
    			continue;
    		}
    		else break;
    	}
    	checkNum(computerNum,userNum);
    	}
    }
    
    //상대방 슷자 셋팅
    public static List<Integer> setComputerNum(){
    	List<Integer> computerNum = new ArrayList<>();
    	while (computerNum.size() < 3) {
    	    int randomNumber = Randoms.pickNumberInRange(1, 9);
    	    if (!computerNum.contains(randomNumber)) {
    	        computerNum.add(randomNumber);
    	    }
    	}
    	return computerNum;
    }
    
    //사용자 숫자 입력 *중복값 제거(setComputerNum참고)
    public static List<Integer> inputUserNum(){
    	System.out.print("숫자를 입력해주세요 : ");
    	List<Integer> userNum = new ArrayList<>();
    	int tmpUserNum = Integer.parseInt(Console.readLine());
    	if(tmpUserNum<100||tmpUserNum>999) {
    		throw new IllegalArgumentException();
    	}
    	userNum.add(tmpUserNum/100);
    	userNum.add(tmpUserNum/10%10);
    	userNum.add(tmpUserNum%10);
    	return userNum;
    }
    
    public static int[] checkNum(List<Integer> computerNum, List<Integer> userNum){
    	int countArr[] = new int[2];
    	int ballCount = 0;
    	int strikeCount = 0;
    	
    	if(!computerNum.contains(userNum.get(0))&&!computerNum.contains(userNum.get(1))&&!computerNum.contains(userNum.get(2))){
    		System.out.println("낫싱");
    	}else {
    		for(int i=0; i<3; i++) {
    			if(computerNum.get(i).equals(userNum.get(i))) {
    				strikeCount++;
    			}else if(computerNum.contains(userNum.get(i))){ 
    				ballCount++;
    			}
    		}
    		if(ballCount>0) {
    			System.out.print(ballCount + "볼 ");
    		}
    		if(strikeCount>0) {
    			System.out.print(strikeCount + "스트라이크 ");
    		}
    		countArr[0] = ballCount;
    		countArr[1] = strikeCount;
    	}
    	return countArr;
}
}