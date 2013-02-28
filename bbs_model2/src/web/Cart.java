package web;
import java.util.LinkedList;

public class Cart {

	private LinkedList<String> codeList = new LinkedList<String>();//상품코드
	private LinkedList<Integer> numberList = new LinkedList<Integer>();//수량
	
	public void addItem(String code, int num){
		//장바구니에 책 정보를 추가 
		for(int cnt = 0; cnt < codeList.size(); cnt++)
		{
			if(codeList.get(cnt).equals(code)){
				numberList.set(cnt, numberList.get(cnt)+num);
				return;
			}
			
		}
		
		codeList.add(code);
		numberList.add(num);
	
	}
	
	public String getCode(int index){
		return codeList.get(index);
		
	}
	public int getNumber(int index){
		return numberList.get(index);
	}
	
	public int getSize(){
		return codeList.size();
	}
	
	
}
