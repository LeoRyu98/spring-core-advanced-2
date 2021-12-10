package hello.proxy.app.v2;

public class OrderRepositoryV2 {

    public void save(String itemId) {
        //저장 로직
        if(itemId.equals("ex")){ // 만약 상품의 아이디가 "ex" 이면 문제 발생
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000); // 상품 저장 소요 시간 지정(1초)
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
