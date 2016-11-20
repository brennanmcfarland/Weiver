/*
    this class doesn't exist
    move on, nothing to see
*/
public class SecretClass {
    public SecretClass() {
        int x = 76;
        int y = 68;
        int z = 1;
        StringBuilder sb = new StringBuilder();
        sb.append((char)(x));
        sb.append((char)(y));
        sb.insert(y%z, "UU");
        sb.insert(2,(char)(y+z));
        sb.insert(3,"SS");
        sb.insert(3,(char)(y+3));
        sb.insert(5,(char)(x-3));
        sb.insert(7,(char)(y));
        sb.insert(9,(char)(x+z));
    }
}
