import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Morse {
    private HashMap<String,String> morse = new HashMap<String,String>();
    private HashMap<String,String> morse_jiami = new HashMap<String,String>();
    private ArrayList<String> jiami = new ArrayList<>();
    private ArrayList<String> jiemi = new ArrayList<>();
    private ArrayList<String> history = new ArrayList<>();
    Morse(){
        init();
        menu();
    }
    private void menu(){
        System.out.println("[1]加密");
        System.out.println("[2]解密");
        System.out.println("[3]历史记录");
        System.out.println("[0]退出");
        Scanner line = new Scanner(System.in);
        int n = Integer.parseInt(line.nextLine());
        while (n!=0){
            switch (n){
                case 1:{
                    String ll = line.nextLine();
                    jiami.add(ll);
                    history.add("jiami^"+ll);
                    System.out.println(jiami(ll));
                    break;
                }
                case 2:{
                    String ll = line.nextLine();
                    jiemi.add(ll);
                    history.add("jiemi^"+ll);
                    System.out.println(jiemi(ll));
                    break;
                }
                case 3:{
                    showHistory();
                    break;
                }
                default:{
                    break;
                }
            }
            n = Integer.parseInt(line.nextLine());
        }
    }
    private void init(){
        morse.put(".-","A");
        morse.put("-...","B");
        morse.put("-.-.","C");
        morse.put("-..","D");
        morse.put(".","E");
        morse.put("..-.","F");
        morse.put("--.","G");
        morse.put("....","H");
        morse.put("..","I");
        morse.put(".---","J");
        morse.put("-.-","K");
        morse.put(".-..","L");
        morse.put("--","M");
        morse.put("-.","N");
        morse.put("---","O");
        morse.put(".--.","P");
        morse.put("--.-","Q");
        morse.put(".-.","R");
        morse.put("...","S");
        morse.put("-","T");
        morse.put("..-","U");
        morse.put("...-","V");
        morse.put(".--","W");
        morse.put("-..-","X");
        morse.put("-.--","Y");
        morse.put("--..","Z");//
        morse.put("-----","0");
        morse.put(".----","1");
        morse.put("..---","2");
        morse.put("...--","3");
        morse.put("....-","4");
        morse.put(".....","5");
        morse.put("-....","6");
        morse.put("--...","7");
        morse.put("---..","8");
        morse.put("----.","9");//
        morse.put(".-.-.-",".");
        morse.put("---...",":");
        morse.put("--..--",",");
        morse.put("-.-.-.",";");
        morse.put("..--..","?");
        morse.put("-...-","=");
        morse.put(".----.","'");
        morse.put("-..-.","/");
        morse.put("-.-.--","!");
        morse.put("-....-","-");
        morse.put("..--.-","_");
        morse.put(".-..-.","\"");
        morse.put("-.--.","(");
        morse.put("-.--.-",")");
        morse.put("...-..-","$");//&	． ．．．
        morse.put(".--.-.","@");
        add();
    }
    private void add(){
        int len = morse.size();
        String[] keys = new String[len];
        String[] values = new String[len];
        int i=0;
        for(String str:morse.keySet()){
            keys[i] = morse.get(str);
            values[i] = str;
            i++;
        }
        for(int j=0;j<len;j++)
            morse_jiami.put(keys[j],values[j]);
//      Set<String> keys_set = morse.keySet();
//      Iterator<String> itor =  keys_set.iterator();
//      while (itor.hasNext()) {
//          String key = itor.next();
//          String value = morse.get(key);
//          morse.put(value, key);
//      }
//      for(String key:keys
//          morse.put(morse.get(key),key);
    }
    private String trans(String letter){
        if(letter!=null&& !letter.equals("")) {
            letter = letter.toUpperCase();
            if(morse.get(letter)!=null)
                return morse.get(letter);
            else
                return letter;
        }
        else{
            return "";
        }
    }
    private String trans_jiami(String letter){
        if(letter!=null&& !letter.equals("")) {
            letter = letter.toUpperCase();
            if(morse_jiami.get(letter)!=null)
                return morse_jiami.get(letter);
            else
                return letter;
        }
        else{
            return "";
        }
    }
    public String word(String word){
        StringBuffer str= new StringBuffer();
        char[] chars = word.toCharArray();
        for(char a:chars){
            str.append(trans(String.valueOf(a))+" ");
            //System.out.println(a);
        }
        return str.toString();
    }
    public String word_jiami(String word){
        StringBuffer str= new StringBuffer();
        char[] chars = word.toCharArray();
        for(char a:chars){
            str.append(trans_jiami(String.valueOf(a))+" ");
            //System.out.println(a);
        }
        return str.toString();
    }
    public String jiami(String sentence){
        StringBuffer str= new StringBuffer();
        StringTokenizer stringTokenizer = new StringTokenizer(sentence," ");
        while (stringTokenizer.hasMoreTokens()){
            str.append(word_jiami(stringTokenizer.nextToken())+"");//是否加空格
        }
        return str.toString();
    }
    public String jiemi(String sentence){
        sentence = sentence+"/";
        StringBuffer str = new StringBuffer();
        StringTokenizer stringTokenizer = new StringTokenizer(sentence," ");
        while (stringTokenizer.hasMoreTokens()){
            StringBuffer word = new StringBuffer();
            char[] c =stringTokenizer.nextToken().toCharArray();
            int i=0;
            for(;i<c.length;i++){
                if(c[i]=='.'||c[i]=='-')
                    word.append(c[i]);
                else{
                    str.append(trans(word.toString()));
                    str.append(c[i]);
                    word.setLength(0);
                }
            }
            str.append(trans(word.toString()));
        }
        str.deleteCharAt(str.length()-1);
        return str.toString();
    }
    public HashMap<String,String> getMorse(){
        return morse;
    }
    public void showMorse(){
        for (String m:morse.keySet()){
            System.out.println(m+morse.get(m));
        }
    }
    public void showHistory(){
        for (int i=0;i<history.size();i++) {
            if(history.get(i).startsWith("jiami")) {
                String str = history.get(i).substring(6);
                System.out.println(str+" → "+jiami(str));
            }
            else if(history.get(i).startsWith("jiemi")){
                String str = history.get(i).substring(6);
                System.out.println(str+" → "+jiemi(str));
            }
            else
                System.out.println("系统错误");
        }
    }
    public static void main(String[] args) {
        Morse test = new Morse();
//        System.out.println(test.trans("A"));
//        System.out.println(test.jiami("hi could you and!"));
//        System.out.println(test.jiemi(".--//.--"));
//        System.out.println(test.jiemi(".-.-.-ssfsfs.-fs-fsf-sf-.s-.ss.-s-fs-.--"));
//        System.out.println(test.jiemi("-....- -....-"));
//        System.out.println(test.jiami("i love you"));
    }
}
