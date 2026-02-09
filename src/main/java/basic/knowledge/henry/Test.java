package basic.knowledge.henry;

class Test {
    public static void main(String[] args) {
        boolean b = new Test().splitString("1234");
        System.out.println(b);
    }


    public boolean splitString(String s) {
        for(int i = 0;i< s.length();i++){
            String d = s.substring(0,i+1);
            if(dfs(d,s.substring(i + 1))){
                return true;
            }
        }

        return false;
    }

    private boolean dfs(String pre,String s){
        if(s.equals("")){
            return true;
        }
        int p = Integer.valueOf(pre);
        for(int i = 0;i< s.length();i++){
            String cur = s.substring(0,i+1);
            int c = Integer.valueOf(cur);
            if(p -c != 1){
                continue;
            }

            if(dfs(cur,s.substring(i + 1))){
                return true;
            }
        }

        return false;
    }

}