package org.pti.poster.dao.basic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang.RandomStringUtils;
import org.pti.poster.model.Constants;
import org.pti.poster.model.Person;
import org.pti.poster.model.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestData {

    public static List<Post> mockPosts() {

        return Arrays.asList(
                new Post("first post", Lists.newArrayList(Tags.tagFourth, Tags.tagFirst)),
                new Post("second post", Lists.newArrayList(Tags.tagFourth)),
                new Post("third post", Lists.newArrayList(Tags.tagFifth, Tags.tagSecond, Tags.tagFirst)),
                new Post("fourth post", Lists.newArrayList(Tags.tagFourth, Tags.tagFirst)),
                new Post("fifth post", Lists.newArrayList(Tags.tagFourth, Tags.tagFirst)),
                new Post("sixth post", Lists.newArrayList(Tags.tagFifth, Tags.tagZero, Tags.tagThird, Tags.tagFirst)));
    }

    public static List<Person> mockedUsers(int count) {
        List<Person> mockedPersonList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int nameLength = getRandomIntInRange(3, 7);
            Person person = new Person(RandomStringUtils.randomAlphabetic(nameLength), RandomStringUtils.randomAlphabetic(12));

            int numberOfRoles = Constants.SECURITY.values().length;
            int ordinal = getRandomIntInRange(1, numberOfRoles + 1);
            Constants.SECURITY value = Constants.SECURITY.values()[ordinal - 1];
            person.setRoles(Sets.newHashSet(value));
            mockedPersonList.add(person);
        }
        return mockedPersonList;
    }

    private static int getRandomIntInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private static final class Tags {
        public static final Post.Tag tagZero = new Post.Tag("tag number 0");
        public static final Post.Tag tagFirst = new Post.Tag("tag number 1");
        public static final Post.Tag tagSecond = new Post.Tag("tag number 2");
        public static final Post.Tag tagThird = new Post.Tag("tag number 3");
        public static final Post.Tag tagFourth = new Post.Tag("tag number 4");
        public static final Post.Tag tagFifth = new Post.Tag("tag number 5");
    }
}
