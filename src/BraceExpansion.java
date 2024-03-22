// Time Complexity:  O(k^(n/k))
// Space Complexity: O(n/k)
// where n = total length of given string
//       k = average length of block
//       n/k = number of blocks

public class Main {
    
    public static void main(String[] args) {
        Main obj = new Main();
        System.out.println(Arrays.toString(obj.braceExpansion("{a,b}cd{e,f}g")));
    }
    
    private String[] braceExpansion(String input) {
        
        List<String> result = new ArrayList<>();
        String[] res;
        List<List<Character>> list = new ArrayList<>();
        
        // Getting in List<List<Character>> format for easiness
        for(int i=0; i<input.length(); i++) {
            List<Character> charList = new ArrayList<>();
            char ch = input.charAt(i);
            if(ch == '{') {
                i++;
                while(input.charAt(i) != '}') {
                    if(input.charAt(i) != ',') {
                        charList.add(input.charAt(i));
                    }
                    i++;
                }
            }
            else {
                charList.add(ch);
            }
            list.add(charList);
        }
        
        // calling dfs
        dfs(list, 0, new StringBuilder(), result);
        
        // converting result list to array
        res = new String[result.size()];
        for(int i=0; i<result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }
    
    private void dfs(List<List<Character>> list, int ind, StringBuilder sb, List<String> result) {
        
        // base case
        if(ind == list.size()) {
            result.add(sb.toString());
            return;
        }
        
        // logic
        List<Character> curList = list.get(ind);
        for(int i=0; i<curList.size(); i++) {
            char ch = curList.get(i);
            // action
            sb.append(ch);
            // logic
            dfs(list, ind+1, sb, result);
            // backtrack
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
