package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_580B {

    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int size = readInt(tokenizer);
        int delta = readInt(tokenizer);

        Friend[] friends = new Friend[size];

        for (int i = 0; i < size; i++) {
            Friend friend = new Friend();
            friend.money = readInt(tokenizer);
            friend.friendship = readInt(tokenizer);
            friends[i] = friend;
        }

        long maxFriendship = calculateMaxFriendship(friends, delta);

        System.out.println(maxFriendship);
    }

    private static long calculateMaxFriendship(Friend[] friends, int delta) {
        Arrays.sort(friends);
        long maxFriendship = 0;

        int first = 0;
        int last = 0;
        long friendship = 0;
        while (last < friends.length) {
            while (last < friends.length && friends[last].money < friends[first].money + delta) {
                friendship += friends[last].friendship;
                last++;
            }

            if (friendship > maxFriendship) {
                maxFriendship = friendship;
            }

            friendship -= friends[first].friendship;
            first++;
        }
        return maxFriendship;
    }

    private static int readInt(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private static class Friend implements Comparable<Friend> {
        private int money;
        private int friendship;

        @Override
        public int compareTo(Friend o) {
            return money - o.money;
        }
    }

}
