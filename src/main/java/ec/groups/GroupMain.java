package ec.groups;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class GroupMain {
    public static void main(String[] args) {

        Person bezawit= new Person("Bezawit", 99, Sex.FEMALE);
        Person tesfaye = new Person("Tesfaye", 90, Sex.MALE);
        Person dawit = new Person("Dawit", 35, Sex.MALE);
        Person melat = new Person("Melat", 30, Sex.FEMALE);
        Person bontu = new Person("Bontu", 1, Sex.MALE);
        Person trhas = new Person("Trhas", 5, Sex.FEMALE);
        Person lili = new Person("Lili", 7, Sex.FEMALE);

        Group group = new Group();
        Stream.of(bezawit, tesfaye, dawit, melat, bontu, trhas, lili).forEach(person -> {
            group.addMember(person)
                    .ifPresent(msg -> System.out.println(person.getName() + " Didn't get to join the group too " + msg));
        });

        group.getMembers().forEach(System.out::println);
    }
}

@Data
class Group {
    private List<Person> members = new ArrayList<>();

    public Optional<String> addMember(Person applicant) {
         AgeGroup applicantAgeGroup =  calculateAgeGroup(applicant);
         try {
             switch (applicantAgeGroup) {
                 case OLD -> verifyThatOnlyTwoOldPeopleAllowedInGroup();
                 case YOUNG -> verifyOnlyOneFromSameSexAllowedInGroup(applicant);
                 default -> verifyOnlyOneFromSameAgeGroupIsAllowedInGroup(applicantAgeGroup);
             }
             members.add(applicant);
             return Optional.empty();
         }catch (MemberShipDeniedException e) {
             return Optional.of(e.getMessage());
         }

    }

    private void verifyThatOnlyTwoOldPeopleAllowedInGroup() throws MemberShipDeniedException {
        int countOldMembers = countAgeGroup(AgeGroup.OLD);
        if (countOldMembers >= 2)
            throw new MemberShipDeniedException(" Already exist " + countOldMembers + " in a group ");
    }

    private void verifyOnlyOneFromSameSexAllowedInGroup(Person applicant) throws MemberShipDeniedException {
        List<Sex> membersSexes =  listAllMembersSexesForAgeGroup(calculateAgeGroup(applicant));
        if (membersSexes.contains(applicant.getSex()))
            throw new MemberShipDeniedException(" Already exist " + applicant.getSex() + " in a group! ");
    }

    private void verifyOnlyOneFromSameAgeGroupIsAllowedInGroup(AgeGroup applicantAgeGroup) throws MemberShipDeniedException {
        List<AgeGroup> memberAgeGroups = listAllMembersAgeGroups();
        if (memberAgeGroups.contains(applicantAgeGroup))
            throw new MemberShipDeniedException(" Already exist " + applicantAgeGroup + " in a group! ");
    }

    private List<AgeGroup> listAllMembersAgeGroups() {
        List<AgeGroup> ageGroups = new LinkedList<>();
        for (Person member : members) {
            AgeGroup memberAgeGroup = calculateAgeGroup(member);
            if (!ageGroups.contains(memberAgeGroup))
                ageGroups.add(memberAgeGroup);
        }
        return ageGroups;
    }

    private AgeGroup calculateAgeGroup(Person person) {
        if (person.getAge() <= 20)
            return AgeGroup.YOUNG;
        if (person.getAge() <= 50)
            return AgeGroup.MIDDLE;
        return AgeGroup.OLD;
    }

    private int countAgeGroup(AgeGroup ageGroup) {
        int countAgeGroup = 0;
        for (Person member : members) {
            AgeGroup memberAgeGroup = calculateAgeGroup(member);
            if (memberAgeGroup.equals(ageGroup))
                countAgeGroup++;
        }
        return countAgeGroup;
    }

    private List<Sex> listAllMembersSexesForAgeGroup(AgeGroup ageGroup) {
        List<Sex> ageGroups = new LinkedList<>();
        for (Person member : members) {
            if (!ageGroups.contains(member.getSex()) && calculateAgeGroup(member).equals(ageGroup))
                ageGroups.add(member.getSex());
        }
        return ageGroups;
    }
}

@Data @AllArgsConstructor
class Person {
    private String name;
    private int age;
    private Sex sex;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}

enum AgeGroup {
    YOUNG,
    MIDDLE,
    OLD
}

enum Sex {
    MALE,
    FEMALE,
}

class MemberShipDeniedException extends Exception {
    public MemberShipDeniedException(String message) {
        super(message);
    }
}
