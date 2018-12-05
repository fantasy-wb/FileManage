package cn.jxufe.web.controller.other;

public class Justy {

    public  static int maxProfit(int[] prices) {
        if(prices.length > 0){
            int first = 0;
            int second = 0;
            int temp = 0;
            int min = prices[0];
            for(int i = 0; i < prices.length; i++){
                if(prices[i] < min){
                    min = prices[i];
                    if(temp != 0){
                        if(temp > first){
                            second = first;
                            first = temp;
                        }

                        else if(temp > second)
                            second = temp;
                    }
                }

                if(prices[i] >= min){
                    temp = prices[i] - min;
                }
            }
            return first + second;
        }
        return 0;
    }

    public static void main(String[] args) {
        int arr[] = {3,3,5,0,0,3,1,4};
        System.out.println(Justy.maxProfit(arr));
    }
}
