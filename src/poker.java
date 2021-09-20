
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class poker {
    public static int[][] cards = { {1,2,3,4,5,6,7,8,9,10,11,12,13},
                                    {1,2,3,4,5,6,7,8,9,10,11,12,13},
                                    {1,2,3,4,5,6,7,8,9,10,11,12,13},
                                    {1,2,3,4,5,6,7,8,9,10,11,12,13}};
    public static int[] p1 = {0,0,0,0,0,0,0};
    public static int[] p1s = {0,0,0,0,0,0,0};
    public static int[] p2 = {0,0,0,0,0,0,0};
    public static int[] p2s = {0,0,0,0,0,0,0};
    public static int[] p3 = {0,0,0,0,0,0,0};
    public static int[] p3s = {0,0,0,0,0,0,0};
    public static int[] p4 = {0,0,0,0,0,0,0};
    public static int[] p4s = {0,0,0,0,0,0,0};
    public static int wins;
    public static int highcard;
    public static int highcard2;
    public static boolean flush;
    public static boolean straight;
    public static int multiple=0;
    public static int lastnum;
    public static int strf = 0;
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("input hand  num suit num suit separatly spade=1 club = 2 heart = 3 diamond 4");
        int n1 = input.nextInt()-1;
        int s1 = input.nextInt()-1;
        int n2 = input.nextInt()-1;
        int s2 = input.nextInt()-1;
        cards[p1s[0]][p1[0]]=0;
        cards[p1s[1]][p1[1]]=0;
        //System.out.println(Arrays.toString(p1));
        IntStream.range(0, 80001).forEach(k -> {
            Arrays.fill(p1, 0);
            Arrays.fill(p1s, 0);
            p1[0] = n1;
            p1[1] = n2;
            p1s[0] = s1;
            p1s[1] = s2;
            Arrays.fill(p2, 0);
            Arrays.fill(p2s, 0);
            Arrays.fill(p3, 0);
            Arrays.fill(p3s, 0);
            Arrays.fill(p4, 0);
            Arrays.fill(p4s, 0);
            if (simulation()) {
                wins++;
            }
            System.out.println(" winns : " + wins + " cylcles : " + k + " strf : " + strf);
        });
        }
        public static boolean simulation(){
            int rand;
            int randsuit;

            int[][] tempcards = new int[4][13];
            //tempcards =cards.clone();//doesn't work for some reason
            for (int y = 0; y < 4; y++){
                for (int x = 0; x < 13; x++){
                    tempcards[y][x] = cards[y][x];
                }
            }
           for(int i = 0; i<2;i++){
               do {
                   rand = (int) ((Math.random() * 4) );
                   randsuit = (int) ((Math.random() * 13) );
               }while (tempcards[rand][randsuit] == 0);
               p2[i]=randsuit;
               p2s[i]=rand;
               tempcards[rand][randsuit] = 0;
           }
            for(int i = 0; i<2;i++){
                do {
                    rand = (int) ((Math.random() * 4) );
                    randsuit = (int) ((Math.random() * 13) );
                }while (tempcards[rand][randsuit] == 0);
                p3[i]=randsuit;
                p3s[i]=rand;
                tempcards[rand][randsuit] = 0;
            }
            for(int i = 0; i<2;i++){
                do {
                    rand = (int) ((Math.random() * 4) );
                    randsuit = (int) ((Math.random() * 13) );
                }while (tempcards[rand][randsuit] == 0);
                p4[i]=randsuit;
                p4s[i]=rand;
                tempcards[rand][randsuit] = 0;
            }
            for (int i = 2; i<7;i++){
                do {
                    rand = (int) ((Math.random() * 4) );
                    randsuit = (int) ((Math.random() * 13));
                }while (tempcards[rand][randsuit] == 0);
                p1[i]=randsuit;
                p1s[i]=rand;
                p2[i]=randsuit;
                p2s[i]=rand;
                p3[i]=randsuit;
                p3s[i]=rand;
                p4[i]=randsuit;
                p4s[i]=rand;
                tempcards[rand][randsuit] = 0;
            }
            return winnings();
        }
        public static boolean winnings(){
            int w1 = checkHand(p1,p1s);
            int w2 = checkHand(p2,p2s);
            int w3 = checkHand(p3,p3s);
            int w4 = checkHand(p4,p4s);
            //System.out.println("Player 1:\n"+Arrays.toString(p1)+"\n"+Arrays.toString(p1s)+"\n"+"Player 2:\n"+Arrays.toString(p2)+"\n"+Arrays.toString(p2s)+"\n"+"Player 3:\n"+Arrays.toString(p3)+"\n"+Arrays.toString(p3s)+"\n"+"Player 4:\n"+Arrays.toString(p4)+"\n"+Arrays.toString(p4s)+"\n");
            //System.out.println("Player 1:"+Arrays.toString(p1)+"\nPlayer 2:"+Arrays.toString(p2)+"\nPlayer 3:"+Arrays.toString(p3)+"\nPlayer 4:"+Arrays.toString(p4));
            if (w1 > 81400 ||w2 > 81400||w3 > 81400||w4 > 81400){
                strf ++;
            }
            System.out.println();
            System.out.println(w1+" "+w2+" "+w3+" "+w4);
            return w1 > w2 && w1 > w3 && w1 > w4;
        }
        public static int checkHand(int[] num, int[]suit){
         flush=false;
         straight =false;
         multiple=0;
         highcard = 0;
         highcard2 = 2;
        Arrays.sort(num);
            Arrays.sort(suit);
        if(straightflush(num,suit)){
            System.out.print("straight flush ");
            return Integer.parseInt(8+""+highcheck(highcard)+highcheck(highcard2));
        }else if (fourkind(num)){
            System.out.print("four kind ");
            return Integer.parseInt(7+""+highcheck(highcard)+highcheck(highcard2));
        }else if (fullhouse(num)){
            System.out.print("full house ");
            return Integer.parseInt(6+""+highcheck(highcard)+highcheck(highcard2));
        }else if (flush(suit)){
            System.out.print("flush ");
            return Integer.parseInt(5+""+highcheck(highcard)+highcheck(highcard2));
        }else if (Straight(num)){
            System.out.print("straight ");
            return Integer.parseInt(4+""+highcheck(highcard)+highcheck(highcard2));
        }else if (threekind(num)){
            System.out.print("three kind ");
            return Integer.parseInt(3+""+highcheck(highcard)+highcheck(highcard2));
        }else if (twoPair(num)){
            System.out.print("two pair ");
            return Integer.parseInt(2+""+highcheck(highcard)+highcheck(highcard2));
        } else if (pair(num)){
            System.out.print("one pair ");
            return Integer.parseInt(1+""+highcheck(highcard)+highcheck(highcard2));
        }
            System.out.print("high card ");
        return high(num);
        }
        public static boolean straightflush(int[] num, int [] suit){
            int count=0;  lastnum=0;
            for (int i = 1; i < 7; i++ ) {

                if(num[i] == num[i-1]+1){
                    count++;
                    //System.out.println(num[i] +" "+ num[i-1]+ " "+ count);
                    if (num[i]== 12 && num[0] == 0) {
                        count++;
                        //System.out.println("num[i] = " + num[i] + " " + count);
                    }
                }else{count = 0; }
                if (count >= 4) {
                    if (num[i]== 12 && num[0] == 0) {
                        highcard = 14;
                        //System.out.println("num[i] = " + num[i] + " " + count);
                    } else { highcard= num[i];}
                    System.out.println(num[i]);

                    lastnum = i;
                    i = 10;
                    straight = true;
                }


            }
            int amount=0,kind=0,tempammount=0;
            for (int i = 0; i <=3;i++){
                for (int p = 0; p < 7; p++){
                    if (suit[p]==i){
                        amount++;
                    }
                }
                if (amount == 5){
                    kind = i;
                    flush = true;
                } else {
                    amount =0;
                }
            }highcard2 =high(num);


            if (straight && flush  && kind == suit[lastnum]){
                return true;
            }else {return false;}


    }
    public static boolean fourkind(int[] num){
        int ammount = 0;
        for (int i = 1; i<=13; i++) {
            for (int p =0; p < 7;p++) {

                if (num[p] == i) {
                    ammount += 1;
                }
                if (ammount==4){
                    if(i ==0){
                        highcard = 14;
                    } else {
                        highcard = i;
                    }

                        highcard2 = high(num);

                    return true;

                }
            }
            ammount = 0;

        }
        return false;

    }
    public static boolean fullhouse(int[] num){
        boolean three = false, two=false;
        int ammount = 0;
        for (int i = 0; i<=13; i++) {
            for (int p =0; p < 7;p++) {

                if (num[p] == i) {
                    ammount += 1;
                }


                }
            if (ammount==3){
                if(i ==0){
                    highcard = 14;
                }else {
                    highcard = i;
                }
                three= true;
                ammount = 0;
            } else if(ammount == 2){
                two = true;
                if(i ==0){
                    highcard2 = 14;
                } else {
                    highcard2 = i;
                }
                ammount = 0;
            }else {
                ammount = 0;
            }


        }
        if(three && two){
            return true;
        }else {
            return false;
        }
    }
    public static boolean flush(int[] suit){
        if (flush){
            return true;
        }else {
            return false;
        }

    }
    public static boolean Straight(int[] num){
        if (straight) {
            return true;
        }else {
            return false;

        }

    }
    public static boolean threekind(int[] num){
        boolean three = false;
        int ammount = 0;
        for (int i = 0; i<=13; i++) {
            for (int p =0; p < 7;p++) {

                if (num[p] == i) {
                    ammount += 1;
                }
                if (ammount==3){
                    if(i ==0){
                        highcard = 14;
                    } else {
                        highcard = i;
                    }
                    highcard2 = high(num);
                    return true;

                }
            }
                    ammount = 0;
        }
        return false;
    }
    public static boolean twoPair(int[] num){
        boolean three1 = false , three2 = false;
        int ammount = 0;
        for (int i = 0; i<=13; i++) {
            for (int p =0; p < 7;p++) {

                if (num[p] == i) {
                    ammount += 1;
                }

            }
            if (ammount==2 && !three1){
                if(i ==0){
                    highcard = 14;
                } else {highcard2 = i;}
                three1 = true;
                ammount = 0;
            }else if (ammount == 2){
                three2 = true;
                if (highcard!= 14) {
                    highcard = i;
                }else {
                    highcard2 = i;
                }

            }
            ammount =0;
        }
        if(three1 && three2){
            return true;
        }else {
            return false;
        }
    }
    public static boolean pair(int[] num){
        int ammount = 0;
        for (int i = 0; i<=13; i++) {
            for (int p =0; p < 7;p++) {

                if (num[p] == i) {
                    ammount += 1;
                }
                if (ammount==2){
                    if (i ==0){
                        highcard =14;
                    }else {
                        highcard = i;
                    }
                    highcard2 = high(num);
                    return true;

                }
            }
            ammount = 0;
        }
        return false;
    }
    public static int high(int[] num){
        if (num[0] == 0){
            return 14 ;
        } else {
            return num[num.length - 1];
        }
    }
    public static String highcheck(int num){
        if (num <10){
            return 0 + "" + num;
        } else{
            return ""+num;
        }
    }



    }


