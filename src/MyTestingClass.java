public class MyTestingClass {
    private String nickname;
    private int userCode;

    public MyTestingClass(String nickname, int userCode) {
        this.nickname = nickname;
        this.userCode = userCode;
    }


    @Override
    public int hashCode() {
        int result = 13;
        result = 37 * result + userCode;

        int stringHash = 0;
        if (nickname != null) {
            for (char c : nickname.toCharArray()) {
                stringHash = stringHash * 37 + c;
            }
        }
        result = 37 * result + stringHash;

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTestingClass that = (MyTestingClass) o;
        if (userCode != that.userCode) return false;
        return nickname != null ? nickname.equals(that.nickname) : that.nickname == null;
    }
}
