import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Group implements Comparable<Group> {
    ArrayList<Member> member = new ArrayList<Member>();

    @Override
    public int compareTo(Group group) {
        if (this.member.size() > group.member.size()) {
            return 1;
        } else if (this.member.size() < group.member.size()) {
            return -1;
        }
        return 0;
    }
}

class Member {
    Group group = null;
    char data;

    Member(char data) {
        this.data = data;
    }

    public void setGroup(Group group) {
        if (this.group == group) {
            return;
        }
        Group prevGroup = this.group;
        this.group = group;
        this.group.member.add(this);

        if (prevGroup != null) {
            prevGroup.member.remove(this);
            while (prevGroup.member.size() > 0) {
                prevGroup.member.get(0).setGroup(group);
            }
            Main.group.remove(prevGroup);
        }

    }
}

class Main {
    static ArrayList<Group> group = new ArrayList<Group>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int test_case = sc.nextInt();

        ArrayList<ArrayList<Member>> map = new ArrayList<ArrayList<Member>>();

        for (int i = 0; i < test_case; i++) {
            String line = sc.next();
            char[] lineArray = line.toCharArray();

            map.add(new ArrayList<Member>());
            int idxHeight = map.size() - 1;
            ArrayList<Member> currentHeight = map.get(idxHeight);

            for (int j = 0; j < lineArray.length; j++) {
                currentHeight.add(new Member(lineArray[j]));

                if (lineArray[j] == '0') {
                    continue;
                }

                Member currentWidth = currentHeight.get(currentHeight.size() - 1);

                boolean marked = false;

                if (i > 0) {
                    ArrayList<Member> prevCurrentHeight = map.get(idxHeight - 1);
                    Member prevCurrentWidth = prevCurrentHeight.get(j);

                    if (prevCurrentWidth.data == '1') {
                        currentWidth.setGroup(prevCurrentWidth.group);
                        marked = true;
                    }
                }

                if (j > 0) {
                    Member prevCurrentWidth = currentHeight.get(j - 1);

                    if (prevCurrentWidth.data == '1') {
                        currentWidth.setGroup(prevCurrentWidth.group);
                        marked = true;
                    }
                }

                if (!marked) {
                    group.add(new Group());
                    currentWidth.setGroup(group.get(group.size() - 1));
                }
            }
        }

        System.out.println(group.size());
        Collections.sort(group);

        for (int i = 0; i < group.size(); i++) {
            int count = group.get(i).member.size();

            System.out.println(count);
        }
    }
}
