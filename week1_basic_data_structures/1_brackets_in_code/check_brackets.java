import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Bracket {
    char type;
    int position;

    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }


}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        Stack<Bracket> openBrackets= new Stack<Bracket>();
        for(int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if( ch == '(' || ch == '{' || ch == '[') {
                openBrackets.push(new Bracket(ch,i));
            } else if (ch == ')'|| ch == '}' || ch == ']') {
                if(! openBrackets.isEmpty()) {
                    Bracket b = openBrackets.pop();
                    if(!b.match(ch)) {
                        System.out.println(i + 1);
                        return;
                    }
                } else {
                    System.out.println(i+1);
                    return;
                }

            }
        }

        if(!openBrackets.isEmpty()) {
            System.out.println(openBrackets.pop().position + 1);
        } else {
            System.out.println("Success");
        }
    }
}
